package net.answeris.web.dao;
import java.util.List;
import net.answeris.web.model.Notice;


public interface NoticeDao {
	
	public List<Notice> getList(String field, String query, int page);
	public List<Notice> getList(int page);
	public List<Notice> getList();
	
	
	public Notice get(String code);
	public Notice getNext(String code);
	public Notice getPrev(String code);
	public int Add(Notice notice); 
	public int update(Notice notice);
	public int delete(String code);
	//조작명령어는 일반적으로 성공/실패 구분을 위해 int형 반환을 쓴다
	
	

}
