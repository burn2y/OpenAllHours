/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capytecmaster;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Chris
 */
public class CaretakerChangePasswordUI extends javax.swing.JFrame {

    // variables declared
    private Integer empID;
    private CaretakerMainMenuGUI parent;
    Connection dbConn = connection.connect();
    Statement stmt = null;
    
    // constructor
    public CaretakerChangePasswordUI(Integer empID, CaretakerMainMenuGUI p) 
    {
        // variables set and GUI initialised
        parent = p;
        this.empID = empID;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        changePasswordLbl = new javax.swing.JLabel();
        currentPasswordLbl = new javax.swing.JLabel();
        newPasswordLbl = new javax.swing.JLabel();
        newPasswordConfirm = new javax.swing.JLabel();
        confirmBtn = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();
        currentPassword = new javax.swing.JPasswordField();
        newPassword = new javax.swing.JPasswordField();
        newPassword1 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        changePasswordLbl.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        changePasswordLbl.setText("Reset Password");

        currentPasswordLbl.setText("Password:");

        newPasswordLbl.setText("New Password:");
        newPasswordLbl.setToolTipText("");

        newPasswordConfirm.setText("New Password:");

        confirmBtn.setText("Confirm");
        confirmBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmBtnActionPerformed(evt);
            }
        });

        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(91, Short.MAX_VALUE)
                        .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(confirmBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(118, 118, 118)
                            .addComponent(changePasswordLbl))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(31, 31, 31)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(newPasswordLbl)
                                        .addComponent(newPasswordConfirm)
                                        .addComponent(currentPasswordLbl))
                                    .addGap(254, 254, 254))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(108, 108, 108)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(currentPassword)
                                        .addComponent(newPassword)
                                        .addComponent(newPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(changePasswordLbl)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentPasswordLbl)
                    .addComponent(currentPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newPasswordLbl)
                    .addComponent(newPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newPasswordConfirm)
                    .addComponent(newPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backBtn)
                    .addComponent(confirmBtn))
                .addGap(48, 48, 48))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // action listener of back button
    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        this.setVisible(false);
        parent.setVisible(true);
    }//GEN-LAST:event_backBtnActionPerformed

    // action litener of confirm button
    private void confirmBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmBtnActionPerformed

        // current password string declared
        String strCurrentPassword = null;
        // SQL string declared
        String getAllEmpsSql = "SELECT * FROM EMPLOYEE WHERE ID = " + empID;
        try
        {
            // statement created and executed
            stmt = dbConn.createStatement();   
            ResultSet rs = stmt.executeQuery(getAllEmpsSql);
            while(rs.next())
            {
                //current password obtained from results set
                strCurrentPassword = rs.getString("Password");
            }
        }
        catch(SQLException sqlex) 
        {
            // error catching
            System.out.println(sqlex.getMessage());
            System.out.println("Duration update error\n");
        }
        
        
        if(currentPassword.getText().equals(strCurrentPassword))
        {
            // continue if user enters correct current password
            if(newPassword.getText().equals(newPassword1.getText()))
            {
                // continue  if new passwords match
                if(newPassword.getText().length() > 3)
                {
                    // continue if new password entered is longer than three characters
                    
                    // SQL string to update users password created
                    String sqlUpdatePassword = "UPDATE EMPLOYEE SET Password = '" + newPassword.getText() + "' WHERE ID = "  + empID;
                    try
                    {
                        // create and execute statement
                        stmt = dbConn.createStatement(); 
                        stmt.executeUpdate(sqlUpdatePassword); 
                    }
                    catch(SQLException sqlex) 
                    {
                        // error catching
                        System.out.println(sqlex.getMessage());
                        System.out.println("Duration update error\n");
                    }
                    // message to user. Password changed
                    JOptionPane.showMessageDialog(this, "Your password has been changed", "Done", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    // else show error message
                    JOptionPane.showMessageDialog(this, "Your password must be longer than 3 characters long1", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                // else show error message
                JOptionPane.showMessageDialog(this, "The new passwords you entered don't match", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            // else show error message
            JOptionPane.showMessageDialog(this, "The password you entered is incorrect", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_confirmBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JLabel changePasswordLbl;
    private javax.swing.JButton confirmBtn;
    private javax.swing.JPasswordField currentPassword;
    private javax.swing.JLabel currentPasswordLbl;
    private javax.swing.JPasswordField newPassword;
    private javax.swing.JPasswordField newPassword1;
    private javax.swing.JLabel newPasswordConfirm;
    private javax.swing.JLabel newPasswordLbl;
    // End of variables declaration//GEN-END:variables
}
