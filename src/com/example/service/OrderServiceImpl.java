package com.example.service;

import com.example.dao.OrderDao;
import com.example.domain.Order;
import com.example.factory.BasicFactory;
import com.example.util.DaoUtils;
import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Pan on 2017/2/15.
 */
public class OrderServiceImpl implements OrderService {

    private OrderDao dao = BasicFactory.getFactory().getInstance(OrderDao.class);
    @Override
    public int findLastOrderGroup() {
        Order order = dao.findLastOrderGroup();
        if (order != null) {
            return order.getOrder_group();
        }
        return 0;
    }

    @Override
    public void addOrder(List<Order> orderList) {
        Connection conn = DaoUtils.getConnection();
        try {
            conn.setAutoCommit(false);
            for (Order order : orderList) {
                dao.addOrderWithTran(conn, order);
            }
            DbUtils.commitAndCloseQuietly(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            DbUtils.rollbackAndCloseQuietly(conn);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orderList = dao.getAllOrders();
        for (Order order : orderList) {
            order.setOrder_time(order.getOrder_time().substring(0, order.getOrder_time().length() - 2));
        }
        return orderList;
    }

    @Override
    public void updateServedByOrderId(String id) {
        dao.updateServedByOrderId(id);
    }

    @Override
    public void batchDelOrder() {
        dao.truncateOrders();
    }
}
