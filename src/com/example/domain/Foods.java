package com.example.domain;

import java.io.Serializable;

/**
 * Created by Pan on 16/12/3.
 */
public class Foods implements Serializable{
    private int id;
    private String name;
    private int price;
    private String imageUrl;
    private String category;
    private String description;

    public Foods() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void validate() {
        if (name == null || "".equals(name)) {
            throw new RuntimeException("用户名不能为空");
        }
        if (price <= 0) {
            throw new RuntimeException("价格必须大于0");
        }
        if (category == null || "".equals(category)) {
            throw new RuntimeException("分类不能为空");
        }
        if (description == null || "".equals(description)) {
            throw new RuntimeException("描述不能为空");
        }
    }
}
