package com.newsManager.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.newsManager.entity.Topic;
import com.newsManager.dao.NewsDao;
import com.newsManager.dao.TopicsDao;
import com.newsManager.dao.impl.NewsDaoImpl;
import com.newsManager.dao.impl.TopicsDaoImpl;
import com.newsManager.service.TopicsService;
/**
 * @author XCDD  邮箱：2207346898@qq.com
 * @description
 * @vision
 */
public class TopicsServiceImpl implements TopicsService {
    TopicsDao topicsDao = new TopicsDaoImpl();

    @Override
    public List<Topic> findAllTopics() throws SQLException {
        return topicsDao.getAllTopics();
    }

    @Override
    public int updateTopic(Topic topic) throws SQLException {

        int result;
        if (topicsDao.findTopicByName(topic.getTname()) == null) {
            result = topicsDao.updateTopic(topic);
        } else {
            result = -1;
        }
        return result;
    }

    @Override
    public Topic findTopicByName(String name) throws SQLException {
        return topicsDao.findTopicByName(name);

    }

    @Override
    public int addTopic(String name) throws SQLException {

        int result;
        if (topicsDao.findTopicByName(name) == null) {
            result = topicsDao.addTopic(name);
        } else {
            result = -1;
        }
        return result;
    }

    @Override
    public int deleteTopic(int tid) throws SQLException {

        int result;

        NewsDao newsDao = new NewsDaoImpl();
        TopicsDao topicsDao = new TopicsDaoImpl();

        if (newsDao.getNewsCountByTID(tid) == 0) {
            result = topicsDao.deleteTopic(tid);
        } else {
            result = -1;
        }
        return result;
    }

}
