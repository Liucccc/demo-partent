package com.liucccc.demo.biz.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.liucccc.demo.biz.pojo.dto.sys.QueryTemplateInfoDTO;
import com.liucccc.demo.biz.pojo.vo.CommonQueryResultVO;
import com.liucccc.demo.biz.pojo.vo.CommonQueryVO;
import com.liucccc.demo.biz.sys.*;
import com.liucccc.demo.common.exception.BizException;
import com.liucccc.demo.common.utils.UUIDGenerator;
import com.liucccc.demo.dao.common.sqlMapper;
import com.liucccc.demo.dao.sys.entity.SysQueryPrimary;
import com.liucccc.demo.dao.sys.entity.SysQueryTemplate;
import com.liucccc.demo.dao.sys.entity.SysQueryTemplateCols;
import com.liucccc.demo.dao.sys.entity.SysQueryTemplateSub;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * CommonQueryServiceImpl
 * <br>
 *
 * @author liuchao
 * @created date 2020/2/18 14:58
 */
@Slf4j
@Service
public class CommonQueryServiceImpl implements CommonQueryService {

    @Autowired
    private SysQueryTemplateService sysQueryTemplateService;
    @Autowired
    private SysQueryTemplateColsService sysQueryTemplateColsService;
    @Autowired
    private SysQueryTemplateSubService sysQueryTemplateSubService;
    @Autowired
    private SysQueryPrimaryService sysQueryPrimaryService;
    @Autowired
    private sqlMapper sqlMapper;

    @Transactional
    @Override
    public boolean saveQueryTemplateInfo(boolean isModify, QueryTemplateInfoDTO queryTemplateInfoDTO) {
        String templateId = queryTemplateInfoDTO.getTemplateId();

        if (!isModify) {
            int count = sysQueryTemplateService.count(new LambdaQueryWrapper<SysQueryTemplate>().eq(SysQueryTemplate::getTemplateId, templateId));
            if (count > 0) {
                throw new BizException(String.format("%s已存在", templateId));
            }
        }

        SysQueryTemplate sysQueryTemplate = new SysQueryTemplate();
        BeanUtils.copyProperties(queryTemplateInfoDTO, sysQueryTemplate);
        boolean result = sysQueryTemplateService.saveOrUpdate(sysQueryTemplate,
                new LambdaQueryWrapper<SysQueryTemplate>().eq(SysQueryTemplate::getTemplateId, templateId));
        if (!result) {
            log.info("保存模板失败：【{}】", queryTemplateInfoDTO.toString());
            throw new BizException("保存模板失败！");
        }
        sysQueryTemplateColsService.remove(new LambdaQueryWrapper<SysQueryTemplateCols>().eq(SysQueryTemplateCols::getTemplateId, templateId));
        SysQueryTemplateCols sysQueryTemplateCols = new SysQueryTemplateCols();
        List<SysQueryTemplateCols> sysQueryTemplateColsList = new ArrayList<>(queryTemplateInfoDTO.getColsList().size());

        for (int i = 0; i < queryTemplateInfoDTO.getColsList().size(); i++) {
            sysQueryTemplateCols = new SysQueryTemplateCols();
            BeanUtils.copyProperties(queryTemplateInfoDTO.getColsList().get(i), sysQueryTemplateCols);
            sysQueryTemplateCols.setTemplateId(templateId);
            for (int j = 0; j < queryTemplateInfoDTO.getSetColsList().size(); j++) {
                sysQueryTemplateCols.setUserFlag(BigDecimal.valueOf(0));
                if (queryTemplateInfoDTO.getColsList().get(i).getColExpr().equals(queryTemplateInfoDTO.getSetColsList().get(j).getColExpr())) {
                    sysQueryTemplateCols.setUserFlag(BigDecimal.valueOf(0));
                    sysQueryTemplateCols.setOrderNo(BigDecimal.valueOf(j));
                    break;
                }
            }
            if (StringUtils.isBlank(sysQueryTemplateCols.getTmpleCode())) {
                sysQueryTemplateCols.setTmpleCode(UUIDGenerator.getUUID());
            }
            sysQueryTemplateColsList.add(sysQueryTemplateCols);
        }
        if (!CollectionUtils.isEmpty(sysQueryTemplateColsList)) {
            result = sysQueryTemplateColsService.saveBatch(sysQueryTemplateColsList);
            if (!result) {
                log.info("保存模板列失败：【{}】", queryTemplateInfoDTO.toString());
                throw new BizException("保存模板列失败！");
            }
        }
        if (!CollectionUtils.isEmpty(queryTemplateInfoDTO.getSubList())) {
            result = sysQueryTemplateSubService.update(
                    new LambdaUpdateWrapper<SysQueryTemplateSub>()
                            .in(SysQueryTemplateSub::getSubId, queryTemplateInfoDTO.getSubList())
                            .set(SysQueryTemplateSub::getTemplateId, templateId));
            if (!result) {
                log.info("保存模板子查询失败：【{}】", queryTemplateInfoDTO.toString());
                throw new BizException("保存模板子查询失败！");
            }
        }

        return sysQueryPrimaryService.updateQueryPrimary(templateId, sysQueryTemplateColsList);
    }

    @Override
    public boolean delCommonQuery(String templateId) {
        sysQueryTemplateService.removeById(templateId);
        sysQueryTemplateColsService.remove(new LambdaUpdateWrapper<SysQueryTemplateCols>().eq(SysQueryTemplateCols::getTemplateId, templateId));
        sysQueryTemplateSubService.remove(new LambdaUpdateWrapper<SysQueryTemplateSub>().eq(SysQueryTemplateSub::getTemplateId, templateId));

        return false;
    }

    @Override
    public CommonQueryResultVO getPageList(CommonQueryVO commonQueryVO) {
        if (commonQueryVO == null || StringUtils.isBlank(commonQueryVO.getTemplateId())) {
            throw new BizException("tempLateId不能为空");
        }

        SysQueryTemplate template = sysQueryTemplateService.getById(commonQueryVO.getTemplateId());
        SysQueryPrimary primary = sysQueryPrimaryService.getById(commonQueryVO.getTemplateId());
        StringBuilder sqlFromWhereOrder = new StringBuilder(template.getTabLst());
        StringBuilder sqlWith = new StringBuilder();
        if (StringUtils.isNotBlank(template.getSqlWhere()) && StringUtils.isNotBlank(commonQueryVO.getSearchWhere())) {
            sqlFromWhereOrder.append(" WHERE ").append(commonQueryVO.getSearchWhere()).append(" ").append(template.getSqlWhere());
        } else if (StringUtils.isNotBlank(template.getSqlWhere())) {
            sqlFromWhereOrder.append(" WHERE ").append(template.getSqlWhere());
        } else if (StringUtils.isNotBlank(commonQueryVO.getSearchWhere())) {
            sqlFromWhereOrder.append(" WHERE ").append(commonQueryVO.getSearchWhere());
        }

        if (StringUtils.isNotBlank(commonQueryVO.getSortKeys())) {
            String[] sort = commonQueryVO.getSortKeys().split("^");
            String colName = sort[0];
            String orderBy = sort[1];
            if (StringUtils.isNotBlank(orderBy)) {
                sqlFromWhereOrder.append(" ORDER BY ").append(colName);
                if (orderBy == "1") {
                    sqlFromWhereOrder.append(" ASC");
                } else if (orderBy == "0") {
                    sqlFromWhereOrder.append(" DESC");
                }
            }
        } else if (StringUtils.isNotBlank(template.getSqlOrderBy())) {
            sqlFromWhereOrder.append(" ORDER BY ").append(template.getSqlOrderBy());
        }

        int rowStart = commonQueryVO.getPageSize() * (commonQueryVO.getCurrentPage() - 1) + 1;
        int rowEnd = rowStart + commonQueryVO.getPageSize() - 1;

        List<SysQueryTemplateSub> subList = sysQueryTemplateSubService.lambdaQuery().eq(SysQueryTemplateSub::getTemplateId, commonQueryVO.getTemplateId()).list();
        LinkedHashMap<String, String> subWhereMap = commonQueryVO.getSubWhereMap();

        subList.forEach(m -> {
            if (CollectionUtils.isEmpty(subWhereMap)) {
                subWhereMap.forEach((k, v) -> {
                    if (m.getTabAlias().toUpperCase().equals(k.toUpperCase())) {
                        if (StringUtils.isNotBlank(v)) {
                            sqlWith.append(m.getTabAlias())
                                    .append(" AS(")
                                    .append(m.getSqlExpr())
                                    .append(" WHERE ")
                                    .append(v);

                            if (StringUtils.isNotBlank(m.getSqlWhere())) {
                                sqlWith.append(" AND ")
                                        .append(m.getSqlWhere());
                            }
                            if (StringUtils.isNotBlank(m.getSqlGroupBy())) {
                                sqlWith.append(" GROUP BY ")
                                        .append(m.getSqlGroupBy());
                            }
                            sqlWith.append("),");
                        }
                        return;
                    }
                });
            }
        });
        String sqlFromWhereOrderStr = sqlFromWhereOrder.toString();
        sqlFromWhereOrderStr = sqlFromWhereOrderStr.replaceAll("$PARENT_REF_ID$", commonQueryVO.getParentId());
        sqlFromWhereOrderStr = sqlFromWhereOrderStr.replaceAll("$USER_ID$", commonQueryVO.getUserId());

        int whereIndex = sqlFromWhereOrderStr.toUpperCase().indexOf("WHERE");
        int orderByIndex = sqlFromWhereOrderStr.toUpperCase().indexOf("ORDER BY");
        int groupByIndex = sqlFromWhereOrderStr.toUpperCase().indexOf("GROUP BY");

        String sqlWithStr = "";
        if (sqlWith.length() > 0) {
            sqlWithStr = sqlWith.toString();
            sqlWithStr = "WITH " + sqlWithStr.substring(0, sqlWith.length() - 1);
            sqlWithStr = sqlWithStr.replaceAll("$PARENT_REF_ID$", commonQueryVO.getParentId());
            sqlWithStr = sqlWithStr.replaceAll("$USER_ID$", commonQueryVO.getUserId());
        }

        CommonQueryResultVO commonQueryResultVO = new CommonQueryResultVO();

        String sqlStr = sqlFromWhereOrderStr;
        //统计总行数
        if (groupByIndex > 0 && groupByIndex > whereIndex) {
            sqlStr = "SELECT COUNT(*) AS TOTALROWS FROM (" + sqlWithStr + " SELECT COUNT(*) FROM " + sqlStr + ")";
        } else {
            sqlStr = sqlWithStr + " SELECT COUNT(*) AS TOTALROWS FROM " + sqlStr;
        }
        long total = (long) sqlMapper.getObject(sqlStr);
        commonQueryResultVO.setTotal(total);

        //统计列
        if (orderByIndex > 0 && orderByIndex > whereIndex) {
            sqlStr = sqlWithStr + " SELECT " + primary.getStaCols() + " FROM " + sqlFromWhereOrderStr.substring(0, orderByIndex - 1);
        }
        sqlStr = sqlWithStr + " SELECT " + primary.getStaCols() + " FROM " + sqlFromWhereOrderStr;
        List<Map> listSta = sqlMapper.getList(sqlStr);


        return commonQueryResultVO;
    }
}
