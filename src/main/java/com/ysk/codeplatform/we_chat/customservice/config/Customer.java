package com.ysk.codeplatform.we_chat.customservice.config;

import org.springframework.context.annotation.Configuration;

/**
 * 描述
 *
 *
 * <dependency>
 *             <groupId>javax.servlet</groupId>
 *             <artifactId>javax.servlet-api</artifactId>
 *             <version>3.1.0</version>
 *             <scope>provided</scope>
 *         </dependency>
 *
 * @author Y.S.K
 * @date 2018/9/21 18:29
 */
@Configuration
public class Customer {

    public static final String APP_ID = "wxa5d0a5c97bd2a8ed";
    public static final String APP_SECRET = "23e42431792bf85cac4c51b01d8d9e91";
    public static final String CUSTOMER_TOKEN = "ulin5";
    public static final String SEND_MSG = "https://api.weixin.qq.com/cgi-bin/message/custom/send";
    public static final String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
    public static final String SEND_MEDIA = "https://api.weixin.qq.com/cgi-bin/media/upload";
}
