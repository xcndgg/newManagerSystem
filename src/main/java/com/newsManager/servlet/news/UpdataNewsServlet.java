package com.newsManager.servlet.news; /**
 * @description
 * @author xcdgg
 * @date 2022/6/24 14:26
 */

import com.newsManager.entity.News;
import com.newsManager.service.NewsService;
import com.newsManager.service.impl.NewsServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "UpdataNewsServlet", value = "/UpdataNewsServlet")
public class UpdataNewsServlet extends HttpServlet {
    NewsService newsService = new NewsServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            String contextPath = request.getContextPath();
            News news = new News();
            String nid = request.getParameter("nid");
            if (nid != null && (nid = nid.trim()).length() > 0)
                news.setNid(Integer.parseInt(nid));
            String fieldName = ""; // 表单字段元素的name属性值
            boolean isMultipart = ServletFileUpload
                    .isMultipartContent(request);
            // 上传文件的存储路径（服务器文件系统上的绝对文件路径）
            String uploadFilePath = getServletContext().getRealPath(
                    "upload");
            if (isMultipart) {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setSizeMax(1024 * 1024 * 5);
                // 解析form表单中所有文件
                try {
                    List<FileItem> items = upload.parseRequest(request);
                    Iterator<FileItem> iter = items.iterator();
                    FileItem item = null;
                    boolean isUnallowedType = false; // 是否为不允许的类型
                    File saveFile = null; // 上传并保存的文件
                    while (iter.hasNext()) { // 依次处理每个文件
                        item = iter.next();
                        if (item.isFormField()) { // 普通表单字段
                            fieldName = item.getFieldName(); // 表单字段的name属性值
                            if (fieldName.equals("ntid")) { // 主题id
                                news.setNtid(Integer.parseInt(item
                                        .getString("UTF-8")));
                            } else if (fieldName.equals("ntitle")) { // 标题
                                news.setNtitle(item.getString("UTF-8"));
                            } else if (fieldName.equals("nauthor")) {// 作者
                                news.setNauthor(item.getString("UTF-8"));
                            } else if (fieldName.equals("nsummary")) { // 摘要
                                news.setNsummary(item.getString("UTF-8"));
                            } else if (fieldName.equals("ncontent")) { // 内容
                                news.setNcontent(item.getString("UTF-8"));
                            }
                        } else { // 文件表单字段
                            String fileName = item.getName();
                            if (fileName.length() > 0) {
                                List<String> filType = Arrays.asList("gif",
                                        "jpg", "jpeg");
                                int index = fileName.lastIndexOf(".");
                                String ext = index == -1 ? "" : fileName
                                        .substring(index + 1).toLowerCase();
                                if (filType.contains(ext)) { // 判断文件类型是否在允许范围内
                                    File fullFile = new File(item.getName());
                                    saveFile = new File(uploadFilePath,
                                            fullFile.getName());
                                    item.write(saveFile);
                                    news.setNpicpath(uploadFilePath
                                            + File.pathSeparator
                                            + fullFile.getName());
                                } else {
                                    isUnallowedType = true;
                                }
                            }
                        }
                    }
                    if (isUnallowedType) {
                        out.print("<script type=\"text/javascript\">");
                        out.print("alert(\"图片上传失败，文件类型只能是gif、jpg、jpeg\");");
                        out.print("location.href=\"" + contextPath
                                + "/util/news?action=toModifyNews&nid="
                                + news.getNid() + "\";");
                        out.print("</script>");
                    } else {
                        // 更新新闻
                        try {
                            int result = newsService.modifyNews(news);
                            if (result == 0) {
                                out.print("<script type=\"text/javascript\">");
                                out.print("alert(\"未找到相关新闻，点击确认返回新闻列表\");");
                                out.print("location.href=\""
                                        + contextPath
                                        + "/util/news?action=list\";");
                                out.print("</script>");
                            } else {
                                out.print("<script type=\"text/javascript\">");
                                out.print("if (window.confirm(\"新闻修改成功，继续修改评论？\")) {");
                                out.print("location.href=\""
                                        + contextPath
                                        + "/util/news?action=toModifyNews&nid="
                                        + news.getNid() + "\";");
                                out.print("} else {");
                                out.print("location.href=\""
                                        + contextPath
                                        + "/util/news?action=list\";");
                                out.print("}");
                                out.print("</script>");
                            }
                        } catch (SQLException e) {
                            if (saveFile != null)
                                saveFile.delete();
                            out.print("<script type=\"text/javascript\">");
                            out.print("alert(\"新闻更新失败，请联系管理员！\");");
                            out.print("location.href=\""
                                    + contextPath
                                    + "/util/news?action=toModifyNews&nid="
                                    + news.getNid() + "\";");
                            out.print("</script>");
                        }
                    }
                } catch (FileUploadBase.SizeLimitExceededException ex) {
                    out.print("<script type=\"text/javascript\">");
                    out.print("alert(\"图片上传失败，文件的最大限制是：5MB\");");

                    out.print("location.href=\"" + contextPath
                            + "/util/news?action=toModifyNews&nid="
                            + news.getNid() + "\";");
                    out.print("</script>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        this.doGet(request, response);
    }
}
