package com.liucccc.demo.admin.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liucccc.demo.admin.dao.sys.entity.SysUser;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author mybatis-plus
 * @since 2020-02-06
 */
public interface SysUserService extends IService<SysUser> {

    UserDetails loadUserByUsername(String username);

    String login(String username, String password);

    SysUser getUserInfoByUserName(String username);

    String refreshToken(String token);
}
