package com.example.web;

import com.example.domain.Foods;
import com.example.factory.BasicFactory;
import com.example.service.FoodsService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Pan on 16/12/6.
 */
@WebServlet(name = "FindFoodsByCondServlet",urlPatterns = "/servlet/FindFoodsByCondServlet")
public class FindFoodsByCondServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        FoodsService service = BasicFactory.getFactory().getInstance(FoodsService.class);
        try {
            //1.封装cust
            Foods foods = new Foods();
            BeanUtils.populate(foods, request.getParameterMap());
            //2.调用service中的方法查找符合条件的bean,并存入request域中
            List<Foods> list = service.findCustByCond(foods);
            //3.将查询到的list存入request域中，请求转发
            request.setAttribute("list", list);
            request.getRequestDispatcher("/listFoods.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
