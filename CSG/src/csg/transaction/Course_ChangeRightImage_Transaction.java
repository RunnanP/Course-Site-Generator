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
public class Course_ChangeRightImage_Transaction implements  jTPS_Transaction{
    CSGData data;
    String newpath;
    String oldpath;
    
    public Course_ChangeRightImage_Transaction(CSGData initdata,String initnewpath,String initoldpath){
        data=initdata;
        newpath=initnewpath;
        oldpath=initoldpath;
    }
    @Override
    public void doTransaction() {
       data.setThirdImageAdd(newpath);
    }

    @Override
    public void undoTransaction() {
        data.setThirdImageAdd(oldpath);
        System.out.println(oldpath);
    }
}
