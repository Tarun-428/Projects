package Bank;
import java.sql.*;

import java.util.*;
public class admin {
	Statement stmt;
	String id;
	String pass;
	Scanner sc1 = new Scanner(System.in);
	Scanner sc = new Scanner(System.in);
	Scanner sc2 = new Scanner(System.in);
	Connect cn = new Connect();
	
	void login() {
	cn.Connectdb();
	
	System.out.print("Enter Admin Id : ");
	id = sc2.nextLine();
	System.out.print("Enter Admin Password : ");
	pass = sc2.nextLine();
	
	}
	void check() {
		
		cn.Connectdb();
		String tempId = null;
		String tempPass = null;
		try {
		ResultSet rs = cn.stmt.executeQuery("Select * from Admin");
		

		while(rs.next()) {
			tempId = rs.getString(1);
			tempPass = rs.getString(2);
			
		}
		
		if(id.equals(tempId) && pass.equals(tempPass)) {
			System.out.println("Admin login succesfully ");
			char choice;
			
			do {
				System.out.println("\n*******************");
				System.out.println("Press a for show user");
				System.out.println("Press b for show request");
				System.out.println("Press c for return main menu");
				choice = sc1.next().charAt(0);
			switch(choice) {
			case 'a' : System.out.println("******User********");
					 cn.showUser();
					 char y;
					 System.out.println("press a for Delete user");
					 System.out.println("press z for Admin Menu");
					 y = sc1.next().charAt(0);
					 if(y=='a') {
					 System.out.println("****Delete User****");
					 	cn.del();
					 } else if(y=='z'){
						 break;
					 }else {
						 System.out.println("Wrong Choice");
					 }
				break;
			case 'b' : System.out.println("*******Request******");
					 cn.showRequest();
					 char x;
					 String UId;
						
					 System.out.println("\n*******************");
					 
					 System.out.println("press a for Approve request");
					 System.out.println("press b for Delete User");
					 System.out.println("press z for Admin menu");
					 x = sc1.next().charAt(0);
					 if(x == 'a') {
						 System.out.println("\nEnter UserId for select specific user to approve");
						 UId = sc.nextLine();
						 cn.approve(UId);
					 }else if(x == 'b') {
						 cn.del();
					 }
					 else if(x == 'z') {
						 break;
					 }else {
						 System.out.println("Wrong choice");
					 }
					
				break;
			case 'c'  : System.out.println("Main Menu");
				break;
			default : System.out.println("Choose Correct Option");
			}
			}while(choice != 'c');
			
		}else {
			System.out.println("Id and Password are not matched");
		}
		}catch(Exception e ) {
			e.printStackTrace();
		}
	}
}
