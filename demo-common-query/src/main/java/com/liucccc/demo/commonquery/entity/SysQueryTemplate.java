package com.liucccc.demo.commonquery.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 查询模板参数表
 * </p>
 *
 * @author mybatis-plus
 * @since 2020-02-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysQueryTemplate extends Model<SysQueryTemplate> {

    private static final long serialVersionUID=1L;

    /**
     * 模板标识
     */
    @TableId(value = "template_id")
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
     * 录入人ID
     */
    private String inputUserId;

    /**
     * 录入人
     */
    private String inputUserName;

    /**
     * 录入时间
     */
    private LocalDateTime inputTime;

    /**
     * 启用标志 1启用、0停用
     */
    private BigDecimal enabled;

    /**
     * 停用备注
     */
    private String enabledRemark;


    @Override
    protected Serializable pkVal() {
        return this.templateId;
    }

}
