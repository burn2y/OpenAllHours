/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chrissoftwareengineeringwork;

public class Employee {
    
    private String fName, sName, dateOfBirth, gender, empType, email, 
            natInsuranceNo, addrLn1, addrLn2, addrCity, addrCounty, addrPostcode,
            sysPassword;
    private Integer sysEmpID;
    
    /*
     * @param fName first name of employee
     * @param sName second name of employee
     * @param dateOfBirth employee's date of birth
     * @param gender gender of employee
     * @param empType type of employee
     * @param email email address of the employee
     * @param natInsuranceNo national insurance number of the employee
     * @param addrLn1 first line of the employees address
     * @param addrLn2 second line of the employees address
     * @param addrCity town or city the employee lives in
     * @param addrPostcode postcode of the employees house
     * @param sysEmpID employee ID of the employee. Used as username for system.
     * @param sysPassword password of the employees account. Used to login.
     */
    public Employee(String fName, String sName, String dateOfBirth, 
                    String gender, String empType, String email, 
                    String natInsuranceNo, String addrLn1, String addrLn2,
                    String addrCity, String addrCounty, String addrPostcode,
                    Integer sysEmpID, String sysPassword)
    {
        this.fName = fName;
        this.sName = sName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.empType = empType;
        this.email = email;
        this.natInsuranceNo = natInsuranceNo;
        this.addrLn1 = addrLn1;
        this.addrLn2 = addrLn2;
        this.addrCity = addrCity;
        this.addrCounty = addrCounty;
        this.addrPostcode = addrPostcode;
        this.sysEmpID = sysEmpID;
        this.sysPassword = sysPassword;
    }
    
    public String getFName() { return fName; }
    public String getSName() { return sName; }
    public String getDateOfBirth() { return dateOfBirth; }
    public String getGender() { return gender; }
    public String getEmpType() { return empType; }
    public String getEmail() { return email; }
    public String getNatInsuranceNo() { return natInsuranceNo; }
    public String getAddrLn1() { return addrLn1; }
    public String getAddrLn2() { return addrLn2; }
    public String getAddrCity() { return addrCity; }
    public String getAddrCounty() { return addrCounty; }
    public String getAddrPostcode() { return addrPostcode; } 
    public Integer getSysEmpID() { return sysEmpID; }
    public String getSysPassword() { return sysPassword; }
    public String toString()
    {
        if(addrLn2.isEmpty() == false)
        {
            return "Name: " + fName + " " + sName +
               "\nDate of Birth: " + dateOfBirth +
               "\nGender: " + gender +
               "\nEmployee Type: " + empType +
               "\nEmail Address: " + email +
               "\nNational Insurance Number: " + natInsuranceNo +
               "\nAddress: \n" +
               addrLn1 + "\n" +
               addrLn2 + "\n" +
               addrCity + "\n" +
               addrCounty + "\n" +
               addrPostcode + "\n" +
               "Employee ID Number: " + sysEmpID;
        }
        else
        {
            return "Name: " + fName + " " + sName +
               "\nDate of Birth: " + dateOfBirth +
               "\nGender: " + gender +
               "\nEmployee Type: " + empType +
               "\nEmail Address: " + email +
               "\nNational Insurance Number: " + natInsuranceNo +
               "\nAddress: \n" +
               addrLn1 + "\n" +
               addrCity + "\n" +
               addrCounty + "\n" +
               addrPostcode + "\n" +
               "Employee ID Number: " + sysEmpID;
        }
                
                
    }
    
    public void setFName(String fName)
    {
        this.fName = fName;
    }
    
    public void setSName(String sName)
    {
        this.sName = sName;
    }
    
    public void setDateOfBirth(String dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }
    
    public void setGender(String gender)
    {
        this.gender = gender;
    }
    
    public void setEmpType(String empType)
    {
        this.empType = empType;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public void setNatInsuranceNo(String natInsurance)
    {
        this.natInsuranceNo = natInsurance;
    }
    
    public void setAddrLn1(String addrLn1)
    {
        this.addrLn1 = addrLn1;
    }
    
    public void setAddrLn2(String addrLn2)
    {
        this.addrLn2 = addrLn2;
    }
    
    public void setAddrCity(String addrCity)
    {
        this.addrCity = addrCity;
    }
    
    public void setAddrCounty(String addrCounty)
    {
        this.addrCounty = addrCounty;
    }
    
    public void setAddrPostcode(String addrPostcode)
    {
        this.addrPostcode = addrPostcode;
    }
    
    public void setSysEmpID(Integer sysEmpID)
    {
        this.sysEmpID = sysEmpID;
    }
    
    public void setSysPassword(String password)
    {
        this.sysPassword = password;
    }
}
