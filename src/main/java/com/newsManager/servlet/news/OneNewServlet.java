package com.newsManager.servlet.news; /**
 * @description
 * @author xcdgg
 * @date 2022/6/24 15:05
 */

import com.newsManager.entity.News;
import com.newsManager.service.CommentsService;
import com.newsManager.service.NewsService;
import com.newsManager.service.impl.CommentsServiceImpl;
import com.newsManager.service.impl.NewsServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "OneNewServlet", value = "/OneNewServlet")
public class OneNewServlet extends HttpServlet {
    NewsService newsService = new NewsServiceImpl();
    CommentsService commentsService = new CommentsServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String nid = request.getParameter("nid");
        News news = null;
        try {
            news = newsService.findNewsByNid(Integer.parseInt(nid));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            news.setComments(commentsService.findCommentsByNid(Integer
                    .parseInt(nid)));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Map<Integer, Integer> topics = new HashMap<Integer, Integer>();
        topics.put(1, 5);
        topics.put(2, 5);
        topics.put(5, 5);
        List<List<News>> latests = null;
        try {
            latests = newsService
                    .findLatestNewsByTid(topics);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("news", news);
        request.setAttribute("list1", latests.get(0));// 左侧国内新闻
        request.setAttribute("list2", latests.get(1));// 左侧国际新闻
        request.setAttribute("list3", latests.get(2));// 左侧娱乐新闻
        request.getRequestDispatcher("/newspages/news_read.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        this.doGet(request, response);
    }
}
