package com.liucccc.demo.biz.sys;

import com.liucccc.demo.dao.sys.entity.SysUserPermission;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户和权限关系表(除角色中定义的权限以外的加减权限) 服务类
 * </p>
 *
 * @author mybatis-plus
 * @since 2020-02-06
 */
public interface SysUserPermissionService extends IService<SysUserPermission> {

}
