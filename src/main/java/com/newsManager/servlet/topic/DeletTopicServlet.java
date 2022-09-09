package com.newsManager.servlet.topic; /**
 * @description
 * @author xcdgg
 * @date 2022/6/24 14:53
 */

import com.newsManager.service.TopicsService;
import com.newsManager.service.impl.TopicsServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeletTopicServlet", value = "/DeletTopicServlet")
public class DeletTopicServlet extends HttpServlet {
    TopicsService topicsService = new TopicsServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String tid = request.getParameter("tid");
        PrintWriter out = response.getWriter();
        String contextPath = request.getContextPath();
        try {
            int result = topicsService.deleteTopic(Integer.parseInt(tid));
            if (result == -1) {
                out.print("<script type=\"text/javascript\">");
                out.print("alert(\"该主题下还有文章，不能删除！\");");
                out.print("location.href=\"" + contextPath
                        + "/util/topics?action=list\";");
                out.print("</script>");
            } else if (result == 0) {
                out.print("<script type=\"text/javascript\">");
                out.print("alert(\"未找到相关主题，点击确认返回主题列表\");");
                out.print("location.href=\"" + contextPath
                        + "/util/topics?action=list\";");
                out.print("</script>");
            } else {
                out.print("<script type=\"text/javascript\">");
                out.print("alert(\"已经成功删除主题，点击确认返回主题列表\");");
                out.print("location.href=\"" + contextPath
                        + "/util/topics?action=list\";");
                out.print("</script>");
            }
        } catch (Exception e) {
            out.print("<script type=\"text/javascript\">");
            out.print("alert(\"删除失败，请联系管理员！点击确认返回主题列表\");");
            out.print("location.href=\"" + contextPath
                    + "/util/topics?action=list\";");
            out.print("</script>");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
