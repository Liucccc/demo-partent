package com.liucccc.demo.web;

import com.liucccc.demo.biz.pojo.dto.sys.QueryTemplateColsDTO;
import com.liucccc.demo.biz.pojo.dto.sys.QueryTemplateInfoDTO;
import com.liucccc.demo.biz.pojo.dto.sys.QueryTemplateSubDTO;
import com.liucccc.demo.biz.pojo.vo.CommonQueryVO;
import com.liucccc.demo.biz.sys.CommonQueryService;
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
 * MyTest
 * <br>
 *
 * @author liuchao
 * @created date 2020/2/23 10:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoWebApplication.class})
public class MyTest {

    @Autowired
    CommonQueryService commonQueryService;

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
    public void test2(){
        CommonQueryVO commonQueryVO = new CommonQueryVO();
        commonQueryVO.setTemplateId("test1");
        commonQueryService.getPageList(commonQueryVO);
    }
}
