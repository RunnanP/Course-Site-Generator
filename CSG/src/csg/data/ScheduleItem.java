/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author runnan
 */
public class ScheduleItem <E extends Comparable<E>> implements Comparable<E>{
     private final StringProperty type;
    private final StringProperty date;
    private final StringProperty time;
    private  final StringProperty title;
    private final StringProperty topic;
    private final StringProperty link;
    private final StringProperty criteria;

    public ScheduleItem(String initType, String initDate, String initTime,String initTitle, String initTopic) {
        type=new SimpleStringProperty(initType);
        date=new SimpleStringProperty(initDate);
        time=new SimpleStringProperty(initTime);
        title=new SimpleStringProperty(initTitle);
        topic=new SimpleStringProperty(initTopic);
        link=new SimpleStringProperty();
        criteria=new SimpleStringProperty();
    }
      public ScheduleItem(String initType, String initDate, String initTime,String initTitle) {
        type=new SimpleStringProperty(initType);
        date=new SimpleStringProperty(initDate);
        time=new SimpleStringProperty(initTime);
        title=new SimpleStringProperty(initTitle);
        topic=new SimpleStringProperty("");
        link=new SimpleStringProperty("");
        criteria=new SimpleStringProperty("");
    }
    
      public ScheduleItem(String initType,String initDate,String initTime,String initTitle,String initTopic,String initLink,String initCriteria){
           type=new SimpleStringProperty(initType);
        date=new SimpleStringProperty(initDate);
        time=new SimpleStringProperty(initTime);
        title=new SimpleStringProperty(initTitle);
        topic=new SimpleStringProperty(initTopic);
        link=new SimpleStringProperty(initLink);
        criteria=new SimpleStringProperty(initCriteria); 
      }
      
    public String getTime(){
        return time.get();
    }  
    public String getLink(){ 
    
       return link.get();
    }
    
    public String getCriteria(){
        return criteria.get();
    }
    public String getType() {
        return type.get();
    }

    public String getDate() {
        return date.get();
    }

    public String getTitle() {
        return title.get();
    }

    public String getTopic() {
        return topic.get();
    }
    
    public void setTime(String initTime){
        time.set(initTime);
    }
      public void setType(String initType) {
        type.set(initType);
    }

    public void setDate(String initData) {
         date.set(initData);
    }

    public void setTitle(String initTitle) {
         title.set(initTitle);
    }

    public void setTopic(String initTopic) {
        topic.set(initTopic);
    }
    
    public int getMonth() throws ParseException{
          SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
       if(!date.get().equals("null")){
     Date startDate=sdf.parse(date.get());
         return startDate.getMonth()+1;
       }
       return 0;
    }
    
      public int getDay() throws ParseException{
          SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
       if(!date.get().equals("null")){
     Date startDate=sdf.parse(date.get());
         return startDate.getDate();
       }
       return 0;
    }
    
    
    @Override
    public int compareTo(E o) {
       return 0;
    }
    
}
