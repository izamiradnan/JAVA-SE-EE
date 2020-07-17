/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tesco.billingsystem.bo;

import com.tesco.billingsystem.dao.EmployeeDAO;
import com.tesco.billingsystem.gui.EmployeeVO;
import java.util.ArrayList;

/**
 *
 * @author Ramsat
 */
public class EmployeeBO {

    public EmployeeVO authenticate(EmployeeVO empVO) {

        EmployeeDAO empDAO = new EmployeeDAO();
        empDAO.getEmployee(empVO);

        return empVO;

    }

    public void deleteEmployee(EmployeeVO empVO) {
        EmployeeDAO empDAO = new EmployeeDAO();
        empDAO.deleteEmployee(empVO);
    }

    public void updateEmployeeName(EmployeeVO empVO) {
        EmployeeDAO empDAO = new EmployeeDAO();
        empDAO.updateEmployee(empVO);
    }

    public void createNewEmployee(EmployeeVO empVO) {
        EmployeeDAO empDAO = new EmployeeDAO();
        empDAO.createEmployee(empVO);
    }

    public EmployeeVO displayAllEmployee(EmployeeVO empVO) {
        EmployeeDAO empDAO = new EmployeeDAO();
        ArrayList<EmployeeVO> list = new ArrayList<EmployeeVO>();
        empDAO.displayEmployee(empVO);
        
        return empVO;
    }

    public void validate(int total) throws ExcessInventoryException {
//        System.out.println("IN BO VALIDATE");
//        System.out.println(total);
        if (total >= 6){
            //System.out.println("eroor caught");
            throw new ExcessInventoryException("error dpt tangkap");
        }else{
            System.out.println("ok");
        }
    }

}
