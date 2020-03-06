package com.liucccc.demo.biz.sys;

import com.liucccc.demo.dao.sys.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

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
