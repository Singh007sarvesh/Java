import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

class CsvReader {

    public static void main(String[] args) throws Exception{

        String csvFile = "employee1.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
                Class.forName("com.mysql.jdbc.Driver");  
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");  
                Statement stmt=con.createStatement(); 
                br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] sql = line.split(cvsSplitBy);
                String query = "insert into employee values("+sql[0]+","+sql[1]+","
                                                  +sql[2]+","+sql[3]+","+sql[4]
                                                        +","+sql[5]+","+sql[6]+","+sql[7]+");";
                System.out.println(query);

                stmt.executeUpdate(query);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
