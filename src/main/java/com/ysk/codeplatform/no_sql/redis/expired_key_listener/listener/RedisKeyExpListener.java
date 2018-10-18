package com.ysk.codeplatform.no_sql.redis.expired_key_listener.listener;

import com.ysk.codeplatform.no_sql.redis.expired_key_listener.model.RedisKeyExpEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 描述
 * 监听到redis key过期
 *
 * @author Y.S.K
 * @date 2018/8/20 18:16
 */
@Component
@Log4j2
public class RedisKeyExpListener {
    @EventListener
    public void onApplicationEvent(RedisKeyExpEvent redisKeyExpEvent) {
        try {
            //todo 执行某个key过期时需要执行的操作
//            Integer uid = Integer.parseInt(redisKeyExpEvent.getRedisMessage().replaceAll(RedisKey.HEART_CHECK_PREFIX, StringUtil.EMPTY));
        } catch (NumberFormatException e) {
            log.error("RedisKeyExpListener err :{}", e);
        }
    }
}
