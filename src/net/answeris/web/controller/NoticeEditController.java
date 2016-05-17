package net.answeris.web.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import net.answeris.web.model.Notice;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.io.IOException;
import java.sql.Connection;




@WebServlet("/customer/notice-edit")
public class NoticeEditController extends HttpServlet {
	

	public void doGet(ServletRequest request, ServletResponse response) throws ServletException, IOException {



		String c= request.getParameter("c");
		String title ="";
		String content ="";
		String writer ="";
		Date regDate = null;
		int hit =0;
		
		String url="jdbc:oracle:thin:@211.238.142.251:1521:orcl";

		Connection con;
		try {
		
			con = DriverManager.getConnection(url, "c##sist", "dclass");
			con.setAutoCommit(false);
			Statement st = con.createStatement();
			
			
			String sqlCode = "SELECT * FROM NOTICE WHERE CODE = " + c ;
			
			ResultSet rs = st.executeQuery(sqlCode);

			
			Notice n = new Notice();
			
			if(rs.next()){
				/*title = rs.getString("title");
				content = rs.getString("content");
				writer = rs.getString("writer");
				hit = rs.getInt("hit");
				regDate = rs.getDate("regDate");*/
				
				
				n.setCode(rs.getString("CODE"));
				n.setTitle(rs.getString("TTILE"));
				n.setWriter(rs.getString("WRITER"));
				n.setRegDate(rs.getDate("REGDATE"));
				n.setContent(rs.getString("CONTENT"));
				n.setHit(rs.getInt("HIT"));
				
				
				
				}
			
			
			
		/*	request.setAttribute("c", c);
			request.setAttribute("title", title);
			request.setAttribute("writer", writer);
			request.setAttribute("regDate", regDate);
			request.setAttribute("content", content);
			request.setAttribute("hit", hit);
		*/
			request.setAttribute("n", n);
			
			
			
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("notice-edit.jsp");
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
