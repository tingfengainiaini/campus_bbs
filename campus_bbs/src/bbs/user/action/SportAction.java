/**
 * 2012-5-17
 * wnf
 * 上午10:34:41
 */
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


import bbsDAO.Constants;
import bbsDAO.DB;

public final class SportAction extends Action{
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		Vector sport = new Vector();
		
		//Vector studyandchange = new Vector();

		ServletContext context = servlet.getServletContext();
		DataSource dataSource = (DataSource) context
				.getAttribute(Constants.DATASOURCE_KEY);
		DB db = new DB(dataSource);

		sport = (Vector) session.getAttribute(Constants.FORUM_LIST_KEY2);

		String pageForward;
		ActionMessages errors = new ActionMessages();

		if (sport != null) {
			/**
			 * 重新封装一下；
			 */
			session.setAttribute(Constants.FORUM_LIST_KEY2, sport);
			pageForward = "ToSport";
		} else {
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"error.accessDeny"));
			if (!errors.isEmpty()) {
				this.saveMessages(request, errors);
			}
			pageForward = "ToErrorPage";
		}
		db.close();
		return (mapping.findForward(pageForward));
	}
}