package com.example.test;

import com.example.domain.Order;
import com.example.util.DaoUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Pan on 16/12/4.
 */
public class addData {

    @Test
    public void addData() {
        try {
            QueryRunner runner = new QueryRunner(DaoUtils.getSource());
            String sql = "INSERT INTO customer VALUES (null,?,?,?,?,?,?,?,?)";
            for (int i = 0; i < 100; i++) {
                runner.update(sql, "名称" + i, "女", "1990-09-09", "18822212121", "qq@qq.com", "篮球，排球", "金牌会员", "描述" + i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void addOrderData(){
        try {
            String sql = "INSERT INTO foods VALUES (null,?,?,?,?,?)";
            QueryRunner runner = new QueryRunner(DaoUtils.getSource());
            runner.update(sql, "111", "222", 13, "dsafa", "safafsa"
            );
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
