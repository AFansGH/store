package com.store.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		//由tomcat调用ProductCentreServlet该类没有service方法，但是继承的是本类baseServlet所以会来执行这个方法
		//这里我们拿到tmocat传递的req拿到页面传递的方法参数
		String methodName = req.getParameter("method");
		//获得对象的字节码对象，通过方法名反射拿到方法
		try {
			this.getClass().getMethod(methodName,HttpServletRequest.class, HttpServletResponse.class ).invoke(this, req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}