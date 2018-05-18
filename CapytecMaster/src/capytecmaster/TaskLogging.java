package capytecmaster;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Date;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author steve
 */

public class TaskLogging extends javax.swing.JFrame {

    /**
     * Creates new form TaskLogging
     */
    
    public String DateCreated;
    long dc;
    
    Connection dbConn = connection.connect();   
    
    public TaskLogging() {
        initComponents();
    }
    
    public void Insert(int taskID, long dateCreated) {
        // query block start
        long ts = System.currentTimeMillis();
        long resultTime = ts - dateCreated;
        int days = (int) (resultTime / (1000*60*60*24));
        
        System.out.println("days: " + days);
        
        java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
        
        PreparedStatement sqlUpdate = null;
  
        try {
            sqlUpdate = dbConn.prepareStatement("UPDATE tasks SET "
                    + "Duration = ?, "
                    + "Completed = ? "
                    + "WHERE ID = ?"); // create a statement
            
            sqlUpdate.setInt(1,days);
            sqlUpdate.setDate(2,sqlDate);
            sqlUpdate.setInt(3,taskID);
             
            sqlUpdate.executeUpdate(); // execute update statement
        } catch(SQLException sqlex) {
            System.out.println(sqlex.getMessage());
            System.out.println("Completion date update error\n");
        } finally {
            JOptionPane.showMessageDialog(null, "Updated", 
                                        "Task Completed", JOptionPane.INFORMATION_MESSAGE);
        } 
    }
    
    public void ListTasks(int input) {
        // LIST TASKS:
        // List tasks bound to specific employee

        Statement stmt = null;
        String query = "SELECT * FROM tasks WHERE Allocated = "+input+"";
          
        String[] header = new String[] {"Task ID"
                ,"Allocated To"
                ,"Task Priority"
                ,"Signed Off"
                ,"Description"
                ,"Sign Off Level"
                ,"Demand"
                ,"Duration"
                ,"Normal Task?"
                ,"Date Created"
                ,"Due Date"
                ,"Completed"};

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model = new DefaultTableModel(null,header);
       
        jTable1.setModel(model);
       
        try {
            stmt = dbConn.createStatement();   
            ResultSet rs = stmt.executeQuery(query);
       
            boolean moreRecords = rs.next ();
       
            if (!moreRecords) {
                return;
            }
       
            do {
                model.addRow(new String[] {
                    rs.getString("ID") + "",
                    rs.getString("Allocated") + "",
                    rs.getString("Priority") + "",
                    rs.getString("SignOff")+ "",
                    rs.getString("Description")+ "",
                    rs.getString("SignOffLevel")+ "",
                    rs.getString("Demand")+ "",
                    rs.getString("Duration")+ "",
                    rs.getString("isNormal")+ "",
                    rs.getString("DateCreated")+ "",
                    rs.getString("DueDate")+ "",
                    rs.getString("Completed")}); 
            } while (rs.next ());
  
        } catch (SQLException e ) {
            System.err.println ("unsuccessful\n");  
        }         
    }
    
    public void tasksWorked() {
        // tasks worked
        jPanel1.setVisible(true);
        String[] header = new String[] {
                "Task ID"
                ,"Allocated To"
                ,"Task Priority"
                ,"Signed Off"
                ,"Description"
                ,"Date Created"
                ,"Sign Off Level"
                ,"Demand"
                ,"Duration"
                ,"Normal Task?"
                ,"Due Date"
                ,"Completed"};

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model = new DefaultTableModel(null,header);
       
        jTable1.setModel(model);
       
        Statement stmt = null;
        String query = "SELECT * FROM tasks WHERE Completed IS NOT NULL";
       
        try{
        stmt = dbConn.createStatement();   
        ResultSet rs = stmt.executeQuery(query);
       
        boolean moreRecords = rs.next ();
       
        if (!moreRecords) {
            return;
        }
       
        do {
            model.addRow(new String[] {
                rs.getString(1) + "",
                rs.getString(2) + "",
                rs.getString(3) + "",
                rs.getString(4)+ "",
                rs.getString(5)+ "",
                rs.getString(6)+ "",
                rs.getString(7)+ "",
                rs.getString(8)+ "",
                rs.getString(9)+ "",
                rs.getString(10)+ "",
                rs.getString(11)+ "",
                rs.getString(12)}); 
    } while (rs.next ());
  
        } catch (SQLException e ) {
            System.err.println ("unsuccessful\n");
        }    
    }
   
    public void tasksOverdue() {
        // tasksoverdue
        jPanel1.setVisible(false);
        String[] header = new String[] {
            "Task ID",
            "Allocated To",
            "Task Priority",
            "Sign Off",
            "Description",
            "Date Created",
            "Sign Off Level",
            "Demand"};

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model = new DefaultTableModel(null,header);
       
        jTable1.setModel(model);

        Statement stmt = null;
        String query = "SELECT * FROM tasks WHERE Completed IS NULL";
       
        try{
        stmt = dbConn.createStatement();   
        ResultSet rs = stmt.executeQuery(query);
       
        boolean moreRecords = rs.next ();
       
        if (!moreRecords) {
            return;
        }   
           
        do {
            model.addRow(new String[] {
                rs.getInt(1) + "",
                rs.getString(2) + "",
                rs.getString(3)+ "",
                rs.getString(4)+ "",
                rs.getString(5)+ "",
                rs.getString(6)+ "",
                rs.getString(7)+ "",
                rs.getString(8)});
        } while (rs.next ());
  
        } catch (SQLException e ) {
            System.err.println ("unsuccessful\n");
        }    
    } 
       
    /**
     * This method is called from within the constructor to initialise the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText(">>");
        jButton1.setName("select"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("find");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Edit Task");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton4.setText("completed");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("overdue");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(153, 255, 153));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jLabel1.setText("Task ID");

        jLabel2.setText("Allocated to");

        jLabel3.setText("Priority");

        jLabel4.setText("Signed Off By");

        jLabel6.setText("Completed");

        jLabel5.setText("Demand");

        jLabel7.setText("Duration");

        jLabel8.setText("Sign Off Level");

        jLabel9.setText("Due Date");

        jLabel10.setText("Task Type");

        jLabel11.setText("Date Created");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                                    .addComponent(jTextField2)
                                    .addComponent(jTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                                    .addComponent(jTextField11))))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField10, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                                    .addComponent(jTextField9)
                                    .addComponent(jTextField5)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(31, 31, 31)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(8, 8, 8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 41, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        jButton6.setText("Complete Task");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1044, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(71, 71, 71)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)
                        .addContainerGap(160, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        getAccessibleContext().setAccessibleName("panel");
        getAccessibleContext().setAccessibleDescription("");
        getAccessibleContext().setAccessibleParent(jButton1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // LOG:
        if(jTable1.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Select an employee", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            Integer allocatedTo = Integer.parseInt(jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 1).toString());

            System.out.println(allocatedTo);
           
            Statement stmt = null;
            String query = "SELECT * FROM tasks WHERE Allocated = "+allocatedTo+"";
           
            try {
               
                stmt = dbConn.createStatement();   
                ResultSet rs = stmt.executeQuery(query);
               
                while (rs.next()) {
                    String ID = rs.getString("ID");
                    String Allocated = rs.getString("Allocated");
                    String Priority = rs.getString("Priority");
                    String SignedOffBy = rs.getString("SignOff");
                    String Description = rs.getString("Description");
                    String DateTaskCreated = rs.getString("DateCreated");
                    String SignOffLevel = rs.getString("SignOffLevel");
                    String Demand = rs.getString("Demand");
                    String Duration = rs.getString("Duration");
                    String Completed = rs.getString("Completed");
                    String DueDate = rs.getString("DueDate");
                    String isNormal = rs.getString("isNormal");
                   
                    System.out.println(ID + "\t" + " " + Allocated + " " + "\t" + DateTaskCreated);
                   
                    jTextField1.setText(ID);
                    jTextField2.setText(Allocated);
                    jTextField3.setText(Priority);
                    jTextField4.setText(SignedOffBy);
                    jTextField5.setText(Completed);
                    jTextField11.setText(DateTaskCreated);

                    jTextField6.setText(Demand);
                    jTextField7.setText(Duration);
                    jTextField8.setText(SignOffLevel);
                    jTextField9.setText(DueDate);
                    jTextField10.setText(isNormal);

                    jTextArea1.setText(Description);
                }
            } catch (SQLException e ) {
                System.err.println ("unsuccessful\n");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // FIND:
        // seperate pop up box to find allocated to - completed or not completed (button)
        // SELECT * FROM tasks WHERE ALLOCATED = (variable inserted - int)
        // verify insert - int only
       
        int input;
        input = Integer.parseInt(JOptionPane.showInputDialog("Worker ID: "));
        
        //VALIDATION TO DO!!!!!!!!!!
        
        if (input == 0) {
            JOptionPane.showMessageDialog(null,"input invalid", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            ListTasks(input);
        }
            
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // EDIT: 
        // Update the chosen task using information entered into the 
        // form underneath the table, after retrieving the data from the
        // text fields a prepared statement is used to test and enter
        // into the database
        
        PreparedStatement sqlUpdate = null;
        
        try { //driver & connects to the DB engine.
        sqlUpdate = dbConn.prepareStatement ("UPDATE tasks SET Allocated = ?, "
                + "Priority = ?, "
                + "SignOff = ?, "
                + "Description = ?, "
                + "DateCreated = ?, "
                + "SignOffLevel = ?, "
                + "Demand = ?, "
                + "Duration = ?, "
                + "Completed = ?, "
                + "DueDate = ?, "
                + "isNormal = ?" + " "
                + "WHERE ID = ?");
        
        sqlUpdate.setString(1, jTextField2.getText());
        sqlUpdate.setString(2, jTextField3.getText());
        sqlUpdate.setString(3, jTextField4.getText());
        sqlUpdate.setString(4, jTextArea1.getText());
        sqlUpdate.setString(5, jTextField11.getText());
        sqlUpdate.setString(6, jTextField8.getText());
        sqlUpdate.setString(7, jTextField6.getText());
        sqlUpdate.setString(8, jTextField7.getText());
        sqlUpdate.setString(9, jTextField5.getText());
        sqlUpdate.setString(10, jTextField9.getText());
        sqlUpdate.setString(11, jTextField10.getText());
        sqlUpdate.setInt(12, Integer.parseInt(jTextField1.getText())); 
        
        //int result = sqlUpdate.executeUpdate();
        
        sqlUpdate.executeUpdate();

        } catch (SQLException sqlex) {
            System.err.println ("SQL Exception on EDIT");
            //sqlex.printStackTrace();
        } finally {
            JOptionPane.showMessageDialog(null, "Task record Updated", "Updated", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // WORKED
        tasksWorked();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // OVERDUE
        tasksOverdue();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // insert, pass values to function for validation?

        Integer testID = Integer.parseInt(jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 1).toString());
        
        Statement completeStmt = null;
        String query = "SELECT DateCreated FROM tasks WHERE ID = "+testID+"";
        
        try{
        completeStmt = dbConn.createStatement();    
        ResultSet rs = completeStmt.executeQuery(query);
            while (rs.next()) {
                //String DateCreatedDB = rs.getString("DateCreated");
                //System.out.println("Date Created: " + DateCreated );
                String DateCreatedDB = rs.getString("DateCreated");
                DateCreated = DateCreatedDB;
            }
            //completionDate = DateCreated;
        } catch(SQLException sqlex) {
            System.err.println(sqlex.getMessage());
            System.err.println("error retrieving task complete from DB");
        }
        
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(DateCreated);
            dc = (date.getTime());
        } catch(Exception e) {
            System.err.println(e.getMessage());
            System.err.println("error parsing creation date");
        }
        System.out.println("testID: " +testID);
        Insert(testID,dc);
    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * @param args the command line arguments
     */

    public static void run() {
        new TaskLogging().setVisible(true);
    }
      
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}