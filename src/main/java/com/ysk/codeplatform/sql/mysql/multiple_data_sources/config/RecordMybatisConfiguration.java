package com.ysk.codeplatform.sql.mysql.multiple_data_sources.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangqm-b
 * @create 2018/4/24
 */
@Configuration
@ConditionalOnBean(name = {RecordDataSourceConfig.DB_RECORD_SQL_SESSION_FACTORY})
@MapperScan(basePackages = RecordDataSourceConfig.RECORD_DAO_PACKAGE,sqlSessionFactoryRef = RecordDataSourceConfig.DB_RECORD_SQL_SESSION_FACTORY)
public class RecordMybatisConfiguration {
}
