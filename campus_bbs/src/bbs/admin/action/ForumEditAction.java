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

import bbs.admin.form.ForumManagerForm;
import bbsDAO.Constants;
import bbsDAO.DB;
import bbsDAO.Forum;
import bbsDAO.User;
import bbsDAO.ForumCategory;

/**
 * 编辑论坛流程控制类：
 * 
 * @author wnf
 * @time 2012-3-23下午04:51:57
 * 
 */
public final class ForumEditAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ForumManagerForm forumEditForm = (ForumManagerForm) form;
		String forumid = forumEditForm.getForumid();
		String forumname = forumEditForm.getForumname();
		String manager = forumEditForm.getManager();
		String category = forumEditForm.getForumcategory();

		// String forumname=new
		// String(forumname2.getBytes("ISO-8859-1"),"utf-8");
		System.out.print(forumname);

		HttpSession session = request.getSession();
		Vector managerUserVector = new Vector();
		Vector forumcategoryVectory = new Vector();

		ServletContext context = servlet.getServletContext();
		DataSource dataSource = (DataSource) context
				.getAttribute(Constants.DATASOURCE_KEY);
		DB db = new DB(dataSource);
		/**
		 * 把该论坛的所有的users封装在容器里：以使后面编辑论坛时供版主人选的修改：
		 */
		managerUserVector = User.searchUsers(db);
		forumcategoryVectory = ForumCategory.searchAllForumCategorys(db);
		session
				.setAttribute(Constants.MANAGER_CANDIDATE_KEY,
						managerUserVector);
		session.setAttribute(Constants.CATEGOTY_NEW_KEY, forumcategoryVectory);
		session.setAttribute(Constants.EDIT_FORUMID_KEY, forumid);
		session.setAttribute(Constants.EDIT_FORUMNAME_KEY, forumname);
		session.setAttribute(Constants.MANAGER_KEY, manager);
		session.setAttribute(Constants.CATEGORY_key, category);
		db.close();
		return (mapping.findForward("ToForumEdit"));
	}
}