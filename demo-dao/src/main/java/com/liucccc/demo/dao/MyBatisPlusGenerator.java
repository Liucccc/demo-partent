package com.liucccc.demo.dao;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class MyBatisPlusGenerator {

    public static void main(String[] args) {
        // 1. 全局配置
        GlobalConfig config = new GlobalConfig();
        // 是否支持AR模式
        config.setActiveRecord(true)
                // 作者
                .setAuthor("mybatis-plus")
                // TODO 生成路径
                .setOutputDir("/Users/liucccc/temp/")
                // 文件覆盖
                .setFileOverride(true)
                // 主键策略
                .setIdType(IdType.AUTO)
                // 设置生成的service接口的名字的首字母是否为I，如IEmployeeService
                .setServiceName("%sService")
                // 生成基本的resultMap
                .setBaseResultMap(true)
                .setBaseColumnList(true)
                // 生成基本的SQL片段p
                .setEnableCache(false);

        // 2. 数据源配置
        DataSourceConfig dsConfig = new DataSourceConfig();
        dsConfig.setDbType(DbType.MYSQL)
                // 设置数据库类型
                .setDriverName("com.mysql.cj.jdbc.Driver")
                // TODO 数据库连接
                .setUrl("jdbc:mysql://localhost:3306/liucccc?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai")
                // TODO 用户名
                .setUsername("root")
                // TODO 密码
                .setPassword("123456");

        // 3. 策略配置globalConfiguration中
        StrategyConfig stConfig = new StrategyConfig();
        stConfig.setCapitalMode(true)
                // 全局大写命名
                // .setDbColumnUnderline(true)
                // 指定表名 字段名是否使用下划线
                // 数据库表映射到实体的命名策略
                .setNaming(NamingStrategy.underline_to_camel)
                //.setTablePrefix("tb≤_")
                // 表的前缀
                // TODO 要生成的表
                .setInclude("sys_query_template_sub")
                .setEntityLombokModel(true)
                .setRestControllerStyle(true);

        // 4. 包名策略配置
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent("com.liucccc.demo")
                // dao
                .setMapper("dao.sys.mapper")
                // servcie
                .setService("biz.sys")
                // serviceImpl
                .setServiceImpl("biz.sys.impl")
                // controller
                .setController("web.controller")
                .setEntity("dao.sys.entity")
                // mapper.xml
                .setXml("mapper.sys");

        // 5. 整合配置
        AutoGenerator ag = new AutoGenerator();
        ag.setGlobalConfig(config)
                .setDataSource(dsConfig)
                .setStrategy(stConfig)
                .setPackageInfo(pkConfig);

        // 6. 执行
        ag.execute();
    }
}
