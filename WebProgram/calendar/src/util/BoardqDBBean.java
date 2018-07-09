package util;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.exceptions.MySQLDataException;



public class BoardqDBBean 
{
	public static Connection getMySQLConnection(){
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/makeup";
			String user = "root";
			String password = "";
			conn = DriverManager.getConnection(url, user, password);
		}catch(ClassNotFoundException e){
			System.out.println("MySQL ����̹��� �����ϴ�.<br/>");
		}catch(MySQLDataException e){
			System.out.println("�����ͺ��̽��� �����ϴ�.<br/>");
		}catch(SQLException e){
			System.out.println("����� ���� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.<br/>");
		}
		return conn;
	}

	public static void close(Connection conn){
		try{if(conn != null){conn.close();}}catch(Exception e){e.printStackTrace();}
	}
	public static void close(Statement stmt){
		try{if(stmt != null){stmt.close();}}catch(Exception e){e.printStackTrace();}
	}
	public static void close(PreparedStatement pstmt){
		try{if(pstmt != null){pstmt.close();}}catch(Exception e){e.printStackTrace();}
	}
	public static void close(ResultSet rs){
		try{if(rs != null){rs.close();}}catch(Exception e){e.printStackTrace();}
	}		

	private static BoardqDBBean instance = new BoardqDBBean();

	public static BoardqDBBean getInstance() {
		return instance;
	}

	private BoardqDBBean() {}
	
	// writePro.jsp
	public void insertArticle(BoardqDataBean article) throws Exception  //���Ⱑ �ȵ�!!!
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// �亯������ �Ϲݱ������� �����ؼ� �Է½����ִ� ����!!!
		int num = article.getNum();
		int ref = article.getRef();
		int re_step = article.getRe_step();
		int re_level = article.getRe_level();
		int number = 0;
		String sql = "";
		
		try
		{
			conn = getMySQLConnection();
			pstmt = (PreparedStatement) conn.prepareStatement("select max(num) from boardq");
			rs = pstmt.executeQuery();
			
			if(rs.next())
				number = rs.getInt(1) + 1;
			else
				number = 1;
			
			if(num != 0)
			{
				sql = "update boardq set re_step=re_step+1 where ref=? and re_step>?";
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, re_step);
				pstmt.executeUpdate();
				
				re_step = re_step + 1;
				re_level = re_level + 1;
			}else{
				ref = number;
				re_step = 0;
				re_level = 0;
			}
			
			// ������ �ۼ�
			sql = "insert into boardq(num, writer, subject, password, reg_date,";
			sql += "ref, re_step, re_level, content)" +
				"values(?,?,?,?,?,?,?,?,?)";
			
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setInt(1, article.getNum());
			pstmt.setString(2, article.getWriter());
			pstmt.setString(3, article.getSubject());
			pstmt.setString(4, article.getPassword());
			pstmt.setTimestamp(5, article.getReg_date());
			pstmt.setInt(6, ref);
			pstmt.setInt(7, re_step);
			pstmt.setInt(8, re_level);
			pstmt.setString(9, article.getContent());
			pstmt.executeUpdate();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(rs != null) try { rs.close(); } catch(SQLException ex) {}
			if(pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if(conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
	
	// list.jsp : ����¡�� ���ؼ� ��ü DB�� �Էµ� ���� ���� �ʿ��ϴ�.
	public int getArticleCount() throws Exception
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int x = 0;
		
		try
		{
			conn = getMySQLConnection();
			pstmt = (PreparedStatement) conn.prepareStatement("select count(*) from boardq");
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				x = rs.getInt(1);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(rs != null) try { rs.close(); } catch(SQLException ex) {}
			if(pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if(conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return x;
	}
	
	// list.jsp => Paging!!! DB�κ��� ���� ���� ����� �޴´�.
	public List getArticles(int start, int end) throws Exception
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List articleList = null;
		
		try
		{
			conn = getMySQLConnection();
			pstmt = (PreparedStatement) conn.prepareStatement("select num, writer, subject, password, reg_date, ref, re_step, re_level, content, readcount from boardq order by ref desc, re_step asc limit ?,?");
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				articleList = new ArrayList(end);
				
				do
				{
					BoardqDataBean article = new BoardqDataBean();
					
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setSubject(rs.getString("subject"));
					article.setPassword(rs.getString("password"));
					article.setReg_date(rs.getTimestamp("reg_date"));
					article.setReadcount(rs.getInt("readcount"));
					article.setRef(rs.getInt("ref"));
					article.setRe_step(rs.getInt("re_step"));
					article.setRe_level(rs.getInt("re_level"));
					article.setContent(rs.getString("content"));
					articleList.add(article);
				}while(rs.next());
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(rs != null) try { rs.close(); } catch(SQLException ex) {}
			if(pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if(conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return articleList;
	}
	
	// list.jsp : DB�κ��� �� ���� �����͸� �����´�.
	public BoardqDataBean getArticle(int num) throws Exception
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardqDataBean article = null;
		String sql = "";
		
		try
		{
			sql = "update boardq set readcount=readcount+1 where num=?";
			conn = getMySQLConnection();
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			pstmt = (PreparedStatement) conn.prepareStatement("select * from boardq where num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				article = new BoardqDataBean();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setSubject(rs.getString("subject"));
				article.setPassword(rs.getString("password"));
				article.setReg_date(rs.getTimestamp("reg_date"));
				article.setReadcount(rs.getInt("readcount"));
				article.setRef(rs.getInt("ref"));
				article.setRe_step(rs.getInt("re_step"));
				article.setRe_level(rs.getInt("re_level"));
				article.setContent(rs.getString("content"));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(rs != null) try { rs.close(); } catch(SQLException ex) {}
			if(pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if(conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return article;
	}
	
	// updateForm.jsp : �������� �� ���� �����͸� ������ ��
	public BoardqDataBean updateGetArticle(int num) throws Exception
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardqDataBean article = null;
		
		try
		{
			conn = getMySQLConnection();
			pstmt = (PreparedStatement) conn.prepareStatement("select * from boardq where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				article = new BoardqDataBean();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setSubject(rs.getString("subject"));
				article.setPassword(rs.getString("password"));
				article.setReg_date(rs.getTimestamp("reg_date"));
				article.setReadcount(rs.getInt("readcount"));
				article.setRef(rs.getInt("ref"));
				article.setRe_step(rs.getInt("re_step"));
				article.setRe_level(rs.getInt("re_level"));
				article.setContent(rs.getString("content"));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(rs != null) try { rs.close(); } catch(SQLException ex) {}
			if(pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if(conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return article;
	}
	
	// updatePro.jsp : ���� �����͸� �����ϴ� �޼���
	public int updateArticle(BoardqDataBean article) throws Exception
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String dbpassword = "";
		String sql = "";
		int x = -1;
		
		try
		{
			conn = getMySQLConnection();
			pstmt = (PreparedStatement) conn.prepareStatement("select password from boardq where num=?");
			pstmt.setInt(1, article.getNum());
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				dbpassword = rs.getString("password");
				
				if(dbpassword.equals(article.getPassword()))
				{
					sql = "update boardq set writer=?, subject=?, password=?";
					sql += ", content=? where num=?";
					
					pstmt = (PreparedStatement) conn.prepareStatement(sql);
					pstmt.setString(1, article.getWriter());
					pstmt.setString(2, article.getSubject());
					pstmt.setString(3, article.getPassword());
					pstmt.setString(4, article.getContent());
					pstmt.setInt(5, article.getNum());
					pstmt.executeUpdate();
					
					x = 1;
				}else{
					x = 0;
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(rs != null) try { rs.close(); } catch(SQLException ex) {}
			if(pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if(conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return x;
	}
	
	// deletePro.jsp : ���� �����͸� �����ϴ� �޼���
	public int deleteArticle(int num, String password) throws Exception
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String dbpassword = "";
		int x = -1;
		
		try
		{
			conn = getMySQLConnection();
			pstmt = (PreparedStatement) conn.prepareStatement("select password from boardq where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				dbpassword = rs.getString("password");
				
				if(dbpassword.equals(password))
				{
					pstmt = (PreparedStatement) conn.prepareStatement("delete from boardq where num=?");
					pstmt.setInt(1, num);
					pstmt.executeUpdate();
					
					x = 1; // �� ���� ����
				}
				else
					x = 0; // ��й�ȣ Ʋ��
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(rs != null) try { rs.close(); } catch(SQLException ex) {}
			if(pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if(conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return x;
	}
}