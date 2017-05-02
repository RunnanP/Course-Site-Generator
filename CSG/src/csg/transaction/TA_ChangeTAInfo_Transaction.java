/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transaction;

import csg.data.CSGData;
import csg.workspace.CSGTAWorkspace;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.control.Label;
import jtps.jTPS_Transaction;

/**
 *
 * @author runnan
 */
public class TA_ChangeTAInfo_Transaction implements jTPS_Transaction{
    
    
       CSGData olddata;
     CSGTAWorkspace oldworkspace;
     String oldtaName;
     String oldtaEmail;
     String oldnewName;
     String oldnewEmail;
     HashMap<String, Label> oldlabels;
     ArrayList<Label> oldjtpsarray;
     
     
    public TA_ChangeTAInfo_Transaction(CSGData data,CSGTAWorkspace workspace,String taName,String taEmail,String newName,String newEmail,HashMap<String, Label> labels,ArrayList<Label> jtpsarray){
          olddata=data;
     oldworkspace=workspace;
      oldtaName=taName;
     oldtaEmail=taEmail;
     oldnewName=newName;
     oldnewEmail=newEmail;
     oldlabels=labels;
      oldjtpsarray=jtpsarray;
    }
    
    
    public void doTransaction(){

             olddata.removeTA(oldtaName);
                 olddata.addTA(oldnewName, oldnewEmail);
  
                for(Label label:oldjtpsarray){
                    if (label.getText().equals(oldtaName)
                    || (label.getText().contains(oldtaName + "\n"))
                    || (label.getText().contains("\n" + oldtaName))){
                     olddata.removeTAFromCell(label.textProperty(), oldtaName);
                        olddata.addTAToCell(label.textProperty(),oldnewName);
                    }
                
                    }
                
                }
                 
        
    public void undoTransaction(){
        
         olddata.removeTA(oldnewName);
                 olddata.addTA(oldtaName, oldtaEmail);
        
           for(Label label:oldjtpsarray){
               
                     olddata.removeTAFromCell(label.textProperty(), oldnewName);
                        olddata.addTAToCell(label.textProperty(),oldtaName);
                
                
                    }
         };

};
        
   
    
    
