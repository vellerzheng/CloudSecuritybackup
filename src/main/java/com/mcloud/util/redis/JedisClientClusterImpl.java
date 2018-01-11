package com.mcloud.util.redis;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mcloud.util.JedisClient;
import com.mcloud.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
/*import com.thinkgem.jeesite.common.utils.StringUtils;*/  // 用到的StringUtils 应该为这个

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

import java.util.List;


public class JedisClientClusterImpl implements JedisClient {

    @Autowired
    private JedisCluster jedisCluster;

    public <T> T get(String key, TypeReference<T> clazz) {
        String json = jedisCluster.get(key);
        if (StringUtils.isNotEmpty(json)) {

            return JsonUtil.json2Object(json, clazz);
        } else {
            return null;
        }
    }

    public <T> T get(String key, Class<T> clazz) {
        String json = jedisCluster.get(key);
        if (StringUtils.isNotEmpty(json)) {
            return JsonUtil.json2Object(json, clazz);
        } else {
            return null;
        }
    }

    public void set(String key, Object o) {
        String json = JsonUtil.object2Json(o);
        jedisCluster.set(key, json);
    }

    public void setAndExpire(String key, Object o, int expire) {
        String json = JsonUtil.object2Json(o);
        jedisCluster.set(key, json);
        jedisCluster.expire(key, expire);
    }

//    public long del(String key) {
//        return jedisCluster.del(key);
//    }

    public String get(String key) {
        return jedisCluster.get(key);
    }



    public String get(String key, int select) {
        jedisCluster.select(select);
        return jedisCluster.get(key);
    }

    @Override
    public String set(String key, String value) {
        return jedisCluster.set(key, value);
    }

    @Override
    public String hget(String hkey, String key) {
        return jedisCluster.hget(hkey, key);
    }

    @Override
    public long hset(String hkey, String key, String value) {
        return jedisCluster.hset(hkey, key, value);
    }

    @Override
    public long incr(String key) {
        return jedisCluster.incr(key);
    }

    public Long decr(String key) {
        return jedisCluster.decr(key);
    }

    @Override
    public long expire(String key, int second) {
        return jedisCluster.expire(key, second);
    }

    @Override
    public long ttl(String key) {
        return jedisCluster.ttl(key);
    }

    @Override
    public long del(String key) {
        return jedisCluster.del(key);
    }

    @Override
    public long hdel(String hkey, String key) {

        return jedisCluster.hdel(hkey, key);
    }

    public Long rpush(String key, String string) {
        return jedisCluster.rpush(key, string);
    }

    public Long lpush(String key, String string) {
        return jedisCluster.lpush(key, string);
    }

    public Boolean exists(String key) {
        return jedisCluster.exists(key);
    }

    public List<String> brpop(int timeout, String key) {
        return jedisCluster.brpop(timeout, key);
    }

}

