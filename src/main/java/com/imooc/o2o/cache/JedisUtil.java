package com.imooc.o2o.cache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.util.SafeEncoder;

import java.util.Set;

public class JedisUtil {

    //操作key的方法
    public Keys KEYS;
    //对存储结构为String类型的操作
    public Strings STRINGS;
    //Redis连接池对象
    public JedisPool jedisPool;

    /**
     * 获取redis连接池
     * @return
     */
    public JedisPool getJedisPool() {
        return jedisPool;
    }

    /**
     * 设置redis连接池
     * @param jedisPoolWriper
     */
    public void setRedisPool(JedisPoolWriper jedisPoolWriper) {
        this.jedisPool = jedisPoolWriper.getJedisPool();
    }

    /**
     * 从jedis连接池中获取jedis对象
     * @return
     */
    public Jedis getJedis() {
        return jedisPool.getResource();
    }

    public void setJedisPool(JedisPoolWriper jedisPool) {
    }


    //-------------------------------------------------keys
    public class Keys {

        public Keys() {

        }

        public Keys(JedisUtil jedisKey) {
        }


        /**
         * 清空所有key
         * @return
         */
        public String flushAll() {
            Jedis jedis = getJedis();
            String stata = jedis.flushAll();
            jedis.close();
            return stata;
        }

        /**
         * 删除keys对应的记录，可以是多个key
         * @param keys
         * @return
         */
        public long del(String... keys) {
            Jedis jedis = getJedis();
            Long count = jedis.del(keys);
            jedis.close();
            return count;
        }

        /**
         *
         *判断key是否存在
         * @param key
         * @return
         */
        public boolean exists(String key) {
            Jedis sjedis = getJedis();
            Boolean exis = sjedis.exists(key);
            sjedis.close();
            return exis;
        }

        /**
         * 查询所有匹配给定的模式的建
         * @param pattern
         * @return
         */
        public Set<String> keys(String pattern) {
            Jedis jedis = getJedis();
            Set<String> set = jedis.keys(pattern);
            jedis.close();
            return set;
        }
    }
//-------------------------------------------------Strings

    public class Strings {

        public Strings() {

        }

        public Strings(JedisUtil jedisStrings) {
        }

        /**
         * 根据key获取记录
         * @param key
         * @return
         */
        public String get(String key) {

            Jedis sjedis = getJedis();
            String value = sjedis.get(key);
            sjedis.close();
            return value;
        }

        /**
         * 添加记录，如果记录已存在将覆盖原有的value
         * @param key
         * @param value
         * @return
         */
        public String set(String key, String value) {
            return set(SafeEncoder.encode(key), SafeEncoder.encode(value));
        }

        /**
         * 添加记录，如果记录以存在将覆盖原有的value

         * @param key
         * @param value
         * @return
         */
        public String set(byte[] key, byte[] value) {
            Jedis jedis = getJedis();
            String status = jedis.set(key, value);
            jedis.close();
            return status;
        }


    }



}
