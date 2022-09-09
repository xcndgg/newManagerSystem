package com.newsManager.dao;

import java.sql.SQLException;
import java.util.List;

import com.newsManager.entity.News;
/**
 * @author XCDD  邮箱：2207346898@qq.com
 * @description
 * @vision
 */
public interface NewsDao {
    // 获取所有新闻
    public List<News> getAllnews() throws SQLException;
    // 获取某主题下的所有新闻（根据主题id）
    public List<News> getAllnewsByTID(int Tid) throws SQLException;
    // 获取某主题下的所有新闻（根据主题名称）
    public List<News> getAllnewsByTname(String tname) throws SQLException;
    // 获取某主题下的新闻数量
    public int getNewsCountByTID(int Tid) throws SQLException;
    // 获取某主题下的最新新闻
    public List<News> getLatestNewsByTID(int tid, int limit) throws SQLException;
    // 获取某条新闻
    public News getNewsByNID(int nid) throws SQLException;
    // 删除某条新闻
    public int deleteNews(int nid) throws SQLException;
    // 获得新闻总数
    public int getTotalCount() throws SQLException;
    // 分页获得新闻
    public List<News> getPageNewsList(int pageNo, int pageSize) throws SQLException;
    //添加新闻
    public int addNews(News news) throws SQLException;
    //修改新闻
    public int updateNews(News news) throws SQLException;
}