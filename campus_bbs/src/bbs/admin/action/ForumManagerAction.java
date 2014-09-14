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

public final class ForumManagerAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		Vector forumVector = new Vector();

		ServletContext context = servlet.getServletContext();
		DataSource dataSource = (DataSource) context
				.getAttribute(Constants.DATASOURCE_KEY);
		DB db = new DB(dataSource);

		String usergrade = (String) session
				.getAttribute(Constants.LOGIN_USERGRADE_KEY);
		String pageForward;
		ActionMessages errors = new ActionMessages();
		if ("admin".equals(usergrade)) {
			forumVector = Forum.search(db);
			session.setAttribute(Constants.FORUM_LIST_KEY, forumVector);
			pageForward = "ToForumManager";
		} else {
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"error.accessDeny"));
			if (!errors.isEmpty()) {
//				saveErrors(request, errors);
				this.saveMessages(request, errors);
			}
			pageForward = "ToErrorPage";
		}

		db.close();
		return (mapping.findForward(pageForward));
	}
}