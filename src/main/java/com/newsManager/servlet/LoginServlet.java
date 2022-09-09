package com.newsManager.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newsManager.entity.User;
import com.newsManager.service.LoginService;
import com.newsManager.service.impl.LoginServiceImpl;
/**
 * @author XCDD  邮箱：2207346898@qq.com
 * @description
 * @vision
 */
public class LoginServlet extends BaseServlet {
    private static final long serialVersionUID = 7308078748761515673L;
    LoginService userService = new LoginServiceImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String contextPath = request.getContextPath();
        try {
            String uname = request.getParameter("uname");
            String password = request.getParameter("upwd");
            User user = new User();
            user.setUname(uname);
            user.setUpwd(password);
            user = userService.doLogin(user);
            if (user == null) {
                out.print("<script type=\"text/javascript\">");
                out.print("alert(\"用户名密码错误，请重新登录\");");
                out.print("open(\"" + contextPath
                        + "/index.jsp\",\"_self\");");
                out.print("</script>");
            } else {
                request.getSession().setAttribute("admin", uname);
                response.sendRedirect(contextPath + "/util/news?action=list");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.flush();
        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        this.doGet(request, response);
    }

}
