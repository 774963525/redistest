package com.example.redistest.Service;

import com.example.redistest.Mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

@Service
public class CreateOrder {

//    插入数据库
    @Autowired
    OrderMapper mapper;
    public boolean addOrder(int userId,int productId,String content){
        try{
            mapper.insert(userId,productId,content);
            return  true;
        }catch(Exception e){
            return false;
        }



    }
}
