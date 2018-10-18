package com.ysk.codeplatform.sql.mysql.multiple_data_sources.config;

/**
 * @author wangqm-b
 * @create 2018/7/19
 */
public class RecordDataSourceConfig {
    public static final String PREFIX ="db";

    public static final String DB_RECORD ="sanguo.record";

    public static final String DB_RECORD_PREFIX =PREFIX+"."+ DB_RECORD;

    public static final String DB_RECORD_SQL_SESSION_FACTORY ="recordSqlSessionFactory";

    public static final String TX_RECORD ="txRecord";

    public static final String RECORD_DAO_PACKAGE ="com.lsj.sanguo_war_back.channel.module.record.dao";
}
