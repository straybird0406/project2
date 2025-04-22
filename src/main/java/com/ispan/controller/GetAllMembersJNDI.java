package com.ispan.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


import com.ispan.bean.MemberBean;

import com.ispan.dao.MemberDAO;



@WebServlet("/GetAllMembersJNDI")
public class GetAllMembersJNDI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetAllMembersJNDI() {
        super();
    }

    
Connection conn;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String empno = request.getParameter("empno");
		MemberDAO member = new MemberDAO();
		List<MemberBean> member_ans = member.getAll();
		
//		if (member_ans == null || member_ans.isEmpty()) {
//            System.out.println("No members found.");
//        } else {
//            System.out.println("Found " + member_ans.size() + " members.");
//        }
		
		
		
		request.setAttribute("members", member_ans);

		request.getRequestDispatcher("/topic2/GetAllMembers.jsp").forward(request, response);
		}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
