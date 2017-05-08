/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transaction;

import csg.CSGApp;
import csg.data.CSGData;
import csg.data.Recitation;
import csg.data.TeachingAssistant;
import csg.workspace.CSGRecitationWorkspace;
import csg.workspace.CSGWorkspace;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.control.Label;
import jtps.jTPS_Transaction;

/**
 *
 * @author runnan
 */
public class TA_RemoveTA_Transaction implements jTPS_Transaction{
    CSGApp app;
      CSGData olddata;
   String oldname;
   String oldemail;
   HashMap<String, Label> oldlabels;
    ArrayList<Label> oldarray;
      ArrayList<Recitation> firstTaRec;
      ArrayList<Recitation> secondTaRec;
      TeachingAssistant ta;
    CSGRecitationWorkspace rworkspace;
    public TA_RemoveTA_Transaction(CSGData data,String initName,String initEmail,HashMap<String, Label> labels,ArrayList<Label> initarray,TeachingAssistant initta,CSGApp initapp){
       app=initapp;
        ta=initta;
        olddata=data;
       oldname=initName;
      oldemail=initEmail;
      oldlabels=labels;
     oldarray=initarray;
      firstTaRec=data.getHaveFirstTaRec(ta);
      secondTaRec=data.getHaveSecondTaRec(ta);
       CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
        rworkspace=temp.getCsgRecitationWorkspace();
    }

    @Override
    public void doTransaction() {
        //remove again
           olddata.removeTA(oldname);
     
                for (Label label : oldlabels.values()) {
                    if (label.getText().equals(oldname)
                    || (label.getText().contains(oldname + "\n"))
                    || (label.getText().contains("\n" + oldname))) {
                        olddata.removeTAFromCell(label.textProperty(), oldname);
                    }
                }
        
        
       
    }

    @Override
    public void undoTransaction() {
        
               //add back
        olddata.addTA(oldname, oldemail);
        
        for(Label label:oldarray){
        olddata.addTAToCell(label.textProperty(), oldname);
        
        
        }
        
          for (Recitation re:firstTaRec){
                    re.setFirstTa(oldname);
                }
                  for (Recitation re:secondTaRec){
                    re.setSecondTa(oldname);
                }
          rworkspace.getRecitationTable().refresh();
        
    }
    
    
    
}
