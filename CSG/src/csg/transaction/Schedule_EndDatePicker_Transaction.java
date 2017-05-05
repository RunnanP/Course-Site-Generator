/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transaction;

import csg.data.CSGData;
import csg.workspace.CSGScheduleWorkspace;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jtps.jTPS_Transaction;

/**
 *
 * @author runnan
 */
public class Schedule_EndDatePicker_Transaction implements jTPS_Transaction{
     CSGData data;
     CSGScheduleWorkspace workspace;
     String olddate;
     String newdate;
    public Schedule_EndDatePicker_Transaction(CSGData initdata,CSGScheduleWorkspace initworkspace,String initolddate,String initnewdate){
        data=initdata;
        workspace=initworkspace;
        olddate=initolddate;
        newdate=initnewdate;
        
    }
    
    
    
    @Override
    public void doTransaction() {
         try {
             data.setEndingDate(newdate);
         } catch (ParseException ex) {
             Logger.getLogger(Schedule_StartDatePicker_Transaction.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @Override
    public void undoTransaction() {
         try {
             data.setEndingDate(olddate);
             workspace.loadCalendarEnd(olddate);
         } catch (ParseException ex) {
             Logger.getLogger(Schedule_StartDatePicker_Transaction.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
}