package com.example.redistest.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    @Insert("INSERT INTO productOrder " +
            "(userId,productId,content) " +
            "VALUES (#{userId},#{productId},#{content})")
    void insert(int userId, int productId, String content) ;
}
