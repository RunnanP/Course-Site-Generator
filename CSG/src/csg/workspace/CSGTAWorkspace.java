/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import com.sun.xml.internal.bind.annotation.OverrideAnnotationOf;
import csg.CSGApp;
import csg.CSGAppProp;
import csg.data.CSGData;
import csg.data.CSGTAData;
import csg.data.TeachingAssistant;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import properties_manager.PropertiesManager;

/**
 *
 * @author runnan
 */
public class CSGTAWorkspace implements WorkspacePart{
    CSGApp app;
    CSGController controller;
    Pane basePane;
    
    HBox tasHeaderBox;
    Label tasHeaderLabel;
    
    TableView<TeachingAssistant> taTable;
    TableColumn<TeachingAssistant,CheckBox> underGradeColumn;
    TableColumn<TeachingAssistant,String> nameColumn;
    TableColumn<TeachingAssistant,String> emailColumn;
    
    HBox addBox;
    TextField nameTextField;
    TextField emailTextField;
    Button addButton;
    Button updateButton;
    Button clearButton;
    
    HBox officeHoursHeaderBox;
    Label officeHoursHeaderLabel;
    
    GridPane officeHoursGridPane;
    HashMap<String, Pane> officeHoursGridTimeHeaderPanes;
    HashMap<String, Label> officeHoursGridTimeHeaderLabels;
    HashMap<String, Pane> officeHoursGridDayHeaderPanes;
    HashMap<String, Label> officeHoursGridDayHeaderLabels;
    HashMap<String, Pane> officeHoursGridTimeCellPanes;
    HashMap<String, Label> officeHoursGridTimeCellLabels;
    HashMap<String, Pane> officeHoursGridTACellPanes;
    HashMap<String, Label> officeHoursGridTACellLabels;
    
    ComboBox startTimeComboBox;
    Label startTimeComboBoxLabel;
   
    ComboBox endTimeComboBox;
    Label endTimeComboBoxLabel;
    
    ObservableList<String> startTimeTableView;
    ObservableList<String> endTimeTableView;
    
    Label startTimeLabel;
    Label endTimeLabel;
    
    public CSGTAWorkspace(CSGApp initapp) {
        app=initapp;
         PropertiesManager props = PropertiesManager.getPropertiesManager();
         
         tasHeaderBox = new HBox();
        String tasHeaderText = props.getProperty(CSGAppProp.TAS_HEADER_TEXT.toString());
        tasHeaderLabel = new Label(tasHeaderText);
        tasHeaderBox.getChildren().add(tasHeaderLabel);
        
         taTable = new TableView();
        taTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
       // CSGData data=(CSGData) app.getDataComponent();
       
        //ObservableList<TeachingAssistant> tableData=data.getTeachingAssistants();
        
        //taTable.setItems(tableData);
        String undergradeColumnText=props.getProperty(CSGAppProp.UNDERGRADE_COLUMN_TEXT.toString());
        String nameColumnText = props.getProperty(CSGAppProp.NAME_COLUMN_TEXT.toString());
        String emailColumnText = props.getProperty(CSGAppProp.EMAIL_COLUMN_TEXT.toString());
        underGradeColumn=new TableColumn(undergradeColumnText);
        nameColumn = new TableColumn(nameColumnText);
        emailColumn=new TableColumn(emailColumnText);
        
        underGradeColumn.setCellValueFactory(
                new PropertyValueFactory<TeachingAssistant, CheckBox>("underGrad")
        );
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<TeachingAssistant, String>("name")
        );
        emailColumn.setCellValueFactory(
                new PropertyValueFactory<TeachingAssistant, String>("email")
        );
           
        taTable.getColumns().add(underGradeColumn);
        taTable.getColumns().add(nameColumn);
        taTable.getColumns().add(emailColumn);
        
        
        
        String namePromptText = props.getProperty(CSGAppProp.NAME_PROMPT_TEXT.toString());
        String emailPromptText = props.getProperty(CSGAppProp.EMAIL_PROMPT_TEXT.toString());
        
        
        String addButtonText = props.getProperty(CSGAppProp.ADD_BUTTON_TEXT.toString());
        String updateButtonText=props.getProperty(CSGAppProp.UPDATE_BUTTON_TEXT.toString());
        String clearButtonText=props.getProperty(CSGAppProp.CLEAR_BUTTON_TEXT.toString());
        
        nameTextField = new TextField();
        emailTextField = new TextField();
        nameTextField.setPromptText(namePromptText);
        emailTextField.setPromptText(emailPromptText);
        
        addButton = new Button(addButtonText);
        updateButton=new Button(updateButtonText);
        clearButton=new Button(clearButtonText);
        
        addBox = new HBox();
        nameTextField.prefWidthProperty().bind(addBox.widthProperty().multiply(.4));
        emailTextField.prefWidthProperty().bind(addBox.widthProperty().multiply(.4));
        
        addButton.prefWidthProperty().bind(addBox.widthProperty().multiply(.2));
        updateButton.prefWidthProperty().bind(addBox.widthProperty().multiply(.2));
        clearButton.prefWidthProperty().bind(addBox.widthProperty().multiply(.2));
        
        
        
        addBox.getChildren().add(nameTextField);
        addBox.getChildren().add(emailTextField);
        addBox.getChildren().add(addButton);
        
        /////////////
           officeHoursHeaderBox = new HBox();
        String officeHoursGridText = props.getProperty(CSGAppProp.OFFICE_HOURS_SUBHEADER.toString());
        officeHoursHeaderLabel = new Label(officeHoursGridText);
        officeHoursHeaderBox.getChildren().add(officeHoursHeaderLabel);
        
      
        
        String startHoursText = props.getProperty(CSGAppProp.START_HOURS.toString());
        String endHoursText = props.getProperty(CSGAppProp.END_HOURS.toString());
        
        startTimeLabel=new Label(startHoursText);
        endTimeLabel=new Label(endHoursText);
        
        initTimeComboBox();
        
        //    right side
        
         HBox timeComboBox=new HBox();
         timeComboBox.getChildren().add(startTimeLabel);
        timeComboBox.getChildren().add(startTimeComboBox);
         timeComboBox.getChildren().add(endTimeLabel);
        timeComboBox.getChildren().add(endTimeComboBox);
        
       HBox space=new HBox();
       space.setHgrow(space, Priority.ALWAYS);
        officeHoursHeaderBox.getChildren().add(space);
         officeHoursHeaderBox.getChildren().add(timeComboBox);
       officeHoursHeaderLabel.setAlignment(Pos.CENTER_LEFT);
        timeComboBox.setAlignment(Pos.CENTER_RIGHT);
   
        
        
        // THESE WILL STORE PANES AND LABELS FOR OUR OFFICE HOURS GRID
        officeHoursGridPane = new GridPane();
        officeHoursGridTimeHeaderPanes = new HashMap();
        officeHoursGridTimeHeaderLabels = new HashMap();
        officeHoursGridDayHeaderPanes = new HashMap();
        officeHoursGridDayHeaderLabels = new HashMap();
        officeHoursGridTimeCellPanes = new HashMap();
        officeHoursGridTimeCellLabels = new HashMap();
        officeHoursGridTACellPanes = new HashMap();
        officeHoursGridTACellLabels = new HashMap();

        // ORGANIZE THE LEFT AND RIGHT PANES
        VBox leftPane = new VBox();
        leftPane.getChildren().add(tasHeaderBox);        
        leftPane.getChildren().add(taTable);        
        leftPane.getChildren().add(addBox);
        VBox rightPane = new VBox();
        rightPane.getChildren().add(officeHoursHeaderBox);
        rightPane.getChildren().add(officeHoursGridPane);
        
        
        
        
         SplitPane sPane = new SplitPane(leftPane, new ScrollPane(rightPane));
         basePane=new Pane();
         basePane.getChildren().add(sPane);
        
       
        

        
        

        
        controller = new CSGController(app);

        
        
    }
    public void initTimeComboBox(){
       startTimeTableView= FXCollections.observableArrayList();
        endTimeTableView= FXCollections.observableArrayList();
             startTimeComboBox=new ComboBox(startTimeTableView);
        endTimeComboBox=new ComboBox(endTimeTableView);
    
    
         startTimeComboBox.getItems().addAll("12:00am");
         
        endTimeComboBox.getItems().addAll("12:00am");
        for (int i=1;i<=11;i++){
               for (int j=1;j<=2;j++){
                   String s = Integer.toString(i);
                   if (j==1){            
                       startTimeComboBox.getItems().add(s+ ":00am");
                       endTimeComboBox.getItems().add(s+":00am");
                   } else {
                     //  startTimeComboBox.getItems().add(s+ ":30am");
                       //endTimeComboBox.getItems().add(s+":30am");
                   }     
               }
             }
       // startTimeComboBox.getItems().addAll("12:00pm","12:30pm");
       // endTimeComboBox.getItems().addAll("12:00pm","12:30pm");
             startTimeComboBox.getItems().addAll("12:00pm");
        endTimeComboBox.getItems().addAll("12:00pm");
         for (int i=1;i<=11;i++){
               for (int j=1;j<=2;j++){
                   String s = Integer.toString(i);
                   if (j==1){            
                       startTimeComboBox.getItems().add(s+ ":00pm");
                       endTimeComboBox.getItems().add(s+":00pm");
                   } else {
                  //     startTimeComboBox.getItems().add(s+ ":30pm");
                 //      endTimeComboBox.getItems().add(s+":30pm");
                   }     
               }
             }
    
}
    
    public Pane getBasePane(){
        return basePane;
    }
}