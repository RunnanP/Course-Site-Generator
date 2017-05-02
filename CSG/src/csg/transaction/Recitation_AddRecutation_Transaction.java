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
public class Recitation_AddRecutation_Transaction implements jTPS_Transaction{
    
    CSGData data;
    Recitation reci;
    String section;
    
    
    
    public Recitation_AddRecutation_Transaction(CSGData initData,Recitation initRecitation,String initSection){
        data=initData;
        reci=initRecitation;
        section=initSection;
    }
    @Override
    public void doTransaction() {
        data.getRecitations().add(reci);
    }

    @Override
    public void undoTransaction() {
        data.getRecitations().remove(reci);
    }
    
}
