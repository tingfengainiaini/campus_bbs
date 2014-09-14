package bbs.admin.form;

import org.apache.struts.action.ActionForm;

public class ForumManagerForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String forumid = null;

	private String forumname = null;

	private String manager = null;

	private String forumcategory=null;

	public String getForumcategory() {
		return forumcategory;
	}

	public void setForumcategory(String forumcategory) {
		this.forumcategory = forumcategory;
	}

	public String getForumid() {
		return forumid;
	}

	public void setForumid(String forumid) {
		this.forumid = forumid;
	}

	public String getForumname() {
		return forumname;
	}

	public void setForumname(String forumname) {
		this.forumname = forumname;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

}