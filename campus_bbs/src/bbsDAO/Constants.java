package bbsDAO;

/**
 * 关键字定义类：
 * 
 * @author wnf  @time 2012-3-9
 * 
 */
public final class Constants {
	// Session keys
	/***
	 * 数据库
	 */
	public static final String DATASOURCE_KEY = "bbsDB";
	/**
	 * 用户名字
	 */
	public static final String USERNAME_KEY = "username";
	/**
	 * 用户详细信息；
	 */
	public static final String USERINFORMATION_KEY = "userinformation";
	/***
	 * 论坛列表
	 */
	public static final String FORUM_LIST_KEY = "forums";
	/**
	 * 分类：学习交流区论坛列表：
	 * 
	 */
	public static final String FORUM_LIST_KEY1 = "study-change";
	/**
	 * 分类：体育专区：论坛列表
	 */
	public static final String FORUM_LIST_KEY2 = "sports";
	/**
	 * 分裂：娱乐专区，论坛列表
	 */
	public static final String FORUM_LIST_KEY3 = "entertainment";
	/**
	 * 主题列表
	 */
	public static final String TOPIC_LIST_KEY = "topics";
	/**
	 * 
	 */
	public static final String RESPONSE_LIST_KEY = "responses";
	/**
	 * 用户列表
	 */
	public static final String USER_LIST_KEY = "users";
	/**
	 * 讨论类型
	 */
	public static final String TALK_TYPE_KEY = "talkType";
	/**
	 * 当前论坛号
	 */
	public static final String CUR_FORUMID_KEY = "CurForumid";
	/**
	 * 当前论坛名字
	 */
	public static final String CUR_FORUMNAME_KEY = "CurForumName";
	/**
	 * 当前主题号
	 */
	public static final String CUR_TOPICID_KEY = "CurTopicid";
	/**
	 *当前页面号
	 */
	public static final String CUR_PAGEID_KEY = "CurPageid";
	/**
	 * 管理员候选人
	 */
	public static final String MANAGER_CANDIDATE_KEY = "managerCandidates";
	/**
	 * 当前论坛管理员
	 */
	public static final String MANAGER_KEY = "manager";
	/**
	 * 当前论坛类别：
	 */
	public static final String CATEGORY_key = "forumcategory";
	/**
	 * 待选论坛类别：
	 */
	public static final String CATEGOTY_NEW_KEY = "newforumcategory";
	/**
	 * 论坛号
	 */
	public static final String EDIT_FORUMID_KEY = "editForumid";
	/**
	 * 论坛名
	 */
	public static final String EDIT_FORUMNAME_KEY = "editForumname";
	/**
	 * 登录用户等级
	 */
	public static final String LOGIN_USERGRADE_KEY = "loginUserGrade";
	/**
	 * 论坛集合
	 */
	public static final String ALLFORUMS_KEY = "AllForums";
	/**
	 * 主题搜索
	 */
	public static final String SEARCH_TOPIC_KEY = "searchTopic";
	/**
	 * 主题页面长度：
	 */
	public static final int TOPIC_PAGE_SIZE = 5;
}
