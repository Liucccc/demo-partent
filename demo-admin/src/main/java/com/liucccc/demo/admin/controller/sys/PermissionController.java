package com.liucccc.demo.admin.controller.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.liucccc.demo.admin.dao.sys.entity.SysPermission;
import com.liucccc.demo.admin.pojo.dto.sys.PermissionPageQueryDTO;
import com.liucccc.demo.admin.service.sys.SysPermissionService;
import com.liucccc.demo.common.api.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PermissionController
 * <br>
 *
 * @author liuchao
 * @created date 2020/2/16 14:11
 */
@RestController
@RequestMapping("/api/sys/permission")
public class PermissionController {

    @Autowired
    private SysPermissionService sysPermissionService;

    @PostMapping("/permissionPageList")
    public CommonResult permissionPageList(PermissionPageQueryDTO permissionPageQueryDTO) {
        IPage<SysPermission> page = sysPermissionService.permissionPageList(permissionPageQueryDTO);
        return CommonResult.success(page);
    }

}
