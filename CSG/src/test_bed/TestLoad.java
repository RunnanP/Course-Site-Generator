/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_bed;

import csg.CSGApp;
import csg.data.CSGData;
import csg.workspace.CSGTAWorkspace;
import csg.workspace.CSGWorkspace;
import djf.components.AppDataComponent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author runnan
 */
public class TestLoad {
    
        CSGApp app;
    
    

    //course part
     static final String JSON_COURSE_SUBJECT="course_subject";  
     static final String JSON_COURSE_NUMBER="course_number";  
     static final String JSON_COURSE_SEMESTER="course_semester";  
     static final String JSON_COURSE_YEAR="course_year";  
     static final String JSON_COURSE_TITLE="course_title";  
     static final String JSON_COURSE_INSTRUCTOR_NAME="course_intructor_name";  
     static final String JSON_COURSE_INSTRUCTOR_HOME="course_instructor_home"; 
     static final String JSON_COURSE_EXPORT_DIR="course_export_dir";  
     static final String JSON_COURSE_TEMPLATE_DIR="course_template_dir";  
     static final String JSON_COURSE_JSHOME="course_home";
    static final String JSON_COURSE_JSSYLLABUS="course_sysllabus";  
static final String JSON_COURSE_JSSCHEDULE="course_schedule";  
static final String JSON_COURSE_JSHWS="course_hws";  
static final String JSON_COURSE_JSPROJECTS="course_project";  
    // TA part
    static final String JSON_START_HOUR = "startHour";
    static final String JSON_END_HOUR = "endHour";
    static final String JSON_START_ON_TIME="startOnTime";
    static final String JSON_END_ON_TIME="endOnTime";
    static final String JSON_OFFICE_HOURS = "officeHours";
    static final String JSON_DAY = "day";
    static final String JSON_TIME = "time";
    static final String JSON_NAME = "name";
    static final String JSON_UNDERGRAD_TAS = "undergrad_tas";
    static final String JSON_TAS="tas";
    static final String JSON_EMAIL = "email";
   // recitation part
    static final String JSON_RECITATION="recitations";
    static final String JSON_RECITATION_SECTION="recitation_section";
    static final String JSON_RECITATION_INSTRUCTOR="recitation_instructor";
    static final String JSON_RECITATION_DAYTIME="recitation_daytime";
    static final String JSON_RECITATION_LOCATION="recitation_location";
    static final String JSON_RECITATION_FIRST_TA="recitation_first_ta";
    static final String JSON_RECITATION_SECOND_TA="recitation_second_ta";
    //schedule part
    static final String JSON_SCHEDULE="schedules";
    static final String JSON_SCHEDULE_CALENDAR_STARTING="schedule_calendar_starting";
    static final String JSON_SCHEDULE_CALENDAR_ENDING="schedule_calendar_ending";
    static final String JSON_SCHEDULE_TYPE="schedule_type";
    static final String JSON_SCHEDULE_DATE="schedule_date";
    static final String JSON_SCHEDULE_TITLE="schedule_title";
    static final String JSON_SCHEDULE_TOPIC="schedule_topic";
    //project part
    static final String JSON_TEAM="teams";
    static final String JSON_PROJECT_TEAM_NAME="project_team_name";
    static final String JSON_PROJECT_TEAM_COLOR="project_team_color";
    static final String JSON_PROJECT_TEAM_TEXTCOLOR="project_team_textcolor";
    static final String JSON_PROJECT_TEAM_LINK="project_team_link";
    
    static final String JSON_STUDENT="students";
    static final String JSON_PROJECT_STUDENT_FIRSTNAME="project_student_firstname";
    static final String JSON_PROJECT_STUDENT_LASTNAME="project_student_lastname";
    static final String JSON_PROJECT_STUDENT_TEAM="project_student_team";
    static final String JSON_PROJECT_STUDENT_ROLE="project_student_role";
    
    //common 
    static final String TEST_PATH="..\\\\CSG\\\\work\\\\SiteSaveTest.json";
    
    public TestLoad(CSGApp initApp){
        app=initApp;
    }
    public void loadData(AppDataComponent data, String filePath)  throws IOException{
        CSGData dataManager = (CSGData)data;
        //ta part///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// LOAD THE JSON FILE WITH ALL THE DATA
	JsonObject json = loadJSONFile(TEST_PATH);

	// LOAD THE START AND END HOURS
	String startHour = json.getString(JSON_START_HOUR);
        String endHour = json.getString(JSON_END_HOUR);

         dataManager.initHours(startHour, endHour);

        // NOW RELOAD THE WORKSPACE WITH THE LOADED DATA
        app.getWorkspaceComponent().reloadWorkspace(app.getDataComponent());
        CSGWorkspace temp=(CSGWorkspace) app.getWorkspaceComponent();
        CSGTAWorkspace workspace=temp.getCsgTAWorkspace();
       // workspace.clearCombobox();
        
        // NOW LOAD ALL THE UNDERGRAD TAs
        JsonArray jsonTAArray = json.getJsonArray(JSON_TAS);
        for (int i = 0; i < jsonTAArray.size(); i++) {
            JsonObject jsonTA = jsonTAArray.getJsonObject(i);
            String name = jsonTA.getString(JSON_NAME);
            String email = jsonTA.getString(JSON_EMAIL);
           Boolean underGrad=Boolean.parseBoolean(jsonTA.getString(JSON_UNDERGRAD_TAS));
           System.out.println(jsonTA.getString(JSON_UNDERGRAD_TAS));
            dataManager.addTA(name, email,underGrad);
        }

        // AND THEN ALL THE OFFICE HOURS
        JsonArray jsonOfficeHoursArray = json.getJsonArray(JSON_OFFICE_HOURS);
        for (int i = 0; i < jsonOfficeHoursArray.size(); i++) {
            JsonObject jsonOfficeHours = jsonOfficeHoursArray.getJsonObject(i);
            String day = jsonOfficeHours.getString(JSON_DAY);
            String time = jsonOfficeHours.getString(JSON_TIME);
            String name = jsonOfficeHours.getString(JSON_NAME);
            dataManager.addOfficeHoursReservation(day, time, name);
        }
        
        //recitation part////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        
        JsonArray jsonRecitionArray=json.getJsonArray(JSON_RECITATION);
        for (int i=0;i<jsonRecitionArray.size();i++){
            JsonObject jsonRecitation=jsonRecitionArray.getJsonObject(i);
            String initsection=jsonRecitation.getString(JSON_RECITATION_SECTION);
            String initinstructor=jsonRecitation.getString(JSON_RECITATION_INSTRUCTOR);
            String initdaytime=jsonRecitation.getString(JSON_RECITATION_DAYTIME);
            String initlocation=jsonRecitation.getString(JSON_RECITATION_LOCATION);
            String initfirstTA=jsonRecitation.getString(JSON_RECITATION_FIRST_TA);
            String initsecondTA=jsonRecitation.getString(JSON_RECITATION_SECOND_TA);
            dataManager.addRecitation(initsection, initinstructor, initdaytime, initlocation, initfirstTA, initsecondTA);
        }
        
        
        //schedule part
        JsonArray jsonScheduleArray=json.getJsonArray(JSON_SCHEDULE);
          for (int i=0;i<jsonScheduleArray.size();i++){
            JsonObject jsonSchedule=jsonScheduleArray.getJsonObject(i);
            String inittype=jsonSchedule.getString(JSON_SCHEDULE_TYPE);
            String initdate=jsonSchedule.getString(JSON_SCHEDULE_DATE);
            String inittitle=jsonSchedule.getString(JSON_SCHEDULE_TITLE);
            String inittopic=jsonSchedule.getString(JSON_SCHEDULE_TOPIC);
     
            dataManager.addScheduleItem(inittype,initdate,inittitle,inittopic);
        }
          
          //project part////////////////////////////////////////////////////////////////////////////////////////////////
          JsonArray jsonTeamArray=json.getJsonArray(JSON_TEAM);
          for (int i=0;i<jsonTeamArray.size();i++){
            JsonObject jsonTeam=jsonTeamArray.getJsonObject(i);
            String initname=jsonTeam.getString(JSON_PROJECT_TEAM_NAME);
            String initcolor=jsonTeam.getString(JSON_PROJECT_TEAM_COLOR);
            String inittextcolor=jsonTeam.getString(JSON_PROJECT_TEAM_TEXTCOLOR);
            String initlink=jsonTeam.getString(JSON_PROJECT_TEAM_LINK);
            
              System.out.println(initname);
              System.out.println(initcolor);
              System.out.println(inittextcolor);
              System.out.println(initlink);
     
            dataManager.addTeam(initname,initcolor,inittextcolor,initlink);
        }
        
             JsonArray jsonStudentArray=json.getJsonArray(JSON_STUDENT);
          for (int i=0;i<jsonStudentArray.size();i++){
            JsonObject jsonStudent=jsonStudentArray.getJsonObject(i);
            String initFirstname=jsonStudent.getString(JSON_PROJECT_STUDENT_FIRSTNAME);
            String initLastname=jsonStudent.getString(JSON_PROJECT_STUDENT_LASTNAME);
            String initTeam=jsonStudent.getString(JSON_PROJECT_STUDENT_TEAM);
            String initRole=jsonStudent.getString(JSON_PROJECT_STUDENT_ROLE);
     
            dataManager.addStudent(initFirstname,initLastname,initTeam,initRole);
        }
          
        
        
        
        
        
        
        
      //   TAWorkspace workspace=(TAWorkspace) app.getWorkspaceComponent();
       //   workspace.reloadCombobox(dataManager);.
    }
    
       private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
	InputStream is = new FileInputStream(jsonFilePath);
	JsonReader jsonReader = Json.createReader(is);
	JsonObject json = jsonReader.readObject();
	jsonReader.close();
	is.close();
	return json;
    }
}
