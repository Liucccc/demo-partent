package com.liucccc.demo.admin.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * CommonQueryResultVO
 * <br>
 *
 * @author liuchao
 * @created date 2020/3/4 17:54
 */
@Data
public class CommonQueryResultVO {
    private List<Object> records;
    private List<Object> staRecords;
    private long total;
    private long size;
    private long current;
}
