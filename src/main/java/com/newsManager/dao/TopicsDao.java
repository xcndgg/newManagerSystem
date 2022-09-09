package com.newsManager.dao;

import java.sql.SQLException;
import java.util.List;

import com.newsManager.entity.Topic;
/**
 * @author XCDD  邮箱：2207346898@qq.com
 * @description
 * @vision
 */
public interface TopicsDao {
    // 获取所有主题
    public List<Topic> getAllTopics() throws SQLException;
    // 更新主题
    public int updateTopic(Topic topic) throws SQLException;
    // 根据名字查找主题
    public Topic findTopicByName(String name) throws SQLException;
    // 添加主题
    public int addTopic(String name) throws SQLException;
    // 通过tid删除主题
    public int deleteTopic(int tid) throws SQLException;
}