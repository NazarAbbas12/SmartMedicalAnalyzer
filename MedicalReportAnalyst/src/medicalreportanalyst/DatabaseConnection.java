/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package medicalreportanalyst;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection connect()
    {
        try
        {
            String path="C:/Users/HAROON TRADERS/OneDrive/Documents/MedicalDatabase.accdb";
             String url = "jdbc:ucanaccess://" + path;
             return DriverManager.getConnection(url);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
