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
public class Schedule_AddScheduleItem_Transaction implements jTPS_Transaction{
  CSGData data;
  ScheduleItem newschedule;
  
  public Schedule_AddScheduleItem_Transaction(CSGData initdata,ScheduleItem initnew){
      data=initdata;
      newschedule=initnew;
  }
    @Override
    public void doTransaction() {
       data.getScheduleItems().add(newschedule);
    }

    @Override
    public void undoTransaction() {
       data.getScheduleItems().remove(newschedule);
    }
    
}
