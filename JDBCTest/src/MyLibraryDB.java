

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class MyLibraryDB
	{
	  Connection conn;
	  static String url;
	  public static void main(String[] args)
	  {
		  try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			url = "jdbc:mysql://localhost:3306/";
		}
		catch (ClassNotFoundException ex) {System.err.println(ex.getMessage());}
	    catch (IllegalAccessException ex) {System.err.println(ex.getMessage());}
	    catch (InstantiationException ex) {System.err.println(ex.getMessage());}

		  Interactive();
	  }
	  
public static void Interactive(){

	  
    //the data that will be entered by the user
    String s; 
    BufferedReader reader;
    reader = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("\nPlease select options below\n"+
    		  "0 - Create Library Database\n" +
			  "1 - Create Tables Books, Book_loan, Book_Copies and Borrower\n" +
			  "2 - Drop Tables Books, Book_loan, Book_Copies and Borrower\n" +
			  "3 - Insert Records into  Books, Book_loan and Borrower Tables\n" +
			  "4 - Delete All Records from  Books, Book_loan and Borrower Tables\n" +
			  "5 - Make Report of Books due yesterday\n" +
			  "999 - DROP LIBRARY DATABASE (NB: THIS ACTION CAN NOT BE UN-DONE)\n" +
    		  "6 - Exit\n");
    try {
		s = reader.readLine();
		new MyLibraryDB(s);
	} catch (IOException e) {
		e.printStackTrace();
	}
	
}	
	 
	  public MyLibraryDB(String s)
	  {
	    try
	    {
      if(RunQuery(s))
	    	  Interactive();
	    }
	    catch (Exception ex) {System.err.println(ex.getMessage());}
	  }
	  
	  public boolean RunQuery(String a){
		  try {
		  	String s;
		    BufferedReader reader;
		    
		    if(a.trim().equals("0")){
		    	CreateSchema();
		    }else if(a.trim().equals("999")){
		    	DropSchema();
		    }else if(a.trim().equals("1")){
				  reader = new BufferedReader(new InputStreamReader(System.in));
				  System.out.println("\nSelect Options below\n"+
							"1 - Create Books Table\n" +
							"2 - Create Book_loan Table\n" +
							"3 - Create Borrower Table\n" +
							"4 - Create Book_copies Table\n" +
							"6 - Back\n");
				  s = reader.readLine();
				  if(s.trim().equals("1")){
					  CreateBooks();
				  }else if(s.trim().equals("2")){
					  CreateBook_Loans();
				  }else if(s.trim().equals("3")){
					  CreateBorrower();
				  }else if(s.trim().equals("4")){
					  CreateBook_Copies();
				  }
				  else
					  return false;
			  }else if(a.trim().equals("2")){
			  reader = new BufferedReader(new InputStreamReader(System.in));
			  System.out.println("\nSelect Options below\n"+
						"1 - Drop Books Table\n" +
						"2 - Drop Book_loan Table\n" +
						"3 - Drop Borrower Table\n" +
						"4 - DropBook_copies Table\n" +
						"6 - Back\n");
			  s = reader.readLine();
			  if(s.trim().equals("1")){
				  DropBooks();
			  }
			  else if(s.trim().equals("2")){
				  DropBook_Loans();
			  }else if(s.trim().equals("3")){
				  DropBorrower();
			  }else if(s.trim().equals("4")){
				  DropBook_Copies();
			  }
			  else
				  return false;
		  }else if(a.trim().equals("3")){
			  reader = new BufferedReader(new InputStreamReader(System.in));
			  System.out.println("\nSelect Options below\n"+
						"1 - Insert Record into Books Table\n" +
						"2 - Insert Record into Book_loan Table\n" +
						"3 - Insert Record into Borrower Table\n" +
						"4 - Insert Record into Book_copies Table\n" +
						"6 - Back\n");
			  s = reader.readLine();
			  if(s.trim().equals("1")){
				  System.out.println("\nSelect Options below\n"+
							"1 - Predefined Records\n" +
							"2 - New Records\n" +
							"6 - Return to Main Screen");
				  s = reader.readLine();
				  if(s.trim().equals("6"))
					  return true;
				  else if(s.trim().equals("2"))
					  while(!(s.trim().split(",").length == 3)){
						  System.out.println("\nPlease Enter Book Records BOOK_ID, TITLE, PUBLISHER_NAME\nseparated by a commas "+
						  "E.g: 3,C Programmin,Willy \n"+
						  "6-Return to Main Screen");
						  s = reader.readLine();
						  if(s.trim().equals("6"))
							  return true;
						  }
				  else
					  s="";
				  InsertBooks(s);
			  }
			  else if(s.trim().equals("2")){
				  System.out.println("\nSelect Options below\n"+
							"1 - Predefined Records\n" +
							"2 - New Records\n" +
							"6 - Return to Main Screen");
				  s = reader.readLine();
				  if(s.trim().equals("6"))
					  return true;
				  else if(s.trim().equals("2"))
					  while(!(s.trim().split(",").length == 5)){
						  System.out.println("\nPlease Enter Book_Loans Records BOOK_ID, BRANCH_ID, CARD_NO, DATE_OUT, DUE_DATE\nseparated by a commas "+
						  "E.g: 3,1,3,2012-10-31,2012-10-31 \n"+
						  "6-Return to Main Screen");
						  s = reader.readLine();
						  if(s.trim().equals("6"))
							  return true;
						  }
				  else
					  s="";
				  InsertBook_Loans(s);
			  }else if(s.trim().equals("3")){
				  System.out.println("\nSelect Options below\n"+
							"1 - Predefined Records\n" +
							"2 - New Records\n" +
							"6 - Return to Main Screen");
				  s = reader.readLine();
				  if(s.trim().equals("6"))
					  return true;
				  else if(s.trim().equals("2"))
					  while(!(s.trim().split(",").length == 4)){
						  System.out.println("\nPlease Enter Borrower Records CARD_NO, NAME, ADDRESS, PHONE\nseparated by a commas "+
						  "E.g: 2,Mathew,Ruston,43434334 \n"+
						  "6 - Return to Main Screen");
						  s = reader.readLine();
						  if(s.trim().equals("6"))
							  return true;
						  }
				  else
					  s="";
				  InsertBorrower(s);
			  }else if(s.trim().equals("4")){
				  System.out.println("\nSelect Options below\n"+
							"1 - Predefined Records\n" +
							"2 - New Records\n" +
							"6 - Return to Main Screen");
				  s = reader.readLine();
				  if(s.trim().equals("6"))
					  return true;
				  else if(s.trim().equals("2"))
					  while(!(s.trim().split(",").length == 3)){
						  System.out.println("\nPlease Enter Book Copies BOOK_ID, BRANCH_ID, NUMBER_OF_COPIES\nseparated by a commas "+
						  "E.g: 1,1,1 \n"+
						  "6 - Return to Main Screen");
						  s = reader.readLine();
						  if(s.trim().equals("6"))
							  return true;
						  }
				  else
					  s="";
				  InsertBook_Copies(s);
			  }
			  else
				  return false;
		  }else if(a.trim().equals("4")){
			  reader = new BufferedReader(new InputStreamReader(System.in));
			  System.out.println("\nSelect Options below\n"+
						"1 - Drop Books Table\n" +
						"2 - Drop Book_loan Table\n" +
						"3 - Drop Borrower Table\n" +
						"4 - DropBook_copies Table\n" +
						"6 - Back\n");
			  s = reader.readLine();
			  if(s.trim().equals("1")){
				  DeleteBooks();
			  }else if(s.trim().equals("2")){
				  DeleteBook_Loans();
			  }else if(s.trim().equals("3")){
				  DeleteBorrower();
			  }else if(s.trim().equals("4")){
				  DeleteBook_Copies();
			  }
			  else
				  return false;
		  }else if(a.trim().equals("5")){
			  SearchBorrower();
		  }else if(a.trim().equals("6")){
			  return false;
		  }else
			  System.out.println("\nInvalid Option Selected\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
	  }

	  private void CreateSchema(){
		    System.out.println("\n[CREATING LIBRARY DATABASE......]");
		    String query = "CREATE DATABASE library;";
		    UpdateDB(query,"");
}
	  private void DropSchema(){
		    System.out.println("\n[DROPPING LIBRARY DATABASE......]");
		    String query = "DROP DATABASE library;";
		    UpdateDB(query,"");
}
	  private void CreateBooks(){
		    System.out.println("\n[CREATING BOOKS TABLE......]");
		    String query = "CREATE TABLE book (  book_id int(11) NOT NULL,  title varchar(45) DEFAULT NULL,  Publisher_name varchar(45) DEFAULT NULL,  PRIMARY KEY (book_id));";
		    UpdateDB(query,"library");
}
	  private void CreateBook_Copies(){
		    System.out.println("\n[CREATING BOOK COPIES TABLE......]");
		    String query = "CREATE TABLE `book_copies` ( `book_id` int(11) NOT NULL,  `branch_id` int(11) DEFAULT NULL,  `number_of_copies` int(11) DEFAULT NULL,  PRIMARY KEY (`book_id`)) ";
		    UpdateDB(query,"library");
	  }
	  private void CreateBook_Loans(){
		    System.out.println("\n[CREATING BOOK LOANS TABLE......]");
		    String query = "CREATE TABLE `book_loans` ( `book_id` int(11) NOT NULL,  `branch_id` int(11) DEFAULT NULL,  `card_no` int(11) DEFAULT NULL,  `date_out` date DEFAULT NULL,  `due_date` date DEFAULT NULL,  PRIMARY KEY (`book_id`))";
		    UpdateDB(query,"library");
	  }
	  private void CreateBorrower(){
		    System.out.println("\n[CREATING BORROWER TABLE......]");
		    String query = "CREATE TABLE `borrower` (  `card_no` int(11) NOT NULL,  `name` varchar(45) DEFAULT NULL,  `address` varchar(45) DEFAULT NULL,  `phone` int(11) DEFAULT NULL,  PRIMARY KEY (`card_no`))";
		    UpdateDB(query,"library");
	  }
	  private void DropBooks(){
		    System.out.println("\n[DROPPING BOOK LOANS TABLE......]");
		    String query = "DROP TABLE `book` ";
		    UpdateDB(query,"library");
	  }

	  private void DropBook_Copies(){
		    System.out.println("\n[DROPPING BOOK COPIES TABLE......]");
		    String query = "DROP TABLE `book_copies` ";
		    UpdateDB(query,"library");
	  }

	  private void DropBook_Loans(){
		    System.out.println("\n[DROPPING BOOK LOANS TABLE......]");
		    String query = "DROP TABLE `book_loans` ";
		    UpdateDB(query,"library");
	  }

	  private void DropBorrower(){
		    System.out.println("\n[DROPPING BORROWER TABLE......]");
		    String query = "DROP TABLE `borrower` ";
		    UpdateDB(query,"library");
	  }

	  private void InsertBook_Loans(String query){
		  if(query.trim().equals("")){
			  query = "INSERT INTO `book_loans` VALUES (1,1,1,'2012-10-01','2012-10-28'),"+
				"(2,1,2,'2012-10-12',DATE(DATE_ADD(NOW(), INTERVAL -1 DAY))),"+
				"(3,1,3,'2012-10-31',DATE(DATE_ADD(NOW(), INTERVAL -1 DAY)));";
		  }else{
			  
			  query = "INSERT INTO `book_loans` VALUES ("+query.trim().split(",")[0]+","+query.trim().split(",")[1]+","+query.trim().split(",")[2]+
				  	",'"+query.trim().split(",")[3]+"','"+query.trim().split(",")[4]+"');";
		  }
		  
		    System.out.println("\n[INSERTING INTO BOOK LOANS TABLE]");
		    UpdateDB(query,"library");
	  }
	  

	  private void InsertBooks(String query){
		  if(query.trim().equals("")){
			  query = "INSERT INTO `book` VALUES (1,'Dabase MS','Hatwib'),"+
				"(2,'Java Prog','John'),"+
				"(3,'C Programmin','Willy');";
		  }else{
			  
			  query = "INSERT INTO `book` VALUES ("+query.trim().split(",")[0]+",'"+query.trim().split(",")[1]+"','"+query.trim().split(",")[2]+"');";
		  }
		    System.out.println("\n[INSERTING INTO  BOOK TABLE]");
		    UpdateDB(query,"library");
	  }
	

	  private void InsertBorrower(String query){
		  if(query.trim().equals("")){
			  query = "INSERT INTO `borrower` VALUES (1,'John','Ruston',1212121),"+
				"(2,'Mathew','Ruston',43434334),"+
				"(3,'Philip','Monroe',98898989);";
		  }else{
			  
			  query = "INSERT INTO `borrower` VALUES ("+query.trim().split(",")[0]+",'"+query.trim().split(",")[1]+"','"+query.trim().split(",")[2]+
				  	"',"+query.trim().split(",")[3]+");";
		  }
		    System.out.println("\n[INSERTING INTO  BORROWER TABLE]");
		    UpdateDB(query,"library");
	  }

	  private void InsertBook_Copies(String query){
		  if(query.trim().equals("")){
			  query = "INSERT INTO `book_copies` VALUES (1,1,1),"+
				"(2,1,1),"+
				"(3,1,1);";
		  }else{
			  
			  query = "INSERT INTO `book_copies` VALUES ("+query.trim().split(",")[0]+","+query.trim().split(",")[1]+","+query.trim().split(",")[2]+");";
		  }
		    System.out.println("\n[INSERTING INTO  BOOK COPIES TABLE]");
		    UpdateDB(query,"library");
	  }

	  private void DeleteBook_Loans(){
		    System.out.println("\n[DELETING FROM BOOK LOANS TABLE]");
		    String query = "DELETE FROM `book_loans`;";
		    UpdateDB(query,"library");
	  }
	  

	  private void DeleteBooks(){
		    System.out.println("\n[DELETING FROM  BOOK TABLE]");
		    String query = "DELETE FROM `book`;";
		    UpdateDB(query,"library");
	  }
	
	  private void DeleteBorrower(){
		    System.out.println("[DELETING INTO  BORROWER TABLE]");
		    String query = "DELETE FROM `borrower`;";
		    UpdateDB(query,"library");
	  }
	  private void DeleteBook_Copies(){
		    System.out.println("[DELETING INTO  BOOK COPIES TABLE]");
		    String query = "DELETE FROM `book_copies`;";
		    UpdateDB(query,"library");
	  }
	  
	  private void UpdateDB(String sql,String db){
		    try
		    {
		    	
			    conn = DriverManager.getConnection(url+db, "root", "");
			    Statement st = conn.createStatement();
			    st.executeUpdate(sql);
			    conn.close();
			    System.out.println("[DONE]\n");
		    }
		    catch (SQLException ex)           {System.err.println(ex.getMessage());}
		  
	  }
	  
	  private void SearchBorrower()
	  {

		    System.out.println("\n[BOOK TITLE AND BORROWER'S NAME OF BOOKS THAT BECAME OVERDUE YESTERDAY]");
		    String query = "SELECT b.title, BR.NAME FROM"+
		    				" BOOK B,BOOK_LOANS BL, BORROWER BR "+
		    				"WHERE B.BOOK_ID = BL.BOOK_ID "+
		    				"AND BL.CARD_NO = BR.CARD_NO "+
		    				"AND DATE(DUE_DATE) = DATE(DATE_ADD(NOW(), INTERVAL -1 DAY));";
		    try
		    {
			  conn = DriverManager.getConnection(url+"library", "root", "");
		      Statement st = conn.createStatement();
		      ResultSet rs = st.executeQuery(query);
		      System.out.println("\nTITLE" + "     " + "NAME\n");
		      while (rs.next())
		      {
		        String s = rs.getString("TITLE");
		        String n = rs.getString("NAME");
		        System.out.println(s + "     " + n+"\n");
		      }
		    }
		    catch (SQLException ex){System.err.println(ex.getMessage());}
		  

	  }
	  
	  
}
