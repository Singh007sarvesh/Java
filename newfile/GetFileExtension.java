import java.io.File;
import java.util.*;
public class GetFileExtension
{

    /**
     * Get File extension in java
     * @param args
     */
    public static void main(String[] args) 
    {
        File file = new File("emp.json");
        List<Employee> empList=new ArrayList<>();
        
        
        if(getFileExtension(file).equals("xml"))
        {
            try
            {
                SaxParserFactory saxParserFactory=new SaxParserFactory();
                empList = saxParserFactory.readSaxParserFactory(file);
                try
                {
                    DatabaseConnection databaseConnection=new DatabaseConnection();
                    for ( Employee employee1 : empList )
                    {
                        EmailValidate emailValidate=new EmailValidate();
                        if(emailValidate.isEmailValid(employee1.email))
                        {
                            databaseConnection.DatabaseConnection1(employee1);
                        }
                        else
                        {
                                System.out.println("Plz Enter Valid Email");
                                return;
                        }
                    }
                    databaseConnection.closeConnection();
                }
                catch(Exception ex)
                {
                    System.out.println(ex);
                }
            }
            catch(Exception ex)
            {
                System.out.println(ex);
            }
            // employees = saxParserFactory.parse();
        }
        else
        {
            if(getFileExtension(file).equals("csv"))
            {
                try
                {
                    CsvReaderFile csvRead=new CsvReaderFile();
                    empList=csvRead.csvReader(file);
                    try
                    {
                        DatabaseConnection databaseConnection=new DatabaseConnection();
                        for ( Employee employee1 : empList )
                        {
                            EmailValidate emailValidate=new EmailValidate();
                            if(emailValidate.isEmailValid(employee1.email))
                            {
                                databaseConnection.DatabaseConnection1(employee1);
                            }
                            else
                            {
                                System.out.println("Plz Enter Valid Email");
                                return;
                            }
                        }
                        databaseConnection.closeConnection();
                    }
                    catch(Exception ex)
                    {
                        System.out.println(ex);
                    }
                }
                catch(Exception ex)
                {
                    System.out.println(ex);
                }
                // employees = saxParserFactory.parse();
            }
            else
            {
                if(getFileExtension(file).equals("json"))
                {
                    try
                    {
                        JsonRead json1=new JsonRead();
                        empList = json1.read(file);
                        try
                        {
                            DatabaseConnection databaseConnection=new DatabaseConnection();
                            for ( Employee employee2 : empList )
                            {
                                EmailValidate emailValidate=new EmailValidate();
                                if(emailValidate.isEmailValid(employee2.email));
                                else
                                {
                                    System.out.println("Plz Enter Valid Email");
                                    return;
                                }
                                databaseConnection.DatabaseConnection1(employee2);
                            }
                            databaseConnection.closeConnection();
                        }
                        catch(Exception ex)
                        {
                            System.out.println(ex);
                        }
                    }
                    catch(Exception ex)
                    {
                        System.out.println(ex);
                    }
                }
                else
                {
                    System.out.println("Plz Read proper extension file");
                }  
            }
            
        }    
    }

    private static String getFileExtension(File file) 
    {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }

}