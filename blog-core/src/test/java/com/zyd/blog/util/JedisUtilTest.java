package com.zyd.blog.util;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @description:
 * @author: bangsun
 * @date: 2020/9/8 17:01
 */
public class JedisUtilTest {
    JedisPool jedisPool;
    Jedis jedis;
    @Before
    public void setUp(){
        jedisPool=new JedisPool(new JedisPoolConfig(),"192.168.23.135");
        jedis=jedisPool.getResource();
    }
    @Test
    public void testGet(){
        System.out.println(jedis.get("lu"));
    }

    @Test
    public void testBasicString(){
        //-----添加数据----------
        jedis.set("name","minxr");//向key-->name中放入了value-->minxr
        System.out.println(jedis.get("name"));//执行结果：minxr
        //-----修改数据-----------
        //1、在原来基础上修改
        jedis.append("name","jarorwar");   //很直观，类似map 将jarorwar append到已经有的value之后
        System.out.println(jedis.get("name"));//执行结果:minxrjarorwar
        //2、直接覆盖原来的数据
        jedis.set("name","闵晓荣");
        System.out.println(jedis.get("name"));//执行结果：闵晓荣
        //删除key对应的记录
        jedis.del("name");
        System.out.println(jedis.get("name"));//执行结果：null

        jedis.mset("name","minxr","jarorwar","闵晓荣");
        System.out.println(jedis.mget("name","jarorwar"));
    }

}
