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

import static csg.file.CSGFiles.*;
import csg.workspace.CSGWorkspace;
import djf.components.AppDataComponent;
import static djf.settings.AppStartupConstants.*;
import static djf.settings.AppStartupConstants.ENGLISH_APP_PROPERTIES_FILE_NAME;
import static djf.settings.AppPropertyType.*;
import static djf.settings.AppStartupConstants.*;
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
import org.junit.Ignore;


/**
 *
 * @author runnan
 */
public class CSGFilesTest {
    
    
    
    public CSGFilesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of saveData method, of class CSGFiles.
     */
//    @Ignore
//    @Test
//    public void testSaveData() throws Exception {
//        System.out.println("saveData");
//        AppDataComponent data = null;
//        String filePath = "..\\\\\\\\CSG\\\\\\\\work\\\\\\\\SiteSaveTest.json";
//        CSGFiles instance = null;
//        instance.saveData(data, filePath);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of loadData method, of class CSGFiles.
     */
    @Test
    public void testLoadData() throws Exception {
        System.out.println("loadData");
        CSGApp app=new CSGApp();
        
       app.loadProperties(ENGLISH_APP_PROPERTIES_FILE_NAME);
        app.setDataComponent(app);
       // app.setWorkspaceComponent(app);
       
        AppDataComponent data = app.getDataComponent();
        String filePath = "..\\\\CSG\\\\work\\\\SiteSaveTest.json";
        CSGFiles instance = new CSGFiles(app);
        
        
        //loadJSONFile
         InputStream is = new FileInputStream(filePath);
	JsonReader jsonReader = Json.createReader(is);
	JsonObject json = jsonReader.readObject();
	jsonReader.close();
	is.close();



       // instance.loadDataTest(data, filePath);
        assertEquals("CSE", json.getString(JSON_COURSE_SUBJECT));
        assertEquals("219", json.getString(JSON_COURSE_NUMBER));
        assertEquals("Spring", json.getString(JSON_COURSE_SEMESTER));
        assertEquals("2017", json.getString(JSON_COURSE_YEAR));
        assertEquals("Computer Science III", json.getString(JSON_COURSE_TITLE));
        assertEquals("Ritwik Banerjee", json.getString(JSON_COURSE_INSTRUCTOR_NAME));
        assertEquals("http://www3.cs.stonybrook.edu/~rbanerjee/", json.getString(JSON_COURSE_INSTRUCTOR_HOME));
       // assertEquals("../CSE219/public", json.getString(JSON_COURSE_STYTLE_SHEET));
        assertEquals("../CSE219/public", json.getString(JSON_COURSE_EXPORT_DIR));
        assertEquals("./templates/CSE219", json.getString(JSON_COURSE_TEMPLATE_DIR));
      //  assertEquals("CSE", json.getString(JSON_COURSE_FIRST_IMAGE_ADDRESS));
       // assertEquals("CSE", json.getString(JSON_COURSE_SECOND_IMAGE_ADDRESS));
       // assertEquals("CSE", json.getString(JSON_COURSE_THIRD_IMAGE_ADDRESS));
        assertTrue( json.getBoolean(JSON_COURSE_JSHOME));
        assertTrue( json.getBoolean(JSON_COURSE_JSSYLLABUS));
        assertTrue( json.getBoolean(JSON_COURSE_JSSCHEDULE));
        assertTrue( json.getBoolean(JSON_COURSE_JSHWS));
        assertTrue( json.getBoolean(JSON_COURSE_JSPROJECTS));
        
        
        
        
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
//        ArrayList<TeachingAssistant> expectTAarray=new ArrayList<>();
//        expectTAarray.add(new TeachingAssistant<>("Jane Doe","jane.doe@yahoo.com",true));
//        expectTAarray.add(new TeachingAssistant<>( "Joe Shmo", "joe.shmo@yale.edu",false));
//         assertEquals(new TeachingAssistant<>("Jane Doe","jane.doe@yahoo.com",true),testTAarray.get(0));
//         assertEquals(new TeachingAssistant<>( "Joe Shmo", "joe.shmo@yale.edu",false),testTAarray.get(1));
         
         assertEquals("Jane Doe",testTAarray.get(0).getName());
         assertEquals("jane.doe@yahoo.com",testTAarray.get(0).getEmail());
         assertEquals("true",testTAarray.get(0).getUnder());
         
         assertEquals( "Joe Shmo",testTAarray.get(1).getName());
         assertEquals( "joe.shmo@yale.edu",testTAarray.get(1).getEmail());
         assertEquals( "false",testTAarray.get(1).getUnder());
         
         
         
         
         ArrayList<CSGTimeSlot> testOfficeHousArray=new ArrayList<>();
            JsonArray jsonOfficeHoursArray = json.getJsonArray(JSON_OFFICE_HOURS);
        for (int i = 0; i < jsonOfficeHoursArray.size(); i++) {
            JsonObject jsonOfficeHours = jsonOfficeHoursArray.getJsonObject(i);
            String day = jsonOfficeHours.getString(JSON_DAY);
            String time = jsonOfficeHours.getString(JSON_TIME);
            String name = jsonOfficeHours.getString(JSON_NAME);
           testOfficeHousArray.add(new CSGTimeSlot(day, time, name));
        }
//         assertEquals(new CSGTimeSlot("MONDAY", "2_00am", "Jane Dow"), testOfficeHousArray.get(0));
//         assertEquals(new CSGTimeSlot("WEDNESDAY", "7_00am", "Jane Dow"), testOfficeHousArray.get(1));
//         assertEquals(new CSGTimeSlot("TUESDAY", "12_00pm", "Joe Shmo"), testOfficeHousArray.get(2));
//         assertEquals(new CSGTimeSlot("MONDAY", "8_00am", "Joe Shmo"), testOfficeHousArray.get(3));
//         
          assertEquals("MONDAY", testOfficeHousArray.get(0).getDay());
         assertEquals("11_00am", testOfficeHousArray.get(0).getTime());
         assertEquals("Jane Dow", testOfficeHousArray.get(0).getName());
         
           assertEquals("WEDNESDAY", testOfficeHousArray.get(3).getDay());
         assertEquals("7_00pm", testOfficeHousArray.get(3).getTime());
         assertEquals("Jane Dow", testOfficeHousArray.get(3).getName());
         
           assertEquals("TUESDAY", testOfficeHousArray.get(1).getDay());
         assertEquals("12_00pm", testOfficeHousArray.get(1).getTime());
         assertEquals("Joe Shmo", testOfficeHousArray.get(1).getName());
         
           assertEquals("MONDAY", testOfficeHousArray.get(2).getDay());
         assertEquals("5_00pm", testOfficeHousArray.get(2).getTime());
         assertEquals("Joe Shmo", testOfficeHousArray.get(2).getName());
            


      
         
         
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
         
        
//        assertEquals(new Recitation<>("R02", "McKenna", "Wed 3:30pm-4:23pm", "Old CS 2114", "bill","mike"), testrecitationArray.get(0));
//        assertEquals(new Recitation<>("R05", "Banerjee", "Tues 5:30pm-6:23pm", "Old CS 2114", "pol", "amy"), testrecitationArray.get(1));
        
        assertEquals("R02", testrecitationArray.get(0).getSection());
        assertEquals("McKenna", testrecitationArray.get(0).getInstructor());
         assertEquals( "Wed 3:30pm-4:23pm", testrecitationArray.get(0).getDaytime());
        assertEquals("Old CS 2114", testrecitationArray.get(0).getLocation());
         assertEquals("bill", testrecitationArray.get(0).getFirstTa());
        assertEquals("mike", testrecitationArray.get(0).getSecondTa());
        
           assertEquals("R05", testrecitationArray.get(1).getSection());
        assertEquals("Banerjee", testrecitationArray.get(1).getInstructor());
         assertEquals("Tues 5:30pm-6:23pm", testrecitationArray.get(1).getDaytime());
        assertEquals("Old CS 2114", testrecitationArray.get(1).getLocation());
         assertEquals("pol", testrecitationArray.get(1).getFirstTa());
        assertEquals( "amy", testrecitationArray.get(1).getSecondTa());
        
        
        
        assertEquals("2017-04-03", json.getString(JSON_SCHEDULE_CALENDAR_STARTING));
        assertEquals("2017-05-05", json.getString(JSON_SCHEDULE_CALENDAR_ENDING));
        
        
        
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
        //assertEquals(new ScheduleItem<>("Holiday", "2017-04-08", "whole day", "SNOW DAY", "..", ".."), testscheduleItemArray.get(0));
        assertEquals("Holiday", testscheduleItemArray.get(0).getType());
        assertEquals("2017-04-08", testscheduleItemArray.get(0).getDate());
        assertEquals("whole day", testscheduleItemArray.get(0).getTime());
        assertEquals("SNOW DAY", testscheduleItemArray.get(0).getTitle());
        assertEquals( "..", testscheduleItemArray.get(0).getTopic());
        assertEquals( "..", testscheduleItemArray.get(0).getLink());
        assertEquals( "..", testscheduleItemArray.get(0).getCriteria());
        
        
        
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
          
          
          
//        assertEquals(new Team<>("Atomic Comics", "552211", "ffffff", "http://atomiccomic.com"), testteamArray.get(0));
//        assertEquals(new Team<>("C4 Comics", "235399", "ffffff", "http://c4-comics.com"), testteamArray.get(1));
        
         assertEquals("Atomic Comics" ,testteamArray.get(0).getTeamname());
        assertEquals("552211", testteamArray.get(0).getColor());
         assertEquals("ffffff",  testteamArray.get(0).getTextcolor());
        assertEquals("http://atomiccomic.com", testteamArray.get(0).getLink());
        
          assertEquals("C4 Comics" ,testteamArray.get(1).getTeamname());
        assertEquals("235399", testteamArray.get(1).getColor());
         assertEquals("ffffff",  testteamArray.get(1).getTextcolor());
        assertEquals("http://c4-comics.com", testteamArray.get(1).getLink());
        
        
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
         
//     assertEquals(new Student("Beau", "Brummell", "Atomic Comics", "Lead Designer"), studentArray.get(0));
//     assertEquals(new Student("Jane", "Doe", "C4 Comics", "Lead Programmer"), studentArray.get(1));
         assertEquals("Beau", studentArray.get(0).getFirstName());
     assertEquals("Brummell", studentArray.get(0).getLastName());
      assertEquals("Atomic Comics", studentArray.get(0).getTeamString());
     assertEquals("Lead Designer", studentArray.get(0).getRole());
     
        
     
        assertEquals("Jane", studentArray.get(1).getFirstName());
     assertEquals( "Doe", studentArray.get(1).getLastName());
      assertEquals("C4 Comics", studentArray.get(1).getTeamString());
     assertEquals( "Lead Programmer", studentArray.get(1).getRole());
      
      
      
      
       
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of exportData method, of class CSGFiles.
     */
//    @Ignore
//    @Test
//    public void testExportData() throws Exception {
//        System.out.println("exportData");
//        AppDataComponent data = null;
//        String filePath = "";
//        CSGFiles instance = null;
//        instance.exportData(data, filePath);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of importData method, of class CSGFiles.
     */
//    @Ignore
//    @Test
//    public void testImportData() throws Exception {
//        System.out.println("importData");
//        AppDataComponent data = null;
//        String filePath = "";
//        CSGFiles instance = null;
//        instance.importData(data, filePath);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
}
