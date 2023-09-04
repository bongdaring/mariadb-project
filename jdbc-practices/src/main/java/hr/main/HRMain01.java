package hr.main;

import java.util.List;
import java.util.Scanner;

import hr.dao.dao.EmployeesDao;
import hr.vo.EmployeesVo;

public class HRMain01 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.println("이름>>");
			String keyworkd = scanner.nextLine();
			if("quit".equals(keyworkd)) {
				break;
			}
			
			List<EmployeesVo> list = new EmployeesDao().findByName(keyworkd);
			
			for(EmployeesVo vo : list) {
				System.out.println(vo.getEmpNo() + ":" + vo.getFirstName() + ":" + vo.getLastName());
			}
			
		}
		scanner.close();

	}

}
