/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transaction;

import csg.data.CSGData;
import csg.data.Team;
import jtps.jTPS_Transaction;

/**
 *
 * @author runnan
 */
public class Project_RemoveTeam_Transaction implements jTPS_Transaction{

    CSGData data;
    Team te;
    
    
    public Project_RemoveTeam_Transaction(CSGData initdata,Team initte){
        data=initdata;
        te=initte;
    }
    @Override
    public void doTransaction() {
    data.removeTeam(te);  
    }

    @Override
    public void undoTransaction() {
        data.addTeam(te);  
    }
    
}
