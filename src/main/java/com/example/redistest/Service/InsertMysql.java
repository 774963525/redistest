package com.example.redistest.Service;

import com.example.redistest.Mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;

@Service("InsertMysql")
public class InsertMysql {
    @Autowired
    private CreateOrder order;
    @Autowired
    OrderMapper mapper;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    int i;
    public void insert() {
//    拿缓存中的order表第一位
        int size = Math.toIntExact(redisTemplate.opsForList().size("order"));
        if(size==0){
//            无订单
            return ;
        }
//        有订单 循环插入数据库
        for(i = 0;i<size;i++){
//            拿出数据
            String data = redisTemplate.opsForList().leftPop("order");
            int userId  =Integer.parseInt( data.split("-")[0]);
            int productId = Integer.parseInt( data.split("-")[1]);

            mapper.insert(userId,productId,data.split("-")[2]);



        }



    }
}
