package com.example.redistest;

import com.example.redistest.Bean.Product;
import com.example.redistest.Service.ProductService;
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
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class RedistestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedistestApplication.class, args);
        Jedis jedis = new Jedis();
        jedis.select(4);
//        项目启动调用数据库 将数据放入缓存中
//        RedistestApplication ra = new RedistestApplication();
//        List<Product> produckList = ra.get();
//        System.out.println(produckList);
        Map m1 = new HashMap();
//        商品id 库存
        m1.put("1","1000");
        m1.put("2","3");
        jedis.hmset("product", m1);



    }
    public List<Product> get(){
        ProductService ps = new ProductService();
        return ps.showAll();
    }




}
