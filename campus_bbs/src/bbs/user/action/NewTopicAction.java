package bbs.user.action;


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

/**
 * 发布新话题流程控：
 * 
 * @author wnf
 * @time 2012-3-24下午03:38:32
 * 
 */
public final class NewTopicAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		ActionMessages errors = new ActionMessages();

		String username = (String) session.getAttribute(Constants.USERNAME_KEY);

		String PageForward;
		if (username == null) {
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"error.login.unlogin"));
			if (!errors.isEmpty()) {
				// saveErrors(request, errors);
				this.saveMessages(request, errors);
			}
			PageForward = "ToErrorPage";

		} else if (username.equals("guest")) {
			/**
			 * 游客身份不能发新话题，
			 */
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"error.privilege.guest.canntcreatetopic"));
			if (!errors.isEmpty()) {
				// saveErrors(request, errors);
				this.saveMessages(request, errors);
			}
			PageForward = "ToErrorPage";

		} else {
			PageForward = "NewArticle";
		}
		return (mapping.findForward(PageForward));
	}

}
