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

import java.io.IOException;

public class loginFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化配置（如果需要）
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 檢查是否有 session 且 session 中有 "username" 屬性
        HttpSession session = httpRequest.getSession();
        String username = (String) session.getAttribute("username");

        // 如果沒有登入，重定向到登入頁面
        if (username == null) {
            httpResponse.sendRedirect("http://localhost:8080/topic2/topic2/login.html");
            return;
        }

        // 如果已經登入，繼續執行請求
        chain.doFilter(request, response);
    }

    public void destroy() {
        // 清理資源（如果需要）
    }
}