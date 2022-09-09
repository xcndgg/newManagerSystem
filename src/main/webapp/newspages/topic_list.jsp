<%@ page language="java" import="java.util.*,com.xc.newsmanager.entity.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>添加主题--管理后台</title>
  <link href="../css/admin.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="header">
  <div id="welcome">
    欢迎使用新闻管理系统！</div>
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
    <ul class="classlist">
      <c:forEach items="${requestScope.list}" var="topic">
        <li> &#160;&#160;&#160;&#160; ${topic.tname} &#160;&#160;&#160;&#160; <a href='../newspages/topic_modify.jsp?tid=${topic.tid}&tname=${topic.tname}'>修改</a> &#160;&#160;&#160;&#160; <a href='../util/topics?action=del&tid=${topic.tid}'>删除</a> </li>
      </c:forEach>
    </ul>
  </div>
</div>
<div id="footer">
  <%@ include file="console_element/bottom.html" %>
</div>
</body>
</html>
