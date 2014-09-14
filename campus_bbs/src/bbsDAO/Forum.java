package bbsDAO;

import java.sql.ResultSet;
import java.util.Vector;

/**
 * 论坛业务处理类：
 * 
 * @version 1.0
 * @author wnf
 * @time 2012-3-22下午10:05:20
 * 
 */
public class Forum {

	private int id;

	private String forumname;

	private String manager;

	private String forumcategory;

	private int topicNum;

	private int lastTopicId;

	private String lastTopicTitle = "";

	private String lastTopicAuthor = "";

	private String lastTopicTime = "";

	public String getForumname() {
		return forumname;
	}

	public void setForumname(String forumname) {
		this.forumname = forumname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getForumcategory() {
		return forumcategory;
	}

	public void setForumcategory(String forumcategory) {
		this.forumcategory = forumcategory;
	}

	public String getLastTopicAuthor() {
		return lastTopicAuthor;
	}

	public void setLastTopicAuthor(String lastTopicAuthor) {
		this.lastTopicAuthor = lastTopicAuthor;
	}

	public int getLastTopicId() {
		return lastTopicId;
	}

	public void setLastTopicId(int lastTopicId) {
		this.lastTopicId = lastTopicId;
	}

	public String getLastTopicTime() {
		return lastTopicTime;
	}

	public void setLastTopicTime(String lastTopicTime) {
		this.lastTopicTime = lastTopicTime;
	}

	public String getLastTopicTitle() {
		return lastTopicTitle;
	}

	public void setLastTopicTitle(String lastTopicTitle) {
		this.lastTopicTitle = lastTopicTitle;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public int getTopicNum() {
		return topicNum;
	}

	public void setTopicNum(int topicNum) {
		this.topicNum = topicNum;
	}

	/**
	 * 构造方法：
	 * 
	 * @param id
	 *            （论坛标识号）
	 * @param forumname
	 *            （论坛名字 ）
	 * @param manager
	 *            （管理员）
	 * @param topicNum
	 *            (主题编号)
	 * @param lastTopicId
	 *            (上次话题编号)
	 * @param lastTopicTitle
	 *            （上次话题标题）
	 * @param lastTopicAuthor
	 *            （上次话题作者）
	 * @param lastTopicTime
	 *            （上次话题时间 ）
	 */
	public Forum(int id, String forumname, String manager,
			String forumcategory, int topicNum, int lastTopicId,
			String lastTopicTitle, String lastTopicAuthor, String lastTopicTime) {
		this.id = id;
		this.forumname = forumname;
		this.manager = manager;
		this.forumcategory = forumcategory;
		
		this.topicNum = topicNum;
		this.lastTopicId = lastTopicId;
		this.lastTopicTitle = lastTopicTitle;
		this.lastTopicAuthor = lastTopicAuthor;
		this.lastTopicTime = lastTopicTime;

	}

	/**
	 * 构造方法2：
	 * 
	 * @param id
	 * @param forumname
	 * @param manager
	 */

	public Forum(int id, String forumname, String manager, String forumcategory) {
		this.id = id;
		this.forumname = forumname;
		this.manager = manager;
		this.forumcategory = forumcategory;
	}

	/**
	 * public class Vector<E>extends AbstractList<E>implements List<E>,
	 * RandomAccess, Cloneable, SerializableVector
	 * 类可以实现可增长的对象数组。与数组一样，它包含可以使用整数索引进行访问的组件。但是，Vector 的大小可以根据需要增大或缩小，以适应创建
	 * Vector 后进行添加或移除项的操作。
	 * 
	 * 每个向量会试图通过维护 capacity 和 capacityIncrement 来优化存储管理。capacity
	 * 始终至少应与向量的大小相等；这个值通常比后者大些，因为随着将组件添加到向量中，其存储将按 capacityIncrement
	 * 的大小增加存储块。应用程序可以在插入大量组件前增加向量的容量；这样就减少了增加的重分配的量。
	 * 
	 * 由 Vector 的 iterator 和 listIterator
	 * 方法所返回的迭代器是快速失败的：如果在迭代器创建后的任意时间从结构上修改了向量（通过迭代器自身的 remove 或 add
	 * 方法之外的任何其他方式），则迭代器将抛出
	 * ConcurrentModificationException。因此，面对并发的修改，迭代器很快就完全失败
	 * ，而不是冒着在将来不确定的时间任意发生不确定行为的风险。Vector 的 elements 方法返回的 Enumeration 不是 快速失败的。
	 * 
	 * 注意，迭代器的快速失败行为不能得到保证，一般来说，存在不同步的并发修改时，不可能作出任何坚决的保证。快速失败迭代器尽最大努力抛出
	 * ConcurrentModificationException
	 * 。因此，编写依赖于此异常的程序的方式是错误的，正确做法是：迭代器的快速失败行为应该仅用于检测 bug。
	 * 
	 * 从 Java 2 平台 v1.2 开始，此类改进为可以实现 List 接口，使它成为 Java Collections Framework
	 * 的成员。与新 collection 实现不同，Vector 是同步的。
	 * 
	 * 
	 * @param db
	 * @return
	 * @throws Exception
	 */

	public static @SuppressWarnings("unchecked")
	Vector search(DB db) throws Exception {
		int topicNum = 0;
		int lastTopicId;
		String lastTopicTitle;
		String lastTopicAuthor;
		String lastTopicTime;
		/**
		 * 定义一个用来装查询结果的容器对象(从 Java 2 平台 v1.2 开始，此类改进为可以实现 List 接口,特点是同步的)；
		 */
		Vector forumVector = new Vector();
		ResultSet rs, rsTopic;
		String strSql;
		int forumid;

		strSql = "select * from forum";
		/**
		 *取得连接对象来吧sql语句放入数据库；
		 */
		rs = db.OpenSql(strSql);
		/**
		 * 只要还存在下一个对象，就继续循环
		 */
		while (rs.next()) {
			lastTopicId = 0;
			lastTopicTitle = null;
			lastTopicAuthor = null;
			lastTopicTime = null;
			/**
			 * 从结果中依次取数据,封装到各个对应的变量中去：
			 */
			forumid = rs.getInt("id");
			strSql = "select count(*) from topic where forumid=" + forumid;
			/**
			 * 再次执行数据库操作,虽然效率变低了；
			 */

			rsTopic = db.OpenSql(strSql);
			if (rsTopic.next()) {
				topicNum = rsTopic.getInt(1);
			}
			/**
			 * 按论坛标识号码降序排列
			 */
			strSql = "select * from topic where forumid=" + forumid
					+ " order by forumid desc";
			rsTopic = db.OpenSql(strSql);
			if (rsTopic.next()) {
				lastTopicId = rsTopic.getInt("id");
				lastTopicTitle = rsTopic.getString("title");
				lastTopicTime = rsTopic.getString("submittime");
				lastTopicAuthor = rsTopic.getString("author");
			}
			/**
			 * 把得到的数据封装到容器里，返回
			 */
			forumVector.add(new Forum(forumid, rs.getString("forumname"), rs
					.getString("manager"), rs.getString("forumcategory"),
					topicNum, lastTopicId, lastTopicTitle, lastTopicTime,
					lastTopicAuthor));

		}
		return forumVector;
	}

	/**
	 * 查询所有的论坛
	 * 
	 * @param db
	 * @return
	 * @throws Exception
	 */
	public static @SuppressWarnings("unchecked")
	Vector searchAllForums(DB db) throws Exception {
		int forumid = 0;
		String forumname = null;
		String manager = null;
		String forumcategory = null;
		Vector forumVector = new Vector();
		ResultSet rs;
		String strSql;

		strSql = "select * from forum";
		rs = db.OpenSql(strSql);

		while (rs.next()) {
			forumid = rs.getInt("id");
			forumname = rs.getString("forumname");
			manager = rs.getString("manager");
			forumcategory = rs.getString("forumcategory");

			forumVector.add(new Forum(forumid, forumname, manager,
					forumcategory));

		}
		return forumVector;
	}

	/**
	 * 根据分类查询论坛：
	 * 
	 * @param db
	 * @param category
	 * @return
	 */
	public static @SuppressWarnings("unchecked")
	Vector searchbycategory(DB db, String category) throws Exception {
		int topicNum = 0;
		int lastTopicId;
		String lastTopicTitle;
		String lastTopicAuthor;
		String lastTopicTime;
		/**
		 * 定义一个用来装查询结果的容器对象(从 Java 2 平台 v1.2 开始，此类改进为可以实现 List 接口,特点是同步的)；
		 */
		Vector forumVector = new Vector();
		ResultSet rs, rsTopic;
		String strSql;
		int forumid;

		strSql = "select * from forum where forumcategory ='" + category + "'";
		/**
		 *取得连接对象来吧sql语句放入数据库；
		 */
		rs = db.OpenSql(strSql);
		/**
		 * 只要还存在下一个对象，就继续循环
		 */
		while (rs.next()) {
			lastTopicId = 0;
			lastTopicTitle = null;
			lastTopicAuthor = null;
			lastTopicTime = null;
			/**
			 * 从结果中依次取数据,封装到各个对应的变量中去：
			 */
			forumid = rs.getInt("id");
			strSql = "select count(*) from topic where forumid=" + forumid;
			/**
			 * 再次执行数据库操作,虽然效率变低了；
			 */

			rsTopic = db.OpenSql(strSql);
			if (rsTopic.next()) {
				topicNum = rsTopic.getInt(1);
			}
			/**
			 * 按论坛标识号码降序排列
			 */
			strSql = "select * from topic where forumid=" + forumid
					+ " order by forumid desc";
			rsTopic = db.OpenSql(strSql);
			if (rsTopic.next()) {
				lastTopicId = rsTopic.getInt("id");
				lastTopicTitle = rsTopic.getString("title");
				lastTopicTime = rsTopic.getString("submittime");
				lastTopicAuthor = rsTopic.getString("author");
			}
			/**
			 * 把得到的数据封装到容器里，返回
			 */
			forumVector.add(new Forum(forumid, rs.getString("forumname"), rs
					.getString("manager"), rs.getString("forumcategory"),
					topicNum, lastTopicId, lastTopicTitle, lastTopicTime,
					lastTopicAuthor));

		}
		return forumVector;
	}

	/**
	 * 插入论坛唯一值得注意的一点就是：论坛号的增加(id没有自增功能)
	 * 
	 * @param db
	 * @param forumname
	 * @return
	 * @throws Exception
	 */
	public static boolean insert(DB db, String forumname, String forumcategory)
			throws Exception {
		String strSql;
		String strSql2;
		ResultSet rs;
		int iMaxId;
		int forumnumbers;
		strSql = "select max(id) from forum";
		strSql2 = "select forumnumbers from forumcategory where categoryname = '"
				+ forumcategory + "'";
		rs = db.OpenSql(strSql);
		if (rs.next()) {
			iMaxId = rs.getInt(1) + 1;
		} else {
			iMaxId = 1;
		}
		rs = db.OpenSql(strSql2);
		if (rs.next()) {
			forumnumbers = rs.getInt(1);
		} else {
			forumnumbers = 0;
		}

		System.out.println(" 当前论坛数目：" + forumnumbers);

		strSql = "insert into forum values('" + iMaxId + "','" + forumname
				+ "','','" + forumcategory + "'" + ")";

		strSql2 = "update forumcategory set forumnumbers = " + "'"
				+ forumnumbers + "'" + "+" + '1' + " where categoryname ='"
				+ forumcategory + "'";
		if (db.ExecSql(strSql) == 0 || db.ExecSql(strSql2) == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 删除论坛业务逻辑
	 * 
	 * @param db
	 * @param forumid
	 * @return
	 * @throws Exception
	 */
	public static boolean delete(DB db, String forumid) throws Exception {
		String strSql;
		String strSql1;
		String strSql2;
		String strSql3;
		String strSql4;
		String strSql5;
		String forumcategory2;// 原来分类的名字；

		String forumcategory;

		int forumnumbers1;
		ResultSet rs;
		strSql = "delete from forum where id=" + forumid;
		strSql1 = "delete from topic where forumid=" + forumid;
		strSql2 = "select forumcategory from forum where id=" + forumid;
		rs = db.OpenSql(strSql2);
		if (rs.next()) {
			System.out.print("OK1");
			forumcategory2 = rs.getString("forumcategory");
			System.out.print(forumcategory2);
		} else {
			forumcategory2 = null;
		}
		forumcategory = new String(forumcategory2.getBytes("gb2312"), "utf-8");
		/**
		 * 编码格式检测：
		 */
		System.out.print(forumcategory);

		strSql3 = "select forumnumbers from forumcategory where categoryname = '"
				+ forumcategory2 + "'";
		rs = db.OpenSql(strSql3);
		if (rs.next()) {
			System.out.print(forumcategory2);
			System.out.print("OK2");
			forumnumbers1 = rs.getInt(1);
		} else {
			forumnumbers1 = 0;
		}
		strSql4 = "update forumcategory set forumnumbers = '" + forumnumbers1
				+ "'" + "-" + '1' + " where categoryname = '" + forumcategory2
				+ "'";

		strSql5 = "select * from topic where forumid=" + forumid;
		if (db.OpenSql(strSql5).next()) {
			if (db.ExecSql(strSql) == 0 || db.ExecSql(strSql4) == 0
					|| db.ExecSql(strSql1) == 0) {
				System.out.print(forumcategory2);
				System.out.print("OK3");
				return false;
			} else {
				return true;
			}
		} else {
			if (db.ExecSql(strSql) == 0 || db.ExecSql(strSql4) == 0) {
				System.out.print(forumcategory2);
				System.out.print("OK3");
				return false;
			} else {
				return true;
			}
		}
	}

	/**
	 * 编辑论坛业务逻辑
	 * 
	 * @param db
	 * @param forumid
	 * @param forumname
	 * @param manager
	 * @return
	 * @throws Exception
	 */
	public static boolean edit(DB db, String forumid, String forumname,
			String manager, String categoryname) throws Exception {
		String strSql1;// 修改论坛名字和管理员
		String strSql2;// 修改论坛分类
		String strSql3;// 取得原来分类的名字
		String strSql4;// 取得原来分类的论坛数量
		String strSql5;// 更新原来分的论坛数量使之减1；
		String strSql6;// 取得当前（修改之后）的分类论坛数量；
		String strSql7;// 修改当前分类论坛数量，使之加1；
		String forumcategory;// 原来分类的名字；
		int forumnumbers1;// 原来分类的论坛数量；
		int forumnumbers2;// 修改之后的论坛数量；
		ResultSet rs;
		/**
		 * 真心感到有点疑问这里:manager 是系统管理员还是版主(按理说应该是版主才对)？ 经初步核实论坛的manager应该是版主
		 */
		strSql1 = "update forum set forumname='" + forumname + "',manager='"
				+ manager + "'where id=" + forumid;
		strSql3 = "select forumcategory from forum where id=" + forumid;
		rs = db.OpenSql(strSql3);
		if (rs.next()) {
			forumcategory = rs.getString(1);
		} else {
			forumcategory = null;
		}
		strSql4 = "select forumnumbers from forumcategory where categoryname = '"
				+ forumcategory + "'";
		rs = db.OpenSql(strSql4);
		if (rs.next()) {
			forumnumbers1 = rs.getInt(1);
		} else {
			forumnumbers1 = 0;
		}
		strSql5 = "update forumcategory set forumnumbers = '" + forumnumbers1
				+ "'" + "-" + '1';

		strSql2 = "update forum set forumcategory='" + categoryname + "'"
				+ "where id=" + forumid;
		strSql6 = "select forumnumbers from forumcategory where categoryname = '"
				+ categoryname + "'";
		rs = db.OpenSql(strSql6);
		if (rs.next()) {
			forumnumbers2 = rs.getInt(1);
		} else {
			forumnumbers2 = 0;
		}
		strSql7 = "update forumcategory set forumnumbers = " + "'"
				+ forumnumbers2 + "'" + "+" + '1';
		if (db.ExecSql(strSql1) == 0 || db.ExecSql(strSql2) == 0
				|| db.ExecSql(strSql5) == 0 || db.ExecSql(strSql7) == 0) {
			return false;
		} else {
			return true;
		}
	}

}