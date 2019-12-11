package com.example.redistest.Service;

import com.example.redistest.Bean.Product;
import com.example.redistest.Bean.returnResult;
import com.example.redistest.Mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductFromMysql {
    @Autowired
    private ProductMapper mapper;

//    程序运行第一步:将mysql的商品表插入redis
    public List showAll(){
        List<Product> list = mapper.showAll();
        return list;
    }
    public Product findOne( int id ){
        return mapper.showOne(id);
    }


}
