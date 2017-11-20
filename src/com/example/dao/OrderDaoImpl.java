package com.example.dao;

import com.example.domain.Order;
import com.example.util.DaoUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/2/15.
 */
public class OrderDaoImpl implements OrderDao {
    @Override
    public void addOrderWithTran(Connection conn,Order order) {
        try {
            String sql = "INSERT INTO orders VALUES (null,?,?,?,null,?,0)";

            QueryRunner runner = new QueryRunner(DaoUtils.getSource());
            runner.update(conn,sql, order.getName(), order.getPrice(), order.getTable_num(), order.getOrder_group());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order findLastOrderGroup() {
        try {
            String sql = "select * from orders order by id DESC limit 1";
            QueryRunner runner = new QueryRunner(DaoUtils.getSource());
            return runner.query(sql, new BeanHandler<Order>(Order.class));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAllOrder() {

    }

    @Override
    public List<Order> getAllOrders() {
        try {
            String sql = "SELECT * FROM orders ORDER BY id DESC";
            QueryRunner runner = new QueryRunner(DaoUtils.getSource());
            return runner.query(sql, new BeanListHandler<Order>(Order.class));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateServedByOrderId(String id) {
        String sql = "UPDATE orders SET is_served = ? where id = ?";
        try {
            QueryRunner runner = new QueryRunner(DaoUtils.getSource());
            runner.update(sql, 1, id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void truncateOrders() {
        String sql = "TRUNCATE TABLE orders";
        try {
            QueryRunner runner = new QueryRunner(DaoUtils.getSource());
            runner.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
