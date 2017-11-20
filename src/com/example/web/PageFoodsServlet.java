package com.example.web;

import com.example.domain.Page;
import com.example.factory.BasicFactory;
import com.example.service.FoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Pan on 16/12/6.
 */
@WebServlet(name = "PageFoodsServlet" , urlPatterns = "/servlet/PageFoodsServlet")
public class PageFoodsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int thispage = Integer.parseInt(request.getParameter("thispage"));
        FoodsService service = BasicFactory.getFactory().getInstance(FoodsService.class);
        int rowPerPage = 10;
        Page page = service.pageCust(thispage, rowPerPage);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/pageList.jsp").forward(request, response);
    }
}
