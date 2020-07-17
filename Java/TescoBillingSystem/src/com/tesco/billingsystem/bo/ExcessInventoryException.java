/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tesco.billingsystem.bo;

/**
 *
 * @author Ramsat
 */
public class ExcessInventoryException extends Exception {

    public ExcessInventoryException(String s) {
        super(s);
       // System.out.println("IN USER DEF EXCEPTION");
    }
}
