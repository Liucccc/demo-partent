package com.liucccc.demo.biz.sys;

import com.liucccc.demo.dao.sys.entity.SysQueryPrimary;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liucccc.demo.dao.sys.entity.SysQueryTemplateCols;

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
