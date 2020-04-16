package com.liucccc.demo.commonquery.controller;

import com.liucccc.demo.common.api.CommonResult;
import com.liucccc.demo.common.utils.RedisUtil;
import com.liucccc.demo.commonquery.pojo.vo.CommonQueryResultVO;
import com.liucccc.demo.commonquery.pojo.vo.CommonQueryVO;
import com.liucccc.demo.commonquery.service.CommonQueryService;
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
 * @created date 2020/4/13 16:56
 */
@RestController
@RequestMapping("/api/common-query")
public class CommonQueryController {

    @Autowired
    private CommonQueryService commonQueryService;
    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/getPageList")
    public CommonResult getPageList(@RequestBody CommonQueryVO commonQueryVO) {
        return CommonResult.success(commonQueryService.getPageList(commonQueryVO));
    }

}
