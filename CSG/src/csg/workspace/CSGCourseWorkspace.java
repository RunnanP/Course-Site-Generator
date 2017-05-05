/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import csg.CSGApp;
import csg.CSGAppProp;
import csg.data.CSGData;
import csg.data.SitePage;
import csg.data.TeachingAssistant;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import properties_manager.PropertiesManager;

/**
 *
 * @author runnan
 */
public class CSGCourseWorkspace implements WorkspacePart{
    CSGApp app;
    CSGController controller;
    ScrollPane basePane;
    
    BorderPane secondBasePane;
    //VBox topPane;
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
          String exporDirDisplayAddressString;
          Button exportDirChangeButton;
          
      
    //center pane
    Label centralPaneHeaderLabel;
    Text descriptionText;
    Label templatesDirLabel;
    Button selectTemplateDirButton;
    Label sitePagesLabel;
    FlowPane sitePageTablePane;
    TableView<SitePage>  sitePagesTable;
    TableColumn<SitePage,Boolean> useColumn;
    TableColumn<SitePage,String> navBarTitleColumn;
    TableColumn<SitePage,String> fileNameColumn;
    TableColumn<SitePage, String> scriptColumn;
    
    //bottom pane
    Label buttomPaneHeaderLabel;
    Label bannerSchoolImageLabel;
    Text firstText;
    Button firstChangeButton;
    Label leftFooterImageLabel;
    Text secondText;
    Button secondChangeButton;
    Label rightFooterImageLabel;
    Text thirdText;
    Button thirdChangeButton;
    
    ImageView firstImageView;
    ImageView secondImageView;
    ImageView thirdImageView;
    String firstImageLocation;
    String secondImageLocation;
    String thirdImageLocation;
    
    
    Label styleSheetLabel;
    ComboBox<String> styleSheetComboBox;
    Text noteDescription;
    HBox pageStyleFirstHBox;
    HBox pageStyleSecondHBox;
    HBox pageStyleThirdHBox;
    HBox pageStyleFourthHBox;
    
   
    
    
    public CSGCourseWorkspace(CSGApp initapp){
        
         app=initapp;
         CSGWorkspace workspace=(CSGWorkspace)app.getWorkspaceComponent();
         initTopPane();
         initCenterPane();
         initBottomPane();
         secondBasePane=new BorderPane();
         secondBasePane.setTop(topPane);
         topPane.prefWidthProperty().bind(app.getGUI().getWindow().widthProperty().multiply(1));
         
         secondBasePane.setCenter(centerPane);
         secondBasePane.setBottom(bottomPane);
         secondBasePane.setMinSize(100, 100);
         //secondBasePane.se
         basePane=new ScrollPane(secondBasePane);
         //basePane.getChildren().add(secondBasePane);
         
         
         controller=new CSGController(app);
         exportDirChangeButton.setOnAction(e->{
             try {
                 controller.handleChangeExportDir();
             } catch (IOException ex) {
                 Logger.getLogger(CSGCourseWorkspace.class.getName()).log(Level.SEVERE, null, ex);
             }
         });
         
         selectTemplateDirButton.setOnAction(e->{
               controller.handleSelectTempleDir();
         
         });
         
         firstChangeButton.setOnAction(e->{
              controller.handleChangeFirstImage();
         });
         
         secondChangeButton.setOnAction(e->{
              controller.handleChangeSecondImage();
         });
         
         thirdChangeButton.setOnAction(e->{
               controller.handleChangeThirdImage();
         
         
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

   private void initTopPane(){
       PropertiesManager props = PropertiesManager.getPropertiesManager();
       
       String topPaneHeaderLabelText = props.getProperty(CSGAppProp.COURSE_WORKSPACE_PART_TOP_PANE_HEADER_TEXT.toString());
       topPaneHeaderLabel=new Label(topPaneHeaderLabelText);
       
       //first hbox
       String subjectLabelText = props.getProperty(CSGAppProp.COURSE_WORKSPACE_PART_TOP_PANE_SUBJECT_TEXT.toString());
       String numberLabelText = props.getProperty(CSGAppProp.COURSE_WORKSPACE_PART_TOP_PANE_NUMBER_TEXT.toString());
         subjectLabel=new Label(subjectLabelText);
         subjectComboBox=new ComboBox<String>();
         subjectComboBox.setPrefWidth(250);
                     for(int i=(int)'A';i<'A'+26;i++){
                          for(int j=(int)'A';j<'A'+26;j++){
                               for(int k=(int)'A';k<'A'+26;k++){
                                   subjectComboBox.getItems().add(""+(char)i+(char)j+(char)k);
                               }
                          }
                     }   
         
              
               
         numberLabel=new Label(numberLabelText);
         numberComboBox=new ComboBox<Integer>();
         numberComboBox.setPrefWidth(250);
                 for (int i=100;i<=500;i++){
                     numberComboBox.getItems().add(i);
                 }
         firstHBox=new HBox();
         HBox aspace=new HBox();
         aspace.setMinWidth(50);
          HBox bspace=new HBox();
         bspace.setMinWidth(50);
          HBox cspace=new HBox();
         cspace.setMinWidth(50);
          HBox dspace=new HBox();
         dspace.setMinWidth(50);
         
         firstHBox.getChildren().addAll(subjectLabel,aspace,subjectComboBox,bspace,numberLabel,cspace,numberComboBox);
         
         //second hbox
        String semesterLabelText = props.getProperty(CSGAppProp.COURSE_WORKSPACE_PART_TOP_PANE_SEMESTER_TEXT.toString());
       String yearLabelText = props.getProperty(CSGAppProp.COURSE_WORKSPACE_PART_TOP_PANE_YEAR_TEXT.toString());
         semesterLabel=new Label(semesterLabelText);
         semesterComboBox=new ComboBox<String>();
         semesterComboBox.setPrefWidth(250);
               semesterComboBox.getItems().add("Spring");
               semesterComboBox.getItems().add("Summer");
               semesterComboBox.getItems().add("Fall");
               semesterComboBox.getItems().add("Winter");
         yearLabel=new Label(yearLabelText);
         yearComboBox=new ComboBox<Integer>();
         yearComboBox.setPrefWidth(250);
                for (int i=2000;i<=2030;i++){
                     yearComboBox.getItems().add(i);
                 }
         
         secondHBox=new HBox();
            aspace=new HBox();
         aspace.setMinWidth(48);
          bspace=new HBox();
         bspace.setMinWidth(50);
           cspace=new HBox();
         cspace.setMinWidth(56);
          dspace=new HBox();
         dspace.setMinWidth(50);
         secondHBox.getChildren().addAll(semesterLabel,aspace,semesterComboBox,bspace,yearLabel,cspace,yearComboBox);
         //third hbox
//         String titleLabelText = props.getProperty(CSGAppProp.COURSE_WORKSPACE_PART_TOP_PANE_TITLE_TEXT.toString());
//      
//         titleLabel=new Label(titleLabelText);
//         titleTextField=new TextField();
//         
//         thirdHBox=new HBox();
//         thirdHBox.getChildren().addAll(titleLabel,titleTextField);
//         
         //fourth hbox
//       String instructorNameLabelText = props.getProperty(CSGAppProp.COURSE_WORKSPACE_PART_TOP_PANE_INCTRUCTOR_NAME_TEXT.toString());
//       
//         instructorNameLabel=new Label(instructorNameLabelText);
//         instructorNameTextField=new TextField();
//         
//         fourthHBox=new HBox();
//         fourthHBox.getChildren().addAll(instructorNameLabel,instructorNameTextField);
         //fifth hBOX
//          String instructorHomeLabelText = props.getProperty(CSGAppProp.COURSE_WORKSPACE_PART_TOP_PANE_INCTRUCTOR_HOME_TEXT.toString());
//       
//         instructorHomeLabel=new Label(instructorHomeLabelText);
//         instructorHomeTextField=new TextField();
//         
//         fifthHBox=new HBox();
//         fifthHBox.getChildren().addAll(instructorHomeLabel,instructorHomeTextField);
         //sixth
         
         
//          String exportDirLabelText = props.getProperty(CSGAppProp.COURSE_WORKSPACE_PART_TOP_PANE_EXPORT_DIR_TEXT.toString());
//          String exportDirButtorLabelText = props.getProperty(CSGAppProp.CHANGE_TEXT.toString());
//       
//         exportDirLabel=new Label(exportDirLabelText);
//         
//         exportDirChangeButton=new Button(exportDirButtorLabelText);
//         
//         sixthHBox=new HBox();
//         exporDirDisplayAddressLabel=new Label();
//         exporDirDisplayAddressLabel.setPrefWidth(200);
//         sixthHBox.getChildren().addAll(exportDirLabel,exporDirDisplayAddressLabel,exportDirChangeButton);
         




         //topPane=new VBox();
         topPane=new VBox();
//         topPane.setHgap(10);
//         topPane.setVgap(5);
         topPane.setPadding(new Insets(11,12,13,14));
//         TilePane temp=new TilePane();
//         temp.setPrefColumns(10);
//         temp.setAlignment(Pos.TOP_LEFT);
      FlowPane temp=new FlowPane();
         TilePane temp1=new TilePane();
         
         temp1.getChildren().addAll(subjectLabel,subjectComboBox,numberLabel,numberComboBox);
         
         TilePane temp2=new TilePane();
         temp2.getChildren().addAll(semesterLabel,semesterComboBox,yearLabel,yearComboBox);
        // ,titleLabel,titleTextField,instructorNameLabel,instructorNameTextField,instructorHomeLabel,instructorHomeTextField);
        
       FlowPane temp3=new FlowPane();
       HBox t0=new HBox();
       HBox t1=new HBox();
       HBox t2=new HBox();
       HBox t3=new HBox();
       HBox t4=new HBox();
       HBox t5=new HBox();
       
       ////////3333333333333333333333333333333333333333333333333333333
         String titleLabelText = props.getProperty(CSGAppProp.COURSE_WORKSPACE_PART_TOP_PANE_TITLE_TEXT.toString());
      
         titleLabel=new Label(titleLabelText);
         titleTextField=new TextField();
        temp3.getChildren().addAll(t0,titleLabel,t1,titleTextField,t2);
        t0.setPrefWidth(60);
        t1.setPrefWidth(150);
        t2.setPrefWidth(2000);
        temp3.setAlignment(Pos.TOP_LEFT);
        //444444444444444444444444444444444444444444444
        FlowPane temp4=new FlowPane();
         t0=new HBox();
        t1=new HBox();
        t2=new HBox();
               String instructorNameLabelText = props.getProperty(CSGAppProp.COURSE_WORKSPACE_PART_TOP_PANE_INCTRUCTOR_NAME_TEXT.toString());
       
         instructorNameLabel=new Label(instructorNameLabelText);
         instructorNameTextField=new TextField();
        temp4.getChildren().addAll(t0,instructorNameLabel,t1,instructorNameTextField,t2);
         t0.setPrefWidth(60);
        t1.setPrefWidth(23);
        t2.setPrefWidth(2000);
         temp4.setAlignment(Pos.BASELINE_LEFT);
         //555555555555555555555555555555555555555555555555555
        FlowPane temp5=new FlowPane();
         t0=new HBox();
        t1=new HBox();
        t2=new HBox();
        
        
           String instructorHomeLabelText = props.getProperty(CSGAppProp.COURSE_WORKSPACE_PART_TOP_PANE_INCTRUCTOR_HOME_TEXT.toString());
       
         instructorHomeLabel=new Label(instructorHomeLabelText);
         instructorHomeTextField=new TextField();
        
        temp5.getChildren().addAll(t0,instructorHomeLabel,t1,instructorHomeTextField,t2);
          t0.setPrefWidth(60);
        t1.setPrefWidth(36);
        t2.setPrefWidth(2000); 
        temp5.setAlignment(Pos.BASELINE_LEFT);
       // temp3.setPrefColumns(2);
       // temp3.setVgap(20);
        //temp3.setAlignment(Pos.CENTER);
        //6666666666666666666666666666666666666666666666666666666666666666666666666
        FlowPane temp6=new FlowPane();
        
        temp6.setPrefWidth(1000);
        
            String exportDirLabelText = props.getProperty(CSGAppProp.COURSE_WORKSPACE_PART_TOP_PANE_EXPORT_DIR_TEXT.toString());
          String exportDirButtorLabelText = props.getProperty(CSGAppProp.CHANGE_TEXT.toString());
       
         exportDirLabel=new Label(exportDirLabelText);
         
         exportDirChangeButton=new Button(exportDirButtorLabelText);
         
         
        // exporDirDisplayAddressString=new String();
           exporDirDisplayAddressLabel=new Label();
           
           
         //exporDirDisplayAddressLabel.setPrefWidth(200);
         HBox Q1=new HBox(exporDirDisplayAddressLabel);
      
         Q1.setPrefWidth(600);
         HBox Q0=new HBox();
         Q0.setPrefWidth(60);
         HBox spaceTest=new HBox();
         spaceTest.setPrefWidth(20);
        temp6.getChildren().setAll(Q0,exportDirLabel,spaceTest,Q1,exportDirChangeButton);
      //  temp6.setAlignment(Pos.);
        temp.getChildren().addAll(temp1,temp2,temp3,temp4,temp5,temp6);
        
       temp.setHgap(20);
         temp.setVgap(20);
         //temp.setPrefColumns(2);
         topPane.getChildren().addAll(topPaneHeaderLabel,temp);
         
         //topPane.setPadding(Insets.EMPTY);
         //topPane.getChildren().addAll( topPaneHeaderLabel,new HBox(),firstHBox,secondHBox,thirdHBox,fourthHBox,fifthHBox,sixthHBox);
   }
   
   private void initCenterPane(){
       PropertiesManager props = PropertiesManager.getPropertiesManager();
       centerPane=new VBox();
       String centerPaneHeaderLabelText = props.getProperty(CSGAppProp.COURSE_WORKSPACE_PART_CENTER_PANE_HEADER_TEXT.toString());
       centralPaneHeaderLabel=new Label(centerPaneHeaderLabelText);
       String descriptionDetail = props.getProperty(CSGAppProp.DESCRIPTION_DETAIL_TEXT.toString());
       descriptionText=new Text(descriptionDetail);
       templatesDirLabel=new Label();
       templatesDirLabel.setPrefHeight(50);
       String siteTemplateButtonText = props.getProperty(CSGAppProp.TEMPLATE_BUTTON_TEXT.toString());
       selectTemplateDirButton=new Button(siteTemplateButtonText);
       String sitePageText = props.getProperty(CSGAppProp.SITE_PAGES_TEXT.toString());
       sitePagesLabel=new Label(sitePageText);
       
        String useColumnText = props.getProperty(CSGAppProp.USE_COLIMN_TEXT.toString());
        String navBarColumnText=props.getProperty(CSGAppProp.NAVBAR_TITLE_COLUMN_TEXT.toString());
        String fileNameColumnText=props.getProperty(CSGAppProp.FILE_NAME_COLUMN_TEXT.toString());
        String scriptColumnText=props.getProperty(CSGAppProp.SCRIPT_COLUMN_TEXT.toString());
        
        useColumn=new TableColumn(useColumnText);
        navBarTitleColumn=new TableColumn(navBarColumnText);
        fileNameColumn=new TableColumn(fileNameColumnText);
        scriptColumn=new TableColumn(scriptColumnText);
        
        sitePagesTable=new TableView<>();
        sitePagesTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        CSGData data=(CSGData)app.getDataComponent();
        ObservableList<SitePage> sitePagesData=data.getSitePages();
        sitePagesTable.setItems(sitePagesData);
        
       
        useColumn.setCellValueFactory(new PropertyValueFactory<SitePage,Boolean>("used"));
        useColumn.setCellFactory(new CheckBoxTableCell().forTableColumn(useColumn));
        
        
        navBarTitleColumn.setCellValueFactory(new PropertyValueFactory<SitePage,String>("navbar"));
        fileNameColumn.setCellValueFactory(new PropertyValueFactory<SitePage,String>("filename"));
        scriptColumn.setCellValueFactory(new PropertyValueFactory<SitePage,String>("scriptname"));
        
         sitePagesTable.setEditable(true);
        useColumn.setEditable(true);
        
        sitePagesTable.getColumns().addAll(useColumn,navBarTitleColumn,fileNameColumn,scriptColumn);
        
      //  HBox space=new HBox(sitePagesTable,new Pane());
        sitePagesTable.prefWidthProperty().bind(app.getGUI().getWindow().widthProperty().multiply(0.3));
        sitePageTablePane=new FlowPane(sitePagesTable);
        HBox Q3=new HBox();
        Q3.setPrefHeight(30);
        centerPane.getChildren().addAll(centralPaneHeaderLabel,descriptionText,templatesDirLabel,selectTemplateDirButton,Q3,sitePagesLabel,sitePageTablePane);
        titleTextField.prefWidthProperty().bind(app.getGUI().getWindow().widthProperty().multiply(.2));
        instructorNameTextField.prefWidthProperty().bind(app.getGUI().getWindow().widthProperty().multiply(.2));
        instructorHomeTextField.prefWidthProperty().bind(app.getGUI().getWindow().widthProperty().multiply(.2));
        centerPane.setPadding(new Insets(11,12,13,14));
        
   }
   
   private void initBottomPane(){
       PropertiesManager props = PropertiesManager.getPropertiesManager();
       bottomPane=new VBox();
       
       String buttomPaneHeaderLabelText=props.getProperty(CSGAppProp.BUTTOM_PANE_HEADER_LABEL_TEXT.toString());
       
       String bannerSchoolImageLabelText=props.getProperty(CSGAppProp.BANNER_LABEL_TEXT.toString());
       String leftFooterImageLabelText=props.getProperty(CSGAppProp.LEFT_LABEL_TEXT.toString());
       String rightFooterImageLabelText=props.getProperty(CSGAppProp.RIGHT_LABEL_TEXT.toString());
       String styleSheetLabelText=props.getProperty(CSGAppProp.STYLESHEET_LABEL_TEXT.toString());
       String changeText=props.getProperty(CSGAppProp.CHANGE_TEXT.toString());
       
          buttomPaneHeaderLabel=new Label(buttomPaneHeaderLabelText);
          bottomPane.getChildren().add(buttomPaneHeaderLabel);
          
         bannerSchoolImageLabel=new Label(bannerSchoolImageLabelText);
         
         firstImageLocation=new String("file:.\\work\\brands\\1.jpg");
         firstImageView=new ImageView(firstImageLocation);
         
         firstText=new Text("YALE UNIVERSITY");
         firstChangeButton=new Button(changeText);
         HBox t1=new HBox();
         HBox t2=new HBox();
         pageStyleFirstHBox=new HBox(bannerSchoolImageLabel,t1,firstImageView,t2,firstChangeButton);
         t1.setPrefWidth(20);
         t2.setPrefWidth(20);
         HBox s1=new HBox();
        s1.setPrefHeight(20);
         bottomPane.getChildren().addAll(pageStyleFirstHBox,s1);
         
         leftFooterImageLabel=new Label(leftFooterImageLabelText);
         
         secondImageLocation=new String("file:.\\work\\brands\\2.png");
         secondImageView=new ImageView(secondImageLocation);
         
         secondText=new Text("YALE UNIVERSITY");
         secondChangeButton=new Button(changeText);
         t1=new HBox();
         t2=new HBox();
         pageStyleSecondHBox=new HBox(leftFooterImageLabel,t1,secondImageView,t2,secondChangeButton);
          t1.setPrefWidth(40);
         t2.setPrefWidth(20);
         s1=new HBox();
         s1.setPrefHeight(20);
         bottomPane.getChildren().addAll(pageStyleSecondHBox,s1);
         
         rightFooterImageLabel=new Label(rightFooterImageLabelText);
         
          thirdImageLocation=new String("file:.\\work\\brands\\3.png");
         thirdImageView=new ImageView(thirdImageLocation);
         thirdText=new Text("YALE CS");
         thirdChangeButton=new Button(changeText);
         t1=new HBox();
         t2=new HBox();
         pageStyleThirdHBox=new HBox(rightFooterImageLabel,t1,thirdImageView,t2,thirdChangeButton);
         
           t1.setPrefWidth(40);
         t2.setPrefWidth(20);
          s1=new HBox();
         s1.setPrefHeight(20);
         bottomPane.getChildren().addAll(pageStyleThirdHBox,s1);
         
    
         styleSheetLabel=new Label(styleSheetLabelText);
         
        styleSheetComboBox=new ComboBox<>();
        t1=new HBox();
        fourthHBox=new HBox(styleSheetLabel,t1,styleSheetComboBox);
        t1.setPrefWidth(120);
        
        
        
        
        styleSheetComboBox.setPrefWidth(400);
          ArrayList<String> csslist=new ArrayList<>(); 
                
                File f6=new File(".\\work\\css\\");
                File flist[]=f6.listFiles();
                for (File f:flist){
                    if (!f.isDirectory()){
                        if(f.getName().endsWith(".css")){
                           // workspace.getStyleSheetComboBox().getItems().add(f.getName());
                           csslist.add(f.getName());
                            System.out.println(f.getName());
                        }
                    }
                    
                }
                
                getStyleSheetComboBox().getItems().clear();
                getStyleSheetComboBox().getItems().addAll(csslist);
        
                
                
                
                
        String noteDescriptionText=props.getProperty(CSGAppProp.NOTE_DESCRIPTION_TEXT.toString());
        noteDescription=new Text(noteDescriptionText);
        
        bottomPane.getChildren().addAll(fourthHBox,noteDescription);
        bottomPane.setPadding(new Insets(11,12,13,14));
        
   }
   
   public void reset(){
       getSubjectComboBox().setValue("");
       getNumberComboBox().setValue(null);
       getSemesterComboBox().setValue("");
       getYearComboBox().setValue(null);
       getTitleTextField().setText("");
       getInstructorNameTextField().setText("");
       getInstructorHomeTextField().setText("");
       
       
       setExporDirDisplayAddressLabel("");
       setTemplatesDirLabel("");
       getStyleSheetComboBox().setValue("");
       
       setFirstImageView(new Image("file:.\\work\\brands\\1.jpg"));
       setFirstImageLocation("file:.\\work\\brands\\1.jpg");
       setSecondImageView(new Image("file:.\\work\\brands\\2.png"));
       setSecondImageLocation("file:.\\work\\brands\\2.png");
       setThirdImageView(new Image("file:.\\work\\brands\\3.png"));
       setThirdImageLocation("file:.\\work\\brands\\3.png");
       
   }
   
   public void loadCourseInfo(String initsubject,int initnumber,String initsemester,int inityear,String inittitle,String initinstructorName,String initinstructorHome){
       
       subjectComboBox.setValue(initsubject);
         
          numberComboBox.setValue(initnumber);
        
        
          semesterComboBox.setValue(initsemester);
          
          yearComboBox.setValue(inityear);
         
        
           titleTextField.setText(inittitle);
         
         
          instructorNameTextField.setText(initinstructorName);
      
       instructorHomeTextField.setText(initinstructorHome);
       
      // styleSheetComboBox.setValue(initstyleSheet);
       
       
   }
    public ScrollPane getBasePane() {
        return basePane;
    }
    
    public void renewPane(){
        
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

    public BorderPane getSecondBasePane() {
        return secondBasePane;
    }

    public void setSecondBasePane(BorderPane secondBasePane) {
        this.secondBasePane = secondBasePane;
    }

    public VBox getTopPane() {
        return topPane;
    }

    public void setTopPane(VBox topPane) {
        this.topPane = topPane;
    }

    public VBox getCenterPane() {
        return centerPane;
    }

    public void setCenterPane(VBox centerPane) {
        this.centerPane = centerPane;
    }

    public VBox getBottomPane() {
        return bottomPane;
    }

    public void setBottomPane(VBox bottomPane) {
        this.bottomPane = bottomPane;
    }

    public Label getTopPaneHeaderLabel() {
        return topPaneHeaderLabel;
    }

    public void setTopPaneHeaderLabel(Label topPaneHeaderLabel) {
        this.topPaneHeaderLabel = topPaneHeaderLabel;
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

    public Label getSubjectLabel() {
        return subjectLabel;
    }

    public void setSubjectLabel(Label subjectLabel) {
        this.subjectLabel = subjectLabel;
    }

    public ComboBox<String> getSubjectComboBox() {
        return subjectComboBox;
    }

    public void setSubjectComboBox(ComboBox<String> subjectComboBox) {
        this.subjectComboBox = subjectComboBox;
    }

    public Label getNumberLabel() {
        return numberLabel;
    }

    public void setNumberLabel(Label numberLabel) {
        this.numberLabel = numberLabel;
    }

    public ComboBox<Integer> getNumberComboBox() {
        return numberComboBox;
    }

    public void setNumberComboBox(ComboBox<Integer> numberComboBox) {
        this.numberComboBox = numberComboBox;
    }

    public Label getSemesterLabel() {
        return semesterLabel;
    }

    public void setSemesterLabel(Label semesterLabel) {
        this.semesterLabel = semesterLabel;
    }

    public ComboBox<String> getSemesterComboBox() {
        return semesterComboBox;
    }

    public void setSemesterComboBox(ComboBox<String> semesterComboBox) {
        this.semesterComboBox = semesterComboBox;
    }

    public Label getYearLabel() {
        return yearLabel;
    }

    public void setYearLabel(Label yearLabel) {
        this.yearLabel = yearLabel;
    }

    public ComboBox<Integer> getYearComboBox() {
        return yearComboBox;
    }

    public void setYearComboBox(ComboBox<Integer> yearComboBox) {
        this.yearComboBox = yearComboBox;
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

    public Label getInstructorNameLabel() {
        return instructorNameLabel;
    }

    public void setInstructorNameLabel(Label instructorNameLabel) {
        this.instructorNameLabel = instructorNameLabel;
    }

    public TextField getInstructorNameTextField() {
        return instructorNameTextField;
    }

    public void setInstructorNameTextField(TextField instructorNameTextField) {
        this.instructorNameTextField = instructorNameTextField;
    }

    public Label getInstructorHomeLabel() {
        return instructorHomeLabel;
    }

    public void setInstructorHomeLabel(Label instructorHomeLabel) {
        this.instructorHomeLabel = instructorHomeLabel;
    }

    public TextField getInstructorHomeTextField() {
        return instructorHomeTextField;
    }

    public void setInstructorHomeTextField(TextField instructorHomeTextField) {
        this.instructorHomeTextField = instructorHomeTextField;
    }

    public Label getExportDirLabel() {
        return exportDirLabel;
    }

    public void setExportDirLabel(Label initexportDirLabel) {
        exportDirLabel = initexportDirLabel;
    }

    public Label getExporDirDisplayAddressLabel() {
        return exporDirDisplayAddressLabel;
    }

    public void setExporDirDisplayAddressLabel(String init) {
        exporDirDisplayAddressLabel.setText(init);
    }

    public Button getExportDirChangeButton() {
        return exportDirChangeButton;
    }

    public void setExportDirChangeButton(Button exportDirChangeButton) {
        this.exportDirChangeButton = exportDirChangeButton;
    }

    public Label getCentralPaneHeaderLabel() {
        return centralPaneHeaderLabel;
    }

    public void setCentralPaneHeaderLabel(Label centralPaneHeaderLabel) {
        this.centralPaneHeaderLabel = centralPaneHeaderLabel;
    }

    public Text getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(Text descriptionText) {
        this.descriptionText = descriptionText;
    }

    public Label getTemplatesDirLabel() {
        return templatesDirLabel;
    }

    public void setTemplatesDirLabel(String init) {
        templatesDirLabel.setText(init);
    }

    public Button getSelectTemplateDirButton() {
        return selectTemplateDirButton;
    }

    public void setSelectTemplateDirButton(Button selectTemplateDirButton) {
        this.selectTemplateDirButton = selectTemplateDirButton;
    }

    public Label getSitePagesLabel() {
        return sitePagesLabel;
    }

    public void setSitePagesLabel(Label sitePagesLabel) {
        this.sitePagesLabel = sitePagesLabel;
    }

    public TableView<SitePage> getSitePagesTable() {
        return sitePagesTable;
    }

    public void setSitePagesTable(TableView<SitePage> sitePagesTable) {
        this.sitePagesTable = sitePagesTable;
    }

    public TableColumn<SitePage, Boolean> getUseColumn() {
        return useColumn;
    }

    public void setUseColumn(TableColumn<SitePage, Boolean> useColumn) {
        this.useColumn = useColumn;
    }

    public TableColumn<SitePage, String> getNavBarTitleColumn() {
        return navBarTitleColumn;
    }

    public void setNavBarTitleColumn(TableColumn<SitePage, String> navBarTitleColumn) {
        this.navBarTitleColumn = navBarTitleColumn;
    }

    public TableColumn<SitePage, String> getFileNameColumn() {
        return fileNameColumn;
    }

    public void setFileNameColumn(TableColumn<SitePage, String> fileNameColumn) {
        this.fileNameColumn = fileNameColumn;
    }

    public TableColumn<SitePage, String> getScriptColumn() {
        return scriptColumn;
    }

    public void setScriptColumn(TableColumn<SitePage, String> scriptColumn) {
        this.scriptColumn = scriptColumn;
    }

    public Label getButtomPaneHeaderLabel() {
        return buttomPaneHeaderLabel;
    }

    public void setButtomPaneHeaderLabel(Label buttomPaneHeaderLabel) {
        this.buttomPaneHeaderLabel = buttomPaneHeaderLabel;
    }

    public Label getBannerSchoolImageLabel() {
        return bannerSchoolImageLabel;
    }

    public void setBannerSchoolImageLabel(Label bannerSchoolImageLabel) {
        this.bannerSchoolImageLabel = bannerSchoolImageLabel;
    }

    public Text getFirstText() {
        return firstText;
    }

    public void setFirstText(Text firstText) {
        this.firstText = firstText;
    }

    public Button getFirstChangeButton() {
        return firstChangeButton;
    }

    public void setFirstChangeButton(Button firstChangeButton) {
        this.firstChangeButton = firstChangeButton;
    }

    public Label getLeftFooterImageLabel() {
        return leftFooterImageLabel;
    }

    public void setLeftFooterImageLabel(Label leftFooterImageLabel) {
        this.leftFooterImageLabel = leftFooterImageLabel;
    }

    public Text getSecondText() {
        return secondText;
    }

    public void setSecondText(Text secondText) {
        this.secondText = secondText;
    }

    public Button getSecondChangeButton() {
        return secondChangeButton;
    }

    public void setSecondChangeButton(Button secondChangeButton) {
        this.secondChangeButton = secondChangeButton;
    }

    public Label getRightFooterImageLabel() {
        return rightFooterImageLabel;
    }

    public void setRightFooterImageLabel(Label rightFooterImageLabel) {
        this.rightFooterImageLabel = rightFooterImageLabel;
    }

    public Text getThirdText() {
        return thirdText;
    }

    public void setThirdText(Text thirdText) {
        this.thirdText = thirdText;
    }

    public Button getThirdChangeButton() {
        return thirdChangeButton;
    }

    public void setThirdChangeButton(Button thirdChangeButton) {
        this.thirdChangeButton = thirdChangeButton;
    }

    public Label getStyleSheetLabel() {
        return styleSheetLabel;
    }

    public void setStyleSheetLabel(Label styleSheetLabel) {
        this.styleSheetLabel = styleSheetLabel;
    }

    public ComboBox<String> getStyleSheetComboBox() {
        return styleSheetComboBox;
    }

    public void setStyleSheetComboBox(ComboBox<String> styleSheetComboBox) {
        this.styleSheetComboBox = styleSheetComboBox;
    }

    public Text getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(Text noteDescription) {
        this.noteDescription = noteDescription;
    }

    public HBox getPageStyleFirstHBox() {
        return pageStyleFirstHBox;
    }

    public void setPageStyleFirstHBox(HBox pageStyleFirstHBox) {
        this.pageStyleFirstHBox = pageStyleFirstHBox;
    }

    public HBox getPageStyleSecondHBox() {
        return pageStyleSecondHBox;
    }

    public void setPageStyleSecondHBox(HBox pageStyleSecondHBox) {
        this.pageStyleSecondHBox = pageStyleSecondHBox;
    }

    public HBox getPageStyleThirdHBox() {
        return pageStyleThirdHBox;
    }

    public void setPageStyleThirdHBox(HBox pageStyleThirdHBox) {
        this.pageStyleThirdHBox = pageStyleThirdHBox;
    }

    public HBox getPageStyleFourthHBox() {
        return pageStyleFourthHBox;
    }

    public void setPageStyleFourthHBox(HBox pageStyleFourthHBox) {
        this.pageStyleFourthHBox = pageStyleFourthHBox;
    }

    public FlowPane getSitePageTablePane() {
        return sitePageTablePane;
    }

    public void setSitePageTablePane(FlowPane sitePageTablePane) {
        this.sitePageTablePane = sitePageTablePane;
    }

    public String getExporDirDisplayAddressString() {
        return exporDirDisplayAddressString;
    }

    public void setExporDirDisplayAddressString(String exporDirDisplayAddressString) {
        this.exporDirDisplayAddressString = exporDirDisplayAddressString;
    }

    public ImageView getFirstImageView() {
        return firstImageView;
    }

    public void setFirstImageView(Image initfirstImage) {
        
        firstImageView.setImage(initfirstImage);
        
    }

    public ImageView getSecondImageView() {
        return secondImageView;
    }

    public void setSecondImageView(Image initsecondImage) {
        secondImageView.setImage(initsecondImage);
    }

    public ImageView getThirdImageView() {
        return thirdImageView;
    }

    public void setThirdImageView(Image initthirdImage) {
        thirdImageView.setImage(initthirdImage);
    }

    public String getFirstImageLocation() {
        return firstImageLocation;
    }

    public void setFirstImageLocation(String initfirstImageLocation) {
        firstImageLocation = initfirstImageLocation;
        Image temp=new Image(firstImageLocation);
        setFirstImageView(temp);   
      //  firstImageView.setImage(new Image(initfirstImageLocation));
    }

    public String getSecondImageLocation() {
        return secondImageLocation;
    }

    public void setSecondImageLocation(String initsecondImageLocation) {
        secondImageLocation = initsecondImageLocation;
           Image temp=new Image(secondImageLocation);
        setSecondImageView(temp);  
    }

    public String getThirdImageLocation() {
        return thirdImageLocation;
    }

    public void setThirdImageLocation(String initthirdImageLocation) {
        thirdImageLocation = initthirdImageLocation;
           Image temp=new Image(thirdImageLocation);
        setThirdImageView(temp);  
    }

   
    
}
