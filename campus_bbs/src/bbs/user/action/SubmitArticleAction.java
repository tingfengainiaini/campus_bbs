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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import bbs.user.form.NewArticleForm;
import bbsDAO.Constants;
import bbsDAO.DB;
import bbsDAO.Response;
import bbsDAO.Topic;
import bbsDAO.TopicDisp;

/**
 * 话题创建之后的提交流程控：
 * 
 * @version 1.0;
 * @author wnf
 * @time 2012-3-24下午05:02:18
 * 
 */
public final class SubmitArticleAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		NewArticleForm newform = (NewArticleForm) form;
		String title = (String) newform.getTitle();
		String content = (String) newform.getContent();
		/**
		 * 检查乱码问题：结论是乱码出在表单传输过程中：
		 */
		System.out.print(title);
		System.out.print(content);
		
		ServletContext context = servlet.getServletContext();
		DataSource dataSource = (DataSource) context
				.getAttribute(Constants.DATASOURCE_KEY);
		DB db = new DB(dataSource);

		HttpSession session = request.getSession(true);
		ActionMessages errors = new ActionMessages();
		/**
		 * Object getAttribute(String name)Returns the object bound with the
		 * specified name in this session, or null if no object is bound under
		 * the name.
		 * 
		 * Parameters: name - a string specifying the name of the object
		 * Returns: the object with the specified name
		 */
		String talkType = (String) session
				.getAttribute(Constants.TALK_TYPE_KEY);
		String username = (String) session.getAttribute(Constants.USERNAME_KEY);
		Vector topicVector = new Vector();
		String pageForward;
		/**
		 * 在toplist.jsp中设置过这个属性(talktype)：
		 */
		if (talkType.equals("topic")) {
			int forumid = ((Integer) session
					.getAttribute(Constants.CUR_FORUMID_KEY)).intValue();
			Topic topic = new Topic();
			topic.setTitle(title);
			topic.setContent(content);
			topic.setForumid(forumid);
			topic.setAuthor(username);

			if (topic.insert(db,username)) {
				/**
				 * 话题提交成功之后，更新 然后 搜索（论坛号+pageid=0）
				 */
				TopicDisp topicdisp = new TopicDisp();
				topicdisp.setForumid(forumid);
				topicVector = topicdisp.search(db, 0);
				/**
				 * 更新topiclist：
				 */
				session.setAttribute(Constants.TOPIC_LIST_KEY, topicVector);
				/**
				 * 新话题发布之后 back to :toplist.jsp(is already updated!)
				 */
				pageForward = "ToTopicList";
			} else {
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"error.article.insertfailed"));
				if (!errors.isEmpty()) {
					// saveErrors(request, errors);
					this.saveMessages(request, errors);
					;
				}
				pageForward = "ToErrorPage";
			}
		} else {
			/**
			 * 否则 回复
			 */
			int topicid = ((Integer) session
					.getAttribute(Constants.CUR_TOPICID_KEY)).intValue();
			Response respon = new Response();
			respon.setTitle(title);
			respon.setContent(content);
			respon.setTopicid(topicid);
			respon.setAuthor(username);

			if (respon.insert(db)) {
				respon.setTopicid(topicid);
				topicVector = Response.search(db, topicid);
				session.setAttribute(Constants.RESPONSE_LIST_KEY, topicVector);
				/**
				 * 跳转到回复列表
				 */
				pageForward = "ToResponseList";
			} else {
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"error.article.insertfailed"));
				if (!errors.isEmpty()) {
					// saveErrors(request, errors);
					this.saveMessages(request, errors);
				}
				pageForward = "ToErrorPage";
			}
		}

		db.close();
		return (mapping.findForward(pageForward));
	}

}
