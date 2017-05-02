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
public class TA_AddTA_Transaction implements jTPS_Transaction{
    String newname;
    String newemail;
    CSGData newdata;
    
    
    public TA_AddTA_Transaction(CSGData data,String initName,String initEmail){
        
         newname=initName;
        newemail=initEmail;
       newdata=data;
        
    }
    
    
    @Override
    public void doTransaction() {
       newdata.addTA(newname, newemail);
    }

    @Override
    public void undoTransaction() {
        newdata.removeTA(newname);
    }
     public String toString(){
        return "Add TA:"+newname;
    }
    
    
    
    
    
    
    
    
    
}
