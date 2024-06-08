package Bank;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.*;

public class Connect{
	Connection con;
	Statement stmt;
	Scanner sc = new Scanner(System.in);
	Scanner sc1 = new Scanner(System.in);
	String name,password,acType;
	String userId;
	String approval = "No";
	int age,choice;
	
	long accountNo,mobileNo;
	double balance = 0.00;
	
	public void Connectdb(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","tarun");
		    stmt =  con.createStatement();
		}catch (Exception e) {
		e.printStackTrace();
	}
	}
	
	void insert() { 
		try {
			
			int result;
			
			
			do {
			System.out.print("Enter your Name : " );
			name = sc.nextLine();
			
			System.out.print("age : ");
			age= sc1.nextInt();
			System.out.print("mobile Number : ");
			mobileNo = sc1.nextLong();
			do {
				System.out.println("*******************");
			System.out.println("choose Account type press \n1 Saving Account\n2 Current Account");
			choice = sc1.nextInt();
			switch(choice) {
			case 1 : acType = "Saving Account";
					System.out.println("Your Account type : " +acType);
				break;
			case 2 : acType = "Current Account";
		            System.out.println("Your Account type : " +acType);
				break;
			default : 
				System.out.println("Choose the correct Option");
			}
			}while(choice != 1 && choice != 2);
			System.out.print("Enter your Password : ");
			
			password = sc.nextLine();
			
			int val = (int) (mobileNo%10000);
			String[] subname = name.split(" ");
			userId = subname[0]+val;
			accountNo = 123 + mobileNo;
			System.out.print("Your User Id is : "+userId+"\nNote:- save userId for future login");			
			String sql = "insert into user values('"+userId+"','"+name+"','"+age+"','"+accountNo+"','"+mobileNo+"','"+balance+"','"+acType+"','"+password+"','"+approval+"')";
			
			result = stmt.executeUpdate(sql);
			
			if(result>0) {
				System.out.println("\nRecord Insertd");
				
			}else {
				System.out.println("\nError in Insertion");
			}
			}while(result != 1);
				
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	void del() { 
			try {
				System.out.println("Enter user id for Deletion");
				userId = sc.nextLine();
				String sql = "delete from user where userId = '"+ userId +"'";
				int result = stmt.executeUpdate(sql);
				if(result>0) {
					System.out.println("record deleted");
				}else {
					System.out.println("error in deletion");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	void showUser() {  
			try {
				String sql = "Select * from user where approval != 'No' ";
				ResultSet rm = stmt.executeQuery(sql);
				while(rm.next()) {
					System.out.println(rm.getString(1)+"\t "+rm.getString(2)+"\t\t "+rm.getInt(3)+"\t\t "+rm.getLong(4)+"\t\t "+rm.getLong(5)+"\t\t "+rm.getFloat(6)+"\t\t "+rm.getString(7)+"\t\t "+rm.getString(9));
				}
					
			}catch(Exception e) {
				e.printStackTrace();
			}
	}
	void showRequest() { 
		try {
			String sql = "Select * from user where approval = 'No' ";
			ResultSet rm = stmt.executeQuery(sql);
			while(rm.next()) {
				System.out.println(rm.getString(1)+"\t  "+rm.getString(2)+"\t  "+rm.getInt(3)+"\t  "+rm.getLong(4)+"\t  "+rm.getLong(5)+"\t  "+rm.getFloat(6)+"\t  "+rm.getString(7)+"\t  "+rm.getString(9));
			}
				
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	void approve(String Id) { 
		try {
			
			String sql = "update user set approval = 'Yes' where userId = '"+Id+"' ";
			int result = stmt.executeUpdate(sql);
			if(result>0) {
				System.out.println("User Approved");
			}else {
				System.out.println("Not Approved");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	float withdraw(String User_id,float Balance) {
		float newBalance = 0;
		try {
		float amount;
		System.out.println("Enter Money : ");
		amount = sc.nextFloat();
		if(Balance >= amount) {
			
			newBalance = Balance - amount;
			String sql = "update user set balance = '"+newBalance+"' where userid = '"+User_id+"'";
			int result = stmt.executeUpdate(sql);
			if(result>0) {
				System.out.println("Amount Withdraw Successfully");
			}else {
				System.out.println("Unable to Withdraw");
			}
		}else {
			System.out.println("Amount is less than your balance");
		}
		
		}catch(Exception e) {
		e.printStackTrace();
	}
		return newBalance;
	
	}
	float deposit(String User_id,float Balance) {
		float newBalance = 0;
		try {
			float amount;
			System.out.println("Enter Money : ");
			amount = sc.nextFloat();
			
				
				newBalance = Balance + amount;
				
				String sql = "update user set balance = '"+newBalance+"' where userid = '"+User_id+"'";
				int result = stmt.executeUpdate(sql);
				if(result>0) {
					System.out.println("Amount Deposit Successfully");
				}else {
					System.out.println("Unable to Deposit");
				}

			}catch(Exception e) {
			e.printStackTrace();
		}
		return newBalance;
	}
	void update(String User_id) {
		try {
			char xx;
			do {
				
				System.out.println("press a for Update Name\npress b for Update Age\npress c for Update Mobile Number\npress d for Update Password\npress e for User Menu");
				xx = sc.next().charAt(0);
				switch(xx) {
					case 'a' :	String name;
								System.out.println("Enter your Correct Name : ");
								name = sc1.nextLine();
								String sql = "update user set name = '"+name+"' where userid = '"+User_id+"'";
								int result = stmt.executeUpdate(sql);
								if(result>0) {
									System.out.println("Name Updated Successfully");
								}else {
									System.out.println("Name Not Updated");
								}
						break;
					case 'b' :	int age;
								System.out.println("Enter your Correct Age : ");
								age = sc.nextInt();
								String sql1 = "update user set age = '"+age+"' where userid = '"+User_id+"'";
								int result1 = stmt.executeUpdate(sql1);
								if(result1>0) {
									System.out.println("Age Updated Successfully");
								}else {
									System.out.println("Age Not Updated");
								}
						break;
					case 'c' :	Long MobileNo;
								System.out.println("Enter your Correct MobileNo : ");
								MobileNo = sc.nextLong();
								String sql2 = "update user set mobileNo = '"+MobileNo+"' where userid = '"+User_id+"'";
								int result2 = stmt.executeUpdate(sql2);
								if(result2>0) {
									System.out.println("Mobile Number Updated Successfully");
								}else {
									System.out.println("Mobile Number Not Updated");
								}
						break;
					case 'd' :	String pass;
								System.out.println("Enter your New Password: ");
								pass = sc1.nextLine();
								String sql3 = "update user set pass = '"+pass+"' where userid = '"+User_id+"'";
								int result3 = stmt.executeUpdate(sql3);
								if(result3>0) {
									System.out.println("Password Updated Successfully");
								}else {
									System.out.println("password Not Updated");
								}
						break;
					case 'e' :	System.out.println("\n****User Menu****");
						break;
					default : 	System.out.println("Wrong Choice");
				}
			}while(xx != 'e');
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	float transfer(String userid, float Balance) {
		float newBalance = 0;
		float uBal = 0;
		float amount;
		try {
			System.out.println("Enter user id whome you want to transfer : ");
			String sql = "Select * from user where approval != 'No' && userid != '"+userid+"' ";
			ResultSet rm = stmt.executeQuery(sql);
			while(rm.next()) {
				System.out.println(rm.getString(1)+" "+rm.getString(2));
			}
			String use;
			use = sc1.nextLine();
			System.out.println("Enter Balance you want to transfer : ");
			
			amount = sc.nextFloat();
			
			 while(rm.next()) {
				 if(rm.getString(1)==use) {
					 uBal = rm.getFloat(6);
				 }
			 }
			
				
			
			 if(Balance >= amount) {
				 	uBal = uBal + amount; 
					newBalance = Balance - amount;
					String sql1 = "update user set balance = '"+newBalance+"' where userid = '"+userid+"'";
					int result = stmt.executeUpdate(sql1);
					String sql3 = "update user set balance = '"+uBal+"' where userid = '"+use+"'";
					int result11 = stmt.executeUpdate(sql3);
					if(result>0 && result11>0) {
						System.out.println("Amount Transfer Succesfully");
					}else {
						System.out.println("Unable to Transfer");
					}
			
				}else {
					System.out.println("Amount is less than your balance");
				}
			 
		
			
		}catch(Exception e) {
			e.printStackTrace();
	}
		 return newBalance;
	}
}
