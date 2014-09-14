package bbs.user.form;

import org.apache.struts.action.ActionForm;

public class ForumForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int forumid = 0;

	private String forumname = null;
	
	private String forumcategory = null;

	private int pageid = 0;

	public String getForumcategory() {
		return forumcategory;
	}

	public void setForumcategory(String forumcategory) {
		this.forumcategory = forumcategory;
	}

	public ForumForm() {
	}

	public int getForumid() {
		return forumid;
	}

	public void setForumid(int forumid) {
		this.forumid = forumid;
	}

	public String getForumname() {
		return forumname;
	}

	public void setForumname(String forumname) {
		this.forumname = forumname;
	}

	public int getPageid() {
		return pageid;
	}

	public void setPageid(int pageid) {
		this.pageid = pageid;
	}

}
