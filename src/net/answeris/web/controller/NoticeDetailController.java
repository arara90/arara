package net.answeris.web.controller;

import java.io.IOException;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;



@WebServlet("/customer/notice-detail")
public class NoticeDetailController extends HttpServlet{
	
	@Override

	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		//컨트롤러
		
		String c= request.getParameter("c");
		String url="jdbc:oracle:thin:@211.238.142.251:1521:orcl";
		
		
		String title ="";
		String content ="";
		String writer ="";
		Date regDate = null;
		int hit =0;
		Connection con;
		
		try {
			con = DriverManager.getConnection(url, "c##sist", "dclass");
			con.setAutoCommit(false);
			Statement st = con.createStatement();
			
			
			String sqlCode = "SELECT * FROM NOTICE WHERE CODE = " + c ;
			
			ResultSet rs = st.executeQuery(sqlCode);
			
			if(rs.next()){
			title = rs.getString("title");
			content = rs.getString("content");
			writer = rs.getString("writer");
			hit = rs.getInt("hit");
			regDate = rs.getDate("regDate");
			}
			
			request.setAttribute("c", c);
			request.setAttribute("title", title);
			request.setAttribute("writer", writer);
			request.setAttribute("regDate", regDate);
			request.setAttribute("content", content);
			request.setAttribute("hit", hit);
		
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("notice-detail.jsp");
			dispatcher.forward(request, response);
			
			
			rs.close();
			st.close();
			con.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}

}
