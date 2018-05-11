package capytecmaster;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author w16012643
 */
public class LoginGUI extends javax.swing.JFrame 
{
    Connection dbConn = connection.connect();
    Statement stmt = null;
    
    public LoginGUI() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        login = new javax.swing.JButton();
        username = new javax.swing.JTextField();
        lblUsername = new javax.swing.JLabel();
        password = new javax.swing.JTextField();
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
        String passwordInput = password.getText();
                
        // connect to db. get all employees into arrayList.
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
        catch(SQLException sqlex) 
        {
            System.out.println(sqlex.getMessage());
            System.out.println("Duration update error\n");
        }
        
        Integer i = 0;
        Integer j = 0;
        for(Employee emp : empList)
        {
            String stfID = Integer.toString(emp.getSysEmpID());
            String stfPassword = emp.getSysPassword();
            
            if(usernameInput.equals(stfID))
            {
                i = 1;
                if(passwordInput.equals(stfPassword))
                {
                    j = 1;
                    if(emp.getEmpType().equals("Caretaker"))
                    {
                        CaretakerMainMenuGUI app = new CaretakerMainMenuGUI(this, emp.getSysEmpID());
                        app.setVisible(true);
                    }
                    else if(emp.getEmpType().equals("Manager"))
                    {
                        new ManagerMainMenuUI(this, emp.getSysEmpID()).setVisible(true);
                    }
                    else
                    {
                        new AdminMainMenuUI(this).setVisible(true);
                    }
                    
                    this.setVisible(false);
                }   
            }
        }
        if(i == 0)
        {
            JOptionPane.showMessageDialog(this, "Username or Password incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
        } 
        if(i == 1 && j == 0)
        {
            JOptionPane.showMessageDialog(this, "Username or Password incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }                                     

    // Variables declaration - do not modify                     
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JButton login;
    private javax.swing.JTextField password;
    private javax.swing.JLabel title;
    private javax.swing.JTextField username;
    // End of variables declaration                   
}


