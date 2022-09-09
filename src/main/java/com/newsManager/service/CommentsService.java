package com.newsManager.service;

import java.sql.SQLException;
import java.util.List;

import com.newsManager.entity.Comment;
/**
 * @author XCDD  邮箱：2207346898@qq.com
 * @description
 * @vision
 */
public interface CommentsService {
    // 通过新闻id查找评论
    public List<Comment> findCommentsByNid(int nid) throws SQLException;
    // 添加评论
    public int addComment(Comment comment) throws SQLException;
    // 删除评论
    public int deleteCommentById(int cid) throws SQLException;
}
