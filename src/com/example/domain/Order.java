package com.example.domain;

import java.io.Serializable;

/**
 * Created by Pan on 2017/2/15.
 */
public class Order implements Serializable{
    private String id;
    private String name;
    private int price;
    private int table_num;
    private String order_time;
    private int order_group;
    private boolean is_served;

    public Order() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public int getTable_num() {
        return table_num;
    }

    public void setTable_num(int table_num) {
        this.table_num = table_num;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public int getOrder_group() {
        return order_group;
    }

    public void setOrder_group(int order_group) {
        this.order_group = order_group;
    }

    public boolean isIs_served() {
        return is_served;
    }

    public void setIs_served(boolean is_served) {
        this.is_served = is_served;
    }
}
