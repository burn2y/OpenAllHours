package chrissoftwareengineeringwork;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class AllocateTasksStp2 extends javax.swing.JFrame {

    AllocateTasksStp1 stp1;
    Task tsk;
    
    public AllocateTasksStp2(AllocateTasksStp1 stp1, Task tsk) {
        this.stp1 = stp1;
        this.tsk = tsk;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmployee = new javax.swing.JTable();
        allocate = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblTask = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        DefaultTableModel model = new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Emp ID", "First Name", "Surname"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] 
            {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) 
            {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) 
            {
                return canEdit [columnIndex];
            }
        };
        
        for(Employee emp : stp1.getParent().getEmpList())
        {
            if(emp.getEmpType() == "Caretaker")
            {
                String empID = Integer.toString(emp.getSysEmpID());
                Object[] row = new Object[]
                {
                    empID, emp.getFName(), emp.getSName(), emp.getEmpType()
                };
                model.addRow(row);
            }
            
        }
        
        tblEmployee.setModel(model);
        
        
        jScrollPane1.setViewportView(tblEmployee);

        allocate.setText("Allocate");

        jLabel1.setText("Select an employee to allocate the task to");

        //lblTask.setText("Task: ID - " + tsk.getTskID() + "- " + tsk.getTskDescription() + "Physical Demands - " + tsk.getPhysicalDemandsRating());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Employees");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTask)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1)
                        .addComponent(allocate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addComponent(jLabel2)))
                .addContainerGap(123, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTask)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(allocate)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        
        allocate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allocateButtonActionPerformed(evt);
            }
        });
        pack();
        
    }// </editor-fold>                        

    private void allocateButtonActionPerformed(java.awt.event.ActionEvent evt)
    {
        if(tblEmployee.getSelectedRow() == -1)
        {
            JOptionPane.showMessageDialog(null, "You have not selected an employee!", "Done", JOptionPane.ERROR_MESSAGE);
        }
        Integer empNo = Integer.parseInt(tblEmployee.getValueAt(tblEmployee.getSelectedRow(), 0).toString());
        for(Task tsk : stp1.getParent().getTskList())
        {
            if(this.tsk == tsk )
            {
                tsk.setAllocatedTo(empNo);
                JOptionPane.showMessageDialog(null, "Task Allocated!", "Done", JOptionPane.INFORMATION_MESSAGE);
                this.setVisible(false);
            }
        }
    }
    // Variables declaration - do not modify                     
    private javax.swing.JButton allocate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTask;
    private javax.swing.JTable tblEmployee;
    // End of variables declaration                   
}
