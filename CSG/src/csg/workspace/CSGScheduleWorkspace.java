/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import csg.CSGApp;
import csg.CSGAppProp;
import csg.data.ScheduleItem;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
public class CSGScheduleWorkspace implements WorkspacePart{
    CSGApp app;
    CSGController controller;
    ScrollPane basePane;
    
   VBox secondBasePane;
   Label scheduleHeaderLabl;
   
   VBox calendarChooseBox;
   Label calendarHeaderLabel;
   HBox dateChooseBox;
   Label startDateLabel;
   DatePicker startDatePicker;
   Label endDateLabel;
   DatePicker endDatePicker;
   
   VBox scheduleItemBox;
   Label scheduleItemHeaderLabel;
   TableView<ScheduleItem> scheduleItemsTable;
   TableColumn<ScheduleItem,String> typeColumn;
   TableColumn<ScheduleItem,String> dateColumn;
   TableColumn<ScheduleItem,String> titleColumn;
   TableColumn<ScheduleItem,String> topicColumn;
   
   Label addeditLabel;
   Label typeLabel;
   ComboBox<ScheduleItem> typeComboBox;
   Label dateLabel;
   DatePicker datePicker;
   Label timeLabel;
   TextField timeTextField;
   Label titleLabel;
   TextField titleTextField;
   Label topicLabel;
   TextField topicTextField;
   Label linkLabel;
   TextField linkTextField;
   Label criteriaLabel;
   TextField criteriaTextField;
   HBox addBox;
   Button addupdateButton;
   Button clearButton;
   HBox firstHBox;
   HBox secondHBox;
   HBox thirdHBox;
   HBox fourthHBox;
   HBox fifthHBox;
   HBox sixthHBox;
   HBox seventhHBox;
   
   
   
   public CSGScheduleWorkspace(CSGApp initapp) {
        app=initapp;
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
         String scheduleHeaderText = props.getProperty(CSGAppProp.SCHEDULE_HEADER_TEXT.toString());
         scheduleHeaderLabl=new Label(scheduleHeaderText);
         
           calendarChooseBox=new VBox();
           String calendarHeaderText = props.getProperty(CSGAppProp.CALENDAR_HEADER_TEXT.toString());
          calendarHeaderLabel=new Label(calendarHeaderText);
         dateChooseBox=new HBox();
         String startDateText = props.getProperty(CSGAppProp.STARTING_DATE_TEXT.toString());
        startDateLabel=new Label(startDateText);
         startDatePicker=new DatePicker();
         startDatePicker.setShowWeekNumbers(true);
          String endDateText = props.getProperty(CSGAppProp.ENDING_DATE_TEXT.toString());
    endDateLabel=new Label(endDateText);
       endDatePicker=new DatePicker();
       endDatePicker.setShowWeekNumbers(true);
        dateChooseBox.getChildren().addAll(startDateLabel,startDatePicker,endDateLabel,endDatePicker);
        
        calendarChooseBox.getChildren().addAll(calendarHeaderLabel,dateChooseBox);
        
        
        scheduleItemBox=new VBox();
        String scheduleItemsText = props.getProperty(CSGAppProp.SCHEDULE_ITEMS_TEXT.toString());
        scheduleHeaderLabl=new Label(scheduleItemsText);
        
        scheduleItemsTable=new TableView<>();
        scheduleItemsTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        String typeColumnText = props.getProperty(CSGAppProp.TYPE_COLUMN_TEXT.toString());
        String dateColumnText = props.getProperty(CSGAppProp.DATE_COLUMN_TEXT.toString());
        String titleColumnText = props.getProperty(CSGAppProp.TITLE_COLUMN_TEXT.toString());
        String topicColumnText=props.getProperty(CSGAppProp.TOPIC_COLUMN_TEXT.toString());
        
        typeColumn=new TableColumn(typeColumnText);
        dateColumn=new TableColumn(dateColumnText);
        titleColumn=new TableColumn(titleColumnText);
        topicColumn=new TableColumn(topicColumnText);
        
        scheduleItemsTable.getColumns().addAll(typeColumn,dateColumn,titleColumn,topicColumn);
        String addeditText=props.getProperty(CSGAppProp.ADD_EDIT_TEXT.toString());
        addeditLabel=new Label(addeditText);
        typeLabel=new Label(typeColumnText+":");
        typeComboBox=new ComboBox<>();
        dateLabel=new Label(dateColumnText+":");
        datePicker=new DatePicker();
        datePicker.setShowWeekNumbers(true);
        String timeText=props.getProperty(CSGAppProp.TIME_TEXT.toString());
        timeLabel=new Label(timeText);
        timeTextField=new TextField();
        titleLabel=new Label(titleColumnText+":");
        titleTextField=new TextField();
        topicLabel=new Label(topicColumnText+":");
        topicTextField=new TextField();
        String linkText=props.getProperty(CSGAppProp.LINK_TEXT.toString());
        linkLabel=new Label(linkText+":");
        linkTextField=new TextField();
        String criteriaText=props.getProperty(CSGAppProp.CRITERIA_TEXT.toString());
        criteriaLabel=new Label(criteriaText+":");
        criteriaTextField=new TextField();
        addBox=new HBox();
        String addupdateButtonText=props.getProperty(CSGAppProp.ADD_UPDATE_TEXT.toString());
        addupdateButton=new Button(addupdateButtonText);
        String clearButtonText=props.getProperty(CSGAppProp.CLEAR_BUTTON_TEXT.toString());
        clearButton=new Button(clearButtonText);
        addBox.getChildren().addAll(addupdateButton,clearButton);
        
          firstHBox=new HBox(typeLabel,typeComboBox);
          secondHBox=new HBox(dateLabel,datePicker);
          thirdHBox=new HBox(timeLabel,timeTextField);
          fourthHBox=new HBox(titleLabel,titleTextField);
          fifthHBox=new HBox(topicLabel,topicTextField);
          sixthHBox=new HBox(linkLabel,linkTextField);
          seventhHBox=new HBox(criteriaLabel,criteriaTextField);
        scheduleItemBox.getChildren().addAll(scheduleHeaderLabl,scheduleItemsTable,addeditLabel,firstHBox,secondHBox,thirdHBox,fourthHBox,fifthHBox,sixthHBox,seventhHBox,addBox);
        secondBasePane=new VBox();
        secondBasePane.getChildren().addAll(scheduleHeaderLabl,calendarChooseBox,scheduleItemBox);
        basePane=new ScrollPane(secondBasePane);
          secondBasePane.prefWidthProperty().bind(app.getGUI().getWindow().widthProperty().multiply(1));
          secondBasePane.prefHeightProperty().bind(app.getGUI().getWindow().heightProperty().multiply(1));
          timeTextField.prefWidthProperty().bind(app.getGUI().getWindow().widthProperty().multiply(.7));
          titleTextField.prefWidthProperty().bind(app.getGUI().getWindow().widthProperty().multiply(.7));
          topicTextField.prefWidthProperty().bind(app.getGUI().getWindow().widthProperty().multiply(.7));
          linkTextField.prefWidthProperty().bind(app.getGUI().getWindow().widthProperty().multiply(.7));
          criteriaTextField.prefWidthProperty().bind(app.getGUI().getWindow().widthProperty().multiply(.7));
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

    public Label getScheduleHeaderLabl() {
        return scheduleHeaderLabl;
    }

    public void setScheduleHeaderLabl(Label scheduleHeaderLabl) {
        this.scheduleHeaderLabl = scheduleHeaderLabl;
    }

    public VBox getCalendarChooseBox() {
        return calendarChooseBox;
    }

    public void setCalendarChooseBox(VBox calendarChooseBox) {
        this.calendarChooseBox = calendarChooseBox;
    }

    public Label getCalendarHeaderLabel() {
        return calendarHeaderLabel;
    }

    public void setCalendarHeaderLabel(Label calendarHeaderLabel) {
        this.calendarHeaderLabel = calendarHeaderLabel;
    }

    public HBox getDateChooseBox() {
        return dateChooseBox;
    }

    public void setDateChooseBox(HBox dateChooseBox) {
        this.dateChooseBox = dateChooseBox;
    }

    public Label getStartDateLabel() {
        return startDateLabel;
    }

    public void setStartDateLabel(Label startDateLabel) {
        this.startDateLabel = startDateLabel;
    }

    public DatePicker getStartDatePicker() {
        return startDatePicker;
    }

    public void setStartDatePicker(DatePicker startDatePicker) {
        this.startDatePicker = startDatePicker;
    }

    public Label getEndDateLabel() {
        return endDateLabel;
    }

    public void setEndDateLabel(Label endDateLabel) {
        this.endDateLabel = endDateLabel;
    }

    public DatePicker getEndDatePicker() {
        return endDatePicker;
    }

    public void setEndDatePicker(DatePicker endDatePicker) {
        this.endDatePicker = endDatePicker;
    }

    public VBox getScheduleItemBox() {
        return scheduleItemBox;
    }

    public void setScheduleItemBox(VBox scheduleItemBox) {
        this.scheduleItemBox = scheduleItemBox;
    }

    public Label getScheduleItemHeaderLabel() {
        return scheduleItemHeaderLabel;
    }

    public void setScheduleItemHeaderLabel(Label scheduleItemHeaderLabel) {
        this.scheduleItemHeaderLabel = scheduleItemHeaderLabel;
    }

    public TableView<ScheduleItem> getScheduleItemsTable() {
        return scheduleItemsTable;
    }

    public void setScheduleItemsTable(TableView<ScheduleItem> scheduleItemsTable) {
        this.scheduleItemsTable = scheduleItemsTable;
    }

    public TableColumn<ScheduleItem, String> getTypeColumn() {
        return typeColumn;
    }

    public void setTypeColumn(TableColumn<ScheduleItem, String> typeColumn) {
        this.typeColumn = typeColumn;
    }

    public TableColumn<ScheduleItem, String> getDateColumn() {
        return dateColumn;
    }

    public void setDateColumn(TableColumn<ScheduleItem, String> dateColumn) {
        this.dateColumn = dateColumn;
    }

    public TableColumn<ScheduleItem, String> getTitleColumn() {
        return titleColumn;
    }

    public void setTitleColumn(TableColumn<ScheduleItem, String> titleColumn) {
        this.titleColumn = titleColumn;
    }

    public TableColumn<ScheduleItem, String> getTopicColumn() {
        return topicColumn;
    }

    public void setTopicColumn(TableColumn<ScheduleItem, String> topicColumn) {
        this.topicColumn = topicColumn;
    }

    public Label getAddeditLabel() {
        return addeditLabel;
    }

    public void setAddeditLabel(Label addeditLabel) {
        this.addeditLabel = addeditLabel;
    }

    public Label getTypeLabel() {
        return typeLabel;
    }

    public void setTypeLabel(Label typeLabel) {
        this.typeLabel = typeLabel;
    }

    public ComboBox<ScheduleItem> getTypeComboBox() {
        return typeComboBox;
    }

    public void setTypeComboBox(ComboBox<ScheduleItem> typeComboBox) {
        this.typeComboBox = typeComboBox;
    }

    public Label getDateLabel() {
        return dateLabel;
    }

    public void setDateLabel(Label dateLabel) {
        this.dateLabel = dateLabel;
    }

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public void setDatePicker(DatePicker datePicker) {
        this.datePicker = datePicker;
    }

    public Label getTimeLabel() {
        return timeLabel;
    }

    public void setTimeLabel(Label timeLabel) {
        this.timeLabel = timeLabel;
    }

    public TextField getTimeTextField() {
        return timeTextField;
    }

    public void setTimeTextField(TextField timeTextField) {
        this.timeTextField = timeTextField;
    }

    public Label getTitleLabel() {
        return titleLabel;
    }

    public void setTitleLabel(Label titleLabel) {
        this.titleLabel = titleLabel;
    }

    public TextField getTitleTextField() {
        return titleTextField;
    }

    public void setTitleTextField(TextField titleTextField) {
        this.titleTextField = titleTextField;
    }

    public Label getTopicLabel() {
        return topicLabel;
    }

    public void setTopicLabel(Label topicLabel) {
        this.topicLabel = topicLabel;
    }

    public TextField getTopicTextField() {
        return topicTextField;
    }

    public void setTopicTextField(TextField topicTextField) {
        this.topicTextField = topicTextField;
    }

    public Label getLinkLabel() {
        return linkLabel;
    }

    public void setLinkLabel(Label linkLabel) {
        this.linkLabel = linkLabel;
    }

    public TextField getLinkTextField() {
        return linkTextField;
    }

    public void setLinkTextField(TextField linkTextField) {
        this.linkTextField = linkTextField;
    }

    public Label getCriteriaLabel() {
        return criteriaLabel;
    }

    public void setCriteriaLabel(Label criteriaLabel) {
        this.criteriaLabel = criteriaLabel;
    }

    public TextField getCriteriaTextField() {
        return criteriaTextField;
    }

    public void setCriteriaTextField(TextField criteriaTextField) {
        this.criteriaTextField = criteriaTextField;
    }

    public HBox getAddBox() {
        return addBox;
    }

    public void setAddBox(HBox addBox) {
        this.addBox = addBox;
    }

    public Button getAddupdateButton() {
        return addupdateButton;
    }

    public void setAddupdateButton(Button addupdateButton) {
        this.addupdateButton = addupdateButton;
    }

    public Button getClearButton() {
        return clearButton;
    }

    public void setClearButton(Button clearButton) {
        this.clearButton = clearButton;
    }

    public HBox getFirstHBox() {
        return firstHBox;
    }

    public void setFirstHBox(HBox firstHBox) {
        this.firstHBox = firstHBox;
    }

    public HBox getSecondHBox() {
        return secondHBox;
    }

    public void setSecondHBox(HBox secondHBox) {
        this.secondHBox = secondHBox;
    }

    public HBox getThirdHBox() {
        return thirdHBox;
    }

    public void setThirdHBox(HBox thirdHBox) {
        this.thirdHBox = thirdHBox;
    }

    public HBox getFourthHBox() {
        return fourthHBox;
    }

    public void setFourthHBox(HBox fourthHBox) {
        this.fourthHBox = fourthHBox;
    }

    public HBox getFifthHBox() {
        return fifthHBox;
    }

    public void setFifthHBox(HBox fifthHBox) {
        this.fifthHBox = fifthHBox;
    }

    public HBox getSixthHBox() {
        return sixthHBox;
    }

    public void setSixthHBox(HBox sixthHBox) {
        this.sixthHBox = sixthHBox;
    }

    public HBox getSeventhHBox() {
        return seventhHBox;
    }

    public void setSeventhHBox(HBox seventhHBox) {
        this.seventhHBox = seventhHBox;
    }
    
}
