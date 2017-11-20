package com.example.service;

import com.example.domain.Order;

import java.util.List;

/**
 * Created by Pan on 2017/2/15.
 */
public interface OrderService {
    /**
     * 获取最后一列的订单号
     * @return 最后一列订单号
     */
    int findLastOrderGroup();

    /**
     * 添加订单记录
     * @param orderList 所点菜品列表
     */
    void addOrder(List<Order> orderList);

    /**
     * 获取所有订单信息
     * @return 所有订单
     */
    List<Order> getAllOrders();

    /**
     * 根据id修改订单是否上菜的状态
     * @param id 订单菜品id
     */
    void updateServedByOrderId(String id);

    /**
     * 删除所有订单
     */
    void batchDelOrder();
}
