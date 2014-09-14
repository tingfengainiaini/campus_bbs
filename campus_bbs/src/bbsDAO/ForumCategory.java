/**
 * 2012-5-15
 * wnf
 * 下午07:14:59
 */
package bbsDAO;

import java.sql.ResultSet;
import java.util.Vector;

public class ForumCategory {

	private int categoryid;

	private String categoryname;

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public ForumCategory(int id, String name) {
		this.categoryid = id;
		this.categoryname = name;

	}

	public static @SuppressWarnings("unchecked")
	Vector searchAllForumCategorys(DB db) throws Exception {
		int categoryid = 0;
		String categoryname = null;
		Vector forumcategoryVector = new Vector();
		ResultSet rs;
		String strSql;

		strSql = "select * from forumcategory";
		rs = db.OpenSql(strSql);
		while (rs.next()) {
			categoryid = rs.getInt("categoryid");
			categoryname = rs.getString("categoryname");

			forumcategoryVector
					.add(new ForumCategory(categoryid, categoryname));

		}
		return forumcategoryVector;

	}

}
