/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;

/**
 *
 * @author runnan
 */
public class Team <E extends Comparable<E>> implements Comparable<E>{
     private final StringProperty teamname;
    private final StringProperty color;
      private final StringProperty textcolor;
    private final StringProperty link;
    private ArrayList<String> studentList;
  //  private Color acColor;

    public Team(String initname, String initcolor, String inittextcolor, String initlink) {
        teamname = new SimpleStringProperty(initname);
        color=new SimpleStringProperty(initcolor);
        textcolor = new SimpleStringProperty(inittextcolor);
        link = new SimpleStringProperty(initlink);
        studentList=new ArrayList<>();
       // initColor();
        
    }
   
    public void initColor(){
        String temp=getColor();
        temp=temp.substring(4);
     
 }
    public int getRed(){
        
         String temp=getColor();
        temp=temp.substring(4);
        temp=temp.substring(0, 2);
        int r=Integer.parseInt(temp, 16);
        
        return r;
    }
    
    public int getGreen(){
        String temp=getColor();
        temp=temp.substring(4);
        temp=temp.substring(2,4);
        int r=Integer.parseInt(temp, 16);
        
        return r;
    }
    
    public int getBlue(){
         String temp=getColor();
        temp=temp.substring(4);
        temp=temp.substring(4);
        int r=Integer.parseInt(temp, 16);
        
        return r;
    }
    
    
    public ArrayList<String> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<String> studentList) {
        this.studentList = studentList;
    }

    
    public String getTeamname() {
        return teamname.get();
    }

    public String getColor() {
        return color.get();
    }

    public String getTextcolor() {
        return textcolor.get();
    }

    public String getLink() {
        return link.get();
    }
    
       public void setTeamname(String initname) {
        teamname.set(initname);
    }

    public void setColor(String initcolor) {
        color.set(initcolor);
    }

    public void setTextcolor(String inittextcolor) {
         textcolor.set(inittextcolor);
    }

    public void setLink(String initlink) {
         link.set(initlink);
    }
    
    @Override
    public int compareTo(E o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
