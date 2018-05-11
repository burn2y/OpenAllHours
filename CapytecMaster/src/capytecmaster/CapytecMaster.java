package capytecmaster;

/**
 *
 * @author Chris
 * @version (11/05/2018)
 * 
 * Main Class creates an instance of LoginGUI and sets it to be visible
 * 
 */
public class CapytecMaster {
    public static void main(String[] args) {
        LoginGUI loginGUI = new LoginGUI();
        
        AdminMainMenuUI p = new AdminMainMenuUI(loginGUI);
        AddEmployeeUI addEmp = new AddEmployeeUI(p);
        addEmp.setVisible(true);
    } 
}
