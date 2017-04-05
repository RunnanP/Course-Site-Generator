/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import csg.CSGApp;
import javafx.scene.layout.Pane;

/**
 *
 * @author runnan
 */
public class CSGProjectWorkspace implements WorkspacePart{
Pane basePane;
    public CSGProjectWorkspace(CSGApp app) {
        
    }

   @Override
    public Pane getBasePane() {
        return basePane;
    }
}
