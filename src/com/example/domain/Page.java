package com.example.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Pan on 16/12/6.
 */
public class Page implements Serializable {
    private List<Foods> list;
    private int firstPage; //第一页
    private int lastPage; //最后一页
    private int thisPage; //当前页
    private int prePage; //前一页
    private int nextPage; //下一页
    private int rowPerPage; //每页多少条数据
    private int countRow; //一共有多少条数据
    private int countPage; //共多少页

    public Page() {
    }

    public List<Foods> getList() {
        return list;
    }

    public void setList(List<Foods> list) {
        this.list = list;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getThisPage() {
        return thisPage;
    }

    public void setThisPage(int thisPage) {
        this.thisPage = thisPage;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getRowPerPage() {
        return rowPerPage;
    }

    public void setRowPerPage(int rowPerPage) {
        this.rowPerPage = rowPerPage;
    }

    public int getCountRow() {
        return countRow;
    }

    public void setCountRow(int countRow) {
        this.countRow = countRow;
    }

    public int getCountPage() {
        return countPage;
    }

    public void setCountPage(int countPage) {
        this.countPage = countPage;
    }
}
