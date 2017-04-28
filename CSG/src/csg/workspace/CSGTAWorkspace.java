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
import csg.data.TeachingAssistant;
import csg.style.CSGStyle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import properties_manager.PropertiesManager;

/**
 *
 * @author runnan
 */
public class CSGTAWorkspace implements WorkspacePart{
    CSGApp app;
    CSGController controller;
    ScrollPane basePane;
    
    HBox tasHeaderBox;
    Label tasHeaderLabel;
    
    TableView<TeachingAssistant> taTable;
    TableColumn<TeachingAssistant,Boolean> underGradeColumn;
  //  TableColumn<TeachingAssistant,CheckBox> checkBoxColumn;
    TableColumn<TeachingAssistant,String> nameColumn;
    TableColumn<TeachingAssistant,String> emailColumn;
    
    HBox addBox;
    TextField nameTextField;
    TextField emailTextField;
    Button addButton;
    Button updateButton;
    Button clearButton;
    Button subButton;
    
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
        subButton=new Button("-");
        tasHeaderBox.getChildren().addAll(tasHeaderLabel,subButton);
        
         taTable = new TableView();
        taTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        CSGData data=(CSGData) app.getDataComponent();
        
        ObservableList<TeachingAssistant> tableData=data.getTeachingAssistants();
        taTable.setItems(tableData);
       
        String undergradeColumnText=props.getProperty(CSGAppProp.UNDERGRADE_COLUMN_TEXT.toString());
        String nameColumnText = props.getProperty(CSGAppProp.NAME_COLUMN_TEXT.toString());
        String emailColumnText = props.getProperty(CSGAppProp.EMAIL_COLUMN_TEXT.toString());
        underGradeColumn=new TableColumn(undergradeColumnText);
        nameColumn = new TableColumn(nameColumnText);
        emailColumn=new TableColumn(emailColumnText);
        
       /* underGradeColumn.setCellValueFactory(
              //  new PropertyValueFactory<TeachingAssistant, Boolean>("underGrad")
               new PropertyValueFactory<TeachingAssistant,Boolean>("test")
        );*/
       
       
     
      // ObservableList<TableColumn<TeachingAssistant,?>> columns=taTable.getColumns();
       //TableColumn<TeachingAssistant,Boolean> loadedColumn=new TableColumn<>("LOAD");
      // loadedColumn.setCellValueFactory(f->f.getValue().test);
       //loadedColumn.setCellFactory(qq->new CheckBoxTableCell<>());
       //loadedColumn.editableProperty().getValue();
       //columns.add(loadedColumn);
       
       
     /*  TableColumn select = new TableColumn("CheckBox");
        select.setMinWidth(200);
        select.setCellValueFactory((TableColumn.CellDataFeatures<TeachingAssistant, CheckBox> arg0) -> {
            TeachingAssistant user = arg0.getValue();
            
            CheckBox checkBox = new CheckBox();
            
            checkBox.selectedProperty().setValue(user.isSelected());
            
            
            
            checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                public void changed(ObservableValue<? extends Boolean> ov,
                        Boolean old_val, Boolean new_val) {
                    
                    user.setSelected(new_val);
                    
                }
            });
            
            return new SimpleObjectProperty<CheckBox>(checkBox);
        });
        taTable.getColumns().addAll( select);
       */
     
    // TableColumn select = new TableColumn("CheckBox");
//     select.setCellValueFactory((Object c) -> {
//         TeachingAssistant kk=(TeachingAssistant)c;
//            return new SimpleBooleanProperty(kk.getUnderGrad());
//        });
    taTable.setEditable(true);
      
     underGradeColumn.setCellValueFactory(new PropertyValueFactory<TeachingAssistant,Boolean> ("underGrad"));
     //underGradeColumn.setCellFactory(tc ->new CheckBoxTableCell());
     
//         underGradeColumn.setCellFactory(new Callback<TableColumn<TeachingAssistant, Boolean>, TableCell<TeachingAssistant, Boolean>>() {
//                    @Override
//                    public TableCell<TeachingAssistant, Boolean> call(
//                            TableColumn<TeachingAssistant, Boolean> param) {
//                        CheckBoxTableCell<TeachingAssistant, Boolean> cell = new CheckBoxTableCell<>();
//                        cell.setAlignment(Pos.CENTER);
//                     //   cell.setGraphic(new CheckBox(true));
//                        return cell;
//                    }
//                });
      underGradeColumn.setCellFactory(new CheckBoxTableCell().forTableColumn(underGradeColumn)); 
     underGradeColumn.setEditable(true);
   //  underGradeColumn.setEditable(true);
       
      // taTable.getColumns().add(select);
       
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<TeachingAssistant, String>("name")
        );
        emailColumn.setCellValueFactory(
                new PropertyValueFactory<TeachingAssistant, String>("email")
        );
        
        
    
        
        
        taTable.getColumns().addAll(underGradeColumn,nameColumn,emailColumn);
//        taTable.getColumns().add(nameColumn);
//        taTable.getColumns().add(emailColumn);
        
        
        
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
      // space.setHgrow(space, Priority.ALWAYS);
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
        
        //officeHoursHeaderBox.prefWidthProperty().bind(officeHoursGridPane.widthProperty().multiply(.1));
        
        
     //   officeHoursHeaderBox.prefWidthProperty().bind(app.getGUI().getWindow().widthProperty().multiply(0.1));
         SplitPane sPane = new SplitPane(leftPane, new ScrollPane(rightPane));
         basePane=new ScrollPane(sPane);
         
        // basePane.getChildren().add(sPane);
        
       taTable.prefHeightProperty().bind(basePane.heightProperty().multiply(.9));
        
       
       
       CSGWorkspace oo=(CSGWorkspace)app.getWorkspaceComponent();
        basePane.prefWidthProperty().bind(app.getGUI().getWindow().widthProperty().multiply(1));
        leftPane.prefWidthProperty().bind(app.getGUI().getWindow().widthProperty().multiply(.1));
        leftPane.prefHeightProperty().bind(app.getGUI().getWindow().heightProperty().multiply(.5));
        taTable.prefHeightProperty().bind(app.getGUI().getWindow().widthProperty().multiply(0.4));
        rightPane.prefWidthProperty().bind(app.getGUI().getWindow().widthProperty().multiply(.9));
        sPane.prefHeightProperty().bind(app.getGUI().getWindow().heightProperty().multiply(.9));
       
controller = new CSGController(app);

        // CONTROLS FOR ADDING TAs
        nameTextField.setOnAction(e -> {
            controller.handleAddTA();
            
        });
        emailTextField.setOnAction(e -> {
            controller.handleAddTA();
            
        });
        addButton.setOnAction(e -> {
            controller.handleAddTA();
        });
        
        updateButton.setOnAction(e ->{
        
            controller.handleUpdateTA();
        
        });

        
         clearButton.setOnAction(e ->{
             controller.handleClearField();
        
        
        });
        taTable.setFocusTraversable(true);
   
        taTable.setOnKeyPressed(e -> {
            controller.handleKeyPress(e.getCode());
           
        });
        
        subButton.setOnAction(e->{
           controller.handleSubButtonPress();
        
        });
        
       taTable.setOnMouseClicked(e->{
            controller.handleAppearUpdateTA(this);
        });
   /*   Label t1=new Label("9 am");    
        startTimeComboBox.setValue(t1);
        Label t2=new Label("8 pm");
        endTimeComboBox.setValue(t2);*/
       
      startTimeComboBox.getSelectionModel().select(9);
            endTimeComboBox.getSelectionModel().select(20);
            
         startTimeComboBox.valueProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue ov, String t, String t1) {
                if(t != null && t1 != null){
                    if(startTimeComboBox.getSelectionModel().getSelectedIndex() != data.getStartHour()){
                        System.out.println("1");
                        controller.changeTime();
                    }
                }
            }
        });
      
           endTimeComboBox.valueProperty().addListener(new ChangeListener<String>() {
            
            public void changed(ObservableValue ov, String t, String t1) {
                if(t != null && t1 != null)
                    if(endTimeComboBox.getSelectionModel().getSelectedIndex() != data.getStartHour())
                          System.out.println("2");
                        controller.changeTime();
            }
        });

    
    
   /* startTimeComboBox.setOnAction(e->{
        if (startTimeComboBox.getSelectionModel().getSelectedIndex()!=-1){
       
            
                controller.handleChangeStartTime(startTimeComboBox,startTimeComboBoxLabel);
         
          
        }
        
    });
    
   endTimeComboBox.setOnAction(e->{
        if (endTimeComboBox.getSelectionModel().getSelectedIndex()!=-1){
      
                controller.handleChangeEndTime(endTimeComboBox,endTimeComboBoxLabel);
          
          
        }
        
    });*/
   

    


/*basePane.setOnKeyPressed(e->{
    if(e.isControlDown() && e.getCode()==KeyCode.Z){
  
        controller.handleUndoTransaction();
        //jTPS.undoTransaction();
    }else if(e.isControlDown() && e.getCode()==KeyCode.Y){

        controller.handleDoTransaction();
       // jTPS.doTransaction();
    }

});*/
        
        

        
    

        
        
    }
    
    
    
    public ComboBox getOfficeHour(boolean start){
        if(start){
            return startTimeComboBox;
        }else{
        
        return endTimeComboBox;
        }
    }
      
      
      
      
    public void initTimeComboBox(){
        
       startTimeTableView= FXCollections.observableArrayList();
        endTimeTableView= FXCollections.observableArrayList();
             startTimeComboBox=new ComboBox(startTimeTableView);
        endTimeComboBox=new ComboBox(endTimeTableView);
    
    
         startTimeComboBox.getItems().addAll("12 am");
         
        endTimeComboBox.getItems().addAll("12 am");
        for (int i=1;i<=11;i++){
               for (int j=1;j<=2;j++){
                   String s = Integer.toString(i);
                   if (j==1){            
                       startTimeComboBox.getItems().add(s+ " am");
                       endTimeComboBox.getItems().add(s+" am");
                   } else {
                     //  startTimeComboBox.getItems().add(s+ ":30am");
                       //endTimeComboBox.getItems().add(s+":30am");
                   }     
               }
             }
       // startTimeComboBox.getItems().addAll("12:00pm","12:30pm");
       // endTimeComboBox.getItems().addAll("12:00pm","12:30pm");
             startTimeComboBox.getItems().addAll("12 pm");
        endTimeComboBox.getItems().addAll("12 pm");
         for (int i=1;i<=11;i++){
               for (int j=1;j<=2;j++){
                   String s = Integer.toString(i);
                   if (j==1){            
                       startTimeComboBox.getItems().add(s+ " pm");
                       endTimeComboBox.getItems().add(s+" pm");
                   } else {
                  //     startTimeComboBox.getItems().add(s+ ":30pm");
                 //      endTimeComboBox.getItems().add(s+":30pm");
                   }     
               }
             }
    
}
    
    public ScrollPane getBasePane(){
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

    public HBox getTasHeaderBox() {
        return tasHeaderBox;
    }

    public void setTasHeaderBox(HBox tasHeaderBox) {
        this.tasHeaderBox = tasHeaderBox;
    }

    public Label getTasHeaderLabel() {
        return tasHeaderLabel;
    }

    public void setTasHeaderLabel(Label tasHeaderLabel) {
        this.tasHeaderLabel = tasHeaderLabel;
    }

    public TableView<TeachingAssistant> getTaTable() {
        return taTable;
    }

    public void setTaTable(TableView<TeachingAssistant> taTable) {
        this.taTable = taTable;
    }

    public TableColumn<TeachingAssistant, Boolean> getUnderGradeColumn() {
        return underGradeColumn;
    }

    public void setUnderGradeColumn(TableColumn<TeachingAssistant, Boolean> underGradeColumn) {
        this.underGradeColumn = underGradeColumn;
    }

    public TableColumn<TeachingAssistant, String> getNameColumn() {
        return nameColumn;
    }

    public void setNameColumn(TableColumn<TeachingAssistant, String> nameColumn) {
        this.nameColumn = nameColumn;
    }

    public TableColumn<TeachingAssistant, String> getEmailColumn() {
        return emailColumn;
    }

    public void setEmailColumn(TableColumn<TeachingAssistant, String> emailColumn) {
        this.emailColumn = emailColumn;
    }

    public HBox getAddBox() {
        return addBox;
    }

    public void setAddBox(HBox addBox) {
        this.addBox = addBox;
    }

    public TextField getNameTextField() {
        return nameTextField;
    }

    public void setNameTextField(TextField nameTextField) {
        this.nameTextField = nameTextField;
    }

    public TextField getEmailTextField() {
        return emailTextField;
    }

    public void setEmailTextField(TextField emailTextField) {
        this.emailTextField = emailTextField;
    }

    public Button getAddButton() {
        return addButton;
    }

    public void setAddButton(Button addButton) {
        this.addButton = addButton;
    }

    public Button getUpdateButton() {
        return updateButton;
    }

    public void setUpdateButton(Button updateButton) {
        this.updateButton = updateButton;
    }

    public Button getClearButton() {
        return clearButton;
    }

    public void setClearButton(Button clearButton) {
        this.clearButton = clearButton;
    }

    public HBox getOfficeHoursHeaderBox() {
        return officeHoursHeaderBox;
    }

    public void setOfficeHoursHeaderBox(HBox officeHoursHeaderBox) {
        this.officeHoursHeaderBox = officeHoursHeaderBox;
    }

    public Label getOfficeHoursHeaderLabel() {
        return officeHoursHeaderLabel;
    }

    public void setOfficeHoursHeaderLabel(Label officeHoursHeaderLabel) {
        this.officeHoursHeaderLabel = officeHoursHeaderLabel;
    }

    public GridPane getOfficeHoursGridPane() {
        return officeHoursGridPane;
    }

    public void setOfficeHoursGridPane(GridPane officeHoursGridPane) {
        this.officeHoursGridPane = officeHoursGridPane;
    }

    public HashMap<String, Pane> getOfficeHoursGridTimeHeaderPanes() {
        return officeHoursGridTimeHeaderPanes;
    }

    public void setOfficeHoursGridTimeHeaderPanes(HashMap<String, Pane> officeHoursGridTimeHeaderPanes) {
        this.officeHoursGridTimeHeaderPanes = officeHoursGridTimeHeaderPanes;
    }

    public HashMap<String, Label> getOfficeHoursGridTimeHeaderLabels() {
        return officeHoursGridTimeHeaderLabels;
    }

    public void setOfficeHoursGridTimeHeaderLabels(HashMap<String, Label> officeHoursGridTimeHeaderLabels) {
        this.officeHoursGridTimeHeaderLabels = officeHoursGridTimeHeaderLabels;
    }

    public HashMap<String, Pane> getOfficeHoursGridDayHeaderPanes() {
        return officeHoursGridDayHeaderPanes;
    }

    public void setOfficeHoursGridDayHeaderPanes(HashMap<String, Pane> officeHoursGridDayHeaderPanes) {
        this.officeHoursGridDayHeaderPanes = officeHoursGridDayHeaderPanes;
    }

    public HashMap<String, Label> getOfficeHoursGridDayHeaderLabels() {
        return officeHoursGridDayHeaderLabels;
    }

    public void setOfficeHoursGridDayHeaderLabels(HashMap<String, Label> officeHoursGridDayHeaderLabels) {
        this.officeHoursGridDayHeaderLabels = officeHoursGridDayHeaderLabels;
    }

    public HashMap<String, Pane> getOfficeHoursGridTimeCellPanes() {
        return officeHoursGridTimeCellPanes;
    }

    public void setOfficeHoursGridTimeCellPanes(HashMap<String, Pane> officeHoursGridTimeCellPanes) {
        this.officeHoursGridTimeCellPanes = officeHoursGridTimeCellPanes;
    }

    public HashMap<String, Label> getOfficeHoursGridTimeCellLabels() {
        return officeHoursGridTimeCellLabels;
    }

    public void setOfficeHoursGridTimeCellLabels(HashMap<String, Label> officeHoursGridTimeCellLabels) {
        this.officeHoursGridTimeCellLabels = officeHoursGridTimeCellLabels;
    }

    public HashMap<String, Pane> getOfficeHoursGridTACellPanes() {
        return officeHoursGridTACellPanes;
    }

    public void setOfficeHoursGridTACellPanes(HashMap<String, Pane> officeHoursGridTACellPanes) {
        this.officeHoursGridTACellPanes = officeHoursGridTACellPanes;
    }

    public HashMap<String, Label> getOfficeHoursGridTACellLabels() {
        return officeHoursGridTACellLabels;
    }

    public void setOfficeHoursGridTACellLabels(HashMap<String, Label> officeHoursGridTACellLabels) {
        this.officeHoursGridTACellLabels = officeHoursGridTACellLabels;
    }

    public ComboBox getStartTimeComboBox() {
        return startTimeComboBox;
    }

    public void setStartTimeComboBox(ComboBox startTimeComboBox) {
        this.startTimeComboBox = startTimeComboBox;
    }

    public Label getStartTimeComboBoxLabel() {
        return startTimeComboBoxLabel;
    }

    public void setStartTimeComboBoxLabel(Label startTimeComboBoxLabel) {
        this.startTimeComboBoxLabel = startTimeComboBoxLabel;
    }

    public ComboBox getEndTimeComboBox() {
        return endTimeComboBox;
    }

    public void setEndTimeComboBox(ComboBox endTimeComboBox) {
        this.endTimeComboBox = endTimeComboBox;
    }

    public Label getEndTimeComboBoxLabel() {
        return endTimeComboBoxLabel;
    }

    public void setEndTimeComboBoxLabel(Label endTimeComboBoxLabel) {
        this.endTimeComboBoxLabel = endTimeComboBoxLabel;
    }

    public ObservableList<String> getStartTimeTableView() {
        return startTimeTableView;
    }

    public void setStartTimeTableView(ObservableList<String> startTimeTableView) {
        this.startTimeTableView = startTimeTableView;
    }

    public ObservableList<String> getEndTimeTableView() {
        return endTimeTableView;
    }

    public void setEndTimeTableView(ObservableList<String> endTimeTableView) {
        this.endTimeTableView = endTimeTableView;
    }

    public Label getStartTimeLabel() {
        return startTimeLabel;
    }

    public void setStartTimeLabel(Label startTimeLabel) {
        this.startTimeLabel = startTimeLabel;
    }

    public Label getEndTimeLabel() {
        return endTimeLabel;
    }

    public void setEndTimeLabel(Label endTimeLabel) {
        this.endTimeLabel = endTimeLabel;
    }
    
    
    public void reloadWorkspace(CSGData data){
        reloadOfficeHoursGrid(data);
    }
    
      public Pane getTACellPane(String cellPane) {
        return officeHoursGridTACellPanes.get(cellPane);
    }

    public void reloadOfficeHoursGrid(CSGData dataComponent) {
       ArrayList<String> gridHeaders =dataComponent.getGridHeaders();
       
       for (int i=0;i<2;i++){
            addCellToGrid(dataComponent, officeHoursGridTimeHeaderPanes, officeHoursGridTimeHeaderLabels, i, 0);
            dataComponent.getCellTextProperty(i, 0).set(gridHeaders.get(i));
       }
       
          for (int i = 2; i < 7; i++) {
            addCellToGrid(dataComponent, officeHoursGridDayHeaderPanes, officeHoursGridDayHeaderLabels, i, 0);
            dataComponent.getCellTextProperty(i, 0).set(gridHeaders.get(i));            
        }
       
            int row = 1;
                 int colhelp=0;
           for (int i = dataComponent.getStartHour(); i < dataComponent.getEndHour(); i++) {
            // START TIME COLUMN
            int col = 0;
            addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row);
            dataComponent.getCellTextProperty(col, row).set(buildCellText(i, "00"));
            addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row+1);
            dataComponent.getCellTextProperty(col, row+1).set(buildCellText(i, "30"));

            // END TIME COLUMN
            col++;
            int endHour = i;
            addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row);
            dataComponent.getCellTextProperty(col, row).set(buildCellText(endHour, "30"));
            addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row+1);
            dataComponent.getCellTextProperty(col, row+1).set(buildCellText(endHour+1, "00"));
            col++;

            // AND NOW ALL THE TA TOGGLE CELLS
            while (col < 7) {
                addCellToGrid(dataComponent, officeHoursGridTACellPanes, officeHoursGridTACellLabels, col, row);
                addCellToGrid(dataComponent, officeHoursGridTACellPanes, officeHoursGridTACellLabels, col, row+1);
                col++;
            }
           
            row += 2;
        }
           
           
                for (Pane p : officeHoursGridTACellPanes.values()) {
            p.setFocusTraversable(true);
            p.setOnKeyPressed(e -> {
                controller.handleKeyPress(e.getCode());
            });
            p.setOnMouseClicked(e -> {
                controller.handleCellToggle((Pane) e.getSource());
            });
            p.setOnMouseExited(e -> {
                controller.handleGridCellMouseExited((Pane) e.getSource());
            });
            p.setOnMouseEntered(e -> {
                controller.handleGridCellMouseEntered((Pane) e.getSource());
            });
        }
                
             
          CSGStyle taStyle = (CSGStyle)app.getStyleComponent();
        taStyle.initOfficeHoursGridStyle();  
                
                
          
           
           
           
       
    }
    
    
    
      public void addCellToGrid(CSGData dataComponent, HashMap<String, Pane> panes, HashMap<String, Label> labels, int col, int row) {       
        // MAKE THE LABEL IN A PANE
        Label cellLabel = new Label("");
        HBox cellPane = new HBox();
        cellPane.setAlignment(Pos.CENTER);
        cellPane.getChildren().add(cellLabel);

        // BUILD A KEY TO EASILY UNIQUELY IDENTIFY THE CELL
        String cellKey = dataComponent.getCellKey(col, row);
        cellPane.setId(cellKey);
        cellLabel.setId(cellKey);
        
        // NOW PUT THE CELL IN THE WORKSPACE GRID
        officeHoursGridPane.add(cellPane, col, row);
        
        // AND ALSO KEEP IN IN CASE WE NEED TO STYLIZE IT
        panes.put(cellKey, cellPane);
        labels.put(cellKey, cellLabel);
        
        // AND FINALLY, GIVE THE TEXT PROPERTY TO THE DATA MANAGER
        // SO IT CAN MANAGE ALL CHANGES
        dataComponent.setCellProperty(col, row, cellLabel.textProperty());        
    }
    
     public void removeCellFromGrid(CSGData dataComponent, HashMap<String, Pane> panes, HashMap<String, Label> labels,int row) {     
         for(int i=1;i<=13;i+=2){
          
       // officeHoursGridPane.getChildren().remove(100);
 
       officeHoursGridPane.getChildren().remove(row*7-i);
  
      //panes.remove(dataComponent.getCellKey(i, row));
         }
        }
     
     public String buildCellKey(int col,int row){
         return ""+col+"_"+row;
     }
        public String buildCellText(int militaryHour, String minutes) {
        // FIRST THE START AND END CELLS
        int hour = militaryHour;
        if (hour > 12) {
            hour -= 12;
        }
        String cellText = "" + hour + ":" + minutes;
        if (militaryHour < 12) {
            cellText += "am";
        } else {
            cellText += "pm";
        }
        return cellText;
    }
    
}