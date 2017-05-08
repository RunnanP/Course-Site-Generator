/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transaction;

import csg.data.CSGData;
import csg.data.Student;
import csg.data.Team;
import csg.workspace.CSGProjectWorkspace;
import java.util.ArrayList;
import jtps.jTPS_Transaction;

/**
 *
 * @author runnan
 */
public class Project_ChangeTeamInfo_Transaction implements jTPS_Transaction{
    
    CSGData data;
    Team newte;
    Team oldte;
    String newteamname;
    String oldteamname;
    ArrayList<Student> stlist;
    CSGProjectWorkspace workspace;
    public Project_ChangeTeamInfo_Transaction(CSGData initdata,Team initnewTeam,Team initoldTeam,CSGProjectWorkspace initworkspace){
        workspace=initworkspace;
        data=initdata;
        newte=initnewTeam;
        oldte=initoldTeam;
        newteamname=newte.getTeamname();
        oldteamname=oldte.getTeamname();
         stlist=data.getHaveTeamStus(oldte);
    }
    @Override
    public void doTransaction() {
        
        data.removeTeam(oldte);
        data.addTeam(newte);
        for(Student st:stlist){
            st.setTeam(newteamname);
        }
        workspace.getStudentsTable().refresh();
    }

    @Override
    public void undoTransaction() {
        data.removeTeam(newte);
        data.addTeam(oldte);
         for(Student st:stlist){
            st.setTeam(oldteamname);
        }
         workspace.getStudentsTable().refresh();
    }
    
}
