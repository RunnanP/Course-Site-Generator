/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_bed;

import csg.CSGApp;
import csg.data.CSGData;
import csg.data.Recitation;
import csg.data.ScheduleItem;
import csg.data.Student;
import csg.data.TeachingAssistant;
import csg.data.Team;
import csg.file.CSGFiles;
import csg.file.CSGTimeSlot;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import djf.components.AppDataComponent;
import djf.components.AppFileComponent;
import static djf.settings.AppStartupConstants.ENGLISH_APP_PROPERTIES_FILE_NAME;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.ObservableList;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;

/**
 *
 * @author runnan
 */
public class TestSave {
    
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
     static final String JSON_COURSE_FIRST_IMAGE_ADDRESS="course_first_image_address";
     static final String JSON_COURSE_SECOND_IMAGE_ADDRESS="course_second_image_address";
     static final String JSON_COURSE_THIRD_IMAGE_ADDRESS="course_third_image_address";
      static final String JSON_COURSE_STYTLE_SHEET="course_stytle_sheet";
      
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
    
    
    
    
    //part with hard code main
    
 
    
    public TestSave(CSGApp initApp){
        app=initApp;
    }
    
     public void saveDataWithoutHardCode(AppDataComponent data,String filePath) throws IOException {
	// GET THE DATA
	CSGData dataManager = (CSGData)data;
      
	// NOW BUILD THE TA JSON OBJCTS TO SAVE
	JsonArrayBuilder taArrayBuilder = Json.createArrayBuilder();
	ObservableList<TeachingAssistant> tas = dataManager.getTeachingAssistants();
	for (TeachingAssistant ta : tas) {	
            
            
	    JsonObject taJson = Json.createObjectBuilder()
		    .add(JSON_NAME, ta.getName())
		    .add(JSON_EMAIL, ta.getEmail())
                    .add(JSON_UNDERGRAD_TAS,ta.getUnder()).build();
	    taArrayBuilder.add(taJson);
	}
	JsonArray undergradTAsArray = taArrayBuilder.build();
  
	// NOW BUILD THE TIME SLOT JSON OBJCTS TO SAVE
	JsonArrayBuilder timeSlotArrayBuilder = Json.createArrayBuilder();
	ArrayList<CSGTimeSlot> officeHours = CSGTimeSlot.buildOfficeHoursList(dataManager);
	for (CSGTimeSlot ts : officeHours) {	    
	    JsonObject tsJson = Json.createObjectBuilder()
		    .add(JSON_DAY, ts.getDay())
		    .add(JSON_TIME, ts.getTime())
		    .add(JSON_NAME, ts.getName()).build();
	    timeSlotArrayBuilder.add(tsJson);
	}
	JsonArray timeSlotsArray = timeSlotArrayBuilder.build();
         
        //Recitation part
        
        
        JsonArrayBuilder recitationArrayBuilder=Json.createArrayBuilder();
        ObservableList<Recitation> recitations=dataManager.getRecitations();
        for(Recitation recitation:recitations){
            
            JsonObject recitationJson=Json.createObjectBuilder()
                    .add(JSON_RECITATION_SECTION,recitation.getSection())
                    .add(JSON_RECITATION_INSTRUCTOR,recitation.getInstructor())
                    .add(JSON_RECITATION_DAYTIME,recitation.getDaytime())
                    .add(JSON_RECITATION_LOCATION,recitation.getLocation())
                    .add(JSON_RECITATION_FIRST_TA,recitation.getFirstTa())
                    .add(JSON_RECITATION_SECOND_TA,recitation.getSecondTa()).build();
            recitationArrayBuilder.add(recitationJson);
        }
        JsonArray recitationArray=recitationArrayBuilder.build();
        
        
        //schedule part
        
         JsonArrayBuilder scheduleArrayBuilder=Json.createArrayBuilder();
        ObservableList<ScheduleItem> schedules=dataManager.getScheduleItems();
        for(ScheduleItem scheduleItem:schedules){
            
            JsonObject scheduleJson=Json.createObjectBuilder()
                    .add(JSON_SCHEDULE_TYPE,scheduleItem.getType())
                    .add(JSON_SCHEDULE_DATE,scheduleItem.getDate())
                    .add(JSON_SCHEDULE_TITLE,scheduleItem.getTitle())
                    .add(JSON_SCHEDULE_TOPIC,scheduleItem.getTopic()).build();
            scheduleArrayBuilder.add(scheduleJson);
        }
        JsonArray scheduleArray=scheduleArrayBuilder.build();
        
        
        
        //project part
        
           JsonArrayBuilder teamArrayBuilder=Json.createArrayBuilder();
        ObservableList<Team> teams=dataManager.getTeams();
        for(Team team:teams){
            
            JsonObject teamJson=Json.createObjectBuilder()
                    .add(JSON_PROJECT_TEAM_NAME,team.getTeamname())
                    .add(JSON_PROJECT_TEAM_COLOR,team.getColor())
                    .add(JSON_PROJECT_TEAM_TEXTCOLOR,team.getTextcolor())
                    .add(JSON_PROJECT_TEAM_LINK,team.getLink()).build();
            teamArrayBuilder.add(teamJson);
        }
        JsonArray teamArray=teamArrayBuilder.build();
        
        
         JsonArrayBuilder studentArrayBuilder=Json.createArrayBuilder();
        ObservableList<Student> students=dataManager.getStudents();
        for(Student student:students){
            
            JsonObject studentJson=Json.createObjectBuilder()
                    .add(JSON_PROJECT_STUDENT_FIRSTNAME,student.getFirstName())
                    .add(JSON_PROJECT_STUDENT_LASTNAME,student.getLastName())
                    .add(JSON_PROJECT_STUDENT_TEAM,student.getTeamString())
                    .add(JSON_PROJECT_STUDENT_ROLE,student.getRole()).build();
            studentArrayBuilder.add(studentJson);
        }
        JsonArray studentArray=studentArrayBuilder.build();
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
	// THEN PUT IT ALL TOGETHER IN A JsonObject
	JsonObject dataManagerJSO = Json.createObjectBuilder()
                .add(JSON_COURSE_JSHOME, dataManager.isJhome())
                .add(JSON_COURSE_JSSYLLABUS, dataManager.isJsyllabus())
                .add(JSON_COURSE_JSSCHEDULE, dataManager.isJschedule())
                .add(JSON_COURSE_JSHWS, dataManager.isJhws())
                .add(JSON_COURSE_JSPROJECTS, dataManager.isJproject())
                
                .add(JSON_COURSE_SUBJECT, ""+dataManager.getSubject())
                .add(JSON_COURSE_NUMBER, ""+dataManager.getNumber())
                .add(JSON_COURSE_SEMESTER, ""+dataManager.getSemester())
                .add(JSON_COURSE_YEAR, ""+dataManager.getYear())
                .add(JSON_COURSE_TITLE, ""+dataManager.getTitle())
                .add(JSON_COURSE_INSTRUCTOR_NAME, ""+dataManager.getInstructorName())
                .add(JSON_COURSE_INSTRUCTOR_HOME, ""+dataManager.getInstructorHome())
                
                 .add(JSON_COURSE_EXPORT_DIR, ""+dataManager.getExportDir())
                .add(JSON_COURSE_TEMPLATE_DIR, ""+dataManager.getSiteTempleDir())
//                
                .add(JSON_COURSE_FIRST_IMAGE_ADDRESS,""+dataManager.getFirstImageAdd())
                .add(JSON_COURSE_SECOND_IMAGE_ADDRESS,""+dataManager.getSecondImageAdd())
                .add(JSON_COURSE_THIRD_IMAGE_ADDRESS,""+dataManager.getThirdImageAdd())
                  .add(JSON_COURSE_STYTLE_SHEET,""+dataManager.getStyleSheet())
                
                //
		.add(JSON_START_HOUR, "" + dataManager.getStartHour())
		.add(JSON_END_HOUR, "" + dataManager.getEndHour())
                .add(JSON_TAS, undergradTAsArray)
                .add(JSON_OFFICE_HOURS, timeSlotsArray)
                //
                .add(JSON_SCHEDULE_CALENDAR_STARTING, ""+dataManager.getStartingDate())
                .add(JSON_SCHEDULE_CALENDAR_ENDING, ""+dataManager.getEndingDate())
                .add(JSON_RECITATION,recitationArray)
                //
                .add(JSON_SCHEDULE, scheduleArray)
                //
                .add(JSON_TEAM, teamArray)
                .add(JSON_STUDENT, studentArray)
		.build();
	
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
	// AND NOW OUTPUT IT TO A JSON FILE WITH PRETTY PRINTING
	Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(dataManagerJSO);
	jsonWriter.close();
 
	// INIT THE WRITER
	//OutputStream os = new FileOutputStream(filePath);
        OutputStream os = new FileOutputStream(TEST_PATH);//////////////////////////////////////////
	JsonWriter jsonFileWriter = Json.createWriter(os);
        
	jsonFileWriter.writeObject(dataManagerJSO);
       
	String prettyPrinted = sw.toString();
    
	//PrintWriter pw = new PrintWriter(filePath);
        PrintWriter pw = new PrintWriter(TEST_PATH);/////////////////////////////////////////////
	pw.write(prettyPrinted);
	pw.close();
         
    }
     
     
     
     
     
        public void saveDataWithHardCode(CSGData data){
         data.setSubject("CSE");
        data.setNumber(219);
        data.setSemester("Spring");
        data.setYear(2017);
        data.setTitle("Computer Science III");
        data.setInstructorName("Ritwik Banerjee");
        data.setInstructorHome("http://www3.cs.stonybrook.edu/~rbanerjee/");
        data.setExportDir("../CSE219/public");
        data.setSiteTempleDir("./templates/CSE219");
        data.setJhome(true);
        data.setJsyllabus(true);
        data.setJschedule(true);
        data.setJhws(true);
        data.setJproject(true);
       
       
        data.addTA( "Jane Doe", "jane.doe@yahoo.com",true);
        data.addTA( "Joe Shmo", "joe.shmo@yale.edu",false);
        data.addOfficeHoursReservation("MONDAY", "11_00am", "Jane Dow");
        data.addOfficeHoursReservation("WEDNESDAY", "7_00pm", "Jane Dow");
        data.addOfficeHoursReservation("TUESDAY", "12_00pm", "Joe Shmo");
        data.addOfficeHoursReservation("MONDAY", "5_00pm", "Joe Shmo");
       
        
        
        
        data.addRecitation("R02", "McKenna", "Wed 3:30pm-4:23pm", "Old CS 2114", "bill","mike");
        data.addRecitation("R05", "Banerjee", "Tues 5:30pm-6:23pm", "Old CS 2114", "pol", "amy");
       
        
        data.setStartingDate("2017-04-03");
        data.setEndingDate("2017-05-05");
        data.addScheduleItem("Holiday", "2017-04-08", "whole day", "SNOW DAY", "..", "..","..");
       
      
        data.addTeam("Atomic Comics", "552211", "ffffff", "http://atomiccomic.com");
        data.addTeam("C4 Comics", "235399", "ffffff", "http://c4-comics.com");
        data.addStudent("Beau", "Brummell", "Atomic Comics", "Lead Designer");
        data.addStudent("Jane", "Doe", "C4 Comics", "Lead Programmer");
   




}
     //hard code       
          public static void test ()throws IOException {
        CSGApp app = new CSGApp();
        CSGData data = new CSGData(app);
        
 
        
        app.loadProperties(ENGLISH_APP_PROPERTIES_FILE_NAME);
        app.setDataComponent(app);
        
        
        
       // app.setWorkspaceComponent(app);
       
//        AppDataComponent data = app.getDataComponent();
//        String filePath = "..\\\\CSG\\\\work\\\\k.json";
//        CSGFiles instance = new CSGFiles(app);
        //course part
        data.setSubject("CSE");
        data.setNumber(219);
        data.setSemester("Spring");
        data.setYear(2017);
        data.setTitle("Computer Science III");
        data.setInstructorName("Ritwik Banerjee");
        data.setInstructorHome("http://www3.cs.stonybrook.edu/~rbanerjee/");
        data.setExportDir("../CSE219/public");
        data.setSiteTempleDir("./templates/CSE219");
        data.setJhome(true);
        data.setJsyllabus(true);
        data.setJschedule(true);
        data.setJhws(true);
        data.setJproject(true);
       
       
        data.addTA( "Jane Dow", "jane.doe@yahoo.com",true);
        data.addTA( "Joe Shmo", "joe.shmo@yale.edu",false);
        data.addOfficeHoursReservation("MONDAY", "11_00am", "Jane Dow");
        data.addOfficeHoursReservation("WEDNESDAY", "14_00pm", "Jane Dow");
        data.addOfficeHoursReservation("TUESDAY", "12_00pm", "Joe Shmo");
        data.addOfficeHoursReservation("MONDAY", "8_00pm", "Joe Shmo");
       
        
        
        
        data.addRecitation("R02", "McKenna", "Wed 3:30pm-4:23pm", "Old CS 2114", "bill","mike");
        data.addRecitation("R05", "Banerjee", "Tues 5:30pm-6:23pm", "Old CS 2114", "pol", "amy");
       
        
        data.setStartingDate("2017-04-03");
        data.setEndingDate("2017-05-05");
        data.addScheduleItem("Holiday", "2017-04-08", "whole day", "SNOW DAY", "..", "..","..");
       
      
        data.addTeam("Atomic Comics", "552211", "ffffff", "http://atomiccomic.com");
        data.addTeam("C4 Comics", "235399", "ffffff", "http://c4-comics.com");
        data.addStudent("Beau", "Brummell", "Atomic Comics", "Lead Designer");
        data.addStudent("Jane", "Doe", "C4 Comics", "Lead Programmer");
       
//        CSGFiles file = new CSGFiles(app);
//        file.saveData(data, "..\\\\CSG\\\\work\\\\SiteSaveTest.json");
    }
        
        
        
}



   