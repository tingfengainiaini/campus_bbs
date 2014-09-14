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

import bbs.user.form.ForumForm;
import bbsDAO.Constants;
import bbsDAO.DB;
import bbsDAO.TopicDisp;

/**
 * 显示某个论坛的主题列表的流程控：
 * 
 * @author wnf
 * @time 2012-3-24下午03:09:32
 * 
 */
public final class TopicListAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ForumForm forumForm = (ForumForm) form;
		int forumid = forumForm.getForumid();
		String forumname2 = forumForm.getForumname();
		/**
		 * 转换编码格式，应对乱码问题：
		 */
		String forumname = new String(forumname2.getBytes("ISO-8859-1"),
				"utf-8");
		
		String forumcategory2= forumForm.getForumcategory();
		String forumcategory=new String(forumcategory2.getBytes("ISO-8859-1"),"utf-8");
		/**
		 * 测试值传递编码格式：
		 */
		System.out.println(forumname);

		int pageid = forumForm.getPageid();
		if (pageid < 0) {
			pageid = 0;
		}

		HttpSession session = request.getSession(true);
		Vector topicVector = new Vector();
		TopicDisp topicdisp = new TopicDisp();
		ActionMessages errors = new ActionMessages();
		String pageForward;

		ServletContext context = servlet.getServletContext();
		DataSource dataSource = (DataSource) context
				.getAttribute(Constants.DATASOURCE_KEY);
		DB db = new DB(dataSource);

		/**
		 * 如果指定论坛的主题数为0
		 */
		if (topicdisp.getTopicCount(db, forumid) == 0) {
			/**
			 * 封装论坛主题列表（为空容器），论坛号，论坛名字，页号; 然后跳转到话题列表界面：
			 */
			session.setAttribute(Constants.TOPIC_LIST_KEY, topicVector);
			session.setAttribute(Constants.CUR_FORUMID_KEY,
					new Integer(forumid));
			session.setAttribute(Constants.CUR_FORUMNAME_KEY, forumname);
			session.setAttribute(Constants.CATEGORY_key, forumcategory);
			session.setAttribute(Constants.CUR_PAGEID_KEY, new Integer(pageid));
			pageForward = "ToTopicList";
			/**
			 * 如果到了尾页(比如说每页5条，共10条，现在到了第2页，pageid 为1，
			 * 后翻的话变为2，则10<11，符合此条件。若共有11条，11<11为false)
			 */

		} else if (topicdisp.getTopicCount(db, forumid) < pageid
				* Constants.TOPIC_PAGE_SIZE + 1) {
			pageid--;

			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"error.topiclist.nomorepage"));
			if (!errors.isEmpty()) {

				this.saveMessages(request, errors);
			}
			pageForward = "ToErrorPage";
			/**
			 * 如果主题数不为0，则搜索出指定论坛中指定页号的主题。
			 */
		} else {
			topicdisp.setForumid(forumid);
			topicVector = topicdisp.search(db, pageid);
			session.setAttribute(Constants.TOPIC_LIST_KEY, topicVector);
			session.setAttribute(Constants.CUR_FORUMID_KEY,
					new Integer(forumid));
			session.setAttribute(Constants.CUR_FORUMNAME_KEY, forumname);
			session.setAttribute(Constants.CATEGORY_key, forumcategory);
			session.setAttribute(Constants.CUR_PAGEID_KEY, new Integer(pageid));
            
			pageForward = "ToTopicList";
		}

		db.close();
		return (mapping.findForward(pageForward));
	}
}
