package bbs.admin.form;

import org.apache.struts.action.ActionForm;

public class ForumidForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String forumid = null;

	public String getForumid() {
		return forumid;
	}

	public void setForumid(String forumid) {
		this.forumid = forumid;
	}

}