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
public class Project_RemoveTeam_Transaction implements jTPS_Transaction{

    CSGData data;
    Team te;
    String teamname;

    ArrayList<Student> stlist;
    CSGProjectWorkspace workspace;
    public Project_RemoveTeam_Transaction(CSGData initdata,Team initte,CSGProjectWorkspace initworkspace){
        workspace=initworkspace;
        data=initdata;
        te=initte;
        teamname=te.getTeamname();
         stlist=data.getHaveTeamStus(te);
    }
    @Override
    public void doTransaction() {
    data.removeTeam(te);  
    }

    @Override
    public void undoTransaction() {
        data.addTeam(te); 
          for(Student st:stlist){
            st.setTeam(teamname);
        }
          workspace.getStudentsTable().refresh();
    }
    
}
