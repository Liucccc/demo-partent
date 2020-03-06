package com.liucccc.demo.biz.sys;

import com.liucccc.demo.biz.pojo.dto.sys.QueryTemplateInfoDTO;
import com.liucccc.demo.biz.pojo.vo.CommonQueryResultVO;
import com.liucccc.demo.biz.pojo.vo.CommonQueryVO;

/**
 * 通用查询服务
 * <br>
 *
 * @author liuchao
 * @created date 2020/2/18 14:58
 */
public interface CommonQueryService {
    boolean saveQueryTemplateInfo(boolean isModify, QueryTemplateInfoDTO queryTemplateInfoDTO);
    boolean delCommonQuery(String templateId);
    CommonQueryResultVO getPageList(CommonQueryVO commonQueryVO);
}
