/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transaction;

import csg.data.Team;
import csg.workspace.CSGProjectWorkspace;
import javafx.scene.paint.Color;
import jtps.jTPS_Transaction;

/**
 *
 * @author runnan
 */
public class Project_ClearTeam_Transaction  implements jTPS_Transaction{
    CSGProjectWorkspace workspace;
    String name;
    Color color;
    Color textcolor;
    String link;
    Team select;
    
    
    public Project_ClearTeam_Transaction(CSGProjectWorkspace initworkspace){
        workspace=initworkspace;
        name=workspace.getNameTextField().getText();
        color=workspace.getColorColorPicker().getValue();
        textcolor=workspace.getTextColorPicker().getValue();
        link=workspace.getLinkTextField().getText();
    }
    
    
    @Override
    public void doTransaction() {
       workspace.getNameTextField().setText("");
                            workspace.getColorColorPicker().setValue(Color.WHITE);
                         workspace.getTextColorPicker().setValue(Color.WHITE);
                            workspace.getLinkTextField().setText("");
             
       workspace.getTeamsTable().getSelectionModel().clearSelection();
    }

    @Override
    public void undoTransaction() {
        workspace.getTeamsTable().getSelectionModel().select(select);
           workspace.getNameTextField().setText(name);
                            workspace.getColorColorPicker().setValue(color);
                         workspace.getTextColorPicker().setValue(textcolor);
                            workspace.getLinkTextField().setText(link);
    }
    
}
