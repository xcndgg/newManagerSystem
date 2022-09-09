package com.newsManager.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.newsManager.entity.News;
import com.newsManager.entity.Page;
/**
 * @author XCDD  邮箱：2207346898@qq.com
 * @description
 * @vision
 */
public interface NewsService {
    // 获取所有新闻
    public List<News> findAllNews() throws SQLException;
    // 获取某主题下的所有新闻（根据主题id）
    public List<News> findAllNewsByTid(int tid) throws SQLException;
    // 获取某主题下的所有新闻（根据主题名称）
    public List<News> findAllNewsByTname(String tname) throws SQLException;
    // 获取某主题下的最新新闻
    public List<News> findLatestNewsByTid(int tid, int limit) throws SQLException;
    // 初始化一组主题下的最新新闻
    public List<List<News>> findLatestNewsByTid(Map<Integer, Integer> topicsMap)
            throws SQLException;
    //获取某条新闻
    public News findNewsByNid(int nid) throws SQLException;
    // 通过nid删除新闻及相关回复
    public int deleteNews(int nid) throws SQLException;
    // 分页获取新闻
    public void findPageNews(Page pageObj) throws SQLException;
    //添加新闻
    public int addNews(News news) throws SQLException;
    //修改新闻
    public int modifyNews(News news) throws SQLException;
}
