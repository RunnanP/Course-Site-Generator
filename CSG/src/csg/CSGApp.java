/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg;

import csg.data.CSGData;
import csg.file.CSGFiles;
import csg.style.CSGStyle;
import csg.workspace.CSGWorkspace;
import djf.AppTemplate;
import java.text.ParseException;
import java.util.Locale;
import static javafx.application.Application.launch;

/**
 *
 * @author runnan
 */


public class CSGApp extends AppTemplate {
    /**
     * This hook method must initialize all four components in the
     * proper order ensuring proper dependencies are respected, meaning
     * all proper objects are already constructed when they are needed
     * for use, since some may need others for initialization.
     */
    @Override
    public void buildAppComponentsHook() throws ParseException{
        // CONSTRUCT ALL FOUR COMPONENTS. NOTE THAT FOR THIS APP
        // THE WORKSPACE NEEDS THE DATA COMPONENT TO EXIST ALREADY
        // WHEN IT IS CONSTRUCTED, SO BE CAREFUL OF THE ORDER
       dataComponent = new CSGData(this);
        workspaceComponent = new CSGWorkspace(this);
        fileComponent = new CSGFiles(this);
        styleComponent = new CSGStyle(this);
    }
    
    
    
    public void setDataComponent(CSGApp app){
        dataComponent = new CSGData(app);
    }
    
      public void setWorkspaceComponent(CSGApp app) throws ParseException{
           workspaceComponent = new CSGWorkspace(app,"");
    }
        public void setFileComponent(CSGApp app){
        fileComponent = new CSGFiles(app);
    }
          public void setStyleComponent(CSGApp app){
      styleComponent = new CSGStyle(app);
    }
    /**
     * This is where program execution begins. Since this is a JavaFX app it
     * will simply call launch, which gets JavaFX rolling, resulting in sending
     * the properly initialized Stage (i.e. window) to the start method inherited
     * from AppTemplate, defined in the Desktop Java Framework.
     * @param args
     */
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        launch(args);
    }
}

