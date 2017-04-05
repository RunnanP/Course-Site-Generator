/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import csg.CSGApp;
import csg.CSGAppProp;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import properties_manager.PropertiesManager;

/**
 *
 * @author runnan
 */
public class CSGCourseWorkspace implements WorkspacePart{
    CSGApp app;
    CSGController controller;
    Pane basePane;
    
    BorderPane secondBasePane;
    VBox topPane;
    VBox centerPane;
    VBox bottomPane;
    
    //top pane 
    Label topPaneHeaderLabel;
    HBox firstHBox;
    HBox secondHBox;
    HBox thirdHBox;
    HBox fourthHBox;
    HBox fifthHBox;
    HBox sixthHBox;
        
        //first Hbox
          Label subjectLabel;
          ComboBox<String> subjectComboBox;
          Label numberLabel;
          ComboBox<Integer> numberComboBox;
        //second HBox
          Label semesterLabel;
          ComboBox<String> semesterComboBox;
          Label yearLabel;
          ComboBox<Integer> yearComboBox;
          // third HBox
          Label titleLabel;
          TextField titleTextField;
          //fourth Hbox
          Label instructorNameLabel;
          TextField instructorNameTextField;
          //fifth Hbox
          Label instructorHomeLabel;
          TextField instructorHomeTextField;
          //sixth Hbox
          Label exportDirLabel;
          Label exporDirDisplayAddressLabel;
          Button exportDirChangeButton;
          
      
    //center pane
    Label centralPaneHeaderLabel;
    
    
    //bottom pane
    Label buttomPaneHeaderLabel;
    
    public CSGCourseWorkspace(CSGApp initapp){
         app=initapp;
        
         initTopPane();
         initCenterPane();
         initBottomPane();
         secondBasePane=new BorderPane();
         secondBasePane.setTop(topPane);
         secondBasePane.setCenter(centerPane);
         secondBasePane.setBottom(bottomPane);
         basePane=new Pane();
         basePane.getChildren().add(secondBasePane);
         
     }

   private void initTopPane(){
       PropertiesManager props = PropertiesManager.getPropertiesManager();
       
       String topPaneHeaderLabelText = props.getProperty(CSGAppProp.COURSE_WORKSPACE_PART_TOP_PANE_HEADER_TEXT.toString());
       topPaneHeaderLabel=new Label(topPaneHeaderLabelText);
       
       //first hbox
       String subjectLabelText = props.getProperty(CSGAppProp.COURSE_WORKSPACE_PART_TOP_PANE_SUBJECT_TEXT.toString());
       String numberLabelText = props.getProperty(CSGAppProp.COURSE_WORKSPACE_PART_TOP_PANE_NUMBER_TEXT.toString());
         subjectLabel=new Label(subjectLabelText);
         subjectComboBox=new ComboBox<String>();
         numberLabel=new Label(numberLabelText);
         numberComboBox=new ComboBox<Integer>();
         
         firstHBox=new HBox();
         firstHBox.getChildren().addAll(subjectLabel,subjectComboBox,numberLabel,numberComboBox);
         
         //second hbox
        String semesterLabelText = props.getProperty(CSGAppProp.COURSE_WORKSPACE_PART_TOP_PANE_SEMESTER_TEXT.toString());
       String yearLabelText = props.getProperty(CSGAppProp.COURSE_WORKSPACE_PART_TOP_PANE_YEAR_TEXT.toString());
         semesterLabel=new Label(semesterLabelText);
         semesterComboBox=new ComboBox<String>();
         yearLabel=new Label(yearLabelText);
         yearComboBox=new ComboBox<Integer>();
         
         secondHBox=new HBox();
         secondHBox.getChildren().addAll(semesterLabel,semesterComboBox,yearLabel,yearComboBox);
         //third hbox
         String titleLabelText = props.getProperty(CSGAppProp.COURSE_WORKSPACE_PART_TOP_PANE_TITLE_TEXT.toString());
      
         titleLabel=new Label(titleLabelText);
         titleTextField=new TextField();
         
         thirdHBox=new HBox();
         thirdHBox.getChildren().addAll(titleLabel,titleTextField);
         
         //fourth hbox
       String instructorNameLabelText = props.getProperty(CSGAppProp.COURSE_WORKSPACE_PART_TOP_PANE_INCTRUCTOR_NAME_TEXT.toString());
       
         instructorNameLabel=new Label(instructorNameLabelText);
         instructorNameTextField=new TextField();
         
         fourthHBox=new HBox();
         fourthHBox.getChildren().addAll(instructorNameLabel,instructorNameTextField);
         //fifth hBOX
          String instructorHomeLabelText = props.getProperty(CSGAppProp.COURSE_WORKSPACE_PART_TOP_PANE_INCTRUCTOR_HOME_TEXT.toString());
       
         instructorHomeLabel=new Label(instructorHomeLabelText);
         instructorHomeTextField=new TextField();
         
         fifthHBox=new HBox();
         fifthHBox.getChildren().addAll(instructorHomeLabel,instructorHomeTextField);
         //sixth
          String exportDirLabelText = props.getProperty(CSGAppProp.COURSE_WORKSPACE_PART_TOP_PANE_EXPORT_DIR_TEXT.toString());
          String exportDirButtorLabelText = props.getProperty(CSGAppProp.CHANGE_TEXT.toString());
       
         exportDirLabel=new Label(exportDirLabelText);
         
         exportDirChangeButton=new Button(exportDirButtorLabelText);
         
         sixthHBox=new HBox();
         sixthHBox.getChildren().addAll(exportDirLabel,exportDirChangeButton);
         
         topPane=new VBox();
         topPane.getChildren().addAll( topPaneHeaderLabel,firstHBox,secondHBox,thirdHBox,fourthHBox,fifthHBox,sixthHBox);
   }
   
   private void initCenterPane(){
       PropertiesManager props = PropertiesManager.getPropertiesManager();
       
       
       centerPane=new VBox();
       
   }
   
   private void initBottomPane(){
       PropertiesManager props = PropertiesManager.getPropertiesManager();
       bottomPane=new VBox();
   }
    public Pane getBasePane() {
        return basePane;
    }
    
    public void renewPane(){
        
    }
}
