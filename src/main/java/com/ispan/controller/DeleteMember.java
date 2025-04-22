package com.ispan.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


import com.ispan.bean.MemberBean;

import com.ispan.dao.MemberDAO;


@WebServlet("/DeleteMember")
public class DeleteMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteMember() {
        super();
    }

    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int member_id = Integer.parseInt(request.getParameter("member_id")) ;

		MemberDAO member = new MemberDAO();
		
		MemberBean member_ans = member.delete(member_id);




		if (member_ans == null) {
		    // 查無此會員
			request.getRequestDispatcher("/topic2/deleteNone.jsp").forward(request, response);
		    
		} else {
		    // 成功刪除會員
		    request.setAttribute("member", member_ans);  // 設置刪除的會員物件
		    request.getRequestDispatcher("/topic2/delete.jsp").forward(request, response);
		    
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
