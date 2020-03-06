package com.liucccc.demo.dao.sys.mapper;

import com.liucccc.demo.dao.sys.entity.SysPermission;
import com.liucccc.demo.dao.sys.entity.SysRolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色与权限关系表 Mapper 接口
 * </p>
 *
 * @author mybatis-plus
 * @since 2020-02-06
 */
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission> {

    List<SysPermission> getPermissionList(Long userId);
}
