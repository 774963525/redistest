package com.example.redistest.Service;

import com.example.redistest.Bean.Product;
import com.example.redistest.Bean.returnResult;
import com.example.redistest.Mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReduceProduct {
    @Autowired
    private ProductMapper mapper;
    @Autowired
    private ProductService service;
    public returnResult reduce(int productId,String content,int userId){
        Jedis jedis = new Jedis();
        jedis.select(4);
        String pId = productId+"";
//        先查缓存有没有当前商品库存数据,库存直接-1 没有 则查数据库 赋值给缓存
        int i = 0;
        if(jedis.hget("product",pId)!=null){
//            缓存里有库存 库存--
            List<String> num= jedis.hmget("product",pId);
//        [100]
            i  = Integer.parseInt( num.get(0) );
//
        }
//        没有库存值 插一个进去
        else{
            //拿到数据库里的库存值
            Product pro = service.findOne(productId);
            int sku = pro.getProductSku();
//        得到sku  附上productid content 加入缓存
            Map m1 = new HashMap();
//        商品id 库存
            m1.put(productId,sku);
            jedis.hmset("product", m1);
            i = sku;
        }

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
