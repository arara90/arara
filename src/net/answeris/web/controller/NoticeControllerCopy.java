package net.answeris.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.answeris.web.model.Notice;

/*@WebServlet("/customer/noticeCopy")*/
public class NoticeControllerCopy extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "jdbc:oracle:thin:@211.238.142.251:1521:orcl";
		String sql = "SELECT * FROM (SELECT * FROM NOTICE_VIEW35)"
	            + "WHERE ROWNUM BETWEEN 1 AND 10";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "c##sist", "dclass");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			List<Notice> noticeList = new ArrayList<Notice>();

			while (rs.next()) {

				Notice n = new Notice();
				n.setCode(rs.getString("CODE"));
				n.setTitle(rs.getString("TTILE"));
				n.setWriter(rs.getString("WRITER"));
				n.setRegDate(rs.getDate("REGDATE"));
				n.setContent(rs.getString("CONTENT"));
				n.setHit(rs.getInt("HIT"));

				noticeList.add(n);

			}

			request.setAttribute("list", noticeList);

			rs.close();
			st.close();
			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("noticeCopy.jsp");
		dispatcher.forward(request, response);

	}
}
