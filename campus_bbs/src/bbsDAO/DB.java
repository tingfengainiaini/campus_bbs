package bbsDAO;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库交互类
 * 
 * @author wnf
 * @time 2012-3-19
 */
public class DB {

	Connection connect = null;
	/**
	 * 定义封装装查询结果的对象（）
	 */
	ResultSet rs = null;

	public DB(DataSource dataSource) {

		try {
			/**
			 *建立连接 Connection getConnection() throws SQLException尝试建立与此
			 * DataSource 对象所表示的数据源"bbsDB"的连接。
			 */
			connect = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet OpenSql(String sql) {
		try {
			/**
			 * createStatement(int resultSetType, int resultSetConcurrency) 创建一个
			 * Statement 对象，该对象将生成具有给定类型和并发性的 ResultSet对象
			 */
			Statement stmt = connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return rs;
	}

	public int ExecSql(String sql) {
		int result = 0;
		try {
			Statement stmt = connect.createStatement();
			/**
			 * int executeUpdate(String sql) throws SQLException执行给定 SQL
			 * 语句，该语句可能为 INSERT、UPDATE 或 DELETE 语句，或者不返回任何内容的 SQL 语句（如 SQL DDL
			 * 语句）
			 * 参数： sql - SQL 数据操作语言（Data Manipulation Language，DML）语句，如
			 * INSERT、UPDATE 或 DELETE；或者不返回任何内容的 SQL 语句，如 DDL 语句。
			 * 
			 * 返回： (1) 对于 SQL 数据操作语言 (DML) 语句，返回行计数 (2) 对于什么都不返回的 SQL 语句，返回 0
			 */
			result = stmt.executeUpdate(sql);
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());

		}
		return result;
	}

	public void close() {
		if (connect != null) {
			try {
				connect.close();
				connect = null;
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
		}
	}
}
