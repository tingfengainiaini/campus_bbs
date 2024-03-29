package bbs.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 乱码处理 （过滤器）：
 * 
 * @author wnf
 * 
 */
public class EncodingFilter implements Filter {

	// public void destroy() {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// public void doFilter(ServletRequest arg0, ServletResponse arg1,
	// FilterChain arg2) throws IOException, ServletException {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// public void init(FilterConfig arg0) throws ServletException {
	// // TODO Auto-generated method stub
	//
	// }
	
	/**
	 * The default character encoding to set for requests that pass through this
	 * filter.
	 */
	protected String encoding = null;

	/**
	 * The filter configuration object we are associated with. If this value is
	 * null, this filter instance is not currently configured.
	 */
	protected FilterConfig filterConfig = null;

	/**
	 * Should a character encoding specified by the client be ignored?
	 */
	protected boolean ignore = true;

	/**
	 * Take this filter out of service.
	 */
	public void destroy() {
		this.encoding = null;
		this.filterConfig = null;
	}

	/**
	 * Select and set (if specified) the character encoding to be used to
	 * interpret request parameters for this request.
	 * 
	 * @param request
	 *            The servlet request we are processing
	 * @param result
	 *            The servlet response we are creating
	 * @param chain
	 *            The filter chain we are processing
	 * 
	 * @exception IOException
	 *                if an input/output error occurs
	 * @exception ServletException
	 *                if a servlet error occurs
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (ignore || (request.getCharacterEncoding() == null)) {
			request.setCharacterEncoding(selectEncoding(request));
		}
		chain.doFilter(request, response);
		 response.setContentType("text/html;charset=utf-8");
	}

	/**
	 * Place this filter into service.
	 * 
	 * @param filterConfig
	 *            The filter configuration object
	 */
	public void init(FilterConfig filterConfig) throws ServletException {

		this.filterConfig = filterConfig;
		this.encoding = filterConfig.getInitParameter("encoding");
		String value = filterConfig.getInitParameter("ignore");
		if (value == null)
			this.ignore = true;
		else if (value.equalsIgnoreCase("true")
				|| value.equalsIgnoreCase("yes"))
			this.ignore = true;
		else
			this.ignore = false;
	}

	/**
	 * Select an appropriate character encoding to be used, based on the
	 * characteristics of the current request and/or filter initialization
	 * parameters. If no character encoding should be set, return
	 * ��code��null��/code��. ��p�� The default implementation unconditionally
	 * returns the value configured by the ��strong��encoding��/strong��
	 * initialization parameter for this filter.
	 * 
	 * @param request
	 *            The servlet request we are processing
	 */
	protected String selectEncoding(ServletRequest request) {
		return (this.encoding);
	}

	/**
	 * 
	 * @return FilterConfig
	 */
	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

	/**
	 * 
	 * @param filterConfig
	 */
	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

}
