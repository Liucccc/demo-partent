package com.liucccc.demo.admin.feign;

import com.liucccc.demo.admin.pojo.vo.CommonQueryVO;
import com.liucccc.demo.common.api.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * CommonQueryService
 * <br>
 *
 * @author liuchao
 * @created date 2020/4/13 16:45
 */
@FeignClient(value = "LIUCCCC-DEMO-COMMON-QUERY", fallback = CommonQueryFallbackService.class)
public interface CommonQueryService {

    @PostMapping("/api/common-query/getPageList")
    CommonResult getPageList(CommonQueryVO commonQueryVO);
}
