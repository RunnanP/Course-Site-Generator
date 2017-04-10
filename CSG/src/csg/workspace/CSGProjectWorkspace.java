/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import csg.CSGApp;
import csg.CSGAppProp;
import csg.data.Student;
import csg.data.Team;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import properties_manager.PropertiesManager;

/**
 *
 * @author runnan
 */
public class CSGProjectWorkspace implements WorkspacePart{
    CSGApp app;
    CSGController controller;
    ScrollPane basePane;
    
    VBox secondBasePane;
    
    Label projectHeaderLabel;
    
    VBox teamsVBox;
    Label teamsHeaderLabel;
    TableView<Team> teamsTable;
    TableColumn<Team,String> nameColumn;
    TableColumn<Team,Color> colorColumn;
    TableColumn<Team,Color> textColumn;
    TableColumn<Team,String> linkColumn;
    Label teamsAddeditHeaderLabel;
    Label nameLabel;
    TextField nameTextField;
    Label colorLabel;
    Circle colorCircle;
    Label colorCircleText;
    Label textColorLabel;
    Circle textColorCircle;
    Label textColorCircleText;
    Label linkLabel;
    TextField linkTextField;
    HBox teamsAddBox;
    Button teamsAddUpdateButton;
    Button teamsClearButton;
    
    
    HBox teamsFirstHBox;
    HBox teamsSecondHBox;
    HBox teamsThirdHBox;
    HBox studentFirstHBox;
    HBox studentSecondHBox;
    HBox studentThirdHBox;
    HBox studentFourthHBox;
    
    VBox studentsVBox;
    Label studentsHeaderLabel;
    TableView<Student> studentsTable;
    TableColumn<Student,String> firstNameColumn;
    TableColumn<Student,String> lastNameColumn;
    TableColumn<Student,Team> teamColumn;
    TableColumn<Student,String> roleColumn;
    Label studentsAddeditHeaderLabel;
    Label firstNameLabel;
    TextField firstNameTextField;
    Label lastNameLabel;
    TextField lastNameTextField;
    Label teamsLabel;
    ComboBox<Team> teamsComboBox;
    Label roleLabel;
    TextField roleTextField;
    HBox studentsAddBox;
    Button studentAddUpdateButton;
    Button studentClearButton;
    
    
    public CSGProjectWorkspace(CSGApp initapp) {
        app=initapp;
         PropertiesManager props = PropertiesManager.getPropertiesManager();
        
         String projectHeaderText = props.getProperty(CSGAppProp.PROJECTS_HEADER_TEXT.toString());
         projectHeaderLabel=new Label(projectHeaderText);
         
         
         teamsVBox=new VBox();
         String teamsHeaderText = props.getProperty(CSGAppProp.TEAMS_TEXT.toString());
         teamsHeaderLabel=new Label(teamsHeaderText);
         teamsVBox.getChildren().add(teamsHeaderLabel);
         String nameColumnText = props.getProperty(CSGAppProp.NAME_COLUMN_TEXT.toString());
         String colorColumnText = props.getProperty(CSGAppProp.COLOR_COLUMN_TEXT.toString());
         String textColorColumnText = props.getProperty(CSGAppProp.TEXT_COLOR_COLUMN_TEXT.toString());
         String linkColumnText = props.getProperty(CSGAppProp.LINK_TEXT.toString());
         
         teamsTable=new TableView<>();
         teamsTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
         
         nameColumn=new TableColumn(nameColumnText);
         colorColumn=new TableColumn(colorColumnText);
         textColumn=new TableColumn(textColorColumnText);
         linkColumn=new TableColumn(linkColumnText);
         
         teamsTable.getColumns().addAll(nameColumn,colorColumn,textColumn,linkColumn);
         teamsVBox.getChildren().add(teamsTable);
         
         String addEditText=props.getProperty(CSGAppProp.ADD_EDIT_TEXT.toString());
         teamsAddeditHeaderLabel=new Label(addEditText);
         
         String nameText=props.getProperty(CSGAppProp.NAME_COLUMN_TEXT.toString());
         nameLabel=new Label(nameText+":");
         nameTextField=new TextField();
         teamsFirstHBox=new HBox(nameLabel,nameTextField);
         teamsVBox.getChildren().add(teamsFirstHBox);
         
         String colorText=props.getProperty(CSGAppProp.COLOR_TEXT.toString());
         String textColorText=props.getProperty(CSGAppProp.TEXT_COLOR_TEXT.toString());
         colorLabel=new Label(colorText);
         colorCircle=new Circle();
         textColorLabel=new Label(textColorText);
         textColorCircle=new Circle();
         teamsSecondHBox=new HBox(colorLabel,colorCircle,textColorLabel,textColorCircle);
         teamsVBox.getChildren().add(teamsSecondHBox);
         
         String linkText=props.getProperty(CSGAppProp.LINK_TEXT.toString());
         linkLabel=new Label(linkText);
         linkTextField=new TextField();
         teamsThirdHBox=new HBox(linkLabel,linkTextField);
         teamsVBox.getChildren().add(teamsThirdHBox);
         
         String addupdateText=props.getProperty(CSGAppProp.ADD_UPDATE_TEXT.toString());
         String clearText=props.getProperty(CSGAppProp.CLEAR_BUTTON_TEXT.toString());
         teamsAddUpdateButton=new Button(addupdateText);
         teamsClearButton=new Button(clearText);
         teamsAddBox=new HBox(teamsAddUpdateButton,teamsClearButton);
         teamsVBox.getChildren().add(teamsAddBox);
         
         studentsVBox=new VBox();
         String studentsText=props.getProperty(CSGAppProp.STUDENTS_TEXT.toString());
          studentsHeaderLabel=new Label(studentsText);
         studentsVBox.getChildren().add(studentsHeaderLabel);
          
          String firstNameColumnText = props.getProperty(CSGAppProp.FIRST_NAME_COLUMN_TEXT.toString());
         String lastNameColumnText = props.getProperty(CSGAppProp.LAST_NAME_COLUMN_TEXT.toString());
         String teamColumnText = props.getProperty(CSGAppProp.TEAM_TEXT.toString());
         String roleColumnText = props.getProperty(CSGAppProp.ROLE_TEXT.toString());
         
         studentsTable=new TableView<>();
         studentsTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
         
         firstNameColumn=new TableColumn(firstNameColumnText);
         lastNameColumn=new TableColumn(lastNameColumnText);
         teamColumn=new TableColumn(teamColumnText);
         roleColumn=new TableColumn(roleColumnText);
         
         studentsTable.getColumns().addAll(firstNameColumn,lastNameColumn,teamColumn,roleColumn);
         studentsVBox.getChildren().add(studentsTable);
         
         studentsAddeditHeaderLabel=new Label(addEditText);
         studentsVBox.getChildren().add(studentsAddeditHeaderLabel);
         firstNameLabel=new Label(firstNameColumnText+":");
         firstNameTextField=new TextField();
         studentFirstHBox=new HBox(firstNameLabel,firstNameTextField);
         
         lastNameLabel=new Label(lastNameColumnText+":");
         lastNameTextField=new TextField();
         studentSecondHBox=new HBox(lastNameLabel,lastNameTextField);
         
         teamsLabel=new Label(teamColumnText+":");
         teamsComboBox=new ComboBox<>();
         studentThirdHBox=new HBox(teamsLabel,teamsComboBox);
         
         roleLabel=new Label(roleColumnText+":");
         roleTextField=new TextField();
         studentFourthHBox=new HBox(roleLabel,roleTextField);
         
         studentAddUpdateButton=new Button(addupdateText);
         studentClearButton=new Button(clearText);
         studentsAddBox=new HBox(studentAddUpdateButton,studentClearButton);
         studentsVBox.getChildren().addAll(studentFirstHBox,studentSecondHBox,studentThirdHBox,studentFourthHBox,studentsAddBox);
         
         secondBasePane=new VBox(projectHeaderLabel,teamsVBox,studentsVBox);
         basePane=new ScrollPane(secondBasePane);
           secondBasePane.prefWidthProperty().bind(app.getGUI().getWindow().widthProperty().multiply(1));
          secondBasePane.prefHeightProperty().bind(app.getGUI().getWindow().heightProperty().multiply(1));
          nameTextField.prefWidthProperty().bind(app.getGUI().getWindow().widthProperty().multiply(.4));
          linkTextField.prefWidthProperty().bind(app.getGUI().getWindow().widthProperty().multiply(.4));
          firstNameTextField.prefWidthProperty().bind(app.getGUI().getWindow().widthProperty().multiply(.4));
          lastNameTextField.prefWidthProperty().bind(app.getGUI().getWindow().widthProperty().multiply(.4));
          roleTextField.prefWidthProperty().bind(app.getGUI().getWindow().widthProperty().multiply(.4));
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

    public Label getProjectHeaderLabel() {
        return projectHeaderLabel;
    }

    public void setProjectHeaderLabel(Label projectHeaderLabel) {
        this.projectHeaderLabel = projectHeaderLabel;
    }

    public VBox getTeamsVBox() {
        return teamsVBox;
    }

    public void setTeamsVBox(VBox teamsVBox) {
        this.teamsVBox = teamsVBox;
    }

    public Label getTeamsHeaderLabel() {
        return teamsHeaderLabel;
    }

    public void setTeamsHeaderLabel(Label teamsHeaderLabel) {
        this.teamsHeaderLabel = teamsHeaderLabel;
    }

    public TableView<Team> getTeamsTable() {
        return teamsTable;
    }

    public void setTeamsTable(TableView<Team> teamsTable) {
        this.teamsTable = teamsTable;
    }

    public TableColumn<Team, String> getNameColumn() {
        return nameColumn;
    }

    public void setNameColumn(TableColumn<Team, String> nameColumn) {
        this.nameColumn = nameColumn;
    }

    public TableColumn<Team, Color> getColorColumn() {
        return colorColumn;
    }

    public void setColorColumn(TableColumn<Team, Color> colorColumn) {
        this.colorColumn = colorColumn;
    }

    public TableColumn<Team, Color> getTextColumn() {
        return textColumn;
    }

    public void setTextColumn(TableColumn<Team, Color> textColumn) {
        this.textColumn = textColumn;
    }

    public TableColumn<Team, String> getLinkColumn() {
        return linkColumn;
    }

    public void setLinkColumn(TableColumn<Team, String> linkColumn) {
        this.linkColumn = linkColumn;
    }

    public Label getTeamsAddeditHeaderLabel() {
        return teamsAddeditHeaderLabel;
    }

    public void setTeamsAddeditHeaderLabel(Label teamsAddeditHeaderLabel) {
        this.teamsAddeditHeaderLabel = teamsAddeditHeaderLabel;
    }

    public Label getNameLabel() {
        return nameLabel;
    }

    public void setNameLabel(Label nameLabel) {
        this.nameLabel = nameLabel;
    }

    public TextField getNameTextField() {
        return nameTextField;
    }

    public void setNameTextField(TextField nameTextField) {
        this.nameTextField = nameTextField;
    }

    public Label getColorLabel() {
        return colorLabel;
    }

    public void setColorLabel(Label colorLabel) {
        this.colorLabel = colorLabel;
    }

    public Circle getColorCircle() {
        return colorCircle;
    }

    public void setColorCircle(Circle colorCircle) {
        this.colorCircle = colorCircle;
    }

    public Label getColorCircleText() {
        return colorCircleText;
    }

    public void setColorCircleText(Label colorCircleText) {
        this.colorCircleText = colorCircleText;
    }

    public Label getTextColorLabel() {
        return textColorLabel;
    }

    public void setTextColorLabel(Label textColorLabel) {
        this.textColorLabel = textColorLabel;
    }

    public Circle getTextColorCircle() {
        return textColorCircle;
    }

    public void setTextColorCircle(Circle textColorCircle) {
        this.textColorCircle = textColorCircle;
    }

    public Label getTextColorCircleText() {
        return textColorCircleText;
    }

    public void setTextColorCircleText(Label textColorCircleText) {
        this.textColorCircleText = textColorCircleText;
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

    public HBox getTeamsAddBox() {
        return teamsAddBox;
    }

    public void setTeamsAddBox(HBox teamsAddBox) {
        this.teamsAddBox = teamsAddBox;
    }

    public Button getTeamsAddUpdateButton() {
        return teamsAddUpdateButton;
    }

    public void setTeamsAddUpdateButton(Button teamsAddUpdateButton) {
        this.teamsAddUpdateButton = teamsAddUpdateButton;
    }

    public Button getTeamsClearButton() {
        return teamsClearButton;
    }

    public void setTeamsClearButton(Button teamsClearButton) {
        this.teamsClearButton = teamsClearButton;
    }

    public HBox getTeamsFirstHBox() {
        return teamsFirstHBox;
    }

    public void setTeamsFirstHBox(HBox teamsFirstHBox) {
        this.teamsFirstHBox = teamsFirstHBox;
    }

    public HBox getTeamsSecondHBox() {
        return teamsSecondHBox;
    }

    public void setTeamsSecondHBox(HBox teamsSecondHBox) {
        this.teamsSecondHBox = teamsSecondHBox;
    }

    public HBox getTeamsThirdHBox() {
        return teamsThirdHBox;
    }

    public void setTeamsThirdHBox(HBox teamsThirdHBox) {
        this.teamsThirdHBox = teamsThirdHBox;
    }

    public HBox getStudentFirstHBox() {
        return studentFirstHBox;
    }

    public void setStudentFirstHBox(HBox studentFirstHBox) {
        this.studentFirstHBox = studentFirstHBox;
    }

    public HBox getStudentSecondHBox() {
        return studentSecondHBox;
    }

    public void setStudentSecondHBox(HBox studentSecondHBox) {
        this.studentSecondHBox = studentSecondHBox;
    }

    public HBox getStudentThirdHBox() {
        return studentThirdHBox;
    }

    public void setStudentThirdHBox(HBox studentThirdHBox) {
        this.studentThirdHBox = studentThirdHBox;
    }

    public HBox getStudentFourthHBox() {
        return studentFourthHBox;
    }

    public void setStudentFourthHBox(HBox studentFourthHBox) {
        this.studentFourthHBox = studentFourthHBox;
    }

    public VBox getStudentsVBox() {
        return studentsVBox;
    }

    public void setStudentsVBox(VBox studentsVBox) {
        this.studentsVBox = studentsVBox;
    }

    public Label getStudentsHeaderLabel() {
        return studentsHeaderLabel;
    }

    public void setStudentsHeaderLabel(Label studentsHeaderLabel) {
        this.studentsHeaderLabel = studentsHeaderLabel;
    }

    public TableView<Student> getStudentsTable() {
        return studentsTable;
    }

    public void setStudentsTable(TableView<Student> studentsTable) {
        this.studentsTable = studentsTable;
    }

    public TableColumn<Student, String> getFirstNameColumn() {
        return firstNameColumn;
    }

    public void setFirstNameColumn(TableColumn<Student, String> firstNameColumn) {
        this.firstNameColumn = firstNameColumn;
    }

    public TableColumn<Student, String> getLastNameColumn() {
        return lastNameColumn;
    }

    public void setLastNameColumn(TableColumn<Student, String> lastNameColumn) {
        this.lastNameColumn = lastNameColumn;
    }

    public TableColumn<Student, Team> getTeamColumn() {
        return teamColumn;
    }

    public void setTeamColumn(TableColumn<Student, Team> teamColumn) {
        this.teamColumn = teamColumn;
    }

    public TableColumn<Student, String> getRoleColumn() {
        return roleColumn;
    }

    public void setRoleColumn(TableColumn<Student, String> roleColumn) {
        this.roleColumn = roleColumn;
    }

    public Label getStudentsAddeditHeaderLabel() {
        return studentsAddeditHeaderLabel;
    }

    public void setStudentsAddeditHeaderLabel(Label studentsAddeditHeaderLabel) {
        this.studentsAddeditHeaderLabel = studentsAddeditHeaderLabel;
    }

    public Label getFirstNameLabel() {
        return firstNameLabel;
    }

    public void setFirstNameLabel(Label firstNameLabel) {
        this.firstNameLabel = firstNameLabel;
    }

    public TextField getFirstNameTextField() {
        return firstNameTextField;
    }

    public void setFirstNameTextField(TextField firstNameTextField) {
        this.firstNameTextField = firstNameTextField;
    }

    public Label getLastNameLabel() {
        return lastNameLabel;
    }

    public void setLastNameLabel(Label lastNameLabel) {
        this.lastNameLabel = lastNameLabel;
    }

    public TextField getLastNameTextField() {
        return lastNameTextField;
    }

    public void setLastNameTextField(TextField lastNameTextField) {
        this.lastNameTextField = lastNameTextField;
    }

    public Label getTeamsLabel() {
        return teamsLabel;
    }

    public void setTeamsLabel(Label teamsLabel) {
        this.teamsLabel = teamsLabel;
    }

    public ComboBox<Team> getTeamsComboBox() {
        return teamsComboBox;
    }

    public void setTeamsComboBox(ComboBox<Team> teamsComboBox) {
        this.teamsComboBox = teamsComboBox;
    }

    public Label getRoleLabel() {
        return roleLabel;
    }

    public void setRoleLabel(Label roleLabel) {
        this.roleLabel = roleLabel;
    }

    public TextField getRoleTextField() {
        return roleTextField;
    }

    public void setRoleTextField(TextField roleTextField) {
        this.roleTextField = roleTextField;
    }

    public HBox getStudentsAddBox() {
        return studentsAddBox;
    }

    public void setStudentsAddBox(HBox studentsAddBox) {
        this.studentsAddBox = studentsAddBox;
    }

    public Button getStudentAddUpdateButton() {
        return studentAddUpdateButton;
    }

    public void setStudentAddUpdateButton(Button studentAddUpdateButton) {
        this.studentAddUpdateButton = studentAddUpdateButton;
    }

    public Button getStudentClearButton() {
        return studentClearButton;
    }

    public void setStudentClearButton(Button studentClearButton) {
        this.studentClearButton = studentClearButton;
    }
}
