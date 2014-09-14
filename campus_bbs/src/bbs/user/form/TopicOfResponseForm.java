package bbs.user.form;

import org.apache.struts.action.ActionForm;

public class TopicOfResponseForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int topicid = 0;

	public TopicOfResponseForm() {
	}

	public void setTopicid(int topicid) {
		this.topicid = topicid;
	}

	public int getTopicid() {
		return topicid;
	}
}