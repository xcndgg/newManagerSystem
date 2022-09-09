package com.newsManager.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.newsManager.dao.CommentsDao;
import com.newsManager.entity.Comment;
import com.newsManager.dao.impl.CommentsDaoImpl;
import com.newsManager.service.CommentsService;
/**
 * @author XCDD  邮箱：2207346898@qq.com
 * @description
 * @vision
 */
public class CommentsServiceImpl implements CommentsService {
    CommentsDao commentsDao = new CommentsDaoImpl();

    @Override
    public List<Comment> findCommentsByNid(int nid) throws SQLException {
        return commentsDao.getCommentsByNid(nid);

    }

    @Override
    public int addComment(Comment comment) throws SQLException {
        return commentsDao.addComment(comment);
    }

    @Override
    public int deleteCommentById(int cid) throws SQLException {
        return commentsDao.deleteCommentsById(cid);
    }

}
