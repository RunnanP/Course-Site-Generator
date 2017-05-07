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
import static csg.file.CSGFiles.JSON_COURSE_JSHOME;
import static csg.file.CSGFiles.JSON_COURSE_NUMBER;
import static csg.file.CSGFiles.JSON_COURSE_SUBJECT;
import static csg.file.CSGFiles.*;
import djf.components.AppDataComponent;
import static djf.settings.AppStartupConstants.ENGLISH_APP_PROPERTIES_FILE_NAME;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author runnan
 */
public class CSGFilesJunitTest {
    
    public CSGFilesJunitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }


    @Test
    public void testLoadDataTest() throws Exception {
         System.out.println("loadData");
        CSGApp app=new CSGApp();
        
       app.loadProperties(ENGLISH_APP_PROPERTIES_FILE_NAME);
        app.setDataComponent(app);
       
        AppDataComponent data = app.getDataComponent();
        String filePath = "..\\\\CSG\\\\work\\\\SiteSaveTest.json";
        CSGFiles instance = new CSGFiles(app);
        
        
        //loadJSONFile
         InputStream is = new FileInputStream(filePath);
	JsonReader jsonReader = Json.createReader(is);
	JsonObject json = jsonReader.readObject();
	jsonReader.close();
	is.close();
        
       instance.loadDataTest(data, filePath);
       CSGData testData=(CSGData)data;
       
       
        assertEquals(json.getString(JSON_COURSE_SUBJECT), testData.getSubjectTest());
        assertEquals( json.getString(JSON_COURSE_NUMBER), String.valueOf(testData.getNumberTest()));
        assertEquals(json.getString(JSON_COURSE_SEMESTER), testData.getSemesterTest());
        assertEquals(json.getString(JSON_COURSE_YEAR), String.valueOf(testData.getYearTest()));
        assertEquals(json.getString(JSON_COURSE_TITLE), testData.getTitleTest());
        assertEquals(json.getString(JSON_COURSE_INSTRUCTOR_NAME), testData.getInstructorNameTest());
        assertEquals(json.getString(JSON_COURSE_INSTRUCTOR_HOME), testData.getInstructorHomeTest());
    
        assertEquals(json.getString(JSON_COURSE_EXPORT_DIR),testData.getExportDirTest());
        assertEquals(json.getString(JSON_COURSE_TEMPLATE_DIR), testData.getSiteTempleDirTest());
     
        assertEquals( json.getBoolean(JSON_COURSE_JSHOME),testData.isJhome());
        assertEquals( json.getBoolean(JSON_COURSE_JSSYLLABUS),testData.isJsyllabus());
        assertEquals( json.getBoolean(JSON_COURSE_JSSCHEDULE),testData.isJschedule());
        assertEquals( json.getBoolean(JSON_COURSE_JSHWS),testData.isJhws());
        assertEquals( json.getBoolean(JSON_COURSE_JSPROJECTS),testData.isJproject());
        
        
        
        
           ArrayList<TeachingAssistant> testTAarray=new ArrayList<>();
          JsonArray jsonTAArray = json.getJsonArray(JSON_TAS);
          for (int i = 0; i < jsonTAArray.size(); i++) {
            JsonObject jsonTA = jsonTAArray.getJsonObject(i);
            String name = jsonTA.getString(JSON_NAME);
            String email = jsonTA.getString(JSON_EMAIL);
            System.out.println(jsonTA.getString(JSON_UNDERGRAD_TAS));
            
           Boolean underGrad=Boolean.parseBoolean(jsonTA.getString(JSON_UNDERGRAD_TAS));
           TeachingAssistant ta=new TeachingAssistant(name, email,underGrad);
           testTAarray.add(ta);
        }
        
        
        
         assertEquals(testTAarray.get(0).getName(),testData.getTeachingAssistants().get(0).getName());
         assertEquals(testTAarray.get(0).getEmail(),testData.getTeachingAssistants().get(0).getEmail());
         assertEquals(testTAarray.get(0).getUnder(),testData.getTeachingAssistants().get(0).getUnder());
         
//         assertEquals( "Joe Shmo",testData.getTeachingAssistants().get(1).getName());
//         assertEquals( "joe.shmo@yale.edu",testData.getTeachingAssistants().get(1).getEmail());
//         assertEquals( "false",testData.getTeachingAssistants().get(1).getUnder());
         
         
         
        ArrayList<CSGTimeSlot> testOfficeHousArray=new ArrayList<>();
            JsonArray jsonOfficeHoursArray = json.getJsonArray(JSON_OFFICE_HOURS);
        for (int i = 0; i < jsonOfficeHoursArray.size(); i++) {
            JsonObject jsonOfficeHours = jsonOfficeHoursArray.getJsonObject(i);
            String day = jsonOfficeHours.getString(JSON_DAY);
            String time = jsonOfficeHours.getString(JSON_TIME);
            String name = jsonOfficeHours.getString(JSON_NAME);
           testOfficeHousArray.add(new CSGTimeSlot(day, time, name));
        }
  
       assertEquals(testOfficeHousArray.get(0).getName(), testData.getOfficeHoursTest().get(testData.getCellKeyTest( testOfficeHousArray.get(0).getDay(), testOfficeHousArray.get(0).getTime())));

         
//     assertEquals("Jane Dow", testData.getOfficeHoursTest().get(testData.getCellKeyTest("WEDNESDAY", "7_00pm")));
//
//////         
//     assertEquals("Joe Shmo", testData.getOfficeHoursTest().get(testData.getCellKeyTest("TUESDAY", "12_00pm")));
//
//////         
////
//    assertEquals("Joe Shmo", testData.getOfficeHoursTest().get(testData.getCellKeyTest("MONDAY", "5_00pm")));



      
         
                 ArrayList<Recitation> testrecitationArray=new ArrayList<>();
           JsonArray jsonRecitionArray=json.getJsonArray(JSON_RECITATION);
        for (int i=0;i<jsonRecitionArray.size();i++){
            JsonObject jsonRecitation=jsonRecitionArray.getJsonObject(i);
            String initsection=jsonRecitation.getString(JSON_RECITATION_SECTION);
            String initinstructor=jsonRecitation.getString(JSON_RECITATION_INSTRUCTOR);
            String initdaytime=jsonRecitation.getString(JSON_RECITATION_DAYTIME);
            String initlocation=jsonRecitation.getString(JSON_RECITATION_LOCATION);
            String initfirstTA=jsonRecitation.getString(JSON_RECITATION_FIRST_TA);
            String initsecondTA=jsonRecitation.getString(JSON_RECITATION_SECOND_TA);
           testrecitationArray.add(new Recitation(initsection,initinstructor,initdaytime,initlocation,initfirstTA,initsecondTA));
        }
      
        assertEquals( testrecitationArray.get(0).getSection(), testData.getRecitations().get(0).getSection());
        assertEquals(testrecitationArray.get(0).getInstructor(), testData.getRecitations().get(0).getInstructor());
         assertEquals( testrecitationArray.get(0).getDaytime(), testData.getRecitations().get(0).getDaytime());
        assertEquals(testrecitationArray.get(0).getLocation(), testData.getRecitations().get(0).getLocation());
         assertEquals(testrecitationArray.get(0).getFirstTa(), testData.getRecitations().get(0).getFirstTa());
        assertEquals(testrecitationArray.get(0).getSecondTa(), testData.getRecitations().get(0).getSecondTa());
        
//           assertEquals("R05", testData.getRecitations().get(1).getSection());
//        assertEquals("Banerjee", testData.getRecitations().get(1).getInstructor());
//         assertEquals("Tues 5:30pm-6:23pm", testData.getRecitations().get(1).getDaytime());
//        assertEquals("Old CS 2114", testData.getRecitations().get(1).getLocation());
//         assertEquals("pol", testData.getRecitations().get(1).getFirstTa());
//        assertEquals( "amy", testData.getRecitations().get(1).getSecondTa());
//        
        
        
        assertEquals(json.getString(JSON_SCHEDULE_CALENDAR_STARTING), testData.getStartingDateTest());
        assertEquals(json.getString(JSON_SCHEDULE_CALENDAR_ENDING), testData.getEndingDateTest());
        
        
        
     
        
        
             ArrayList<ScheduleItem>  testscheduleItemArray=new ArrayList<>();
         JsonArray jsonScheduleArray=json.getJsonArray(JSON_SCHEDULE);
          for (int i=0;i<jsonScheduleArray.size();i++){
            JsonObject jsonSchedule=jsonScheduleArray.getJsonObject(i);
            String inittype=jsonSchedule.getString(JSON_SCHEDULE_TYPE);
            String initdate=jsonSchedule.getString(JSON_SCHEDULE_DATE);
            String inittime=jsonSchedule.getString(JSON_SCHEDULE_TIME);
            String inittitle=jsonSchedule.getString(JSON_SCHEDULE_TITLE);
            String inittopic=jsonSchedule.getString(JSON_SCHEDULE_TOPIC);
             String initLink=jsonSchedule.getString(JSON_SCHEDULE_LINK);
            String initCriteria=jsonSchedule.getString(JSON_SCHEDULE_CRITERIA);
     
        testscheduleItemArray.add(new ScheduleItem(inittype, initdate, inittime, inittitle, inittopic, initLink, initCriteria));
        }
        
        assertEquals( testscheduleItemArray.get(0).getType(), testData.getScheduleItems().get(0).getType());
        assertEquals(testscheduleItemArray.get(0).getDate(), testData.getScheduleItems().get(0).getDate());
        assertEquals( testscheduleItemArray.get(0).getTime(), testData.getScheduleItems().get(0).getTime());
        assertEquals( testscheduleItemArray.get(0).getTitle(), testData.getScheduleItems().get(0).getTitle());
        assertEquals(  testscheduleItemArray.get(0).getTopic(), testData.getScheduleItems().get(0).getTopic());
        assertEquals(  testscheduleItemArray.get(0).getLink(), testData.getScheduleItems().get(0).getLink());
        assertEquals( testscheduleItemArray.get(0).getCriteria(), testData.getScheduleItems().get(0).getCriteria());
        
        
//          assertEquals("HW", testData.getScheduleItems().get(1).getType());
//        assertEquals("2017-04-27", testData.getScheduleItems().get(1).getDate());
//        assertEquals("8:00", testData.getScheduleItems().get(1).getTime());
//        assertEquals("hw1", testData.getScheduleItems().get(1).getTitle());
//        assertEquals( "game play", testData.getScheduleItems().get(1).getTopic());
//        assertEquals( "www.google.com", testData.getScheduleItems().get(1).getLink());
//        assertEquals( "nothing", testData.getScheduleItems().get(1).getCriteria());
        
       
        
        
        



         ArrayList<Team>  testteamArray=new ArrayList<>();
           JsonArray jsonTeamArray=json.getJsonArray(JSON_TEAM);
          for (int i=0;i<jsonTeamArray.size();i++){
            JsonObject jsonTeam=jsonTeamArray.getJsonObject(i);
            String initname=jsonTeam.getString(JSON_PROJECT_TEAM_NAME);
            String initcolor=jsonTeam.getString(JSON_PROJECT_TEAM_COLOR);
            String inittextcolor=jsonTeam.getString(JSON_PROJECT_TEAM_TEXTCOLOR);
            String initlink=jsonTeam.getString(JSON_PROJECT_TEAM_LINK);
            
              testteamArray.add(new Team(initname,initcolor,inittextcolor,initlink));
        }
          
     
         assertEquals(testteamArray.get(0).getTeamname() ,testData.getTeams().get(0).getTeamname());
        assertEquals(testteamArray.get(0).getColor(), testData.getTeams().get(0).getColor());
         assertEquals(testteamArray.get(0).getTextcolor(),  testData.getTeams().get(0).getTextcolor());
        assertEquals(testteamArray.get(0).getLink(), testData.getTeams().get(0).getLink());
        
//          assertEquals("C4 Comics" ,testData.getTeams().get(1).getTeamname());
//        assertEquals("235399", testData.getTeams().get(1).getColor());
//         assertEquals("ffffff",  testData.getTeams().get(1).getTextcolor());
//        assertEquals("http://c4-comics.com", testData.getTeams().get(1).getLink());
//        
        
      


             ArrayList<Student>  studentArray=new ArrayList<>();
           JsonArray jsonStudentArray=json.getJsonArray(JSON_STUDENT);
          for (int i=0;i<jsonStudentArray.size();i++){
            JsonObject jsonStudent=jsonStudentArray.getJsonObject(i);
            String initFirstname=jsonStudent.getString(JSON_PROJECT_STUDENT_FIRSTNAME);
            String initLastname=jsonStudent.getString(JSON_PROJECT_STUDENT_LASTNAME);
            String initTeam=jsonStudent.getString(JSON_PROJECT_STUDENT_TEAM);
            String initRole=jsonStudent.getString(JSON_PROJECT_STUDENT_ROLE);
        studentArray.add(new Student(initFirstname, initLastname, initTeam, initRole));

    }
      
         assertEquals(studentArray.get(0).getFirstName(), testData.getStudents().get(0).getFirstName());
     assertEquals(studentArray.get(0).getLastName(), testData.getStudents().get(0).getLastName());
      assertEquals(studentArray.get(0).getTeamString(), testData.getStudents().get(0).getTeamString());
     assertEquals( studentArray.get(0).getRole(), testData.getStudents().get(0).getRole());
     
        
     
//        assertEquals("Jane", testData.getStudents().get(1).getFirstName());
//     assertEquals( "Doe", testData.getStudents().get(1).getLastName());
//      assertEquals("C4 Comics", testData.getStudents().get(1).getTeamString());
//     assertEquals( "Lead Programmer",testData.getStudents().get(1).getRole());
      
        
        
        
        
    }


    
}
