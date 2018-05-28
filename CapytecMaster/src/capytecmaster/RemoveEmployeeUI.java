package capytecmaster;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

/*
 *  GUI class to remove employee from database
 *  @author Chris Burn (w16012643)
 *
*/
public class RemoveEmployeeUI extends javax.swing.JFrame {

    // variables declared
    private AdminMainMenuUI parent;
    Connection dbConn = connection.connect();
    Statement stmt = null;
    
    // constructor
    public RemoveEmployeeUI(AdminMainMenuUI p) {
        // variables set
        parent = p;
        initComponents();
    }

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

    // delete button action listener
    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {   
        
        // arrayList Declared
        ArrayList<Employee> empList = new ArrayList<>();
        
        // SQL string declared
        String getAllEmpsSql = "SELECT * FROM EMPLOYEE";
            try
            {
                // SQL executed
                stmt = dbConn.createStatement();   
                ResultSet rs = stmt.executeQuery(getAllEmpsSql);
                while(rs.next())
                {
                    // employee created from result set
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
             
        // Integer declared and set to 0
        Integer i = 0;
        for(Employee emp : empList)
        {
            // i set to 1 if number entered by user matches someones empId in DB
            if(empNumber.getText().equals(emp.getSysEmpID().toString()))
            {
                i = 1;
            }
        }
        
        if(i == 0)
        {
            // if empID not found, error message shown
            JOptionPane.showMessageDialog(null, "The employee number you entered"
                    + " does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            // else code continues
            if(empList.size() == 1)
            {
                // if there's only 1 employee in database. error message shown
                JOptionPane.showMessageDialog(null, 
                        "There is only one employee in the system!", "Error", 
                        JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                // else code continues
                
                // number converted to Integer and stored in variable
                Integer empID = Integer.parseInt(empNumber.getText());
                // SQL String declared
                String sqlRemove = "DELETE * FROM EMPLOYEE WHERE ID = " + empID;
            
                try
                {
                    // SQL executed
                    stmt = dbConn.createStatement(); 
                    stmt.executeUpdate(sqlRemove); 
                }
                catch(SQLException sqlex) 
                {
                    // error catching
                    System.out.println(sqlex.getMessage());
                    System.out.println("Duration update error\n");
                }
            
                // information message shown
                JOptionPane.showMessageDialog(null, "Employee number " + empID +
                        " removed." , "Done", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }                                      

    // back button action listener
    private void backActionPerformed(java.awt.event.ActionEvent evt) {
        // this GUI set to invisible and parent class set to visible
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