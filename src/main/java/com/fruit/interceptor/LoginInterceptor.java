package com.fruit.interceptor;

import com.fruit.pojo.Staff;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {

	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {

		// 登录拦截器，只放行登录和注册功能，其他模块如果没登录就直接跳转到登录页面
		String requestURI = request.getRequestURI();
		if(!requestURI.contains("/login") && !requestURI.contains("/register")) {
			Staff staff = (Staff) request.getSession().getAttribute("staff");
			if(null == staff) {
				response.sendRedirect(request.getContextPath() + "/login.jsp");
				return false;
			}
		}
		return true;
	}

}
