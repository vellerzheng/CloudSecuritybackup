package com.mcloud.util.redis;



import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
/**
 * JedisConnection工具类
 * @author Administrator
 *
 */
public class JedisConnectionUtils {

    private static JedisConnectionFactory jedisConnectionFactory;

    public static void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
        JedisConnectionUtils.jedisConnectionFactory = jedisConnectionFactory;
    }
    /**
     * 获取JedisConnection连接
     * @return
     */
    public static JedisConnection getJedisConnection() {
        return (JedisConnection) jedisConnectionFactory.getConnection();
    }
    /**
     * 关闭连接
     * @param jedisConnection
     */
    public static void close(JedisConnection jedisConnection) {
        if(jedisConnection != null) {
            jedisConnection.close();
        }
    }
}
