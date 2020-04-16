package com.liucccc.demo.admin.controller.sys;

import com.liucccc.demo.admin.feign.CommonQueryService;
import com.liucccc.demo.admin.pojo.vo.CommonQueryVO;
import com.liucccc.demo.common.api.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CommonQueryController
 * <br>
 *
 * @author liuchao
 * @created date 2020/4/13 18:04
 */
@RestController
@RequestMapping("/api/common-query")
public class CommonQueryController {
    @Autowired
    private CommonQueryService commonQueryService;

    @PostMapping("/getPageList")
    public CommonResult getPageList(@RequestBody CommonQueryVO commonQueryVO) {
        CommonResult pageList = commonQueryService.getPageList(commonQueryVO);
        return pageList ;
    }
}
