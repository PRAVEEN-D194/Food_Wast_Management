package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.io.FileInputStream;
import java.util.Properties;


import static java.lang.Class.forName;

public class dbconnection {


    public static Connection getConnection() {
        Connection con = null;
        try{
            Properties prop = new Properties();
            prop.load(new FileInputStream("config.properties"));
            String URL = prop.getProperty("db.url");
            String USER = prop.getProperty("db.username");
            String PASSWORD = prop.getProperty("db.password");

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Database connected successfully");

        }catch (Exception e){
            System.out.println("Connection Failed!" );
            e.printStackTrace();
        }
        return  con;
    }

    public static void main(String[] args) {
        Connection con = dbconnection.getConnection();

        if(con != null){
            System.out.println("db connected successfully");
        }else{
            System.out.println("db connected failed");
        }
    }
}
