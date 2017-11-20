package com.example.dao;

import com.example.domain.Foods;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Pan on 16/12/3.
 */
public interface FoodsDao {
    /**
     * 根据客户名称获取用户
     * @param name 用户名
     * @return 查找到的用户，如果没有则返回null
     */
    Foods findUserByName(String name);

    /**
     * 添加用户
     * @param foods 要添加的用户
     */
    void addCust(Foods foods);

    /**
     * 获取数据库中所有用户
     * @return 所有用户信息
     */
    List<Foods> getAllCust();

    /**
     * 根据id获取用户
     * @param id 用户id
     * @return 查找到的用户
     */
    Foods findUserById(String id);


    /**
     * 更新用户
     * @param foods 要更新的客户
     */
    void updateCust(Foods foods);

    /**
     * 根据id删除客户
     * @param id 要删除的客户id
     */
    void delCustById(String id);

    /**
     * 批量删除客户
     * @param conn 连接
     * @param id 要删除的id
     */
    void delCustByIdWithTran(Connection conn, String id);

    /**
     * 根据条件查找用户
     * @param foods 要搜索的客户信息
     * @return 符合条件的客户list
     */
    List<Foods> findCustByCond(Foods foods);

    /**
     * 获取所有列数
     * @return
     */
    int getCountRow();

    /**
     * 分页查询
     * @param i 查询起止条数
     * @param rowPerPage 每页的条数
     * @return 查询到的客户
     */
    List<Foods> getCustByPage(int i, int rowPerPage);
}


