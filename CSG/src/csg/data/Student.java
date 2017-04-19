/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author runnan
 */
public class Student <E extends Comparable<E>> implements Comparable<E>{
    private final StringProperty firstName;
    private final StringProperty lastName;
    private  final StringProperty Team;
    private final StringProperty role;

    public Student(String initfirstName, String initlastName, String initTeam, String initrole) {
       firstName=new SimpleStringProperty(initfirstName);
    lastName = new SimpleStringProperty(initlastName);
       Team = new SimpleStringProperty(initTeam);
        role =new SimpleStringProperty(initrole);
    }

    public String getFirstName() {
         return firstName.get();
    }

    public String getLastName() {
        return lastName.get();
    }

    public String getTeam() {
        return Team.get();
    }

    public String getRole() {
        return role.get();
    }
    
    
      public void setFirstName(String initFirstName) {
          firstName.set(initFirstName);
    }

    public void setLastName(String initLastName) {
        lastName.set(initLastName);
    }

    public void setTeam(String initTeam) {
         Team.set(initTeam);
    }

    public void setRole(String initRole) {
         role.set(initRole);
    }
    
    @Override
    public int compareTo(E o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
