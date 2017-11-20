package com.example.service;

import com.example.dao.FoodsDao;
import com.example.domain.Foods;
import com.example.domain.Page;
import com.example.factory.BasicFactory;
import com.example.util.DaoUtils;
import com.example.util.PathUtils;
import org.apache.commons.dbutils.DbUtils;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Pan on 16/12/3.
 */
public class FoodsServiceImpl implements FoodsService {

    private FoodsDao dao = BasicFactory.getFactory().getInstance(FoodsDao.class);
    @Override
    public void addCust(Foods foods) {
        //1.检查是否有该用户
        if (dao.findUserByName(foods.getName()) != null) {
            throw new RuntimeException("用户已存在");
        }
        //2.添加用户
        dao.addCust(foods);
    }

    @Override
    public List<Foods> getAllCust() {
        return dao.getAllCust();
    }

    @Override
    public Foods findCustById(String id) {
        return dao.findUserById(id);
    }

    @Override
    public Foods findCustByName(String name) {
        return dao.findUserByName(name);
    }

    @Override
    public void updateCust(Foods foods) {
        dao.updateCust(foods);
    }

    @Override
    public void delCustById(String id) {
        dao.delCustById(id);
    }

    @Override
    public void batchDel(String[] ids) {
        Connection conn = DaoUtils.getConnection();
        try {
            conn.setAutoCommit(false);
            for (String id : ids) {
                dao.delCustByIdWithTran(conn, id);
            }
            DbUtils.commitAndCloseQuietly(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            DbUtils.rollbackAndCloseQuietly(conn);
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Foods> findCustByCond(Foods foods) {
        return dao.findCustByCond(foods);
    }

    @Override
    public Page pageCust(int thispage, int rowPerPage) {
        Page page = new Page();
        page.setRowPerPage(rowPerPage);
        int countRow = dao.getCountRow();
        page.setCountRow(countRow);
        int countPage = countRow / rowPerPage + (countRow % rowPerPage == 0 ? 0 : 1);
        page.setCountPage(countPage);
        page.setFirstPage(1);
        page.setLastPage(countPage);
        page.setThisPage(thispage);
        page.setPrePage(thispage == 1 ? 1 : thispage - 1);
        page.setNextPage(thispage ==countPage ? countPage : thispage + 1);
        List<Foods> list = dao.getCustByPage((thispage - 1) * rowPerPage, rowPerPage);
        page.setList(list);
        return page;
    }

    @Override
    public void deleteImageByName(String name) {
        Foods foods = findCustByName(name);
        if (foods != null) {
            deleteImage(foods);
        }
    }

    @Override
    public void deleteImageById(String id) {
        Foods foods = findCustById(id);
        if (foods != null) {
            deleteImage(foods);
        }
    }

    /**
     * 删除给定的cust对应的图片
     * @param foods
     */
    private void deleteImage(Foods foods) {
        String oldImage = foods.getImageUrl();
        String[] oldNames = oldImage.split("[/]");
        String oldName = oldNames[oldNames.length - 1];
        String path = PathUtils.getImagePath();
        File file = new File(path, oldName);
        file.delete();
    }
}
