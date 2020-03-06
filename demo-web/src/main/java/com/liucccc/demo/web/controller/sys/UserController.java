package com.liucccc.demo.web.controller.sys;

import com.liucccc.demo.biz.sys.SysUserService;
import com.liucccc.demo.common.api.CommonResult;
import com.liucccc.demo.dao.sys.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
