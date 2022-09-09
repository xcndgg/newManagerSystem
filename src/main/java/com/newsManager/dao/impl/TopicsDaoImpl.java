package com.newsManager.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.newsManager.dao.BaseDao;
import com.newsManager.dao.TopicsDao;
import com.newsManager.entity.Topic;
/**
 * @author XCDD  邮箱：2207346898@qq.com
 * @description
 * @vision
 */
public class TopicsDaoImpl extends BaseDao implements TopicsDao {


    public List<Topic> getAllTopics() throws SQLException {
        List<Topic> list = new ArrayList<Topic>();
        String sql = "select * from topic";
        return queryForList(Topic.class, sql);
    }

    public int deleteTopic(int tid) throws SQLException {
        String sql = "DELETE FROM `TOPIC` WHERE `tid` = ?";
        return update(sql, tid);

    }

    public int updateTopic(Topic topic) throws SQLException {
        String sql = "UPDATE `TOPIC` SET `tname` = ? WHERE `tid` = ?";
        return update(sql, topic.getTname(), topic.getTid());


    }

    public Topic findTopicByName(String name) throws SQLException {
        String sql = "select * from topic where tname=?";
        return queryForOne(Topic.class, sql, name);


    }

    public int addTopic(String name) throws SQLException {
        String sql = "insert into topic(TNAME) values(?)";
        int result = 0;
        result = update(sql, name);
        return result;
    }
}
