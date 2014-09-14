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
 * 用户编辑类（）
 * 
 * @author wnf
 * @time 2012-3-22下午03:47:14
 * 
 */
public final class UserEditAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		UserManagerForm userManagerForm = (UserManagerForm) form;
		String username = (String) userManagerForm.getUsername();
		String gradeId = (String) userManagerForm.getGrade();
		String forumid = (String) userManagerForm.getForum();
		/**
		 * 测试中文传递：
		 */
		System.out.println(username);

		String grade = null;
		if (gradeId.equals("0")) {
			grade = "admin";
		} else if (gradeId.equals("1")) {
			grade = "banzhu";
		} else if (gradeId.equals("2")) {
			grade = "user";
		}

		HttpSession session = request.getSession();
		Vector users = new Vector();

		ServletContext context = servlet.getServletContext();
		DataSource dataSource = (DataSource) context
				.getAttribute(Constants.DATASOURCE_KEY);

		DB db = new DB(dataSource);

		String pageForward;
		ActionMessages errors = new ActionMessages();
		if (User.edit(db, username, grade, forumid)) {
			/**
			 * 编辑成功之后，就更新并显示
			 */
			users = User.search(db, "");
			/**
			 * 封装起来scope="session"：
			 */
			session.setAttribute(Constants.USER_LIST_KEY, users);
			/**
			 * 跳转
			 */
			pageForward = "ToUserManager";
		} else {
			/**
			 * 封装更新失败信息：
			 */
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"error.update.failed"));
			if (!errors.isEmpty()) {
				this.saveMessages(request, errors);
			}
			pageForward = "ToErrorPage";
		}

		db.close();
		return (mapping.findForward(pageForward));
	}
}