package com.newsManager.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.newsManager.dao.BaseDao;
import com.newsManager.entity.News;
import com.newsManager.dao.NewsDao;
/**
 * @author XCDD  邮箱：2207346898@qq.com
 * @description
 * @vision
 */
public class NewsDaoImpl extends BaseDao implements NewsDao {
    // 获取所有新闻
    public List<News> getAllnews() throws SQLException {
        String sql = "SELECT `nid`, `ntid`, `ntitle`, `nauthor`,"
                + " `ncreateDate`, `nsummary`, `tname` FROM `NEWS`, `TOPIC`"
                + " WHERE `NEWS`.`ntid` = `TOPIC`.`tid`"
                + " ORDER BY `ncreateDate` DESC";

        return queryForList(News.class, sql);
    }

    // 获取某主题下的所有新闻
    public List<News> getAllnewsByTID(int tid) throws SQLException {
        String sql = "SELECT `nid`, `ntid`, `ntitle`, `nauthor`,"
                + " `ncreateDate`, `nsummary`, `tname` FROM `NEWS`, `TOPIC`"
                + " WHERE `NEWS`.`ntid` = `TOPIC`.`tid` AND `NEWS`.`ntid` = ?"
                + " ORDER BY `ncreateDate` DESC";

        return queryForList(News.class, sql, tid);
    }

    // 获取某主题下的最新新闻
    public List<News> getLatestNewsByTID(int tid, int limit)
            throws SQLException {
        String sql = "SELECT `nid`, `ntid`, `ntitle` FROM `NEWS` WHERE"
                + " `ntid` = ? ORDER BY `ncreatedate` DESC LIMIT ?";
        return queryForList(News.class, sql, tid, limit);
    }

    // 获取某主题下的新闻数量
    public int getNewsCountByTID(int tid) throws SQLException {
        String sql = "SELECT COUNT(`ntid`) FROM `news` WHERE `ntid` = ?";
        Number count = (Number) queryForSingleValue(sql, tid);
        return count.intValue();
    }

    // 获取某条新闻
    public News getNewsByNID(int nid) throws SQLException {
        String sql = "SELECT * FROM `NEWS`, `TOPIC`"
                + " WHERE `NEWS`.`ntid` = `TOPIC`.`tid` AND `NEWS`.`nid` = ?"
                + " ORDER BY `ncreateDate` DESC";

        return queryForOne(News.class, sql, nid);
    }


    public List<News> getAllnewsByTname(String tname) throws SQLException {
        // 获取某主题下的所有新闻
        String sql = "SELECT `nid`, `ntid`, `ntitle`, `nauthor`,"
                + " `ncreateDate`, `nsummary`, `tname` FROM `NEWS`, `TOPIC`"
                + " WHERE `NEWS`.`ntid` = `TOPIC`.`tid` AND `TOPIC`.`tname` = ?"
                + " ORDER BY `ncreateDate` DESC";

        return queryForList(News.class, sql, tname);
    }

    // 删除某条新闻
    public int deleteNews(int nid) throws SQLException {
        String sql = "DELETE FROM `NEWS` WHERE `NID` = ?";
        return update(sql, nid);
    }

    // 获得所有新闻的数量
    public int getTotalCount() throws SQLException {

        String sql = "SELECT COUNT(`nid`) FROM `news`";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    // 分页获得新闻
    public List<News> getPageNewsList(int pageNo, int pageSize)
            throws SQLException {
        String sql = "SELECT `nid`, `ntid`, `ntitle`, `nauthor`,"
                + " `ncreateDate`, `nsummary`, `tname` FROM `NEWS`, `TOPIC`"
                + " WHERE `NEWS`.`ntid` = `TOPIC`.`tid`"
                + " ORDER BY `ncreateDate` DESC LIMIT ?, ?";

        return queryForList(News.class, sql, (pageNo - 1) * pageSize, pageSize);
    }


    @Override
    public int addNews(News news) throws SQLException {
        String sql = "insert into news(NTID,NTITLE,NAUTHOR,NCONTENT,NSUMMARY,"
                + "NCREATEDATE,NMODIFYDATE,NPICPATH) values(?,?,?,?,?,?,?,?)";

        return update(sql, news.getNtid(), news.getNtitle(),
                news.getNauthor(), news.getNcontent(),
                news.getNsummary(), news.getNcreatedate(),
                news.getNmodifydate(), news.getNpicpath());
    }

    @Override
    public int updateNews(News news) throws SQLException {
        String sql = "UPDATE news SET NTID=?,NTITLE=?,NAUTHOR=?,NCONTENT=?,NSUMMARY=?,"
                + "NMODIFYDATE=?,NPICPATH=? where NID=?";

        return update(sql, news.getNtid(), news.getNtitle(),
                news.getNauthor(), news.getNcontent(),
                news.getNsummary(), news.getNmodifydate(),
                news.getNpicpath(), news.getNid());

    }
}