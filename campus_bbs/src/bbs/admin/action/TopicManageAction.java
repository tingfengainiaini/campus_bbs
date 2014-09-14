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

import bbs.admin.form.TopicManagerForm;
import bbsDAO.Constants;
import bbsDAO.DB;
import bbsDAO.Topic;

/**
 * 主题加精流程控：
 * 
 * @version 1.0
 * @author wnf
 * @time 2012-3-23下午07:20:10
 * 
 */
public final class TopicManageAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TopicManagerForm topicManagerForm = (TopicManagerForm) form;
		String topicid = topicManagerForm.getTopicid();

		System.out.print(topicid); // 测试空指针异常；

		HttpSession session = request.getSession();
		Vector topics = new Vector();

		ServletContext context = servlet.getServletContext();
		DataSource dataSource = (DataSource) context
				.getAttribute(Constants.DATASOURCE_KEY);

		DB db = new DB(dataSource);

		String pageForward;
		ActionMessages errors = new ActionMessages();
		if (Topic.manage(db, topicid)) {
			String searchtopic = (String) session
					.getAttribute(Constants.SEARCH_TOPIC_KEY);
			topics = Topic.search(db, searchtopic);
			session.setAttribute(Constants.TOPIC_LIST_KEY, topics);
			pageForward = "ToTopicManager";
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