/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import csg.CSGApp;
import csg.CSGAppProp;
import djf.components.AppDataComponent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Observable;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
       boolean firstImage;
       boolean secondImage;
       boolean thirdImage;
       
       //ta part
         HashMap<String,StringProperty> officeHours;
       ArrayList<String> gridHeaders;
       int startHour;
       int endHour;
       
       //recitation part
       
       //schedule part
       String startingDate;
       String endingDate;
       public static final int MIN_START_HOUR=9;
       public static final int MAX_END_HOUR=20;
       
       //teams part
    
    

    public CSGData(CSGApp initApp) {
        app=initApp;
        
        
        //TA PART
        teachingAssistants = FXCollections.observableArrayList();
        startHour = MIN_START_HOUR;
        endHour = MAX_END_HOUR;
        officeHours=new HashMap();
         PropertiesManager props = PropertiesManager.getPropertiesManager();
        ArrayList<String> timeHeaders = props.getPropertyOptionsList(CSGAppProp.OFFICE_HOURS_TABLE_HEADERS);
        ArrayList<String> dowHeaders = props.getPropertyOptionsList(CSGAppProp.DAYS_OF_WEEK);
        gridHeaders = new ArrayList();
        gridHeaders.addAll(timeHeaders);
        gridHeaders.addAll(dowHeaders);
    }


    @Override
    public void resetData() {
          startHour = MIN_START_HOUR;
        endHour = MAX_END_HOUR;
        teachingAssistants.clear();
        officeHours.clear();
        
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
        return exportDir;
    }

    public void setExportDir(String exportDir) {
        this.exportDir = exportDir;
    }

    public String getSiteTempleDir() {
        return siteTempleDir;
    }

    public void setSiteTempleDir(String siteTempleDir) {
        this.siteTempleDir = siteTempleDir;
    }

    public String getStyleSheet() {
        return styleSheet;
    }

    public void setStyleSheet(String styleSheet) {
        this.styleSheet = styleSheet;
    }

    public boolean isFirstImage() {
        return firstImage;
    }

    public void setFirstImage(boolean firstImage) {
        this.firstImage = firstImage;
    }

    public boolean isSecondImage() {
        return secondImage;
    }

    public void setSecondImage(boolean secondImage) {
        this.secondImage = secondImage;
    }

    public boolean isThirdImage() {
        return thirdImage;
    }

    public void setThirdImage(boolean thirdImage) {
        this.thirdImage = thirdImage;
    }

    public HashMap<String, StringProperty> getOfficeHours() {
        return officeHours;
    }

    public void setOfficeHours(HashMap<String, StringProperty> officeHours) {
        this.officeHours = officeHours;
    }

    public ArrayList<String> getGridHeaders() {
        return gridHeaders;
    }

    public void setGridHeaders(ArrayList<String> gridHeaders) {
        this.gridHeaders = gridHeaders;
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

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public String getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
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

    public String getCellKey(int col, int row) {
        return col+"_"+row;
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
            
     public void setCellProperty(int col, int row, StringProperty prop) {
        String cellKey = getCellKey(col, row);
          officeHours.put(cellKey, prop);
    }    
}
