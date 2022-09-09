<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${sessionScope.list == null}">
        <c:redirect url="../util/news?action=list"/>
    </c:when>
    <c:otherwise>
        <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
        <html>
        <head>
            <meta http-equiv="pragma" content="no-cache">
            <meta http-equiv="cache-control" content="no-cache">
            <meta http-equiv="expires" content="0">
            <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
            <meta http-equiv="description" content="This is my page">
            <title>添加主题--管理后台</title>
            <link href="../css/admin.css" rel="stylesheet" type="text/css" />
        </head>
        <body>
        <div id="header">
            <div id="welcome">欢迎使用新闻管理系统！</div>
            <div id="nav">
                <div id="logo"><img src="../images/logo.jpg" alt="新闻中国" /></div>
                <div id="a_b01"><img src="../images/a_b01.gif" alt="" /></div>
            </div>
        </div>
        <div id="admin_bar">
            <div id="status">管理员： 登录  &#160;&#160;&#160;&#160; <a href="#">login out</a></div>
            <div id="channel"> </div>
        </div>
        <div id="main">
            <%@ include file="console_element/left.html" %>
            <div id="opt_area">
                <script type="text/javascript" >
                    function clickdel(nid){
                        if (confirm("此新闻的相关评论也将删除，确定删除吗？"))
                            window.location="../util/news?action=delete&nid="+nid;
                    }

                </script>
                <ul class="classlist">
                    <c:forEach items="${sessionScope.list}" var="news" varStatus="i">
                        <li>${news.ntitle}<span> 作者：${news.nauthor} &#160;&#160;&#160;&#160;
<a href='../util/news?action=toModifyNews&nid=${news.nid}'>修改</a> &#160;&#160;&#160;&#160;
<a href='javascript:;' onclick='return clickdel(${news.nid})'>删除</a></span> </li>
                        <c:if test="${i.count % 5 == 0}">
                            <li class='space'></li>
                        </c:if>
                    </c:forEach>
                    <c:remove var="list" scope="session"/>
                </ul>
            </div>
        </div>
        <div id="footer">
            <%@ include file="console_element/bottom.html" %>
        </div>
        </body>
        </html>
    </c:otherwise>
</c:choose>