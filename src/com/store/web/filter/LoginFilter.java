package com.store.web.filter;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.store.domain.User;
import com.store.service.LoginService;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 由于参数类型为不带http的，所以有的特有方法不能被调用，所以我们进行强转
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		LoginService ls = new LoginService();
		HttpSession session = req.getSession();
		String username = null;
		String username_code = null;
		String password = null;
		User user = null;
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
		
			for (Cookie coo : cookies) {
				if ("username".equals(coo.getName())) {
					username_code = coo.getValue();
					// 对编过码的username解码
					username = URLDecoder.decode(username_code, "utf-8");
					session.setAttribute("username", username);
				}
				if ("password".equals(coo.getName())) {
					password = coo.getValue();
				}
			}
		}
		if (password != null && username != null) {

			user = ls.login(username, password);
			// 在session中存入user
			session.setAttribute("user", user);
		}
		// 对于过滤器来说，在配置了哪些请求需要过滤后，需要对请求放行
		chain.doFilter(req, resp);

	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
