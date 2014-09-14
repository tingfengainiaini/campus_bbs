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

import bbs.admin.form.UserManagerForm;
import bbsDAO.Constants;
import bbsDAO.DB;
import bbsDAO.User;
/**
 * 
 * @author wnf
 * @time 2012-3-22下午09:37:48
 *
 */
public final class SearchUserAction extends Action{  
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,  
		HttpServletResponse response) throws Exception {
			
   		UserManagerForm searchUserForm = (UserManagerForm) form;         
		String username = (String)searchUserForm.getUsername();
		
        HttpSession session = request.getSession();
		Vector users = new Vector();
		
   		ServletContext context = servlet.getServletContext();
		DataSource dataSource = 
			(DataSource)context.getAttribute(Constants.DATASOURCE_KEY);
        DB db = new DB(dataSource);
        
		users = User.search(db,username);
		
		session.setAttribute(Constants.USER_LIST_KEY,users);
   		
		db.close();

	   	return (mapping.findForward("ToUserManager"));
 	}	
}