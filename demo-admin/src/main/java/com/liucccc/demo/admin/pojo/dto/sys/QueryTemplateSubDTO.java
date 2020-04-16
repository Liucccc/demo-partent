package com.liucccc.demo.admin.pojo.dto.sys;

import lombok.Data;

/**
 * QueryTemplateSubDTO
 * <br>
 *
 * @author liuchao
 * @created date 2020/2/18 15:11
 */
@Data
public class QueryTemplateSubDTO {
    private String subId;

    private String templateId;

    private String tabAlias;

    private String sqlExpr;

    private String sqlWhere;

    private String sqlGroupBy;
}
