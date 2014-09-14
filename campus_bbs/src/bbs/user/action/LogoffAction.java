package bbs.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LogoffAction extends Action {
	public ActionForward execute(ActionMapping map, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3)
			throws Exception {
		HttpSession session = request.getSession();
		session.invalidate();
		return map.findForward("ToUserLogin");
	}
}