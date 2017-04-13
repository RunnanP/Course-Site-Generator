/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import csg.CSGApp;
import csg.CSGAppProp;
import static csg.CSGAppProp.*;
import csg.data.CSGData;
import csg.data.TeachingAssistant;
import csg.file.CSGFiles;
import csg.file.CSGTimeSlot;
import static csg.style.CSGStyle.*;
import djf.ui.AppGUI;
import djf.ui.AppMessageDialogSingleton;
import djf.ui.AppYesNoCancelDialogSingleton;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import properties_manager.PropertiesManager;

/**
 *
 * @author runnan
 */
public class CSGController {
    CSGApp app;
    

    CSGController(CSGApp initapp) {
        app=initapp;
}   
    private void markWorkAsEdited(){
        AppGUI gui=app.getGUI();
        gui.getFileController().markAsEdited(gui);
        
        
    }

    
    //TA part
    public void handleAddTA() {
        CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
        CSGTAWorkspace workspace=temp.getTAWorkspace();
        TextField nameTextField = workspace.getNameTextField();
        TextField emailTextField = workspace.getEmailTextField();
        String name = nameTextField.getText();
        String email = emailTextField.getText();
        
        // WE'LL NEED TO ASK THE DATA SOME QUESTIONS TOO
        CSGData data = (CSGData)app.getDataComponent();
        
        // WE'LL NEED THIS IN CASE WE NEED TO DISPLAY ANY ERROR MESSAGES
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        // DID THE USER NEGLECT TO PROVIDE A TA NAME?
        if (name.isEmpty()) {
	    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_TA_NAME_TITLE), props.getProperty(MISSING_TA_NAME_MESSAGE));            
        }
        // DID THE USER NEGLECT TO PROVIDE A TA EMAIL?
        else if (email.isEmpty()) {
	    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_TA_EMAIL_TITLE), props.getProperty(MISSING_TA_EMAIL_MESSAGE));                        
        }
        // DOES A TA ALREADY HAVE THE SAME NAME OR EMAIL?
        else if (data.containsTA(name, email)) {
	    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(TA_NAME_AND_EMAIL_NOT_UNIQUE_TITLE), props.getProperty(TA_NAME_AND_EMAIL_NOT_UNIQUE_MESSAGE));                                    
        }
        // EVERYTHING IS FINE, ADD A NEW TA
        else {
            
            EmailValidator emailValidator=new EmailValidator();
          if(  emailValidator.validate(email)){
            
            // ADD THE NEW TA TO THE DATA
              data.addTA(name, email);
           // jTPS_Transaction transaction=new AddingTA_Transaction(data, name, email);
            //this.getJTPS().addTransaction(transaction);
            
            // CLEAR THE TEXT FIELDS
            nameTextField.setText("");
            emailTextField.setText("");
            
            // AND SEND THE CARET BACK TO THE NAME TEXT FIELD FOR EASY DATA ENTRY
            nameTextField.requestFocus();
            
            // WE'VE CHANGED STUFF
            markWorkAsEdited();
          }else {
              AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(WRONG_TA_EMAIL_TITLE), props.getProperty(WRONG_TA_EMAIL_MESSAGE)); 
          }
        }
    }

    public void handleUpdateTA() {
        CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
        CSGTAWorkspace workspace=temp.getCsgTAWorkspace();
         CSGData data = (CSGData)app.getDataComponent();
            TableView taTable = workspace.getTaTable();
            Object selectedItem = taTable.getSelectionModel().getSelectedItem();
             PropertiesManager props = PropertiesManager.getPropertiesManager();
        if (selectedItem != null) {
                TeachingAssistant ta = (TeachingAssistant)selectedItem;
                String taName = ta.getName();
                String taEmail=ta.getEmail();
                
                 //data.removeTA(taName);
                 String newName = workspace.getNameTextField().getText();
                 String newEmail =  workspace.getEmailTextField().getText();
                 EmailValidator emailValidator=new EmailValidator();
          if(  emailValidator.validate(newEmail)){
            
             data.removeTA(taName);
                 data.addTA(newName, newEmail);
                  HashMap<String, Label> labels = workspace.getOfficeHoursGridTACellLabels();
               ArrayList<Label> jtpsarray=new ArrayList<Label>();
                for (Label label : labels.values()) {
                    if (label.getText().equals(taName)
                    || (label.getText().contains(taName + "\n"))
                    || (label.getText().contains("\n" + taName))) {
                       data.removeTAFromCell(label.textProperty(), taName);
                        data.addTAToCell(label.textProperty(),newName);
                        jtpsarray.add(label);
                    }
                }
                 
                 workspace.getNameTextField().setText("");
                 workspace.getEmailTextField().setText("");
                 workspace.getNameTextField().requestFocus();
                 workspace.getAddBox().getChildren().remove(workspace.getUpdateButton());
                 workspace.getAddBox().getChildren().remove(workspace.getClearButton());
                 workspace.getAddBox().getChildren().add(workspace.getAddButton());
            
              // jTPS_Transaction transaction=new ChangeTAInfo_Transaction(data,workspace,taName,taEmail,newName,newEmail,labels,jtpsarray);
              // this.getJTPS().addTransaction(transaction);
               
               
                 if(!((taName.equals(newName))&&(taEmail.equals(newEmail)))){
                 markWorkAsEdited();
                 }
          }else {
              AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(WRONG_TA_EMAIL_TITLE), props.getProperty(WRONG_TA_EMAIL_MESSAGE)); 
          }
                 
        }
   
    }

    public void handleClearField() {
              PropertiesManager props = PropertiesManager.getPropertiesManager();
        CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
        CSGTAWorkspace workspace=temp.getCsgTAWorkspace();
       workspace.getNameTextField().setText("");
       workspace.getEmailTextField().setText("");
        workspace.getAddBox().getChildren().remove(workspace.getUpdateButton());
        workspace.getAddBox().getChildren().remove(workspace.getClearButton());
        workspace.getAddBox().getChildren().add(workspace.getAddButton());
        
        
    
    }

    public void handleKeyPress(KeyCode code) {
         if (code == KeyCode.DELETE ) {
            // GET THE TABLE
            CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
            CSGTAWorkspace workspace=temp.getTAWorkspace();
            TableView taTable = workspace.getTaTable();
            
            // IS A TA SELECTED IN THE TABLE?
            Object selectedItem = taTable.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                // GET THE TA AND REMOVE IT
                TeachingAssistant ta = (TeachingAssistant)selectedItem;
               
                String taName = ta.getName();
               String jtpstaname=taName;
                String jtpstaemail=ta.getEmail();
                CSGData data = (CSGData)app.getDataComponent();
                data.removeTA(taName);
                
                // AND BE SURE TO REMOVE ALL THE TA'S OFFICE HOURS
                HashMap<String, Label> labels = workspace.getOfficeHoursGridTACellLabels();
                ArrayList<Label> jtpsarray=new ArrayList<>(); 
                for (Label label : labels.values()) {
                    if (label.getText().equals(taName)
                    || (label.getText().contains(taName + "\n"))
                    || (label.getText().contains("\n" + taName))) {
                      data.removeTAFromCell(label.textProperty(), taName);
                        jtpsarray.add(label);
                    }
                }
                // WE'VE CHANGED STUFF
                //jTPS_Transaction transaction=new RemovingTA_Transaction(data, jtpstaname,jtpstaemail,labels,jtpsarray);
                //this.getJTPS().addTransaction(transaction);
                markWorkAsEdited();
            }
        }
    }
    
    
    public void handleSubButtonPress(){
        
         CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
            CSGTAWorkspace workspace=temp.getTAWorkspace();
            TableView taTable = workspace.getTaTable();
            
            // IS A TA SELECTED IN THE TABLE?
            Object selectedItem = taTable.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                // GET THE TA AND REMOVE IT
                TeachingAssistant ta = (TeachingAssistant)selectedItem;
               
                String taName = ta.getName();
               String jtpstaname=taName;
                String jtpstaemail=ta.getEmail();
                CSGData data = (CSGData)app.getDataComponent();
                data.removeTA(taName);
                
                // AND BE SURE TO REMOVE ALL THE TA'S OFFICE HOURS
                HashMap<String, Label> labels = workspace.getOfficeHoursGridTACellLabels();
                ArrayList<Label> jtpsarray=new ArrayList<>(); 
                for (Label label : labels.values()) {
                    if (label.getText().equals(taName)
                    || (label.getText().contains(taName + "\n"))
                    || (label.getText().contains("\n" + taName))) {
                      data.removeTAFromCell(label.textProperty(), taName);
                        jtpsarray.add(label);
                    }
                }
                // WE'VE CHANGED STUFF
                //jTPS_Transaction transaction=new RemovingTA_Transaction(data, jtpstaname,jtpstaemail,labels,jtpsarray);
                //this.getJTPS().addTransaction(transaction);
                markWorkAsEdited();
            }
        
    }

    public void handleAppearUpdateTA(CSGTAWorkspace workspace) {
             try{
       TableView taTable = workspace.getTaTable();
            Object selectedItem = taTable.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                TeachingAssistant ta = (TeachingAssistant)selectedItem;
                String taName = ta.getName();
                String taEmail=ta.getEmail();
                workspace.getNameTextField().setText(taName);
                workspace.getEmailTextField().setText(taEmail);
                workspace.getAddBox().getChildren().remove(workspace.getAddButton());
              workspace.getAddBox().getChildren().add(workspace.getUpdateButton());
             workspace.getAddBox().getChildren().add(workspace.getClearButton());
   
            }
       } catch(Exception e){
         
       }
    }

    public void handleChangeStartTime(ComboBox startTimeComboBox, Label startTimeComboBoxLabel) {
           
       
               
    }

    public void handleChangeEndTime(ComboBox endTimeComboBox, Label endTimeComboBoxLabel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
        public void handleCellToggle(Pane pane) {
        // GET THE TABLE
        CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
        CSGTAWorkspace workspace=temp.getCsgTAWorkspace();
        TableView taTable = workspace.getTaTable();
        
        // IS A TA SELECTED IN THE TABLE?
        Object selectedItem = taTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // GET THE TA
            TeachingAssistant ta = (TeachingAssistant)selectedItem;
            String taName = ta.getName();
            CSGData data = (CSGData)app.getDataComponent();
            String cellKey = pane.getId();
            
            // AND TOGGLE THE OFFICE HOURS IN THE CLICKED CELL
         
               data.toggleTAOfficeHours(cellKey, taName);
            //jTPS_Transaction transaction=new ToggleOfficeHour_Transaction(data,cellKey,taName,selectedItem);
             //this.getJTPS().addTransaction(transaction);
            
            
            // WE'VE CHANGED STUFF
            markWorkAsEdited();
        }
    }
   

     public void changeTime(){
        CSGData data = (CSGData)app.getDataComponent();
        CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
        CSGTAWorkspace workspace=temp.getCsgTAWorkspace();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        ComboBox startComboBox = workspace.getOfficeHour(true);
        ComboBox endComboBox = workspace.getOfficeHour(false);
        int startTime = data.getStartHour();
        int endTime = data.getEndHour();
        
        int newStartTime = startComboBox.getSelectionModel().getSelectedIndex();
        int newEndTime = endComboBox.getSelectionModel().getSelectedIndex();
        
        if(newStartTime > endTime || newEndTime < startTime){
            startComboBox.getSelectionModel().select(startTime);
            endComboBox.getSelectionModel().select(endTime);
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            dialog.show(props.getProperty(CSGAppProp.START_OVER_END_TITLE.toString()), props.getProperty(CSGAppProp.START_OVER_END_MESSAGE.toString()));
            return;
        }
        ArrayList<CSGTimeSlot> officeHours = CSGTimeSlot.buildOfficeHoursList(data);
        if(officeHours.isEmpty()){
            workspace.getOfficeHoursGridPane().getChildren().clear();
            data.initHours("" + newStartTime, "" + newEndTime);
        }
        String firsttime = officeHours.get(0).getTime();
         System.out.println(firsttime);
        int firsthour = Integer.parseInt(firsttime.substring(0, firsttime.indexOf('_')));
        if(firsttime.contains("pm"))
            firsthour += 12;
        if(firsttime.contains("12"))
            firsthour -= 12;
        String lasttime = officeHours.get(officeHours.size() - 1).getTime();
        int lasthour = Integer.parseInt(lasttime.substring(0, lasttime.indexOf('_')));
        if(lasttime.contains("pm"))
            lasthour += 12;
        if(lasttime.contains("12"))
            lasthour -= 12;
        if(firsthour < newStartTime || lasthour + 1 > newEndTime){
            AppYesNoCancelDialogSingleton yesNoDialog = AppYesNoCancelDialogSingleton.getSingleton();
            yesNoDialog.show(props.getProperty(CSGAppProp.OFFICE_HOURS_REMOVED_TITLE.toString()), props.getProperty(CSGAppProp.OFFICE_HOURS_REMOVED_MESSAGE).toString());
            String selection = yesNoDialog.getSelection();
            if (!selection.equals(AppYesNoCancelDialogSingleton.YES)){
                startComboBox.getSelectionModel().select(startTime);
                endComboBox.getSelectionModel().select(endTime);
                return;
            }
        }
        
        workspace.getOfficeHoursGridPane().getChildren().clear();
        data.changeTime(newStartTime, newEndTime, officeHours);
        
        markWorkAsEdited();
    }

   public void handleGridCellMouseExited(Pane pane) {
        String cellKey = pane.getId();
        CSGData data = (CSGData)app.getDataComponent();
        int column = Integer.parseInt(cellKey.substring(0, cellKey.indexOf("_")));
        int row = Integer.parseInt(cellKey.substring(cellKey.indexOf("_") + 1));
          CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
        CSGTAWorkspace workspace=temp.getCsgTAWorkspace();

        Pane mousedOverPane = workspace.getTACellPane(data.getCellKey(column, row));
        mousedOverPane.getStyleClass().clear();
        mousedOverPane.getStyleClass().add(CLASS_OFFICE_HOURS_GRID_TA_CELL_PANE);

        // THE MOUSED OVER COLUMN HEADER
        Pane headerPane = workspace.getOfficeHoursGridDayHeaderPanes().get(data.getCellKey(column, 0));
        headerPane.getStyleClass().remove(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);

        // THE MOUSED OVER ROW HEADERS
        headerPane = workspace.getOfficeHoursGridTimeCellPanes().get(data.getCellKey(0, row));
        headerPane.getStyleClass().remove(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);
        headerPane = workspace.getOfficeHoursGridTimeCellPanes().get(data.getCellKey(1, row));
        headerPane.getStyleClass().remove(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);
        
        // AND NOW UPDATE ALL THE CELLS IN THE SAME ROW TO THE LEFT
        for (int i = 2; i < column; i++) {
            cellKey = data.getCellKey(i, row);
            Pane cell = workspace.getTACellPane(cellKey);
            cell.getStyleClass().remove(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);
            cell.getStyleClass().add(CLASS_OFFICE_HOURS_GRID_TA_CELL_PANE);
        }

        // AND THE CELLS IN THE SAME COLUMN ABOVE
        for (int i = 1; i < row; i++) {
            cellKey = data.getCellKey(column, i);
            Pane cell = workspace.getTACellPane(cellKey);
            cell.getStyleClass().remove(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);
            cell.getStyleClass().add(CLASS_OFFICE_HOURS_GRID_TA_CELL_PANE);
        }
    }

    public void handleGridCellMouseEntered(Pane pane) {
        String cellKey = pane.getId();
        CSGData data = (CSGData)app.getDataComponent();
        int column = Integer.parseInt(cellKey.substring(0, cellKey.indexOf("_")));
        int row = Integer.parseInt(cellKey.substring(cellKey.indexOf("_") + 1));
        CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
        CSGTAWorkspace workspace=temp.getCsgTAWorkspace();
        // THE MOUSED OVER PANE
        Pane mousedOverPane = workspace.getTACellPane(data.getCellKey(column, row));
        mousedOverPane.getStyleClass().clear();
        mousedOverPane.getStyleClass().add(CLASS_HIGHLIGHTED_GRID_CELL);
        
        // THE MOUSED OVER COLUMN HEADER
        Pane headerPane = workspace.getOfficeHoursGridDayHeaderPanes().get(data.getCellKey(column, 0));
        headerPane.getStyleClass().add(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);
        
        // THE MOUSED OVER ROW HEADERS
        headerPane = workspace.getOfficeHoursGridTimeCellPanes().get(data.getCellKey(0, row));
        headerPane.getStyleClass().add(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);
        headerPane = workspace.getOfficeHoursGridTimeCellPanes().get(data.getCellKey(1, row));
        headerPane.getStyleClass().add(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);
        
        // AND NOW UPDATE ALL THE CELLS IN THE SAME ROW TO THE LEFT
        for (int i = 2; i < column; i++) {
            cellKey = data.getCellKey(i, row);
            Pane cell = workspace.getTACellPane(cellKey);
            cell.getStyleClass().add(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);
        }

        // AND THE CELLS IN THE SAME COLUMN ABOVE
        for (int i = 1; i < row; i++) {
            cellKey = data.getCellKey(column, i);
            Pane cell = workspace.getTACellPane(cellKey);
            cell.getStyleClass().add(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);
        }
    }


     
     
    
      
  
}