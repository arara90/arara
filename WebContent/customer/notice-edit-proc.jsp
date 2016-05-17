<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>

 <%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%

if(request.getMethod().equals("POST")){
	
	String c = request.getParameter("c");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	String writer = "천재";
	
	String url="jdbc:oracle:thin:@211.238.142.251:1521:orcl";
	Class.forName("oracle.jdbc.driver.OracleDriver");
	
	Connection con = DriverManager.getConnection(url, "c##sist", "dclass");
	Statement st = con.createStatement();

	
	/* --------------------------게시글 등록 할 때----------------------------*/
	/* -----------------------  파라미터 필요 할 떄      ------------------------*/
	
	String sql = "UPDATE NOTICE SET TITLE=?, CONTENT=? WHERE CODE=?";
	PreparedStatement pst = con.prepareStatement(sql);
	pst.setString(1, title);  /* 첫번째물음표에 코드를 넣겠다. */
	pst.setString(2, content);
	pst.setString(3, c);
	
	pst.executeUpdate();	
	

   response.sendRedirect("notice-detail.jsp?c="+c);
}




%>