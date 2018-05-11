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
    
    private static java.sql.Date getCurrentDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }

    
    public void Insert(int taskID, long dateCreated) {
        // query block start
       
        long ts = System.currentTimeMillis();
        long resultTime = ts - dateCreated;
        int days = (int) (resultTime / (1000*60*60*24));

        int days2 = days / 100000;

        System.out.println("days: "+days + " days 2: "+days2);

        PreparedStatement stmtc = null;
        PreparedStatement stmt = null;

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

       
        //java.sql.Date sqlDate = new java.sql.Date(df.getTime());

        //df.format(currentDate);

        //System.out.println("date entry : "+df.format(currentDate));
       
        String update = "UPDATE tasks SET expectedDuration = "+days2+" WHERE taskID = "+taskID+"";
        String complete = "UPDATE tasks SET DateDue = "+getCurrentDate()+" WHERE taskID = "+taskID+"";
       
        System.out.println("current date: " + getCurrentDate());
       
        try {
            stmt = dbConn.prepareStatement(update); // create a statement
            stmt.executeUpdate(); // execute update statement
  
            int result = stmt.executeUpdate();
          
            if (result == 0) {
                System.err.println ("result set returned 0\n");
            }
          
        } catch(SQLException sqlex) {
              System.out.println(sqlex.getMessage());
              System.out.println("Duration update error\n");
        }
       
        try {
            stmtc = dbConn.prepareStatement(complete); // create a statement
            //stmtc.setDate(11, getCurrentDate());
            stmtc.executeUpdate(); // execute update statement
  
            int result = stmtc.executeUpdate();
          
            if (result == 0) {
                System.err.println ("result set returned 0\n");
            }
          
        } catch(SQLException sqlex) {
              System.out.println(sqlex.getMessage());
              System.out.println("Completion date update error\n");
        }
       
        finally {

        if (stmt != null) {
            try {
                stmt.close();
            } catch(SQLException sqlex) {
              System.out.println(sqlex.getMessage());
              System.out.println("Error closing statement\n");
            }
        }

        if (dbConn != null) {
            try {
                dbConn.close();
                } catch(SQLException sqlex) {
                System.out.println(sqlex.getMessage());
                System.out.println("Error closing DB connection after statement\n");
                }
            }
        }               
        JOptionPane.showMessageDialog(null, "Updated", "Task Completed", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void ListTasks(int input) {
       
        //String sql = "SELECT * FROM tasks WHERE Allocated = ?";
        //PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
        //preparedStatement.setString(1, myInput);
       
        Statement stmt = null;
        String query = "SELECT * FROM tasks WHERE AllocatedTo = "+input+"";
          
        String[] header = new String[] {"Task ID"
                ,"Allocated To"
                ,"Task Priority"
                ,"Signed Off"
                ,"Description"
                ,"Date Created"
                ,"Date Due"
                ,"Sign Off Level"
                ,"Demand"
                ,"Duration"
                ,"One off or Regular"};

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model = new DefaultTableModel(null,header);
       
        jTable1.setModel(model);
       

        try{
        stmt = dbConn.createStatement();   
        ResultSet rs = stmt.executeQuery(query);
       
        boolean moreRecords = rs.next ();
       
        if (!moreRecords) {
            return;
        }
       
        do {
            model.addRow(new String[] {
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
    
    public void tasksWorked() {
        // tasks worked
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
                rs.getString(10)}); 
    } while (rs.next ());
  
        } catch (SQLException e ) {
            System.err.println ("unsuccessful\n");
        }    
    }
   
    public void tasksOverdue() {
        // tasksoverdue
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
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText(">>");
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

        jButton3.setText("edit");
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

        jButton4.setText("worked");
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

        jLabel4.setText("Signed off by");

        jLabel6.setText("Completed");

        jLabel5.setText("Demand");

        jLabel7.setText("Duration");

        jLabel8.setText("test");
        jLabel8.setToolTipText("");

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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField8)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(6, 6, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 49, Short.MAX_VALUE))))
        );

        jButton6.setText("complete");
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
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton6)
                        .addGap(0, 66, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(71, 71, 71)
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton5)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton4)
                            .addComponent(jButton5))
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addGap(36, 36, 36)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton3)
                        .addComponent(jButton6)))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("panel");
        getAccessibleContext().setAccessibleDescription("");
        getAccessibleContext().setAccessibleParent(jButton1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // LOG:

        if(jTable1.getSelectedRow() == -1)
        {
            JOptionPane.showMessageDialog(null, "Select an employee", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            Integer allocatedTo = Integer.parseInt(jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 1).toString());

            System.out.println(allocatedTo);
           
            Statement stmt = null;
            String query = "SELECT * FROM tasks WHERE AllocatedTo = "+allocatedTo+"";
           
            try {
               
                stmt = dbConn.createStatement();   
                ResultSet rs = stmt.executeQuery(query);
               
                while (rs.next()) {
                    String TaskID = rs.getString("taskID");
                    String AllocatedTo = rs.getString("AllocatedTo");
                    String TaskPriority = rs.getString("Priority");
                    String SignedOffBy = rs.getString("SignedOffBy");
                    String TaskDesc = rs.getString("TaskDesc");
                    String TaskDateCreated = rs.getString("DateCreated");
                    String DateDue = rs.getString("DateDue");
                    String RequiredSignLvl = rs.getString("RequiredSignLvl");
                    String PhysDemRating = rs.getString("PhsyicalDemands");
                    String TaskDuration = rs.getString("expectedDuration");
                    String isNormal = rs.getString("isNormal");
                   
                    System.out.println(TaskID + "\t" + " " + AllocatedTo + " " + "\t" + TaskDateCreated);
                   
                
                   
                    jTextField1.setText(TaskID);
                    jTextField2.setText(AllocatedTo);
                    jTextField3.setText(TaskPriority);
                    jTextField4.setText(SignedOffBy);
                    jTextField5.setText(DateDue);

                    jTextField6.setText(PhysDemRating);
                    jTextField7.setText(TaskDuration);

                    jTextArea1.setText(TaskDesc);
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
        
        //if (input == 0) {
        //    JOptionPane.showMessageDialog(null,"input invalid", "Error", JOptionPane.ERROR_MESSAGE);
        //} else {
        //    ListTasks(input);
        //}
        
        ListTasks(input);
        //JPanel jpanel = new JPanel();
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setBounds(200, 100, 250, 300);              
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // EDIT:
        //ArrayList tasks = new ArrayList();
        DefaultListModel listModel = new DefaultListModel();
       
        Statement stmt = null;
        String query = "SELECT * FROM tasks";

        try {
        stmt = dbConn.createStatement();   
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            int TaskID = rs.getInt("taskID");
            int AllocatedTo = rs.getInt("Allocated");
            int TaskPriority = rs.getInt("Priority");
            int SignedOffBy = rs.getInt("SignOff");
            String TaskDesc = rs.getString("Description");
            String DateCreated2 = rs.getString("DateCreated");
            String SignOffLevel = rs.getString("SignOffLevel");
            float PhysDemRating = rs.getFloat("Demand");
            float TaskDuration = rs.getFloat("Duration");
           
            //listModel.addElement(TaskID + "\t" + AllocatedTo +
            //                "\t" + TaskPriority + "\t" + SignedOffBy +
            //                "\t" + TaskDesc + "\t" + DateCreated +
            //                "\t" + SignOffLevel + "\t" + PhysDemRating +
            //                "\t" + TaskDuration);
           
            listModel.addElement(TaskID + "\t" + " " + AllocatedTo + " " + "\t" + DateCreated);
           
            // set a variable to record allocated to, change to name, selected from list
            // action listener to change list on click           
        }
        //jList1.setModel(listModel);
        //System.out.println("Contents of tasks: " + tasks);
        // next int next string etc... get task ID / name?
    } catch (SQLException e ) {
        System.err.println ("unsuccessful\n");
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
        
        int testID = Integer.parseInt(jTextField1.getText());
        
        Statement completeStmt = null;
        String query = "SELECT DateCreated FROM tasks WHERE taskID = "+testID+"";
        
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
            System.out.println(sqlex.getMessage());
            System.out.println("error retrieving task complete from DB");
        }
        
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(DateCreated);
            dc = (date.getTime());
        } catch(Exception e) {
            System.out.println(e.getMessage());
            System.out.println("error parsing creation date");
        }
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables
}