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
 * 新增论坛流程控制类：
 * 
 * @author wnf
 * @time 2012-3-23下午04:07:23
 * 
 */

public final class ForumCreateAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/**
		 * protected boolean isTokenValid(javax.servlet.http.HttpServletRequest
		 * request)Return true if there is a transaction token stored in the
		 * user's current session, and the value submitted as a request
		 * parameter with this action matches it. Returns false under any of the
		 * following circumstances:
		 * 
		 * *No session associated with this request *No transaction token saved
		 * in the session *No transaction token included as a request parameter
		 * The included transaction token value does not match the transaction
		 * token in the user's session Parameters: request - The servlet request
		 * we are processing
		 */

		/**
		 * 如果没有交互令牌存在的话，返回一个封装有上下文相关的输入表单路径的消息
		 */

		if (!isTokenValid(request)) {
			saveToken(request);
			/**
			 * public java.lang.String getInput()Get the context-relative path
			 * of the input form to which control should be returned if a
			 * validation error is encountered. Returns: context-relative path
			 * of the input form to which control should be returned if a
			 * validation error is encountered. ***ActionMapping() 继承
			 * ActionConfig()： 用子类对象调用父类的public方法
			 */
			return (new ActionForward(mapping.getInput()));
		}
		/**
		 * Reset the saved transaction token in the user's session. This
		 * indicates that transactional token checking will not be needed on the
		 * next request that is submitted
		 */
		resetToken(request);
		/**
		 * 取表单信息:
		 */
		ForumManagerForm forumManagerForm = (ForumManagerForm) form;
		String forumname = (String) forumManagerForm.getForumname();
		String forumcategory=(String) forumManagerForm.getForumcategory();
        /**
         * 乱码否？
         */
		System.out.print(forumname);
		
		HttpSession session = request.getSession();
		Vector sorts = new Vector();

		ServletContext context = servlet.getServletContext();
		DataSource dataSource = (DataSource) context
				.getAttribute(Constants.DATASOURCE_KEY);
		DB db = new DB(dataSource);

		String pageForward;
		ActionMessages errors = new ActionMessages();
		/**
		 * 新增论坛之持久化数据库：
		 */
		if (Forum.insert(db, forumname, forumcategory)) {
			sorts = Forum.search(db);
			session.setAttribute(Constants.FORUM_LIST_KEY, sorts);
			pageForward = "ToForumManager";
		} else {
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"error.insert.failed"));
			if (!errors.isEmpty()) {
				// saveErrors(request, errors);
				this.saveMessages(request, errors);
			}
			pageForward = "ToErrorPage";
		}
		/**
		 * 别忘了数据库的关闭：
		 */
		db.close();
		return (mapping.findForward(pageForward));
	}
}