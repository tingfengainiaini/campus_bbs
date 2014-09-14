package bbs.user.action;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import bbs.user.form.RegistrationForm;
import bbsDAO.Constants;
import bbsDAO.DB;
import bbsDAO.User;

/**
 * 注册流程控：
 * 
 * @author wnf
 * @time 2012-3-24下午04:50:32
 * 
 */
public final class RegistrationAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RegistrationForm rgtForm = (RegistrationForm) form;
		String username = rgtForm.getUsername();
		String password = rgtForm.getPassword();
		String sex = rgtForm.getSex();
		String email = rgtForm.getEmail();
		String qq = rgtForm.getQq();
		String signature = rgtForm.getSignature();
		String grade = rgtForm.getGrade();

		GregorianCalendar calendar = new GregorianCalendar();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);

		String registertime = year + "-" + month + "-" + day;
		// Date date = null;

		/**
		 * 测试用户注册之后值传递编码格式；
		 */
		System.out.println(username);

		ServletContext context = servlet.getServletContext();
		DataSource dataSource = (DataSource) context
				.getAttribute(Constants.DATASOURCE_KEY);
		DB db = new DB(dataSource);

		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setSex(sex);
		user.setEmail(email);
		user.setQq(qq);
		user.setSignature(signature);
		user.setGrade(grade);
		user.setRegistertime(registertime);

		String pageForward;
		ActionMessages errors = new ActionMessages();

		if (user.insert(db)&& user.updateuserregistertime(db, registertime,username)) {
			pageForward = "ToUserLogin";
		} else {
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"error.registration.failed"));
			if (!errors.isEmpty()) {

				this.saveMessages(request, errors);
			}
			pageForward = "ToErrorPage";
		}

		db.close();
		return mapping.findForward(pageForward);
	}
}