package com.newsManager.servlet.topic; /**
 * @description
 * @author xcdgg
 * @date 2022/6/24 14:35
 */

import com.newsManager.entity.Topic;
import com.newsManager.service.TopicsService;
import com.newsManager.service.impl.TopicsServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UpdataTopicServlet", value = "/UpdataTopicServlet")
public class UpdataTopicServlet extends HttpServlet {
    TopicsService topicsService = new TopicsServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String contextPath = request.getContextPath();
        String tid = request.getParameter("tid");
        String tname = request.getParameter("tname");
        Topic topic = new Topic();
        topic.setTid(Integer.parseInt(tid));
        topic.setTname(tname);
        try {
            int result = topicsService.updateTopic(topic);
            if (result == -1) {
                out.print("<script type=\"text/javascript\">");
                out.print("alert(\"当前主题已存在，请输入不同的主题！\");");
                out.print("location.href=\"" + contextPath
                        + "/newspages/topic_modify.jsp?tid=" + tid
                        + "&tname=" + tname + "\";");
                out.print("</script>");
            } else if (result == 0) {
                out.print("<script type=\"text/javascript\">");
                out.print("alert(\"未找到相关主题，点击确认返回主题列表\");");
                out.print("location.href=\"" + contextPath
                        + "/util/topics?action=list\";");
                out.print("</script>");
            } else {
                out.print("<script type=\"text/javascript\">");
                out.print("alert(\"已经成功更新主题，点击确认返回主题列表\");");
                out.print("location.href=\"" + contextPath
                        + "/util/topics?action=list\";");
                out.print("</script>");
            }
        } catch (Exception e) {
            out.print("<script type=\"text/javascript\">");
            out.print("alert(\"更新失败，请联系管理员！点击确认返回主题列表\");");
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
