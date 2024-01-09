package com.stitchcodes.common.redis;

import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author: stitch
 * @Date: 2023/4/28 16:50
 * @Description: 操作Redis缓存Bean
 */
@SuppressWarnings(value = {"unchecked"})
@Component
public class RedisCache {

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 缓存基本参数(Integer String 实体类)
     *
     * @param key   参数键
     * @param value 参数值
     */
    public <T> void setCacheObject(final String key, final T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 设置基本参数缓存(Integer String 实体类)
     *
     * @param key      参数键
     * @param value    参数值
     * @param timeout  有效时间
     * @param timeUnit 有效时间单位
     */
    public <T> void setCacheObject(final String key, final T value, final Integer timeout, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }


    /**
     * 设置缓存有效时间
     *
     * @param key      参数键
     * @param timeout  有效时间
     * @param timeUnit 有效时间单位
     * @return 是否成功设置
     */
    public boolean expire(final String key, final long timeout, final TimeUnit timeUnit) {
        return redisTemplate.expire(key, timeout, timeUnit);
    }

    /**
     * 设置有效时间
     *
     * @param key     参数值
     * @param timeout 有效时间单位秒
     * @return 是否设置成功
     */
    public boolean expire(final String key, final long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 获取有效时间
     *
     * @param key 参数键
     * @return 有效时间
     */
    public long getExpire(final String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 缓存中是否存在某个参数
     *
     * @param key 参数键
     * @return 是否存在
     */
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 获取缓存对象
     *
     * @param key 参数键
     * @return 参数值
     */
    public <T> T getCacheObject(String key) {
        ValueOperations<String, T> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    /**
     * 移除缓存
     *
     * @param key 参数键
     * @return 是否移除成功
     */
    public boolean deleteObject(final String key) {
        return redisTemplate.delete(key);
    }


    /**
     * 删除多个缓存对象
     *
     * @param keys 多个缓存keys
     * @return 是否删除成功
     */
    public boolean deleteObject(final Collection<String> keys) {
        return redisTemplate.delete(keys) > 0;
    }

    /**
     * 缓存List数据
     *
     * @param key      参数键
     * @param dataList 数据列表
     * @return
     */
    public <T> long setCacheList(final String key, final List<T> dataList) {
        return redisTemplate.opsForList().rightPushAll(key, dataList);
    }

    /**
     * 获取缓存List数据
     *
     * @param key 参数键
     * @return 缓存List数据
     */
    public <T> List<T> getCacheList(final String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 缓存Set数据
     *
     * @param key     参数键
     * @param dataSet 参数值
     * @return 缓存数据的对象
     */
    public <T> BoundSetOperations<String, T> setCacheSet(final String key, final Set<T> dataSet) {
        BoundSetOperations<String, T> operations = redisTemplate.boundSetOps(key);
        Iterator<T> iterator = dataSet.iterator();
        while (iterator.hasNext()) {
            operations.add(iterator.next());
        }
        return operations;
    }

    /**
     * 获取缓存的Set数据
     *
     * @param key 参数键
     * @return 缓存的Set数据
     */
    public <T> Set<T> getCacheSet(final String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 缓存Map数据
     *
     * @param key     参数键
     * @param dataMap Map数据
     */
    public <T> void setCacheMap(final String key, final Map<String, T> dataMap) {
        if (dataMap != null) {
            redisTemplate.opsForHash().putAll(key, dataMap);
        }
    }

    /**
     * 获取缓存Map数据
     *
     * @param key 参数键
     * @return 缓存的Map数据
     */
    public <T> Map<String, T> getCacheMap(final String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 缓存Hash数据
     *
     * @param key   参数键
     * @param hKey  hash键
     * @param value hash值
     */
    public <T> void setCacheMapValue(final String key, final String hKey, final T value) {
        redisTemplate.opsForHash().put(key, hKey, value);
    }

    /**
     * 获取缓存的Hash数据
     *
     * @param key  参数键
     * @param hKey hash键
     * @return 缓存的Hash数据
     */
    public <T> T getCacheMapValue(final String key, final String hKey) {
        HashOperations<String, String, T> hashOperations = redisTemplate.opsForHash();
        return hashOperations.get(key, hKey);
    }

    /**
     * 获取多个缓存的Hash数据
     *
     * @param key   参数键
     * @param hKeys 多个hash键
     * @return 缓存的hash数据列表
     */
    public <T> List<T> getMultiCacheValue(final String key, final Collection<Object> hKeys) {
        return redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    /**
     * 删除hash数据
     *
     * @param key  参数键
     * @param hKey hash键
     * @return 是否删除成功
     */
    public boolean deleteCacheMapValue(final String key, final String hKey) {
        return redisTemplate.opsForHash().delete(key, hKey) > 0;
    }


    /**
     * 获取缓存的基本对象列表
     *
     * @param pattern 字符串前缀
     * @return 基本对象列表
     */
    public Collection<String> keys(final String pattern) {
        return redisTemplate.keys(pattern);
    }


}
