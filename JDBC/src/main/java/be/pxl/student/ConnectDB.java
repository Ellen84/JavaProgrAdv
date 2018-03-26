package be.pxl.student;

import java.sql.*;

public class ConnectDB {
    public static void main (String[] args){
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:63342/StudentDB", "root", "root")) {
            System.out.println("Connection OK");
        }
    }
}
