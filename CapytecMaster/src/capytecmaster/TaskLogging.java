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
       
        //String sql = "SELECT * FROM tasks WHERE Allocated = ?";
        //PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
        //preparedStatement.setString(1, myInput);
       
        Statement stmt = null;
        String query = "SELECT * FROM tasks WHERE Allocated = "+input+"";
          
        String[] header = new String[] {"Task ID"
                ,"Allocated To"
                ,"Task Priority"
                ,"Signed Off"
                ,"Description"
                ,"Sign Off Level"
                ,"Demand"
                ,"Task Type"
                ,"Date Created"
                ,"Due Date"
                ,"Completed"
                ,"Duration"};

        DefaultTableModel model = (DefaultTableModel) taskTable.getModel();
        model = new DefaultTableModel(null,header);
       
        taskTable.setModel(model);
       
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
                    rs.getString("isNormal")+ "",
                    rs.getString("DateCreated")+ "",
                    rs.getString("DueDate")+ "",
                    rs.getString("Completed")+ "",
                    rs.getString("Duration")}); 
            } while (rs.next ());
  
        } catch (SQLException sqlex ) {
            System.err.println(sqlex.getMessage());
            System.err.println ("Error listing tasks"); 
        }         
    }
    
    public void tasksWorked() {
        // tasks worked
        editPanel.setVisible(true);
        carryOver.setVisible(true);
        updateButton.setVisible(true);
        completeButton.setVisible(false);
        
        String[] header = new String[] {
                "Task ID"
                ,"Allocated To"
                ,"Task Priority"
                ,"Signed Off"
                ,"Description"
                ,"Sign Off Level"
                ,"Demand"
                ,"Task Type"
                ,"Date Created"
                ,"Due Date"
                ,"Completed"
                ,"Duration"};

        DefaultTableModel model = (DefaultTableModel) taskTable.getModel();
        model = new DefaultTableModel(null,header);
       
        taskTable.setModel(model);
       
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
                rs.getString("ID") + "",
                rs.getString("Allocated") + "",
                rs.getString("Priority") + "",
                rs.getString("SignOff")+ "",
                rs.getString("Description")+ "",
                rs.getString("SignOffLevel")+ "",
                rs.getString("Demand")+ "",
                rs.getString("isNormal")+ "",
                rs.getString("DateCreated")+ "",
                rs.getString("DueDate")+ "",
                rs.getString("Completed")+ "",
                rs.getString("Duration")}); 
    } while (rs.next ());
  
        } catch (SQLException sqlex ) {
            System.err.println(sqlex.getMessage());
            System.err.println ("Error displaying worked tasks");
        }    
    }
    
    public void tasksDue() {
        // tasksoverdue
        carryOver.setVisible(false);
        updateButton.setVisible(false);
        editPanel.setVisible(false);
        completeButton.setVisible(true);
        
        String[] header = new String[] {
            "Task ID",
            "Allocated To",
            "Task Priority",
            "Sign Off",
            "Description",
            "Sign Off Level",
            "Demand",
            "Task Type",
            "Date Created",
            "Due Date"};

        DefaultTableModel model = (DefaultTableModel) taskTable.getModel();
        model = new DefaultTableModel(null,header);
       
        taskTable.setModel(model);

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
                rs.getString("ID") + "",
                rs.getString("Allocated") + "",
                rs.getString("Priority") + "",
                rs.getString("SignOff")+ "",
                rs.getString("Description")+ "",
                rs.getString("SignOffLevel")+ "",
                rs.getString("Demand")+ "",
                rs.getString("isNormal")+ "",
                rs.getString("DateCreated")+ "",
                rs.getString("DueDate")}); 
            } while (rs.next ());
  
        } catch (SQLException sqlex ) {
            System.err.println(sqlex.getMessage());
            System.err.println ("Error displaying due tasks");
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

        carryOver = new javax.swing.JButton();
        findButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        taskTable = new javax.swing.JTable();
        completedButton = new javax.swing.JButton();
        overdueButton = new javax.swing.JButton();
        editPanel = new javax.swing.JPanel();
        taskTextField = new javax.swing.JTextField();
        allocateTextField = new javax.swing.JTextField();
        prioTextField = new javax.swing.JTextField();
        SignOffTextField = new javax.swing.JTextField();
        completedTextField = new javax.swing.JTextField();
        demandTextField = new javax.swing.JTextField();
        durationTextField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        DescArea = new javax.swing.JTextArea();
        idLabel = new javax.swing.JLabel();
        allocateLabel = new javax.swing.JLabel();
        prioLabel = new javax.swing.JLabel();
        signOffLabel = new javax.swing.JLabel();
        CompletedLabel = new javax.swing.JLabel();
        DemandLabel = new javax.swing.JLabel();
        DurationLabel = new javax.swing.JLabel();
        SignLevelTextField = new javax.swing.JTextField();
        SignLevelLabel = new javax.swing.JLabel();
        DueDateTextField = new javax.swing.JTextField();
        taskTypeTextField = new javax.swing.JTextField();
        DueDateLabel = new javax.swing.JLabel();
        TaskTypeLabel = new javax.swing.JLabel();
        DateCreatedLabel = new javax.swing.JLabel();
        dateCreateTextField = new javax.swing.JTextField();
        completeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        carryOver.setText(">>");
        carryOver.setName("select"); // NOI18N
        carryOver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carryOverActionPerformed(evt);
            }
        });

        findButton.setText("find");
        findButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findButtonActionPerformed(evt);
            }
        });

        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        taskTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(taskTable);

        completedButton.setText("completed");
        completedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                completedButtonActionPerformed(evt);
            }
        });

        overdueButton.setText("overdue");
        overdueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                overdueButtonActionPerformed(evt);
            }
        });

        editPanel.setBackground(new java.awt.Color(153, 255, 153));

        DescArea.setColumns(20);
        DescArea.setRows(5);
        jScrollPane2.setViewportView(DescArea);

        idLabel.setText("Task ID");

        allocateLabel.setText("Allocated to");

        prioLabel.setText("Priority");

        signOffLabel.setText("Signed Off By");

        CompletedLabel.setText("Completed");

        DemandLabel.setText("Demand");

        DurationLabel.setText("Duration");

        SignLevelLabel.setText("Sign Off Level");

        DueDateLabel.setText("Due Date");

        TaskTypeLabel.setText("Task Type");

        DateCreatedLabel.setText("Date Created");

        javax.swing.GroupLayout editPanelLayout = new javax.swing.GroupLayout(editPanel);
        editPanel.setLayout(editPanelLayout);
        editPanelLayout.setHorizontalGroup(
            editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editPanelLayout.createSequentialGroup()
                        .addGroup(editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(editPanelLayout.createSequentialGroup()
                                .addComponent(idLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                                .addComponent(taskTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(editPanelLayout.createSequentialGroup()
                                .addGroup(editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(allocateLabel)
                                    .addComponent(prioLabel)
                                    .addComponent(signOffLabel)
                                    .addComponent(SignLevelLabel))
                                .addGap(18, 18, 18)
                                .addGroup(editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(prioTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                                    .addComponent(allocateTextField)
                                    .addComponent(SignLevelTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                                    .addComponent(SignOffTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                                    .addComponent(dateCreateTextField))))
                        .addGap(28, 28, 28)
                        .addGroup(editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(editPanelLayout.createSequentialGroup()
                                .addGroup(editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TaskTypeLabel)
                                    .addComponent(CompletedLabel)
                                    .addComponent(DueDateLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(taskTypeTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                                    .addComponent(DueDateTextField)
                                    .addComponent(completedTextField)))
                            .addGroup(editPanelLayout.createSequentialGroup()
                                .addComponent(DurationLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(durationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(editPanelLayout.createSequentialGroup()
                                .addComponent(DemandLabel)
                                .addGap(31, 31, 31)
                                .addComponent(demandTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(8, 8, 8))
                    .addGroup(editPanelLayout.createSequentialGroup()
                        .addComponent(DateCreatedLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );
        editPanelLayout.setVerticalGroup(
            editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editPanelLayout.createSequentialGroup()
                        .addGroup(editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(taskTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idLabel)
                            .addComponent(DemandLabel)
                            .addComponent(demandTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(editPanelLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(allocateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(allocateLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(prioTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(prioLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(SignOffTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(signOffLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(SignLevelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(SignLevelLabel)))
                            .addGroup(editPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(durationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(DurationLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(completedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CompletedLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(DueDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(DueDateLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(taskTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TaskTypeLabel))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DateCreatedLabel)
                            .addComponent(dateCreateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 41, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        completeButton.setText("Complete Task");
        completeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                completeButtonActionPerformed(evt);
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
                        .addComponent(carryOver))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(findButton)
                        .addGap(71, 71, 71)
                        .addComponent(completedButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(overdueButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(editPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(completeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(updateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(findButton)
                    .addComponent(completedButton)
                    .addComponent(overdueButton))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(carryOver))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(updateButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(completeButton)
                        .addContainerGap(160, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(editPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        getAccessibleContext().setAccessibleName("panel");
        getAccessibleContext().setAccessibleDescription("");
        getAccessibleContext().setAccessibleParent(carryOver);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void carryOverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carryOverActionPerformed
        // LOG:
        if(taskTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Select an employee", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            Integer allocatedTo = Integer.parseInt(taskTable.getModel().getValueAt(taskTable.getSelectedRow(), 1).toString());

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
                   
                    taskTextField.setText(ID);
                    allocateTextField.setText(Allocated);
                    prioTextField.setText(Priority);
                    SignOffTextField.setText(SignedOffBy);
                    completedTextField.setText(Completed);
                    dateCreateTextField.setText(DateTaskCreated);
                    demandTextField.setText(Demand);
                    durationTextField.setText(Duration);
                    SignLevelTextField.setText(SignOffLevel);
                    DueDateTextField.setText(DueDate);
                    taskTypeTextField.setText(isNormal);

                    DescArea.setText(Description);
                }
            } catch (SQLException sqlex ) {
                System.err.println(sqlex.getMessage());
                System.err.println("Error retrieving task from DB");
            }
        }
    }//GEN-LAST:event_carryOverActionPerformed

    private void findButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findButtonActionPerformed
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
        
        //A DO WHILE FOR THE DIALOG BOX ^        
    }//GEN-LAST:event_findButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        // EDIT: 
        // Update the chosen task using information entered into the 
        // form underneath the table, after retrieving the data from the
        // text fields a prepared statement is used to test and enter
        // into the database
        
        try {
            // validate input - empty fields
            if (allocateTextField.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Allocated can not be null", 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (prioTextField.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Allocated can not be null", 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (SignOffTextField.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Allocated can not be null", 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (DescArea.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Allocated can not be null", 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (dateCreateTextField.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Allocated can not be null", 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (SignLevelTextField.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Allocated can not be null", 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (demandTextField.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Allocated can not be null", 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (durationTextField.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Allocated can not be null", 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (completedTextField.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Allocated can not be null", 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (DueDateTextField.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Allocated can not be null", 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (taskTypeTextField.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Allocated can not be null", 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (taskTextField.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Allocated can not be null", 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            try {
                // validate input - number only format
                int a = Integer.parseInt(allocateTextField.getText());
                int p = Integer.parseInt(prioTextField.getText());
                int s = Integer.parseInt(SignOffTextField.getText());
                int de = Integer.parseInt(demandTextField.getText());
                int du = Integer.parseInt(durationTextField.getText());
                int t = Integer.parseInt(taskTypeTextField.getText());
            }catch(NumberFormatException e){
                System.err.println(e.getMessage());
                System.err.println("Number format exception");
            }

        } catch(Exception e){
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(null,"Problem with field formats", "Error", JOptionPane.ERROR_MESSAGE);
        } 
        
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

        sqlUpdate.setString(1, allocateTextField.getText());
        sqlUpdate.setString(2, prioTextField.getText());
        sqlUpdate.setString(3, SignOffTextField.getText());
        sqlUpdate.setString(4, DescArea.getText());
        sqlUpdate.setString(5, dateCreateTextField.getText());
        sqlUpdate.setString(6, SignLevelTextField.getText());
        sqlUpdate.setString(7, demandTextField.getText());
        sqlUpdate.setString(8, durationTextField.getText());
        sqlUpdate.setString(9, completedTextField.getText());
        sqlUpdate.setString(10, DueDateTextField.getText());
        sqlUpdate.setString(11, taskTypeTextField.getText());
        sqlUpdate.setInt(12, Integer.parseInt(taskTextField.getText())); 
        
        //int result = sqlUpdate.executeUpdate();
        
        sqlUpdate.executeUpdate();

        } catch (SQLException sqlex) {
            System.err.println(sqlex.getMessage());
            System.err.println("SQL Exception on update");
            //sqlex.printStackTrace();
        } finally {
            JOptionPane.showMessageDialog(null, "Task record Updated", "update success", JOptionPane.PLAIN_MESSAGE);
        }
        
    }//GEN-LAST:event_updateButtonActionPerformed

    private void completedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_completedButtonActionPerformed
        // call tasks worked on click
        tasksWorked();
    }//GEN-LAST:event_completedButtonActionPerformed

    private void overdueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_overdueButtonActionPerformed
        // call tasks due on click
        tasksDue();
    }//GEN-LAST:event_overdueButtonActionPerformed

    private void completeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_completeButtonActionPerformed
        // prepare 

    Integer testID = Integer.parseInt(taskTable.getModel().getValueAt(taskTable.getSelectedRow(), 0).toString());
        
    Statement completeStmt = null;
    String query = "SELECT DateCreated FROM tasks WHERE ID = "+testID+"";
        
    try {
        completeStmt = dbConn.createStatement();    
        ResultSet rs = completeStmt.executeQuery(query);
        
        while (rs.next()) {
            String DateCreatedDB = rs.getString("DateCreated");
            DateCreated = DateCreatedDB;
        }
            
    } catch(SQLException sqlex) {
        System.err.println(sqlex.getMessage());
        System.err.println("error retrieving task completion date from DB");
    }
        
    try {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(DateCreated);
        dc = (date.getTime());
    } catch(Exception e) {
        System.err.println(e.getMessage());
        System.err.println("error parsing creation date");
    }
    System.out.println("testID: " +testID);
    Insert(testID,dc);
    
    }//GEN-LAST:event_completeButtonActionPerformed

    public static void run() {
        new TaskLogging().setVisible(true);
    }
      
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CompletedLabel;
    private javax.swing.JLabel DateCreatedLabel;
    private javax.swing.JLabel DemandLabel;
    private javax.swing.JTextArea DescArea;
    private javax.swing.JLabel DueDateLabel;
    private javax.swing.JTextField DueDateTextField;
    private javax.swing.JLabel DurationLabel;
    private javax.swing.JLabel SignLevelLabel;
    private javax.swing.JTextField SignLevelTextField;
    private javax.swing.JTextField SignOffTextField;
    private javax.swing.JLabel TaskTypeLabel;
    private javax.swing.JLabel allocateLabel;
    private javax.swing.JTextField allocateTextField;
    private javax.swing.JButton carryOver;
    private javax.swing.JButton completeButton;
    private javax.swing.JButton completedButton;
    private javax.swing.JTextField completedTextField;
    private javax.swing.JTextField dateCreateTextField;
    private javax.swing.JTextField demandTextField;
    private javax.swing.JTextField durationTextField;
    private javax.swing.JPanel editPanel;
    private javax.swing.JButton findButton;
    private javax.swing.JLabel idLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton overdueButton;
    private javax.swing.JLabel prioLabel;
    private javax.swing.JTextField prioTextField;
    private javax.swing.JLabel signOffLabel;
    private javax.swing.JTable taskTable;
    private javax.swing.JTextField taskTextField;
    private javax.swing.JTextField taskTypeTextField;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}