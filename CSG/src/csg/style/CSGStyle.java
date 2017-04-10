/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.style;

import csg.CSGApp;
import csg.data.TeachingAssistant;
import csg.workspace.CSGCourseWorkspace;
import csg.workspace.CSGProjectWorkspace;
import csg.workspace.CSGRecitationWorkspace;
import csg.workspace.CSGScheduleWorkspace;
import csg.workspace.CSGTAWorkspace;
import csg.workspace.CSGWorkspace;
import djf.AppTemplate;
import djf.components.AppStyleComponent;
import java.util.HashMap;
import javafx.beans.binding.Bindings;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author runnan
 */
public class CSGStyle extends AppStyleComponent{
    // FIRST WE SHOULD DECLARE ALL OF THE STYLE TYPES WE PLAN TO USE
    
    // WE'LL USE THIS FOR ORGANIZING LEFT AND RIGHT CONTROLS
    public static String CLASS_PLAIN_PANE = "plain_pane";
    
    // THESE ARE THE HEADERS FOR EACH SIDE
    public static String CLASS_HEADER_PANE = "header_pane";
    public static String CLASS_HEADER_LABEL = "header_label";

    // ON THE LEFT WE HAVE THE TA ENTRY
    public static String CLASS_TA_TABLE = "ta_table";
    public static String CLASS_TA_TABLE_COLUMN_HEADER = "ta_table_column_header";
    public static String CLASS_ADD_TA_PANE = "add_ta_pane";
    public static String CLASS_ADD_TA_TEXT_FIELD = "add_ta_text_field";
    public static String CLASS_ADD_TA_BUTTON = "add_ta_button";
    public static String CLASS_UPDATE_TA_BUTTON = "update_ta_button";
    public static String CLASS_CLEAR_TA_BUTTON = "clear_ta_button";

    // ON THE RIGHT WE HAVE THE OFFICE HOURS GRID
    public static String CLASS_OFFICE_HOURS_GRID = "office_hours_grid";
    public static String CLASS_OFFICE_HOURS_GRID_TIME_COLUMN_HEADER_PANE = "office_hours_grid_time_column_header_pane";
    public static String CLASS_OFFICE_HOURS_GRID_TIME_COLUMN_HEADER_LABEL = "office_hours_grid_time_column_header_label";
    public static String CLASS_OFFICE_HOURS_GRID_DAY_COLUMN_HEADER_PANE = "office_hours_grid_day_column_header_pane";
    public static String CLASS_OFFICE_HOURS_GRID_DAY_COLUMN_HEADER_LABEL = "office_hours_grid_day_column_header_label";
    public static String CLASS_OFFICE_HOURS_GRID_TIME_CELL_PANE = "office_hours_grid_time_cell_pane";
    public static String CLASS_OFFICE_HOURS_GRID_TIME_CELL_LABEL = "office_hours_grid_time_cell_label";
    public static String CLASS_OFFICE_HOURS_GRID_TA_CELL_PANE = "office_hours_grid_ta_cell_pane";
    public static String CLASS_OFFICE_HOURS_GRID_TA_CELL_LABEL = "office_hours_grid_ta_cell_label";

    // FOR HIGHLIGHTING CELLS, COLUMNS, AND ROWS
    public static String CLASS_HIGHLIGHTED_GRID_CELL = "highlighted_grid_cell";
    public static String CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN = "highlighted_grid_row_or_column";
    
    
    //new 
    public static String HEADER_STYLE="header_style";
    public static String BIG_HEADER_STYLE="big_header_style";
    public static String HEADER_PANE="header_pane";
    public static String BUTTON_STYLE="button_style";
    public static String LABEL_STYLE="label_style";
    public static String PANE_STYLE="pane_style";
    
    //WORKSPACE 
    public static String STATE_BAR_STYLE="state_bar_style";
    //COURSE workspace
    public static String SECOND_BASE_PANE_STYLE="second_pane_style";//gap
    
    public static String COURSE_PART_TOP_PANE="course_part_top_pane";
    public static String COURSE_PART_CENTER_PANE="course_part_center_pane";
    public static String COURSE_PART_BOTTOM_PANE="course_part_bottom_pane";
    
    
    
    
    
    
    
    
    
    // THIS PROVIDES ACCESS TO OTHER COMPONENTS
    private AppTemplate app;
    

    public CSGStyle(CSGApp initApp) {
       app = initApp;

        // LET'S USE THE DEFAULT STYLESHEET SETUP
        super.initStylesheet(app);

        // INIT THE STYLE FOR THE FILE TOOLBAR
        app.getGUI().initFileToolbarStyle();

        // AND NOW OUR WORKSPACE STYLE
        initCSGWorkspaceStyle();
    }
    
  


   
    private void initCSGWorkspaceStyle() {
        // LEFT SIDE - THE HEADER
        CSGWorkspace workspaceComponent = (CSGWorkspace)app.getWorkspaceComponent();
        CSGCourseWorkspace courseWorkspaceComponent=workspaceComponent.getCourseWorkspace();
        CSGTAWorkspace tAWorkspaceComponent=workspaceComponent.getTAWorkspace();
        CSGRecitationWorkspace recitationWorkspaceComponent=workspaceComponent.getRecitationWorkspace();
        CSGScheduleWorkspace scheduleWorkspaceComponent=workspaceComponent.getScheduleWorkspace();
        CSGProjectWorkspace projectWorkspaceComponent=workspaceComponent.getProjectWorkspace();
        
        workspaceComponent.getWorkspaceStateBar().getStyleClass().add(STATE_BAR_STYLE);
        //course workspace
     //   courseWorkspaceComponent.getSecondBasePane().getStyleClass().add(SECOND_BASE_PANE_STYLE);
       // courseWorkspace.getPageStyleFirstHBox().getStyleClass().add(FIRST_HBOX);
      
       courseWorkspaceComponent.getTopPane().getStyleClass().add(COURSE_PART_TOP_PANE);
       courseWorkspaceComponent.getCenterPane().getStyleClass().add(COURSE_PART_CENTER_PANE);
       courseWorkspaceComponent.getBottomPane().getStyleClass().add(COURSE_PART_BOTTOM_PANE);
       courseWorkspaceComponent.getTopPaneHeaderLabel().getStyleClass().add(HEADER_STYLE);
       courseWorkspaceComponent.getCentralPaneHeaderLabel().getStyleClass().add(HEADER_STYLE);
       courseWorkspaceComponent.getButtomPaneHeaderLabel().getStyleClass().add(HEADER_STYLE);
       courseWorkspaceComponent.getExportDirChangeButton().getStyleClass().add(BUTTON_STYLE);
       courseWorkspaceComponent.getFirstChangeButton().getStyleClass().add(BUTTON_STYLE);
       courseWorkspaceComponent.getSecondChangeButton().getStyleClass().add(BUTTON_STYLE);
       courseWorkspaceComponent.getThirdChangeButton().getStyleClass().add(BUTTON_STYLE);
       courseWorkspaceComponent.getSelectTemplateDirButton().getStyleClass().add(BUTTON_STYLE);
       courseWorkspaceComponent.getSubjectLabel().getStyleClass().add(LABEL_STYLE);
       courseWorkspaceComponent.getNumberLabel().getStyleClass().add(LABEL_STYLE);
       courseWorkspaceComponent.getSemesterLabel().getStyleClass().add(LABEL_STYLE);
       courseWorkspaceComponent.getYearLabel().getStyleClass().add(LABEL_STYLE);
       courseWorkspaceComponent.getTitleLabel().getStyleClass().add(LABEL_STYLE);
       courseWorkspaceComponent.getInstructorNameLabel().getStyleClass().add(LABEL_STYLE);
       courseWorkspaceComponent.getInstructorHomeLabel().getStyleClass().add(LABEL_STYLE);
       courseWorkspaceComponent.getExportDirLabel().getStyleClass().add(LABEL_STYLE);
       
       courseWorkspaceComponent.getSitePagesLabel().getStyleClass().add(LABEL_STYLE);
       courseWorkspaceComponent.getBannerSchoolImageLabel().getStyleClass().add(LABEL_STYLE);
       courseWorkspaceComponent.getLeftFooterImageLabel().getStyleClass().add(LABEL_STYLE);
       courseWorkspaceComponent.getRightFooterImageLabel().getStyleClass().add(LABEL_STYLE);
       courseWorkspaceComponent.getStyleSheetLabel().getStyleClass().add(LABEL_STYLE);
       
       
       
        

       
       //ta workspace
        // LEFT SIDE - THE TABLE
         tAWorkspaceComponent.getTasHeaderBox().getStyleClass().add(CLASS_HEADER_PANE);
        tAWorkspaceComponent.getTasHeaderLabel().getStyleClass().add(CLASS_HEADER_LABEL);
       TableView<TeachingAssistant> taTable = tAWorkspaceComponent.getTaTable();
        taTable.getStyleClass().add(CLASS_TA_TABLE);
        for (TableColumn tableColumn : taTable.getColumns()) {
            tableColumn.getStyleClass().add(CLASS_TA_TABLE_COLUMN_HEADER);
        }

        // LEFT SIDE - THE TA DATA ENTRY
        tAWorkspaceComponent.getAddBox().getStyleClass().add(CLASS_ADD_TA_PANE);
        tAWorkspaceComponent.getNameTextField().getStyleClass().add(CLASS_ADD_TA_TEXT_FIELD);
        tAWorkspaceComponent.getEmailTextField().getStyleClass().add(CLASS_ADD_TA_TEXT_FIELD);
        tAWorkspaceComponent.getAddButton().getStyleClass().add(CLASS_ADD_TA_BUTTON);
        tAWorkspaceComponent.getUpdateButton().getStyleClass().add(CLASS_UPDATE_TA_BUTTON);
        tAWorkspaceComponent.getClearButton().getStyleClass().add(CLASS_CLEAR_TA_BUTTON);

        // RIGHT SIDE - THE HEADER
        tAWorkspaceComponent.getOfficeHoursHeaderBox().getStyleClass().add(CLASS_HEADER_PANE);
        tAWorkspaceComponent.getOfficeHoursHeaderLabel().getStyleClass().add(CLASS_HEADER_LABEL);
        
        
        //recitation workspace
        recitationWorkspaceComponent.getAddeditHeaderLabel().getStyleClass().add(HEADER_STYLE);
        recitationWorkspaceComponent.getRecitationHeaderLabel().getStyleClass().add(BIG_HEADER_STYLE);
        recitationWorkspaceComponent.getRecitationPartAddUpdateButton().getStyleClass().add(BUTTON_STYLE);
        recitationWorkspaceComponent.getRecitationPartClearrButton().getStyleClass().add(BUTTON_STYLE);
        recitationWorkspaceComponent.getSectionLabel().getStyleClass().add(LABEL_STYLE);
        recitationWorkspaceComponent.getInstructorLabel().getStyleClass().add(LABEL_STYLE);
        recitationWorkspaceComponent.getDaytimeLabel().getStyleClass().add(LABEL_STYLE);
        recitationWorkspaceComponent.getLocationLabel().getStyleClass().add(LABEL_STYLE);
        recitationWorkspaceComponent.getFirstTALabel().getStyleClass().add(LABEL_STYLE);
        recitationWorkspaceComponent.getSecondTALabel().getStyleClass().add(LABEL_STYLE);
       recitationWorkspaceComponent.getBasePane().getStyleClass().add(COURSE_PART_TOP_PANE);
       recitationWorkspaceComponent.getSecondBasePane().getStyleClass().add(COURSE_PART_CENTER_PANE);
       recitationWorkspaceComponent.getAddeditRecitationPane().getStyleClass().add(COURSE_PART_BOTTOM_PANE);
        
        
        
        
        
        //schedule workspace
        scheduleWorkspaceComponent.getScheduleHeaderLabl().getStyleClass().add(BIG_HEADER_STYLE);
        scheduleWorkspaceComponent.getCalendarHeaderLabel().getStyleClass().add(HEADER_STYLE);
        scheduleWorkspaceComponent.getAddeditLabel().getStyleClass().add(HEADER_STYLE);
        scheduleWorkspaceComponent.getAddupdateButton().getStyleClass().add(BUTTON_STYLE);
        scheduleWorkspaceComponent.getClearButton().getStyleClass().add(BUTTON_STYLE);
        scheduleWorkspaceComponent.getStartDateLabel().getStyleClass().add(LABEL_STYLE);
        scheduleWorkspaceComponent.getEndDateLabel().getStyleClass().add(LABEL_STYLE);
        scheduleWorkspaceComponent.getTypeLabel().getStyleClass().add(LABEL_STYLE);
        scheduleWorkspaceComponent.getDateLabel().getStyleClass().add(LABEL_STYLE);
        scheduleWorkspaceComponent.getTimeLabel().getStyleClass().add(LABEL_STYLE);
        scheduleWorkspaceComponent.getTitleLabel().getStyleClass().add(LABEL_STYLE);
        scheduleWorkspaceComponent.getTopicLabel().getStyleClass().add(LABEL_STYLE);
        scheduleWorkspaceComponent.getLinkLabel().getStyleClass().add(LABEL_STYLE);
        scheduleWorkspaceComponent.getCriteriaLabel().getStyleClass().add(LABEL_STYLE);
        scheduleWorkspaceComponent.getBasePane().getStyleClass().add(COURSE_PART_TOP_PANE);
        scheduleWorkspaceComponent.getCalendarChooseBox().getStyleClass().add(COURSE_PART_CENTER_PANE);
        scheduleWorkspaceComponent.getScheduleItemBox().getStyleClass().add(COURSE_PART_BOTTOM_PANE);
        scheduleWorkspaceComponent.getSecondBasePane().getStyleClass().add(COURSE_PART_TOP_PANE);
     
        
        //project workspace
        projectWorkspaceComponent.getProjectHeaderLabel().getStyleClass().add(BIG_HEADER_STYLE);
        projectWorkspaceComponent.getStudentsHeaderLabel().getStyleClass().add(HEADER_STYLE);
        projectWorkspaceComponent.getTeamsHeaderLabel().getStyleClass().add(HEADER_STYLE);
        projectWorkspaceComponent.getNameLabel().getStyleClass().add(LABEL_STYLE);
        projectWorkspaceComponent.getColorLabel().getStyleClass().add(LABEL_STYLE);
        projectWorkspaceComponent.getTextColorLabel().getStyleClass().add(LABEL_STYLE);
        projectWorkspaceComponent.getLinkLabel().getStyleClass().add(LABEL_STYLE);
        projectWorkspaceComponent.getFirstNameLabel().getStyleClass().add(LABEL_STYLE);
        projectWorkspaceComponent.getLastNameLabel().getStyleClass().add(LABEL_STYLE);
        projectWorkspaceComponent.getTeamsLabel().getStyleClass().add(LABEL_STYLE);
        projectWorkspaceComponent.getRoleLabel().getStyleClass().add(LABEL_STYLE);
        projectWorkspaceComponent.getBasePane().getStyleClass().add(COURSE_PART_TOP_PANE);
        projectWorkspaceComponent.getTeamsVBox().getStyleClass().add(COURSE_PART_CENTER_PANE);
        projectWorkspaceComponent.getStudentsVBox().getStyleClass().add(COURSE_PART_BOTTOM_PANE);
        projectWorkspaceComponent.getSecondBasePane().getStyleClass().add(COURSE_PART_TOP_PANE);
        
        

    }
    
    
    public void initOfficeHoursGridStyle() {
        // RIGHT SIDE - THE OFFICE HOURS GRID TIME HEADERS
        CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
        CSGTAWorkspace workspaceComponent=temp.getCsgTAWorkspace();
        workspaceComponent.getOfficeHoursGridPane().getStyleClass().add(CLASS_OFFICE_HOURS_GRID);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridTimeHeaderPanes(), CLASS_OFFICE_HOURS_GRID_TIME_COLUMN_HEADER_PANE);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridTimeHeaderLabels(), CLASS_OFFICE_HOURS_GRID_TIME_COLUMN_HEADER_LABEL);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridDayHeaderPanes(), CLASS_OFFICE_HOURS_GRID_DAY_COLUMN_HEADER_PANE);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridDayHeaderLabels(), CLASS_OFFICE_HOURS_GRID_DAY_COLUMN_HEADER_LABEL);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridTimeCellPanes(), CLASS_OFFICE_HOURS_GRID_TIME_CELL_PANE);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridTimeCellLabels(), CLASS_OFFICE_HOURS_GRID_TIME_CELL_LABEL);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridTACellPanes(), CLASS_OFFICE_HOURS_GRID_TA_CELL_PANE);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridTACellLabels(), CLASS_OFFICE_HOURS_GRID_TA_CELL_LABEL);
    }
    

    private void setStyleClassOnAll(HashMap nodes, String styleClass) {
        for (Object nodeObject : nodes.values()) {
            Node n = (Node)nodeObject;
            n.getStyleClass().add(styleClass);
        }
    }

}
