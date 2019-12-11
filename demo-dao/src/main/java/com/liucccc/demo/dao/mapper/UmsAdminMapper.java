package com.liucccc.demo.dao.mapper;

import com.liucccc.demo.dao.entity.UmsAdmin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Liucccc
 * @date 2019/12/11 21:22
 */
public interface UmsAdminMapper {
    UmsAdmin selectByPrimaryKey(Long id);
}
