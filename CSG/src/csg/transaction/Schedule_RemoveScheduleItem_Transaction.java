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
public class Schedule_RemoveScheduleItem_Transaction implements jTPS_Transaction{
    
    CSGData data;
    ScheduleItem sche;
    
    public Schedule_RemoveScheduleItem_Transaction(CSGData initdata,ScheduleItem initsche) {
      data=initdata;
      sche=initsche;
    }
    
    
    
    @Override
    public void doTransaction() {
        data.removeScheduleItem(sche);
    }

    @Override
    public void undoTransaction() {
        data.getScheduleItems().add(sche);
    }
    
}
