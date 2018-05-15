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
import java.util.Iterator;
import javax.swing.JOptionPane;

public class RemoveEmployeeUI extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    private AdminMainMenuUI parent;
    Connection dbConn = connection.connect();
    Statement stmt = null;
    
    public RemoveEmployeeUI(AdminMainMenuUI p) {
        parent = p;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        title = new javax.swing.JLabel();
        lblEmpID = new javax.swing.JLabel();
        empNumber = new javax.swing.JTextField();
        back = new javax.swing.JButton();
        delete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        title.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        title.setText("Remove Employee");

        lblEmpID.setText("Employee Number: ");

        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        delete.setText("Remove Employee");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(title)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblEmpID)
                            .addGap(18, 18, 18)
                            .addComponent(empNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(title)
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmpID)
                    .addComponent(empNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(back)
                    .addComponent(delete))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {   
        
        ArrayList<Employee> empList = new ArrayList<>();
        
        String getAllEmpsSql = "SELECT * FROM EMPLOYEE";
             try
            {
                stmt = dbConn.createStatement();   
                ResultSet rs = stmt.executeQuery(getAllEmpsSql);
                while(rs.next())
                {
                    Employee emp = new Employee(rs.getString("Firstname"), 
                            rs.getString("Surname"), rs.getString("DateOfBirth"),
                            rs.getString("Gender"), rs.getString("EmploymentType"),
                            rs.getString("Email"), rs.getString("NINO"), 
                            rs.getString("AddressLine1"), 
                            rs.getString("AddressLineTwo"), rs.getString("City"), 
                            rs.getString("County"), rs.getString("Postcode"),
                            rs.getInt("ID") ,rs.getString("Password"));
                    empList.add(emp);
                }
            }
            catch(SQLException sqlex) {
              System.out.println(sqlex.getMessage());
              System.out.println("Duration update error\n");
        }
             
        Integer i = 0;
        for(Employee emp : empList)
        {
            if(empNumber.getText().equals(emp.getSysEmpID().toString()))
            {
                i = 1;
            }
        }
        
        if(i == 0)
        {
            JOptionPane.showMessageDialog(null, "The employee number you entered does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            if(empList.size() == 1)
            {
                JOptionPane.showMessageDialog(null, "There is only one employee in the system!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                Integer empID = Integer.parseInt(empNumber.getText());
                String sqlRemove = "DELETE * FROM EMPLOYEE WHERE ID = " + empID;
            
                try
                {
                    stmt = dbConn.createStatement(); // create a statement
                    stmt.executeUpdate(sqlRemove); // execute update statement
                }
                catch(SQLException sqlex) 
                {
                    System.out.println(sqlex.getMessage());
                    System.out.println("Duration update error\n");
                }
            
                JOptionPane.showMessageDialog(null, "Employee number " + empID + " removed." , "Done", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
     
    }                                      

    private void backActionPerformed(java.awt.event.ActionEvent evt) {                                     
        this.setVisible(false);
        parent.setVisible(true);
    }                                    

    // Variables declaration - do not modify                     
    private javax.swing.JButton back;
    private javax.swing.JButton delete;
    private javax.swing.JTextField empNumber;
    private javax.swing.JLabel lblEmpID;
    private javax.swing.JLabel title;
    // End of variables declaration                   
}