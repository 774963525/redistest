package com.example.redistest.Controller;

import com.example.redistest.Bean.returnResult;
import com.example.redistest.Service.ReduceProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

@RestController
public class RedisController {


    @ResponseBody
    @RequestMapping("/click")
    public returnResult redis(int productId, int userId, String content){

        System.out.println("商品id: "+productId+",用户id: "+userId);
        ReduceProduct reduce = new ReduceProduct();
//        减少商品数量

        return reduce.reduce(productId,content,userId);

    }
}
