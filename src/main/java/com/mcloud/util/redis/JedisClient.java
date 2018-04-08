package com.mcloud.util.redis;




import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public interface JedisClient {
    String get(String key);

    <T> T get(String key, TypeReference<T> clazz);

    <T> T get(String key, Class<T> clazz);

    String get(String key, int select);

    void setAndExpire(String key, Object o, int expire);

    Long rpush(String key, String string);

    //    Long del(String... keys);
    Long lpush(String key, String string);

    void set(String key, Object o);

    String set(String key, String value);

    String hget(String hkey, String key);

    long hset(String hkey, String key, String value);

    long incr(String key);

    long expire(String key, int second);

    long ttl(String key);

    long del(String key);

    long hdel(String hkey, String key);

    Boolean exists(String key);

    Long decr(String key);

    List<String> brpop(int timeout, String key);

}

