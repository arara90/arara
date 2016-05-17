<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
 <%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
    
    

 <%
if(request.getMethod().equals("POST")){
	
	String c = request.getParameter("c");
	String url="jdbc:oracle:thin:@211.238.142.251:1521:orcl";
	Class.forName("oracle.jdbc.driver.OracleDriver");
	
	Connection con = DriverManager.getConnection(url, "c##sist", "dclass");
	Statement st = con.createStatement();

	
	/* --------------------------게시글 등록 할 때----------------------------*/
	/* -----------------------  파라미터 필요 할 떄      ------------------------*/
	
	String sql = "DELETE FROM NOTICE WHERE CODE=" + c ;
	
	st.executeQuery(sql);
	con.commit();
 	st.close();
 	con.close();

 	
 	 response.sendRedirect("notice.jsp");


}
  
%>
    
    
 