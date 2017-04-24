/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.file;

import csg.CSGApp;
import csg.data.CSGData;
import csg.data.Recitation;
import csg.data.ScheduleItem;
import csg.data.Student;
import csg.data.TeachingAssistant;
import csg.data.Team;
import csg.workspace.CSGTAWorkspace;
import csg.workspace.CSGWorkspace;
import djf.components.AppDataComponent;
import djf.components.AppFileComponent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import test_bed.TestExport;
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
    
    static final String JSON_SCHEDULE_STARTING_MONDAYMONTH="startingMondayMonth";
    static final String JSON_SCHEDULE_STARTING_MONDAYDAY="startingMondayDay";
    static final String JSON_SCHEDULE_ENDING_FRIDAYMONTH="endingFridayMonth";
    static final String JSON_SCHEDULE_ENDING_FRIDAYDAY="endingFridayDay";
    
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
    //common 
    static final String TEST_PATH="..\\\\CSG\\\\work\\\\SiteSaveTest.json";
    
    
    
   
             
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
    public CSGFiles(CSGApp initApp) {
     app=initApp;
    }

    @Override
    public void saveData(AppDataComponent data, String filePath) throws IOException {
//       TestSave test =new TestSave(app);
//       test.saveData(data, filePath);


CSGData dataManager = (CSGData)data;
      
	// NOW BUILD THE TA JSON OBJCTS TO SAVE
	JsonArrayBuilder taArrayBuilder = Json.createArrayBuilder();
	ObservableList<TeachingAssistant> tas = dataManager.getTeachingAssistants();
	for (TeachingAssistant ta : tas) {	
            
            
	    JsonObject taJson = Json.createObjectBuilder()
		    .add(JSON_NAME, ""+ta.getName())
		    .add(JSON_EMAIL, ""+ta.getEmail())
                    .add(JSON_UNDERGRAD_TAS,""+ta.getUnder()).build();
	    taArrayBuilder.add(taJson);
	}
	JsonArray undergradTAsArray = taArrayBuilder.build();
  
	// NOW BUILD THE TIME SLOT JSON OBJCTS TO SAVE
	JsonArrayBuilder timeSlotArrayBuilder = Json.createArrayBuilder();
	ArrayList<CSGTimeSlot> officeHours = CSGTimeSlot.buildOfficeHoursList(dataManager);
	for (CSGTimeSlot ts : officeHours) {	    
	    JsonObject tsJson = Json.createObjectBuilder()
		    .add(JSON_DAY, ""+ts.getDay())
		    .add(JSON_TIME, ""+ts.getTime())
		    .add(JSON_NAME, ""+ts.getName()).build();
	    timeSlotArrayBuilder.add(tsJson);
	}
	JsonArray timeSlotsArray = timeSlotArrayBuilder.build();
         
        //Recitation part
        
        
        JsonArrayBuilder recitationArrayBuilder=Json.createArrayBuilder();
        ObservableList<Recitation> recitations=dataManager.getRecitations();
        for(Recitation recitation:recitations){
            
            JsonObject recitationJson=Json.createObjectBuilder()
                    .add(JSON_RECITATION_SECTION,""+recitation.getSection())
                    .add(JSON_RECITATION_INSTRUCTOR,""+recitation.getInstructor())
                    .add(JSON_RECITATION_DAYTIME,""+recitation.getDaytime())
                    .add(JSON_RECITATION_LOCATION,""+recitation.getLocation())
                    .add(JSON_RECITATION_FIRST_TA,""+recitation.getFirstTa())
                    .add(JSON_RECITATION_SECOND_TA,""+recitation.getSecondTa()).build();
            recitationArrayBuilder.add(recitationJson);
        }
        JsonArray recitationArray=recitationArrayBuilder.build();
        
        
        //schedule part
        
         JsonArrayBuilder scheduleArrayBuilder=Json.createArrayBuilder();
        ObservableList<ScheduleItem> schedules=dataManager.getScheduleItems();
        for(ScheduleItem scheduleItem:schedules){
            
            JsonObject scheduleJson=Json.createObjectBuilder()
                    .add(JSON_SCHEDULE_TYPE,""+scheduleItem.getType())
                    .add(JSON_SCHEDULE_DATE,""+scheduleItem.getDate())
                    .add(JSON_SCHEDULE_TITLE,""+scheduleItem.getTitle())
                    .add(JSON_SCHEDULE_TOPIC,""+scheduleItem.getTopic()).build();
            scheduleArrayBuilder.add(scheduleJson);
        }
        JsonArray scheduleArray=scheduleArrayBuilder.build();
        
        
        
        //project part
        
           JsonArrayBuilder teamArrayBuilder=Json.createArrayBuilder();
        ObservableList<Team> teams=dataManager.getTeams();
        for(Team team:teams){
            
            JsonObject teamJson=Json.createObjectBuilder()
                    .add(JSON_PROJECT_TEAM_NAME,""+team.getTeamname())
                    .add(JSON_PROJECT_TEAM_COLOR,""+team.getColor())
                    .add(JSON_PROJECT_TEAM_TEXTCOLOR,""+team.getTextcolor())
                    .add(JSON_PROJECT_TEAM_LINK,""+team.getLink()).build();
            teamArrayBuilder.add(teamJson);
        }
        JsonArray teamArray=teamArrayBuilder.build();
        
        
         JsonArrayBuilder studentArrayBuilder=Json.createArrayBuilder();
        ObservableList<Student> students=dataManager.getStudents();
        for(Student student:students){
            
            JsonObject studentJson=Json.createObjectBuilder()
                    .add(JSON_PROJECT_STUDENT_FIRSTNAME,""+student.getFirstName())
                    .add(JSON_PROJECT_STUDENT_LASTNAME,""+student.getLastName())
                    .add(JSON_PROJECT_STUDENT_TEAM,""+student.getTeamString())
                    .add(JSON_PROJECT_STUDENT_ROLE,""+student.getRole()).build();
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
                .add(JSON_SCHEDULE_STARTING_MONDAYMONTH, ""+dataManager.getStartMonth())
                .add(JSON_SCHEDULE_STARTING_MONDAYDAY,""+dataManager.getStartDate())
                .add(JSON_SCHEDULE_ENDING_FRIDAYMONTH,""+dataManager.getEndMonth())
                .add(JSON_SCHEDULE_ENDING_FRIDAYDAY,""+dataManager.getEndDate())
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
	OutputStream os = new FileOutputStream(filePath);
       // OutputStream os = new FileOutputStream(TEST_PATH);//////////////////////////////////////////
	JsonWriter jsonFileWriter = Json.createWriter(os);
        
	jsonFileWriter.writeObject(dataManagerJSO);
       
	String prettyPrinted = sw.toString();
    
	PrintWriter pw = new PrintWriter(filePath);
        //PrintWriter pw = new PrintWriter(TEST_PATH);/////////////////////////////////////////////
	pw.write(prettyPrinted);
	pw.close();
         
    }
    

    @Override
    public void loadData(AppDataComponent data, String filePath) throws IOException {
//        TestLoad test=new TestLoad(app);
//         try {
//             test.loadData(data, filePath);
//         } catch (ParseException ex) {
//             Logger.getLogger(CSGFiles.class.getName()).log(Level.SEVERE, null, ex);
//         }


       CSGData dataManager = (CSGData)data;
        
       
	// LOAD THE JSON FILE WITH ALL THE DATA
	//JsonObject json = loadJSONFile(TEST_PATH);
        JsonObject json = loadJSONFile(filePath);
        
        
        
        
        //course part
        
        String courseSubject=json.getString(JSON_COURSE_SUBJECT);
       
        String courseNumber=json.getString(JSON_COURSE_NUMBER);
        String courseSemster=json.getString(JSON_COURSE_SEMESTER);
        String courseYear=json.getString(JSON_COURSE_YEAR);
        String courseTitle=json.getString(JSON_COURSE_TITLE);
        String courseInstructorName=json.getString(JSON_COURSE_INSTRUCTOR_NAME);
        String courseInstructorHome=json.getString(JSON_COURSE_INSTRUCTOR_HOME);
          dataManager.initCourseInfo(courseSubject,courseNumber,courseSemster,courseYear,courseTitle,courseInstructorName,courseInstructorHome);
        
          
          
        String styleSheet=json.getString(JSON_COURSE_STYTLE_SHEET);
        dataManager.setStyleSheet(""+styleSheet);
          
       
        
        String exportDir=json.getString(JSON_COURSE_EXPORT_DIR);
        dataManager.setExportDir(""+exportDir);
         
          
          
        String templeDir=json.getString(JSON_COURSE_TEMPLATE_DIR);
        dataManager.setSiteTempleDir(""+templeDir);
        
        String firstA=json.getString(JSON_COURSE_FIRST_IMAGE_ADDRESS);
        dataManager.setFirstImageAdd(""+firstA);
        
        String secondA=json.getString(JSON_COURSE_SECOND_IMAGE_ADDRESS);
        dataManager.setSecondImageAdd(""+secondA);
        
        String thirdA=json.getString(JSON_COURSE_THIRD_IMAGE_ADDRESS);
        dataManager.setThirdImageAdd(""+thirdA);
//dataManager.setStyleSheet(styleSheet);
//        Boolean courseJhome=Boolean.parseBoolean(json.getString(JSON_COURSE_JSHOME));
//        Boolean courseJsyllabus=Boolean.parseBoolean(json.getString(JSON_COURSE_JSSYLLABUS));
//        Boolean courseJschedule=Boolean.parseBoolean(json.getString(JSON_COURSE_JSSCHEDULE));
//        Boolean courseJhws=Boolean.parseBoolean(json.getString(JSON_COURSE_JSHWS));
//        Boolean courseJproject=Boolean.parseBoolean(json.getString(JSON_COURSE_JSPROJECTS));
       Boolean courseJhome=json.getBoolean(JSON_COURSE_JSHOME);
        Boolean courseJsyllabus=json.getBoolean(JSON_COURSE_JSSYLLABUS);
        Boolean courseJschedule=json.getBoolean(JSON_COURSE_JSSCHEDULE);
        Boolean courseJhws=json.getBoolean(JSON_COURSE_JSHWS);
        Boolean courseJproject=json.getBoolean(JSON_COURSE_JSPROJECTS);
        dataManager.setJhome(courseJhome);
        dataManager.setJsyllabus(courseJsyllabus);
        dataManager.setJschedule(courseJschedule);
        dataManager.setJhws(courseJhws);
        dataManager.setJproject(courseJproject);
        
        
       
                
         //ta part/////////////////////////////////////////////////////////////////////////////
	// LOAD THE START AND END HOURS
	String startHour = json.getString(JSON_START_HOUR);
        String endHour = json.getString(JSON_END_HOUR);

         dataManager.initHours(""+startHour, ""+endHour);

        // NOW RELOAD THE WORKSPACE WITH THE LOADED DATA
//        app.getWorkspaceComponent().reloadWorkspace(app.getDataComponent());
//        CSGWorkspace temp=(CSGWorkspace) app.getWorkspaceComponent();
//        CSGTAWorkspace workspace=temp.getCsgTAWorkspace();



       // workspace.clearCombobox();
        ///////////////
        // NOW LOAD ALL THE UNDERGRAD TAs
        JsonArray jsonTAArray = json.getJsonArray(JSON_TAS);
        for (int i = 0; i < jsonTAArray.size(); i++) {
            JsonObject jsonTA = jsonTAArray.getJsonObject(i);
            String name = jsonTA.getString(JSON_NAME);
            String email = jsonTA.getString(JSON_EMAIL);
            System.out.println(jsonTA.getString(JSON_UNDERGRAD_TAS));
            
           Boolean underGrad=Boolean.parseBoolean(jsonTA.getString(JSON_UNDERGRAD_TAS));
           
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
        String scheduleCalendarStarting=json.getString(JSON_SCHEDULE_CALENDAR_STARTING);
         String scheduleCalendarEnding=json.getString(JSON_SCHEDULE_CALENDAR_ENDING);
         try {
             dataManager.initCalendar(scheduleCalendarStarting,scheduleCalendarEnding);
         } catch (ParseException ex) {
             Logger.getLogger(CSGFiles.class.getName()).log(Level.SEVERE, null, ex);
         }
             
        
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
    }
       private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
	InputStream is = new FileInputStream(jsonFilePath);
	JsonReader jsonReader = Json.createReader(is);
	JsonObject json = jsonReader.readObject();
	jsonReader.close();
	is.close();
	return json;
    }

    @Override
    public void exportData(AppDataComponent data, String filePath) throws IOException,ParseException {
//           TestExport test =new TestExport(app);
//         try {
//             test.exportData(data, filePath);
//         } catch (ParseException ex) {
//             Logger.getLogger(CSGFiles.class.getName()).log(Level.SEVERE, null, ex);
//         }


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
		    .add(ADD_JSON_PROJECT_NAME, ""+team.getTeamname())
		    .add(ADD_JSON_PROJECT_STUDENT, studentArray)
                    .add(ADD_JSON_PROJECT_LINK,""+team.getLink())
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
		    .add(ADD_JSON_OFFICE_DAY, ""+ts.getDay())
		    .add(ADD_JSON_OFFICE_TIME, ""+ts.getTime())
		    .add(ADD_JSON_OFFICE_NAME, ""+ts.getName()).build();
	    timeSlotArrayBuilder.add(tsJson);
	}
	JsonArray officeHourArray = timeSlotArrayBuilder.build();
      //underta array
	
	
      JsonArrayBuilder taArrayBuilder = Json.createArrayBuilder();
	ObservableList<TeachingAssistant> tas = dataManager.getTeachingAssistants();
	for (TeachingAssistant ta : tas) {	
            
            
	    JsonObject taJson = Json.createObjectBuilder()
		    .add(JSON_NAME, ""+ta.getName())
		    .add(JSON_EMAIL, ""+ta.getEmail())
                    .add(JSON_UNDERGRAD_TAS,""+ta.getUnder()).build();
	    taArrayBuilder.add(taJson);
	}
	JsonArray underTaArray = taArrayBuilder.build();
       
      //recitation array
      
       JsonArrayBuilder recitationArrayBuilder = Json.createArrayBuilder();
	ObservableList<Recitation> recitations = dataManager.getRecitations();
	for (Recitation re : recitations) {	
            
            
	    JsonObject recitationJson = Json.createObjectBuilder()
		    .add(ADD_JSON_RECITATION_SECTION, ""+re.getSection())
		    .add(ADD_JSON_RECITATION_DAYTIME, ""+re.getDaytime())
                    .add(ADD_JSON_RECITATION_LOCATION,""+re.getLocation())
                    .add(ADD_JSON_RECITATION_FIRSTTA, ""+re.getFirstTa())
                    .add(ADD_JSON_RECITATION_SECONDTA, ""+re.getSecondTa())
                    .build();
	    recitationArrayBuilder.add(recitationJson);
	}
	JsonArray recitationArray = recitationArrayBuilder.build();
      
      //teams array
      
         JsonArrayBuilder teamsArrayBuilder = Json.createArrayBuilder();
	ObservableList<Team> teams = dataManager.getTeams();
	for (Team te : teams) {	
            
            
	    JsonObject teamsJson = Json.createObjectBuilder()
		    .add(ADD_JSON_TS_NAME, ""+te.getTeamname())
		    .add(ADD_JSON_TS_RED, ""+te.getRed())
                    .add(ADD_JSON_TS_GREEN,""+te.getGreen())
                    .add(ADD_JSON_TS_BLUE, ""+te.getBlue())
                    .add(ADD_JSON_TS_TEXTCOLOR, ""+te.getTextcolor())
                    .build();
	    teamsArrayBuilder.add(teamsJson);
	}
	JsonArray teamsArray = teamsArrayBuilder.build();
      
      //teamStudent array
      
      
          JsonArrayBuilder teamStudentsArrayBuilder = Json.createArrayBuilder();
	ObservableList<Student> students = dataManager.getStudents();
	for (Student st : students) {	
            
            
	    JsonObject teamStudentsJson = Json.createObjectBuilder()
		    .add(ADD_JSON_TS_LASTNAME, ""+st.getLastName())
		    .add(ADD_JSON_TS_FIRSTNAME, ""+st.getFirstName())
                    .add(ADD_JSON_TS_TEAM,""+st.getTeamString())
                    .add(ADD_JSON_TS_ROLE, ""+st.getRole())
              
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
 
	
	OutputStream os = new FileOutputStream(filePath);
        //OutputStream os = new FileOutputStream(TEST_EXPORT_PATH);//////////////////////////////////////////
	JsonWriter jsonFileWriter = Json.createWriter(os);
        
	jsonFileWriter.writeObject(dataManagerJSO);
       
	String prettyPrinted = sw.toString();
    
	PrintWriter pw = new PrintWriter(filePath);
        //PrintWriter pw = new PrintWriter(TEST_EXPORT_PATH);/////////////////////////////////////////////
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
      

    @Override
    public void importData(AppDataComponent data, String filePath) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
