package bbs.user.form;

import org.apache.struts.action.ActionForm;

/**
 * <strong>UserInsertForm </strong> handles the form that the user will use to
 * insert a new user into the database.
 */

public final class NewArticleForm extends ActionForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String title;
    
    private String content;

    /**
     * @return Returns the content.
     */
    public String getContent() {
        return content;
    }
    /**
     * @param content The content to set.
     */
    public void setContent(String content) {
        this.content = content;
    }
    /**
     * @return Returns the title.
     */
    public String getTitle() {
        return title;
    }
    /**
     * @param title The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }
}