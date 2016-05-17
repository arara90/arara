import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;





@WebServlet("/key2")
public class Nana extends HttpServlet{
	public void service(HttpServletRequest request, 
		HttpServletResponse response) throws IOException, ServletException{

	/*OutputStream os = response.getOutputStream();
	PrintStream out = new PrintStream(os);								
	out.println("Hello, Servlet");*/
	
	
	response.setCharacterEncoding("UTF-8"); 
	response.setContentType("text/html; charset=UTF-8");
	
	PrintWriter outt = response.getWriter();
	
	
	String _cnt = request.getParameter("cnt");
	int cnt = 100;
	if(_cnt != null && !_cnt.equals(""))
		cnt = Integer.parseInt(_cnt);
	
	for(int i =0; i<cnt; i++)
	{
		outt.println((i+1)+" :안녕, Servlet!!<br />");
	}
 }
}
		

//톰캣이 호출.
//request 입력도구
//response 출력도구
