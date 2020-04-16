package com.liucccc.demo.commonquery.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liucccc.demo.commonquery.entity.SysQueryPrimary;
import com.liucccc.demo.commonquery.entity.SysQueryTemplateCols;

import java.util.List;

/**
 * <p>
 * 标准查询表 服务类
 * </p>
 *
 * @author mybatis-plus
 * @since 2020-02-18
 */
public interface SysQueryPrimaryService extends IService<SysQueryPrimary> {
    boolean updateQueryPrimary(String templateId, List<SysQueryTemplateCols> colsList);
}
