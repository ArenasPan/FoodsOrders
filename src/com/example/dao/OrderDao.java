package com.example.dao;

import com.example.domain.Order;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Pan on 2017/2/15.
 */
public interface OrderDao {
    /**
     * 添加点菜的订单
     * @param order 订单中的一个菜
     */
    void addOrderWithTran(Connection conn, Order order);

    /**
     * 获取最后一项订单id，用于指定下一订单id
     * @return 最后一项的订单id
     */
    Order findLastOrderGroup();

    /**
     * 删除所有订单
     */
    void deleteAllOrder();

    /**
     * 获取所有订单信息
      * @return 数据库中所有订单信息
     */
    List<Order> getAllOrders();

    /**
     * 更新数据库中指定id的行中is_served字段为1
     * @param id 指定id
     */
    void updateServedByOrderId(String id);

    /**
     * 清除order表中所有记录
     */
    void truncateOrders();
}
