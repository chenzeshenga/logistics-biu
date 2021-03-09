package com.abc.chenzeshenga.logistics.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenzeshenga
 */
@Configuration
@MapperScan({"com.abc.dao", "com.abc.chenzeshenga.logistics.mapper"})
public class MyBatisPlusConfig {

    /**
     * 分页插件，自动识别数据库类型
     *
     * @return 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
