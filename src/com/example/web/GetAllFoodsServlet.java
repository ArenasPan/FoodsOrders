package com.example.web;

import com.alibaba.fastjson.JSON;
import com.example.domain.AllFoodsResponseInfo;
import com.example.domain.Foods;
import com.example.factory.BasicFactory;
import com.example.service.FoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Pan on 17/2/10.
 * pc端接口
 */
@WebServlet(name = "GetAllFoodsServlet" , urlPatterns = "/servlet/GetAllFoodsServlet")
public class GetAllFoodsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=UTF-8");

        FoodsService service = BasicFactory.getFactory().getInstance(FoodsService.class);
        List<Foods> foodsList =service.getAllCust();
        AllFoodsResponseInfo responseInfo = new AllFoodsResponseInfo();
        if (foodsList != null && foodsList.size() != 0) {
            //查询数据成功
            responseInfo.setMsg(AllFoodsResponseInfo.MSG_SUCCESS);
            responseInfo.setStatus(1);
            responseInfo.setFoods(foodsList);
            String json = JSON.toJSONString(responseInfo);
            response.getWriter().write(json);
        } else {
            //查询数据失败
            responseInfo.setMsg(AllFoodsResponseInfo.MSG_FAILURE);
            responseInfo.setStatus(0);
            String json = JSON.toJSONString(responseInfo);
            response.getWriter().write(json);
        }
    }
}
