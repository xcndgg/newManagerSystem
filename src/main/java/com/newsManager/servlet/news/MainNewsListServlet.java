package com.newsManager.servlet.news; /**
 * @description
 * @author xcdgg
 * @date 2022/6/24 15:17
 */

import com.newsManager.entity.News;
import com.newsManager.entity.Page;
import com.newsManager.service.NewsService;
import com.newsManager.service.impl.NewsServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "MainNewsListServlet", value = "/MainNewsListServlet")
public class MainNewsListServlet extends HttpServlet {
    NewsService newsService = new NewsServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String contextPath = request.getContextPath();
            Map<Integer, Integer> topics = new HashMap<Integer, Integer>();
            topics.put(1, 5);
            topics.put(2, 5);
            topics.put(5, 5);
            List<List<News>> latests = newsService
                    .findLatestNewsByTid(topics);
            List<News> list = null;
            String tid = request.getParameter("tid");
            String pageIndex = request.getParameter("pageIndex");// 获得当前页数
            if (pageIndex == null
                    || (pageIndex = pageIndex.trim()).length() == 0) {
                pageIndex = "1";
            }
            int currPageNo = Integer.parseInt(pageIndex);
            if (currPageNo < 1)
                currPageNo = 1;
            Page pageObj = new Page();
            pageObj.setCurrPageNo(currPageNo); // 设置当前页码
            pageObj.setPageSize(15); // 设置每页显示条数
            if (tid == null || (tid = tid.trim()).length() == 0) {
                newsService.findPageNews(pageObj); // 分页查询新闻
                list = pageObj.getNewsList();
            }
            request.getSession().setAttribute("list", list);
            request.getSession().setAttribute("pageObj", pageObj);
            response.sendRedirect(contextPath + "/newspages/admin.jsp");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        this.doGet(request, response);
    }
}
