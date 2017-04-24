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
import csg.file.CSGTimeSlot;
import djf.components.AppDataComponent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.ObservableList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import static test_bed.TestSave.JSON_NAME;

/**
 *
 * @author runnan
 */
public class TestExport {
    
    CSGApp app;
    
    
    
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
    static final String JSON_COURSE_JSSYLLABUS="course_syllabus";  
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
    static final String JSON_UNDERGRAD_TAS = "under_tas";
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
    
    
    
    
    //addition for export
    //projectsData.json
    static final String ADD_JSON_PROJECT_WORK="work";
      static final String ADD_JSON_PROJECT_SEMESTER="semester";
      static final String ADD_JSON_PROJECT_PROJECT="project";
         static final String ADD_JSON_PROJECT_NAME="name";
         static final String ADD_JSON_PROJECT_STUDENT="students";
         static final String ADD_JSON_PROJECT_LINK="link";
       //scheduleData.json 
    static final String ADD_JSON_SCHEDULE_STARTING_MONDAYMONTH="startingMondayMonth";
    static final String ADD_JSON_SCHEDULE_STARTING_MONDAYDAY="startingMondayDay";
    static final String ADD_JSON_SCHEDULE_ENDING_FRIDAYMONTH="endingFridayMonth";
    static final String ADD_JSON_SCHEDULE_ENDING_FRIDAYDAY="endingFridayDay";
        static final String ADD_JSON_SCHEDULE_HOLIDAYS="holidays";
        static final String ADD_JSON_SCHEDULE_LECTURES="lectures";
        static final String ADD_JSON_SCHEDULE_REFERENCES="references";
        static final String ADD_JSON_SCHEDULE_HWS="hws";
            static final String ADD_JSON_SCHEDULE_MONTH="month";
            static final String ADD_JSON_SCHEDULE_DAY="day";
            static final String ADD_JSON_SCHEDULE_TITLE="title";
            static final String ADD_JSON_SCHEDULE_TOPIC="topic";
            static final String ADD_JSON_SCHEDULE_LINK="link";
            static final String ADD_JSON_SCHEDULE_TIME="time";
            static final String ADD_JSON_SCHEDULE_CRITERIA="criteria";
     
        //officeHoursGridData.json
            
     static final String ADD_JSON_OFFICE_STARTHOUR="startHour";
     static final String ADD_JSON_OFFICE_ENDHOUR="endHour";
     static final String ADD_JSON_OFFICE_OFFICEHOURS="officeHours";
             static final String ADD_JSON_OFFICE_DAY="day";
             static final String  ADD_JSON_OFFICE_TIME="time";
             static final String ADD_JSON_OFFICE_NAME="name";
     static final String ADD_JSON_OFFICE_UNDERGRAD="undergrad_tas";
             static final String ADD_JSON_OFFICE_EMAIL="email";
             
        //recitationsData.json
     static final String ADD_JSON_RECITATION_RECITATIONS="recitations";
          static final String ADD_JSON_RECITATION_SECTION="section";
          static final String ADD_JSON_RECITATION_DAYTIME="day_time";
          static final String ADD_JSON_RECITATION_LOCATION="location";
          static final String ADD_JSON_RECITATION_FIRSTTA="ta_1";
          static final String ADD_JSON_RECITATION_SECONDTA="ta_2";
       
      //teams and student json
      static final String ADD_JSON_TS_TEAMS="teams";
           static final String ADD_JSON_TS_NAME="name";
           static final String ADD_JSON_TS_RED="red";
           static final String ADD_JSON_TS_GREEN="green";
           static final String ADD_JSON_TS_BLUE="blue";
           static final String ADD_JSON_TS_TEXTCOLOR="text_color";
           
      static final String ADD_JSON_TS_STUDENTS="students";
           static final String ADD_JSON_TS_LASTNAME="lastName";
           static final String ADD_JSON_TS_FIRSTNAME="firstName";
           static final String ADD_JSON_TS_TEAM="team";
           static final String ADD_JSON_TS_ROLE="role";
    //common 
    static final String TEST_EXPORT_PATH="..\\\\CSG\\\\work\\\\SiteSaveTestExport.json";
      public TestExport(CSGApp initApp){
        app=initApp;
    }
      
       public void exportData(AppDataComponent data,String filePath) throws IOException, ParseException {
	
	CSGData dataManager = (CSGData)data;
        
        //work array
        
        
        
      //holiady array
      //lecture array
      //references array
      //hws array
      JsonArrayBuilder holidaysBuilder = Json.createArrayBuilder();
            JsonArrayBuilder lecturesBuilder = Json.createArrayBuilder();
                  JsonArrayBuilder referencesBuilder = Json.createArrayBuilder();
                        JsonArrayBuilder hwsBuilder = Json.createArrayBuilder();
      
	ObservableList<ScheduleItem> schedules = dataManager.getScheduleItems();
	for (ScheduleItem si : schedules) {
            if (si.getType().equals("HW")){
	    JsonObject hwJson = Json.createObjectBuilder()
		    .add(ADD_JSON_SCHEDULE_MONTH, ""+si.getMonth())
		    .add(ADD_JSON_SCHEDULE_DAY, ""+si.getDay())
		    .add(ADD_JSON_SCHEDULE_TITLE, ""+si.getTitle())
                    .add(ADD_JSON_SCHEDULE_TOPIC, ""+si.getTopic())
                    .add(ADD_JSON_SCHEDULE_LINK, ""+si.getLink())
                    .add(ADD_JSON_SCHEDULE_CRITERIA, ""+si.getCriteria())
                    .build();
	    hwsBuilder.add(hwJson);
	  }else if(si.getType().equals("Holiday")){
               JsonObject holidayJson = Json.createObjectBuilder()
		    .add(ADD_JSON_SCHEDULE_MONTH, ""+si.getMonth())
		    .add(ADD_JSON_SCHEDULE_DAY, ""+si.getDay())
		    .add(ADD_JSON_SCHEDULE_TITLE, ""+si.getTitle())
                    .add(ADD_JSON_SCHEDULE_TOPIC, ""+si.getTopic())
                    .add(ADD_JSON_SCHEDULE_LINK, ""+si.getLink())
                    .add(ADD_JSON_SCHEDULE_CRITERIA, ""+si.getCriteria())
                    .build();
	    holidaysBuilder.add(holidayJson);
              
          }else if(si.getType().equals("Lecture")){
               JsonObject lectureJson = Json.createObjectBuilder()
		    .add(ADD_JSON_SCHEDULE_MONTH, ""+si.getMonth())
		    .add(ADD_JSON_SCHEDULE_DAY, ""+si.getDay())
		    .add(ADD_JSON_SCHEDULE_TITLE, ""+si.getTitle())
                    .add(ADD_JSON_SCHEDULE_TOPIC, ""+si.getTopic())
                    .add(ADD_JSON_SCHEDULE_LINK, ""+si.getLink())
                    .add(ADD_JSON_SCHEDULE_CRITERIA, ""+si.getCriteria())
                    .build();
	    lecturesBuilder.add(lectureJson);
          }else if(si.getType().equals("Reference")){
               JsonObject referenceJson = Json.createObjectBuilder()
		    .add(ADD_JSON_SCHEDULE_MONTH, ""+si.getMonth())
		    .add(ADD_JSON_SCHEDULE_DAY, ""+si.getDay())
		    .add(ADD_JSON_SCHEDULE_TITLE, ""+si.getTitle())
                    .add(ADD_JSON_SCHEDULE_TOPIC, ""+si.getTopic())
                    .add(ADD_JSON_SCHEDULE_LINK, ""+si.getLink())
                    .add(ADD_JSON_SCHEDULE_CRITERIA, ""+si.getCriteria())
                    .build();
	    referencesBuilder.add(referenceJson);
          }
        }
        JsonArray holidayArray = holidaysBuilder.build();
        JsonArray lectureArray = lecturesBuilder.build();
        JsonArray referenceArray = referencesBuilder.build();
	JsonArray hwArray = hwsBuilder.build();
      
      /////////////////////////////////////////
        
        
        
        
       JsonArrayBuilder projectArrayBuilder = Json.createArrayBuilder();
	ObservableList<Team> projectteams = dataManager.getTeams();
	for (Team team : projectteams) {	
            JsonArray studentArray=makeStudentArray(dataManager,team);
            
	    JsonObject projectteamJson = Json.createObjectBuilder()
		    .add(ADD_JSON_PROJECT_NAME, team.getTeamname())
		    .add(ADD_JSON_PROJECT_STUDENT, studentArray)
                    .add(ADD_JSON_PROJECT_LINK,team.getLink())
                    .build();
	    projectArrayBuilder.add(projectteamJson);
	}
	JsonArray projectArray = projectArrayBuilder.build();
      
        
        
         JsonArrayBuilder workArrayBuilder = Json.createArrayBuilder();
	
		JsonObject teamJson = Json.createObjectBuilder()
		    .add(ADD_JSON_PROJECT_SEMESTER, dataManager.getSemester()+" "+dataManager.getYear())
		    .add(ADD_JSON_PROJECT_PROJECT, projectArray)
                    .build();
	    workArrayBuilder.add(teamJson);
	
	JsonArray workArray = workArrayBuilder.build();
//        
      
      
      
      //office hour array
      JsonArrayBuilder timeSlotArrayBuilder = Json.createArrayBuilder();
	ArrayList<CSGTimeSlot> officeHours = CSGTimeSlot.buildOfficeHoursList(dataManager);
	for (CSGTimeSlot ts : officeHours) {	    
	    JsonObject tsJson = Json.createObjectBuilder()
		    .add(ADD_JSON_OFFICE_DAY, ts.getDay())
		    .add(ADD_JSON_OFFICE_TIME, ts.getTime())
		    .add(ADD_JSON_OFFICE_NAME, ts.getName()).build();
	    timeSlotArrayBuilder.add(tsJson);
	}
	JsonArray officeHourArray = timeSlotArrayBuilder.build();
      //underta array
	
	
      JsonArrayBuilder taArrayBuilder = Json.createArrayBuilder();
	ObservableList<TeachingAssistant> tas = dataManager.getTeachingAssistants();
	for (TeachingAssistant ta : tas) {	
            
            
	    JsonObject taJson = Json.createObjectBuilder()
		    .add(JSON_NAME, ta.getName())
		    .add(JSON_EMAIL, ta.getEmail())
                    .add(JSON_UNDERGRAD_TAS,ta.getUnder()).build();
	    taArrayBuilder.add(taJson);
	}
	JsonArray underTaArray = taArrayBuilder.build();
       
      //recitation array
      
       JsonArrayBuilder recitationArrayBuilder = Json.createArrayBuilder();
	ObservableList<Recitation> recitations = dataManager.getRecitations();
	for (Recitation re : recitations) {	
            
            
	    JsonObject recitationJson = Json.createObjectBuilder()
		    .add(ADD_JSON_RECITATION_SECTION, re.getSection())
		    .add(ADD_JSON_RECITATION_DAYTIME, re.getDaytime())
                    .add(ADD_JSON_RECITATION_LOCATION,re.getLocation())
                    .add(ADD_JSON_RECITATION_FIRSTTA, re.getFirstTa())
                    .add(ADD_JSON_RECITATION_SECONDTA, re.getSecondTa())
                    .build();
	    recitationArrayBuilder.add(recitationJson);
	}
	JsonArray recitationArray = recitationArrayBuilder.build();
      
      //teams array
      
         JsonArrayBuilder teamsArrayBuilder = Json.createArrayBuilder();
	ObservableList<Team> teams = dataManager.getTeams();
	for (Team te : teams) {	
            
            
	    JsonObject teamsJson = Json.createObjectBuilder()
		    .add(ADD_JSON_TS_NAME, te.getTeamname())
		    .add(ADD_JSON_TS_RED, ""+te.getRed())
                    .add(ADD_JSON_TS_GREEN,""+te.getGreen())
                    .add(ADD_JSON_TS_BLUE, ""+te.getBlue())
                    .add(ADD_JSON_TS_TEXTCOLOR, te.getTextcolor())
                    .build();
	    teamsArrayBuilder.add(teamsJson);
	}
	JsonArray teamsArray = teamsArrayBuilder.build();
      
      //teamStudent array
      
      
          JsonArrayBuilder teamStudentsArrayBuilder = Json.createArrayBuilder();
	ObservableList<Student> students = dataManager.getStudents();
	for (Student st : students) {	
            
            
	    JsonObject teamStudentsJson = Json.createObjectBuilder()
		    .add(ADD_JSON_TS_LASTNAME, st.getLastName())
		    .add(ADD_JSON_TS_FIRSTNAME, st.getFirstName())
                    .add(ADD_JSON_TS_TEAM,st.getTeamString())
                    .add(ADD_JSON_TS_ROLE, st.getRole())
              
                    .build();
	    teamStudentsArrayBuilder.add(teamStudentsJson);
	}
	JsonArray teamStudentsArray = teamStudentsArrayBuilder.build();
      
      
      
        
        
        
      
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
               
               //projectsData
                .add(ADD_JSON_PROJECT_WORK,workArray)
               //scheduleData
               .add(ADD_JSON_SCHEDULE_STARTING_MONDAYMONTH,""+dataManager.getStartMonth() )
               .add(ADD_JSON_SCHEDULE_STARTING_MONDAYDAY, ""+dataManager.getStartDate())
               .add(ADD_JSON_SCHEDULE_ENDING_FRIDAYMONTH, ""+dataManager.getEndMonth())
               .add(ADD_JSON_SCHEDULE_ENDING_FRIDAYDAY,""+dataManager.getEndDate())
               .add(ADD_JSON_SCHEDULE_HOLIDAYS,holidayArray)
               .add(ADD_JSON_SCHEDULE_LECTURES,lectureArray)
               .add(ADD_JSON_SCHEDULE_REFERENCES,referenceArray)
               .add(ADD_JSON_SCHEDULE_HWS,hwArray)
               
               //officeData
               .add(ADD_JSON_OFFICE_STARTHOUR,"" + dataManager.getStartHour())
               .add(ADD_JSON_OFFICE_ENDHOUR, "" + dataManager.getEndHour())
               .add(ADD_JSON_OFFICE_OFFICEHOURS,officeHourArray)
               .add(ADD_JSON_OFFICE_UNDERGRAD,underTaArray)
               
               //recitation
               .add(ADD_JSON_RECITATION_RECITATIONS, recitationArray)
               
               //teams and student
               
               .add(ADD_JSON_TS_TEAMS, teamsArray)
               .add(ADD_JSON_TS_STUDENTS,teamStudentsArray)
               
               
               .build();
       
       
       
       
       Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(dataManagerJSO);
	jsonWriter.close();
 
	
	//OutputStream os = new FileOutputStream(filePath);
        OutputStream os = new FileOutputStream(TEST_EXPORT_PATH);//////////////////////////////////////////
	JsonWriter jsonFileWriter = Json.createWriter(os);
        
	jsonFileWriter.writeObject(dataManagerJSO);
       
	String prettyPrinted = sw.toString();
    
	//PrintWriter pw = new PrintWriter(filePath);
        PrintWriter pw = new PrintWriter(TEST_EXPORT_PATH);/////////////////////////////////////////////
	pw.write(prettyPrinted);
	pw.close();
       
       }
       
       public JsonArray makeStudentArray(CSGData dataManager,Team temp){
           
       JsonArrayBuilder studentArrayBuilder = Json.createArrayBuilder();
	ArrayList<String> teams = temp.getStudentList();
	for (String ts : teams) {	    
	    JsonObject studentJson = Json.createObjectBuilder()
		    .add("", ts)
		    .build();
	    studentArrayBuilder.add(studentJson);
	}
	JsonArray studentArray = studentArrayBuilder.build();
           
           
         return studentArray;
           
       }
      
}
