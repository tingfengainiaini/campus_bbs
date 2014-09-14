package bbs.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 管理员注销类：
 * 
 * @author wnf
 * @time 2012-3-22下午10:07:58
 * 
 */
public class AdminLogoffAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3)
			throws Exception {
		HttpSession session = request.getSession();
		/**
		 * void invalidate() 
		 * Invalidates（注销） this session then unbinds(释放) any
		 * objects bound（封装） to it. 
		 * 就是说把封装所有objects的session对象注销掉！！
		 */

		session.invalidate();
		return mapping.findForward("ToAdminLogin");
	}
}