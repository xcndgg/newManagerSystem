package com.newsManager.servlet.topic; /**
 * @description
 * @author xcdgg
 * @date 2022/6/24 11:08
 */

import com.newsManager.entity.Topic;
import com.newsManager.service.TopicsService;
import com.newsManager.service.impl.TopicsServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "FindTopServlet", value = "/FindTopServlet")
public class FindTopServlet extends HttpServlet {
    TopicsService topicsService = new TopicsServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        List<Topic> topics = null;
        try {
            topics = topicsService.findAllTopics();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("topics",topics);
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        this.doGet(request, response);
    }
}
