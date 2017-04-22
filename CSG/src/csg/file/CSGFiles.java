/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.file;

import csg.CSGApp;
import csg.data.CSGData;
import csg.workspace.CSGTAWorkspace;
import csg.workspace.CSGWorkspace;
import djf.components.AppDataComponent;
import djf.components.AppFileComponent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import test_bed.TestLoad;
import test_bed.TestSave;

/**
 *
 * @author runnan
 */
public class CSGFiles implements AppFileComponent{
 //   public CSGFiles(CSGApp initApp){
        
    //}
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
    public CSGFiles(CSGApp initApp) {
     app=initApp;
    }

    @Override
    public void saveData(AppDataComponent data, String filePath) throws IOException {
       TestSave test =new TestSave(app);
       test.saveData(data, filePath);
    }

    @Override
    public void loadData(AppDataComponent data, String filePath) throws IOException {
        TestLoad test=new TestLoad(app);
         try {
             test.loadData(data, filePath);
         } catch (ParseException ex) {
             Logger.getLogger(CSGFiles.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
//       private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
//	InputStream is = new FileInputStream(jsonFilePath);
//	JsonReader jsonReader = Json.createReader(is);
//	JsonObject json = jsonReader.readObject();
//	jsonReader.close();
//	is.close();
//	return json;
//    }

    @Override
    public void exportData(AppDataComponent data, String filePath) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void importData(AppDataComponent data, String filePath) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
