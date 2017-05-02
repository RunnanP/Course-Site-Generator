/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transaction;

import csg.data.CSGData;
import csg.data.Recitation;
import javax.swing.text.html.HTML;
import jtps.jTPS_Transaction;

/**
 *
 * @author runnan
 */
public class Recitation_ChangeRecitationInfo_Transaction implements jTPS_Transaction{

    CSGData data;
    Recitation newreci;
    Recitation oldreci;
    
    
    public Recitation_ChangeRecitationInfo_Transaction(CSGData initData,Recitation initnewReci,Recitation initoldReci){
        data=initData;
        newreci=initnewReci;
        oldreci=initoldReci;
    }
    @Override
    public void doTransaction() {
        data.getRecitations().remove(oldreci);
         data.getRecitations().add(newreci);
    }

    @Override
    public void undoTransaction() {
          data.getRecitations().remove(newreci);
         data.getRecitations().add(oldreci);
  
    }
    
}
