package bbs.admin.action;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import bbs.admin.form.AdminLoginForm;
import bbsDAO.Constants;
import bbsDAO.DB;
import bbsDAO.User;

/**
 * 接受表单传过来的数据并实现流程控制：
 * 
 * @author wnf
 * @time 2012-3-21
 */
public final class AdminLoginAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		AdminLoginForm userform = (AdminLoginForm) form;
		/**
		 * 先从数据库取出姓名作为后面的验证参考系
		 */
		String username = userform.getUsername();

		ServletContext context = servlet.getServletContext();
		/**
		 * Object getAttribute(String name) Returns: an Object containing the
		 * value of the attribute, or null if no attribute exists matching the
		 * given name
		 */
		DataSource dataSource = (DataSource) context
				.getAttribute(Constants.DATASOURCE_KEY);
		DB db = new DB(dataSource);
		/**
		 * HttpSession getSession(boolean create) Returns the current
		 * HttpSession associated with this request or, if there is no current
		 * session and create is true, returns a new session. If create is false
		 * and the request has no valid HttpSession, this method returns null.
		 * 从request范围到session范围
		 */
		HttpSession session = request.getSession(true);

		String PageForward;
		/**
		 * 消息类对象:
		 */
		ActionMessages errors = new ActionMessages();
		/**
		 * 身份验证（管理员、版主、或者user）
		 */
		if ("admin".equals(User.getUserGrade(db, username))) {
			/**
			 * session封装属性
			 */
			session.setAttribute(Constants.LOGIN_USERGRADE_KEY, "admin");
			session.setAttribute(Constants.USERNAME_KEY, username);
			/**
			 * 建立跳转对象：
			 */
			PageForward = "ToAdminIndex";
		} else if ("banzhu".equals(User.getUserGrade(db, username))) {
			session.setAttribute(Constants.LOGIN_USERGRADE_KEY, "banzhu");
			PageForward = "ToAdminIndex";
		} else {
			/**
			 * 封装错误信息：
			 */
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"error.accessDeny"));
			if (!errors.isEmpty()) {
				this.saveMessages(request, errors);
			}
			PageForward = "ToErrorPage";
		}

		db.close();
		/**
		 * 实现跳转：
		 */
		return mapping.findForward(PageForward);
	}

}
