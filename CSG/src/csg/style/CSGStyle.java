/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.style;

import csg.CSGApp;
import csg.data.CSGRecitationData;
import csg.workspace.CSGCourseWorkspace;
import csg.workspace.CSGProjectWorkspace;
import csg.workspace.CSGRecitationWorkspace;
import csg.workspace.CSGScheduleWorkspace;
import csg.workspace.CSGTAWorkspace;
import csg.workspace.CSGWorkspace;
import djf.AppTemplate;
import djf.components.AppStyleComponent;

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
        CSGCourseWorkspace courseWorkspace=workspaceComponent.getCourseWorkspace();
        CSGTAWorkspace tAWorkspace=workspaceComponent.getTAWorkspace();
        CSGRecitationWorkspace recitationWorkspace=workspaceComponent.getRecitationWorkspace();
        CSGScheduleWorkspace scheduleWorkspace=workspaceComponent.getScheduleWorkspace();
        CSGProjectWorkspace projectWorkspace=workspaceComponent.getProjectWorkspace();
        
        workspaceComponent.getWorkspaceStateBar().getStyleClass().add(STATE_BAR_STYLE);
        //course workspace
        courseWorkspace.getSecondBasePane().getStyleClass().add(SECOND_BASE_PANE_STYLE);
       // courseWorkspace.getPageStyleFirstHBox().getStyleClass().add(FIRST_HBOX);
       courseWorkspace.getTopPane().getStyleClass().add(COURSE_PART_TOP_PANE);
       courseWorkspace.getCenterPane().getStyleClass().add(COURSE_PART_CENTER_PANE);
       courseWorkspace.getBottomPane().getStyleClass().add(COURSE_PART_BOTTOM_PANE);

        // LEFT SIDE - THE TABLE
     /*   TableView<TeachingAssistant> taTable = workspaceComponent.getTATable();
        taTable.getStyleClass().add(CLASS_TA_TABLE);
        for (TableColumn tableColumn : taTable.getColumns()) {
            tableColumn.getStyleClass().add(CLASS_TA_TABLE_COLUMN_HEADER);
        }

        // LEFT SIDE - THE TA DATA ENTRY
        workspaceComponent.getAddBox().getStyleClass().add(CLASS_ADD_TA_PANE);
        workspaceComponent.getNameTextField().getStyleClass().add(CLASS_ADD_TA_TEXT_FIELD);
        workspaceComponent.getEmailTextField().getStyleClass().add(CLASS_ADD_TA_TEXT_FIELD);
        workspaceComponent.getAddButton().getStyleClass().add(CLASS_ADD_TA_BUTTON);
        workspaceComponent.getUpdateButton().getStyleClass().add(CLASS_UPDATE_TA_BUTTON);
        workspaceComponent.getClearButton().getStyleClass().add(CLASS_CLEAR_TA_BUTTON);

        // RIGHT SIDE - THE HEADER
        workspaceComponent.getOfficeHoursSubheaderBox().getStyleClass().add(CLASS_HEADER_PANE);
        workspaceComponent.getOfficeHoursSubheaderLabel().getStyleClass().add(CLASS_HEADER_LABEL);
*/
    }
    
    
    public void initOfficeHoursGridStyle() {
        // RIGHT SIDE - THE OFFICE HOURS GRID TIME HEADERS
       /* TAWorkspace workspaceComponent = (TAWorkspace)app.getWorkspaceComponent();
        workspaceComponent.getOfficeHoursGridPane().getStyleClass().add(CLASS_OFFICE_HOURS_GRID);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridTimeHeaderPanes(), CLASS_OFFICE_HOURS_GRID_TIME_COLUMN_HEADER_PANE);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridTimeHeaderLabels(), CLASS_OFFICE_HOURS_GRID_TIME_COLUMN_HEADER_LABEL);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridDayHeaderPanes(), CLASS_OFFICE_HOURS_GRID_DAY_COLUMN_HEADER_PANE);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridDayHeaderLabels(), CLASS_OFFICE_HOURS_GRID_DAY_COLUMN_HEADER_LABEL);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridTimeCellPanes(), CLASS_OFFICE_HOURS_GRID_TIME_CELL_PANE);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridTimeCellLabels(), CLASS_OFFICE_HOURS_GRID_TIME_CELL_LABEL);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridTACellPanes(), CLASS_OFFICE_HOURS_GRID_TA_CELL_PANE);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridTACellLabels(), CLASS_OFFICE_HOURS_GRID_TA_CELL_LABEL);*/
    }
    

  //  private void setStyleClassOnAll(HashMap nodes, String styleClass) {
       /* for (Object nodeObject : nodes.values()) {
            Node n = (Node)nodeObject;
            n.getStyleClass().add(styleClass);
        }*/
   // }

}
