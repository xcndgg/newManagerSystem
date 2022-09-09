package com.newsManager.servlet.topic;
/**
 * @description
 * @author xcdgg
 * @date 2022/6/24 9:54
 */

import com.newsManager.service.TopicsService;
import com.newsManager.service.impl.TopicsServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * @author XCDD  邮箱：2207346898@qq.com
 * @description
 * @vision
 */
@WebServlet(name = "addTopicServlet", value = "/addTopicServlet")
public class AddTopicServlet extends HttpServlet {
    TopicsService topicsService = new TopicsServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String contextPath = request.getContextPath();
        String tname = request.getParameter("tname");
        try {
            int result = topicsService.addTopic(tname);
            if (result == -1) {
                out.print("<script type=\"text/javascript\">");
                out.print("alert(\"当前主题已存在，请输入不同的主题！\");");
                out.print("location.href=\"" + contextPath
                        + "/newspages/topic_add.jsp\";");
                out.print("</script>");
            } else {
                out.print("<script type=\"text/javascript\">");
                out.print("alert(\"主题创建成功，点击确认返回主题列表！\");");
                out.print("location.href=\"" + contextPath
                        + "/util/topics?action=list\";");
                out.print("</script>");
            }
        } catch (Exception e) {
            out.print("<script type=\"text/javascript\">");
            out.print("alert(\"添加失败，请联系管理员！点击确认返回主题列表\");");
            out.print("location.href=\"" + contextPath
                    + "/util/topics?action=list\";");
            out.print("</script>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        this.doGet(request, response);
    }
}
