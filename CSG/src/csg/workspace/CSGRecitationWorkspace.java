/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import csg.CSGApp;
import csg.CSGAppProp;
import csg.data.Recitation;
import csg.data.TeachingAssistant;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import properties_manager.PropertiesManager;

/**
 *
 * @author runnan
 */
public class CSGRecitationWorkspace implements WorkspacePart{
    CSGApp app;
    CSGController controller;
    Pane basePane;
    
    VBox secondBasePane;
    
    Label recitationHeaderLabel;
    
    TableView<Recitation> recitationTable;
    TableColumn<Recitation,String> sectionColumn;
    TableColumn<Recitation,String> instructorColumn;
    TableColumn<Recitation,String> daytimeColumn;
    TableColumn<Recitation,String> locationColumn;
    TableColumn<Recitation,TeachingAssistant> firstTAColumn;
    TableColumn<Recitation,TeachingAssistant> secondTAColumn;
    
    
    VBox addeditRecitationPane;
    Label addeditHeaderLabel;
    Label sectionLabel;
    TextField sectionTextField;
    Label instructorLabel;
    TextField instructorTextField;
    Label daytimeLabel;
    TextField daytimeTextField;
    Label locationLabel;
    TextField locationTextField;
    Label firstTALabel;
    Label secondTALabel;
    ComboBox<TeachingAssistant> firstTAComboBox;
    ComboBox<TeachingAssistant> secondTAComboBox;
    
    HBox addRecitationBox;
    Button RecitationPartAddUpdateButton;
    Button RecitationPartClearrButton;
    
    
    
    public CSGRecitationWorkspace(CSGApp initapp) {
        app=initapp;
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        String recitationHeaderText=props.getProperty(CSGAppProp.RECITATION_HEADER_TEXT.toString());
        recitationHeaderLabel=new Label(recitationHeaderText);
        
        recitationTable=new TableView();
        recitationTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        String sectionColumnText=props.getProperty(CSGAppProp.SECTION_COLUMN_TEXT.toString());
        String instructorColumnText=props.getProperty(CSGAppProp.INSTRUCTOR_COLUMN_TEXT.toString());
        String daytimeColumnText=props.getProperty(CSGAppProp.DAYTIME_COLUMN_TEXT.toString());
        String locationColumnText=props.getProperty(CSGAppProp.LOCATION_COLUMN_TEXT.toString());
        String taColumnText=props.getProperty(CSGAppProp.TA_COLUMN_TEXT.toString());
         sectionColumn=new TableColumn(sectionColumnText);
         instructorColumn=new TableColumn(instructorColumnText);
        daytimeColumn=new TableColumn(daytimeColumnText);
         locationColumn=new TableColumn(locationColumnText);
          firstTAColumn=new TableColumn(taColumnText);
          secondTAColumn=new TableColumn(taColumnText);
          
          
          
          recitationTable.getColumns().addAll(sectionColumn,instructorColumn,daytimeColumn,locationColumn,firstTAColumn,secondTAColumn);
          
           addeditRecitationPane=new VBox();
          String addeditText=props.getProperty(CSGAppProp.ADD_EDIT_TEXT.toString());
          String supervisingTAText=props.getProperty(CSGAppProp.SUPERVISING_TA_TEXT.toString());
         addeditHeaderLabel=new Label(addeditText);
         
         sectionLabel=new Label(sectionColumnText+":");
         sectionTextField=new TextField();
         HBox firstHBox=new HBox(sectionLabel,sectionTextField);
         
         instructorLabel=new Label(instructorColumnText+":");
         instructorTextField=new TextField();
         HBox secondHBox=new HBox(instructorLabel,instructorTextField);
         
         daytimeLabel=new Label(daytimeColumnText+":");
         daytimeTextField=new TextField();
         HBox thirdHBox=new HBox(daytimeLabel,daytimeTextField);
         
         locationLabel=new Label(locationColumnText+":");
         locationTextField=new TextField();
         HBox fourthHBox=new HBox(locationLabel,locationTextField);
         
         firstTALabel=new Label(supervisingTAText+":");
         secondTALabel=new Label(supervisingTAText+":");
         firstTAComboBox=new ComboBox<TeachingAssistant>();
         secondTAComboBox=new ComboBox<TeachingAssistant>();
         
         HBox fifthHBox=new HBox(firstTALabel,firstTAComboBox);
         HBox sixthHBox=new HBox(secondTALabel,secondTAComboBox);
         
         String addupdateText=props.getProperty(CSGAppProp.ADD_UPDATE_TEXT.toString());
         String clearText=props.getProperty(CSGAppProp.CLEAR_BUTTON_TEXT.toString());
    
         RecitationPartAddUpdateButton=new Button(addupdateText);
         RecitationPartClearrButton=new Button(clearText);
          addRecitationBox=new HBox(RecitationPartAddUpdateButton,RecitationPartClearrButton);
          
          secondBasePane=new VBox();
          addeditRecitationPane=new VBox();
          addeditRecitationPane.getChildren().addAll( addeditHeaderLabel,firstHBox,secondHBox,thirdHBox,fourthHBox,fifthHBox,sixthHBox,addRecitationBox);
          secondBasePane.getChildren().addAll(recitationHeaderLabel,recitationTable,addeditRecitationPane);
          
          basePane=new Pane();
          basePane.getChildren().add(secondBasePane);
          
    }

    @Override
    public Pane getBasePane() {
        return basePane;
    }
    
}
