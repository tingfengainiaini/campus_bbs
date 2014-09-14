package bbs.admin.form;

import org.apache.struts.action.ActionForm;

/**
 * 接受表单并封装交给action类处理：
 * 
 * @author wnf
 * 
 */
public class AdminLoginForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username = null;

	private String password = null;

	/**
	 * 默认构造函数
	 */
	public AdminLoginForm() {
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

}