package bbsDAO;

import java.util.*;
import java.sql.ResultSet;

/**
 * 主题业务逻辑实现类：
 * 
 * @version 1.0
 * @author wnf @time 2012=3-13
 */
public class Topic {

	protected int id;

	protected String title;

	protected String content;

	protected String author;

	protected String submittime;

	protected int forumid;

	protected String level;

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Topic() {
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getForumid() {
		return forumid;
	}

	public void setForumid(int forumid) {
		this.forumid = forumid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubmittime() {
		return submittime;
	}

	public void setSubmittime(String submittime) {
		this.submittime = submittime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 插入主题实现类
	 * 
	 * @param db
	 * @return
	 * @throws Exception
	 */
	public boolean insert(DB db, String username) throws Exception {
		String strSql;
		String strSql1;
		String strSql2;
		ResultSet rs;
		int submit;
		int iMaxId;
		strSql2 = "select submit from user where username ='" + username + "'";
		strSql = "Select max(id) From topic";
		rs = db.OpenSql(strSql);
		if (rs.next()) {
			iMaxId = rs.getInt(1) + 1;
		} else {
			iMaxId = 1;
		}
		rs = db.OpenSql(strSql2);
		if (rs.next()) {

			submit = rs.getInt("submit");
		} else {

			submit = 0;
		}
		GregorianCalendar calendar = new GregorianCalendar();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);

		String submittime = year + "-" + month + "-" + day;

		strSql = "insert into topic values(" + iMaxId + ",'" + title + "','"
				+ content + "','" + author + "','" + submittime + "',"
				+ forumid + ",'" + " " + "')";
		/**
		 * 更新用户发表帖子数量：
		 */
		strSql1 = "update user set submit ='" + submit + "'" + "+" + '1'
				+ " where username ='" + username + "'";

		if (db.ExecSql(strSql) == 0 || db.ExecSql(strSql1) == 0) {
			return false;
		} else {
			return true;
		}

	}

	public static @SuppressWarnings("unchecked")
	Vector search(DB db, String title) throws Exception {
		Vector Topics = new Vector();
		ResultSet rs;
		String strSql = null;
		/**
		 * 模糊查询,给定关键字进行查询
		 */
		strSql = "select * from topic where title like '%" + title + "%'";
		rs = db.OpenSql(strSql);

		while (rs.next()) {
			Topic topic = new Topic();

			topic.setId(rs.getInt("id"));
			topic.setTitle(rs.getString("title"));
			topic.setAuthor(rs.getString("author"));
			topic.setContent(rs.getString("content"));
			topic.setLevel(rs.getString("level"));
			Topics.add(topic);
		}

		return Topics;
	}

	public static boolean delete(DB db, String id) throws Exception {
		String strSql;
		String strSql1;
		strSql = "delete from topic where id=" + id;
		strSql1 = "delete from response where topicid=" + id;
		if (db.ExecSql(strSql) == 0 || db.ExecSql(strSql1) == 0) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean manage(DB db, String id) throws Exception {
		String strSql;
		strSql = "update topic set level='" + "精" + "'" + "where id=" + id;
		if (db.ExecSql(strSql) == 0) {
			return false;
		} else {
			return true;
		}

	}

}