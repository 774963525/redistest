package com.example.redistest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.w3c.dom.ls.LSOutput;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class RedistestApplication {


    public static void main(String[] args) {
        SpringApplication.run(RedistestApplication.class, args);
        Jedis jedis = new Jedis();
        jedis.select(4);
        Map m1 = new HashMap();
//        商品id 库存
        m1.put("1","1000");
        m1.put("2","3");
        jedis.hmset("product", m1);



    }




}
