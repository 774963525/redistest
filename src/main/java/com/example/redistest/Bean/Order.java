package com.example.redistest.Bean;

public class Order {
    private int userId;
    private int productId;
    private String content;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Order() {
    }

    public Order(int userId, int productId, String content) {
        this.userId = userId;
        this.productId = productId;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Order{" +
                "userId=" + userId +
                ", productId=" + productId +
                ", content='" + content + '\'' +
                '}';
    }
}
