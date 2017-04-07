/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import csg.CSGApp;
import csg.CSGAppProp;
import csg.data.SitePage;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
    Text descriptionText;
    Label templatesDirLabel;
    Button selectTemplateDirButton;
    Label sitePagesLabel;
    TableView<SitePage>  sitePagesTable;
    TableColumn<SitePage,CheckBox> useColumn;
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
    Label styleSheetLabel;
    ComboBox<String> styleSheetComboBox;
    Text noteDescription;
    HBox pageStyleFirstHBox;
    HBox pageStyleSecondHBox;
    HBox pageStyleThirdHBox;
    HBox pageStyleFourthHBox;
    
    public CSGCourseWorkspace(CSGApp initapp){
         app=initapp;
        
         initTopPane();
         initCenterPane();
         initBottomPane();
         secondBasePane=new BorderPane();
         secondBasePane.setTop(topPane);
         secondBasePane.setCenter(centerPane);
         secondBasePane.setBottom(bottomPane);
         basePane=new ScrollPane(secondBasePane);
         //basePane.getChildren().add(secondBasePane);
         
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
       String centerPaneHeaderLabelText = props.getProperty(CSGAppProp.COURSE_WORKSPACE_PART_CENTER_PANE_HEADER_TEXT.toString());
       centralPaneHeaderLabel=new Label(centerPaneHeaderLabelText);
       String descriptionDetail = props.getProperty(CSGAppProp.DESCRIPTION_DETAIL_TEXT.toString());
       descriptionText=new Text(descriptionDetail);
       templatesDirLabel=new Label();
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
        sitePagesTable.getColumns().addAll(useColumn,navBarTitleColumn,fileNameColumn,scriptColumn);
        
        centerPane.getChildren().addAll(centralPaneHeaderLabel,descriptionText,templatesDirLabel,selectTemplateDirButton,sitePagesLabel,sitePagesTable);
        
        
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
         firstText=new Text("YALE UNIVERSITY");
         firstChangeButton=new Button(changeText);
         pageStyleFirstHBox=new HBox(bannerSchoolImageLabel,firstText,firstChangeButton);
         bottomPane.getChildren().add(pageStyleFirstHBox);
         
         leftFooterImageLabel=new Label(leftFooterImageLabelText);
         secondText=new Text("YALE UNIVERSITY");
         secondChangeButton=new Button(changeText);
         pageStyleSecondHBox=new HBox(leftFooterImageLabel,secondText,secondChangeButton);
         bottomPane.getChildren().add(pageStyleSecondHBox);
         
         rightFooterImageLabel=new Label(rightFooterImageLabelText);
         thirdText=new Text("YALE CS");
         thirdChangeButton=new Button(changeText);
         pageStyleThirdHBox=new HBox(rightFooterImageLabel,thirdText,thirdChangeButton);
         bottomPane.getChildren().add(pageStyleThirdHBox);
         
    
         styleSheetLabel=new Label(styleSheetLabelText);
         
        styleSheetComboBox=new ComboBox<>();
        fourthHBox=new HBox(styleSheetLabel,styleSheetComboBox);
        String noteDescriptionText=props.getProperty(CSGAppProp.NOTE_DESCRIPTION_TEXT.toString());
        noteDescription=new Text(noteDescriptionText);
        
        bottomPane.getChildren().addAll(fourthHBox,noteDescription);
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

    public void setExportDirLabel(Label exportDirLabel) {
        this.exportDirLabel = exportDirLabel;
    }

    public Label getExporDirDisplayAddressLabel() {
        return exporDirDisplayAddressLabel;
    }

    public void setExporDirDisplayAddressLabel(Label exporDirDisplayAddressLabel) {
        this.exporDirDisplayAddressLabel = exporDirDisplayAddressLabel;
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

    public void setTemplatesDirLabel(Label templatesDirLabel) {
        this.templatesDirLabel = templatesDirLabel;
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

    public TableColumn<SitePage, CheckBox> getUseColumn() {
        return useColumn;
    }

    public void setUseColumn(TableColumn<SitePage, CheckBox> useColumn) {
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
    
}
