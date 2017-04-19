/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author runnan
 */
public class Recitation <E extends Comparable<E>> implements Comparable<E>{
   
    private final StringProperty section;
    private final StringProperty instructor;
    private  final StringProperty daytime;
    private final StringProperty location;
    private final StringProperty firstTa;
    private  final StringProperty secondTa;

    public String getSection() {
        return section.get();
    }

    public String getInstructor() {
        return instructor.get();
    }

    public String getDaytime() {
        return daytime.get();
    }

    public String getLocation() {
        return location.get();
    }

    public String getFirstTa() {
        return firstTa.get();
    }

    public String getSecondTa() {
        return secondTa.get();
    }
    
    
    
      public void setSection(String initSection) {
         section.set(initSection);
    }

    public void setInstructor(String initInstructor) {
        instructor.set(initInstructor);
    }

    public void setDaytime(String initDaytime) {
         daytime.set(initDaytime);
    }

    public void setLocation(String initLocation) {
          location.set(initLocation);
    }

    public void setFirstTa(String initFirstTa) {
         firstTa.set(initFirstTa);
    }

    public void setSecondTa(String initSecondTa) {
        secondTa.set(initSecondTa);
    }
    
    
    public Recitation(String initSection,String initInstructor,String initDaytime,String initLocation,String initFirstTa,String initSecondTa){
        section=new SimpleStringProperty(initSection);
        instructor=new SimpleStringProperty(initInstructor);
        daytime=new SimpleStringProperty(initDaytime);
        location=new SimpleStringProperty(initLocation);
        firstTa=new SimpleStringProperty(initFirstTa);
        secondTa=new SimpleStringProperty(initSecondTa);
        
    }
    
    public Recitation(String initSection,String initInstructor){
           section=new SimpleStringProperty(initSection);
        instructor=new SimpleStringProperty(initInstructor);
         daytime=new SimpleStringProperty("TBD");
        location=new SimpleStringProperty("TBD");
        firstTa=new SimpleStringProperty("");
        secondTa=new SimpleStringProperty("");
    }
    
    @Override
    public int compareTo(E o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
