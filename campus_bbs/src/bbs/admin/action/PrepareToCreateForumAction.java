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

import bbsDAO.Constants;
import bbsDAO.ForumCategory;
import bbsDAO.DB;

/**
 * 新增论坛流程控制中转类:
 * 
 * @author wnf
 * @time 2012-3-23下午03:48:00
 * 
 */
public final class PrepareToCreateForumAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/**
		 * protected void saveToken(javax.servlet.http.HttpServletRequest request)
		 * Save a new transaction token in the user's current session,
		 * creating a new session if necessary.
		 * Parameters: request - The servlet request we are processing
		 */
		HttpSession session = request.getSession();
		ServletContext context = servlet.getServletContext();
		DataSource dataSource = (DataSource) context
				.getAttribute(Constants.DATASOURCE_KEY);
		DB db = new DB(dataSource);
		Vector forumcategoryVectory = new Vector();
		forumcategoryVectory = ForumCategory.searchAllForumCategorys(db);
		session.setAttribute(Constants.CATEGOTY_NEW_KEY, forumcategoryVectory);
		saveToken(request);
		return (mapping.findForward("ToForumCreate"));
	}

}
