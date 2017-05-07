/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transaction;

import csg.data.CSGData;
import csg.data.Student;
import csg.data.Team;
import javafx.collections.ObservableList;
import jtps.jTPS_Transaction;

/**
 *
 * @author runnan
 */
public class Project_ChangeStudentInfo_Transaction implements jTPS_Transaction{
    CSGData data;
    Student newstu;
    Student oldstu;
    String newstuname;
    String oldstuname;
    
    public Project_ChangeStudentInfo_Transaction(CSGData initdata,Student initnew,Student initold){
        data=initdata;
        newstu=initnew;
        oldstu=initold;
        newstuname=newstu.getFirstName()+" "+newstu.getLastName();
        oldstuname=oldstu.getFirstName()+" "+oldstu.getLastName();
        
    }
    @Override
    public void doTransaction() {
        data.getStudents().remove(oldstu);
        
           ObservableList<Team> teams= data.getTeams();
         for (Team te:teams){
            if (oldstu.getTeamString().equals(te.getTeamname())){
                te.getStudentList().remove(oldstu);
                
            }
        }
        
        data.getStudents().add(newstu);
           for (Team te:teams){
            if (newstu.getTeamString().equals(te.getTeamname())){
                te.getStudentList().add(newstu);
                
            }
        }
    }

    @Override
    public void undoTransaction() {
        data.getStudents().remove(newstu);
              ObservableList<Team> teams= data.getTeams();
         for (Team te:teams){
            if (newstu.getTeamString().equals(te.getTeamname())){
                te.getStudentList().remove(newstu);
                
            }
        }
        data.getStudents().add(oldstu);
        
        
        
           for (Team te:teams){
            if (oldstu.getTeamString().equals(te.getTeamname())){
                te.getStudentList().add(oldstu);
                
            }
        }
    }
    
}
