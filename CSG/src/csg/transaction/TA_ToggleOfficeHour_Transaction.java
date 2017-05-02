/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transaction;

import csg.data.CSGData;
import jtps.jTPS_Transaction;

/**
 *
 * @author runnan
 */
public class TA_ToggleOfficeHour_Transaction implements jTPS_Transaction{
    String oldcellKey;
    String oldtaName;
    CSGData olddata;
    Object oldselectItem;

    
        public TA_ToggleOfficeHour_Transaction(CSGData data,String cellKey,String taName,Object selectItem){
        oldcellKey=cellKey;
        oldtaName=taName;
        olddata=data;
        oldselectItem=selectItem;
    }
    
    @Override
    public void doTransaction() {
         if (oldselectItem!=null){
          
             olddata.toggleTAOfficeHours(oldcellKey, oldtaName);}
    }

    @Override
    public void undoTransaction() {
        if (oldselectItem!=null){
            olddata.toggleTAOfficeHours(oldcellKey, oldtaName);}
    }
    
}
