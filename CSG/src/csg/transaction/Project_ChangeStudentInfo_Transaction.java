/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transaction;

import csg.data.CSGData;
import csg.data.Student;
import jtps.jTPS_Transaction;

/**
 *
 * @author runnan
 */
public class Project_ChangeStudentInfo_Transaction implements jTPS_Transaction{
    CSGData data;
    Student newstu;
    Student oldstu;
    
    public Project_ChangeStudentInfo_Transaction(CSGData initdata,Student initnew,Student initold){
        data=initdata;
        newstu=initnew;
        oldstu=initold;
    }
    @Override
    public void doTransaction() {
        data.getStudents().remove(oldstu);
        data.getStudents().add(newstu);
    }

    @Override
    public void undoTransaction() {
        data.getStudents().remove(newstu);
        data.getStudents().add(oldstu);
    }
    
}
