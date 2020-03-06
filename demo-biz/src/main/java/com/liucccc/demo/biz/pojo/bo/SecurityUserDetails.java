package com.liucccc.demo.biz.pojo.bo;

import com.liucccc.demo.dao.sys.entity.SysPermission;
import com.liucccc.demo.dao.sys.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SecurityUserDetails
 *
 * @author liuchao
 * @date 2020/2/6 18:01
 */
public class SecurityUserDetails implements UserDetails {
    private SysUser sysUser;
    private List<SysPermission> sysPermissionList;

    public SecurityUserDetails(SysUser sysUser, List<SysPermission> sysPermissionList) {
        this.sysUser = sysUser;
        this.sysPermissionList = sysPermissionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限
        return sysPermissionList.stream()
                .filter(permission -> permission.getValue() != null)
                .map(permission -> new SimpleGrantedAuthority(permission.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return sysUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return sysUser.getStatus().equals("1");
    }
}
