/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transaction;

import csg.data.CSGData;
import csg.data.Student;
import csg.data.Team;
import jtps.jTPS_Transaction;

/**
 *
 * @author runnan
 */
public class Project_AddStudent_Transaction implements jTPS_Transaction{
    CSGData data;
    Student newstu;
    
    public Project_AddStudent_Transaction(CSGData initdata,Student initnew){
        data=initdata;
        newstu=initnew;
    }
    @Override
    public void doTransaction() {
        data.getStudents().add(newstu);
    }

    @Override
    public void undoTransaction() {
        data.getStudents().remove(newstu);
    }
    
}
