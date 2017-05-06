/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transaction;

import csg.data.Student;
import csg.workspace.CSGProjectWorkspace;
import jtps.jTPS_Transaction;

/**
 *
 * @author runnan
 */
public class Project_ClearStudent_Transaction implements jTPS_Transaction{
   CSGProjectWorkspace workspace;
   String firstname;
   String lastname;
   String team;
   String role;
    Student select;
    
    public Project_ClearStudent_Transaction(CSGProjectWorkspace initworkspace){
        workspace=initworkspace;
        firstname=workspace.getFirstNameTextField().getText();
        lastname=workspace.getLastNameTextField().getText();
        team=workspace.getTeamsComboBox().getValue();
        role=workspace.getRoleTextField().getText();
        workspace.getStudentsTable().getSelectionModel().getSelectedItem();
    }
    @Override
    public void doTransaction() {
        workspace.getFirstNameTextField().setText("");
                            workspace.getLastNameTextField().setText("");
                            workspace.getTeamsComboBox().setValue("");
                            workspace.getRoleTextField().setText("");
                            workspace.getStudentsTable().getSelectionModel().clearSelection();
    }

    @Override
    public void undoTransaction() {
         workspace.getStudentsTable().getSelectionModel().select(select);
       workspace.getFirstNameTextField().setText(firstname);
                            workspace.getLastNameTextField().setText(lastname);
                            workspace.getTeamsComboBox().setValue(team);
                            workspace.getRoleTextField().setText(role);
                           
    }
    
}
