package com.liucccc.demo.admin.controller.sys;

import com.liucccc.demo.admin.dao.sys.entity.SysUser;
import com.liucccc.demo.admin.service.sys.SysUserService;
import com.liucccc.demo.common.api.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * UserController
 *
 * @author liuchao
 * @date 2020/2/10 18:31
 */
@RestController
@RequestMapping("/api/sys/user")
public class UserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/getUserInfo")
    public CommonResult getUserInfo(Principal principal){
        String name = principal.getName();
        SysUser userInfo = sysUserService.getUserInfoByUserName(name);
        Map<String, Object> data = new HashMap<>();
        data.put("name",userInfo.getUsername());
        data.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return CommonResult.success(data);
    }
}
