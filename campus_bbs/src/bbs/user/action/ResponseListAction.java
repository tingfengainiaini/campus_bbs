package bbs.user.action;


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

import bbs.user.form.TopicOfResponseForm;
import bbsDAO.Constants;
import bbsDAO.DB;
import bbsDAO.Response;
/**
 * 回复流程控：
 * @author wnf
 * @time 2012-3-25上午11:35:06
 *
 */
public final class ResponseListAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TopicOfResponseForm topicForm = (TopicOfResponseForm) form;

		int topicid = topicForm.getTopicid();

		HttpSession session = request.getSession();
		Vector v = new Vector();
		
		ServletContext context = servlet.getServletContext();
		DataSource dataSource = (DataSource) context
				.getAttribute(Constants.DATASOURCE_KEY);
		DB db = new DB(dataSource);
		
		v = Response.search(db, topicid);
		session.setAttribute(Constants.RESPONSE_LIST_KEY, v);
		session.setAttribute(Constants.CUR_TOPICID_KEY, new Integer(topicid));

		db.close();
		return (mapping.findForward("ToResponseList"));
	}
}
