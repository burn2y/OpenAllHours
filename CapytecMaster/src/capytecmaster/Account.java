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

    public class Account {
    int id;
    //String username;
    String password;
    String accountLevel;
    String firstName;
    String lastName;

    public Account(int id /*, String username*/, String password, String accountLevel, String firstName, String lastName) {
        this.id = id;
        //this.username = username;
        this.password = password;
        this.accountLevel = accountLevel;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }*/

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountLevel() {
        return accountLevel;
    }

    public void setAccountLevel(String accountLevel) {
        this.accountLevel = accountLevel;
    }
}

