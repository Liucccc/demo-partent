package com.liucccc.demo.commonquery.pojo.dto.sys;

import lombok.Data;

import java.math.BigDecimal;

/**
 * QueryTemplateColsDTO
 * <br>
 *
 * @author liuchao
 * @created date 2020/2/18 15:09
 */
@Data
public class QueryTemplateColsDTO {
    private String colName;

    private String colExpr;

    private BigDecimal staFlag;

    private BigDecimal userFlag;
}
