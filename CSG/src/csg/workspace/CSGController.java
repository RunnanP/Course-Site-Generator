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
import csg.data.Recitation;
import csg.data.ScheduleItem;
import csg.data.Student;
import csg.data.TeachingAssistant;
import csg.data.Team;
import csg.file.CSGFiles;
import csg.file.CSGTimeSlot;
import static csg.style.CSGStyle.*;
import csg.transaction.*;
import csg.transaction.TA_AddTA_Transaction;
import csg.transaction.TA_ChangeTAInfo_Transaction;
import csg.transaction.TA_ChangeTime_Transaction;
import csg.transaction.TA_RemoveTA_Transaction;
import csg.transaction.TA_ToggleOfficeHour_Transaction;
import static djf.settings.AppPropertyType.LOAD_WORK_TITLE;
import static djf.settings.AppStartupConstants.PATH_WORK;
import static djf.settings.AppStartupConstants.*;
import static djf.settings.AppPropertyType.*;
import djf.ui.AppGUI;
import djf.ui.AppMessageDialogSingleton;
import djf.ui.AppYesNoCancelDialogSingleton;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import jtps.jTPS_Transaction;
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

    //course part//////////////////////////////////////////////////////////////////////////////////////
    public void handleChangeExportDir() throws IOException{
        PropertiesManager props = PropertiesManager.getPropertiesManager();

        DirectoryChooser fc = new DirectoryChooser();
        fc.setInitialDirectory(new File(PATH_WORK));
	fc.setTitle(props.getProperty(CHOOSE_EXPORT_DIR));
        File selectedFile = fc.showDialog(app.getGUI().getWindow());
        
        if(selectedFile!=null){
            
            
            
            try{
            String path=selectedFile.getAbsolutePath();
            CSGData data = (CSGData)app.getDataComponent();
          //  data.setExportDir(path);
          CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
        CSGCourseWorkspace workspace=temp.getCourseWorkspace();
        
        String oldpath=data.getExportDir();
      //  data.setExportDir(path);
       // workspace.setExporDirDisplayAddressString(path);
       
        jTPS_Transaction transaction=new Course_ChangeExportDir_Transaction(data, workspace,path,oldpath);
            app.getJTPS().addTransaction(transaction);
        
            }catch(Exception e) {
                
              
                
            }   
        
        
        
        }
        
     
        
    }
    
    public void handleSelectTempleDir(){
        
        
        
               PropertiesManager props = PropertiesManager.getPropertiesManager();

        DirectoryChooser fc = new DirectoryChooser();
        fc.setInitialDirectory(new File(PATH_WORK));
	fc.setTitle(props.getProperty(CHOOSE_TEMPLE_DIR));
        File selectedFile = fc.showDialog(app.getGUI().getWindow());
        
        if(selectedFile!=null){
            
            
            
       //     try{
            String path=selectedFile.getPath();
            CSGData data = (CSGData)app.getDataComponent();
         //   data.setSiteTempleDir(path);
              CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
        CSGCourseWorkspace workspace=temp.getCourseWorkspace();
        CSGFiles file=(CSGFiles)app.getFileComponent();
        
        
        String oldpath=data.getSiteTempleDir();
        
     
       //  data.setSiteTempleDir(path);
      //  workspace.setTemplatesDirLabel(path);
        
        
         
       
           //     file.loadData(app.getDataComponent(), path+"\\\\data\\\\savefordeter.json");
         
//                 File f1=new File(path+"\\\\index.html");
//                if(f1.exists()){
//                    data.setJhome(true);
//                }else {
//                   data.setJhome(false);
//               }
//                
//                
//                File f2=new File(path+"\\\\syllabus.html");
//               if(f2.exists()){
//                   data.setJsyllabus(true);
//               }else {
//                   data.setJsyllabus(false);
//               }
//                
//                File f3=new File(path+"\\\\schedule.html");
//                if(f3.exists()){
//                   data.setJschedule(true);
//               }else {
//                   data.setJschedule(false);
//               }
//                
//                
//                
//                
//                
//                File f4=new File(path+"\\\\hws.html");
//                if(f4.exists()){
//                   data.setJhws(true);
//               }else {
//                   data.setJhws(false);
//               }
//                
//                File f5=new File(path+"\\\\projects.html");
//                if(f5.exists()){
//                   data.setJproject(true);
//               }else {
//                   data.setJproject(false);
//               }
                
                
               
                
//               ArrayList<String> csslist=new ArrayList<>(); 
//                
//                File f6=new File(path+"\\\\css\\\\");
//                File flist[]=f6.listFiles();
//                for (File f:flist){
//                    if (!f.isDirectory()){
//                        if(f.getName().endsWith(".css")){
//                           // workspace.getStyleSheetComboBox().getItems().add(f.getName());
//                           csslist.add(f.getName());
//                        }
//                    }
//                    
//                }
             //     workspace.setTemplatesDirLabel(path);
               
                  
              jTPS_Transaction transaction=new Course_ChangeTemplateDir_Transaction(app,data, workspace,file,path,oldpath);
            app.getJTPS().addTransaction(transaction);
                  
                  
//            }catch(Exception e) {
//                
//              
//              
//            }   
   
        markWorkAsEdited();
         
        }
        
    }
    
    
    public void handleChangeFirstImage(){
        
           PropertiesManager props = PropertiesManager.getPropertiesManager();

        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(PATH_WORK));
	//fc.setTitle(props.getProperty(CHOOSE_EXPORT_DIR));
        File selectedFile = fc.showOpenDialog(app.getGUI().getWindow());
      
        if(selectedFile!=null){
              
            String path=selectedFile.getAbsolutePath();
            CSGData data = (CSGData)app.getDataComponent();
            System.out.println(path);
            //data.setFirstImageAdd("file:./images/2.png");
            
            String oldpath=data.getFirstImageAdd();
          //  data.setFirstImageAdd("file:"+path);
              jTPS_Transaction transaction=new Course_ChangeBannerImage_Transaction(data,"file:"+path,oldpath);
            app.getJTPS().addTransaction(transaction);
        markWorkAsEdited();
        
        
        }
        
    }
    
    public void handleChangeSecondImage(){
        
            PropertiesManager props = PropertiesManager.getPropertiesManager();

        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(PATH_WORK));
	//fc.setTitle(props.getProperty(CHOOSE_EXPORT_DIR));
        File selectedFile = fc.showOpenDialog(app.getGUI().getWindow());
      
        if(selectedFile!=null){
              
            String path=selectedFile.getAbsolutePath();
            CSGData data = (CSGData)app.getDataComponent();
            System.out.println(path);
            //data.setFirstImageAdd("file:./images/2.png");
          //  data.setSecondImageAdd("file:"+path);
           String oldpath=data.getSecondImageAdd();
          //  data.setFirstImageAdd("file:"+path);
              jTPS_Transaction transaction=new Course_ChangeLeftImage_Transaction(data,"file:"+path,oldpath);
            app.getJTPS().addTransaction(transaction);
        markWorkAsEdited();
        
        
        }
        
    }
    
    public void handleChangeThirdImage(){
                PropertiesManager props = PropertiesManager.getPropertiesManager();

        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(PATH_WORK));
	//fc.setTitle(props.getProperty(CHOOSE_EXPORT_DIR));
        File selectedFile = fc.showOpenDialog(app.getGUI().getWindow());
      
        if(selectedFile!=null){
              
            String path=selectedFile.getAbsolutePath();
            CSGData data = (CSGData)app.getDataComponent();
            System.out.println(path);
            //data.setFirstImageAdd("file:./images/2.png");
          //  data.setThirdImageAdd("file:"+path);
           String oldpath=data.getThirdImageAdd();
          //  data.setFirstImageAdd("file:"+path);
              jTPS_Transaction transaction=new Course_ChangeRightImage_Transaction(data,"file:"+path,oldpath);
            app.getJTPS().addTransaction(transaction);
        
        
        markWorkAsEdited();
        }
        
    }
    
    
    //TA part///////////////////////////////////////////////////////////////////////////////////////////////
    public void handleAddTA() {
        CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
        CSGTAWorkspace workspace=temp.getTAWorkspace();
        TextField nameTextField = workspace.getNameTextField();
        TextField emailTextField = workspace.getEmailTextField();
        String name = nameTextField.getText();
        String email = emailTextField.getText();
     
        CSGData data = (CSGData)app.getDataComponent();
       
        PropertiesManager props = PropertiesManager.getPropertiesManager();
    
        if (name.isEmpty()) {
	    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_TA_NAME_TITLE), props.getProperty(MISSING_TA_NAME_MESSAGE));            
        }
        // DID THE USER NEGLECT TO PROVIDE A TA EMAIL?
        else if (email.isEmpty()) {
	    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_TA_EMAIL_TITLE), props.getProperty(MISSING_TA_EMAIL_MESSAGE));                        
        }
        else if (data.containsTA(name, email)) {
	    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(TA_NAME_AND_EMAIL_NOT_UNIQUE_TITLE), props.getProperty(TA_NAME_AND_EMAIL_NOT_UNIQUE_MESSAGE));                                    
        }
     
        else {
            
            EmailValidator emailValidator=new EmailValidator();
          if(  emailValidator.validate(email)){
            
           
            //  data.addTA(name, email);
              
              
              //addTAtoReciCombobox(name);
            jTPS_Transaction transaction=new TA_AddTA_Transaction(data, name, email);
            app.getJTPS().addTransaction(transaction);
            
          
            nameTextField.setText("");
            emailTextField.setText("");
            
           
            nameTextField.requestFocus();
            
          
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
         //    removeTAfromReciCombobox(taName);
                 data.addTA(newName, newEmail);
         //   addTAtoReciCombobox(newName);
            
                  HashMap<String, Label> labels = workspace.getOfficeHoursGridTACellLabels();
               ArrayList<Label> jtpsarray=new ArrayList<Label>();
                for (Label label : labels.values()) {
                    if (label.getText().equals(taName)
                    || (label.getText().contains(taName + "\n"))
                    || (label.getText().contains("\n" + taName))) {
                  //     data.removeTAFromCell(label.textProperty(), taName);
                    //    data.addTAToCell(label.textProperty(),newName);
                        jtpsarray.add(label);
                    }
                }
                 
                 workspace.getNameTextField().setText("");
                 workspace.getEmailTextField().setText("");
                 workspace.getNameTextField().requestFocus();
                 workspace.getAddBox().getChildren().remove(workspace.getUpdateButton());
                 workspace.getAddBox().getChildren().remove(workspace.getClearButton());
                 workspace.getAddBox().getChildren().add(workspace.getAddButton());
            
               jTPS_Transaction transaction=new TA_ChangeTAInfo_Transaction(data,workspace,taName,taEmail,newName,newEmail,labels,jtpsarray);
               app.getJTPS().addTransaction(transaction);
               
               
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
                      TableView taTable = workspace.getTaTable();
             taTable.getSelectionModel().clearSelection();
        
    
    }

    public void handleKeyPress(KeyCode code) {
         if (code == KeyCode.DELETE ) {
       
            CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
            CSGTAWorkspace workspace=temp.getTAWorkspace();
            TableView taTable = workspace.getTaTable();
         
            Object selectedItem = taTable.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                TeachingAssistant ta = (TeachingAssistant)selectedItem;
               
                String taName = ta.getName();
               String jtpstaname=taName;
                String jtpstaemail=ta.getEmail();
                CSGData data = (CSGData)app.getDataComponent();
               // data.removeTA(taName);
                
                // AND BE SURE TO REMOVE ALL THE TA'S OFFICE HOURS
                HashMap<String, Label> labels = workspace.getOfficeHoursGridTACellLabels();
                ArrayList<Label> jtpsarray=new ArrayList<>(); 
                for (Label label : labels.values()) {
                    if (label.getText().equals(taName)
                    || (label.getText().contains(taName + "\n"))
                    || (label.getText().contains("\n" + taName))) {
                   //   data.removeTAFromCell(label.textProperty(), taName);
                        jtpsarray.add(label);
                    }
                }
                // WE'VE CHANGED STUFF
                jTPS_Transaction transaction=new TA_RemoveTA_Transaction(data, jtpstaname,jtpstaemail,labels,jtpsarray);
                app.getJTPS().addTransaction(transaction);
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
            //    data.removeTA(taName);
                
                // AND BE SURE TO REMOVE ALL THE TA'S OFFICE HOURS
                HashMap<String, Label> labels = workspace.getOfficeHoursGridTACellLabels();
                ArrayList<Label> jtpsarray=new ArrayList<>(); 
                for (Label label : labels.values()) {
                    if (label.getText().equals(taName)
                    || (label.getText().contains(taName + "\n"))
                    || (label.getText().contains("\n" + taName))) {
                 //     data.removeTAFromCell(label.textProperty(), taName);
                        jtpsarray.add(label);
                    }
                }
                // WE'VE CHANGED STUFF
              jTPS_Transaction transaction=new TA_RemoveTA_Transaction(data, jtpstaname,jtpstaemail,labels,jtpsarray);
                app.getJTPS().addTransaction(transaction);
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

//    public void handleChangeStartTime(ComboBox startTimeComboBox, Label startTimeComboBoxLabel) {
//           
//       
//               
//    }
//
//    public void handleChangeEndTime(ComboBox endTimeComboBox, Label endTimeComboBoxLabel) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    
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
         
             //  data.toggleTAOfficeHours(cellKey, taName);
            jTPS_Transaction transaction=new TA_ToggleOfficeHour_Transaction(data,cellKey,taName,selectedItem);
             app.getJTPS().addTransaction(transaction);
            
            
            // WE'VE CHANGED STUFF
            markWorkAsEdited();
        }
    }
   

     public void changeTime(){
         markWorkAsEdited();
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
            //markWorkAsEdited();
            return;
        }
        ArrayList<CSGTimeSlot> officeHours = CSGTimeSlot.buildOfficeHoursList(data);
        if(officeHours.isEmpty()){
            workspace.getOfficeHoursGridPane().getChildren().clear();
            data.initHours("" + newStartTime, "" + newEndTime);
        }
        String firsttime = officeHours.get(0).getTime();
        // System.out.println(firsttime);
        int firsthour = Integer.parseInt(firsttime.substring(0, firsttime.indexOf('_')));
        if(firsttime.contains("pm")){
            firsthour += 12;
        }
        
        if(firsttime.contains("12")){
            firsthour -= 12;
        }
        String lasttime = officeHours.get(officeHours.size() - 1).getTime();
        int lasthour = Integer.parseInt(lasttime.substring(0, lasttime.indexOf('_')));
        if(lasttime.contains("pm")){
            lasthour += 12;
        }
            
        if(lasttime.contains("12")){
            lasthour -= 12;
        }
        if(firsthour < newStartTime || lasthour + 1 > newEndTime){
            AppYesNoCancelDialogSingleton yesNoDialog = AppYesNoCancelDialogSingleton.getSingleton();
            yesNoDialog.show(props.getProperty(CSGAppProp.OFFICE_HOURS_REMOVED_TITLE.toString()), props.getProperty(CSGAppProp.OFFICE_HOURS_REMOVED_MESSAGE).toString());
            String selection = yesNoDialog.getSelection();
            if (!selection.equals(AppYesNoCancelDialogSingleton.YES)){
                startComboBox.getSelectionModel().select(startTime);
                endComboBox.getSelectionModel().select(endTime);
               // markWorkAsEdited();
                return;
            }
        }
        
    //    workspace.getOfficeHoursGridPane().getChildren().clear();
     //   data.changeTime(newStartTime, newEndTime, officeHours);
        

        jTPS_Transaction transaction=new TA_ChangeTime_Transaction(app,newStartTime,newEndTime,startTime,endTime);
             app.getJTPS().addTransaction(transaction);
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


     ////////recitation part/////////////////////////////////////////////////////////////////
     public void handleRecitationUpdate(){
            CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
        CSGRecitationWorkspace workspace=temp.getCsgRecitationWorkspace();
         CSGData data = (CSGData)app.getDataComponent();
            TableView recitationTable = workspace.getRecitationTable();
            Object selectedItem = recitationTable.getSelectionModel().getSelectedItem();
             PropertiesManager props = PropertiesManager.getPropertiesManager();
       
        if(workspace.getSectionTextField().getText()+""==""){
             AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	           dialog.show(props.getProperty(WRONG_RECITATION_TITLE), props.getProperty(PROVE_RECITATION_SECTION));  
                  return;
        }
         String initsection=workspace.getSectionTextField().getText()+"";
         String initinstructor=workspace.getInstructorTextField().getText()+"";
         String initdaytime=workspace.getDaytimeTextField().getText()+"";
         String initlocation=workspace.getLocationTextField().getText()+"";
         String initFirstta=workspace.getFirstTAComboBox().getValue()+"";
         String initSecondta=workspace.getSecondTAComboBox().getValue()+"";
         Recitation newreci=new Recitation(initsection, initinstructor,initdaytime,initlocation,initFirstta,initSecondta);
          if (selectedItem == null) {
             ObservableList<Recitation> old=data.getRecitations();
              
              for(Recitation k:old){
                    if(k.getSection().equals(initsection)){
                            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                            dialog.show(props.getProperty(WRONG_RECITATION_TITLE), props.getProperty(WRONG_RECITATION_MESSAGE));  
                            return;
                     }    
             }
                  
                  //data.getRecitations().add(newreci);
                  jTPS_Transaction transaction=new Recitation_AddRecutation_Transaction(data, newreci,initsection);
                  app.getJTPS().addTransaction(transaction);
                  
                  
                            workspace.getSectionTextField().setText("");
                                            workspace.getInstructorTextField().setText("");
                                                 workspace.getDaytimeTextField().setText("");
                                                workspace.getLocationTextField().setText("");
                                    workspace.getFirstTAComboBox().setValue("");
                                        workspace.getSecondTAComboBox().setValue("");
                                        
                            
                                        
                                        
                                        markWorkAsEdited();             
            
              
               
        }else{
         Recitation oldreci=(Recitation)selectedItem;
//         data.getRecitations().remove(oldreci);
//         data.getRecitations().add(newreci);
         
         jTPS_Transaction transaction=new Recitation_ChangeRecitationInfo_Transaction(data, newreci,oldreci);
                  app.getJTPS().addTransaction(transaction);
         
            workspace.getSectionTextField().setText("");
       workspace.getInstructorTextField().setText("");
        workspace.getDaytimeTextField().setText("");
       workspace.getLocationTextField().setText("");
        workspace.getFirstTAComboBox().setValue("");
        workspace.getSecondTAComboBox().setValue("");
             markWorkAsEdited();
            
        }
      
        }
     
     
     public void handleRecitationClear(){
             PropertiesManager props = PropertiesManager.getPropertiesManager();
        CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
        CSGRecitationWorkspace workspace=temp.getCsgRecitationWorkspace();
       workspace.getSectionTextField().setText("");
       workspace.getInstructorTextField().setText("");
        workspace.getDaytimeTextField().setText("");
       workspace.getLocationTextField().setText("");
        workspace.getFirstTAComboBox().setValue("");
        workspace.getSecondTAComboBox().setValue("");
                      TableView reTable = workspace.getRecitationTable();
             reTable.getSelectionModel().clearSelection();
      
        
     }
     
     public void handleEditRecitation(){
           CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
        CSGRecitationWorkspace workspace=temp.getCsgRecitationWorkspace();
         CSGData data = (CSGData)app.getDataComponent();
            TableView recitationTable = workspace.getRecitationTable();
            Object selectedItem = recitationTable.getSelectionModel().getSelectedItem();
             PropertiesManager props = PropertiesManager.getPropertiesManager();
        if (selectedItem != null) {
                Recitation reci = (Recitation)selectedItem;
                String section = reci.getSection();
                String instructor=reci.getInstructor();
                String daytime=reci.getDaytime();
                String location=reci.getLocation();
                String firstTa=reci.getFirstTa();
                String secondTa=reci.getSecondTa();
                
                
        
        
                 
                 workspace.getSectionTextField().setText(""+section);
                 workspace.getInstructorTextField().setText(""+instructor);
             //    workspace.getSectionTextField().requestFocus();
                 workspace.getDaytimeTextField().setText(""+daytime);
                 workspace.getLocationTextField().setText(""+location);
                 workspace.getFirstTAComboBox().setValue(""+firstTa);
                 workspace.getSecondTAComboBox().setValue(""+secondTa);
            
              // jTPS_Transaction transaction=new ChangeTAInfo_Transaction(data,workspace,taName,taEmail,newName,newEmail,labels,jtpsarray);
              
               
//                 if(!((taName.equals(newName))&&(taEmail.equals(newEmail)))){
//                 markWorkAsEdited();
//                 }
          }else {
              AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	 //   dialog.show(props.getProperty(WRONG_TA_EMAIL_TITLE), props.getProperty(WRONG_TA_EMAIL_MESSAGE)); 
          }
         
     }
     
     
  
     
     public void handleRecitationKeyPress(KeyCode code){
             if (code == KeyCode.DELETE ) {
            // GET THE TABLE
            CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
            CSGRecitationWorkspace workspace=temp.getRecitationWorkspace();
            TableView reciTable = workspace.getRecitationTable();
            
            // IS A TA SELECTED IN THE TABLE?
            Object selectedItem = reciTable.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                // GET THE TA AND REMOVE IT
                Recitation reci = (Recitation)selectedItem;
               
                String section = reci.getSection();
//               String jtpstaname=taName;
//                String jtpstaemail=ta.getEmail();
                CSGData data = (CSGData)app.getDataComponent();
             //   data.removeRecitation(section);
            // data.getRecitations().remove(reci);
                
              jTPS_Transaction transaction=new Recitation_RemoveRecitation_Transaction(data, reci);
                  app.getJTPS().addTransaction(transaction);
            
            
                // AND BE SURE TO REMOVE ALL THE TA'S OFFICE HOURS
           
                // WE'VE CHANGED STUFF
                //jTPS_Transaction transaction=new RemovingTA_Transaction(data, jtpstaname,jtpstaemail,labels,jtpsarray);
                //this.getJTPS().addTransaction(transaction);
                markWorkAsEdited();
            }
        }
     }
     
     
     public void handleRecitationRemove(){
         
         CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
            CSGRecitationWorkspace workspace=temp.getRecitationWorkspace();
            TableView reciTable = workspace.getRecitationTable();
            
            // IS A TA SELECTED IN THE TABLE?
            Object selectedItem = reciTable.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                // GET THE TA AND REMOVE IT
                Recitation reci = (Recitation)selectedItem;
               
                String section = reci.getSection();
//               String jtpstaname=taName;
//                String jtpstaemail=ta.getEmail();
                CSGData data = (CSGData)app.getDataComponent();
               // data.removeRecitation(section);
                
               data.getRecitations().remove(reci);
               
                 jTPS_Transaction transaction=new Recitation_RemoveRecitation_Transaction(data, reci);
                  app.getJTPS().addTransaction(transaction);
                  
                  
                    workspace.getSectionTextField().setText("");
                 workspace.getInstructorTextField().setText("");
             //    workspace.getSectionTextField().requestFocus();
                 workspace.getDaytimeTextField().setText("");
                 workspace.getLocationTextField().setText("");
                 workspace.getFirstTAComboBox().setValue("");
                 workspace.getSecondTAComboBox().setValue("");
            
                
                // AND BE SURE TO REMOVE ALL THE TA'S OFFICE HOURS
           
                // WE'VE CHANGED STUFF
                //jTPS_Transaction transaction=new RemovingTA_Transaction(data, jtpstaname,jtpstaemail,labels,jtpsarray);
                //this.getJTPS().addTransaction(transaction);
                markWorkAsEdited();
            }
         
         
         
         
         
     }
     
     
     
    //schedule part///////////////////////////////////////////////////////////////
     public void handleEditScheduleItem() throws ParseException{
         
                CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
        CSGScheduleWorkspace workspace=temp.getCsgScheduleWorkspace();
         CSGData data = (CSGData)app.getDataComponent();
            TableView scheduleItemTable = workspace.getScheduleItemsTable();
            Object selectedItem = scheduleItemTable.getSelectionModel().getSelectedItem();
             PropertiesManager props = PropertiesManager.getPropertiesManager();
        if (selectedItem != null) {
               ScheduleItem sche = (ScheduleItem)selectedItem;
               
                String type = sche.getType();
                String date=sche.getDate();
                String time=sche.getTime();
                String title=sche.getTitle();
                String topic=sche.getTopic();
                String link=sche.getLink();
                String criteria=sche.getCriteria();
                
                
        
        
                 workspace.getTypeComboBox().setValue(type);
                  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
       if(!date.equals("null")){
       Date endDate=sdf.parse(date);
      workspace.getDatePicker().setValue(LocalDate.of(endDate.getYear()+1900, endDate.getMonth()+1, endDate.getDate()));
       }else{
           workspace.getDatePicker().setValue(null);
       }
                // workspace.getDatePicker().setValue(null);
        workspace.getTimeTextField().setText(time);
       workspace.getTitleTextField().setText(title);
        workspace.getTopicTextField().setText(topic);
        workspace.getLinkTextField().setText(link);
        workspace.getCriteriaTextField().setText(criteria);
             
                markWorkAsEdited();
            
              // jTPS_Transaction transaction=new ChangeTAInfo_Transaction(data,workspace,taName,taEmail,newName,newEmail,labels,jtpsarray);
              
               
//                 if(!((taName.equals(newName))&&(taEmail.equals(newEmail)))){
//                 markWorkAsEdited();
//                 }
          }else {
              AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	 //   dialog.show(props.getProperty(WRONG_TA_EMAIL_TITLE), props.getProperty(WRONG_TA_EMAIL_MESSAGE)); 
          }
         
     }
     
     public void handleScheduleItemKeyPress(KeyCode code){
            if (code == KeyCode.DELETE ) {
                CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
            CSGScheduleWorkspace workspace=temp.getScheduleWorkspace();
            TableView scheduleTable = workspace.getScheduleItemsTable();
        
            Object selectedItem = scheduleTable.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                // GET THE TA AND REMOVE IT
                ScheduleItem sche = (ScheduleItem)selectedItem;
                 CSGData data = (CSGData)app.getDataComponent();
              //  data.removeScheduleItem(type,date,time,title,topic,link,criteria);
                data.removeScheduleItem(sche);
                
                 jTPS_Transaction transaction=new Schedule_RemoveScheduleItem_Transaction(data, sche);
                  app.getJTPS().addTransaction(transaction);
                  
           workspace.getTypeComboBox().setValue("");
       
       workspace.getDatePicker().setValue(null);
        workspace.getTimeTextField().setText("");
       workspace.getTitleTextField().setText("");
        workspace.getTopicTextField().setText("");
        workspace.getLinkTextField().setText("");
        workspace.getCriteriaTextField().setText("");
             
                markWorkAsEdited();
            }
        }
         
     }
     public void handleScheduleItemRemove(){
             CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
            CSGScheduleWorkspace workspace=temp.getScheduleWorkspace();
            TableView scheduleTable = workspace.getScheduleItemsTable();
        
            Object selectedItem = scheduleTable.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
          
                ScheduleItem sche = (ScheduleItem)selectedItem;
               
                String type = sche.getType();
                String date=sche.getDate();
                String time=sche.getTime();
                String title=sche.getTitle();
                String topic=sche.getTopic();
                String link=sche.getLink();
                String criteria=sche.getCriteria();
//               String jtpstaname=taName;
//                String jtpstaemail=ta.getEmail();
                CSGData data = (CSGData)app.getDataComponent();
              //  data.removeScheduleItem(type,date,time,title,topic,link,criteria);
              //  data.removeScheduleItem(sche);
              
               jTPS_Transaction transaction=new Schedule_RemoveScheduleItem_Transaction(data, sche);
                  app.getJTPS().addTransaction(transaction);
                
           workspace.getTypeComboBox().setValue("");
       workspace.getDatePicker().setValue(null);
        workspace.getTimeTextField().setText("");
       workspace.getTitleTextField().setText("");
        workspace.getTopicTextField().setText("");
        workspace.getLinkTextField().setText("");
        workspace.getCriteriaTextField().setText("");
                
                // AND BE SURE TO REMOVE ALL THE TA'S OFFICE HOURS
           
                // WE'VE CHANGED STUFF
                //jTPS_Transaction transaction=new RemovingTA_Transaction(data, jtpstaname,jtpstaemail,labels,jtpsarray);
                //this.getJTPS().addTransaction(transaction);
                markWorkAsEdited();
            }
     }
     public void handleScheduleItemUpdate(){
         
             CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
        CSGScheduleWorkspace workspace=temp.getCsgScheduleWorkspace();
         CSGData data = (CSGData)app.getDataComponent();
            TableView scheduleItemsTable = workspace.getScheduleItemsTable();
            Object selectedItem = scheduleItemsTable.getSelectionModel().getSelectedItem();
             PropertiesManager props = PropertiesManager.getPropertiesManager();
       
        
         String type=workspace.getTypeComboBox().getValue()+"";
         String date="0-0-0";
         if(workspace.getDatePicker().getValue()!=null){
             System.out.println(workspace.getDatePicker().getValue().toString()+"");
          date=workspace.getDatePicker().getValue().toString()+"";
         }
         String time=workspace.getTimeTextField().getText()+"";
         String title=workspace.getTitleTextField().getText()+"";
         String topic=workspace.getTopicTextField().getText()+"";
         String link=workspace.getLinkTextField().getText()+"";
         String criteria=workspace.getCriteriaTextField().getText()+"";
         ScheduleItem newschedule=new ScheduleItem(type, date, time, title,topic,link,criteria);
          if (selectedItem == null) {
                  
              ObservableList<ScheduleItem> old=data.getScheduleItems();
              for(ScheduleItem k:old){
                  
                  if(k.getType().equals(type)&&k.getDate().equals(date)){
                       AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	           dialog.show(props.getProperty(WRONG_SCHEDULEITEM_TITLE), props.getProperty(WRONG_SCHEDULEITEM_MESSAGE));  
                  return;
              }
              }
              
              
              
                     // data.getScheduleItems().add(newschedule);
                      jTPS_Transaction transaction=new Schedule_AddScheduleItem_Transaction(data, newschedule);
                  app.getJTPS().addTransaction(transaction);
         
          workspace.getTypeComboBox().setValue("");
       workspace.getDatePicker().setValue(null);
        workspace.getTimeTextField().setText("");
       workspace.getTitleTextField().setText("");
        workspace.getTopicTextField().setText("");
        workspace.getLinkTextField().setText("");
        workspace.getCriteriaTextField().setText("");
                  
        }else{
         ScheduleItem oldsche=(ScheduleItem)selectedItem;
        //  data.getScheduleItems().remove(oldsche);
        // data.getScheduleItems().add(newschedule);
         
          jTPS_Transaction transaction=new Schedule_ChangeScheduleItemInfo_Transaction(data, newschedule,oldsche);
                  app.getJTPS().addTransaction(transaction);
         
          workspace.getTypeComboBox().setValue("");
       workspace.getDatePicker().setValue(null);
        workspace.getTimeTextField().setText("");
       workspace.getTitleTextField().setText("");
        workspace.getTopicTextField().setText("");
        workspace.getLinkTextField().setText("");
        workspace.getCriteriaTextField().setText("");
            
            
        }
       markWorkAsEdited();
         
         
         
         
     }
     
     public void handleScheduleClear(){
          PropertiesManager props = PropertiesManager.getPropertiesManager();
        CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
        CSGScheduleWorkspace workspace=temp.getCsgScheduleWorkspace();
       workspace.getTypeComboBox().setValue("");
       
       workspace.getDatePicker().setValue(null);
        workspace.getTimeTextField().setText("");
       workspace.getTitleTextField().setText("");
        workspace.getTopicTextField().setText("");
        workspace.getLinkTextField().setText("");
        workspace.getCriteriaTextField().setText("");
                      TableView scheTable = workspace.getScheduleItemsTable();
             scheTable.getSelectionModel().clearSelection();
     }
     
     
     
     
     //project part////////////////////////////////
            //teams 
             public void handleTeamUpdate(){
                 
                 
                  CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
        CSGProjectWorkspace workspace=temp.getCsgProjectWorkspace();
         CSGData data = (CSGData)app.getDataComponent();
            TableView teamTable = workspace.getTeamsTable();
            Object selectedItem = teamTable.getSelectionModel().getSelectedItem();
             PropertiesManager props = PropertiesManager.getPropertiesManager();
       String color="ffffff";
       String textcolor="ffffff";
       String name;
        if(workspace.getNameTextField().getText()+""!=""){
          name=workspace.getNameTextField().getText()+"";
        }else{
             AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	           dialog.show(props.getProperty(WRONG_TEAM_TITLE), props.getProperty(PROVE_TEAM_NAME));  
                  return;
        }
         if(workspace.getColorColorPicker().getValue()!=null){
          color=workspace.getColorColorPicker().getValue().toString().substring(2)+"";
         
         }
          if(workspace.getTextColorPicker().getValue()!=null){
         textcolor=workspace.getTextColorPicker().getValue().toString().substring(2)+"";
          }
         String link=workspace.getLinkTextField().getText()+"";
       
         Team newteam=new Team(name,color,textcolor,link);
          if (selectedItem == null) {
              
              ObservableList<Team> old=data.getTeams();
              for(Team k:old){
                if(k.getTeamname().equals(name)){
                    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	           dialog.show(props.getProperty(WRONG_TEAM_TITLE), props.getProperty(WRONG_TEAM_MESSAGE));  
                  return;
                }
          }
              Team newte=new Team(name,color,textcolor,link);
              
      //   data.addTeam(name,color,textcolor,link);
         jTPS_Transaction transaction=new Project_AddTeam_Transaction(data, newte);
            app.getJTPS().addTransaction(transaction);
         
          workspace.getNameTextField().setText("");
                            workspace.getColorColorPicker().setValue(Color.WHITE);
                         workspace.getTextColorPicker().setValue(Color.WHITE);
                            workspace.getLinkTextField().setText("");
                            
        }else{
         Team oldteam=(Team)selectedItem;
          Team newte=new Team(name,color,textcolor,link);
      //   data.removeTeam(oldteam);
      //   data.addTeam(name,color,textcolor,link);
      
           jTPS_Transaction transaction=new Project_ChangeTeamInfo_Transaction(data, newte,oldteam);
            app.getJTPS().addTransaction(transaction);
         
          workspace.getNameTextField().setText("");
                            workspace.getColorColorPicker().setValue(Color.WHITE);
                         workspace.getTextColorPicker().setValue(Color.WHITE);
                            workspace.getLinkTextField().setText("");
            
        }
       markWorkAsEdited();
                 
                 
                 
             }
             
             
             
             
             
             
             
             public void handleTeamClear(){
                           PropertiesManager props = PropertiesManager.getPropertiesManager();
                     CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
                    CSGProjectWorkspace workspace=temp.getCsgProjectWorkspace();
                    
                         workspace.getNameTextField().setText("");
                            workspace.getColorColorPicker().setValue(Color.WHITE);
                         workspace.getTextColorPicker().setValue(Color.WHITE);
                            workspace.getLinkTextField().setText("");
             
      TableView teamTable = workspace.getTeamsTable();
             teamTable.getSelectionModel().clearSelection();
                 
             }
             
             
             
             public void handleTeamRemove(){
                              CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
            CSGProjectWorkspace workspace=temp.getProjectWorkspace();
            TableView teamsTable = workspace.getTeamsTable();
        
            Object selectedItem = teamsTable.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                // GET THE TA AND REMOVE IT
                Team te = (Team)selectedItem;
                 CSGData data = (CSGData)app.getDataComponent();
              //  data.removeScheduleItem(type,date,time,title,topic,link,criteria);
             //   data.removeTeam(te);       
              
                 jTPS_Transaction transaction=new Project_RemoveTeam_Transaction(data, te);
            app.getJTPS().addTransaction(transaction);
            
       
            workspace.getNameTextField().setText("");
                            workspace.getColorColorPicker().setValue(Color.WHITE);
                         workspace.getTextColorPicker().setValue(Color.WHITE);
                            workspace.getLinkTextField().setText("");
             
                markWorkAsEdited();
                 
             }
             }
             
             
             
             
             
             
             public void handleEditTeam(){
                 
                   CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
        CSGProjectWorkspace workspace=temp.getCsgProjectWorkspace();
         CSGData data = (CSGData)app.getDataComponent();
            TableView teamsTable = workspace.getTeamsTable();
            Object selectedItem = teamsTable.getSelectionModel().getSelectedItem();
             PropertiesManager props = PropertiesManager.getPropertiesManager();
        if (selectedItem != null) {
               Team te = (Team)selectedItem;
               
                String name=te.getTeamname();
                String color=te.getColor();
               int colorRed=te.getRed();
                int colorBlue=te.getBlue();
                int colorGreen=te.getGreen();
                String textcolor=te.getTextcolor();
                int textColorRed=te.getTextRed();
                int textColorBlue=te.getTextBlue();
                int textColorGreen=te.getTextGreen();
                String link=te.getLink();
                
        
        
          
      
                // workspace.getDatePicker().setValue(null);
        workspace.getNameTextField().setText(name);
       workspace.getColorColorPicker().setValue(Color.rgb(colorRed, colorGreen, colorBlue));
       workspace.getTextColorPicker().setValue(Color.rgb(textColorRed, textColorGreen, textColorBlue));
        workspace.getLinkTextField().setText(link);
             
                markWorkAsEdited();
            
              // jTPS_Transaction transaction=new ChangeTAInfo_Transaction(data,workspace,taName,taEmail,newName,newEmail,labels,jtpsarray);
              
               
//                 if(!((taName.equals(newName))&&(taEmail.equals(newEmail)))){
//                 markWorkAsEdited();
//                 }
          }else {
              AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	 //   dialog.show(props.getProperty(WRONG_TA_EMAIL_TITLE), props.getProperty(WRONG_TA_EMAIL_MESSAGE)); 
          }
                 
             } 
             
             
             
             
             
             
             
             public void handleTeamKeyPress(KeyCode code){
                 
                  if (code == KeyCode.DELETE ) {
                CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
            CSGProjectWorkspace workspace=temp.getProjectWorkspace();
            TableView teamsTable = workspace.getTeamsTable();
        
            Object selectedItem = teamsTable.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                // GET THE TA AND REMOVE IT
                Team te = (Team)selectedItem;
                 CSGData data = (CSGData)app.getDataComponent();
              //  data.removeScheduleItem(type,date,time,title,topic,link,criteria);
            //    data.removeTeam(te);    
            
            jTPS_Transaction transaction=new Project_RemoveTeam_Transaction(data, te);
            app.getJTPS().addTransaction(transaction);
        
       
            workspace.getNameTextField().setText("");
                            workspace.getColorColorPicker().setValue(Color.WHITE);
                         workspace.getTextColorPicker().setValue(Color.WHITE);
                            workspace.getLinkTextField().setText("");
             
                markWorkAsEdited();
            }
        }
                 
             }
           //student
              public void handleStudentUpdate(){
                  
                  
                          CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
        CSGProjectWorkspace workspace=temp.getCsgProjectWorkspace();
         CSGData data = (CSGData)app.getDataComponent();
            TableView studentTable = workspace.getStudentsTable();
            Object selectedItem = studentTable.getSelectionModel().getSelectedItem();
             PropertiesManager props = PropertiesManager.getPropertiesManager();
       
        
         String firstname=workspace.getFirstNameTextField().getText()+"";
         String lastname=workspace.getLastNameTextField().getText()+"";
         String team=workspace.getTeamsComboBox().getValue()+"";
         String role=workspace.getRoleTextField().getText()+"";
       
         Student newstu=new Student(firstname,lastname,team,role);
          if (selectedItem == null) {
                ObservableList<Student> old=data.getStudents();
              for (Student k:old){
                  if(k.getFirstName().equals(firstname)&&k.getLastName().equals(lastname)){
                         AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	           dialog.show(props.getProperty(WRONG_STUDENT_TITLE), props.getProperty(WRONG_STUDENT_MESSAGE));  
                  return;
                  }
              }
            //  data.getStudents().add(newstu);
              
              
         jTPS_Transaction transaction=new Project_AddStudent_Transaction(data, newstu);
            app.getJTPS().addTransaction(transaction);
            
            
               workspace.getFirstNameTextField().setText("");
                            workspace.getLastNameTextField().setText("");
                            workspace.getTeamsComboBox().setValue("");
                            workspace.getRoleTextField().setText("");
        }else{
         Student oldstu=(Student)selectedItem;
     //    data.getStudents().remove(oldstu);
       //  data.getStudents().add(newstu);
         
         
         jTPS_Transaction transaction=new Project_ChangeStudentInfo_Transaction(data, newstu,oldstu);
            app.getJTPS().addTransaction(transaction);
         
             workspace.getFirstNameTextField().setText("");
                            workspace.getLastNameTextField().setText("");
                            workspace.getTeamsComboBox().setValue("");
                            workspace.getRoleTextField().setText("");
            
        }
       markWorkAsEdited();
                 
          
             }
              
              
              
              
              
             public void handleStudentClear(){
                        PropertiesManager props = PropertiesManager.getPropertiesManager();
                     CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
                    CSGProjectWorkspace workspace=temp.getCsgProjectWorkspace();
                    
                         workspace.getFirstNameTextField().setText("");
                            workspace.getLastNameTextField().setText("");
                            workspace.getTeamsComboBox().setValue("");
                            workspace.getRoleTextField().setText("");
                            
                            TableView stTable = workspace.getStudentsTable();
             stTable.getSelectionModel().clearSelection();
                            
                 
             }
             public void handleStudentRemove(){
                       CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
            CSGProjectWorkspace workspace=temp.getProjectWorkspace();
            TableView studentsTable = workspace.getStudentsTable();
        
            Object selectedItem = studentsTable.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                // GET THE TA AND REMOVE IT
                Student st = (Student)selectedItem;
                 CSGData data = (CSGData)app.getDataComponent();
              //  data.removeScheduleItem(type,date,time,title,topic,link,criteria);
           //     data.removeStudent(st);     
              jTPS_Transaction transaction=new Project_RemoveStudent_Transaction(data, st);
            app.getJTPS().addTransaction(transaction);
        
       
              workspace.getFirstNameTextField().setText("");
                            workspace.getLastNameTextField().setText("");
                            workspace.getTeamsComboBox().setValue("");
                            workspace.getRoleTextField().setText("");
                markWorkAsEdited();
             }
                 
             }
             public void handleEditStudent(){
                 
                 
                 
                      CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
        CSGProjectWorkspace workspace=temp.getCsgProjectWorkspace();
         CSGData data = (CSGData)app.getDataComponent();
            TableView stuTable = workspace.getStudentsTable();
            Object selectedItem = stuTable.getSelectionModel().getSelectedItem();
             PropertiesManager props = PropertiesManager.getPropertiesManager();
        if (selectedItem != null) {
                Student st = (Student)selectedItem;
               
                String firstName=st.getFirstName()+"";
                String lastName=st.getLastName()+"";
                String team=st.getTeamString()+"";
                String role=st.getRole()+"";
                
        
        
        
      
                // workspace.getDatePicker().setValue(null);
        workspace.getFirstNameTextField().setText(firstName);
       workspace.getLastNameTextField().setText(lastName);
       workspace.getTeamsComboBox().setValue(team);
       workspace.getRoleTextField().setText(role);
             
                markWorkAsEdited();
            
              // jTPS_Transaction transaction=new ChangeTAInfo_Transaction(data,workspace,taName,taEmail,newName,newEmail,labels,jtpsarray);
              
               
//                 if(!((taName.equals(newName))&&(taEmail.equals(newEmail)))){
//                 markWorkAsEdited();
//                 }
          }else {
              AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	 //   dialog.show(props.getProperty(WRONG_TA_EMAIL_TITLE), props.getProperty(WRONG_TA_EMAIL_MESSAGE)); 
          }
                 
                 
                 
                 
                 
                 
             } 
             public void handleStudentKeyPress(KeyCode code){
                        
                  if (code == KeyCode.DELETE ) {
                CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
            CSGProjectWorkspace workspace=temp.getProjectWorkspace();
            TableView studentsTable = workspace.getStudentsTable();
        
            Object selectedItem = studentsTable.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                // GET THE TA AND REMOVE IT
                Student st = (Student)selectedItem;
                 CSGData data = (CSGData)app.getDataComponent();
              //  data.removeScheduleItem(type,date,time,title,topic,link,criteria);
               // data.removeStudent(st);       
           jTPS_Transaction transaction=new Project_RemoveStudent_Transaction(data, st);
            app.getJTPS().addTransaction(transaction);
       
              workspace.getFirstNameTextField().setText("");
                            workspace.getLastNameTextField().setText("");
                            workspace.getTeamsComboBox().setValue("");
                            workspace.getRoleTextField().setText("");
                markWorkAsEdited();
            }
                 
             }
     
             }
     
     
     
     
     
     //connect to recitation workspace
//     
//             public void addTAtoReciCombobox(String inittaName){
//            CSGWorkspace temp=(CSGWorkspace)app.getWorkspaceComponent();
//            CSGRecitationWorkspace workspace=temp.getCsgRecitationWorkspace();
//            workspace.getFirstTAComboBox().getItems().add(inittaName);
//            workspace.getSecondTAComboBox().getItems().add(inittaName);
//            
//        }
//             
//             public void removeTAfromReciCombobox(String inittaname){
//                 
//                  CSGWorkspace temp=(CSGWorkspace)app.getWorkspaceComponent();
//            CSGRecitationWorkspace workspace=temp.getCsgRecitationWorkspace();
//           //  workspace.getFirstTAComboBox().getItems()
//         //   for (String k:workspace.getFirstTAComboBox().getItems()){
//           //     if(k==inittaname)
//            workspace.getFirstTAComboBox().getItems().remove(inittaname);
//         //   }
//            workspace.getSecondTAComboBox().getItems().remove(inittaname);
//                 
//             }
             
             
             
             
         public void handleUndoTransaction(){
       app.getJTPS().undoTransaction();
        markWorkAsEdited();
    }
    
     public void handleDoTransaction(){
       app.getJTPS().doTransaction();
        markWorkAsEdited();
    }
             
             
}