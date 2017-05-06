/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transaction;

import csg.data.CSGData;
import jtps.jTPS_Transaction;

/**
 *
 * @author runnan
 */
public class Course_ChangeBannerImage_Transaction implements  jTPS_Transaction{
   
    CSGData data;
    String newpath;
    String oldpath;
    
    public Course_ChangeBannerImage_Transaction(CSGData initdata,String initnewpath,String initoldpath){
        data=initdata;
        newpath=initnewpath;
        oldpath=initoldpath;
    }
    @Override
    public void doTransaction() {
       data.setFirstImageAdd(newpath);
    }

    @Override
    public void undoTransaction() {
        data.setFirstImageAdd(oldpath);
        
    }
    
}
