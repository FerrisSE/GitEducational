//package myapp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

public class DerbyT 

{
    private static String dbURL = "jdbc:derby://localhost:1527/myDB;create=true;user=test7;password=test1";
   // private static String dbURLembedded="jdbc:derby:myDB;create=true;user=test7;password=test1";
   private static String dbURLembedded="jdbc:derby:c:/Users/abusham1/MyDB3";//create=true";
    

    private static String tableName = "RESTAURANTS";//"Persons";;
  //  Connection connection= DriverManager.getConnection(

    // jdbc Connection
    private static Connection conn = null; //adding a comment
    private static Statement stmt = null;

    private static  void testQuery() {
    	try {  
            stmt = conn.createStatement();

//    		stmt.execute("SET SCHEMA APP");
    		//stmt.execute("");
    		// stmt.executeQuery("SET SCHEMA APP");
    		   ResultSet results = stmt.executeQuery("SELECT * from APP.PERSON");
       while(results.next())
        {//int id = results.getInt(1);
            String restName = results.getString(2);
            String cityName = results.getString(3);
            System.out.println(" 2nd string  "+ results.getString(2)+ "  3rd string  "+results.getString(3));
            
            System.out.println(" person ID "+ results.getString("PersonID")+ " \n  name "+ results.getString("FirstName")+ "\n Last Name " + results.getString("LastName")+ " Address "+ results.getString("Address")+ "City "+results.getString("City"));
            	
        }
    		stmt.executeQuery("SELECT * FROM \"APP\".\"PERSON\""); }      
    	catch (SQLException sqlExcept){ sqlExcept.printStackTrace();}
    }
    public static void main(String[] args)
    {
        createConnection();
        System.out.println("created connection");
        testQuery();
        //insertRestaurants(5, "LaVals", "Berkeley");
        //selectRestaurants();
        //shutdown();
    }

    
    private static void createConnection()
    {
        try
        {
        	Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
        	//Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            //Get a connection
            conn = DriverManager.getConnection(dbURLembedded); 
        }
        catch (Exception except)
        {
            except.printStackTrace();
        }
        System.out.println("no worries");
    }

    
    
    private static void insertRestaurants(int id, String restName, String cityName)
    {
        try
        {
            stmt = conn.createStatement();
            stmt.execute("insert into " + tableName + " values (" +
                    id + ",'" + restName + "','" + cityName +"')");
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }
    
    private static void selectRestaurants()
    {
        try
        {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from " + tableName);
            ResultSetMetaData rsmd = results.getMetaData();
            int numberCols = rsmd.getColumnCount();
            for (int i=1; i<=numberCols; i++)
            {
                //print Column Names
                System.out.print(rsmd.getColumnLabel(i)+"\t\t");  
            }

            System.out.println("\n-------------------------------------------------");

            while(results.next())
            {
                int id = results.getInt(1);
                String restName = results.getString(2);
                String cityName = results.getString(3);
                System.out.println(id + "\t\t" + restName + "\t\t" + cityName);
            }
            results.close();
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }
    
    private static void shutdown()
    {
        try
        {
            if (stmt != null)
            {
                stmt.close();
            }
            if (conn != null)
            {
                DriverManager.getConnection(dbURLembedded + ";shutdown=true");
                conn.close();
            }           
        }
        catch (SQLException sqlExcept)
        {
            
        }

    }
}