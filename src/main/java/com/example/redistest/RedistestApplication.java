package com.example.redistest;

import com.example.redistest.Bean.Product;
import com.example.redistest.Service.InsertMysql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@EnableScheduling
public class RedistestApplication {

    @Autowired
    InsertMysql insert;

    public static void main(String[] args) {
        SpringApplication.run(RedistestApplication.class, args);
    }

    @Scheduled(fixedDelay=2000)
    public void test() throws InterruptedException {
//        System.out.println("hhh");
        insert.insert();


    }
}
