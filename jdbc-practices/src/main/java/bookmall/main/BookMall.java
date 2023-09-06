package bookmall.main;

import java.time.LocalDateTime;
import java.util.List;

import bookmall.dao.CategoryDao;
import bookmall.dao.MemberDao;
import bookmall.dao.OrderDao;
import bookmall.vo.CategoryVo;
import bookmall.vo.MemberVo;
import bookmall.vo.OrderBookVo;
import bookmall.dao.BookDao;
import bookmall.dao.CartDao;
import bookmall.vo.BookVo;

public class BookMall {

	public static void main(String[] args) {

		insertMemeber();
		insertCategory();
		insertBook();
		insertCart();
		insertOrder();
		insertOrderBook();
		System.out.println("## 회원리스트 ##");
		findAllMember();
		System.out.println();
		System.out.println("## 카테고리 ##");
		findAllCategory();
		System.out.println();
		System.out.println("## 상품 ##");
		findAllBook();
		System.out.println();
		System.out.println("## 카트 ##");
		findAllCart();
		System.out.println();
		System.out.println("## 주문 ##");
		findAllOrder();
		System.out.println();
		System.out.println("## 주문 도서 ##");
		findAllOrderBook();
	}

	private static void findAllOrderBook() {
		List<OrderBookVo> list = new OrderDao().findAllOrderBook();
		
		for(OrderBookVo vo :list) {
			BookVo book = new BookDao().findByNo(vo.getBookNo());
			System.out.println("[ 도서번호:"+ vo.getBookNo() +", 도서제목:"+book.getTitle() 
			+ ", 수량:"+ vo.getCount()+ " ]");
		}
		
	}

	private static void insertOrderBook() {
		OrderDao dao = new OrderDao();
		OrderBookVo vo1 = new OrderBookVo();
		vo1.setBookNo(1);
		vo1.setCount(2);
		vo1.setOrderNo(1);
		
		OrderBookVo vo2 = new OrderBookVo();
		vo2.setBookNo(3);
		vo2.setCount(3);
		vo2.setOrderNo(1);
		
		dao.insertOrderBook(vo1);
		dao.insertOrderBook(vo2);
	}

	private static void findAllCart() {
		List<BookVo> list = new CartDao().findAll();
		
		for(BookVo vo :list) {
			BookVo book = new BookDao().findByNo(vo.getNo());
			System.out.println("[ 도서제목:"+book.getTitle() + ", 수량:"+ vo.getCount() 
			+", 가격:"+book.getPrice() * vo.getCount()+ " ]");
		}
		
	}

	private static void insertCart() {
		CartDao dao = new CartDao();
		BookVo vo1 = new BookVo();
		vo1.setCount(2);
		vo1.setNo(1);
		vo1.setMemberNo(1);
		
		BookVo vo2 = new BookVo();
		vo2.setCount(3);
		vo2.setNo(3);
		vo2.setMemberNo(2);
		
		dao.insertCart(vo1);
		dao.insertCart(vo2);
	}

	private static void findAllOrder() {
		List<MemberVo> list = new OrderDao().findAll();
		
		for(MemberVo vo :list) {
			int totalPrice = 0;
			MemberVo memberVo = new MemberDao().findByNo(vo.getNo());
			List<OrderBookVo> orderBookVoList = new OrderDao().findByOrderNo(vo.getOrderNo());
			
			for(OrderBookVo orderBookVo: orderBookVoList) {
				BookVo bookVo = new BookDao().findByNo(orderBookVo.getBookNo());
				totalPrice += bookVo.getPrice() * orderBookVo.getCount();
			}
			
			System.out.println("[ 주문번호:" + vo.getOrderManageNo() 
			+ ", 주문자 이름:"+memberVo.getName() +", 주문자 이메일:"+memberVo.getEmail() 
			+ ", 결제금액:"+ totalPrice
			+", 배송지:"+vo.getAddress()+ " ]");
		}
	}

	private static void insertOrder() {
		MemberVo vo = new MemberVo();
		vo.setOrderManageNo(LocalDateTime.now().toString()+vo.getNo());
		vo.setAddress("서울시 관악구");
		vo.setTotalPrice(16000);
		vo.setNo(1);
		
		new OrderDao().insertOrder(vo);
	}

	private static void findAllBook() {
		List<BookVo> list = new BookDao().findAll();
		
		for(BookVo vo :list) {
			System.out.println(vo.toString());
		}
		
	}

	private static void insertBook() {
		BookDao dao = new BookDao();
		BookVo vo1 = new BookVo();
		vo1.setTitle("셜록홈즈");
		vo1.setPrice(10000);
		vo1.setCategoryNo(3);
		
		BookVo vo2 = new BookVo();
		vo2.setTitle("오만과 편견");
		vo2.setPrice(7000);
		vo2.setCategoryNo(2);
		
		BookVo vo3 = new BookVo();
		vo3.setTitle("바람과 함께 사라지다");
		vo3.setPrice(9000);
		vo3.setCategoryNo(1);
		
		dao.insertBook(vo1);
		dao.insertBook(vo2);
		dao.insertBook(vo3);
	}

	private static void findAllCategory() {
		List<CategoryVo> list = new CategoryDao().findAll();
		
		for(CategoryVo vo :list) {
			System.out.println(vo.toString());
		}
		
	}

	private static void insertCategory() {
		CategoryDao dao = new CategoryDao();
		CategoryVo vo1 = new CategoryVo();
		vo1.setName("소설");
		CategoryVo vo2 = new CategoryVo();
		vo2.setName("추리");
		CategoryVo vo3 = new CategoryVo();
		vo3.setName("스릴러");
		
		dao.insertCategory(vo1);
		dao.insertCategory(vo2);
		dao.insertCategory(vo3);
	}

	private static void findAllMember() {
		List<MemberVo> list = new MemberDao().findAll();
		
		for(MemberVo vo :list) {
			System.out.println("[ 이름:"+vo.getName() +", 전화번호:"+vo.getPhone() + ", 이메일:"+vo.getEmail()+", 비밀번호:"+vo.getPassword()+ " ]");
		}
	}

	private static void insertMemeber() {
		MemberDao dao = new MemberDao();
		MemberVo memberVo1 = new MemberVo();
		memberVo1.setName("둘리");
		memberVo1.setEmail("dooly@poscodx.com");
		memberVo1.setPassword("dooly");
		memberVo1.setPhone("010-2222-2222");
		dao.insertMember(memberVo1);
		
		MemberVo memberVo2 = new MemberVo();
		memberVo2.setName("마이콜");
		memberVo2.setEmail("micol@poscodx.com");
		memberVo2.setPassword("micol");
		memberVo2.setPhone("010-3333-3333");
		
		dao.insertMember(memberVo2);
	}

	
}
