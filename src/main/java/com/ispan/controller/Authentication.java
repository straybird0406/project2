package com.ispan.controller;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.ispan.dao.AdminDAO;
import com.ispan.dao.MemberDAO;

/**
 * Servlet Filter implementation class Authentication
 */
@WebFilter("/login")
public class Authentication extends HttpFilter implements Filter {
       
    public Authentication() {
        super();

    }


	public void destroy() {

	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		
		System.out.println("Authentication filter triggered");
		
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		AdminDAO admin = new AdminDAO();
		String password_search = admin.getPassword(username);
		System.out.println("搜尋到的:"+password_search);
			
		if (password_search == null) {
		    // 帳號不存在，直接跳轉到錯誤頁面
		    System.out.println("查無此帳號");
		    HttpServletResponse httpResponse = (HttpServletResponse) response;
		    httpResponse.sendRedirect("topic2/errorPage.jsp?error=username not found");
		    return;  // 這裡應該結束執行，避免繼續執行後面的邏輯
		} 

		// 如果帳號存在，檢查密碼
		if (password.equals(password_search)) {
//		    System.out.println("密碼正確");
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpSession session = httpRequest.getSession();        //密碼正確的話  給session
			session.setAttribute("username", username);
			
			
		    chain.doFilter(request, response);  // 密碼正確，繼續處理
		} else {
		    System.out.println("密碼錯誤");
		    HttpServletResponse httpResponse_password = (HttpServletResponse) response;
		    httpResponse_password.sendRedirect("topic2/errorPage.jsp?error=Incorrect password");  // 密碼錯誤，跳轉錯誤頁面
		}
		
		
		
		
		
		
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
