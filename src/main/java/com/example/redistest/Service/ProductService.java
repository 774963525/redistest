package com.example.redistest.Service;

import com.example.redistest.Bean.Product;
import com.example.redistest.Bean.returnResult;
import com.example.redistest.Mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {
    @Autowired
    private ProductMapper mapper;

    public List<Product> showAll(){
        return mapper.showAll();
    }
    public Product findOne( int id ){
        return mapper.showOne(id);
    }

    public returnResult insert(String productName,int roductSku,String content){
        try{
            mapper.insert(productName,roductSku,content);
            return new returnResult(true,"插入成功");
        }catch(Exception e){
            return new returnResult(false,"插入失败");
        }

    }
}
