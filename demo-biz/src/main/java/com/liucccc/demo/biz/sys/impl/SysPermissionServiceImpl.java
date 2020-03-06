package com.liucccc.demo.biz.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liucccc.demo.biz.pojo.dto.sys.PermissionPageQueryDTO;
import com.liucccc.demo.dao.sys.entity.SysPermission;
import com.liucccc.demo.dao.sys.mapper.SysPermissionMapper;
import com.liucccc.demo.biz.sys.SysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户权限表 服务实现类
 * </p>
 *
 * @author mybatis-plus
 * @since 2020-02-06
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public IPage<SysPermission> permissionPageList(PermissionPageQueryDTO permissionPageQueryDTO) {
        QueryWrapper<SysPermission> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(permissionPageQueryDTO.getName())) {
            queryWrapper.like("name", permissionPageQueryDTO.getName());
        }
        Page<SysPermission> page = new Page<>();
        page.setCurrent(permissionPageQueryDTO.getCurrentPage());
        page.setSize(permissionPageQueryDTO.getPageSize());
        return sysPermissionMapper.selectPage(page, queryWrapper);
    }
}
