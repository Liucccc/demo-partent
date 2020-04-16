package com.liucccc.demo.commonquery.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 标准查询表
 * </p>
 *
 * @author mybatis-plus
 * @since 2020-02-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysQueryPrimary extends Model<SysQueryPrimary> {

    private static final long serialVersionUID=1L;

    /**
     * 查询标识
     */
    @TableId(value = "query_id")
    private String queryId;

    /**
     * 查询名称
     */
    private String queryName;

    /**
     * 固定列
     */
    private String fixeCols;

    /**
     * 查询列集合
     */
    private String showCols;

    /**
     * 统计列集合
     */
    private String staCols;

    /**
     * 表集合
     */
    private String tabLst;

    /**
     * 表关联集合
     */
    private String tabRel;

    /**
     * 附加查询条件
     */
    private String sqlWhere;

    private String sqlOrderBy;

    /**
     * 分组字段,多个字段以逗号分割
     */
    private String sqlGroupBy;

    /**
     * 查询列集合  code拼接
     */
    private String showColsCode;


    @Override
    protected Serializable pkVal() {
        return this.queryId;
    }

}
