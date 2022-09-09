package com.newsManager.servlet.news; /**
 * @description
 * @author xcdgg
 * @date 2022/6/24 14:59
 */

import com.newsManager.entity.News;
import com.newsManager.entity.Page;
import com.newsManager.entity.Topic;
import com.newsManager.service.NewsService;
import com.newsManager.service.TopicsService;
import com.newsManager.service.impl.NewsServiceImpl;
import com.newsManager.service.impl.TopicsServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ListNewsServlet", value = "/ListNewsServlet")
public class ListNewsServlet extends HttpServlet {
    TopicsService topicService = new TopicsServiceImpl();
    NewsService newsService = new NewsServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            Map<Integer, Integer> topics = new HashMap<Integer, Integer>();
            topics.put(1, 5);
            topics.put(2, 5);
            topics.put(5, 5);
            List<List<News>> latests = newsService
                    .findLatestNewsByTid(topics);
            List<Topic> list = topicService.findAllTopics();
            List<News> list4 = null;
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
                list4 = pageObj.getNewsList();
            } else
                // 查询指定主题下的新闻
                list4 = newsService.findAllNewsByTid(Integer.parseInt(tid));
            request.setAttribute("list1", latests.get(0));// 左侧国内新闻
            request.setAttribute("list2", latests.get(1));// 左侧国际新闻
            request.setAttribute("list3", latests.get(2));// 左侧娱乐新闻
            request.setAttribute("list", list); // 所有的主题
            request.setAttribute("list4", list4);// 中间的新闻
            request.setAttribute("pageObj", pageObj);
            request.getRequestDispatcher("/index.jsp").forward(request,
                    response);
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
