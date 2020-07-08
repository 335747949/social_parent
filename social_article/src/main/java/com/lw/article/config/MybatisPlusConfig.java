package com.lw.article.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lanwei
 * @email 335747949@qq.com
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor().setDialectType("mysql");
    }


    /**
     * 乐观锁拦截器
     */
    @Bean
    public OptimisticLockerInterceptor getOptimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }


    /**
     * 逻辑删除，sql注入器  3.1.1版本不在需要
     * @return
     */
    @Bean
    public ISqlInjector sqlInjector(){
        return new LogicSqlInjector();
    }

}

