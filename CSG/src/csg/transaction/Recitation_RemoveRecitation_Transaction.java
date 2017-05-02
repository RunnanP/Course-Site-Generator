/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transaction;

import csg.data.CSGData;
import csg.data.Recitation;
import jtps.jTPS_Transaction;

/**
 *
 * @author runnan
 */
public class Recitation_RemoveRecitation_Transaction implements jTPS_Transaction{
     CSGData  data;
     Recitation reci;
    
    public Recitation_RemoveRecitation_Transaction(CSGData initData,Recitation initReci){
        data=initData;
        reci=initReci;
    }
    
    
    
    
    
    
    
    @Override
    public void doTransaction() {
       data.getRecitations().remove(reci);
    }

    @Override
    public void undoTransaction() {
       data.getRecitations().add(reci);
    }
    
}
