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
public class Project_ChangeTeamInfo_Transaction implements jTPS_Transaction{
    
    CSGData data;
    Team newte;
    Team oldte;
    
    
    public Project_ChangeTeamInfo_Transaction(CSGData initdata,Team initnewTeam,Team initoldTeam){
        data=initdata;
        newte=initnewTeam;
        oldte=initoldTeam;
    }
    @Override
    public void doTransaction() {
        data.removeTeam(oldte);
        data.addTeam(newte);
    }

    @Override
    public void undoTransaction() {
        data.removeTeam(newte);
        data.addTeam(oldte);
    }
    
}
