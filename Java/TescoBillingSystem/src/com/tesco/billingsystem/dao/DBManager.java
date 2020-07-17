/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tesco.billingsystem.dao;

import java.io.FileReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Ramsat
 */
public class DBManager {

    public void driver() {
        try {
            Properties pr = new Properties();
            FileReader read = new FileReader("src\\com\\tesco\\billingsystem\\dao\\db.properties");
            pr.load(read);
            
            Class.forName(pr.getProperty("DB_DRIVER"));
            System.out.println("Successfully loaded databse driver");
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public Connection connection() {

        Connection con = null;
        try {
             Properties pr = new Properties();
            FileReader read = new FileReader("src\\com\\tesco\\billingsystem\\dao\\db.properties");
            pr.load(read);

            con = DriverManager.getConnection(pr.getProperty("DB_NAME"),pr.getProperty( "DB_USER"),pr.getProperty( "DB_PASSWORD"));
            System.out.println("Successfully connected to the database");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return con;
    }

    public void closeConnection(ResultSet rs, CallableStatement cStmt, Connection con) {

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                /* ignored */
            }
        }
        if (cStmt != null) {
            try {
                cStmt.close();
            } catch (SQLException e) {
                /* ignored */
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                /* ignored */
            }
        }

    }

    public void closeConnection(CallableStatement cStmt, Connection con) {

        if (cStmt != null) {
            try {
                cStmt.close();
            } catch (SQLException e) {
                /* ignored */
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                /* ignored */
            }
        }

    }

}
