package com.liucccc.demo.admin.pojo.vo;

import lombok.Data;

import java.util.LinkedHashMap;

/**
 * CommonQueryVO
 * <br>
 *
 * @author liuchao
 * @created date 2020/2/26 16:31
 */
@Data
public class CommonQueryVO {
    /**
     * 模板编号
     */
    private String templateId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 当前的页数
     */
    private int currentPage = 1;

    /**
     * 一页多少条记录
     */
    private int pageSize = 20;

    /**
     * 查询搜索条件 主查询条件的  where查询  select * from where  [ AND xxxxx]
     * where 后面的SQL条件
     */
    private String searchWhere;

    /**
     * 排序条件,以字段^0或者字段^1传输，0表示DESC，1表示ASC
     */
    private String sortKeys;

    /**
     * with as 的查询条件替换
     */
    private LinkedHashMap<String, String> subWhereMap;

    /**
     * parentId
     */
    private String parentId;
}
