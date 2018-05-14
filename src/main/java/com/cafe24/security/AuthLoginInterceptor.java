package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter {
	// 이렇게도 주입하는 건 가능하지만... 
	/*@Autowired
	private UserService userService;*/
	
	@Override
	public boolean preHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		// 모든 컨테이너는 ApplicationContext를 구현하고 있음
		// 어디서든 container에 접근하는 클래스 (servletContext -> 전역)
		ApplicationContext ac = WebApplicationContextUtils
									.getWebApplicationContext(
											request.getServletContext());
		// ApplicationContext에서 userService를 받음
		UserService userService = ac.getBean(UserService.class);
		UserVo vo = new UserVo();
		vo.setEmail(email);
		vo.setPassword(password);
		UserVo authUser = userService.getUser(vo);

		// 아래 주석과 같이 쓰면 안됨 -> service를 new 할 수 없기 때문
		/* 
		 * UserService userService = new UserService();
		 * UserVo vo = new UserVo();
		 * vo.setEmail(email);
		 * vo.setPassword(password);
		 * UserVo authUser = userService.getUser(vo);
		 */
		
		if (authUser == null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);
		response.sendRedirect(request.getContextPath()+"/main");
		
		return false;
	}

}
