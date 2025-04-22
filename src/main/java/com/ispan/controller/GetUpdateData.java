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


@WebServlet("/GetUpdateData")
public class GetUpdateData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetUpdateData() {
        super();
    }

    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String member_id = request.getParameter("member_id");
			MemberDAO member = new MemberDAO();
			MemberBean member_ans = member.getOne(member_id);
			
			

			request.setAttribute("member", member_ans);

			request.getRequestDispatcher("/topic2/UpdateData.jsp").forward(request, response);
		} 


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
