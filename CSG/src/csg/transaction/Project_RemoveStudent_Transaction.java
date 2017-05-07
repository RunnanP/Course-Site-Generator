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
public class Project_RemoveStudent_Transaction implements jTPS_Transaction{
  CSGData data;
  Student st;
  String stuname;
    public Project_RemoveStudent_Transaction(CSGData initdata,Student initst) {
        
        data=initdata;
        st=initst;
        stuname=st.getFirstName()+" "+st.getLastName();
    }
   
    
    @Override
    public void doTransaction() {
       data.removeStudent(st);
       
    }

    @Override
    public void undoTransaction() {
        data.getStudents().add(st);
    }
    
}
