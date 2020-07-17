/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tesco.billingsystem.gui;

import com.tesco.billingsystem.bo.EmployeeBO;
import java.sql.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Ramsat
 */
public class Login extends javax.swing.JFrame {

    EmployeeVO empVO = new EmployeeVO();
    EmployeeBO empBO = new EmployeeBO();

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        icno_txt = new javax.swing.JTextField();
        pwd_txt = new javax.swing.JTextField();
        submit_btn = new javax.swing.JButton();
        cancel_btn = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("IC Number");

        jLabel2.setText("Password");

        submit_btn.setText("SUBMIT");
        submit_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submit_btnActionPerformed(evt);
            }
        });

        cancel_btn.setText("Cancel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(icno_txt)
                            .addComponent(pwd_txt, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(submit_btn))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(cancel_btn)))
                .addContainerGap(139, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(icno_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(pwd_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(submit_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancel_btn)
                .addContainerGap(107, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //In runtime when you click on the submit button, this method will be invoked.
    //This is called event handling for Java GUI swing components.
    //This method is an "event" that is attached to the submit button.
    private void submit_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submit_btnActionPerformed

        if (empVO.getCounter() < 3) {

            String icno = icno_txt.getText();
            String pwd = pwd_txt.getText();

            empVO.setIcno(icno);
            empVO.setPwd(pwd);
            empVO.setCounter(empVO.getCounter());
            empBO.authenticate(empVO);

            if (empVO.isLoginFlag() == true) {
                //MainMenu mm = new MainMenu();
                MainMenu name = new MainMenu(empVO);
                name.setVisible(true);
                this.setVisible(false);

            } else {
                JOptionPane.showMessageDialog(rootPane, "Please Try Again");
                System.out.println("Salah ke-" + empVO.getCounter());

            }
        } else {
            String icno = icno_txt.getText();
            String pwd = pwd_txt.getText();

            empVO.setIcno(icno);
            empVO.setPwd(pwd);
            empVO.setDate(System.currentTimeMillis());
            empVO.setStatus(false);
            empBO.authenticate(empVO);
            //System.exit(0);
        }


    }//GEN-LAST:event_submit_btnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Login loginPage = new Login();
        loginPage.setVisible(true);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cancel_btn;
    private javax.swing.JTextField icno_txt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField pwd_txt;
    private javax.swing.JButton submit_btn;
    // End of variables declaration//GEN-END:variables

}
