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

import bbs.admin.form.TopicManagerForm;
import bbsDAO.Constants;
import bbsDAO.DB;
import bbsDAO.Topic;

/**
 * 话题模糊查询流程控制类：
 * 
 * @version 1.0
 * @author wnf
 * @time 2012-3-23下午07:11:34
 * 
 */
public final class UserTopicSearchAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TopicManagerForm topicManagerForm = (TopicManagerForm) form;
		String title = topicManagerForm.getTitle();

		/**
		 * 测试标题是否传递到位：
		 */
		System.out.println(title);

		HttpSession session = request.getSession();
		Vector topicVector = new Vector();

		ServletContext context = servlet.getServletContext();
		DataSource dataSource = (DataSource) context
				.getAttribute(Constants.DATASOURCE_KEY);

		DB db = new DB(dataSource);

		topicVector = Topic.search(db, title);
		/**
		 * 封装对象
		 */
		session.setAttribute(Constants.TOPIC_LIST_KEY, topicVector);
		session.setAttribute(Constants.SEARCH_TOPIC_KEY, title);

		db.close();
		/**
		 * 查询之后带着结果返回到主题管理界面：
		 */
		return (mapping.findForward("ToUserTopic"));
	}
}