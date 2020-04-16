package com.liucccc.demo.commonquery.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * sqlMapper
 * <br>
 *
 * @author liuchao
 * @created date 2020/3/4 15:21
 */
public interface SqlMapper {
    List<Map> getList(@Param("sql") String sql);
    Object getObject(@Param("sql") String sql);
}
