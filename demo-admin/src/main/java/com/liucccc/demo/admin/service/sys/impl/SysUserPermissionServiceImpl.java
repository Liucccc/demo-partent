package com.liucccc.demo.admin.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liucccc.demo.admin.dao.sys.entity.SysUserPermission;
import com.liucccc.demo.admin.dao.sys.mapper.SysUserPermissionMapper;
import com.liucccc.demo.admin.service.sys.SysUserPermissionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户和权限关系表(除角色中定义的权限以外的加减权限) 服务实现类
 * </p>
 *
 * @author mybatis-plus
 * @since 2020-02-06
 */
@Service
public class SysUserPermissionServiceImpl extends ServiceImpl<SysUserPermissionMapper, SysUserPermission> implements SysUserPermissionService {

}
