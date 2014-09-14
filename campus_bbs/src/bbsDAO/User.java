package bbsDAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Vector;

/**
 * 用户类，封装用户的详细信息，
 * 
 * @version 1.0
 * @author wnf @time 2012-3-14
 * 
 */
public class User {

	private String username = null;

	private String password = null;

	private String sex = null;

	private String email = null;

	private String qq = null;

	private String signature = null;

	private String grade = null;

	private String registertime = null;

	private int submit;

	private String lastlogtime = null;

	public String getLastlogtime() {
		return lastlogtime;
	}

	public void setLastlogtime(String lastlogtime) {
		this.lastlogtime = lastlogtime;
	}

	public String getRegistertime() {
		return registertime;
	}

	public void setRegistertime(String registertime) {
		this.registertime = registertime;
	}

	public int getSubmit() {
		return submit;
	}

	public void setSubmit(int submit) {
		this.submit = submit;
	}

	public User() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 用户 存在性验证：
	 */
	public static boolean checkUser(DB db, String username, String password)
			throws Exception {
		String strSql;
		ResultSet rs;
		strSql = "select * from user where username='" + username
				+ "' and password='" + password + "'";
		rs = db.OpenSql(strSql);
		if (rs.next()) {
			return true;
		} else {
			return false;
		}

	}

	/***
	 * 用户插入类：
	 * 
	 * @param db
	 * @return
	 * @throws Exception
	 */
	public boolean insert(DB db) throws Exception {
		String strSql;
		System.out.print("用户qq:"+qq);
		strSql = "insert into user values('" + username + "','" + password
				+ "','" + sex + "','" + email + "','" + qq + "','" + signature
				+ "','" + " " + "','" + '0' + "','" + " " + "','" + " " + "')";
		if (db.ExecSql(strSql) == 0) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean updateuserregistertime(DB db, String time,
			String username) throws Exception {
		String strSql;
		strSql = "update user set registertime ='" + time + "'"
				+ "where username ='" + username + "'";
		if (db.ExecSql(strSql) == 0) {
			return false;
		} else {
			return true;
		}

	}

	public static boolean updatelastlogtime(DB db, String time, String username)
			throws Exception {
		String strSql;
		strSql = "update user set lastlogtime ='" + time + "'"
				+ "where username ='" + username + "'";
		if (db.ExecSql(strSql) == 0) {

			return false;
		} else {

			return true;
		}

	}

	/**
	 *取用户等级(根据名字)类：
	 * 
	 * @param db
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public static String getUserGrade(DB db, String username) throws Exception {
		String strSql;
		ResultSet rs;
		strSql = "select * from user where username='" + username + "'";
		rs = db.OpenSql(strSql);
		if (rs.next()) {
			return rs.getString("grade");
		} else {
			return null;
		}

	}

	/**
	 * 根据名字模糊查询
	 * 
	 * @param db
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public static @SuppressWarnings("unchecked")
	Vector search(DB db, String username) throws Exception {
		Vector Users = new Vector();
		ResultSet rs;
		String strSql = null;

		strSql = "select * from user where username like '%" + username + "%'";
		rs = db.OpenSql(strSql);

		while (rs.next()) {
			User user = new User();

			user.setUsername(rs.getString("username"));
			user.setGrade(rs.getString("grade"));

			Users.add(user);
		}
		return Users;
	}

	/**
	 * 查询用户详细信息；
	 * 
	 * @param db
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public static @SuppressWarnings("unchecked")
	Vector searchallInformation(DB db, String username) throws Exception {
		Vector Users = new Vector();
		ResultSet rs;
		String strSql = null;
		/**
		 * 乱码问题检查：
		 */
		System.out.print(username);

		strSql = "select * from user where username ='" + username + "'";
		rs = db.OpenSql(strSql);

		while (rs.next()) {
			User user = new User();

			user.setUsername(rs.getString("username"));
			user.setGrade(rs.getString("grade"));
			user.setEmail(rs.getString("email"));
			user.setQq(rs.getString("qq"));
			user.setSex(rs.getString("sex"));
			user.setSignature(rs.getString("signature"));
			user.setSubmit(rs.getInt("submit"));
			user.setRegistertime(rs.getString("registertime"));
			user.setLastlogtime(rs.getString("lastlogtime"));
			Users.add(user);
		}
		return Users;
	}

	/**
	 * 查询所有用户
	 * 
	 * @param db
	 * @return
	 * @throws Exception
	 */
	public static @SuppressWarnings("unchecked")
	Vector searchUsers(DB db) throws Exception {
		Vector userVector = new Vector();
		ResultSet rs;
		String strSql = null;

		strSql = "select * from user";
		rs = db.OpenSql(strSql);

		while (rs.next()) {
			User user = new User();

			user.setUsername(rs.getString("username"));
			user.setGrade(rs.getString("grade"));

			userVector.add(user);
		}

		return userVector;
	}

	/**
	 * 根据名字进行删除
	 * 
	 * @param db
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public static boolean delete(DB db, String username) throws Exception {
		String strSql;
		strSql = "delete from user where username='" + username + "'";
		if (db.ExecSql(strSql) == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 对用户权限进行编辑(根据名字)
	 * 
	 * @param db
	 * @param username
	 * @param grade
	 * @param forumid
	 * @return
	 * @throws Exception
	 */
	public static boolean edit(DB db, String username, String grade,
			String forumid) throws Exception {
		String strSql;
		strSql = "update user set grade='" + grade + "' where username='"
				+ username + "'";
		if (db.ExecSql(strSql) == 0) {
			return false;
		} else {
			/**
			 * 如果是管理员权限的话，同步修改论坛表中管理员的名字 ！！！！！！感觉下面的判读代码有问题；me:
			 * if(grade.equals("admin")){修改（增添）论坛管理员姓名}
			 */
			if (!grade.equals("����")) {
				/**
				 * 设置为管理员
				 */
				strSql = "update forum set manager='" + username
						+ "' where id=" + forumid;
				db.ExecSql(strSql);
			}
			return true;
		}
	}
}