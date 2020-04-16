package com.liucccc.demo.admin.service.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liucccc.demo.admin.dao.sys.entity.SysPermission;
import com.liucccc.demo.admin.pojo.dto.sys.PermissionPageQueryDTO;

/**
 * <p>
 * 后台用户权限表 服务类
 * </p>
 *
 * @author mybatis-plus
 * @since 2020-02-06
 */
public interface SysPermissionService extends IService<SysPermission> {

    IPage<SysPermission> permissionPageList(PermissionPageQueryDTO permissionPageQueryDTO);
}
