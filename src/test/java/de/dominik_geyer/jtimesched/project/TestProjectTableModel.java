package de.dominik_geyer.jtimesched.project;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;


public class TestProjectTableModel {

    Project project;
    ArrayList<Project> projectList;
    ProjectTableModel projectTableModel;


    @Before
    public void setUp() {

        project = new Project("test");
        projectList = new ArrayList<>();
        projectList.add(new Project("1"));
        projectList.add(new Project("2"));
        projectList.add(new Project("3"));
        projectList.add(new Project("4"));
        projectTableModel = new ProjectTableModel((ArrayList<Project>)projectList.clone());
        assertEquals("Initial number of projects", 4, projectTableModel.getArPrj().size()); // verifica se o numero de projetos adicionado no setup foi correto

    }


    /*********************************************/
    /**                                         **/
    /**       TESTES CATEGORY PARTITION         **/
    /**                                         **/
    /*********************************************/

    @Test
    public void testAddProjectExistent() {
        projectTableModel.addProject(project);
        projectList.add(project);
        assertEquals("Final number of projects", 5, projectTableModel.getArPrj().size()); // verifica o numero de projetos na lista aumentou
        assertEquals("Check if arrays are equal",projectList, projectTableModel.getArPrj());
    }


    @Test
    public void testAddProjectNull() {
        projectTableModel.addProject(null);
        assertEquals("Final number of projects", 4, projectTableModel.getArPrj().size()); // verifica o numero de projetos ficou o mesmo
        assertEquals("Check if arrays are equal",projectList, projectTableModel.getArPrj());

    }


    @Test(expected = ProjectException.class)
    public void testRemoveProjectRowNegative() throws ProjectException {
        projectTableModel.removeProject(-1);
    }


    @Test
    public void testRemoveProjectRealProjectRow() throws ProjectException {
        projectTableModel.removeProject(2);
        projectList.remove(2);
        assertEquals("Final number of projects", 3, projectTableModel.getArPrj().size());
        assertEquals("Final array list", projectList,projectTableModel.getArPrj());
    }

    @Test(expected = ProjectException.class)
    public void testRemoveProjectNumberAboveExistentMaxRows() throws ProjectException {
        projectTableModel.removeProject(8);
    }


    /*********************************************/
    /**                                         **/
    /**     FIM TESTES CATEGORY PARTITION       **/
    /**                                         **/
    /*********************************************/


    /*********************************************/
    /**                                         **/
    /**      TESTES BOUNDARY VALUE ANALYSIS     **/
    /**                                         **/
    /*********************************************/

    @Test(expected = ProjectException.class)
    public void testRemoveProjectP1T1() throws ProjectException {
        projectTableModel.removeProject(-1);
    }

    @Test
    public void testRemoveProjectP2T2() throws ProjectException {
        projectTableModel.removeProject(0);
        assertEquals(3,projectTableModel.getArPrj().size());
    }

    @Test
    public void testRemoveProjectP2T3() throws ProjectException {
        projectTableModel.removeProject(3);
        assertEquals(3,projectTableModel.getArPrj().size());
    }

    @Test(expected = ProjectException.class)
    public void testRemoveProjectP3T4() throws ProjectException {
        projectTableModel.removeProject(4);
    }

    /*********************************************/
    /**                                         **/
    /**   FIM TESTES BOUNDARY VALUE ANALYSIS    **/
    /**                                         **/
    /*********************************************/


    /*********************************************/
    /**                                         **/
    /**      TESTES PARA AUMENTAR COVERAGE      **/
    /**                                         **/
    /*********************************************/

    @Test
    public void testGetValueAtColumnActionDelete(){
        assertFalse((boolean)projectTableModel.getValueAt(0,0));
    }
    @Test
    public void testGetValueAtColumnCheckTrue(){
        projectTableModel.getArPrj().get(0).setChecked(true);
        assertTrue((boolean) projectTableModel.getValueAt(0,1));
    }
    @Test
    public void testGetValueAtColumnCheckFalse(){
        assertFalse((boolean) projectTableModel.getValueAt(0,1));
    }
    @Test
    public void testGetValueAtColumnTitle(){
        assertEquals("1", projectTableModel.getValueAt(0,2));
    }
    @Test
    public void testGetValueAtColumnColor(){
        assertEquals(null, projectTableModel.getValueAt(0,3));
    }
    @Test
    public void testGetValueAtColumnCreated(){
        Date tempDate = new Date();
        projectTableModel.getProjectAt(0).setTimeCreated(tempDate);

        assertEquals(tempDate, projectTableModel.getValueAt(0,4));
    }
    @Test
    public void testGetValueAtColumnTimeOverall(){

        assertEquals(0, projectTableModel.getValueAt(0,5));
    }
    @Test
    public void testGetValueAtColumnTimeToday(){

        assertEquals(0, projectTableModel.getValueAt(0,6));
    }
    @Test
    public void testGetValueAtColumnActionStartPauseRunningTrue(){
        projectTableModel.getArPrj().get(0).setRunning(true);
        assertTrue((boolean) projectTableModel.getValueAt(0,7));
    }
    @Test
    public void testGetValueAtColumnActionStartPauseRunningFalse(){
        assertFalse((boolean) projectTableModel.getValueAt(0,7));
    }
    @Test
    public void testGetValueAtColumnDefault(){
        assertEquals("wtf?",projectTableModel.getValueAt(0,9));
    }


    @Test
    public void testGetColumnClassColumnColor(){
        assertEquals(Color.class , projectTableModel.getColumnClass(3));
    }
    @Test
    public void testGetColumnClassColumnCreated(){
        assertEquals(Date.class , projectTableModel.getColumnClass(4));
    }
    @Test
    public void testGetColumnClassColumnTimeOverall(){
        assertEquals(Integer.class , projectTableModel.getColumnClass(5));
    }
    @Test
    public void testGetColumnClassColumnTimeToday(){
        assertEquals(Integer.class , projectTableModel.getColumnClass(6));
    }
    @Test
    public void testGetColumnClassColumnChecked(){
        assertEquals(Boolean.class , projectTableModel.getColumnClass(1));
    }
    @Test
    public void testGetColumnClassColumnActionDelete(){
        assertEquals(Boolean.class , projectTableModel.getColumnClass(0));
    }
    @Test
    public void testGetColumnClassColumnStartPause(){
        assertEquals(Boolean.class , projectTableModel.getColumnClass(7));
    }
    @Test
    public void testGetColumnClassColumnTitle(){
        assertEquals(String.class , projectTableModel.getColumnClass(2));
    }


    @Test
    public void testIsCellEditableColumnActionDelete(){
        assertFalse(projectTableModel.isCellEditable(0,0));
    }
    @Test
    public void testIsCellEditableColumnCheck(){
        assertTrue(projectTableModel.isCellEditable(0,1));
    }
    @Test
    public void testIsCellEditableColumnTitle(){
        assertTrue(projectTableModel.isCellEditable(0,2));
    }
    @Test
    public void testIsCellEditableColumnColor(){
        assertTrue(projectTableModel.isCellEditable(0,3));
    }
    @Test
    public void testIsCellEditableColumnCreated(){
        assertTrue(projectTableModel.isCellEditable(0,4));
    }
    @Test
    public void testIsCellEditableColumnTimeOverallRunningFalse(){
        assertTrue(projectTableModel.isCellEditable(0,5));
    }
    @Test
    public void testIsCellEditableColumnTimeOverallRunningTrue(){
        projectTableModel.getArPrj().get(0).setRunning(true);
        assertFalse(projectTableModel.isCellEditable(0,5));
    }
    @Test
    public void testIsCellEditableColumnTimeTodayRunningFalse(){
        assertTrue(projectTableModel.isCellEditable(0,6));
    }
    @Test
    public void testIsCellEditableColumnTimeTodayRunningTrue(){
        projectTableModel.getArPrj().get(0).setRunning(true);
        assertFalse(projectTableModel.isCellEditable(0,6));
    }



    @Test
    public void testSetValueAtColumnActionDelete(){
        projectTableModel.setValueAt("test",0,0);

        assertFalse((boolean) projectTableModel.getValueAt(0,0));
    }
    @Test
    public void testSetValueAtColumnCheckTrue(){
        projectTableModel.setValueAt(true,0,1);

        assertTrue(projectTableModel.getArPrj().get(0).isChecked());

    }
    @Test
    public void testSetValueAtColumnCheckFalse(){
        projectTableModel.setValueAt(false,0,1);

        assertFalse(projectTableModel.getArPrj().get(0).isChecked());

    }
    @Test
    public void testSetValueAtColumnTitle(){
        projectTableModel.setValueAt("Teste",0,2);

        assertEquals("Teste",projectTableModel.getArPrj().get(0).getTitle());

    }
    @Test
    public void testSetValueAtColumnColor(){
        projectTableModel.setValueAt(Color.white,0,3);

        assertEquals(Color.white,projectTableModel.getArPrj().get(0).getColor());

    }
    @Test
    public void testSetValueAtColumnCreated(){
        Date tempDate = new Date();
        tempDate.setTime(873129737);

        projectTableModel.setValueAt(tempDate,0,4);

        assertEquals(tempDate,projectTableModel.getArPrj().get(0).getTimeCreated());

    }
    @Test
    public void testSetValueAtColumnTimeOverall(){
        projectTableModel.setValueAt(23,0,5);

        assertEquals(23,projectTableModel.getArPrj().get(0).getSecondsOverall());

    }
    @Test
    public void testSetValueAtColumnTimeToday(){
        projectTableModel.setValueAt(15,0,6);

        assertEquals(15,projectTableModel.getArPrj().get(0).getSecondsToday());

    }



    @Test
    public void testGetColumnCount(){
        assertEquals(8,projectTableModel.getColumnCount());
    }

    @Test
    public void testGetColumnNameActionDelete(){
        assertEquals("",projectTableModel.getColumnName(0));

    }
    @Test
    public void testGetColumnNameCheck(){
        assertEquals("",projectTableModel.getColumnName(1));
    }
    @Test
    public void testGetColumnNameTitle(){
        assertEquals("Title",projectTableModel.getColumnName(2));
    }
    @Test
    public void testGetColumnNameColor(){
        assertEquals("",projectTableModel.getColumnName(3));
    }
    @Test
    public void testGetColumnNameCreated(){
        assertEquals("Created",projectTableModel.getColumnName(4));
    }
    @Test
    public void testGetColumnNameTimeOverall(){
        assertEquals("Time Overall",projectTableModel.getColumnName(5));
    }
    @Test
    public void testGetColumnNameTimeToday(){
        assertEquals("Time Today",projectTableModel.getColumnName(6));
    }
    @Test
    public void testGetColumnNameActionStartPause(){
        assertEquals("",projectTableModel.getColumnName(7));
    }


    /*********************************************/
    /**                                         **/
    /**   FIM TESTES PARA AUMENTAR COVERAGE     **/
    /**                                         **/
    /*********************************************/


    /*********************************************/
    /**                                         **/
    /**         TESTES DATAFLOW TESTING         **/
    /**                                         **/
    /*********************************************/

    @Test(expected = ProjectException.class)
    public void testRemoveProjectRowNegativeValue() throws ProjectException {
        projectTableModel.removeProject(-1);
    }

    @Test(expected = ProjectException.class)
    public void testRemoveProjectWithRowValueAboveExistingProjectsSize() throws ProjectException {
        projectTableModel.removeProject(5);
    }

    @Test
    public void testRemoveProjectWithRowValueBellowExistingProjectsSize() throws ProjectException {
        projectTableModel.removeProject(3);
        projectList.remove(3);
        assertEquals("Final number of projects", 3, projectTableModel.getArPrj().size());
        assertEquals("Final array list", projectList,projectTableModel.getArPrj());
    }



    /*********************************************/
    /**                                         **/
    /**      FIM TESTES DATAFLOW TESTING        **/
    /**                                         **/
    /*********************************************/
}
