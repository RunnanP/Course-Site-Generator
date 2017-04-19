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
public class Team <E extends Comparable<E>> implements Comparable<E>{
     private final StringProperty name;
    private final StringProperty color;
      private final StringProperty textcolor;
    private final StringProperty link;

    public Team(String initname, String initcolor, String inittextcolor, String initlink) {
        name = new SimpleStringProperty(initname);
        color=new SimpleStringProperty(inittextcolor);
        textcolor = new SimpleStringProperty(inittextcolor);
        link = new SimpleStringProperty(initlink);
    }

    public String getName() {
        return name.get();
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
    
       public void setName(String initname) {
        name.set(initname);
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
