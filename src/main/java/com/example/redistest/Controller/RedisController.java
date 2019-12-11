package com.example.redistest.Controller;

import com.example.redistest.Bean.Product;
import com.example.redistest.Bean.returnResult;
import com.example.redistest.Mapper.ProductMapper;
import com.example.redistest.Service.CreateOrder;
import com.example.redistest.Service.InsertMysql;
import com.example.redistest.Service.ProductFromMysql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.*;

@RestController
public class RedisController {

    @Autowired
    private ProductMapper mapper;
    @Autowired
    private ProductFromMysql service ;
    @Autowired
    private CreateOrder order;
    @Autowired
    InsertMysql insert;
    private Jedis jedis;
    private Set set;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    /**
     * 用户进入
     * 传入用户id,商品id 存入缓存 判断当前id有无资格 set
     *
     * */
    @ResponseBody
    @RequestMapping("/click")
    public returnResult redis(int productId, int userId, String content){
//      查看用户有无购买资格
        if(redisTemplate.opsForSet().add("records", userId+"-"+productId)==1){
//            可以插入 没有记录
            //        库存--
            long num = redisTemplate.opsForHash().increment("product",productId+"", -1);
            if(num>=0){
    //            可以购买 生成订单
                redisTemplate.opsForList().rightPush("order",userId+"-"+productId+"-"+content);
                return new returnResult(true,"成功");
            }else{
//                售罄,将资格返还
                redisTemplate.opsForSet().remove("records",userId+"-"+productId );
                return new returnResult(false,"售罄");
            }
        }else{
//            没有资格
            return new returnResult(false,"请勿重复购买");
        }



    }
    @ResponseBody
    @RequestMapping("/getProductFromRedis")
    public returnResult show(){
        List<Product> list = service.showAll();
        int id = 0;
        int sku = 0;
        jedis = new Jedis();

        Map map = new HashMap<>();
        for(int i = 0;i<list.size();i++){
            id = list.get(i).getProductId();
            sku = list.get(i).getProductSku();
            map.put(id+"",sku+"");
            System.out.println(map);
            jedis.hmset("product",map);
            map.clear();
        }
        return new returnResult(true,"ok");
    }
//    第三步 取出订单 塞入数据库
    @ResponseBody
    @RequestMapping("/insertintomysql")
    public String insertIntoMysql() throws InterruptedException {
        insert.insert();
        return "ok";
    }

}
