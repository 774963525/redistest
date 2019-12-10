package com.example.redistest.Service;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

public class Order {

    public boolean addOrder(int productId,int userId,String content){
        Jedis jedis = new Jedis();
        jedis.select(5);
        if(jedis.get("xulie")==null){
            jedis.set("xulie",0+"");
        }
//        序号+1
        int i= Math.toIntExact(jedis.incrBy("xulie", 1));
        String user = "order_"+i;
        Map map = new HashMap();
        map.put("userId",userId+"");
        map.put("productId",productId+"");
        map.put("content",content);
        System.out.println(map);
        try{
            jedis.hmset(user,map);

            return true;
        }catch(Exception e){
            jedis.incrBy("xulie", -1);
            return false;

        }


    }
}
