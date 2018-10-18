package com.ysk.codeplatform.no_sql.redis.expired_key_listener.config;

import com.ysk.codeplatform.no_sql.redis.expired_key_listener.listener.RedisMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * ！！！记得修改redis配置 notify-keyspace-events Ex
 *
 *
 *  <dependency>
 *             <groupId>org.springframework.boot</groupId>
 *             <artifactId>spring-boot-starter-data-redis</artifactId>
 *             <exclusions>
 *                 <exclusion>
 *                     <artifactId>spring-boot-starter</artifactId>
 *                     <groupId>org.springframework.boot</groupId>
 *                 </exclusion>
 *             </exclusions>
 *         </dependency>
 *
 * @author Y.S.K
 * @date 2018/10/18 10:08
 */
@Configuration
public class Beans {
    @Autowired
    private RedisMessageListener redisMessageListener;

    @Bean
    MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter(redisMessageListener);
    }


    @Value("${spring.redis.database}")
    public String redisChannel;

    @Autowired
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {

        final RedisMessageListenerContainer container =
                new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(messageListener(), new ChannelTopic(String.format("__keyevent@%s__:expired", redisChannel)));
        return container;
    }
}
