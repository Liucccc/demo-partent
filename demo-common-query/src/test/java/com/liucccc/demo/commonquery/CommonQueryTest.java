package com.liucccc.demo.commonquery;

import com.liucccc.demo.common.utils.RedisUtil;
import com.liucccc.demo.commonquery.pojo.dto.sys.QueryTemplateColsDTO;
import com.liucccc.demo.commonquery.pojo.dto.sys.QueryTemplateInfoDTO;
import com.liucccc.demo.commonquery.pojo.vo.CommonQueryVO;
import com.liucccc.demo.commonquery.service.CommonQueryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * CommonQueryTest
 * <br>
 *
 * @author liuchao
 * @created date 2020/4/13 14:36
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoCommonQueryApplication.class})
public class CommonQueryTest {
    @Autowired
    CommonQueryService commonQueryService;

    @Autowired
    RedisUtil redisUtil;

    @Test
    public void test1() {
        QueryTemplateInfoDTO queryTemplateInfoDTO = new QueryTemplateInfoDTO();
        queryTemplateInfoDTO.setTemplateId("test1");
        queryTemplateInfoDTO.setTemplateName("test1name");
        queryTemplateInfoDTO.setFixedCols("t.role_id as 'id'");
        queryTemplateInfoDTO.setTabLst("sys_role t");
        queryTemplateInfoDTO.setSqlWhere("t.role_id=1");
        queryTemplateInfoDTO.setSqlOrderBy("t.role_id");
        queryTemplateInfoDTO.setMenuId("menu");
        QueryTemplateColsDTO queryTemplateColsDTO;
        List<QueryTemplateColsDTO> colsDTOList = new ArrayList<>();
        List<QueryTemplateColsDTO> setColsDTOList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            queryTemplateColsDTO = new QueryTemplateColsDTO();
            queryTemplateColsDTO.setColExpr(String.valueOf(i));
            queryTemplateColsDTO.setColName(i + "Name");
            if (i % 2 == 0) {
                queryTemplateColsDTO.setStaFlag(BigDecimal.ONE);
                queryTemplateColsDTO.setUserFlag(BigDecimal.ONE);
                setColsDTOList.add(queryTemplateColsDTO);
            }
            colsDTOList.add(queryTemplateColsDTO);
        }
//        QueryTemplateSubDTO queryTemplateSubDTO = new QueryTemplateSubDTO();
//        List<QueryTemplateSubDTO> subDTOList = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            queryTemplateSubDTO.setSqlExpr("setSqlExpr");
//            queryTemplateSubDTO.setSqlGroupBy("setSqlGroup");
//            queryTemplateSubDTO.setSqlWhere("setSqlWhere");
//            queryTemplateSubDTO.setSubId("");
//            queryTemplateSubDTO.setTabAlias("setTabAlias");
//            subDTOList.add(queryTemplateSubDTO);
//        }
        queryTemplateInfoDTO.setColsList(colsDTOList);
        queryTemplateInfoDTO.setSetColsList(setColsDTOList);
//        queryTemplateInfoDTO.setSubList(subDTOList);

        boolean res = commonQueryService.saveQueryTemplateInfo(false, queryTemplateInfoDTO);
        Assert.assertTrue(res);
    }

    @Test
    public void test2() {
        CommonQueryVO commonQueryVO = new CommonQueryVO();
        commonQueryVO.setTemplateId("test1");
        commonQueryService.getPageList(commonQueryVO);
    }

    @Test
    public void testRedit(){
        redisUtil.set("aaa","1123");
        System.out.println(redisUtil.get("aaa"));
    }
}
