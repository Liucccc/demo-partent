package com.liucccc.demo.biz.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liucccc.demo.biz.sys.SysQueryTemplateService;
import com.liucccc.demo.common.exception.BizException;
import com.liucccc.demo.dao.sys.entity.SysQueryPrimary;
import com.liucccc.demo.dao.sys.entity.SysQueryTemplate;
import com.liucccc.demo.dao.sys.entity.SysQueryTemplateCols;
import com.liucccc.demo.dao.sys.mapper.SysQueryPrimaryMapper;
import com.liucccc.demo.biz.sys.SysQueryPrimaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 标准查询表 服务实现类
 * </p>
 *
 * @author mybatis-plus
 * @since 2020-02-18
 */
@Slf4j
@Service
public class SysQueryPrimaryServiceImpl extends ServiceImpl<SysQueryPrimaryMapper, SysQueryPrimary> implements SysQueryPrimaryService {

    @Autowired
    private SysQueryPrimaryMapper sysQueryPrimaryMapper;
    @Autowired
    private SysQueryTemplateService sysQueryTemplateService;

    @Override
    public boolean updateQueryPrimary(String templateId, List<SysQueryTemplateCols> colsList) {
        if (CollectionUtils.isEmpty(colsList)) {
            throw new BizException("列不能为空");
        }
        StringBuilder showCols = new StringBuilder();
        StringBuilder showColsCode = new StringBuilder();
        StringBuilder statCols = new StringBuilder();
        for (int i = 0; i < colsList.size(); i++) {
            if (i > 0) {
                showCols.append(",");
                showColsCode.append(",");
                statCols.append(",");
            }
            showCols.append(String.format("%s AS \"%s\"", colsList.get(i).getColExpr(), colsList.get(i).getColName()));
            showColsCode.append(colsList.get(i).getTmpleCode());
            if (BigDecimal.valueOf(1) == colsList.get(i).getStaFlag()) {
                if (colsList.get(i).getColExpr().toUpperCase().contains("SUM(")) {
                    statCols.append(String.format("%s AS \"%s\"", colsList.get(i).getColExpr(), colsList.get(i).getColName()));
                } else {
                    statCols.append(String.format("SUM(%s) AS \"%s\"", colsList.get(i).getColExpr(), colsList.get(i).getColName()));
                }
            }
        }
        SysQueryPrimary primary = sysQueryPrimaryMapper.selectOne(new LambdaQueryWrapper<SysQueryPrimary>().eq(SysQueryPrimary::getQueryId, templateId));
        SysQueryTemplate sysQueryTemplate = sysQueryTemplateService.getOne(new LambdaQueryWrapper<SysQueryTemplate>().eq(SysQueryTemplate::getTemplateId, templateId));
        if (sysQueryTemplate == null) {
            throw new BizException("查询模板为空！");
        }
        SysQueryPrimary sysQueryPrimary = new SysQueryPrimary();
        sysQueryPrimary.setQueryName(sysQueryTemplate.getTemplateName());
        sysQueryPrimary.setFixeCols(sysQueryTemplate.getFixedCols());
        sysQueryPrimary.setTabLst(sysQueryTemplate.getTabLst());
        sysQueryPrimary.setSqlWhere(sysQueryTemplate.getSqlWhere());
        sysQueryPrimary.setSqlOrderBy(sysQueryTemplate.getSqlOrderBy());
        sysQueryPrimary.setShowCols(showCols.toString());
        sysQueryPrimary.setShowColsCode(showColsCode.toString());
        sysQueryPrimary.setStaCols(statCols.toString());
        if (primary == null) {
            sysQueryPrimary.setQueryId(templateId);
            int count = sysQueryPrimaryMapper.insert(sysQueryPrimary);
            if (count <= 0) {
                log.info("添加SysQueryPrimary失败：【{},{}】", templateId, colsList.toString());
                throw new BizException("添加失败！");
            }
        } else {
            int count = sysQueryPrimaryMapper.update(sysQueryPrimary, lambdaUpdate().eq(SysQueryPrimary::getQueryId, templateId));
            if (count <= 0) {
                log.info("更新SysQueryPrimary失败：【{},{}】", templateId, colsList.toString());
                throw new BizException("更新失败！");
            }
        }
        return true;
    }
}
