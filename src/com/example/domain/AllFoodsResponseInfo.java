package com.example.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Pan on 17/2/10.
 */
public class AllFoodsResponseInfo implements Serializable{
    public static final String MSG_SUCCESS = "返回数据成功";
    public static final String MSG_FAILURE = "返回数据失败";

    private int status;
    private String msg;
    private List<Foods> foods;

    public AllFoodsResponseInfo() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Foods> getFoods() {
        return foods;
    }

    public void setFoods(List<Foods> foods) {
        this.foods = foods;
    }
}
