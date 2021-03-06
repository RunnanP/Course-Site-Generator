/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import csg.CSGApp;
import csg.CSGAppProp;
import csg.data.CSGData;
import csg.data.Recitation;
import csg.data.ScheduleItem;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
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
   HBox scheduleHeaderBox;
   
   VBox calendarChooseBox;
   Label calendarHeaderLabel;
   HBox dateChooseBox;
   Label startDateLabel;
   DatePicker startDatePicker;
   Label endDateLabel;
   DatePicker endDatePicker;
   
   FlowPane scheduleItemBox;
   Label scheduleItemHeaderLabel;
   Button subButton;
   TableView<ScheduleItem> scheduleItemsTable;
   TableColumn<ScheduleItem,String> typeColumn;
   TableColumn<ScheduleItem,String> dateColumn;
   TableColumn<ScheduleItem,String> titleColumn;
   TableColumn<ScheduleItem,String> topicColumn;
   
   Label addeditLabel;
   Label typeLabel;
   ComboBox<String> typeComboBox;
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
   
   
   
   public CSGScheduleWorkspace(CSGApp initapp) throws ParseException {
        app=initapp;
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
         String scheduleHeaderText = props.getProperty(CSGAppProp.SCHEDULE_HEADER_TEXT.toString());
         scheduleHeaderLabl=new Label(scheduleHeaderText);
         scheduleHeaderBox=new HBox(scheduleHeaderLabl);
         
           calendarChooseBox=new VBox();
             calendarChooseBox.setPadding(new Insets(11,12,13,14));
           String calendarHeaderText = props.getProperty(CSGAppProp.CALENDAR_HEADER_TEXT.toString());
          calendarHeaderLabel=new Label(calendarHeaderText);
         dateChooseBox=new HBox();
         String startDateText = props.getProperty(CSGAppProp.STARTING_DATE_TEXT.toString());
        startDateLabel=new Label(startDateText+":");
         startDatePicker=new DatePicker();
         startDatePicker.setShowWeekNumbers(true);
       //  startDatePicker.setDisable(true);
       
       
       
       
       
       
       
        
          String endDateText = props.getProperty(CSGAppProp.ENDING_DATE_TEXT.toString());
    endDateLabel=new Label(endDateText+":");
       endDatePicker=new DatePicker();
       endDatePicker.setShowWeekNumbers(true);
       
       
       
         
     //   endDatePicker.setValue(startDatePicker.getValue().plusDays(1));
       
       
       HBox a1=new HBox();
        dateChooseBox.getChildren().addAll(startDateLabel,startDatePicker,a1,endDateLabel,endDatePicker);
        a1.setPrefWidth(50);
        
        calendarChooseBox.getChildren().addAll(calendarHeaderLabel,dateChooseBox);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
            final Callback<DatePicker, DateCell> startdayCellFactory = 
            new Callback<DatePicker, DateCell>() {
                @Override
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);
                            if (item.isBefore(
                                    startDatePicker.getValue().plusDays(1))
                                ) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                            }
                            long p = ChronoUnit.DAYS.between(
                                    startDatePicker.getValue(), item
                            );
                         
                    }
                };
            }
        };
       
        
        
        
        
            
            
            
            
               final Callback<DatePicker, DateCell> enddayCellFactory = 
            new Callback<DatePicker, DateCell>() {
                @Override
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);
                            if (item.isAfter(
                                    endDatePicker.getValue().plusDays(-1))
                                ) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                            }
                            long p = ChronoUnit.DAYS.between(
                                    endDatePicker.getValue(), item
                            );
                         
                    }
                };
            }
        };
            
         
               
        startDatePicker.setDayCellFactory(enddayCellFactory);
        endDatePicker.setDayCellFactory(startdayCellFactory);
        
        
        
        
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
        
        
        
        scheduleItemBox=new FlowPane();
        scheduleItemBox.setHgap(20);
        scheduleItemBox.setPadding(new Insets(11,12,13,14));
        String scheduleItemsText = props.getProperty(CSGAppProp.SCHEDULE_ITEMS_TEXT.toString());
        scheduleItemHeaderLabel=new Label(scheduleItemsText);
        
        scheduleItemsTable=new TableView<>();
        scheduleItemsTable.prefWidthProperty().bind(app.getGUI().getWindow().widthProperty().multiply(0.8));
        scheduleItemsTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        CSGData data=(CSGData) app.getDataComponent();
        
        ObservableList<ScheduleItem> scheduleItemsData=data.getScheduleItems();
        scheduleItemsTable.setItems(scheduleItemsData);
        
        String typeColumnText = props.getProperty(CSGAppProp.TYPE_COLUMN_TEXT.toString());
        String dateColumnText = props.getProperty(CSGAppProp.DATE_COLUMN_TEXT.toString());
        String titleColumnText = props.getProperty(CSGAppProp.TITLE_COLUMN_TEXT.toString());
        String topicColumnText=props.getProperty(CSGAppProp.TOPIC_COLUMN_TEXT.toString());
        
        typeColumn=new TableColumn(typeColumnText);
        typeColumn.setPrefWidth(300);
        dateColumn=new TableColumn(dateColumnText);
         dateColumn.setPrefWidth(300);
        titleColumn=new TableColumn(titleColumnText);
       titleColumn.setPrefWidth(300);
        topicColumn=new TableColumn(topicColumnText);
        topicColumn.setPrefWidth(300);
        
        typeColumn.setCellValueFactory(new PropertyValueFactory<ScheduleItem, String>("type"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<ScheduleItem, String>("date"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<ScheduleItem, String>("title"));
        topicColumn.setCellValueFactory(new PropertyValueFactory<ScheduleItem, String>("topic"));
        
        
        scheduleItemsTable.getColumns().addAll(typeColumn,dateColumn,titleColumn,topicColumn);
        String addeditText=props.getProperty(CSGAppProp.ADD_EDIT_TEXT.toString());
        addeditLabel=new Label(addeditText);
        typeLabel=new Label(typeColumnText+":");
        typeComboBox=new ComboBox<>();
        
        typeComboBox.getItems().addAll("Holiday","Lecture","Reference","HW");
        dateLabel=new Label(dateColumnText+":");
        datePicker=new DatePicker();
        datePicker.setShowWeekNumbers(true);
        String timeText=props.getProperty(CSGAppProp.TIME_TEXT.toString());
        timeLabel=new Label(timeText+":");
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
        HBox s0=new HBox();
        addBox.getChildren().addAll(addupdateButton,s0,clearButton);
        s0.setPrefWidth(20);
              
            HBox s1=new HBox();
          HBox s2=new HBox();
          HBox s3=new HBox();
          HBox s4=new HBox();
          HBox s5=new HBox();
          HBox s6=new HBox();
          HBox s7=new HBox();
          firstHBox=new HBox(typeLabel,s1,typeComboBox);
          typeComboBox.setPrefWidth(200);
          secondHBox=new HBox(dateLabel,s2,datePicker);
          thirdHBox=new HBox(timeLabel,s3,timeTextField);
          fourthHBox=new HBox(titleLabel,s4,titleTextField);
          fifthHBox=new HBox(topicLabel,s5,topicTextField);
          sixthHBox=new HBox(linkLabel,s6,linkTextField);
          seventhHBox=new HBox(criteriaLabel,s7,criteriaTextField);
           s1.setPrefWidth(60);
          s2.setPrefWidth(60);
          s3.setPrefWidth(60);
          s4.setPrefWidth(48);
          s5.setPrefWidth(48);
          s6.setPrefWidth(60);
          s7.setPrefWidth(10);
          
          
          
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
          subButton=new Button("-");
        scheduleItemBox.getChildren().addAll(scheduleItemHeaderLabel,subButton,t1,scheduleItemsTable,t0,addeditLabel,t2,firstHBox,t3,secondHBox,t4,thirdHBox,t5,fourthHBox,t6,fifthHBox,t7,sixthHBox,t8,seventhHBox,t9,addBox);
        scheduleItemBox.setVgap(20);
        t0.setPrefWidth(300);
        t1.setPrefWidth(2000);
          t2.setPrefWidth(2000);
          t3.setPrefWidth(2000);
          t4.setPrefWidth(2000);
          t5.setPrefWidth(2000);
          t6.setPrefWidth(2000);
          t7.setPrefWidth(2000);
          t8.setPrefWidth(2000);
          t9.setPrefWidth(2000);
        
        secondBasePane=new VBox();
        secondBasePane.getChildren().addAll(scheduleHeaderBox,calendarChooseBox,scheduleItemBox);
        basePane=new ScrollPane(secondBasePane);
          secondBasePane.prefWidthProperty().bind(app.getGUI().getWindow().widthProperty().multiply(1));
          secondBasePane.prefHeightProperty().bind(app.getGUI().getWindow().heightProperty().multiply(1));
          timeTextField.prefWidthProperty().bind(app.getGUI().getWindow().widthProperty().multiply(.7));
          titleTextField.prefWidthProperty().bind(app.getGUI().getWindow().widthProperty().multiply(.7));
          topicTextField.prefWidthProperty().bind(app.getGUI().getWindow().widthProperty().multiply(.7));
          linkTextField.prefWidthProperty().bind(app.getGUI().getWindow().widthProperty().multiply(.7));
          criteriaTextField.prefWidthProperty().bind(app.getGUI().getWindow().widthProperty().multiply(.7));
          
          
          
          
          
          
          
          
          controller=new CSGController(app);
          
          
          
          
          
          
          startDatePicker.setOnAction(e->{
              
             
            try {
                controller.handleStartMonday(e,startDatePicker.getValue(),startDatePicker,this,data);
            } catch (ParseException ex) {
                Logger.getLogger(CSGScheduleWorkspace.class.getName()).log(Level.SEVERE, null, ex);
            }
              
          });
          
          
           endDatePicker.setOnAction(e->{
              
              
            try {
                controller.handleEndFriday(e,endDatePicker.getValue(),endDatePicker,this,data);
            } catch (ParseException ex) {
                Logger.getLogger(CSGScheduleWorkspace.class.getName()).log(Level.SEVERE, null, ex);
            }
              
          });
           
           
           
          
          scheduleItemsTable.setFocusTraversable(true);
          scheduleItemsTable.setEditable(true);
          scheduleItemsTable.setOnMouseClicked(e->{
          
            try {
                controller.handleEditScheduleItem();
            } catch (ParseException ex) {
                Logger.getLogger(CSGScheduleWorkspace.class.getName()).log(Level.SEVERE, null, ex);
            }
          
          });
          
          
          scheduleItemsTable.setOnKeyPressed(e->{
               controller.handleScheduleItemKeyPress(e.getCode());
          });
          
          subButton.setOnAction(e->{
                controller.handleScheduleItemRemove();
          
          });
          
          addupdateButton.setOnAction(e->{
             controller.handleScheduleItemUpdate();
          });
          
          clearButton.setOnAction(e->{
              controller.handleScheduleClear();
          });
          
          
                         this.basePane.setOnKeyPressed(e->{
           if(e.isControlDown() && e.getCode()==KeyCode.Z){
  
        controller.handleUndoTransaction();
        //jTPS.undoTransaction();
    }else if(e.isControlDown() && e.getCode()==KeyCode.Y){

        controller.handleDoTransaction();
       // jTPS.doTransaction();
    }

});
          
    }

   
   
   public void reset() throws ParseException{
     //  getStartDatePicker().setValue(LocalDate.now());
       //getEndDatePicker().setValue(LocalDate.now());
       loadCalendar("2017-05-01", "2017-05-19");
       getDatePicker().setValue(LocalDate.now());
       getTypeComboBox().getSelectionModel().clearSelection();
       getTimeTextField().clear();
       getTitleTextField().clear();
       getTopicTextField().clear();
       getLinkTextField().clear();
       getCriteriaTextField().clear();
       
   }
   public void loadCalendar(String initStart,String initEnd) throws ParseException{
       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
       if(!initStart.equals("null")){
     Date startDate=sdf.parse(initStart);
      startDatePicker.setValue(LocalDate.of(startDate.getYear()+1900,startDate.getMonth()+1,startDate.getDate()));
       }
       
       if(!initEnd.equals("null")){
       Date endDate=sdf.parse(initEnd);
       endDatePicker.setValue(LocalDate.of(endDate.getYear()+1900, endDate.getMonth()+1, endDate.getDate()));
       }
   }
   
   
   public void loadCalendarStart(String initStart) throws ParseException{
           SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
       if(!initStart.equals("null")){
     Date startDate=sdf.parse(initStart);
      startDatePicker.setValue(LocalDate.of(startDate.getYear()+1900,startDate.getMonth()+1,startDate.getDate()));
       }
   }
   
   public void loadCalendarEnd(String initEnd) throws ParseException{
       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
       if(!initEnd.equals("null")){
       Date endDate=sdf.parse(initEnd);
       endDatePicker.setValue(LocalDate.of(endDate.getYear()+1900, endDate.getMonth()+1, endDate.getDate()));
       }
   }
   
    public void loadChooseDate(String initStart) throws ParseException{
           SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
     //  if(!initStart.equals("null")){
     Date startDate=sdf.parse(initStart);
      datePicker.setValue(LocalDate.of(startDate.getYear()+1900,startDate.getMonth()+1,startDate.getDate()));
   //    }
   }
   
   
  // public
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

    public FlowPane getScheduleItemBox() {
        return scheduleItemBox;
    }

    public void setScheduleItemBox(FlowPane scheduleItemBox) {
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

    public ComboBox<String> getTypeComboBox() {
        return typeComboBox;
    }

    public void setTypeComboBox(ComboBox<String> typeComboBox) {
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

    public HBox getScheduleHeaderBox() {
        return scheduleHeaderBox;
    }

    public void setScheduleHeaderBox(HBox scheduleHeaderBox) {
        this.scheduleHeaderBox = scheduleHeaderBox;
    }
    
    
}
