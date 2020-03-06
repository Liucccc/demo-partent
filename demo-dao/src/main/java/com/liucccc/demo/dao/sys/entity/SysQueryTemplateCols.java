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
public class SysQueryTemplateCols extends Model<SysQueryTemplateCols> {

    private static final long serialVersionUID=1L;

    private String templateId;

    private String colName;

    private String colExpr;

    private BigDecimal staFlag;

    private BigDecimal userFlag;

    private BigDecimal delFlag;

    private BigDecimal orderNo;

    /**
     * 主键code
     */
    @TableId(value = "tmple_code")
    private String tmpleCode;


    @Override
    protected Serializable pkVal() {
        return this.templateId;
    }

}
