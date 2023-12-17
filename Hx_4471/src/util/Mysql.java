package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;




public class Mysql {
     public static void main(String[] args) throws Exception{
    	 Class.forName("com.mysql.jdbc.Driver");
    	 Connection con = DriverManager.getConnection
    			 ("jdbc:mysql://localhost:3306/hexa","root","root");
    	 Statement stmt = (Statement) con.createStatement();
    	 //PreparedStatement p = null;
    	 //String query= "update Patient set lastname='Robinson' where patientId = 3";
    	 //p = con.prepareStatement(query);
    	 //p.execute();
    	 
    	// int rs = stmt.executeUpdate("insert into doctor(doctorId,firstName,lastName,specialization,contactnumber) values (110,'Dr. Mark ','Antony','Psychologist','9867505665')");
    	// if (rs>0) System.out.println("Inserted");
    	// else System.out.println("Unsuccessful");
    	 ResultSet r = stmt.executeQuery("select * from Doctor");
    	 
    	 while(r.next()) {
    		 System.out.println(r.getInt(1)+" "+r.getString(2)+" "+r.getString(3)+" "+r.getString(4)+" "+r.getString(5));
    	 }
    	 //if (rs>0) System.out.println("Inserted");
    	// else System.out.println("Unsuccessful");
    	con.close();
    	 
     }
}
