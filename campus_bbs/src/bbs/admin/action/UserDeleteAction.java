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

import bbs.admin.form.UserManagerForm;
import bbsDAO.Constants;
import bbsDAO.DB;
import bbsDAO.User;

/**
 * 删除用户流程控制类：
 * 
 * @author wnf
 * @time 2012-3-22下午09:49:15
 * 
 */
public final class UserDeleteAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		UserManagerForm userManagerForm = (UserManagerForm) form;
		String username2 = (String) userManagerForm.getUsername();
		String username = new String(username2.getBytes("ISO-8859-1"),"utf-8");
 		/**
		 * 删除用户测试：
		 */
		System.out.println(username);

		HttpSession session = request.getSession();
		Vector users = new Vector();

		ServletContext context = servlet.getServletContext();
		DataSource dataSource = (DataSource) context
				.getAttribute(Constants.DATASOURCE_KEY);
		DB db = new DB(dataSource);

		String pageForward;
		/**
		 * 定义错误信息对象：
		 */
		ActionMessages errors = new ActionMessages();
		if (User.delete(db, username)) {
			/**
			 * 删除成功之后接着更新：
			 */
			users = User.search(db, "");
			/**
			 * 封装更新之后的对象信息：
			 */
			session.setAttribute(Constants.USER_LIST_KEY, users);
			pageForward = "ToUserManager";
		} else {
			/**
			 * 封装删除失败信息：
			 */
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"error.delete.failed"));
			if (!errors.isEmpty()) {
				this.saveMessages(request, errors);
			}
			pageForward = "ToErrorPage";
		}

		db.close();
		return (mapping.findForward(pageForward));
	}
}