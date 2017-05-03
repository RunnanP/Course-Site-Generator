/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transaction;

import csg.data.CSGData;
import csg.workspace.CSGCourseWorkspace;
import jtps.jTPS_Transaction;

/**
 *
 * @author runnan
 */
public class Course_ChangeExportDir_Transaction implements  jTPS_Transaction{
    CSGData data;
    CSGCourseWorkspace workspace;
    String newpath;
    String oldpath;
    
    public Course_ChangeExportDir_Transaction(CSGData initdata,CSGCourseWorkspace initworkspace,String initnewpath,String initoldpath){
       
    data=initdata;
    workspace=initworkspace;
    newpath=initnewpath;
    oldpath=initoldpath;
    }
            
            
    
    @Override
    public void doTransaction() {
         data.setExportDir(newpath);
        workspace.setExporDirDisplayAddressString(newpath);
    }

    @Override
    public void undoTransaction() {
        data.setExportDir(oldpath);
        workspace.setExporDirDisplayAddressString(oldpath);
    }
    
}
