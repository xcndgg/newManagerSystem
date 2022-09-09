package com.newsManager.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.newsManager.dao.CommentsDao;
import com.newsManager.dao.impl.CommentsDaoImpl;
import com.newsManager.dao.impl.NewsDaoImpl;
import com.newsManager.entity.News;
import com.newsManager.entity.Page;
import com.newsManager.service.NewsService;
/**
 * @author XCDD  邮箱：2207346898@qq.com
 * @description
 * @vision
 */
public class NewsServiceImpl implements NewsService {
    NewsDaoImpl newsDao = new NewsDaoImpl();
    CommentsDao commentsDao = new CommentsDaoImpl();

    @Override
    public List<News> findAllNews() throws SQLException {
        return newsDao.getAllnews();
    }

    @Override
    public List<News> findAllNewsByTid(int tid) throws SQLException {
        return newsDao.getAllnewsByTID(tid);

    }

    @Override
    public List<News> findAllNewsByTname(String tname) throws SQLException {
        return newsDao.getAllnewsByTname(tname);
    }

    @Override
    public List<News> findLatestNewsByTid(int tid, int limit)
            throws SQLException {
        return newsDao.getLatestNewsByTID(tid, limit);
    }

    @Override
    public List<List<News>> findLatestNewsByTid(Map<Integer, Integer> topicsMap)
            throws SQLException {
        List<List<News>> result = null;
        if (topicsMap != null && topicsMap.size() != 0) {
            result = new ArrayList<List<News>>();
            Iterator<Entry<Integer, Integer>> topics = topicsMap.entrySet()
                    .iterator();
            while (topics.hasNext()) {
                Entry<Integer, Integer> oneTopic = topics.next();
                result.add(newsDao.getLatestNewsByTID(oneTopic.getKey(),
                        oneTopic.getValue()));
            }
        }
        return result;

    }

    @Override
    public News findNewsByNid(int nid) throws SQLException {
        return newsDao.getNewsByNID(nid);
    }

    @Override
    public int deleteNews(int nid) throws SQLException {
        commentsDao.deleteCommentsByNid(nid);
        // 删除新闻
        return newsDao.deleteNews(nid);


    }

    // 分页获取新闻
    public void findPageNews(Page pageObj) throws SQLException {
        int totalCount = newsDao.getTotalCount();
        pageObj.setTotalCount(totalCount); // 设置总数量并计算总页数
        if (totalCount > 0) {
            if (pageObj.getCurrPageNo() > pageObj.getTotalPageCount())
                pageObj.setCurrPageNo(pageObj.getTotalPageCount());
            List<News> newsList = newsDao.getPageNewsList(
                    pageObj.getCurrPageNo(), pageObj.getPageSize());
            pageObj.setNewsList(newsList);
        } else {
            pageObj.setCurrPageNo(0);
            pageObj.setNewsList(new ArrayList<News>());
        }
    }


    @Override
    public int addNews(News news) throws SQLException {
        // 添加新闻
        news.setNcreatedate(new Date());
        news.setNmodifydate(news.getNcreatedate());
        return newsDao.addNews(news);


    }

    @Override
    public int modifyNews(News news) throws SQLException {
        int result;
        news.setNmodifydate(new Date());
        result = newsDao.updateNews(news);
        return result;
    }
}
