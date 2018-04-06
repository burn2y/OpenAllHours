package chrissoftwareengineeringwork;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author w16012643
 */
public class LoginGUI extends javax.swing.JFrame 
{
    
    ArrayList<Employee> empList = new ArrayList<>();
    ArrayList<Task> tskList = new ArrayList<>();
    AdminMainMenuUI adminMainMenu;
    ManagerMainMenuUI managerMainMenu;
    CaretakerMainMenuGUI caretakerMainMenuGUI;

    
    public LoginGUI() {
        initComponents();
        
        Employee newEmp = new Employee("Chris", "Burn", "07/07/1998", "male", 
                                        "Admin", "chrisburn2009@hotmail.co.uk",
                                        "PE 04 84 72 A", "3, St Georges Road", ""
                                        , "Hexham", "Northumberland", "NE462HG",
                                        1, "STADIUM10"); 
        empList.add(newEmp);
        
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        login = new javax.swing.JButton();
        username = new javax.swing.JTextField();
        lblUsername = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        lblPassword = new javax.swing.JLabel();
        title = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        login.setText("Log In");
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });

        lblUsername.setText("Username: ");
        lblPassword.setText("Password:");

        title.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        title.setText("Log In");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(login, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                            .addComponent(password)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lblUsername)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(105, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsername))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPassword))
                .addGap(40, 40, 40)
                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void loginActionPerformed(java.awt.event.ActionEvent evt) 
    {                                      
        String usernameInput = username.getText();
        String passwordInput = String.valueOf(password.getPassword());
        Integer i = 0;
        for(Employee emp : empList)
        {
            String stfID = Integer.toString(emp.getSysEmpID());
            String stfPassword = emp.getSysPassword();
            
            if(usernameInput.equals(stfID))
            {
                i = 1;
                if(passwordInput.equals(stfPassword))
                {
                    if(emp.getEmpType().equals("Caretaker"))
                    {
                        caretakerMainMenuGUI = new CaretakerMainMenuGUI(emp.getSysEmpID(), empList, tskList, this);
                        caretakerMainMenuGUI.setVisible(true);
                    }
                    else if(emp.getEmpType().equals("Manager"))
                    {
                        managerMainMenu = new ManagerMainMenuUI(empList, tskList, this);
                        managerMainMenu.setVisible(true);
                    }
                    else
                    {
                        adminMainMenu = new AdminMainMenuUI(empList, tskList, this);
                        adminMainMenu.setVisible(true);
                    }
                    
                    this.setVisible(false);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Username or password incorrect.", "Done", JOptionPane.ERROR_MESSAGE);
                }

            }
        }
        
        if(i != 1)
        {
            JOptionPane.showMessageDialog(null, "Username or password incorrect.", "Done", JOptionPane.ERROR_MESSAGE);
        }
    }

                             

    // Variables declaration - do not modify                     
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JButton login;
    private javax.swing.JPasswordField password;
    private javax.swing.JLabel title;
    private javax.swing.JTextField username;
    // End of variables declaration                   
}


