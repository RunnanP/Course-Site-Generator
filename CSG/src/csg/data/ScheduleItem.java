/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author runnan
 */
public class ScheduleItem <E extends Comparable<E>> implements Comparable<E>{
     private final StringProperty type;
    private final StringProperty date;
    private  final StringProperty title;
    private final StringProperty topic;

    public ScheduleItem(String initType, String initDate, String initTitle, String initTopic) {
        type=new SimpleStringProperty(initType);
        date=new SimpleStringProperty(initDate);
        title=new SimpleStringProperty(initTitle);
        topic=new SimpleStringProperty(initTopic);
    }
      public ScheduleItem(String initType, String initDate, String initTitle) {
        type=new SimpleStringProperty(initType);
        date=new SimpleStringProperty(initDate);
        title=new SimpleStringProperty(initTitle);
        topic=new SimpleStringProperty("");
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
    
    
    @Override
    public int compareTo(E o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
