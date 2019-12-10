package com.example.redistest.Bean;

import lombok.Data;

@Data
public class Product {
    private int id ;
    private String productName;
    private int productSku ;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductSku() {
        return productSku;
    }

    public void setProductSku(int productSku) {
        this.productSku = productSku;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
