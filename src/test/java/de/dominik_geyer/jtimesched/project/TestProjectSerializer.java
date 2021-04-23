package de.dominik_geyer.jtimesched.project;


import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import static org.junit.Assert.*;

public class TestProjectSerializer {



    @Test
    public void testWriteReadXMLWithNoProjects() throws IOException, SAXException, TransformerConfigurationException, ParserConfigurationException {

        ProjectSerializer projectSerializer = new ProjectSerializer("testWriteReadXMLWithNoProjects.projects");

        ArrayList<Project> projectListToWrite = new ArrayList<>();

        projectSerializer.writeXml(projectListToWrite);

        ArrayList<Project> projectListFromReading = projectSerializer.readXml();

        assertEquals("ProjectList Size", 0,projectListFromReading.size());

    }


    @Test
    public void testWriteReadXMLWithDefaultProject() throws IOException, SAXException, TransformerConfigurationException, ParserConfigurationException {

        ProjectSerializer projectSerializer = new ProjectSerializer("testWriteReadXMLWithDefaultProject.projects");

        Date date = new Date();


        ArrayList<Project> projectListToWrite = new ArrayList<>();
        Project project = new Project("test");
        project.setTimeCreated(date);
        project.setTimeStart(date);
        projectListToWrite.add(project);


        projectSerializer.writeXml(projectListToWrite);

        ArrayList<Project> projectListFromReading = projectSerializer.readXml();

        assertEquals("ProjectList Size", 1,projectListFromReading.size());
        assertEquals("Project title:", "test",projectListFromReading.get(0).getTitle());
        assertEquals("Project notes","", projectListFromReading.get(0).getNotes());
        assertEquals("Project color",null,projectListFromReading.get(0).getColor());
        assertEquals("Project timeStart", date, projectListFromReading.get(0).getTimeStart());
        assertEquals("Project timeCreated", date, projectListFromReading.get(0).getTimeCreated());

    }

    @Test
    public void testWriteReadXMLWithOneProjectWithCustomValues() throws IOException, SAXException, TransformerConfigurationException, ParserConfigurationException {

        ProjectSerializer projectSerializer = new ProjectSerializer("testWriteReadXMLWithOneProjectWithCustomValues.projects");


        //This test is meant to exercise the branchs that didnt got exercised by the las
        Date date = new Date();

        ArrayList<Project> projectListToWrite = new ArrayList<>();
        Project project = new Project("");
        project.setTimeCreated(date);
        project.setTimeStart(date);
        project.setColor(Color.CYAN);
        project.setRunning(true);
        project.setChecked(true);
        project.setNotes("test");
        projectListToWrite.add(project);



        projectSerializer.writeXml(projectListToWrite);

        ArrayList<Project> projectListFromReading = projectSerializer.readXml();

        assertEquals("ProjectList Size", 1,projectListFromReading.size());
        assertEquals("Project title:", "",projectListFromReading.get(0).getTitle());
        assertEquals("Project notes","test", projectListFromReading.get(0).getNotes());
        assertEquals("Project color",Color.CYAN,projectListFromReading.get(0).getColor());
        assertEquals("Project timeStart", date, projectListFromReading.get(0).getTimeStart());
        assertEquals("Project timeCreated", date, projectListFromReading.get(0).getTimeCreated());

    }

    /*********************************************/
    /**                                         **/
    /**         TESTES MUTATION TESTING         **/
    /**                                         **/
    /*********************************************/

    @Test
    public void testWriteReadXMLForMutations() throws IOException, SAXException, TransformerConfigurationException, ParserConfigurationException {

        ProjectSerializer projectSerializer = new ProjectSerializer("testWriteReadXMLWithOneProjectWithCustomValues.projects");


        //This test is meant to exercise the branchs that didnt got exercised by the las
        Date date = new Date();

        ArrayList<Project> projectListToWrite = new ArrayList<>();
        Project project = new Project("");
        project.setTimeCreated(date);
        project.setTimeStart(date);
        project.setSecondsOverall(5);
        project.setSecondsToday(6);
        project.setQuotaOverall(7);
        project.setQuotaToday(8);
        project.setColor(Color.CYAN);
        project.setRunning(true);
        project.setChecked(true);
        project.setNotes("test");
        projectListToWrite.add(project);


        projectSerializer.writeXml(projectListToWrite);

        ArrayList<Project> projectListFromReading = projectSerializer.readXml();

        assertEquals("ProjectList Size", 1,projectListFromReading.size());
        assertEquals("Project title:", "",projectListFromReading.get(0).getTitle());
        assertEquals("Project notes","test", projectListFromReading.get(0).getNotes());
        assertEquals("Project color",Color.CYAN,projectListFromReading.get(0).getColor());
        assertEquals("Project timeStart", date, projectListFromReading.get(0).getTimeStart());
        assertEquals("Project timeCreated", date, projectListFromReading.get(0).getTimeCreated());
        assertTrue("Project Checked", projectListFromReading.get(0).isChecked());
        assertFalse("Project IsRunning", projectListFromReading.get(0).isRunning());
        assertEquals("Project Seconds Overall",5, projectListFromReading.get(0).getSecondsOverall());
        assertEquals("Project Seconds Today",6, projectListFromReading.get(0).getSecondsToday());
        assertEquals("Project Quota Overall",7, projectListFromReading.get(0).getQuotaOverall());
        assertEquals("Project Quota Today",8, projectListFromReading.get(0).getQuotaToday());


    }

    /*********************************************/
    /**                                         **/
    /**      FIM TESTES MUTATION TESTING        **/
    /**                                         **/
    /*********************************************/


}
