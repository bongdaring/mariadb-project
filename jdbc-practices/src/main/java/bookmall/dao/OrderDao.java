package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.BookVo;
import bookmall.vo.MemberVo;
import bookmall.vo.OrderBookVo;

public class OrderDao {
	
	public void insertOrder(MemberVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();

			String sql =
					"insert into orders values (null, ?, ?, ?, ?);";
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, vo.getTotalPrice());
			pstmt.setString(2, vo.getAddress());
			pstmt.setInt(3, vo.getNo());
			pstmt.setString(4, vo.getOrderManageNo());

			pstmt.executeUpdate();		
			
		} catch (SQLException e) {
			System.out.println("error:"+e);
		} finally {
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
	}

	public List<MemberVo> findAll() {
		List<MemberVo> result = new ArrayList<>(); 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {	
			conn = getConnection();
			
			String sql =
					" select no, member_no, order_manage_no, address "
					+ " from orders";
			
			pstmt = conn.prepareStatement(sql);	
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt(1);
				int memberNo = rs.getInt(2);
				String orderManageNo = rs.getString(3);
				String address = rs.getString(4);
				
				MemberVo vo = new MemberVo();
				vo.setNo(memberNo);
				vo.setOrderNo(no);
				vo.setOrderManageNo(orderManageNo.split("\\.")[1]);
				vo.setAddress(address);
				
				result.add(vo);
			}
			
			
		} catch (SQLException e) {
			System.out.println("error:"+e);
		} finally {
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
	
	public void insertOrderBook(OrderBookVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();

			String sql =
					"insert into orders_book values (null, ?, ?, ?);";
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, vo.getCount());
			pstmt.setInt(2, vo.getOrderNo());
			pstmt.setInt(3, vo.getBookNo());

			pstmt.executeUpdate();		
			
		} catch (SQLException e) {
			System.out.println("error:"+e);
		} finally {
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
	}

	public List<OrderBookVo> findAllOrderBook() {
		List<OrderBookVo> result = new ArrayList<>(); 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {	
			conn = getConnection();
			
			String sql =
					"select book_no, count from orders_book";
			pstmt = conn.prepareStatement(sql);	
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int bookNo = rs.getInt(1);
				int count = rs.getInt(2);
				
				OrderBookVo vo = new OrderBookVo();
				vo.setBookNo(bookNo);
				vo.setCount(count);

				result.add(vo);
			}
			
			
		} catch (SQLException e) {
			System.out.println("error:"+e);
		} finally {
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
	
	public List<OrderBookVo> findByOrderNo(int orderNo) {
		List<OrderBookVo> result = new ArrayList<>(); 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {	
			conn = getConnection();
			
			String sql =
					"select book_no, count from orders_book where orders_no=?";
			pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1, orderNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int bookNo = rs.getInt(1);
				int count = rs.getInt(2);
				
				OrderBookVo vo = new OrderBookVo();
				vo.setBookNo(bookNo);
				vo.setCount(count);
				
				result.add(vo);
			}

			
		} catch (SQLException e) {
			System.out.println("error:"+e);
		} finally {
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
	
	
	
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://192.168.0.173:3307/bookmall?charset=utf8";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 : "+e);
		}

		return conn;
	}

}
