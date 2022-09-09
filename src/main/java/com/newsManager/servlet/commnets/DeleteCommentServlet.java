package com.newsManager.servlet.commnets; /**
 * @description
 * @author xcdgg
 * @date 2022/6/24 15:11
 */

import com.newsManager.service.CommentsService;
import com.newsManager.service.impl.CommentsServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeleteCommentServlet", value = "/DeleteCommentServlet")
public class DeleteCommentServlet extends HttpServlet {
    CommentsService commentsService = new CommentsServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String cid = request.getParameter("cid");
        String nid = request.getParameter("nid");
        PrintWriter out = response.getWriter();
        String contextPath = request.getContextPath();
        try {
            int result = commentsService.deleteCommentById(Integer.parseInt(cid));
            if (result == 0) {
                out.print("<script type=\"text/javascript\">");
                out.print("alert(\"未找到相关评论，点击确认返回\");");
                out.print("location.href=\"" + contextPath
                        + "/util/news?action=toModifyNews&nid=" + nid
                        + "\";");
                out.print("</script>");
            } else {
                out.print("<script type=\"text/javascript\">");
                out.print("alert(\"评论已经成功删除，点击确认返回\");");
                out.print("location.href=\"" + contextPath
                        + "/util/news?action=toModifyNews&nid=" + nid
                        + "\";");
                out.print("</script>");
            }
        } catch (Exception e) {
            out.print("<script type=\"text/javascript\">");
            out.print("alert(\"删除评论失败，请联系管理员！点击确认返回\");");
            out.print("location.href=\"" + contextPath
                    + "/util/news?action=toModifyNews&nid=" + nid + "\";");
            out.print("</script>");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        this.doGet(request, response);
    }
}
