package com.ysk.codeplatform.sql.mysql.multiple_data_sources.config;


import com.baomidou.mybatisplus.core.MybatisSessionFactoryBuilder;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author wangqm-b
 * @create 2018/7/19
 */
@Configuration
public class RecordDataSourceConfiguration {

    //配置数据库

    @Bean(value = RecordDataSourceConfig.DB_RECORD, destroyMethod = "")
    @ConfigurationProperties(RecordDataSourceConfig.DB_RECORD_PREFIX)
    public DataSource recordDataSource() {
        DataSourceBuilder factory = DataSourceBuilder.create().type(HikariDataSource.class);
        return factory.build();
    }


    @Bean(RecordDataSourceConfig.DB_RECORD_SQL_SESSION_FACTORY)
    @Profile("dev")
    public MybatisSqlSessionFactoryBean devRecordSqlSessionFactory(@Qualifier(RecordDataSourceConfig.DB_RECORD) DataSource dataSource) {
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(dataSource);
        mybatisSqlSessionFactoryBean.setPlugins(new Interceptor[]{
                new PaginationInterceptor(),
                new PerformanceInterceptor(),
                new OptimisticLockerInterceptor()
        });
        mybatisSqlSessionFactoryBean.setSqlSessionFactoryBuilder(new MybatisSessionFactoryBuilder());
        return mybatisSqlSessionFactoryBean;
    }


    @Bean(RecordDataSourceConfig.DB_RECORD_SQL_SESSION_FACTORY)
    @Profile("pro")
    public MybatisSqlSessionFactoryBean proRecordSqlSessionFactory(@Qualifier(RecordDataSourceConfig.DB_RECORD) DataSource dataSource) {
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(dataSource);
        mybatisSqlSessionFactoryBean.setPlugins(new Interceptor[]{
//                new PerformanceInterceptor(),
        });
        mybatisSqlSessionFactoryBean.setSqlSessionFactoryBuilder(new MybatisSessionFactoryBuilder());
        return mybatisSqlSessionFactoryBean;
    }


    @Bean(RecordDataSourceConfig.TX_RECORD)
    public DataSourceTransactionManager recordTx(@Qualifier(RecordDataSourceConfig.DB_RECORD) DataSource dataSource) {
        DataSourceTransactionManager basicTx = new DataSourceTransactionManager();
        basicTx.setDataSource(dataSource);
        return basicTx;
    }
}
