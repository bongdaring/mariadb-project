package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateTest02 {

	public static void main(String[] args) {
		DeptVo vo = new DeptVo();
		vo.setNo(2L);
		vo.setName("품질관리팀");
		boolean result = updateDepartment(vo);
		System.out.println(result ? "성공":"실패");
	}

	private static boolean updateDepartment(DeptVo vo) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		
		try {
			//1. JDBC Driver Class 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mariadb://192.168.0.173:3307/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "mysql123");
			
			//3. Sql 준비
			String sql =
					"update dept set name=? where no=?";
			
			//3. Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			
			// 4. binding
			pstmt.setString(1,  vo.getName());
			pstmt.setLong(2, vo.getNo());
			
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
