package com.example.dao;

import com.example.domain.Foods;
import com.example.util.DaoUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pan on 16/12/3.
 */
public class FoodsDaoImpl implements FoodsDao {

    @Override
    public Foods findUserByName(String name) {
        try {
            String sql = "SELECT * FROM foods WHERE name = ?";
            QueryRunner runner = new QueryRunner(DaoUtils.getSource());

            return runner.query(sql, new BeanHandler<Foods>(Foods.class), name);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addCust(Foods foods) {
        try {
            String sql = "INSERT INTO foods VALUES (null,?,?,?,?,?)";
            QueryRunner runner = new QueryRunner(DaoUtils.getSource());
            runner.update(sql, foods.getName(), foods.getImageUrl(), foods.getPrice(), foods.getCategory(), foods.getDescription()
                    );
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Foods> getAllCust() {
        try {
            String sql = "SELECT * FROM foods";
            QueryRunner runner = new QueryRunner(DaoUtils.getSource());
            return runner.query(sql, new BeanListHandler<Foods>(Foods.class));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Foods findUserById(String id) {
        String sql = "SELECT * FROM foods where id = ?";
        try {
            QueryRunner runner = new QueryRunner(DaoUtils.getSource());
            return runner.query(sql, new BeanHandler<Foods>(Foods.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCust(Foods foods) {
        String sql = "UPDATE foods SET name = ?, imageUrl = ?, price = ?, category = ?, " +
                " description = ? where id = ?";
        try {
            QueryRunner runner = new QueryRunner(DaoUtils.getSource());
            runner.update(sql, foods.getName(), foods.getImageUrl(), foods.getPrice(), foods.getCategory(), foods.getDescription(),
                     foods.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delCustById(String id) {
        String sql = "DELETE FROM foods WHERE id = ?";
        try {
            QueryRunner runner = new QueryRunner(DaoUtils.getSource());
            runner.update(sql, id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delCustByIdWithTran(Connection conn, String id) {
        String sql = "DELETE FROM foods WHERE id = ?";
        try {
            QueryRunner runner = new QueryRunner(DaoUtils.getSource());
            runner.update(conn, sql, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Foods> findCustByCond(Foods foods) {
        String sql = "SELECT * FROM foods WHERE 1=1 ";
        List<Object> list = new ArrayList<>();
        if (foods.getName() != null && !"".equals(foods.getName())) {
            sql += "AND name like ? ";
            list.add("%" + foods.getName() + "%");
        }
        if (foods.getPrice()>0) {
            sql += "AND price = ? ";
            list.add(foods.getPrice());
        }
        if (foods.getCategory() != null && !"".equals(foods.getCategory())) {
            sql += "AND category = ? ";
            list.add(foods.getCategory());
        }
        try {
            QueryRunner runner = new QueryRunner(DaoUtils.getSource());
            if (list.size() == 0) {
                return runner.query(sql, new BeanListHandler<>(Foods.class));
            } else {
                return runner.query(sql, new BeanListHandler<Foods>(Foods.class), list.toArray());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getCountRow() {
        String sql = "SELECT count(*) FROM foods";
        try {
            QueryRunner runner = new QueryRunner(DaoUtils.getSource());
            return ((Long)runner.query(sql, new ScalarHandler<>())).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Foods> getCustByPage(int i, int rowPerPage) {
        String sql = "SELECT * FROM foods LIMIT ?, ?";
        try {
            QueryRunner runner = new QueryRunner(DaoUtils.getSource());
            return runner.query(sql, new BeanListHandler<>(Foods.class), i, rowPerPage);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
