/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transaction;

import csg.data.CSGData;
import csg.workspace.CSGController;
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
    CSGController controller;
    
    public Course_ChangeExportDir_Transaction(CSGData initdata,CSGCourseWorkspace initworkspace,String initnewpath,String initoldpath,CSGController initcontroller){
       
    data=initdata;
    workspace=initworkspace;
    newpath=initnewpath;
    oldpath=initoldpath;
    controller=initcontroller;
    }
            
            
    
    @Override
    public void doTransaction() {
         data.setExportDir(newpath);
        workspace.setExporDirDisplayAddressString(newpath);
        if(newpath.equals("")){
            controller.markCannotExport();
        }else{
            controller.markCanExport();
        }
    }

    @Override
    public void undoTransaction() {
        data.setExportDir(oldpath);
        workspace.setExporDirDisplayAddressString(oldpath);
         if(oldpath.equals("")){
            controller.markCannotExport();
        }else{
            controller.markCanExport();
        }
    }
    
}
