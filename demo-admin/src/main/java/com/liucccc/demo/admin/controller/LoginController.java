package com.liucccc.demo.admin.controller;

import com.liucccc.demo.admin.pojo.dto.LoginDTO;
import com.liucccc.demo.admin.service.sys.SysUserService;
import com.liucccc.demo.common.api.CommonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * LoginController
 *
 * @author liuchao
 * @date 2020/2/7 17:53
 */
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private SysUserService sysUserService;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @ApiOperation("登录")
    @PostMapping("/login")
    public CommonResult login(@Valid @RequestBody LoginDTO loginDTO, BindingResult result) {
        String token = sysUserService.login(loginDTO.getUsername(), loginDTO.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation("刷新token")
    @GetMapping("/refreshToken")
    public CommonResult refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = sysUserService.refreshToken(token);
        if (refreshToken == null) {
            return CommonResult.failed("token已经过期！");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation("注销")
    @GetMapping("/logout")
    public CommonResult logout(){
        return CommonResult.success();
    }
}
