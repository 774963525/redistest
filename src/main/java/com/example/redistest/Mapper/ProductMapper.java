package com.example.redistest.Mapper;

import com.example.redistest.Bean.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface ProductMapper {

    @Select("SELECT * FROM product ")
    List<Product> showAll();

    @Insert("INSERT into product (productName,productSku,content) VALUES (#{productName},#{productSku},#{content} ")
    void insert(String productName,int productSku,String content);

    @Select("SELECT * FROM product WHERE productId = #{productId}")
    Product showOne(int productId);
}
