package bookmall.dao.test;

import java.util.List;

import bookmall.dao.MemberDao;
import bookmall.vo.MemberVo;


public class MemberDaoTest {

	public static void main(String[] args) {
		insertTest();
		findAllTest();
	}

	private static void findAllTest() {
		List<MemberVo> list = new MemberDao().findAll();
		
		for(MemberVo vo :list) {
			System.out.println("이름 : "+vo.getName() +", 전화번호 : "+vo.getPhone() + ", 이메일 : "+vo.getEmail()+", 패스워드 : "+vo.getPassword());
		}
		
	}

	private static void insertTest() {
		MemberVo memberVo1 = new MemberVo();
		memberVo1.setName("유산균");
		memberVo1.setEmail("latcto@core.fit");
		memberVo1.setPassword("pro");
		memberVo1.setPhone("010-2222-2222");
		new MemberDao().insertMember(memberVo1);
		
		MemberVo memberVo2 = new MemberVo();
		memberVo2.setName("히알루");
		memberVo2.setEmail("mini@hanmi.me");
		memberVo2.setPassword("hello world");
		memberVo2.setPhone("010-3333-3333");
		
		new MemberDao().insertMember(memberVo2);

	}

}
