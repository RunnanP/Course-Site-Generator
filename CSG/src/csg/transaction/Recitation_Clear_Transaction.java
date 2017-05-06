/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transaction;

import csg.data.Recitation;
import csg.workspace.CSGRecitationWorkspace;
import jtps.jTPS_Transaction;

/**
 *
 * @author runnan
 */
public class Recitation_Clear_Transaction implements jTPS_Transaction{
   CSGRecitationWorkspace workspace;
   String section;
   String instructor;
   String daytime;
   String location;
   String firstTA;
   String secondTA;
    Recitation select;
    
    
    
    
    public Recitation_Clear_Transaction(CSGRecitationWorkspace initworkspace){
        workspace=initworkspace;   
        section=workspace.getSectionTextField().getText();
       instructor=workspace.getInstructorTextField().getText();
        daytime=workspace.getDaytimeTextField().getText();
       location=workspace.getLocationTextField().getText();
        firstTA=workspace.getFirstTAComboBox().getValue();
        secondTA=workspace.getSecondTAComboBox().getValue(); 
         select=(Recitation)workspace.getRecitationTable().getSelectionModel().getSelectedItem();
    }
    @Override
    public void doTransaction() {
         workspace.getSectionTextField().setText("");
       workspace.getInstructorTextField().setText("");
        workspace.getDaytimeTextField().setText("");
       workspace.getLocationTextField().setText("");
        workspace.getFirstTAComboBox().setValue("");
        workspace.getSecondTAComboBox().setValue(""); 
        workspace.getRecitationTable().getSelectionModel().clearSelection();
    }

    @Override
    public void undoTransaction() {
        workspace.getRecitationTable().getSelectionModel().select(select);
          workspace.getSectionTextField().setText(section);
       workspace.getInstructorTextField().setText(instructor);
        workspace.getDaytimeTextField().setText(daytime);
       workspace.getLocationTextField().setText(location);
        workspace.getFirstTAComboBox().setValue(firstTA);
        workspace.getSecondTAComboBox().setValue(secondTA); 
        
    }
    
}
