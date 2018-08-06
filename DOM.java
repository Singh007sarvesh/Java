import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.sql.*;
 
 public class DOM{
 

  public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";
                             
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
  public static void insert(Employee emp)
  { 

    try
    {     
        // Class.forName("com.mysql.jdbc.Driver");     
      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");    
      Statement stmt=con.createStatement();   
      if(isValid(emp.email));
      else
      {
        System.out.println("Plz Enter Valid Email");
        return;
      }
      String sql= ("insert into emp_records values(\""+emp.firstname+"\",\""+emp.lastname+"\",\""
                                                  +emp.dob+"\",\""+emp.aadahr_id+"\",\""+emp.gender
                                                        +"\",\""+emp.email+"\",\""+emp.username+"\",\""+emp.password+"\");");
      stmt.executeUpdate(sql);// while(rs.next())    
          //System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getString(5)+"  "+rs.getString(6)+"  "+rs.getString(7)+"  "+rs.getString(8));   
          con.close();     
    }
    catch(Exception e)
    { 
      System.out.println(e);
                
    }  
    
  }      

  public static void main(String[] args) throws Exception {
    SAXParserFactory parserFactor = SAXParserFactory.newInstance();
    //System.out.println("success11113333");
    SAXParser parser = parserFactor.newSAXParser();
  //  System.out.println("success1111222");
    SAXHandler handler = new SAXHandler();
    parser.parse(ClassLoader.getSystemResourceAsStream("employee.xml"),
                 handler);
  //    System.out.println("success1111");
    //Printing the list of employees obtained from XML

    for ( Employee emp : handler.empList){
   //  System.out.println(emp);
     //String sql ="insert into emp_records(first_name,last_name,DOB,aadahr_id,gender,email,username,password)values('sarvesh','singh','2008-11-11','123456789125','male','sar@codekraft.in','sar','123')";
     insert(emp);
    }
  }
}
/**
 * The Handler for SAX Events.
 */ 
class SAXHandler extends DefaultHandler {
 
  List<Employee> empList = new ArrayList<>();
  Employee emp = null;
  String content = null;
  @Override
  //Triggered when the start of tag is found.
  public void startElement(String uri, String localName,
                           String qName, Attributes attributes)
                           throws SAXException {
       
    switch(qName){
      //Create a new Employee object when the start tag is found
      case "employee":
        emp = new Employee();
        break;
    }
  }
 
  @Override
  public void endElement(String uri, String localName,
                         String qName) throws SAXException {
   switch(qName){
     //Add the employee to list once end tag is found
     case "employee":
       empList.add(emp);      
       break;
     //For all other end tags the employee has to be updated.
     case "firstName":
       emp.firstname = content;
       break;
     case "lastName":
       emp.lastname = content;
       break;
     case "DOB":
       emp.dob = content;
       break;
      case "aadahr_id":
        emp.aadahr_id = content;
        break;
      case "gender":
        emp.gender = content;
        break;
      case "email":
        emp.email= content;
        break;
      case "username":
        emp.username = content;
        break;
      case "password":
        emp.password= content;
        break;
   }
  }
 
  @Override
  public void characters(char[] ch, int start, int length)
          throws SAXException {
    content = String.copyValueOf(ch, start, length).trim();
  }
     
}
 
class Employee {

  String firstname; 
  String lastname;
  String dob;
  String aadahr_id;
  String  gender;
  String email;
  String username;
  String password;
 
  @Override
  public String toString() {

   return firstname+ " " + lastname+" "+" "+dob+" "+ aadahr_id +" "+gender+" "+email+" "+username+" "+password;
  }
}
