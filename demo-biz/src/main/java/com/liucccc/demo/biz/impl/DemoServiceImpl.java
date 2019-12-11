package com.liucccc.demo.biz.impl;

import com.liucccc.demo.biz.DemoService;
import com.liucccc.demo.dao.entity.UmsAdmin;
import com.liucccc.demo.dao.mapper.UmsAdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Liucccc
 * @date 2019/12/11 21:02
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private UmsAdminMapper umsAdminMapper;

    @Override
    public String Test() {
        UmsAdmin umsAdmin = umsAdminMapper.selectByPrimaryKey(1L);
        return umsAdmin.toString();
    }
}
