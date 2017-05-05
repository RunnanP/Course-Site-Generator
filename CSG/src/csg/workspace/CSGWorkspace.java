/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import csg.CSGApp;
import csg.CSGAppProp;
import csg.data.CSGData;
import djf.components.AppDataComponent;
import djf.components.AppWorkspaceComponent;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
public class CSGWorkspace extends AppWorkspaceComponent{
   CSGApp app;
   CSGController controller;
   
   VBox workspaceBasicPane;   //use to put state bar and current state pane
   HBox workspaceStateBar;
   
   Button workspaceCoursePartButton;
   Button workspaceTAPartButton;
   Button workspaceRecitationPartButton;
   Button workspaceSchedulePartButton;
   Button workspaceProjectPartButton;
   
   CSGCourseWorkspace csgCourseWorkspace;
   CSGTAWorkspace csgTAWorkspace;
   CSGRecitationWorkspace csgRecitationWorkspace;
   CSGScheduleWorkspace csgScheduleWorkspace;
   CSGProjectWorkspace csgProjectWorkspace;
   Pane currentWorkspace;
   
   
   public CSGWorkspace(CSGApp initApp,String k) throws ParseException{
       app=initApp;
        PropertiesManager props=PropertiesManager.getPropertiesManager();
        controller=new CSGController(app);
       initAllWorkspaceParts();
   }
    public CSGWorkspace(CSGApp initApp) throws ParseException {
        app=initApp;
        PropertiesManager props=PropertiesManager.getPropertiesManager();
        controller=new CSGController(app);
        workspaceBasicPane=new VBox();
        workspaceStateBar=new HBox();
        currentWorkspace=new Pane();
        
        String workspaceCoursePartButtonText=props.getProperty(CSGAppProp.WORKSPACE_STATE_BUTTON_COURSE_PART_TEXT.toString());
         String workspaceTAPartButtonText=props.getProperty(CSGAppProp.WORKSPACE_STATE_BUTTON_TA_PART_TEXT.toString());
          String workspaceRecitationPartButtonText=props.getProperty(CSGAppProp.WORKSPACE_STATE_BUTTON_RECITATION_PART_TEXT.toString());
           String workspaceSchedulePartButtonText=props.getProperty(CSGAppProp.WORKSPACE_STATE_BUTTON_SCHEDULE_PART_TEXT.toString());
            String workspaceProjectPartButtonText=props.getProperty(CSGAppProp.WORKSPACE_STATE_BUTTON_PROJECT_PART_TEXT.toString());
            
        
         workspaceCoursePartButton=new Button(workspaceCoursePartButtonText);
         workspaceTAPartButton=new Button(workspaceTAPartButtonText);
         workspaceRecitationPartButton=new Button(workspaceRecitationPartButtonText);
         workspaceSchedulePartButton=new Button(workspaceSchedulePartButtonText);
         workspaceProjectPartButton=new Button(workspaceProjectPartButtonText);
            
         workspaceCoursePartButton.prefWidthProperty().bind(workspaceStateBar.widthProperty().multiply(.2));
         workspaceTAPartButton.prefWidthProperty().bind(workspaceStateBar.widthProperty().multiply(.2));
         workspaceRecitationPartButton.prefWidthProperty().bind(workspaceStateBar.widthProperty().multiply(.2));
          workspaceSchedulePartButton.prefWidthProperty().bind(workspaceStateBar.widthProperty().multiply(.2));
         workspaceProjectPartButton.prefWidthProperty().bind(workspaceStateBar.widthProperty().multiply(.2));
         workspaceStateBar.getChildren().addAll(workspaceCoursePartButton,workspaceTAPartButton,workspaceRecitationPartButton,workspaceSchedulePartButton,workspaceProjectPartButton);
        workspaceBasicPane.getChildren().addAll(workspaceStateBar,currentWorkspace);
         workspace=new BorderPane();
         ((BorderPane)workspace).setCenter(workspaceBasicPane);
          
         initAllWorkspaceParts();
         
         
         
         
         workspaceCoursePartButton.setOnAction(e->{
            
              workspaceBasicPane=new VBox();
            workspaceBasicPane.getChildren().addAll(workspaceStateBar,csgCourseWorkspace.getBasePane());
            app.getGUI().getAppPane().setCenter(workspaceBasicPane);
            
           
         });
         
         
         
         workspaceTAPartButton.setOnAction(e->{
             //controller.handleChangeTAPart(this,workspaceStateBar,csgTAWorkspace.getBasePane());
            // workspace=new Pane(csgTAWorkspace.getBasePane());
            //app.getWorkspaceComponent().resetWorkspace();
            //workspace=new Pane(csgTAWorkspace.getBasePane());
            // workspace=new BorderPane();
        // ((BorderPane)workspace).setCenter(csgCourseWorkspace.getBasePane());
             //controller.handleChangeTAPart(app,this,workspaceStateBar,csgTAWorkspace.getBasePane());
            // app.getWorkspaceComponent().activateWorkspace(app.getGUI().getAppPane());
            
            workspaceBasicPane=new VBox();
            workspaceBasicPane.getChildren().addAll(workspaceStateBar,csgTAWorkspace.getBasePane());
          //  CSGWorkspace oo=(CSGWorkspace)app.getWorkspaceComponent();
            workspaceBasicPane.prefWidthProperty().bind(workspaceStateBar.widthProperty().multiply(1));
            app.getGUI().getAppPane().setCenter(workspaceBasicPane);
            
          
            
         });
         
         
         
         workspaceRecitationPartButton.setOnAction(e->{
            workspaceBasicPane=new VBox();
            workspaceBasicPane.getChildren().addAll(workspaceStateBar,csgRecitationWorkspace.getBasePane());
            app.getGUI().getAppPane().setCenter(workspaceBasicPane);
         
         });
         
         
         workspaceSchedulePartButton.setOnAction(e->{
            workspaceBasicPane=new VBox();
            workspaceBasicPane.getChildren().addAll(workspaceStateBar,csgScheduleWorkspace.getBasePane());
            app.getGUI().getAppPane().setCenter(workspaceBasicPane);
          
         });
         
         
         workspaceProjectPartButton.setOnAction(e->{
            workspaceBasicPane=new VBox();
            workspaceBasicPane.getChildren().addAll(workspaceStateBar,csgProjectWorkspace.getBasePane());
            app.getGUI().getAppPane().setCenter(workspaceBasicPane);
           
         });
         
         
         workspace.setOnKeyPressed(e->{
           if(e.isControlDown() && e.getCode()==KeyCode.Z){
  
        controller.handleUndoTransaction();
        //jTPS.undoTransaction();
    }else if(e.isControlDown() && e.getCode()==KeyCode.Y){

        controller.handleDoTransaction();
       // jTPS.doTransaction();
    }

});
    }
  
  

    @Override
    public void resetWorkspace() {
        csgCourseWorkspace.reset();
       csgTAWorkspace.reset();
        csgRecitationWorkspace.reset();
       try {
           csgScheduleWorkspace.reset();
       } catch (ParseException ex) {
           Logger.getLogger(CSGWorkspace.class.getName()).log(Level.SEVERE, null, ex);
       }
        csgProjectWorkspace.reset();
        currentWorkspace.getChildren().clear();
    }

    @Override
    public void reloadWorkspace(AppDataComponent dataComponent) {
       CSGData data=(CSGData)dataComponent;
      // this.getCsgCourseWorkspace().reloadWorkspace(data);
       this.getCsgTAWorkspace().reloadWorkspace(data);
       //this.getCsgRecitationWorkspace().reloadWorkspace(data);
      // this.getCsgScheduleWorkspace().reloadWorkspace(data);
       //this.getCsgProjectWorkspace().reloadWorkspace(data);
               
       
        
        
    }
    
    public void initAllWorkspaceParts() throws ParseException{
          csgCourseWorkspace=new CSGCourseWorkspace(app);
          csgTAWorkspace=new CSGTAWorkspace(app);
          csgRecitationWorkspace=new CSGRecitationWorkspace(app);
          csgScheduleWorkspace=new CSGScheduleWorkspace(app);
          csgProjectWorkspace=new CSGProjectWorkspace(app);
    }
    
    public CSGCourseWorkspace getCourseWorkspace(){
        return csgCourseWorkspace;
    }
    public CSGTAWorkspace getTAWorkspace(){
        return csgTAWorkspace;
    }
    public CSGProjectWorkspace getProjectWorkspace(){
        return csgProjectWorkspace;
    }
    public CSGRecitationWorkspace getRecitationWorkspace(){
       return csgRecitationWorkspace;       
    }
    public CSGScheduleWorkspace getScheduleWorkspace(){
        return csgScheduleWorkspace;
    }

    public CSGApp getApp() {
        return app;
    }

    public void setApp(CSGApp app) {
        this.app = app;
    }

    public CSGController getController() {
        return controller;
    }

    public void setController(CSGController controller) {
        this.controller = controller;
    }

    public VBox getWorkspaceBasicPane() {
        return workspaceBasicPane;
    }

    public void setWorkspaceBasicPane(VBox workspaceBasicPane) {
        this.workspaceBasicPane = workspaceBasicPane;
    }

    public HBox getWorkspaceStateBar() {
        return workspaceStateBar;
    }

    public void setWorkspaceStateBar(HBox workspaceStateBar) {
        this.workspaceStateBar = workspaceStateBar;
    }

    public Button getWorkspaceCoursePartButton() {
        return workspaceCoursePartButton;
    }

    public void setWorkspaceCoursePartButton(Button workspaceCoursePartButton) {
        this.workspaceCoursePartButton = workspaceCoursePartButton;
    }

    public Button getWorkspaceTAPartButton() {
        return workspaceTAPartButton;
    }

    public void setWorkspaceTAPartButton(Button workspaceTAPartButton) {
        this.workspaceTAPartButton = workspaceTAPartButton;
    }

    public Button getWorkspaceRecitationPartButton() {
        return workspaceRecitationPartButton;
    }

    public void setWorkspaceRecitationPartButton(Button workspaceRecitationPartButton) {
        this.workspaceRecitationPartButton = workspaceRecitationPartButton;
    }

    public Button getWorkspaceSchedulePartButton() {
        return workspaceSchedulePartButton;
    }

    public void setWorkspaceSchedulePartButton(Button workspaceSchedulePartButton) {
        this.workspaceSchedulePartButton = workspaceSchedulePartButton;
    }

    public Button getWorkspaceProjectPartButton() {
        return workspaceProjectPartButton;
    }

    public void setWorkspaceProjectPartButton(Button workspaceProjectPartButton) {
        this.workspaceProjectPartButton = workspaceProjectPartButton;
    }

    public CSGCourseWorkspace getCsgCourseWorkspace() {
        return csgCourseWorkspace;
    }

    public void setCsgCourseWorkspace(CSGCourseWorkspace csgCourseWorkspace) {
        this.csgCourseWorkspace = csgCourseWorkspace;
    }

    public CSGTAWorkspace getCsgTAWorkspace() {
        return csgTAWorkspace;
    }

    public void setCsgTAWorkspace(CSGTAWorkspace csgTAWorkspace) {
        this.csgTAWorkspace = csgTAWorkspace;
    }

    public CSGRecitationWorkspace getCsgRecitationWorkspace() {
        return csgRecitationWorkspace;
    }

    public void setCsgRecitationWorkspace(CSGRecitationWorkspace csgRecitationWorkspace) {
        this.csgRecitationWorkspace = csgRecitationWorkspace;
    }

    public CSGScheduleWorkspace getCsgScheduleWorkspace() {
        return csgScheduleWorkspace;
    }

    public void setCsgScheduleWorkspace(CSGScheduleWorkspace csgScheduleWorkspace) {
        this.csgScheduleWorkspace = csgScheduleWorkspace;
    }

    public CSGProjectWorkspace getCsgProjectWorkspace() {
        return csgProjectWorkspace;
    }

    public void setCsgProjectWorkspace(CSGProjectWorkspace csgProjectWorkspace) {
        this.csgProjectWorkspace = csgProjectWorkspace;
    }

    public Pane getCurrentWorkspace() {
        return currentWorkspace;
    }

    public void setCurrentWorkspace(Pane currentWorkspace) {
        this.currentWorkspace = currentWorkspace;
    }

    public Pane getWorkspace() {
        return workspace;
    }

    public void setWorkspace(Pane workspace) {
        this.workspace = workspace;
    }

     
    
}
