package bookmall.vo;

public class MemberVo {
	private int no;
	private String name;
	private String phone;
	private String email;
	private String password;
	
	private int orderNo;
	private String orderManageNo;
	private String address;
	private int totalPrice;
	
	
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getOrderManageNo() {
		return orderManageNo;
	}
	public void setOrderManageNo(String orderManageNo) {
		this.orderManageNo = orderManageNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	@Override
	public String toString() {
		return "이름 : "+name +", 전화번호 : "+phone + ", 이메일 : "+email+", 패스워드 : "+password;
	}
	
	
}
