package com.ysk.codeplatform.sql.mysql.multiple_data_sources.config;

import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wangqm-b
 * @create 2018/7/17
        */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Transactional(RecordDataSourceConfig.TX_RECORD)
public @interface TxRecord {
}
