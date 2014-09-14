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

import bbs.admin.form.ForumManagerForm;
import bbsDAO.Constants;
import bbsDAO.DB;
import bbsDAO.Forum;

/**
 * 编辑论坛提交之后的业务流程控制(持久化到数据库)：
 * 
 * @author wnf
 * @time 2012-3-23下午05:05:40
 * 
 */
public final class ForumEditSubmitAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ForumManagerForm forumEditForm = (ForumManagerForm) form;
		String forumid = forumEditForm.getForumid();
		String forumname = (String) forumEditForm.getForumname();
		String manager = (String) forumEditForm.getManager();
		String forumcategory = (String) forumEditForm.getForumcategory();

		HttpSession session = request.getSession();
		Vector forumVector = new Vector();

		ServletContext context = servlet.getServletContext();
		DataSource dataSource = (DataSource) context
				.getAttribute(Constants.DATASOURCE_KEY);
		DB db = new DB(dataSource);

		String PageForward;
		ActionMessages errors = new ActionMessages();
		if (Forum.edit(db, forumid, forumname, manager, forumcategory)) {
			forumVector = Forum.search(db);
			session.setAttribute(Constants.FORUM_LIST_KEY, forumVector);
			PageForward = "ToForumManager";
		} else {
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"error.update.failed"));
			if (!errors.isEmpty()) {

				this.saveMessages(request, errors);
			}
			PageForward = "ToErrorPage";
		}

		db.close();
		return (mapping.findForward(PageForward));
	}
}