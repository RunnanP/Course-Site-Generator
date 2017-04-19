/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author runnan
 */
public class SitePage <E extends Comparable<E>> implements Comparable<E>{
    
    private StringProperty navbar;
    private  StringProperty filename;
    private  StringProperty scriptname;
    private  BooleanProperty used;
   
    
    public SitePage(String initNavbar,String initFile,String initScript){
        navbar=new SimpleStringProperty(initNavbar);
        filename=new SimpleStringProperty(initFile);
        scriptname=new SimpleStringProperty(initScript);
        used=new SimpleBooleanProperty(true);
        
        
        
        
        
    }

    public String getNavbar() {
        return navbar.get();
    }

    public void setNavbar(StringProperty navbar) {
        this.navbar = navbar;
    }

    public String getFilename() {
        return filename.get();
    }

    public void setFilename(StringProperty filename) {
        this.filename = filename;
    }

    public String getScriptname() {
        return scriptname.get();
    }

    public void setScriptname(StringProperty scriptname) {
        this.scriptname = scriptname;
    }

    public BooleanProperty getUsed() {
        return used;
    }

    public void setUsed(BooleanProperty initused) {
        used = initused;
    }
    public BooleanProperty usedProperty(){
        return used;
    }
    
    
    @Override
    public int compareTo(E o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
