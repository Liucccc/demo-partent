package com.liucccc.demo.dao.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Liucccc
 * @date 2019/12/11 21:21
 */
@Data
public class UmsAdmin {
    private Long id;

    private String username;

    private String password;

    private String icon;

    private String email;

    private String nickName;

    private String note;

    private LocalDateTime createTime;

    private LocalDateTime loginTime;

    private Integer status;
}
