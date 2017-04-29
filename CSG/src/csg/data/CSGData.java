/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import csg.CSGApp;
import csg.CSGAppProp;
import csg.file.CSGTimeSlot;
import csg.workspace.CSGCourseWorkspace;
import csg.workspace.CSGScheduleWorkspace;
import csg.workspace.CSGTAWorkspace;
import csg.workspace.CSGWorkspace;
import djf.components.AppDataComponent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Observable;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import properties_manager.PropertiesManager;

/**
 *
 * @author runnan
 */
public class CSGData implements AppDataComponent{
        CSGApp app;
        
        ObservableList<TeachingAssistant> teachingAssistants;
        ObservableList<SitePage> sitePages;
        ObservableList<Recitation> recitations;
        ObservableList<ScheduleItem> scheduleItems;
        ObservableList<Team> teams;
        ObservableList<Student> students;
        
      
       
       //course part
       String subject;
       int number;
       String semester;
       int year;
       String title;
       String instructorName;
       String instructorHome;
       
       String exportDir;
       String siteTempleDir;
       String styleSheet;
       
       String firstImageAdd;
       String secondImageAdd;
       String thirdImageAdd;
       
       
       boolean jhome;
       boolean jsyllabus;
       boolean jschedule;
       boolean jhws;
       boolean jproject;
       
       //ta part
         HashMap<String,StringProperty> officeHours;
         HashMap<String ,String>officeHoursTest;
       ArrayList<String> gridHeaders;
       ArrayList<String> gridHeadersTest;
       int startHour;
       int endHour;
        public static final int MIN_START_HOUR=9;
       public static final int MAX_END_HOUR=20;
       
       //recitation part
       
       //schedule part
       String startingDate;
       String endingDate;
       int startMonth;
       int startDay;
       int endMonth;
       int endDay;
      
       
       //teams part
    
    

    public CSGData(CSGApp initApp) {
        app=initApp;
        
        //course part
//         subject="";
//       number=0;
//       semester="";
//       year=0;
//       title="";
//        instructorName="";
//       instructorHome="";
        
        sitePages=FXCollections.observableArrayList();
        initSitePage();
     

        //TA PART
        teachingAssistants = FXCollections.observableArrayList();
        startHour = MIN_START_HOUR;
        endHour = MAX_END_HOUR;
        officeHours=new HashMap();
        officeHoursTest=new HashMap();
         PropertiesManager props = PropertiesManager.getPropertiesManager();
        ArrayList<String> timeHeaders = props.getPropertyOptionsList(CSGAppProp.OFFICE_HOURS_TABLE_HEADERS);
     
        ArrayList<String> dowHeaders = props.getPropertyOptionsList(CSGAppProp.DAYS_OF_WEEK);
        gridHeaders = new ArrayList();
        gridHeadersTest = new ArrayList();
//         ArrayList<String> timeHeaders =new ArrayList<>();
//          ArrayList<String> dowHeaders=new ArrayList<>();
       gridHeaders.addAll(timeHeaders);
       gridHeaders.addAll(dowHeaders);
       
         gridHeadersTest.addAll(timeHeaders);
       gridHeadersTest.addAll(dowHeaders);
        
        
        //recitation part
        recitations=FXCollections.observableArrayList();
        
        //schedule part
        scheduleItems=FXCollections.observableArrayList();
        
        //project part
        teams=FXCollections.observableArrayList();
        students=FXCollections.observableArrayList();
        
        
    }


    @Override
    public void resetData() {
          startHour = MIN_START_HOUR;
        endHour = MAX_END_HOUR;
        sitePages.clear();
        initSitePage();
        teachingAssistants.clear();
        officeHours.clear();
        recitations.clear();
        scheduleItems.clear();
        teams.clear();
        students.clear();
    }

    public String getFirstImageAdd() {
       
        
         CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
        CSGCourseWorkspace workspace=temp.getCsgCourseWorkspace();
        firstImageAdd=workspace.getFirstImageLocation();
        return firstImageAdd;
    }
    
      public String getFirstImageAddTest() {
        return firstImageAdd;
    }

      
      
      
      
      
      
    public void setFirstImageAdd(String initfirstImageAdd) {
      firstImageAdd = initfirstImageAdd;
        
        CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
        CSGCourseWorkspace workspace=temp.getCsgCourseWorkspace();
        
        
        workspace.setFirstImageLocation(firstImageAdd);
         workspace.setFirstImageView(new Image(firstImageAdd));
    }
    
      public void setFirstImageAddTest(String initfirstImageAdd) {
      firstImageAdd = initfirstImageAdd;
    }
    
    
    
      
      
      
      

    public String getSecondImageAdd() {
         CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
        CSGCourseWorkspace workspace=temp.getCsgCourseWorkspace();
        secondImageAdd=workspace.getSecondImageLocation();
       
        return secondImageAdd;
    }

      public String getSecondImageAddTest() {
       
        return secondImageAdd;
    }
      
      
      
      
      
    public void setSecondImageAdd(String initsecondImageAdd) {
          secondImageAdd = initsecondImageAdd;
        
        CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
        CSGCourseWorkspace workspace=temp.getCsgCourseWorkspace();
        
        
        workspace.setSecondImageLocation(secondImageAdd);
         workspace.setSecondImageView(new Image(secondImageAdd));
    }
    
        public void setSecondImageAddTest(String initsecondImageAdd) {
          secondImageAdd = initsecondImageAdd;
       
    }
    

        
        
        
        
        
        
        
        
    public String getThirdImageAdd() {
           CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
        CSGCourseWorkspace workspace=temp.getCsgCourseWorkspace();
        thirdImageAdd=workspace.getThirdImageLocation();
       
        return thirdImageAdd;
    }

      public String getThirdImageAddTest() {
        return thirdImageAdd;
    }
    
    
    
    
    public void setThirdImageAdd(String initthirdImageAdd) {
          thirdImageAdd = initthirdImageAdd;
        
        CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
        CSGCourseWorkspace workspace=temp.getCsgCourseWorkspace();
        
        
        workspace.setThirdImageLocation(thirdImageAdd);
         workspace.setThirdImageView(new Image(thirdImageAdd));
    }
        public void setThirdImageAddTest(String initthirdImageAdd) {
          thirdImageAdd = initthirdImageAdd;
    }

        
        
        
        
        
        
    public CSGApp getApp() {
        return app;
    }

    public void setApp(CSGApp app) {
        this.app = app;
    }

    public ObservableList<TeachingAssistant> getTeachingAssistants() {
        return teachingAssistants;
    }

    public void setTeachingAssistants(ObservableList<TeachingAssistant> teachingAssistants) {
        this.teachingAssistants = teachingAssistants;
    }

    public ObservableList<SitePage> getSitePages() {
        return sitePages;
    }

    public void setSitePages(ObservableList<SitePage> sitePages) {
        this.sitePages = sitePages;
    }

    public ObservableList<Recitation> getRecitations() {
        return recitations;
    }

    public void setRecitations(ObservableList<Recitation> recitations) {
        this.recitations = recitations;
    }

    public ObservableList<ScheduleItem> getScheduleItems() {
        return scheduleItems;
    }

    public void setScheduleItems(ObservableList<ScheduleItem> scheduleItems) {
        this.scheduleItems = scheduleItems;
    }

    public ObservableList<Team> getTeams() {
        return teams;
    }

    public void setTeams(ObservableList<Team> teams) {
        this.teams = teams;
    }

    public ObservableList<Student> getStudents() {
        return students;
    }

    public void setStudents(ObservableList<Student> students) {
        this.students = students;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getInstructorHome() {
        return instructorHome;
    }

    public void setInstructorHome(String instructorHome) {
        this.instructorHome = instructorHome;
    }

    
    
    
    
    public String getExportDir() {
         CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
        CSGCourseWorkspace workspace=temp.getCsgCourseWorkspace();
        exportDir=workspace.getExporDirDisplayAddressLabel().getText();
        return exportDir;
    }
  public String getExportDirTest() {
        return exportDir;
    }
    
    
    
  
    
    public void setExportDir(String initexportDir) {
            CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
        CSGCourseWorkspace workspace=temp.getCsgCourseWorkspace();
        
        exportDir = initexportDir;
        workspace.setExporDirDisplayAddressLabel(exportDir);
    }
      public void setExportDirTest(String initexportDir) {
        exportDir = initexportDir;
       
    }
      
      
      
      
      
      

    public String getSiteTempleDir() {
        CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
        CSGCourseWorkspace workspace=temp.getCsgCourseWorkspace();
        siteTempleDir=workspace.getTemplatesDirLabel().getText();
        return siteTempleDir;
    }
       public String getSiteTempleDirTest() {
        return siteTempleDir;
    }
       
       
       
       

    public void setSiteTempleDir(String initsiteTempleDir) {
       
        
          CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
        CSGCourseWorkspace workspace=temp.getCsgCourseWorkspace();
        
        siteTempleDir = initsiteTempleDir;
        workspace.setTemplatesDirLabel(siteTempleDir);
        
    }
        public void setSiteTempleDirTest(String initsiteTempleDir) {
        siteTempleDir = initsiteTempleDir;
     
        
    }
        

        
        
        
        
        
    public String getStyleSheet() {
        CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
        CSGCourseWorkspace workspace=temp.getCsgCourseWorkspace();
        styleSheet=workspace.getStyleSheetComboBox().getValue();
        return styleSheet;
    }
     public String getStyleSheetTest() {
      
        return styleSheet;
    }
    
    
    
    
    public void setStyleSheet(String initstyleSheet) {
        styleSheet = initstyleSheet;
        CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
        CSGCourseWorkspace workspace=temp.getCsgCourseWorkspace();
        workspace.getStyleSheetComboBox().setValue(styleSheet);
        
    }
     public void setStyleSheetTest(String initstyleSheet) {
        styleSheet = initstyleSheet;
        
    }


     
     

    public HashMap<String, StringProperty> getOfficeHours() {
        return officeHours;
    }
   public HashMap<String, String> getOfficeHoursTest() {
        return officeHoursTest;
    }
   
   
   
   
    public void setOfficeHours(HashMap<String, StringProperty> initofficeHours) {
        this.officeHours = initofficeHours;
    }
       public void setOfficeHoursTest(HashMap<String, String> initofficeHours) {
        this.officeHoursTest = initofficeHours;
    }
       
       
       

    public ArrayList<String> getGridHeaders() {
        return gridHeaders;
    }
    public ArrayList<String> getGridHeadersTest() {
        return gridHeadersTest;
    }
    

    public void setGridHeaders(ArrayList<String> gridHeaders) {
        this.gridHeaders = gridHeaders;
    }
      public void setGridHeadersTest(ArrayList<String> gridHeaders) {
        this.gridHeadersTest = gridHeaders;
    }
      
      
      

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }
    
    
    public int getStartMonth(){
        int test=0;
         CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
                CSGScheduleWorkspace workspace=temp.getCsgScheduleWorkspace();
                if(workspace.getStartDatePicker().getValue()!=null){
                test= workspace.getStartDatePicker().getValue().getMonthValue();
                }        
        return test;
        
    }
     public int getStartMonthTest() throws ParseException{
                     SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
       if(!startingDate.equals("null")){
       Date startDate=sdf.parse(startingDate);
       startMonth=startDate.getMonth()+1;
       }
        return startMonth;
        
    }
    
     
     
     
     
     
     
     
    
       public int getStartDate(){
        int test=0;
         CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
                CSGScheduleWorkspace workspace=temp.getCsgScheduleWorkspace();
                if(workspace.getStartDatePicker().getValue()!=null){
                test= workspace.getStartDatePicker().getValue().getDayOfMonth();
                }        
        return test;
        
    }
      public int getStartDateTest() throws ParseException{
              SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
       if(!startingDate.equals("null")){
       Date startDate=sdf.parse(startingDate);
       startDay=startDate.getDate();
       }
        return startDay;
        
    }
            
            
            
            
            
            
            
            
            
       
       
         public int getEndMonth(){
        int test=0;
         CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
                CSGScheduleWorkspace workspace=temp.getCsgScheduleWorkspace();
                if(workspace.getEndDatePicker().getValue()!=null){
                test= workspace.getEndDatePicker().getValue().getMonthValue();
                }        
        return test;
        
    }
       public int getEndMonthTest() throws ParseException{
         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
       if(!endingDate.equals("null")){
       Date endDate=sdf.parse(endingDate);
       endMonth=endDate.getMonth()+1;
       }
         
        return endMonth;
        
    }
                
                
                
                
                
                
                
                
                
    
       public int getEndDate(){
        int test=0;
         CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
                CSGScheduleWorkspace workspace=temp.getCsgScheduleWorkspace();
                if(workspace.getEndDatePicker().getValue()!=null){
                test= workspace.getEndDatePicker().getValue().getDayOfMonth();
                }        
        return test;
        
    }
        public int getEndDateTest() throws ParseException{
         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
       if(!endingDate.equals("null")){
       Date endDate=sdf.parse(endingDate);
       endDay=endDate.getDate();
       }
        return endDay;
        
    }
        
        
        
        
        
        
       
       
       
       
        
        
       
    public String getStartingDate() {
         CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
                CSGScheduleWorkspace workspace=temp.getCsgScheduleWorkspace();
                if(workspace.getStartDatePicker().getValue()!=null){
               startingDate= workspace.getStartDatePicker().getValue().toString();
                }        
        return startingDate;
    }
 public String getStartingDateTest() {
             
        return startingDate;
    }
 
 
 
 
 
 
 
    public void setStartingDate(String initstartingDate) throws ParseException {
           CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
                CSGScheduleWorkspace workspace=temp.getCsgScheduleWorkspace();
                workspace.loadCalendarStart(initstartingDate);
        startingDate = initstartingDate;
    }
        public void setStartingDateTest(String initstartingDate) throws ParseException {
          
        startingDate = initstartingDate;
    }
        
        
        
        
        
        
        
        
        

    public String getEndingDate() {
          CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
                CSGScheduleWorkspace workspace=temp.getCsgScheduleWorkspace();
                if(workspace.getEndDatePicker().getValue()!=null){
               endingDate= workspace.getEndDatePicker().getValue().toString();}
        return endingDate;
    }
       public String getEndingDateTest() {
       
        return endingDate;
    }
       
       
       
       
       
       
       
       

    public void setEndingDate(String initendingDate) throws ParseException {
         CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
                CSGScheduleWorkspace workspace=temp.getCsgScheduleWorkspace();
                workspace.loadCalendarEnd(initendingDate);
        endingDate = initendingDate;
    }
      public void setEndingDateTest(String initendingDate) throws ParseException {
     
        endingDate = initendingDate;
    }
      
      
      
      
      
      
      
      

    public boolean isJhome() {
        for (SitePage i:sitePages){
          if ( i.getNavbar().equals("Home")){
              jhome=i.getUsed().get();
          }
        }
        return jhome;
    }
    
    

    

    public void setJhome(boolean initjhome) {
        this.jhome = initjhome;
          for (SitePage i:sitePages){
          if ( i.getNavbar().equals("Home")){
              i.setUsed(jhome);
          }
        }
    }

    public boolean isJsyllabus() {
        for (SitePage i:sitePages){
          if ( i.getNavbar().equals("Syllabus")){
              jsyllabus=i.getUsed().get();
          }
        }
        return jsyllabus;
    }

    public void setJsyllabus(boolean initjsyllabus) {
        jsyllabus = initjsyllabus;
        for (SitePage i:sitePages){
          if ( i.getNavbar().equals("Syllabus")){
              i.setUsed(jsyllabus);
          }
        }
    }

    public boolean isJschedule() {
        for (SitePage i:sitePages){
          if ( i.getNavbar().equals("Schedule")){
              jschedule=i.getUsed().get();
          }
        }
        return jschedule;
    }

    public void setJschedule(boolean initjschedule) {
        jschedule = initjschedule;
        for (SitePage i:sitePages){
          if ( i.getNavbar().equals("Schedule")){
              i.setUsed(jschedule);
          }
        }
    }

    public boolean isJhws() {
        for (SitePage i:sitePages){
          if ( i.getNavbar().equals("HWs")){
              jhws=i.getUsed().get();
          }
        }
        return jhws;
    }

    public void setJhws(boolean initjhws) {
        jhws = initjhws;
        for (SitePage i:sitePages){
          if ( i.getNavbar().equals("HWs")){
              i.setUsed(jhws);
          }
        }
    }

    public boolean isJproject() {
        for (SitePage i:sitePages){
          if ( i.getNavbar().equals("Projects")){
              jproject=i.getUsed().get();
          }
        }
        
        return jproject;
    }

    public void setJproject(boolean initjproject) {
        jproject = initjproject;
        for (SitePage i:sitePages){
          if ( i.getNavbar().equals("Projects")){
              i.setUsed(jproject);
          }
        }
        
    }
    
    
    ///course page////////////////////////////////////
    
    public void initCourseInfo(String initsubject,String initnumber,String initsemester,String inityear,String inittitle,String initstructorname,String initinstructorhome){
         subject=new String(initsubject);
        number=Integer.parseInt(initnumber);
        semester=new String(initsemester);
        year=Integer.parseInt(inityear);
        title=new String(inittitle);
        instructorName=new String(initstructorname);
         instructorHome=new String(initinstructorhome); 
         //styleSheet=new String(initstylesheet);
           CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
                CSGCourseWorkspace workspace=temp.getCourseWorkspace();
                workspace.loadCourseInfo(subject,number,semester,year,title,instructorName,instructorHome);
        
    }
        public void initCourseInfoTest(String initsubject,String initnumber,String initsemester,String inityear,String inittitle,String initstructorname,String initinstructorhome){
         subject=new String(initsubject);
        number=Integer.parseInt(initnumber);
        semester=new String(initsemester);
        year=Integer.parseInt(inityear);
        title=new String(inittitle);
        instructorName=new String(initstructorname);
         instructorHome=new String(initinstructorhome); 
         //styleSheet=new String(initstylesheet);
         
    }
        
        
        
        
        
        
        
        
        
        
        
        
    public void initSitePage(){
        jhome=false;
        jsyllabus=false;
        jschedule=false;
        jhws=false;
        jproject=false;
        SitePage homeSite=new SitePage(jhome,"Home","index.html","HomeBuilder.js");
        SitePage syllabusSite=new SitePage(jsyllabus,"Syllabus","syllabus.html","SyllabusBuilder.js");
        SitePage scheduleSite=new SitePage(jschedule,"Schedule","schedule.html","ScheduleBuilder.js");
        SitePage hwsSite=new SitePage(jhws,"HWs","hws.html","HWsBuilder.js");
        SitePage projectSite=new SitePage(jproject,"Projects","projects.html","ProjectBuilder.js");
        sitePages.addAll(homeSite,syllabusSite,scheduleSite,hwsSite,projectSite);
    }
    
    
    
    
    ///////////////////////ta part
        public boolean containsTA(String testName, String testEmail) {
        for (TeachingAssistant ta : teachingAssistants) {
            if (ta.getName().equals(testName)) {
                return true;
            }
            if (ta.getEmail().equals(testEmail)) {
                return true;
            }
        }
        return false;
    }
      
          public void addTA(String initName, String initEmail) {
        // MAKE THE TA
        TeachingAssistant ta = new TeachingAssistant(initName, initEmail);

        // ADD THE TA
        if (!containsTA(initName, initEmail)) {
            teachingAssistants.add(ta);
        }

        // SORT THE TAS
        Collections.sort(teachingAssistants);
    }
      
          
       public void addTA(String initName, String initEmail,boolean initUndergrad) {
        // MAKE THE TA
        TeachingAssistant ta = new TeachingAssistant(initName, initEmail,initUndergrad);

        // ADD THE TA
        if (!containsTA(initName, initEmail)) {
            teachingAssistants.add(ta);
        }

        // SORT THE TAS
        Collections.sort(teachingAssistants);
    }     
          
          
              public void removeTA(String name) {
        for (TeachingAssistant ta : teachingAssistants) {
            if (name.equals(ta.getName())) {
                teachingAssistants.remove(ta);
                return;
            }
        }
    }
              
              
        public void removeTAFromCell(StringProperty cellProp, String taName) {
        // GET THE CELL TEXT
        String cellText = cellProp.getValue();
        // IS IT THE ONLY TA IN THE CELL?
        if (cellText.equals(taName)) {
            cellProp.setValue("");
        }
        // IS IT THE FIRST TA IN A CELL WITH MULTIPLE TA'S?
        else if (cellText.indexOf(taName) == 0) {
            int startIndex = cellText.indexOf("\n") + 1;
            cellText = cellText.substring(startIndex);
            cellProp.setValue(cellText);
        }
        // IS IT IN THE MIDDLE OF A LIST OF TAs
        else if (cellText.indexOf(taName) < cellText.indexOf("\n", cellText.indexOf(taName))) {
            int startIndex = cellText.indexOf("\n" + taName);
            int endIndex = startIndex + taName.length() + 1;
            cellText = cellText.substring(0, startIndex) + cellText.substring(endIndex);
            cellProp.setValue(cellText);
        }
        // IT MUST BE THE LAST TA
        else {
            int startIndex = cellText.indexOf("\n" + taName);
            cellText = cellText.substring(0, startIndex);
            cellProp.setValue(cellText);
        }
    }
        
        
           public void removeTAFromCellTest(String cellkey,String cellProp, String taName) {
        // GET THE CELL TEXT
        String cellText = cellProp;
        // IS IT THE ONLY TA IN THE CELL?
        if (cellText.equals(taName)) {
            officeHoursTest.put(cellkey, "");
            System.out.println(officeHoursTest.get(cellkey));
            
        }
        // IS IT THE FIRST TA IN A CELL WITH MULTIPLE TA'S?
        else if (cellText.indexOf(taName) == 0) {
            int startIndex = cellText.indexOf("\n") + 1;
            cellText = cellText.substring(startIndex);
            officeHoursTest.put(cellkey, cellText);
            System.out.println(officeHoursTest.get(cellkey));
        }
        // IS IT IN THE MIDDLE OF A LIST OF TAs
        else if (cellText.indexOf(taName) < cellText.indexOf("\n", cellText.indexOf(taName))) {
            int startIndex = cellText.indexOf("\n" + taName);
            int endIndex = startIndex + taName.length() + 1;
            cellText = cellText.substring(0, startIndex) + cellText.substring(endIndex);
            officeHoursTest.put(cellkey, cellText);
            System.out.println(officeHoursTest.get(cellkey));
        }
        // IT MUST BE THE LAST TA
        else {
            int startIndex = cellText.indexOf("\n" + taName);
            cellText = cellText.substring(0, startIndex);
           officeHoursTest.put(cellkey, cellText);
           System.out.println(officeHoursTest.get(cellkey));
        }
    }
        
        
        
        
        
        
        
        
            public void addTAToCell(StringProperty cellProp,String taName){
        String cellText=cellProp.getValue();
        if (cellText.length() == 0) {
            cellProp.setValue(taName);
        } else {
            cellProp.setValue(cellText + "\n" + taName);
        }
        
        
    }

    public StringProperty getCellTextProperty(int col, int row) {
      String cellKey = getCellKey(col, row);
      return officeHours.get(cellKey);
    }
      public String getCellTextPropertyTest(int col, int row) {
      String cellKey = getCellKeyTest(col, row);
      return officeHoursTest.get(cellKey);
    }
      
      
      
      

    public String getCellKey(int col, int row) {
        return col+"_"+row;
    }
     public String getCellKeyTest(int col, int row) {
        return col+"_"+row;
    }
    
     
     
     
     
    public String getCellKey(String day, String time) {
        int col = gridHeaders.indexOf(day);
        int row = 1;
        int hour = Integer.parseInt(time.substring(0, time.indexOf("_")));
        int milHour = hour;
//        if (hour < startHour)
        if(time.contains("pm"))
            milHour += 12;
        if(time.contains("12"))
            milHour -= 12;
        row += (milHour - startHour) * 2;
        if (time.contains("_30"))
            row += 1;
        return getCellKey(col, row);
    }
       public String getCellKeyTest(String day, String time) {
        int col = gridHeadersTest.indexOf(day);
        int row = 1;
        int hour = Integer.parseInt(time.substring(0, time.indexOf("_")));
        int milHour = hour;
//        if (hour < startHour)
        if(time.contains("pm"))
            milHour += 12;
        if(time.contains("12"))
            milHour -= 12;
        row += (milHour - startHour) * 2;
        if (time.contains("_30"))
            row += 1;
        return getCellKeyTest(col, row);
    }
       
       
       
       
            
       public void toggleTAOfficeHours(String cellKey, String taName) {
      try{
        StringProperty cellProp = officeHours.get(cellKey);
        String cellText = cellProp.getValue();

        // IF IT ALREADY HAS THE TA, REMOVE IT
        if (cellText.contains(taName)) {
            removeTAFromCell(cellProp, taName);
        } // OTHERWISE ADD IT
        else if (cellText.length() == 0) {
            cellProp.setValue(taName);
        } else {
            cellProp.setValue(cellText + "\n" + taName);
        }
      }catch(Exception e){
          return;
      }
      
      
      }
          public void toggleTAOfficeHoursTest(String cellKey, String taName) {
      if(officeHoursTest.get(cellKey)!=null){
              try{
        String cellProp = officeHoursTest.get(cellKey);
          System.out.println(cellProp);
        String cellText = cellProp;
          System.out.println("2222    "+cellKey+"   "+taName  );

        // IF IT ALREADY HAS THE TA, REMOVE IT
        if (cellText.contains(taName)) {
            removeTAFromCellTest(cellKey,cellProp, taName);
        } // OTHERWISE ADD IT
        else if (cellText.length() == 0) {
            officeHoursTest.put(cellKey, taName);
            System.out.println(officeHoursTest.get(cellKey));
        } else {
             officeHoursTest.put(cellKey, cellText + "\n" + taName);
              System.out.println(officeHoursTest.get(cellKey));
            
        }
      }catch(Exception e){
          return;
      }
      }else{
           officeHoursTest.put(cellKey, taName);
      }
      
      }
          
          
          
          
          
          
          
            
     public void setCellProperty(int col, int row, StringProperty prop) {
        String cellKey = getCellKey(col, row);
          officeHours.put(cellKey, prop);
    }  
     
     public void setCellPropertyTest(int col,int row,String prop){
             String cellKey = getCellKeyTest(col, row);
          officeHoursTest.put(cellKey, prop);
          System.out.println(officeHoursTest.get(cellKey));
     }
     
     
        public int getNumRows() {
        return ((endHour - startHour) * 2) + 1;
    }
        
        
        
         public void initHours(String startHourText, String endHourText) {
        int initStartHour = Integer.parseInt(startHourText);
        int initEndHour = Integer.parseInt(endHourText);
        if ((initStartHour >= MIN_START_HOUR)
                && (initEndHour <= MAX_END_HOUR)
                && (initStartHour <= initEndHour)) {
            // THESE ARE VALID HOURS SO KEEP THEM
            initOfficeHours(initStartHour, initEndHour);
        }
    }
                  public void initHoursTest(String startHourText, String endHourText) {
        int initStartHour = Integer.parseInt(startHourText);
        int initEndHour = Integer.parseInt(endHourText);
        if ((initStartHour >= MIN_START_HOUR)
                && (initEndHour <= MAX_END_HOUR)
                && (initStartHour <= initEndHour)) {
            // THESE ARE VALID HOURS SO KEEP THEM
            initOfficeHoursTest(initStartHour, initEndHour);
        }
    }
         
         
         
         
                  
                  
         
         
         
            private void initOfficeHours(int initStartHour, int initEndHour) {
        // NOTE THAT THESE VALUES MUST BE PRE-VERIFIED
        startHour = initStartHour;
        endHour = initEndHour;
        
        // EMPTY THE CURRENT OFFICE HOURS VALUES
        officeHours.clear();
            
        // WE'LL BUILD THE USER INTERFACE COMPONENT FOR THE
        // OFFICE HOURS GRID AND FEED THEM TO OUR DATA
        // STRUCTURE AS WE GO
        CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
                CSGTAWorkspace workspaceComponent=temp.getCsgTAWorkspace();
        workspaceComponent.reloadOfficeHoursGrid(this);
        
        workspaceComponent.getOfficeHour(true).getSelectionModel().select(startHour);
        workspaceComponent.getOfficeHour(false).getSelectionModel().select(endHour);
    }
         private void initOfficeHoursTest(int initStartHour, int initEndHour) {
        // NOTE THAT THESE VALUES MUST BE PRE-VERIFIED
        startHour = initStartHour;
        endHour = initEndHour;
        
        // EMPTY THE CURRENT OFFICE HOURS VALUES
        officeHours.clear();
            
        // WE'LL BUILD THE USER INTERFACE COMPONENT FOR THE
        // OFFICE HOURS GRID AND FEED THEM TO OUR DATA
        // STRUCTURE AS WE GO
   
    }
                   
                   
                   
                   
                   
                   
                   
                   
                   
                   
    
      public void changeTime(int startTime, int endTime, ArrayList<CSGTimeSlot> officeHours){
        initHours("" + startTime, "" + endTime);
        for(CSGTimeSlot ts : officeHours){
            String temp = ts.getTime();
            int tempint = Integer.parseInt(temp.substring(0, temp.indexOf('_')));
            if(temp.contains("pm"))
                tempint += 12;
            if(temp.contains("12"))
                tempint -= 12;
            if(tempint >= startTime && tempint <= endTime)
                addOfficeHoursReservation(ts.getDay(), ts.getTime(), ts.getName());
        }
    }
        public void addOfficeHoursReservation(String day, String time, String taName) {
        String cellKey = getCellKey(day, time);
        toggleTAOfficeHours(cellKey, taName);
    }
              public void addOfficeHoursReservationTest(String day, String time, String taName) {
        String cellKey = getCellKeyTest(day, time);
      //  officeHoursTest.put(cellKey,taName);
        toggleTAOfficeHoursTest(cellKey, taName);
                  System.out.println("1111111111111");
    }
        
        
        //recitation part /////////////////////////////////////////////////////////////////////////////////
        public void addRecitation(String initSection,String initInstructor,String initDaytime,String initLocation,String initFirstTa,String initSecondTa){
           Recitation recitation = new Recitation(initSection,initInstructor,initDaytime,initLocation,initFirstTa,initSecondTa);
            if (!containsRecitation(initSection)) {
            recitations.add(recitation);
        }

        
        Collections.sort(recitations);
            
            
        }
        
        public boolean containsRecitation(String initSection){
           for (Recitation recitation : recitations) {
            if (recitation.getSection().equals(initSection)) {
                return true;
            }
 
        }
        return false;
            
        }
        
               
              public void removeRecitation(String section) {
        for (Recitation reci : recitations) {
            if (section.equals(reci.getSection())) {
                recitations.remove(reci);
                return;
            }
        }
    }
        
        
        //Schedule part?????????????????????????????????????????????????????????????????????????
        public void initCalendar(String initStarting,String initEnding) throws ParseException{
            startingDate=new String(initStarting);
            endingDate=new String(initEnding);
              CSGWorkspace temp = (CSGWorkspace)app.getWorkspaceComponent();
                CSGScheduleWorkspace workspace=temp.getScheduleWorkspace();
                workspace.loadCalendar(startingDate,endingDate);
            
            
        }
            public void initCalendarTest(String initStarting,String initEnding) throws ParseException{
            startingDate=new String(initStarting);
            endingDate=new String(initEnding);
        
            
            
        }
        
        
        
        
        
        
        
        public void addScheduleItem (String initType,String initDate,String initTitle,String initTopic){
            ScheduleItem scheduleItem=new ScheduleItem(initType, initDate, initTitle, initTopic);
            scheduleItems.add(scheduleItem);
            System.out.println(scheduleItem.getTitle());
          //  Collections.sort(scheduleItems);
            }
        
        
              public void addScheduleItem (String initType,String initDate,String initTime,String initTitle,String initTopic,String initLink,String initCriteria){
            ScheduleItem scheduleItem=new ScheduleItem(initType, initDate, initTime,initTitle, initTopic,initLink,initCriteria);
            scheduleItems.add(scheduleItem);
            System.out.println(scheduleItem.getTitle());
          //  Collections.sort(scheduleItems);
            }
        //project part//////////////////////////////////////////////////////////////////////
        public void addTeam(String initName,String initColor,String initTextColor,String initLink){
            Team team=new Team(initName,initColor,initTextColor,initLink);
            if (!containsTeam(initName)){
                teams.add(team);
            }
        //    Collections.sort(teams);
        }
        public boolean containsTeam(String initName){
            
            for (Team team:teams){
                if (team.getTeamname().equals(initName)){
                    return true;
                }
            }
            return false;
        }
        
        
        
        public void addStudent(String initFirstName,String initLastName,String initTeam,String initRole){
            Student student=new Student(initFirstName, initLastName, initTeam, initRole);
            if(!containsStudent(initFirstName,initLastName)){
                students.add(student);
            }
          //  Collections.sort(students);
        }
        public boolean containsStudent(String initFirstName,String initLastName){
            for(Student student:students){
                if(student.getFirstName().equals(initFirstName)&& student.getLastName().equals(initLastName)){
                    return true;
                }
            }
            return false;
        }

}
