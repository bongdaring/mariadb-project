package bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookshop.vo.BookVo;


public class BookDao {
	
	public boolean updateRent(BookVo vo) {
		
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//1. JDBC Driver Class 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mariadb://192.168.0.173:3307/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb","mysql123");
			
			
			//3. Sql 준비
			String sql =
					"update book set rent=? where no=?";
			
			//3. Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getRent());
			pstmt.setLong(2, vo.getBookNo());
			
			// 5. SQL 실행
			int count = pstmt.executeUpdate();
			
			// 5. 결과 처리
			result = count == 1;
			
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 : "+e);
		} catch (SQLException e) {
			System.out.println("error:"+e);
		} finally {
			// 6. 자원정리
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	

	public List<BookVo> findAll() {
		List<BookVo> result = new ArrayList<>(); 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//1. JDBC Driver Class 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mariadb://192.168.0.173:3307/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb","mysql123");
			
			
			//3. Sql 준비
			String sql =
					"select b.no, b.title, b.rent, a.name\r\n"
					+ "	from book b, author a\r\n"
					+ "where b.author_no = a.no;";
			
			//3. Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			// 5. 결과 처리
			while(rs.next()) {
				int bookNo = rs.getInt(1);
				String title = rs.getString(2);
				String rent = rs.getString(3);
				String authorName = rs.getString(4);
				
				BookVo vo = new BookVo();
				vo.setBookNo(bookNo);
				vo.setTitle(title);
				vo.setRent(rent);
				vo.setAuthorName(authorName);
				result.add(vo);
			}
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 : "+e);
		} catch (SQLException e) {
			System.out.println("error:"+e);
		} finally {
			// 6. 자원정리
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
