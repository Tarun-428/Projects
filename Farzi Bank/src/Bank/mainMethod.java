package Bank;

import java.util.*;

class mainMethod {

	public static void main(String[] args) {
		try {
		char choice;
		Scanner ip1 = new Scanner(System.in);
		Connect cn = new Connect();
		admin ad = new admin();
		User us = new User();
		cn.Connectdb();
		do {
			System.out.println("*********Welcome********");
			System.out.println("press a for Admin login");
			System.out.println("Press b for User login");
			System.out.println("Press c for Register New User");
			System.out.println("Press d for Exit");
			choice = ip1.next().charAt(0);
			switch(choice) {
			case'a': System.out.println("Admin Login");
				ad.login();
				ad.check();
			break;
			case'b': System.out.println("User Login");
				us.Userlogin();
				us.UserCheck();
			break;
			case'c': System.out.println("Register New User");
				cn.insert();
			break;
			case 'd' : System.out.println("****Program Exit****");
			break;
			default : System.out.println("Enter the correct Option");
			
			}
		}while(choice!= 'd');
	
		System.out.println("*****Thank You*****");
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	}
		
}

