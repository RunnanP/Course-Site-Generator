/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.CheckBox;

/**
 *
 * @author runnan
 */
public class TeachingAssistant<E extends Comparable<E>> implements Comparable<E>  {
    // THE TABLE WILL STORE TA NAMES AND EMAILS
    private final StringProperty email;
    private final StringProperty name;
    private  final BooleanProperty underGrad;
    private final StringProperty kk;
//    public boolean underornot;
//    public CheckBox cb;
//    public SimpleBooleanProperty test;
    

    /**
     * Constructor initializes both the TA name and email.
     */
    public TeachingAssistant(String initName, String initEmail) {
        name = new SimpleStringProperty(initName);
        email = new SimpleStringProperty(initEmail);
      //  underGrad
       underGrad=new SimpleBooleanProperty(true);
       kk=new SimpleStringProperty("pp");
     
    }

    // ACCESSORS AND MUTATORS FOR THE PROPERTIES

    public String getName() {
        return name.get();
    }

    public void setName(String initName) {
        name.set(initName);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String initEmail) {
        email.set(initEmail);
    }
     
   public void setUnderGrad(boolean initUnderGrad){
         underGrad.set(initUnderGrad);
    }
    
    public boolean getUnderGrad(){
        return underGrad.get();
    }
    @Override
    public int compareTo(E otherTA) {
        return getName().compareTo(((TeachingAssistant)otherTA).getName());
    }
    
    @Override
    public String toString() {
        return name.getValue();
    }

//    public Boolean isSelected() {
//        return underornot;
//    }
//
//    public void setSelected(Boolean new_val) {
//       underornot=new_val;
//    }
     
    public String getKk(){
        return kk.get();
    }
    public void setKk(String ii){
        kk.set(ii);
    }

    
}