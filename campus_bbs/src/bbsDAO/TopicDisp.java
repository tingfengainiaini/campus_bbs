package bbsDAO;

import java.sql.ResultSet;
import java.util.Vector;

/**
 * 主题服务实现类：
 * 
 * @author wnf @time 2012-3-12
 * 
 */
public class TopicDisp extends Topic {

	private int reCount;

	private String lastTalk;

	private String level = "精";

	public TopicDisp() {
	}

	public int getReCount() {

		return reCount;

	}

	public void setReCount(int reCount) {

		this.reCount = reCount;
	}

	public String getLastTalk() {

		return lastTalk;
	}

	public void setLastTalk(String lastTalk) {

		this.lastTalk = lastTalk;
	}

	/**
	 * 取得主题数量
	 * 
	 * @param db
	 * @param forumid
	 * @return
	 * @throws Exception
	 */
	public int getTopicCount(DB db, int forumid) throws Exception {
		ResultSet rs;
		String strSql = null;
		int iRecordCount = 0;

		strSql = "select count(*) from topic where forumid=" + forumid;
		rs = db.OpenSql(strSql);
		if (rs.next()) {
			iRecordCount = rs.getInt(1);
		}
		return iRecordCount;
	}

	public @SuppressWarnings("unchecked")
	Vector search(DB db, int pageid) throws Exception {
		Vector Topics = new Vector();
		ResultSet rs, rsNest;
		String strSql = null;
		int iCurRecord = 0;
		/**
		 * 按照主题号降序排列所有的主题
		 */
		strSql = "select * from topic where forumid=" + forumid
				+ " order by id desc";
		rs = db.OpenSql(strSql);

		int iCount = 0;
		/**
		 * 当前叶号乘页面长度+1就得到所要的页面：
		 */
		iCurRecord = pageid * Constants.TOPIC_PAGE_SIZE + 1;
		/**
		 * 移动光标到icurRecourd行，正的正着数，负的倒着数
		 */
		rs.absolute(iCurRecord);
		do {
			TopicDisp topic = new TopicDisp();

			topic.setId(rs.getInt("id"));
			topic.setTitle(rs.getString("title"));
			topic.setContent(rs.getString("content"));
			topic.setAuthor(rs.getString("author"));
			topic.setSubmittime(rs.getString("submittime"));
			topic.setForumid(forumid);
			topic.setLastTalk(rs.getString("submittime"));

			topic.setLevel(rs.getString("level"));

			strSql = "select count(*) from response where topicid=" + topic.id;
			rsNest = db.OpenSql(strSql);
			if (rsNest.next()) {
				topic.setReCount(rsNest.getInt(1));
			}

			strSql = "select * from response where topicid=" + topic.id
					+ " order by id desc";
			rsNest = db.OpenSql(strSql);
			if (rsNest.next()) {
				topic.setLastTalk(rsNest.getString("submittime"));
			}

			Topics.add(topic);
			iCount++;
			/**
			 * 长度判断：
			 */

			if (iCount >= Constants.TOPIC_PAGE_SIZE) {
				break;
			}
		} while (rs.next());
		return Topics;
	}

	public @SuppressWarnings("unchecked")
	Vector searchbylevel(DB db, int pageid) throws Exception {
		Vector Topics = new Vector();
		ResultSet rs, rsNest;
		String strSql = null;
		int iCurRecord = 0;
		/**
		 * 按照主题号降序排列所有的主题
		 */
		strSql = "select * from topic where forumid=" + forumid
				+ " and level='" + level + "'" + " order by id desc";
		rs = db.OpenSql(strSql);
		if (rs.next()) {
			int iCount = 0;
			/**
			 * 当前叶号乘页面长度+1就得到所要的页面：
			 */
			iCurRecord = pageid * Constants.TOPIC_PAGE_SIZE + 1;
			/**
			 * 移动光标到icurRecourd行，正的正着数，负的倒着数
			 */
			rs.absolute(iCurRecord);

			do {

				TopicDisp topic = new TopicDisp();

				topic.setId(rs.getInt("id"));
				topic.setTitle(rs.getString("title"));
				topic.setContent(rs.getString("content"));
				topic.setAuthor(rs.getString("author"));
				topic.setSubmittime(rs.getString("submittime"));
				topic.setForumid(forumid);
				topic.setLastTalk(rs.getString("submittime"));

				topic.setLevel(rs.getString("level"));

				strSql = "select count(*) from response where topicid="
						+ topic.id;
				rsNest = db.OpenSql(strSql);
				if (rsNest.next()) {
					topic.setReCount(rsNest.getInt(1));
				}

				strSql = "select * from response where topicid=" + topic.id
						+ " order by id desc";
				rsNest = db.OpenSql(strSql);
				if (rsNest.next()) {
					topic.setLastTalk(rsNest.getString("submittime"));
				}

				Topics.add(topic);
				iCount++;
				/**
				 * 长度判断：
				 */

				if (iCount >= Constants.TOPIC_PAGE_SIZE) {
					break;
				}

			} while (rs.next());
		} else {
			Topics = null;
		}
		return Topics;
	}
}