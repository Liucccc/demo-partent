package com.liucccc.demo.dao.sys.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author mybatis-plus
 * @since 2020-02-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysQueryTemplateSub extends Model<SysQueryTemplateSub> {

    private static final long serialVersionUID=1L;

    @TableId(value = "sub_id")
    private String subId;

    private String templateId;

    private String tabAlias;

    private String sqlExpr;

    private String sqlWhere;

    private String sqlGroupBy;

    private BigDecimal delFlag;


    @Override
    protected Serializable pkVal() {
        return this.subId;
    }

}
