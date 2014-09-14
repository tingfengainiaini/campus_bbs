package bbs.admin.action;


import java.util.Vector;

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

import bbsDAO.Constants;
import bbsDAO.DB;
import bbsDAO.Forum;
import bbsDAO.User;

public final class UserManagerAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/**
		 * 定义一个会话：
		 */
		HttpSession session = request.getSession();
		/**
		 * 定义一个容器：
		 */
		Vector users = new Vector();

		ServletContext context = servlet.getServletContext();
		DataSource dataSource = (DataSource) context
				.getAttribute(Constants.DATASOURCE_KEY);
		DB db = new DB(dataSource);
		Vector allforumsVector = Forum.searchAllForums(db);
		session.setAttribute(Constants.ALLFORUMS_KEY, allforumsVector);
		/**
		 * 此属性在adminlogoinAction里面（登陆验证时）设置
		 */
		String usergrade = (String) session
				.getAttribute(Constants.LOGIN_USERGRADE_KEY);

		String pageForward;
		ActionMessages errors = new ActionMessages();
		/**
		 * 非管理员权限不能跳转：
		 */
		if ("admin".equals(usergrade)) {
			users = User.search(db, "");
			session.setAttribute(Constants.USER_LIST_KEY, users);
			pageForward = "ToUserManager";
		} else {
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"error.accessDeny"));
			if (!errors.isEmpty()) {
				this.saveMessages(request, errors);
			}
			pageForward = "ToErrorPage";
		}

		db.close();
		return (mapping.findForward(pageForward));
	}
}