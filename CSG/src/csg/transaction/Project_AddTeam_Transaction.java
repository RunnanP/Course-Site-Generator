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
public class Project_AddTeam_Transaction implements jTPS_Transaction{
   
    CSGData data;
    Team newte;
    
    public Project_AddTeam_Transaction(CSGData initdata,Team initnewte){
        data=initdata;
        newte=initnewte;
    }
    @Override
    public void doTransaction() {
       data.addTeam(newte);
    }

    @Override
    public void undoTransaction() {
        data.removeTeam(newte);
    }
    
}
