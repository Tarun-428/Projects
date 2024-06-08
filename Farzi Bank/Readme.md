Bank Management System

A Java-based console application for managing a banking system. Users can create accounts, log in, transfer money, manage personal details, and admins can approve new user registrations.

Installation

Ensure you have Eclipse IDE and MySQL installed on your system. Create a MySQL database named "Bank". Create two tables: user table with columns: userId varchar(20) primary key, name char(30), age tinyint(3), accountNo int(15), mobileNo int(10), balance float(10), acType char(12), password varchar(12), approval char(3) admin table with columns: AdminId varchar(10) primary key, AdminPass varchar(12)

Modify the connectdb() in connect.java file to set your MySQL username and password for establishing a database connection. in line no 25 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","your_username","yourpassword") update these.

Usage Run mainMethod.java.

Select from the following options:

Admin Login User Login New Registration Exit Follow the prompts to perform actions such as logging in, transferring money, editing personal details, etc.

Features User can transfer money to other users in the database. User can withdraw and deposit money. User can edit personal details after logging in. New users can register only after admin approval. Contributing Contributions are welcome. If you have any ideas or suggestions, please feel free to reach out.

Contact Email: mr.tarunpatiadar824@gmail.com Twitter: @mr_tarun2004
