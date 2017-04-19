/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.file;

import csg.CSGApp;
import csg.data.CSGData;
import csg.workspace.CSGWorkspace;
import djf.components.AppDataComponent;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author runnan
 */
public class CSGFilesTest {
    
    
    
    public CSGFilesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of saveData method, of class CSGFiles.
     */
    @Ignore
    @Test
    public void testSaveData() throws Exception {
        System.out.println("saveData");
        AppDataComponent data = null;
        String filePath = "..\\\\\\\\CSG\\\\\\\\work\\\\\\\\SiteSaveTest.json";
        CSGFiles instance = null;
        instance.saveData(data, filePath);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadData method, of class CSGFiles.
     */
    @Test
    public void testLoadData() throws Exception {
        System.out.println("loadData");
        CSGApp app=new CSGApp();
        app.buildAppComponentsHook();
      
        AppDataComponent data = app.getDataComponent();
        String filePath = "..\\\\\\\\CSG\\\\\\\\work\\\\\\\\SiteSaveTest.json";
        CSGFiles instance = new CSGFiles(app);
        instance.loadData(data, filePath);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of exportData method, of class CSGFiles.
     */
    @Ignore
    @Test
    public void testExportData() throws Exception {
        System.out.println("exportData");
        AppDataComponent data = null;
        String filePath = "";
        CSGFiles instance = null;
        instance.exportData(data, filePath);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of importData method, of class CSGFiles.
     */
    @Ignore
    @Test
    public void testImportData() throws Exception {
        System.out.println("importData");
        AppDataComponent data = null;
        String filePath = "";
        CSGFiles instance = null;
        instance.importData(data, filePath);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
