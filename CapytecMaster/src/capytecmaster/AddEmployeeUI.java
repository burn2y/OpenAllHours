package capytecmaster;

import java.sql.*;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chris
 */
public class AddEmployeeUI extends javax.swing.JFrame {

   
    private AdminMainMenuUI parent;
    Connection dbConn = connection.connect();
    Statement stmt = null;
    Integer maxEmpID = null;
    
    public AddEmployeeUI(AdminMainMenuUI p) {
        parent = p;       
        initComponents();
    }      

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        name = new javax.swing.JTextField();
        lblFName = new javax.swing.JLabel();
        lblSurname = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        lblDateOfBirth = new javax.swing.JLabel();
        dayOfBirth = new javax.swing.JSpinner();
        monthOfBirth = new javax.swing.JSpinner();
        yearOfBirth = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        lblGender = new javax.swing.JLabel();
        male = new javax.swing.JRadioButton();
        female = new javax.swing.JRadioButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        lblEmpType = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        lblNatInsuranceNo = new javax.swing.JLabel();
        natInsuranceNo = new javax.swing.JTextField();
        lblSysPassword = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        lblAddrLn1 = new javax.swing.JLabel();
        addrLn1 = new javax.swing.JTextField();
        lblAddrLn2 = new javax.swing.JLabel();
        addrLn2 = new javax.swing.JTextField();
        lblAddrCity = new javax.swing.JLabel();
        city = new javax.swing.JTextField();
        lblCounty = new javax.swing.JLabel();
        county = new javax.swing.JTextField();
        lblPostcode = new javax.swing.JLabel();
        postcode = new javax.swing.JTextField();
        back = new javax.swing.JButton();
        addEmployee = new javax.swing.JButton();
        title = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        addEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEmployeeActionPerformed(evt);
            }
        });
        
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        lblFName.setText("First Name:");

        lblSurname.setText("Surname:");

        lblDateOfBirth.setText("Date Of Birth:");

        dayOfBirth.setModel(new javax.swing.SpinnerNumberModel(1, 1, 31, 1));

        monthOfBirth.setModel(new javax.swing.SpinnerNumberModel(1, 1, 12, 1));

        yearOfBirth.setModel(new javax.swing.SpinnerNumberModel(1900, 1900, 2100, 1));

        jLabel1.setText("  Day        Month       Year");

        lblGender.setText("Gender:");

        male.setText("Male");

        female.setText("Female");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Caretaker", "Manager", "Admin" }));

        lblEmpType.setText("Employee Type:");

        lblEmail.setText("Email:");

        lblNatInsuranceNo.setText("NI Number:");

        lblSysPassword.setText("Password:");

        lblAddrLn1.setText("Address Line 1:");

        lblAddrLn2.setText("Address Line 2:");

        lblAddrCity.setText("City:");

        lblCounty.setText("County:");

        lblPostcode.setText("Postcode:");

        back.setText("Back");

        addEmployee.setText("Add Employee");

        title.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        title.setText("Enter Employee Details");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFName)
                                    .addComponent(lblDateOfBirth)
                                    .addComponent(lblEmpType)
                                    .addComponent(lblNatInsuranceNo)
                                    .addComponent(lblAddrLn1)
                                    .addComponent(lblAddrCity)
                                    .addComponent(lblPostcode))
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(dayOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(monthOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(yearOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(name)
                                    .addComponent(natInsuranceNo)
                                    .addComponent(addrLn1)
                                    .addComponent(city)
                                    .addComponent(postcode)))
                            .addComponent(back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(lblGender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblSysPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)))
                                    .addGap(10, 10, 10)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(male, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(female, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCounty)
                                    .addComponent(lblAddrLn2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(addrLn2)
                                    .addComponent(county)))
                            .addComponent(addEmployee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(title)
                        .addGap(166, 166, 166)))
                .addContainerGap(86, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(title)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFName)
                    .addComponent(lblSurname)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblGender)
                    .addComponent(male))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDateOfBirth)
                    .addComponent(dayOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(monthOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(female))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEmpType)
                    .addComponent(lblEmail)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNatInsuranceNo)
                    .addComponent(natInsuranceNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSysPassword)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddrLn1)
                    .addComponent(addrLn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAddrLn2)
                    .addComponent(addrLn2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddrCity)
                    .addComponent(city, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCounty)
                    .addComponent(county, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPostcode)
                    .addComponent(postcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addEmployee)
                    .addComponent(back))
                .addGap(43, 43, 43))
        );

        pack();
    }// </editor-fold>      
    
    private void backActionPerformed(java.awt.event.ActionEvent evt)
    {
        this.setVisible(false);
        parent.setVisible(true);
    }

                                   
private void addEmployeeActionPerformed(java.awt.event.ActionEvent evt) 
    {           
        Integer errors = 0;
        if(name.getText().trim().isEmpty())
        {
            errors = errors + 1;
        }
        if(jTextField1.getText().trim().isEmpty())
        {
            errors = errors + 1;
        }
        if(addrLn1.getText().trim().isEmpty())
        {
            errors = errors + 1;
        }
        if(city.getText().trim().isEmpty())
        {
            errors = errors + 1;
        }
        if(county.getText().trim().isEmpty())
        {
            errors = errors + 1;
        }
        if(email.getText().trim().isEmpty())
        {
            errors = errors + 1;
        }
        if(natInsuranceNo.getText().trim().isEmpty())
        {
            errors = errors + 1;
        }
        if(password.getText().trim().isEmpty())
        {
            errors = errors + 1;
        }
        if(male.isSelected() == true && female.isSelected())
        {
            errors = errors + 1;
        }
        
        if(errors == 0)
        {
            String gender;
            String empType;
            String dateOfBirth = dayOfBirth.getValue().toString() + "/" + monthOfBirth.getValue().toString() + "/" + yearOfBirth.getValue();
            String passwordInput = String.valueOf(password.getPassword());
            if(male.isSelected() == true)
            {
                gender = "Male";
            }
            else
            {
                gender = "Female";
            }
            
            empType = jComboBox1.getSelectedItem().toString();
            
            
            // get Max employeeID from db. store it in maxEmpID
            String maxEmpIDSql = "SELECT MAX(ID) AS MAXID FROM EMPLOYEE";
             try
            {
                stmt = dbConn.createStatement();   
                ResultSet rs = stmt.executeQuery(maxEmpIDSql);
                if(rs.next())
                {
                    maxEmpID = rs.getInt("MAXID");
                }
            }
            catch(SQLException sqlex) {
              System.out.println(sqlex.getMessage());
              System.out.println("Duration update error\n");
        }
            
            Employee newEmp = new Employee(name.getText(), jTextField1.getText(), 
                    dateOfBirth, gender, empType, 
                    email.getText(), natInsuranceNo.getText(), 
                    addrLn1.getText(), addrLn2.getText(), city.getText(), 
                    county.getText(), postcode.getText(), 
                    maxEmpID + 1, passwordInput);
            
            
            
            String sqlInsert = 
                    "INSERT INTO employee (ID, Firstname, DateOfBirth, Surname, Gender, EmploymentType, Email, NINO, AddressLine1, AddressLineTwo, City, County, Postcode, Password)VALUES (" + newEmp.getSysEmpID() +  
                    " , '" + newEmp.getFName() + "' , '" + newEmp.getDateOfBirth() + 
                    "' , '" + newEmp.getSName() + "' , '" + newEmp.getGender() + 
                    "' , '" + newEmp.getEmpType() + "' , '" + newEmp.getEmail() + 
                    "' , '" + newEmp.getNatInsuranceNo() + "' , '" + newEmp.getAddrLn1() +
                    "' , '" + newEmp.getAddrLn2() + "', ' " + newEmp.getAddrCity() +
                    "' , '" + newEmp.getAddrCounty() + "' , '" + newEmp.getAddrPostcode() +
                    "' , '" + newEmp.getSysPassword() + "')";
            
            try
            {
                stmt = dbConn.createStatement(); // create a statement
                stmt.executeUpdate(sqlInsert); // execute update statement
            }
            catch(SQLException sqlex) 
            {
              System.out.println(sqlex.getMessage());
              System.out.println("Duration update error\n");
            }
            
            
            JOptionPane.showMessageDialog(this, "Employee Added",
            "Done", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(this, "The information you entered is invalid. Please check the form and try again.",
            "Error", JOptionPane.ERROR_MESSAGE);
        }
    } 

    // Variables declaration - do not modify                     
    private javax.swing.JButton addEmployee;
    private javax.swing.JTextField addrLn1;
    private javax.swing.JTextField addrLn2;
    private javax.swing.JButton back;
    private javax.swing.JTextField city;
    private javax.swing.JTextField county;
    private javax.swing.JSpinner dayOfBirth;
    private javax.swing.JTextField email;
    private javax.swing.JRadioButton female;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblAddrCity;
    private javax.swing.JLabel lblAddrLn1;
    private javax.swing.JLabel lblAddrLn2;
    private javax.swing.JLabel lblCounty;
    private javax.swing.JLabel lblDateOfBirth;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEmpType;
    private javax.swing.JLabel lblFName;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblNatInsuranceNo;
    private javax.swing.JLabel lblPostcode;
    private javax.swing.JLabel lblSurname;
    private javax.swing.JLabel lblSysPassword;
    private javax.swing.JRadioButton male;
    private javax.swing.JSpinner monthOfBirth;
    private javax.swing.JTextField name;
    private javax.swing.JTextField natInsuranceNo;
    private javax.swing.JPasswordField password;
    private javax.swing.JTextField postcode;
    private javax.swing.JLabel title;
    private javax.swing.JSpinner yearOfBirth;
    // End of variables declaration                   
}