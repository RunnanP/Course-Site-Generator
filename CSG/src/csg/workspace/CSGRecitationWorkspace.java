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
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
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
    ScrollPane basePane;
    
    VBox secondBasePane;
    
    Label recitationHeaderLabel;
    HBox recitationHeaderBox;
    Button subButton;
    
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
         subButton=new Button("-");
        recitationHeaderBox=new HBox(recitationHeaderLabel,subButton);
        
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
          recitationTable.prefWidthProperty().bind(app.getGUI().getWindow().widthProperty().multiply(0.8));
          recitationTable.prefHeightProperty().bind(app.getGUI().getWindow().heightProperty().multiply(0.5));
          FlowPane tableFlowPane=new FlowPane(recitationTable);
          tableFlowPane.setPadding(new Insets(11,12,13,14));
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
         HBox s1=new HBox();
         
          addRecitationBox=new HBox(RecitationPartAddUpdateButton,s1,RecitationPartClearrButton);
          s1.setPrefWidth(100);
          secondBasePane=new VBox();
          addeditRecitationPane=new VBox();
          addeditRecitationPane.setPadding(new Insets(11,12,13,14));
          FlowPane tempflowpane=new FlowPane();
          HBox t0=new HBox();
          HBox t1=new HBox();
          HBox t2=new HBox();
          HBox t3=new HBox();
          HBox t4=new HBox();
          HBox t5=new HBox();
          HBox t6=new HBox();
          HBox t7=new HBox();
          HBox t8=new HBox();
          HBox t9=new HBox();
          HBox t10=new HBox();
          HBox t11=new HBox();
          HBox t12=new HBox();
          tempflowpane.getChildren().addAll(addeditHeaderLabel,t0,sectionLabel,t1,sectionTextField,t2,instructorLabel,t3,instructorTextField,t4,daytimeLabel,t5,daytimeTextField,t6,locationLabel,t7,locationTextField,t8,firstTALabel,t9,firstTAComboBox,t10,secondTALabel,t11,secondTAComboBox,t12,addRecitationBox);
          t0.setPrefWidth(2000);
          t1.setPrefWidth(90);
          t2.setPrefWidth(2000);
          t3.setPrefWidth(50);
          t4.setPrefWidth(2000);
          t5.setPrefWidth(80);
          t6.setPrefWidth(2000);
          t7.setPrefWidth(80);
          t8.setPrefWidth(2000);
          t9.setPrefWidth(100);
          t10.setPrefWidth(2000);
          t11.setPrefWidth(100);
          t12.setPrefWidth(2000);
         tempflowpane.setVgap(10);
         firstTAComboBox.setPrefWidth(300);
         secondTAComboBox.setPrefWidth(300);

//addeditRecitationPane.getChildren().addAll( addeditHeaderLabel,firstHBox,secondHBox,thirdHBox,fourthHBox,fifthHBox,sixthHBox,addRecitationBox);
            addeditRecitationPane.getChildren().addAll(tempflowpane);
          secondBasePane.getChildren().addAll(recitationHeaderBox,tableFlowPane,addeditRecitationPane);
          
          basePane=new ScrollPane(secondBasePane);
          //basePane.getChildren().add(secondBasePane);
          secondBasePane.prefWidthProperty().bind(app.getGUI().getWindow().widthProperty().multiply(1));
          secondBasePane.prefHeightProperty().bind(app.getGUI().getWindow().heightProperty().multiply(1));
          sectionTextField.prefWidthProperty().bind(app.getGUI().getWindow().widthProperty().multiply(.3));
          instructorTextField.prefWidthProperty().bind(app.getGUI().getWindow().widthProperty().multiply(.3));
          daytimeTextField.prefWidthProperty().bind(app.getGUI().getWindow().widthProperty().multiply(.3));
          locationTextField.prefWidthProperty().bind(app.getGUI().getWindow().widthProperty().multiply(.3));
    }

    @Override
    public ScrollPane getBasePane() {
        return basePane;
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

    public VBox getSecondBasePane() {
        return secondBasePane;
    }

    public void setSecondBasePane(VBox secondBasePane) {
        this.secondBasePane = secondBasePane;
    }

    public Label getRecitationHeaderLabel() {
        return recitationHeaderLabel;
    }

    public void setRecitationHeaderLabel(Label recitationHeaderLabel) {
        this.recitationHeaderLabel = recitationHeaderLabel;
    }

    public TableView<Recitation> getRecitationTable() {
        return recitationTable;
    }

    public void setRecitationTable(TableView<Recitation> recitationTable) {
        this.recitationTable = recitationTable;
    }

    public TableColumn<Recitation, String> getSectionColumn() {
        return sectionColumn;
    }

    public void setSectionColumn(TableColumn<Recitation, String> sectionColumn) {
        this.sectionColumn = sectionColumn;
    }

    public TableColumn<Recitation, String> getInstructorColumn() {
        return instructorColumn;
    }

    public void setInstructorColumn(TableColumn<Recitation, String> instructorColumn) {
        this.instructorColumn = instructorColumn;
    }

    public TableColumn<Recitation, String> getDaytimeColumn() {
        return daytimeColumn;
    }

    public void setDaytimeColumn(TableColumn<Recitation, String> daytimeColumn) {
        this.daytimeColumn = daytimeColumn;
    }

    public TableColumn<Recitation, String> getLocationColumn() {
        return locationColumn;
    }

    public void setLocationColumn(TableColumn<Recitation, String> locationColumn) {
        this.locationColumn = locationColumn;
    }

    public TableColumn<Recitation, TeachingAssistant> getFirstTAColumn() {
        return firstTAColumn;
    }

    public void setFirstTAColumn(TableColumn<Recitation, TeachingAssistant> firstTAColumn) {
        this.firstTAColumn = firstTAColumn;
    }

    public TableColumn<Recitation, TeachingAssistant> getSecondTAColumn() {
        return secondTAColumn;
    }

    public void setSecondTAColumn(TableColumn<Recitation, TeachingAssistant> secondTAColumn) {
        this.secondTAColumn = secondTAColumn;
    }

    public VBox getAddeditRecitationPane() {
        return addeditRecitationPane;
    }

    public void setAddeditRecitationPane(VBox addeditRecitationPane) {
        this.addeditRecitationPane = addeditRecitationPane;
    }

    public Label getAddeditHeaderLabel() {
        return addeditHeaderLabel;
    }

    public void setAddeditHeaderLabel(Label addeditHeaderLabel) {
        this.addeditHeaderLabel = addeditHeaderLabel;
    }

    public Label getSectionLabel() {
        return sectionLabel;
    }

    public void setSectionLabel(Label sectionLabel) {
        this.sectionLabel = sectionLabel;
    }

    public TextField getSectionTextField() {
        return sectionTextField;
    }

    public void setSectionTextField(TextField sectionTextField) {
        this.sectionTextField = sectionTextField;
    }

    public Label getInstructorLabel() {
        return instructorLabel;
    }

    public void setInstructorLabel(Label instructorLabel) {
        this.instructorLabel = instructorLabel;
    }

    public TextField getInstructorTextField() {
        return instructorTextField;
    }

    public void setInstructorTextField(TextField instructorTextField) {
        this.instructorTextField = instructorTextField;
    }

    public Label getDaytimeLabel() {
        return daytimeLabel;
    }

    public void setDaytimeLabel(Label daytimeLabel) {
        this.daytimeLabel = daytimeLabel;
    }

    public TextField getDaytimeTextField() {
        return daytimeTextField;
    }

    public void setDaytimeTextField(TextField daytimeTextField) {
        this.daytimeTextField = daytimeTextField;
    }

    public Label getLocationLabel() {
        return locationLabel;
    }

    public void setLocationLabel(Label locationLabel) {
        this.locationLabel = locationLabel;
    }

    public TextField getLocationTextField() {
        return locationTextField;
    }

    public void setLocationTextField(TextField locationTextField) {
        this.locationTextField = locationTextField;
    }

    public Label getFirstTALabel() {
        return firstTALabel;
    }

    public void setFirstTALabel(Label firstTALabel) {
        this.firstTALabel = firstTALabel;
    }

    public Label getSecondTALabel() {
        return secondTALabel;
    }

    public void setSecondTALabel(Label secondTALabel) {
        this.secondTALabel = secondTALabel;
    }

    public ComboBox<TeachingAssistant> getFirstTAComboBox() {
        return firstTAComboBox;
    }

    public void setFirstTAComboBox(ComboBox<TeachingAssistant> firstTAComboBox) {
        this.firstTAComboBox = firstTAComboBox;
    }

    public ComboBox<TeachingAssistant> getSecondTAComboBox() {
        return secondTAComboBox;
    }

    public void setSecondTAComboBox(ComboBox<TeachingAssistant> secondTAComboBox) {
        this.secondTAComboBox = secondTAComboBox;
    }

    public HBox getAddRecitationBox() {
        return addRecitationBox;
    }

    public void setAddRecitationBox(HBox addRecitationBox) {
        this.addRecitationBox = addRecitationBox;
    }

    public Button getRecitationPartAddUpdateButton() {
        return RecitationPartAddUpdateButton;
    }

    public void setRecitationPartAddUpdateButton(Button RecitationPartAddUpdateButton) {
        this.RecitationPartAddUpdateButton = RecitationPartAddUpdateButton;
    }

    public Button getRecitationPartClearrButton() {
        return RecitationPartClearrButton;
    }

    public void setRecitationPartClearrButton(Button RecitationPartClearrButton) {
        this.RecitationPartClearrButton = RecitationPartClearrButton;
    }

    public HBox getRecitationHeaderBox() {
        return recitationHeaderBox;
    }

    public void setRecitationHeaderBox(HBox recitationHeaderBox) {
        this.recitationHeaderBox = recitationHeaderBox;
    }
    
    
}
