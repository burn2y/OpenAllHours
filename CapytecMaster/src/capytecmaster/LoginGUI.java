package capytecmaster;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Chris Burn (w16012643)
 */
public class LoginGUI extends javax.swing.JFrame 
{
    
    // database connection retrived from connection class and Statement declared
    private Connection dbConn = connection.connect();
    private Statement stmt = null;
    
    // Login GUI constructor
    public LoginGUI() {
        initComponents();
    }

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
        lblPassword.setText("Password: ");

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

    // action listener for login button
    private void loginActionPerformed(java.awt.event.ActionEvent evt) 
    {                                  
        // username and password retrieved from text fields and stored in variables
        String usernameInput = username.getText();
        String passwordInput = password.getText();
                
        // arraylist declared
        ArrayList<Employee> empList = new ArrayList<>();
        // SQL query created
        String getAllEmpsSql = "SELECT * FROM EMPLOYEE";
        try
        {
            // SQL executed
            stmt = dbConn.createStatement();   
            ResultSet rs = stmt.executeQuery(getAllEmpsSql);
            while(rs.next())
            {
                // new employee created from result set.
                Employee emp = new Employee(rs.getString("Firstname"), 
                        rs.getString("Surname"), rs.getString("DateOfBirth"),
                        rs.getString("Gender"), rs.getString("EmploymentType"),
                        rs.getString("Email"), rs.getString("NINO"), 
                        rs.getString("AddressLine1"), 
                        rs.getString("AddressLineTwo"), rs.getString("City"), 
                        rs.getString("County"), rs.getString("Postcode"),
                        rs.getInt("ID") ,rs.getString("Password"));
                // employee added to arraylist
                empList.add(emp);
            }
        }
        catch(SQLException sqlex) 
        {
            // error catching
            System.out.println(sqlex.getMessage());
            System.out.println("Duration update error\n");
        }
        
        // two integers declared
        Integer i = 0;
        Integer j = 0;
        
        // for loop loops through ArrayList retrieved from database
        for(Employee emp : empList)
        {
            // EmpID converted to string and stored in variable
            String stfID = Integer.toString(emp.getSysEmpID());
            // password stored in variable
            String stfPassword = emp.getSysPassword();
            
            // if employeeID equals employee ID user enters. Continue
            if(usernameInput.equals(stfID))
            {
                // i set to 1 if username found
                i = 1;
                if(passwordInput.equals(stfPassword))
                {
                    // j set to 1 if password correct
                    j = 1;
                    
                    if(emp.getEmpType().equals("Caretaker"))
                    {
                        // Caretaker MainMenu created if empType is Caretaker
                        CaretakerMainMenuGUI app = new CaretakerMainMenuGUI(this, emp.getSysEmpID());
                        app.setVisible(true);
                    }
                    else if(emp.getEmpType().equals("Manager"))
                    {
                        // ManagerMainMenuUI created if empType is Manager
                        new ManagerMainMenuUI(this, emp.getSysEmpID()).setVisible(true);
                    }
                    else
                    {
                        // else Admin Main Menu
                        new AdminMainMenuUI(this, emp.getSysEmpID()).setVisible(true);
                    }
                    // this GUI (Login) set to visible
                    this.setVisible(false);
                }   
            }
        }
        if(i == 0)
        {
            // if username is incorrect. Display error message
            JOptionPane.showMessageDialog(this, "Username or Password incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
        } 
        if(i == 1 && j == 0)
        {
            // if username correct and password incorrect
            JOptionPane.showMessageDialog(this, "Username or Password incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
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


