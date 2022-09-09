<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
  <div class="sidebar">
    <h1> <img src="images/title_1.gif" alt="国内新闻" /> </h1>
    <div class="side_list">
      <ul>
      	<c:forEach items="${list1}" var="news">
            <li><a href='util/news?action=readNew&nid=${news.nid}'><b>${news.ntitle}</b></a></li>
      	</c:forEach>
      </ul>
    </div>
    <h1> <img src="images/title_2.gif" alt="国际新闻" /> </h1>
    <div class="side_list">
      <ul>
      	<c:forEach items="${list2}" var="news">
            <li><a href='util/news?action=readNew&nid=${news.nid}'><b>${news.ntitle}</b></a></li>
        </c:forEach>
      </ul>
    </div>
    <h1> <img src="images/title_3.gif" alt="娱乐新闻" /> </h1>
    <div class="side_list">
      <ul>
      	<c:forEach items="${list3}" var="news">
            <li><a href='util/news?action=readNew&nid=${news.nid}'><b>${news.ntitle}</b></a></li>
        </c:forEach>
      </ul>
    </div>
  </div>
<%--
	request.removeAttribute("news_in_topic");
--%>
