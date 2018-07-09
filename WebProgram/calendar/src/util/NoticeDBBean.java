package util;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.exceptions.MySQLDataException;

import util.NoticeDataBean;

public class NoticeDBBean {
	
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

	private static NoticeDBBean instance = new NoticeDBBean();

	public static NoticeDBBean getInstance() {
		return instance;
	}

	private NoticeDBBean() {}

	/*private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";

		return DriverManager.getConnection(jdbcDriver);
	}*/

	// writePro.jsp
	public void insertNotice(NoticeDataBean notice) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 답변글인지 일반글인지를 구분해서 입력시켜주는 로직!!!
		int num = notice.getNum();
		int ref = notice.getRef();
		int re_step = notice.getRe_step();
		int re_level = notice.getRe_level();
		int number = 0;
		String sql = "";

		try {
			conn = getMySQLConnection();
			pstmt = (PreparedStatement)conn.prepareStatement("select max(num) from notice");
			rs = pstmt.executeQuery();

			if (rs.next())
				number = rs.getInt(1) + 1;
			else
				number = 1;

			if (num != 0) {
				sql = "update notice set re_step=re_step+1 where ref=? and re_step>?";
				pstmt = (PreparedStatement)conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, re_step);
				pstmt.executeUpdate();

				re_step = re_step + 1;
				re_level = re_level + 1;
			} else {
				ref = number;
				re_step = 0;
				re_level = 0;
			}
			
			// 쿼리를 작성
			sql = "insert into notice(num, writer, subject, password, reg_date,";
			sql += "ref, re_step, re_level, content)" + "values(?,?,?,?,?,?,?,?,?)";

			
			pstmt = (PreparedStatement)conn.prepareStatement(sql);
			pstmt.setInt(1, notice.getNum());
			pstmt.setString(2, notice.getWriter());
			pstmt.setString(3, notice.getSubject());
			pstmt.setString(4, notice.getPassword());
			pstmt.setTimestamp(5, notice.getReg_date());
			pstmt.setInt(6, ref);
			pstmt.setInt(7, re_step);
			pstmt.setInt(8, re_level);
			pstmt.setString(9, notice.getContent());
			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
	}

	// list.jsp : 페이징을 위해서 전체 DB에 입력된 행의 수가 필요하다.
	public int getNoticeCount() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int x = 0;

		try {
			conn = getMySQLConnection();
			pstmt = (PreparedStatement)conn.prepareStatement("select count(*) from notice");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return x;
	}

	// list.jsp => Paging!!! DB로부터 여러 행을 결과로 받는다.
	public List getNotices(int start, int end) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List noticeList = null;

		try {
			conn = getMySQLConnection();
			pstmt = (PreparedStatement)conn.prepareStatement("select * from notice order by ref desc, re_step asc limit ?,?");
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				noticeList = new ArrayList(end);

				do {
					NoticeDataBean notice = new NoticeDataBean();

					notice.setNum(rs.getInt("num"));
					notice.setWriter(rs.getString("writer"));
					notice.setSubject(rs.getString("subject"));
					notice.setPassword(rs.getString("password"));
					notice.setReg_date(rs.getTimestamp("reg_date"));
					notice.setReadcount(rs.getInt("readcount"));
					notice.setRef(rs.getInt("ref"));
					notice.setRe_step(rs.getInt("re_step"));
					notice.setRe_level(rs.getInt("re_level"));
					notice.setContent(rs.getString("content"));
					noticeList.add(notice);
				} while (rs.next());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return noticeList;
	}

	// DB로부터 한 줄의 데이터를 가져온다.
	public NoticeDataBean getNotice(int num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		NoticeDataBean notice = null;
		String sql = "";

		try {
			sql = "update notice set readcount=readcount+1 where num=?";   
			conn = getMySQLConnection();
			pstmt = (PreparedStatement)conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			pstmt = (PreparedStatement)conn.prepareStatement("select * from notice where num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				notice = new NoticeDataBean();
				notice.setNum(rs.getInt("num"));
				notice.setWriter(rs.getString("writer"));
				notice.setSubject(rs.getString("subject"));
				notice.setPassword(rs.getString("password"));
				notice.setReg_date(rs.getTimestamp("reg_date"));
				notice.setReadcount(rs.getInt("readcount"));
				notice.setRef(rs.getInt("ref"));
				notice.setRe_step(rs.getInt("re_step"));
				notice.setRe_level(rs.getInt("re_level"));
				notice.setContent(rs.getString("content"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return notice;
	}

	// updateForm.jsp : 수정폼에 한 줄의 데이터를 가져올 때
	public NoticeDataBean updateGetNotice(int num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		NoticeDataBean notice = null;

		try {
			conn = getMySQLConnection();
			pstmt = (PreparedStatement)conn.prepareStatement("select * from notice where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				notice = new NoticeDataBean();
				notice.setNum(rs.getInt("num"));
				notice.setWriter(rs.getString("writer"));
				notice.setSubject(rs.getString("subject"));
				notice.setPassword(rs.getString("password"));
				notice.setReg_date(rs.getTimestamp("reg_date"));
				notice.setReadcount(rs.getInt("readcount"));
				notice.setRef(rs.getInt("ref"));
				notice.setRe_step(rs.getInt("re_step"));
				notice.setRe_level(rs.getInt("re_level"));
				notice.setContent(rs.getString("content"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return notice;
	}

	// updatePro.jsp : 실제 데이터를 수정하는 메서드
	public int updateNotice(NoticeDataBean notice) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String dbpassword = "";
		String sql = "";
		int x = -1;

		try {
			conn = getMySQLConnection();
			pstmt = (PreparedStatement)conn.prepareStatement("select password from notice where num=?");
			pstmt.setInt(1, notice.getNum());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dbpassword = rs.getString("password");

				if (dbpassword.equals(notice.getPassword())) {
					sql = "update notice set writer=?, subject=?, password=?";
					sql += ", content=? where num=?";

					pstmt = (PreparedStatement)conn.prepareStatement(sql);
					pstmt.setString(1, notice.getWriter());
					pstmt.setString(2, notice.getSubject());
					pstmt.setString(3, notice.getPassword());
					pstmt.setString(4, notice.getContent());
					pstmt.setInt(5, notice.getNum());
					pstmt.executeUpdate();

					x = 1;
				} else {
					x = 0;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return x;
	}

	// deletePro.jsp : 실제 데이터를 삭제하는 메서드
	public int deleteNotice(int num, String password) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String dbpassword = "";
		int x = -1;

		try {
			conn = getMySQLConnection();
			pstmt = (PreparedStatement)conn.prepareStatement("select password from notice where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dbpassword = rs.getString("password");

				if (dbpassword.equals(password)) {
					pstmt = (PreparedStatement)conn.prepareStatement("delete from notice where num=?");
					pstmt.setInt(1, num);
					pstmt.executeUpdate();

					x = 1; // 글 삭제 성공
				} else
					x = 0; // 비밀번호 틀림
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return x;
	}
}
