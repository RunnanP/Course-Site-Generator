/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transaction;

import csg.data.CSGData;
import csg.data.Student;
import csg.data.Team;
import java.util.Observable;
import javafx.collections.ObservableList;
import jtps.jTPS_Transaction;

/**
 *
 * @author runnan
 */
public class Project_AddStudent_Transaction implements jTPS_Transaction{
    CSGData data;
    Student newstu;
    String stuname;
    
    public Project_AddStudent_Transaction(CSGData initdata,Student initnew){
        data=initdata;
        newstu=initnew;
        stuname=newstu.getFirstName()+" "+newstu.getLastName();
//        ObservableList<Team> teams= data.getTeams();
//        for (Team te:teams){
//            if (newstu.getTeamString().equals(te.getTeamname())){
//                te.getStudentList().add(newstu.getFirstName()+" "+newstu.getLastName());
//                
//            }
//        }
       
    }
    @Override
    public void doTransaction() {
        data.getStudents().add(newstu);
        ObservableList<Team> teams= data.getTeams();
        for (Team te:teams){
            if (newstu.getTeamString().equals(te.getTeamname())){
                te.getStudentList().add(stuname);
                
            }
        }
    }

    @Override
    public void undoTransaction() {
        data.getStudents().remove(newstu);
       
        ObservableList<Team> teams= data.getTeams();
         for (Team te:teams){
            if (newstu.getTeamString().equals(te.getTeamname())){
                te.getStudentList().remove(stuname);
                
            }
        }
        
        
    }
    
}
