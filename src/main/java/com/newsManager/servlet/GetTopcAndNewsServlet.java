package com.newsManager.servlet; /**
 * @description
 * @author xcdgg
 * @date 2022/6/23 20:55
 */

import com.newsManager.entity.Comment;
import com.newsManager.entity.News;
import com.newsManager.entity.Topic;
import com.newsManager.service.CommentsService;
import com.newsManager.service.NewsService;
import com.newsManager.service.TopicsService;
import com.newsManager.service.impl.CommentsServiceImpl;
import com.newsManager.service.impl.NewsServiceImpl;
import com.newsManager.service.impl.TopicsServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author XCDD  邮箱：2207346898@qq.com
 * @description
 * @vision
 */
@WebServlet(name = "GetTopcAndNewsServlet", value = "/GetTopcAndNewsServlet")
public class GetTopcAndNewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String contextPath = request.getContextPath();
        String action = request.getParameter("action");
        TopicsService topicService = new TopicsServiceImpl();
        NewsService newsService = new NewsServiceImpl();
        CommentsService commentsService = new CommentsServiceImpl();
        try {
            if (action.equals("addComment")) {// 添加评论
                String cauthor = request.getParameter("cauthor");
                String cnid = request.getParameter("nid");
                String cip = request.getParameter("cip");
                String ccontent = request.getParameter("ccontent");
                Comment comment = new Comment();
                comment.setCnid(Integer.parseInt(cnid));
                comment.setCcontent(ccontent);
                comment.setCdate(new Date());
                comment.setCip(cip);
                comment.setCauthor(cauthor);
                try {
                    commentsService.addComment(comment);
                    out.print("<script type=\"text/javascript\">");
                    out.print("alert(\"评论成功，点击确认返回原来页面\");");
                    out.print("location.href=\"" + contextPath
                            + "/util/news?action=readNew&nid=" + cnid + "\";");
                    out.print("</script>");
                } catch (Exception e) {
                    out.print("<script type=\"text/javascript\">");
                    out.print("alert(\"评论添加失败！请联系管理员查找原因！点击确认返回原来页面\");");
                    out.print("location.href=\"" + contextPath
                            + "/util/news?action=readNew&nid=" + cnid + "\";");
                    out.print("</script>");
                }
            } else if ("readNew".equals(action)) {
                String nid = request.getParameter("nid");
                News news = newsService.findNewsByNid(Integer.parseInt(nid));
                news.setComments(commentsService.findCommentsByNid(Integer
                        .parseInt(nid)));
                Map<Integer, Integer> topics = new HashMap<Integer, Integer>();
                topics.put(1, 5);
                topics.put(2, 5);
                topics.put(5, 5);
                List<List<News>> latests = newsService
                        .findLatestNewsByTid(topics);
                request.setAttribute("news", news);
                request.getRequestDispatcher("/newspages/news_read.jsp")
                        .forward(request, response);
            } else if ("listTitle".equals(action)) {
                Map<Integer, Integer> topics = new HashMap<Integer, Integer>();
                topics.put(1, 5);
                topics.put(2, 5);
                topics.put(5, 5);
                List<List<News>> latests = newsService
                        .findLatestNewsByTid(topics);
                List<Topic> list = topicService.findAllTopics();
                List<News> listNews = null;
                String tid = request.getParameter("tid");
                if (tid == null || (tid = tid.trim()).length() == 0) {
                    listNews = newsService.findAllNews();

                } else
                    listNews = newsService.findAllNewsByTid(Integer.parseInt(tid));
                request.setAttribute("listTopic", list);
                request.setAttribute("listNews", listNews);
                request.getRequestDispatcher("/index.jsp").forward(request,
                        response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
