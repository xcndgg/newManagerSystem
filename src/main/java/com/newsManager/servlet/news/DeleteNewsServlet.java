package com.newsManager.servlet.news; /**
 * @description
 * @author xcdgg
 * @date 2022/6/24 14:42
 */

import com.newsManager.service.NewsService;
import com.newsManager.service.impl.NewsServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeleteNewsServlet", value = "/DeleteNewsServlet")
public class DeleteNewsServlet extends HttpServlet {
    NewsService newsService = new NewsServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String nid = request.getParameter("nid");
        PrintWriter out = response.getWriter();
        String contextPath = request.getContextPath();
        try {
            int result = newsService.deleteNews(Integer.parseInt(nid));
            if (result == 0) {
                out.print("<script type=\"text/javascript\">");
                out.print("alert(\"未找到相关新闻，点击确认返回新闻列表\");");
                out.print("location.href=\"" + contextPath
                        + "/util/news?action=list\";");
                out.print("</script>");
            } else {
                out.print("<script type=\"text/javascript\">");
                out.print("alert(\"已经成功删除新闻，点击确认返回新闻列表\");");
                out.print("location.href=\"" + contextPath
                        + "/util/news?action=list\";");
                out.print("</script>");
            }
        } catch (Exception e) {
            out.print("<script type=\"text/javascript\">");
            out.print("alert(\"删除失败，请联系管理员！点击确认返回新闻列表\");");
            out.print("location.href=\"" + contextPath
                    + "/util/news?action=list\";");
            out.print("</script>");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        this.doGet(request, response);
    }
}
