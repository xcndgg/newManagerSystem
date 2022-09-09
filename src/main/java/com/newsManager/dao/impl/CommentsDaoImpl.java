package com.newsManager.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.newsManager.dao.BaseDao;
import com.newsManager.dao.CommentsDao;
import com.newsManager.entity.Comment;

/**
 * @author XCDD  邮箱：2207346898@qq.com
 * @description
 * @vision
 */
public class CommentsDaoImpl extends BaseDao implements CommentsDao {

    public List<Comment> getCommentsByNid(int nid) throws SQLException {
        List<Comment> list = new ArrayList<Comment>();
        String sql = "SELECT * FROM `comments` WHERE `cnid` = ?"
                + " ORDER BY `cdate` desc";
        return queryForList(Comment.class, sql, nid);
    }

    public int addComment(Comment comment) throws SQLException {
        String sql = "INSERT INTO `comments`(`CNID`, `CCONTENT`, `CDATE`," +
                "`CIP`,`CAUTHOR`) VALUES(?, ?, ?, ?, ?)";
        return update(sql, comment.getCnid(), comment.getCcontent(), comment.getCdate(), comment.getCip(), comment.getCauthor());
    }

    public int deleteCommentsByNid(int nid) throws SQLException {
        String sql = "DELETE FROM `comments` WHERE `CNID` = ?";
        return update(sql, nid);
    }

    public int deleteCommentsById(int cid) throws SQLException {
        String sql = "DELETE FROM `comments` WHERE `CID` = ?";
        return update(sql, cid);
    }
}
