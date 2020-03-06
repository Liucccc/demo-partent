package com.liucccc.demo.biz.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liucccc.demo.biz.pojo.bo.SecurityUserDetails;
import com.liucccc.demo.common.utils.RedisUtil;
import com.liucccc.demo.dao.sys.entity.SysPermission;
import com.liucccc.demo.dao.sys.entity.SysUser;
import com.liucccc.demo.dao.sys.mapper.SysRolePermissionMapper;
import com.liucccc.demo.dao.sys.mapper.SysUserMapper;
import com.liucccc.demo.biz.sys.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liucccc.demo.security.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author mybatis-plus
 * @since 2020-02-06
 */
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public UserDetails loadUserByUsername(String username) {
        val userInfo = getUserInfoByUserName(username);
        if (userInfo == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        List<SysPermission> permissionList = getPermissionList(userInfo.getUserId());
        return new SecurityUserDetails(userInfo, permissionList);
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Override
    public SysUser getUserInfoByUserName(String username) {
        QueryWrapper<SysUser> sysUserQueryWrapper = new QueryWrapper<>();
        sysUserQueryWrapper.eq("username", username);
        return sysUserMapper.selectOne(sysUserQueryWrapper);
    }

    @Override
    public String refreshToken(String token) {
        return jwtTokenUtil.refreshHeadToken(token);
    }

    private List<SysPermission> getPermissionList(Long userId) {
        return sysRolePermissionMapper.getPermissionList(userId);
    }
}
