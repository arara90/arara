<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
int x = 3;
int y = 4;

int sum = x+y;
String btn="";



if (request.getMethod().equals("POST")) {
	String _x = request.getParameter("x");
	String _y = request.getParameter("y");
	String _sum = request.getParameter("sum");
	String _btn = request.getParameter("btn");
	
	if (_x != null && !_x.equals(""))
		x = Integer.parseInt(_x);

	if (_y != null && !_y.equals(""))
		y = Integer.parseInt(_y);
	
	if (_sum != null && !_sum.equals(""))
		sum = Integer.parseInt(_sum);

	///버튼에 따른 처리 로직 ----------------------------------
	if(_btn != null && !_btn.equals(""))
		btn = _btn;
	
	if(btn.equals("Application"))
	{
		//ServletContext application = request.getServletContext();
		application.setAttribute("sum", sum);
	}
	
	else if(btn.equals("Session")){
		//HttpSession session = request.getSession();
		session.setAttribute("sum", sum);
	}
	
	else if(btn.equals("Cookie")){
		Cookie cookie = new Cookie("sum", String.valueOf(sum));
		cookie.setMaxAge(5*60);
		response.addCookie(cookie);
	}
	
	else{
		sum = x+y;
	}
	
		
	
}




%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

   <form action="addp.jsp" method="post">
      <ul>
         <li><label for="x"> X: </label> <input name="x" /></li>

         <li><label for="y"> Y: </label> <input name="y" /></li>
      </ul>

      <p>
      <input type="hidden" name="sum" value="<%=sum%>" />
         <input type="submit" value="덧셈" />
         <input type="submit" name="btn" value="application" />
         <input type="submit" name="btn" value="session" />
         <input type="submit" name="btn" value="cookie" />
      </p>
   </form>
<p> result:
     <%= sum%>
</p>
<p><a href="mypage">마이페이지</a></p>
</body>
</html>