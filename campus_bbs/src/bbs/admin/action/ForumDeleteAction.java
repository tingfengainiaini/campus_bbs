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

import bbs.admin.form.ForumidForm;
import bbsDAO.Constants;
import bbsDAO.DB;
import bbsDAO.Forum;

/**
 * 论坛删除业务流程控制类：
 * 
 * @version 1.0
 * @author wnf
 * @time 2012-3-23下午05:50:00
 * 
 */
public final class ForumDeleteAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ForumidForm forumidForm = (ForumidForm) form;
		String forumid = forumidForm.getForumid();

		HttpSession session = request.getSession();
		Vector sorts = new Vector();

		ServletContext context = servlet.getServletContext();
		DataSource dataSource = (DataSource) context
				.getAttribute(Constants.DATASOURCE_KEY);
		DB db = new DB(dataSource);

		String pageForward;
		ActionMessages errors = new ActionMessages();
		if (Forum.delete(db, forumid)) {
			/**
			 * 删除之后更新数据库并取出封装在容器里
			 */
			sorts = Forum.search(db);
			/**
			 * 固化在session范围：然后传回就OK
			 */
			session.setAttribute(Constants.FORUM_LIST_KEY, sorts);
			pageForward = "ToForumManager";
		} else {
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"error.delete.failed"));
			if (!errors.isEmpty()) {
				// saveErrors(request, errors);
				this.saveMessages(request, errors);
			}
			pageForward = "ToErrorPage";
		}

		db.close();
		return (mapping.findForward(pageForward));

	}
}