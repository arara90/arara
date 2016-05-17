package net.answeris.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;

import net.answeris.web.model.Notice;


/*Data Access Object*/
public class JdbcNoticeDao implements NoticeDao {


	
	@Override
	public List<Notice> getList(String field, String query, int page) {
		
		  
		
		int pageSize =10;
		int start = ((page-1)*pageSize)+1;
		int end = page*pageSize;

		  String url="jdbc:oracle:thin:@211.238.142.251:1521:orcl";
		  String sql="SELECT * FROM (SELECT ROWNUM NUM, NOTICE_VIEW.* FROM NOTICE_VIEW "
		            +"WHERE "+field+" LIKE ? ) WHERE NUM BETWEEN ? AND ?";
	   
	      
	      try {
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         Connection con = DriverManager.getConnection(url, "c##sist", "dclass");
	        /* Statement st = con.createStatement();*/
	       
	         
	         PreparedStatement st = con.prepareStatement(sql);
	         st.setString(1, "%" + query + "%");
	         st.setInt(2, start);
	         st.setInt(3, end);
	         
	         ResultSet rs= st.executeQuery();
	         
	         List<Notice> noticeList = new ArrayList<Notice>();
	      
	         
	         while(rs.next()){ //서버에서 레코드 하나 가져옴
	            Notice n = new Notice();
	            
	            n.setCode(rs.getString("CODE"));
	            n.setTitle(rs.getString("TITLE"));
	            n.setRegDate(rs.getDate("REGDATE"));
	            n.setHit(rs.getInt("HIT"));
	            n.setWriter(rs.getString("WRITER"));
	            n.setContent(rs.getString("CONTENT"));
	            n.setWriterName(rs.getString("WRITER_NAME"));
	            n.setCmtCnt(rs.getInt("CMT_CNT"));
	            
	            noticeList.add(n);

	        }
	         
	         rs.close();
	         st.close();
	         con.close();
	         
	         return noticeList;
	         
	         
	      } catch (ClassNotFoundException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }

		return null;
	}

	
	
	
	
	@Override
	public List<Notice> getList(int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notice> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Notice getNext(String code) {
		// TODO Auto-generated method stub
		
		
		
		String sql="";
		
		
		
		return null;
	}

	@Override
	public Notice getPrev(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int Add(Notice notice) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Notice notice) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String code) {
		// TODO Auto-generated method stub
		return 0;
	}





	@Override
	public Notice get(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
	

}
