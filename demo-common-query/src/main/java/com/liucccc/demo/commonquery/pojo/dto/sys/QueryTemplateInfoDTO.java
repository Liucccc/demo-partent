package com.liucccc.demo.commonquery.pojo.dto.sys;

import lombok.Data;

import java.util.List;

/**
 * TemplateInfoDTO
 * <br>
 *
 * @author liuchao
 * @created date 2020/2/18 15:02
 */
@Data
public class QueryTemplateInfoDTO {

    /**
     * 模板编号
     */
    private String templateId;

    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 菜单节点ID
     */
    private String menuId;

    /**
     * 固定列
     */
    private String fixedCols;

    /**
     * 表集合及关系
     */
    private String tabLst;

    /**
     * 初始查询条件
     */
    private String sqlWhere;

    /**
     * 初始排序条件
     */
    private String sqlOrderBy;

    /**
     * 子查询
     */
    private List<QueryTemplateSubDTO> subList;

    /**
     * 可选列集合
     */
    private List<QueryTemplateColsDTO> colsList;

    /**
     * 默认列集合
     */
    private List<QueryTemplateColsDTO> setColsList;
}
