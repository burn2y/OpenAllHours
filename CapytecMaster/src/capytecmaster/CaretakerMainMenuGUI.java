/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capytecmaster;

public class CaretakerMainMenuGUI extends javax.swing.JFrame {

    // variable declarations
    LoginGUI loginGUI;
    Integer empID;
    
    // constructor
    public CaretakerMainMenuGUI(LoginGUI loginGUI, Integer empID) {
        // variables set and GUI initialised
        this.empID = empID;
        this.loginGUI = loginGUI;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        title = new javax.swing.JLabel();
        logInInfo = new javax.swing.JTextField();
        logout = new javax.swing.JButton();
        signOffTask = new javax.swing.JButton();
        viewDailyTasks = new javax.swing.JButton();
        changePassword = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        title.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        title.setText("Caretaker Main Menu");

        logInInfo.setEditable(false);
        logInInfo.setText("Employee " + empID + " logged in");

        logout.setText("Logout");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });
        
        signOffTask.setText("Sign Off Task");
        signOffTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signOffTaskActionPerformed(evt);
            }
        });

        viewDailyTasks.setText("View Daily Tasks");
        viewDailyTasks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewDailyTasksActionPerformed(evt);
            }
        });

        changePassword.setText("Change Password");
        changePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePasswordActionPerformed(evt);
            }
        });
        

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(logout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(viewDailyTasks, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(75, 75, 75)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(logInInfo)
                    .addComponent(signOffTask, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(changePassword, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(title)
                    .addComponent(logInInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(logout)
                    .addComponent(signOffTask))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewDailyTasks)
                    .addComponent(changePassword))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    // action listener for logout button
    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {                                       
        this.setVisible(false);
        loginGUI.setVisible(true);
    }                                      

    // action listener for sign off task button
    private void signOffTaskActionPerformed(java.awt.event.ActionEvent evt) {                                            
        TaskLogging tskLog = new TaskLogging();
        tskLog.setVisible(true);
    }                                           

    // action listener for view daily tasks button
    private void viewDailyTasksActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
    }                                              

    // action listener for change password button
    private void changePasswordActionPerformed(java.awt.event.ActionEvent evt) {
        // new CaretakerChangePasswordUI created and set to visible
        CaretakerChangePasswordUI changePassword = 
                new CaretakerChangePasswordUI(empID, this);
        changePassword.setVisible(true);
    }              
    
    // Variables declaration - do not modify                     
    private javax.swing.JButton changePassword;
    private javax.swing.JTextField logInInfo;
    private javax.swing.JButton logout;
    private javax.swing.JButton signOffTask;
    private javax.swing.JLabel title;
    private javax.swing.JButton viewDailyTasks;
    // End of variables declaration                   
}



