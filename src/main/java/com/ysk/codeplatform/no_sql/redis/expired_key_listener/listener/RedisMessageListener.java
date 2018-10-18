package com.ysk.codeplatform.no_sql.redis.expired_key_listener.listener;

import com.ysk.codeplatform.no_sql.redis.expired_key_listener.model.RedisKeyExpEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * 描述
 * 监听redis key过期事件
 *
 * @author Y.S.K
 * @date 2018/8/20 16:25
 */
@Log4j2
@Component
public class RedisMessageListener implements MessageListener {
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        //发布事件，移除不活跃用户。
        String redisMessage = message.toString();
        //todo 修改逻辑
        if (redisMessage.contains("1")) {
            applicationContext.publishEvent(new RedisKeyExpEvent(this, message.toString()));
        }
    }
}
