/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transaction;

import csg.data.CSGData;
import csg.data.ScheduleItem;
import jtps.jTPS_Transaction;

/**
 *
 * @author runnan
 */
public class Schedule_ChangeScheduleItemInfo_Transaction implements jTPS_Transaction{
   CSGData data;
   ScheduleItem newsche;
   ScheduleItem oldsche;
   
   public Schedule_ChangeScheduleItemInfo_Transaction(CSGData initdata,ScheduleItem initnew,ScheduleItem initold){
       data=initdata;
       newsche=initnew;
       oldsche=initold;
   }
    @Override
    public void doTransaction() {
         data.getScheduleItems().remove(oldsche);
        data.getScheduleItems().add(newsche);
    }

    @Override
    public void undoTransaction() {
         data.getScheduleItems().remove(newsche);
        data.getScheduleItems().add(oldsche);
    }
    
}
