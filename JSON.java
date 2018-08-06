import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.sql.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
class JSON 
{
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
    public static void main(String[] args) throws Exception 
    {

        Class.forName("com.mysql.jdbc.Driver");  
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");  
        Statement stmt=con.createStatement(); 
        Object obj = new JSONParser().parse(new FileReader("emp.json"));
        JSONArray emp = (JSONArray) obj;
        Iterator<String> it = emp.iterator();
        while(it.hasNext())
        {

            Object a = (Object)it.next();
            JSONObject j = (JSONObject)a;
            JSONObject jo= (JSONObject)j.get("employee");
            String first_name = (String) jo.get("first_name");
            String last_name = (String) jo.get("last_name");
            String date_of_birth = (String) jo.get("date_of_birth");
            String aadahr_id = (String) jo.get("aadahr_id");
            String gender = (String) jo.get("gender");
            String email = (String) jo.get("email");
            String username = (String) jo.get("username");
            String password = (String) jo.get("password");
            String query = ("insert into emp_records values(\""+first_name+"\",\""+last_name+"\",\""
                                                     +date_of_birth+"\",\""+aadahr_id+"\",\""+gender
                                                            +"\",\""+email+"\",\""+username+"\",\""+password+"\");");
           // System.out.println(query);
		if(isValid(email));
		else
		{
			System.out.println("Plz Enter Valid Email");
			return;
		}
		System.out.println("hello");
            stmt.executeUpdate(query);

        }
        con.close();
    }
}
