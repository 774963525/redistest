package com.example.redistest.Service;

import com.example.redistest.Bean.returnResult;
import redis.clients.jedis.Jedis;

import java.util.List;
public class ReduceProduct {

    public returnResult reduce(int productId,String content,int userId){
        Jedis jedis = new Jedis();
        jedis.select(4);
        String pId = productId+"";
        List<String> num= jedis.hmget("product",pId);
//        System.out.println("00-0");
//        System.out.println(num);
//        [100]
        int i = Integer.parseInt( num.get(0) );

        Order order = new Order();
        if(i!=0){
//            添加成功
            jedis.hincrBy("product",pId,-1);
            boolean  b = order.addOrder(productId,userId,content);
            if(b){
                return new returnResult(true,"抢购成功!");
            }else{
                jedis.hincrBy("product",pId,1);
                System.out.println("false");
                return new returnResult(false,"服务器繁忙,请重试");
            }
        }else{
            System.out.println("false2");
            return new returnResult(false,"已售罄");
        }

    }
}
