package bbs.user.form;

import org.apache.struts.action.ActionForm;

public final class UserLoginForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username = null; // �û���

	private String password = null; // ����

	public UserLoginForm() {
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