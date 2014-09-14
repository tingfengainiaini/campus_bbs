/**
 * 2012-4-4
 * wnf
 * 下午08:16:54
 */
package bbs.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import bbsDAO.Constants;

public final class BackAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String usergrade = (String) session
				.getAttribute(Constants.LOGIN_USERGRADE_KEY);
		//String usergrade2 = (String) request
	//			.getAttribute(Constants.LOGIN_USERGRADE_KEY);
		String pageForward = null;
		ActionMessages errors = new ActionMessages();
		if ("admin".equalsIgnoreCase(usergrade)) {
			pageForward = "ToAdminLogin";
		} else if ("banzhu".equalsIgnoreCase(usergrade)
				|| "user".equals(usergrade)) {
			pageForward = "ToUserLogin";
		} else {
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"error.accessDeny"));
			if (!errors.isEmpty()) {
				this.saveMessages(request, errors);
			}
			pageForward = "ToErrorPage";
		}
		return (mapping.findForward(pageForward));
	}

}
