package util;

import java.net.URLDecoder;
import java.sql.*;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.exceptions.MySQLDataException;

import util.SignupDTO;


public class DBUtil {
	
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
			System.out.println("MySQL 드라이버가 없습니다.<br/>");
		}catch(MySQLDataException e){
			System.out.println("데이터베이스가 없습니다.<br/>");
		}catch(SQLException e){
			System.out.println("사용자 계정 또는 비밀번호가 일치하지 않습니다.<br/>");
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

	private static DBUtil dbutil = new DBUtil();
	public static DBUtil getInstance(){
		return dbutil;
	}
	private DBUtil(){}

	//select      
    public ArrayList<SignupDTO> select(){
      ArrayList<SignupDTO> list = new ArrayList<SignupDTO>();
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      
      try{
       conn = getMySQLConnection();
       pstmt = (PreparedStatement) conn.prepareStatement("select * from signup");
       rs = pstmt.executeQuery();
      
      while(rs.next()){
         
         SignupDTO dto = new SignupDTO();
         dto.setId(rs.getString("id"));
         dto.setPassword(rs.getString("password"));
         dto.setQuestion(rs.getString("question"));
         dto.setAnswer(rs.getString("answer"));
         dto.setName(rs.getString("name"));
         dto.setNumber1(rs.getString("number1"));
         dto.setNumber2(rs.getString("number2"));
         dto.setAddress(rs.getString("address"));
         dto.setPhone(rs.getString("phone"));
         dto.setPhone1(rs.getString("phone1"));
         dto.setPhone2(rs.getString("phone2"));
         dto.setPhone3(rs.getString("phone3"));
         dto.setEmail1(rs.getString("email1"));
         dto.setEmail2(rs.getString("email2"));
         list.add(dto);
      }
   }catch(Exception e){
      e.printStackTrace();
   }finally{
      try{rs.close();}catch(SQLException s){}
      try{pstmt.close();}catch(SQLException s){}
      try{conn.close();}catch(SQLException s){}
   }
      return list;
  }
	
	//loginPro에서 로그인 할 때 사용
	public int userCheck(String id, String password) throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String dbpasswd="";
		int x=-1;
		
		try{
			conn = getMySQLConnection();
			pstmt = (PreparedStatement)conn.prepareStatement("select password from signup where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
		
			if(rs.next())
			{
			dbpasswd=rs.getString("password");
			
			if(dbpasswd.equals(password))
				x=1; //성공
			else
				x=0; //비밀번호 틀림
		}else{
			
			x=-1;	//해당 아이디 없음
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException s){}
			try{pstmt.close();}catch(SQLException s){}
			try{conn.close();}catch(SQLException s){}			
		}
		return x;		
	}
//아이디 중복확인	
	public int confirmId(String id)throws Exception{
		int x=-1;
		try{
			Connection conn = getMySQLConnection();
			PreparedStatement pstmt = (PreparedStatement)conn.prepareStatement("select id from signup where id=?");
			pstmt.setString(1, id);
		
			ResultSet rs = pstmt.executeQuery();
		
			if(rs.next())
				x=1;
			else
				x=-1;	
		}catch(Exception e){
			e.printStackTrace();
		}
		return x;
	}	
	
	public int findId(String name, String phone)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String dbPhone="";
		String dbName="";
		int x=-1;
		
		try{
			conn = getMySQLConnection();
			pstmt = (PreparedStatement) conn.prepareStatement("select name, phone from signup where (name,phone) = (?,?)");
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				dbPhone = rs.getString("phone");
				dbName = rs.getString("name");
				if(dbPhone.equals(phone)&&dbName.equals(name))
				{
					x= 1; //이름 phone 정보 일치
				} else {
					x= 0; //이름 phone 정보 불일치
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null)try{conn.close();}catch(SQLException ex){}
			
		}
		return x;
	} //end findId
	
	public int findPassword(String id, String question, String answer)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String dbId="";
		String dbQuestion="";
		String dbAnswer="";
		int x=-1;
		
		try{
			conn = getMySQLConnection();
			pstmt = (PreparedStatement) conn.prepareStatement("select id, question, answer from signup where (id, question, answer) = (?,?,?)");
			pstmt.setString(1, id);
			pstmt.setString(2, question);
			pstmt.setString(3, answer);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				dbId = rs.getString("id");
				dbQuestion = rs.getString("question");
				dbAnswer = rs.getString("answer");
				if(dbId.equals(id)&&dbQuestion.equals(question)&&dbAnswer.equals(answer))
				{
					x= 1; //id,question,answer 정보 일치
				} else {
					x= 0; //id,question,answer 정보 불일치
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null)try{conn.close();}catch(SQLException ex){}
			
		}
		return x;
	} //end findPassword
	
	public SignupDTO getUserInfo(String id)   
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SignupDTO member = null;

		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT * FROM signup WHERE ID=?");

			conn = DBUtil.getMySQLConnection();
			pstmt = (PreparedStatement)conn.prepareStatement(query.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) 
			{
				String mail = rs.getString("email");
				int idx = mail.indexOf("@"); 
				String mail1 = mail.substring(0, idx);
				String mail2 = mail.substring(idx+1);
				
				String phone = rs.getString("phone");
				int idx2 = phone.indexOf("-"); 
				String phone1 = phone.substring(0, idx2);
				String phone2 = phone.substring(idx2+1);
				String phone3 = phone.substring(idx2+2);
				
				String number = rs.getString("residentnum");
				int idx3 = number.indexOf("-"); 
				String number1 = number.substring(0, idx3);
				String number2 = number.substring(idx3+1);
				
				
				member = new SignupDTO();
				member.setId(rs.getString("id"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setNumber1(number1);
				member.setNumber2(number2);
				member.setAddress(rs.getString("address"));  
				member.setPhone1(phone1);
				member.setPhone2(phone2);
				member.setPhone3(phone3);
				member.setEmail1(mail1);
				member.setEmail2(mail2);
				member.setPoint(rs.getString("point"));
			}

			return member;

		} catch (Exception sqle) {
			throw new RuntimeException(sqle.getMessage());
		} finally {
			try{
				if ( pstmt != null ){ pstmt.close(); pstmt=null; }
				if ( conn != null ){ conn.close(); conn=null;	}
			}catch(Exception e){
				throw new RuntimeException(e.getMessage());
			}
		}
	}	// end getUserInfo
	
	public SignupDTO getUserInfo2(String name)   
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SignupDTO member = null;

		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT id FROM signup WHERE name=?");

			conn = DBUtil.getMySQLConnection();
			pstmt = (PreparedStatement)conn.prepareStatement(query.toString());
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();

			if (rs.next()) 
			{
				member = new SignupDTO();
				member.setId(rs.getString("id"));
			}

			return member;

		} catch (Exception sqle) {
			throw new RuntimeException(sqle.getMessage());
		} finally {
			try{
				if ( pstmt != null ){ pstmt.close(); pstmt=null; }
				if ( conn != null ){ conn.close(); conn=null;	}
			}catch(Exception e){
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	
	public void updateMember(SignupDTO member) throws SQLException{
		 		 
		Connection conn = null;
		PreparedStatement pstmt = null;
	
		try {
	
			StringBuffer query = new StringBuffer();
			query.append("UPDATE signup SET");
			query.append(" PASSWORD=?, EMAIL=?, PHONE=?, ADDRESS=?");
			query.append(" WHERE ID=?");
			
			conn = DBUtil.getMySQLConnection();
			pstmt = (PreparedStatement)conn.prepareStatement(query.toString());
	
			conn.setAutoCommit(false);
			
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getEmail1()+"@"+member.getEmail2());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, URLDecoder.decode(member.getAddress(),"UTF-8"));
			pstmt.setString(5, member.getId());
			pstmt.executeUpdate();
			conn.commit(); 
						
		} catch (Exception sqle) {
			conn.rollback(); 
			throw new RuntimeException(sqle.getMessage());
		} finally {
			try{
				if ( pstmt != null ){ pstmt.close(); pstmt=null; }
				if ( conn != null ){ conn.close(); conn=null;	}
			}catch(Exception e){
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	 // end updateMember
		
	//탈퇴	
	public int deleteMember(String id, String password)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String dbpasswd="";
		int x=-1;
		
		try{
			conn = getMySQLConnection();
			pstmt = (PreparedStatement) conn.prepareStatement("select password from signup where id=?");
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				dbpasswd = rs.getString("password");
				if(dbpasswd.equals(password))
				{
					pstmt = (PreparedStatement) conn.prepareStatement("delete from signup where id=?");
					pstmt.setString(1, id);
					pstmt.executeUpdate();
					x= 1; //탈퇴 성공
				} else {
					x= 0; //비밀번호 틀림
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null)try{conn.close();}catch(SQLException ex){}
			
		}
		return x;
	}
		
public void insertEvent(String id,String coupon,String email) throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		try{
			conn = getMySQLConnection();
			pstmt = (PreparedStatement)conn.prepareStatement("insert into event(id, coupon, email) values(?,?,?)");
			pstmt.setString(1, id);
			pstmt.setString(2, coupon);
			pstmt.setString(3, email);
			pstmt.executeUpdate();
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{pstmt.close();}catch(SQLException s){}
			try{conn.close();}catch(SQLException s){}			
		}		
	}

	public SignupDTO getPoint(String id)   
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SignupDTO member = null;

		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT point FROM signup WHERE id=?");

			conn = DBUtil.getMySQLConnection();
			pstmt = (PreparedStatement)conn.prepareStatement(query.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) 
			{
				member = new SignupDTO();
				member.setPoint(rs.getString("point"));
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null)try{conn.close();}catch(SQLException ex){}
			}
		return member;
		
	}//end getPoint
	
	public void minusPoint1000(String id) throws SQLException{
		 
		Connection conn = null;
		PreparedStatement pstmt = null;
	
		try {
	
			StringBuffer query = new StringBuffer();
			query.append("UPDATE signup SET point = point-1000 WHERE ID=?");
			
			conn = DBUtil.getMySQLConnection();
			pstmt = (PreparedStatement)conn.prepareStatement(query.toString());
	
			conn.setAutoCommit(false);
			
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			conn.commit(); 
						
		} catch (Exception sqle) {
			conn.rollback(); 
			throw new RuntimeException(sqle.getMessage());
		} finally {
			try{
				if ( pstmt != null ){ pstmt.close(); pstmt=null; }
				if ( conn != null ){ conn.close(); conn=null;	}
			}catch(Exception e){
				throw new RuntimeException(e.getMessage());
			}
		}
	}//end minusPoint1000
	
	public void minusPoint2000(String id) throws SQLException{
		 
		Connection conn = null;
		PreparedStatement pstmt = null;
	
		try {
	
			StringBuffer query = new StringBuffer();
			query.append("UPDATE signup SET point = point-2000 WHERE ID=?");
			
			conn = DBUtil.getMySQLConnection();
			pstmt = (PreparedStatement)conn.prepareStatement(query.toString());
	
			conn.setAutoCommit(false);
			
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			conn.commit(); 
						
		} catch (Exception sqle) {
			conn.rollback(); 
			throw new RuntimeException(sqle.getMessage());
		} finally {
			try{
				if ( pstmt != null ){ pstmt.close(); pstmt=null; }
				if ( conn != null ){ conn.close(); conn=null;	}
			}catch(Exception e){
				throw new RuntimeException(e.getMessage());
			}
		}
	}//end minusPoint2000
	
	public void minusPoint5000(String id) throws SQLException{
		 
		Connection conn = null;
		PreparedStatement pstmt = null;
	
		try {
	
			StringBuffer query = new StringBuffer();
			query.append("UPDATE signup SET point = point-5000 WHERE ID=?");
			
			conn = DBUtil.getMySQLConnection();
			pstmt = (PreparedStatement)conn.prepareStatement(query.toString());
	
			conn.setAutoCommit(false);
			
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			conn.commit(); 
						
		} catch (Exception sqle) {
			conn.rollback(); 
			throw new RuntimeException(sqle.getMessage());
		} finally {
			try{
				if ( pstmt != null ){ pstmt.close(); pstmt=null; }
				if ( conn != null ){ conn.close(); conn=null;	}
			}catch(Exception e){
				throw new RuntimeException(e.getMessage());
			}
		}
	}//end minusPoint5000
	}


