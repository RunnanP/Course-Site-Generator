/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transaction;

import csg.data.ScheduleItem;
import csg.workspace.CSGScheduleWorkspace;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import jtps.jTPS_Transaction;

/**
 *
 * @author runnan
 */
public class Schedule_Clear_Transaction implements jTPS_Transaction{
  CSGScheduleWorkspace workspace;
  String type;
  String date;
  String time;
  String title;
  String topic;
  String link;
  String criteria;
    ScheduleItem select;
  
    
    
    public Schedule_Clear_Transaction(CSGScheduleWorkspace initWorkspace){
        workspace=initWorkspace;
        type=  workspace.getTypeComboBox().getValue();       
      date= workspace.getDatePicker().getValue().toString();
       time= workspace.getTimeTextField().getText();
       title=workspace.getTitleTextField().getText();
        topic=workspace.getTopicTextField().getText();
        link=workspace.getLinkTextField().getText();
        criteria=workspace.getCriteriaTextField().getText();
        select=(ScheduleItem)workspace.getScheduleItemsTable().getSelectionModel().getSelectedItem();
        
    }
    @Override
    public void doTransaction() {
        workspace.getTypeComboBox().setValue("");       
       workspace.getDatePicker().setValue(LocalDate.now());
        workspace.getTimeTextField().setText("");
       workspace.getTitleTextField().setText("");
        workspace.getTopicTextField().setText("");
        workspace.getLinkTextField().setText("");
        workspace.getCriteriaTextField().setText("");
           workspace.getScheduleItemsTable().getSelectionModel().clearSelection();
    }

    @Override
    public void undoTransaction() {
        workspace.getScheduleItemsTable().getSelectionModel().select(select);
         workspace.getTypeComboBox().setValue(type);       
      try {
          workspace.loadChooseDate(date);
      } catch (ParseException ex) {
          Logger.getLogger(Schedule_Clear_Transaction.class.getName()).log(Level.SEVERE, null, ex);
      }
        workspace.getTimeTextField().setText(time);
       workspace.getTitleTextField().setText(title);
        workspace.getTopicTextField().setText(topic);
        workspace.getLinkTextField().setText(link);
        workspace.getCriteriaTextField().setText(criteria);
           
    }
    
}
