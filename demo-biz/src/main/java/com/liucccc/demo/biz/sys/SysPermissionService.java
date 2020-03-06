package com.liucccc.demo.biz.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.liucccc.demo.biz.pojo.dto.sys.PermissionPageQueryDTO;
import com.liucccc.demo.dao.sys.entity.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

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
