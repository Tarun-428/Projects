package Bank;

import java.sql.*; 
import java.util.Scanner;

public class User {
	Connect cm = new Connect();
	String User_id,Userpass;
	Scanner sc = new Scanner(System.in);
	Scanner sc1 = new Scanner(System.in);
	Scanner sc2 = new Scanner(System.in);
	void Userlogin() {
		cm.Connectdb();
		
		
		System.out.print("Enter User Id : ");
		User_id = sc2.nextLine();
		System.out.print("Enter User Password : ");
		Userpass = sc2.nextLine();
		}
	
	void UserCheck() {
		try {
		boolean x = false;
		String sql = "select * from user where approval = 'Yes'";
		String tempUId;
		String tempUpass;
		cm.Connectdb();
		ResultSet rs = cm.stmt.executeQuery(sql);
		String name = null;
		int age = 0;
		long accountNo = 0;
		long mobile = 0;
		float Balance = 0;
		String accounttype = null;
		while(rs.next()) {
			tempUId = rs.getString("userId");
			tempUpass = rs.getString("password");
			if(tempUId.equals(User_id) && tempUpass.equals(Userpass)) {
				System.out.println("\n****User login successfully****\n");
				name = rs.getString(2);
				age = rs.getInt(3);
				accountNo = rs.getLong(4);
				mobile = rs.getLong(5);
				Balance = rs.getFloat(6);
				accounttype = rs.getString(7);
				System.out.println("User Id : " +rs.getString(1)+"\nName : "+rs.getString(2)+"\nAge : "+rs.getInt(3)+"\nAccount No : "+rs.getLong(4)+"\nMobile NO. : "+rs.getLong(5)+"\nBalance : "+rs.getFloat(6)+"\nAccount Type : "+rs.getString(7)+"\nApprove Status : "+rs.getString(9));
				x = true;
				break;
				
				
			}	
		}
		if(x==false) {
			System.out.println("Enter Correct Details !\nor User Not approved");
		}
		char vs;
		if(x==true){
			do {
				System.out.println("\npress a for Withdraw \npress b for Deposit \npress c for update details \npress d for Logout\npress e for Transfer Money\npress f for Show Details");
				vs = sc1.next().charAt(0);
			switch(vs) {
			case 'a' :	Balance = cm.withdraw(User_id,Balance);
				break;
			case 'b' :	Balance = cm.deposit(User_id,Balance);
				break;
			case 'c' :	cm.update(User_id);
				break;
			case 'd' : System.out.println("Logout Successfully");
				break;
			case 'e' : Balance = cm.transfer(User_id,Balance);
				break;
			case 'f' :	System.out.println("\n\nUser Details");	
				   		System.out.println("User Id : " +User_id+"\nName : "+name+"\nAge : "+age+"\nAccount No : "+accountNo+"\nMobile NO. : "+mobile+"\nBalance : "+Balance+"\nAccount Type : "+accounttype+"\n");
				break;
			default : System.out.println("Wrong Choice");
		}
	}while(vs != 'd');
		
	}} catch(Exception e) {
		e.printStackTrace();
	}
	}
}
