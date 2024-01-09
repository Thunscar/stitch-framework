package com.stitchcodes.common.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Author: stitch
 * @Date: 2023/4/29 22:29
 * @Description:
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();

        //字符串序列化规则
        RedisSerializer stringSerializer = new StringRedisSerializer();
        //自定义Json序列化规则
        FastJsonRedisSerializer<Object> jsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);

        redisTemplate.setConnectionFactory(factory);

        //设置常用键值序列化规则
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(jsonRedisSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(jsonRedisSerializer);

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
