package com.fnst.paper.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class ClientNoCacheFilter implements Filter{
	private FilterConfig filterConfig;
	private boolean clientCache = false;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		String tempFE = filterConfig.getInitParameter("clientCache");
		if ("true".equals(tempFE)) {
			clientCache = true;
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {

		boolean flag = false;
		String pnc = request.getParameter("CNC");
		Object anc = request.getAttribute("CNC");
		if (pnc != null || anc != null) {
			flag = true;
		}

		if (flag || !clientCache) {
			((HttpServletResponse) response).setHeader("Cache-Control",
					"no-cache");
			((HttpServletResponse) response).setHeader("Pragma", "no-cache");
			((HttpServletResponse) response).setDateHeader("Expires", -1);
		}

		filterChain.doFilter(request, response);
	}

	public void destroy() {
	}

	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	public boolean isClientCache() {
		return clientCache;
	}

	public void setClientCache(boolean clientCache) {
		this.clientCache = clientCache;
	}
}
