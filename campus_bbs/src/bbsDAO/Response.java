package bbsDAO;

import java.sql.ResultSet;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

/**
 * 话题回复 业务逻辑实现类
 * 
 * @author wnf
 * @time 2012-3-10
 */
public class Response {

	private int id;
	private String title;
	private String content;
	private String author;
	private String submittime;
	private int topicid;

	private String grade;

	public Response() {
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

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
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

	public int getTopicid() {
		return topicid;
	}

	public void setTopicid(int topicid) {
		this.topicid = topicid;
	}

	/**
	 * 向回复表中插入回复帖子：（注意一点就是帖子的标识号的增加）
	 * 
	 * @param db
	 * @return
	 * @throws Exception
	 * @author wnf
	 */
	public boolean insert(DB db) throws Exception {
		String strSql;
		ResultSet rs;
		int iMaxId;
		/**
		 * 先计算id号
		 */
		strSql = "select max(id) from response";
		rs = db.OpenSql(strSql);
		if (rs.next()) {
			iMaxId = rs.getInt(1) + 1;
		} else {
			iMaxId = 1;
		}
		/**
		 * 取当前时间：
		 */
		GregorianCalendar calendar = new GregorianCalendar();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);

		String submittime = year + "-" + month + "-" + day;

		// System.out.println("值：" + iMaxId + "iMaxId--" + title + "title--" +
		// content + "content--" + owner + "owner--" + topicid+ "topicid--" );
		strSql = "insert into response values(" + iMaxId + ",'" + title + "','"
				+ content + "','" + author + "','" + submittime + "',"
				+ topicid + ")";
		// strSql =
		// "insert into response values(5,'title','test','own','2006-01-01',1);";
		if (db.ExecSql(strSql) == 0) {
			return false;
		} else {
			return true;
		}

	}

	/**
	 * 查询回复帖子实现
	 * 
	 * @param db
	 * @param topicid
	 * @return
	 * @throws Exception
	 */
	public static @SuppressWarnings("unchecked")
	Vector search(DB db, int topicid) throws Exception {
		Vector Contents = new Vector();
		ResultSet rs, rsNest;
		String strSql = null;

		strSql = "select * from topic where id=" + topicid;
		rs = db.OpenSql(strSql);

		if (rs.next()) {
			Response response = new Response();

			response.setId(rs.getInt("id"));
			response.setTitle(rs.getString("title"));
			response.setContent(rs.getString("content"));
			response.setAuthor(rs.getString("author"));
			response.setSubmittime(rs.getString("submittime"));
			response.setTopicid(topicid);

			strSql = "select * from user where username='"
					+ response.getAuthor() + "'";
			rsNest = db.OpenSql(strSql);
			if (rsNest.next()) {
				response.setGrade(rsNest.getString("grade"));
			}

			Contents.add(response);
		}

		strSql = "select * from response where topicid=" + topicid
				+ " order by id desc";
		rs = db.OpenSql(strSql);

		while (rs.next()) {
			Response response = new Response();

			response.setId(rs.getInt("id"));
			response.setTitle(rs.getString("title"));
			response.setContent(rs.getString("content"));
			response.setAuthor(rs.getString("author"));
			response.setSubmittime(rs.getString("submittime"));
			response.setTopicid(topicid);

			strSql = "select * from user where username='"
					+ response.getAuthor() + "'";
			rsNest = db.OpenSql(strSql);
			if (rsNest.next()) {
				response.setGrade(rsNest.getString("grade"));
			}

			Contents.add(response);
		}
		return Contents;
	}
}