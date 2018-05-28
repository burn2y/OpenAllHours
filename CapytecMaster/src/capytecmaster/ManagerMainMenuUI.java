/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capytecmaster;

/**
 *
 * @author Chris
 */
public class ManagerMainMenuUI extends javax.swing.JFrame {

    // variables declared
    LoginGUI loginGUI;
    Integer empID;
    
    // constructor
    public ManagerMainMenuUI(LoginGUI loginGUI, Integer empID) 
    {
        // variables check
        this.empID = empID;
        this.loginGUI = loginGUI;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        removeTask = new javax.swing.JButton();
        updateTask = new javax.swing.JButton();
        addTask = new javax.swing.JButton();
        allocateDailyTasks = new javax.swing.JButton();
        changePassword = new javax.swing.JButton();
        logout = new javax.swing.JButton();
        logInInfo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        removeTask.setText("Remove Task");
        removeTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeTaskActionPerformed(evt);
            }
        });

        updateTask.setText("Update Task");
        updateTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateTaskActionPerformed(evt);
            }
        });

        addTask.setText("Add Task");
        addTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTaskActionPerformed(evt);
            }
        });

        allocateDailyTasks.setText("Allocate Task");
        allocateDailyTasks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allocateDailyTasksActionPerformed(evt);
            }
        });

        changePassword.setText("Change Password");
        changePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePasswordActionPerformed(evt);
            }
        });

        logout.setText("Log out");
        logout.setToolTipText("");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });

        logInInfo.setEditable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Manager Main Menu");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logInInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(addTask, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(allocateDailyTasks, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updateTask, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(changePassword))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(removeTask, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(logInInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addTask)
                    .addComponent(updateTask)
                    .addComponent(removeTask))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(allocateDailyTasks)
                    .addComponent(changePassword)
                    .addComponent(logout))
                .addGap(90, 90, 90))
        );

        pack();
    }// </editor-fold>                        

    // add task action listener
    private void addTaskActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
    }                                       

    // update task action listener
    private void updateTaskActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    // remove task action listener
    private void removeTaskActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    // allocate tasks action listener
    private void allocateDailyTasksActionPerformed(java.awt.event.ActionEvent evt) {                                                   
         AllocateTasksStp1 stp1 = new AllocateTasksStp1(this);
         stp1.setVisible(true);
    }                                                  

    // change password button action listener
    private void changePasswordActionPerformed(java.awt.event.ActionEvent evt) {
        // ManagerChangePasswordUI created and set to visible
        ManagerChangePasswordUI changePassword = new ManagerChangePasswordUI(empID, this);
        changePassword.setVisible(true);
    }                                              

    // logout button action listener
    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {                                       
        this.setVisible(false);
        loginGUI.setVisible(true);
    }                                      
                          

    // Variables declaration - do not modify                     
    private javax.swing.JButton addTask;
    private javax.swing.JButton allocateDailyTasks;
    private javax.swing.JButton changePassword;
    private javax.swing.JButton removeTask;
    private javax.swing.JButton updateTask;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField logInInfo;
    private javax.swing.JButton logout;
    // End of variables declaration                   
}
