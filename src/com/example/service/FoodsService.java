package com.example.service;

import com.example.domain.Foods;
import com.example.domain.Page;

import java.util.List;

/**
 * Created by Pan on 16/12/3.
 */
public interface FoodsService {
    /**
     * 添加客户
     * @param foods 需要添加的客户
     */
    void addCust(Foods foods);

    /**
     * 查找所有用户
     * @return 所有用户
     */
    List<Foods> getAllCust();

    /**
     * 根据id查找客户
     * @param id 客户id
     * @return 查找到的客户
     */
    Foods findCustById(String id);

    /**
     * 根据菜名查找数据
     * @param name 菜名
     * @return 查找到的菜名
     */
    Foods findCustByName(String name);

    /**
     * 更新客户
     * @param foods 需要更新的客户
     */
    void updateCust(Foods foods);

    /**
     * 根据id删除用户
     * @param id 要删除的用户
     */
    void delCustById(String id);

    /**
     * 批量删除用户
     * @param ids 要批量删除的用户ids
     */
    void batchDel(String[] ids);

    /**
     * 根据条件查找用户
     * @param foods 要搜索的客户信息
     * @return 符合条件的客户list
     */
    List<Foods> findCustByCond(Foods foods);

    /**
     * 分页查询客户
     * @param thispage 当前页数
     * @param rowPerPage 每页的条数
     * @return 当前页所有信息
     */
    Page pageCust(int thispage, int rowPerPage);

    /**
     * 根据菜名删除相应图片
     * @param name 菜名
     */
    void deleteImageByName(String name);

    /**
     * 根据id删除相应图片
     * @param id 菜品id
     */
    void deleteImageById(String id);
}
