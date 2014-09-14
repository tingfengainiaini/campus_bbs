package bbs.user.action;

import java.util.Calendar;
import java.util.GregorianCalendar;

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

import bbs.user.form.UserLoginForm;
import bbsDAO.Constants;
import bbsDAO.DB;
import bbsDAO.Forum;
import bbsDAO.User;

/**
 * 用户登录流程控： 首先看看是否是游客身份： 游客：相应属性持久化到数据库； 否则 跳转到登陆成功页面：
 * 
 * @version 1.0;
 * @author wnf
 * @time 2012-3-24下午04:51:16
 * 
 */
public final class UserLoginAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		UserLoginForm userLoginForm = (UserLoginForm) form;
		String username2 = userLoginForm.getUsername();
		String category1 = "学习交流区";
		String category2 = "体育专区";
		String category3 = "娱乐专区";
		/**
		 * 中文用户名字编码测试：
		 */
		System.out.println(username2);

		String password = userLoginForm.getPassword();
		// String usergrade= userLoginForm;

		// String username = new
		// String(username2.getBytes("ISO-8859-1"),"utf-8");
		/**
		 * 下面的编码格式换没什么效果的： 始终这个地方出现乱码，格式几乎转变一遍； 始终是不行，不知道哪个地方出现了问题。
		 * 待解决（！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！）
		 */
		String username = new String(username2.getBytes("ISO-8859-1"), "UTF-8");

		GregorianCalendar gc = new GregorianCalendar();
		int year=gc.get(Calendar.YEAR);
		int month=gc.get(Calendar.MONTH)+1;
		int day=gc.get(Calendar.DAY_OF_MONTH);
		
		int hour=gc.get(Calendar.HOUR_OF_DAY);
		int minute=gc.get(Calendar.MINUTE);
		int second=gc.get(Calendar.SECOND);

		String time = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second ;
		/**
		 * 中文用户名字\登录时间编码测试：
		 */

		System.out.print(time);

		System.out.println(username);

		ServletContext context = servlet.getServletContext();
		DataSource dataSource = (DataSource) context.getAttribute("bbsDB");
		DB db = new DB(dataSource);

		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(365 * 24 * 3600);

		ActionMessages errors = new ActionMessages();

		String pageForward;
		/**
		 * 用户登录之后，记录其权限等级
		 */
		if ("admin".equals(User.getUserGrade(db, username2))) {
			session.setAttribute(Constants.LOGIN_USERGRADE_KEY, "admin");
		} else if ("banzhu".equals(User.getUserGrade(db, username2))) {
			session.setAttribute(Constants.LOGIN_USERGRADE_KEY, "banzhu");

		} else if ("user".equals(User.getUserGrade(db, username2))) {
			session.setAttribute(Constants.LOGIN_USERGRADE_KEY, "user");
		} else {
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"error.login.failed"));
			if (!errors.isEmpty()) {
				this.saveMessages(request, errors);
			}
			pageForward = "ToErrorPage";
		}

		if ((!username.equals("guest"))
				&& (!User.checkUser(db, username, password))) {
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"error.login.failed"));
			if (!errors.isEmpty()) {
				this.saveMessages(request, errors);
			}
			pageForward = "ToErrorPage";
		} else {
			/**
			 * 下面有汇总的论坛列表和分类放置的论坛列表；
			 */
			if (User.updatelastlogtime(db, time,username)) {
				session
						.setAttribute(Constants.FORUM_LIST_KEY, Forum
								.search(db));
				session.setAttribute(Constants.FORUM_LIST_KEY1, Forum
						.searchbycategory(db, category1));
				session.setAttribute(Constants.FORUM_LIST_KEY2, Forum
						.searchbycategory(db, category2));
				session.setAttribute(Constants.FORUM_LIST_KEY3, Forum
						.searchbycategory(db, category3));
				session.setAttribute(Constants.USERNAME_KEY, username);
				// session.setAttribute(Constants.LOGIN_USERGRADE_KEY, arg1)

			}
			pageForward = "UserLoginSucceed";
		}
		db.close();
		return (mapping.findForward(pageForward));
	}

}
