package com.medicine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class medicineDBUtil {
    
    private static Connection con = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
     

    //insert new medicine
    public static boolean addMedicine(String medname, int medqty, double medprice, String medexpiredate) {
        boolean isSuccess = false;

        try {
            con = DBConnect.getConnection();
            stmt = con.createStatement();
            
            // Insert statement
            String sql = "INSERT INTO medicine (medname, medqty, medprice, medexpiredate) VALUES ('" + medname + "', " + medqty + ", " + medprice + ", '" + medexpiredate + "')";
            
            // insert query execution
            int rs = stmt.executeUpdate(sql);

            // Checking 
            if (rs > 0) {
                isSuccess = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
    
    
    // get the list of all medicines
    public static List<Medicine> getMedicineList() {
        ArrayList<Medicine> medicineList = new ArrayList<>();

        try {
            con = DBConnect.getConnection();
            stmt = con.createStatement();
            
            // Select query 
            String sql = "SELECT * FROM medicine ORDER BY medid DESC";
            rs = stmt.executeQuery(sql);

           
            while (rs.next()) {
                int medid = rs.getInt("medid");
                String medname = rs.getString("medname");
                int medqty = rs.getInt("medqty");
                double medprice = rs.getDouble("medprice");
                String medexpiredate = rs.getString("medexpiredate");

               
                Medicine medicine = new Medicine(medid, medname, medqty, medprice, medexpiredate);
                medicineList.add(medicine);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return medicineList;
    }
    
    
    //  update medicine
    public static boolean updateMedicine(int medid, String medname, int medqty, double medprice, String medexpiredate) {
        boolean isUpdated = false;

        try {
            con = DBConnect.getConnection();
            String query = "UPDATE medicine SET medname=?, medqty=?, medprice=?, medexpiredate=? WHERE medid=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, medname);
            pstmt.setInt(2, medqty);
            pstmt.setDouble(3, medprice);
            pstmt.setString(4, medexpiredate);
            pstmt.setInt(5, medid);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                isUpdated = true;
            }

            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isUpdated;
    }
    

    //  delete medicine
    public static boolean deleteMedicine(String medid) {
        boolean isSuccess = false;
        int convMedID = Integer.parseInt(medid);
        
        try {
            con = DBConnect.getConnection();
            stmt = con.createStatement();
            String sql = "DELETE FROM medicine WHERE medid = '" + convMedID + "'";
            int rs = stmt.executeUpdate(sql);
            
            if (rs > 0) {
                isSuccess = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return isSuccess;
    }
}


