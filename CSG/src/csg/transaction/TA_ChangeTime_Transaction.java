/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transaction;

import csg.CSGApp;
import csg.data.CSGData;
import csg.file.CSGTimeSlot;
import csg.workspace.CSGTAWorkspace;
import csg.workspace.CSGWorkspace;
import csg.workspace.WorkspacePart;
import java.util.ArrayList;
import javafx.scene.control.ComboBox;
import jtps.jTPS_Transaction;
import properties_manager.PropertiesManager;

/**
 *
 * @author runnan
 */
public class TA_ChangeTime_Transaction implements jTPS_Transaction{
    private CSGApp app;
    private int startTime;
    private int endTime;
    private int newStartTime;
    private int newEndTime;
    private ArrayList<CSGTimeSlot> officeHours;
    
    public TA_ChangeTime_Transaction(CSGApp app,int initnewStartTime,int initnewEndTime,int initStartTime,int initEndTime){
        this.app=app;
        CSGData data=(CSGData)app.getDataComponent();
        CSGWorkspace temp=(CSGWorkspace)app.getWorkspaceComponent();
        CSGTAWorkspace workspace=temp.getTAWorkspace();
        PropertiesManager props=PropertiesManager.getPropertiesManager();
        ComboBox startComboBox=workspace.getOfficeHour(true);
        ComboBox endComboBox=workspace.getOfficeHour(false);
        startTime=initStartTime;
        endTime=initEndTime;
        newStartTime=initnewStartTime;
        newEndTime=initnewEndTime;


       //        startTime=data.getStartHour();
//        endTime=data.getEndHour();
//        newStartTime=startComboBox.getSelectionModel().getSelectedIndex();
//        newEndTime=endComboBox.getSelectionModel().getSelectedIndex();
        officeHours=CSGTimeSlot.buildOfficeHoursList(data);
        
    }
    
    
    @Override
    public void doTransaction() {
       CSGWorkspace temp=(CSGWorkspace)app.getWorkspaceComponent();
       CSGTAWorkspace workspace=temp.getTAWorkspace();
       workspace.getOfficeHoursGridPane().getChildren().clear();
       ((CSGData)app.getDataComponent()).changeTime(newStartTime, newEndTime, officeHours);
               
     }

    @Override
    public void undoTransaction() {
              CSGWorkspace temp=(CSGWorkspace)app.getWorkspaceComponent();
       CSGTAWorkspace workspace=temp.getTAWorkspace();
       workspace.getOfficeHoursGridPane().getChildren().clear();
       ((CSGData)app.getDataComponent()).changeTime(startTime, endTime, officeHours);
    }
    
}
