/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transaction;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import csg.CSGApp;
import csg.data.CSGData;
import csg.data.SitePage;
import csg.file.CSGFiles;
import csg.workspace.CSGCourseWorkspace;
import csg.workspace.WorkspacePart;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import jtps.jTPS_Transaction;

/**
 *
 * @author runnan
 */
public class Course_ChangeTemplateDir_Transaction implements  jTPS_Transaction{
    CSGApp app;
    CSGData data;
    CSGCourseWorkspace workspace;
    CSGFiles file;
    String newpath;
    String oldpath;
    ArrayList<String> newcsslist=new ArrayList<>();
    ObservableList<String>oldcsslist;
    
    boolean oldJhome=false;
    boolean oldJsyllabus=false;
    boolean oldJschedule=false;
    boolean oldJhws=false;
    boolean oldJproject=false;
    
    boolean newJhome=false;
    boolean newJsyllabus=false;
    boolean newJschedule=false;
    boolean newJhws=false;
    boolean newJproject=false;
          
    public Course_ChangeTemplateDir_Transaction(CSGApp initapp,CSGData initdata,CSGCourseWorkspace initworkspace,CSGFiles initfile,String initnewpath,String initoldpath){
        app=initapp;
        data=initdata;
        workspace =initworkspace;
        file=initfile;
        newpath=initnewpath;
        oldpath=initoldpath;
       // newcsslist=initnewcsslist;
         File f1=new File(newpath+"\\\\index.html");
                if(f1.exists()){
                    newJhome=true;
                } 
                File f2=new File(newpath+"\\\\syllabus.html");
               if(f2.exists()){
                    newJsyllabus=true;
               }
                
                File f3=new File(newpath+"\\\\schedule.html");
                if(f3.exists()){
                   newJschedule=true;
               }
            
                
                File f4=new File(newpath+"\\\\hws.html");
                if(f4.exists()){
                    newJhws=true;
               }
                
                File f5=new File(newpath+"\\\\projects.html");
                if(f5.exists()){
                    newJproject=true;
               }
            
                ObservableList<SitePage> temp=data.getSitePages();
                
                for(SitePage si:temp){
                    switch (si.getNavbar()){
                        case "Home": 
                            oldJhome=si.getUsed().get();
                            break;
                        case "Syllabus":
                            oldJsyllabus=si.getUsed().get();
                            break;
                        case "Schedule":
                            oldJschedule=si.getUsed().get();
                            break;
                        case "HWs":
                            oldJhws=si.getUsed().get();
                            break;
                        case "Projects":
                            oldJproject=si.getUsed().get();
                            break;
                        
                    }
                    
                }
                
                
                File f6=new File(newpath+"\\\\css\\\\");
                File flist[]=f6.listFiles();
                for (File f:flist){
                    if (!f.isDirectory()){
                        if(f.getName().endsWith(".css")){
                           // workspace.getStyleSheetComboBox().getItems().add(f.getName());
                           newcsslist.add(f.getName());
                        }
                    }
                    
                }
               oldcsslist= workspace.getStyleSheetComboBox().getItems();
                
                if(oldpath==""){
                  //  file.saveData(app.getDataComponent(), "");
                }
                
        
    }
    
    
    
    @Override
    public void doTransaction() {
        data.setSiteTempleDir(newpath);
        workspace.setTemplatesDirLabel(newpath);
        try {
            file.loadData(app.getDataComponent(), newpath+"\\\\data\\\\savefordeter.json");
        } catch (IOException ex) {
            Logger.getLogger(Course_ChangeTemplateDir_Transaction.class.getName()).log(Level.SEVERE, null, ex);
        }
        data.setJhome(newJhome);
        data.setJsyllabus(newJsyllabus);
        data.setJschedule(newJschedule);
        data.setJhws(newJhws);
        data.setJproject(newJproject);
        
        
        workspace.getStyleSheetComboBox().getItems().clear();
        workspace.getStyleSheetComboBox().getItems().addAll(newcsslist);
         workspace.setTemplatesDirLabel(newpath);
         
    }

    @Override
    public void undoTransaction() {
         data.setSiteTempleDir(oldpath);
        workspace.setTemplatesDirLabel(oldpath);
        try {
            file.loadData(app.getDataComponent(), oldpath+"\\\\data\\\\savefordeter.json");
        } catch (IOException ex) {
            Logger.getLogger(Course_ChangeTemplateDir_Transaction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
             data.setJhome(oldJhome);
        data.setJsyllabus(oldJsyllabus);
        data.setJschedule(oldJschedule);
        data.setJhws(oldJhws);
        data.setJproject(oldJproject);
        workspace.getStyleSheetComboBox().getItems().clear();
        workspace.getStyleSheetComboBox().getItems().addAll(oldcsslist);
         workspace.setTemplatesDirLabel(oldpath);
    }
    
}
