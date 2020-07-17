/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tesco.billingsystem.dao;

import com.tesco.billingsystem.gui.EmployeeVO;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EmployeeDAO extends DBManager {

    ResultSet rs = null;
    CallableStatement cStmt = null;

    public EmployeeVO getEmployee(EmployeeVO empVO) {
//        System.out.println(empVO.getIcno());
//        System.out.println(empVO.getPwd());

        try {
            driver();
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            System.out.println("Successfully loaded databse driver");
            connection();
//            Connection con;
//            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Tesco", "sa", "123456");
//            System.out.println("Successfully connected to the database");

            cStmt = connection().prepareCall("{call dbo.loginEmployee(?,?)}");
            cStmt.setString(1, empVO.getIcno());
            cStmt.setString(2, empVO.getPwd());
            rs = cStmt.executeQuery();
            System.out.println("Successfully executed SP: dbo.loginEmployee");

            if (rs.next() == true) {
                System.out.println("Successfully authentication");
                empVO.setLoginFlag(true);
                empVO.setName(rs.getString("name"));
            } else {

                if (empVO.isStatus() == true) {
                    System.out.println("Failed authentication");
                    empVO.setLoginFlag(false);
                    empVO.setCounter(empVO.getCounter() + 1);
                } else {
                    cStmt = connection().prepareCall("{call dbo.blockEmployee(?,?)}");
                    cStmt.setBoolean(1, false);
                    //cStmt.setLong(2, empVO.getDate());
                    

                    rs = cStmt.executeQuery();
                    System.out.println("Successfully executed SP: dbo.blockEmployee");
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(rs, cStmt, connection());
        }
        return empVO;
    }

    public void deleteEmployee(EmployeeVO empVO) {

        try {
            driver();
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            System.out.println("Successfully loaded databse driver");
            connection();
//            Connection con;
//            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Tesco", "sa", "123456");
//            System.out.println("Successfully connected to the database");

            //String sql = "DELETE FROM dbo.employee WHERE icno = "+empVO.getIcno();
            cStmt = connection().prepareCall("{call dbo.deleteEmployee(?)}");
            //String sql = "DELETE FROM dbo.employee WHERE icno = ICNO";
            cStmt.setString(1, empVO.getIcno());
            int result = cStmt.executeUpdate();
            System.out.println(result);

            if (result == 1) {
                //JOptionPane.showMessageDialog(rootPane, "Successfully deleted");
                System.out.println("Successfully deleted");
            } else {
                System.out.println("Failed deleted");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(cStmt, connection());
        }
    }

    public void updateEmployee(EmployeeVO empVO) {
        try {
            driver();

            connection();

            //String sql = "DELETE FROM dbo.employee WHERE icno = "+empVO.getIcno();
            cStmt = connection().prepareCall("{call dbo.updateEmployee(?,?)}");
            //String sql = "DELETE FROM dbo.employee WHERE icno = ICNO";
            cStmt.setString(1, empVO.getIcno());
            cStmt.setString(2, empVO.getName());
            int result = cStmt.executeUpdate();
            System.out.println(result);

            if (result == 1) {
                System.out.println("Successfully updated");
            } else {
                System.out.println("Failed updated");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(cStmt, connection());

        }
    }

    public void createEmployee(EmployeeVO empVO) {

        try {

            driver();

            connection();

            //String sql = "DELETE FROM dbo.employee WHERE icno = "+empVO.getIcno();
            cStmt = connection().prepareCall("{call dbo.createEmployee(?,?,?)}");
            //String sql = "DELETE FROM dbo.employee WHERE icno = ICNO";
            cStmt.setString(1, empVO.getIcno());
            cStmt.setString(2, empVO.getPwd());
            cStmt.setString(3, empVO.getName());
            int result = cStmt.executeUpdate();
            System.out.println(result);

            if (result == 1) {
                System.out.println("Successfully created");
            } else {
                System.out.println("Failed create");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(cStmt, connection());

        }

    }

    public EmployeeVO displayEmployee(EmployeeVO empVO) {

        ArrayList<EmployeeVO> list = new ArrayList<EmployeeVO>();

        try {
            driver();
            connection();
            cStmt = connection().prepareCall("{call dbo.displayEmployee()}");
            //String sql = "DELETE FROM dbo.employee WHERE icno = ICNO";   

            rs = cStmt.executeQuery();
            System.out.println("Successfully executed SP: dbo.displayEmployee");

            //System.out.println(rs+"RS");
            while (rs.next() == true) {
                EmployeeVO vo = new EmployeeVO();
                vo.setIcno(rs.getString("icno"));
                vo.setPwd(rs.getString("pwd"));
                vo.setName(rs.getString("name"));
                list.add(vo);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(rs, cStmt, connection());

        }

        empVO.setList(list);

        return empVO;
    }

}
