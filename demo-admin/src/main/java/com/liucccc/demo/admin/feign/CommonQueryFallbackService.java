package com.liucccc.demo.admin.feign;

import com.liucccc.demo.admin.pojo.vo.CommonQueryVO;
import com.liucccc.demo.common.api.CommonResult;
import org.springframework.stereotype.Component;

/**
 * 通用查询服务降级处理类
 * <br>
 *
 * @author liuchao
 * @created date 2020/4/14 14:35
 */
@Component
public class CommonQueryFallbackService implements CommonQueryService {
    @Override
    public CommonResult getPageList(CommonQueryVO commonQueryVO) {
        return CommonResult.failed("调用失败，服务被降级！");
    }
}
