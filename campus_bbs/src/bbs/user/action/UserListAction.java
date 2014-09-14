/**
 * 2012-5-18
 * wnf
 * 下午04:33:35
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

import bbs.admin.form.UserManagerForm;
import bbsDAO.Constants;
import bbsDAO.DB;
import bbsDAO.User;

public final class UserListAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserManagerForm userform = (UserManagerForm) form;
		String username2 = userform.getUsername();
		String username = new String(username2.getBytes("ISO-8859-1"),"utf-8");
		/**
		 * 检测是出现了乱码；
		 */
		System.out.print(username);

		HttpSession session = request.getSession();
		Vector uservector = new Vector();

		ServletContext context = servlet.getServletContext();
		DataSource dataSource = (DataSource) context
				.getAttribute(Constants.DATASOURCE_KEY);
		DB db = new DB(dataSource);

		uservector = User.searchallInformation(db, username);
		session.setAttribute(Constants.USERINFORMATION_KEY, uservector);
		// session.setAttribute(name, value);
		db.close();
		return (mapping.findForward("ToUserInformation"));
	}

}
