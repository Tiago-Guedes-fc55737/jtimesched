package de.dominik_geyer.jtimesched.project;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.Date;

import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;


public class TestProject {

    Project project;

    @Before
    public void setProject(){
        project = new Project("test");
    }


    /*********************************************/
    /**                                         **/
    /**       TESTES CATEGORY PARTITION         **/
    /**                                         **/
    /*********************************************/
    @Test(expected = ProjectException.class)
    public void testPauseRunningFalse() throws ProjectException {
        project.pause();
    }

    @Test
    public void testPauseRunningTrue() throws InterruptedException, ProjectException {
        project.setRunning(true);

        assertEquals("Initial seconds overall",0, project.getSecondsOverall());
        assertEquals("Initial seconds today",0, project.getSecondsToday());
        TimeUnit.SECONDS.sleep(2);
        project.pause();
        assertEquals("Final seconds overall",2, project.getSecondsOverall());
        assertEquals("Final seconds today",2, project.getSecondsToday());
        assertFalse("Running after pause",project.isRunning());
    }

    @Test
    public void testAdjustSecondsTodayWithNegativeNumbers(){
        project.setSecondsOverall(4);
        assertEquals("Initial seconds overall",4, project.getSecondsOverall());
        assertEquals("Initial seconds today",0, project.getSecondsToday());
        project.adjustSecondsToday(-2);
        assertEquals("Final seconds overall",4, project.getSecondsOverall());
        assertEquals("Final seconds today",0, project.getSecondsToday());
    }

    @Test
    public void testAdjustSecondsTodayWithValueBiggerThenCurrent(){
        project.setSecondsOverall(7);
        project.setSecondsToday(3);
        assertEquals("Initial seconds overall",7, project.getSecondsOverall());
        assertEquals("Initial seconds today",3, project.getSecondsToday());
        project.adjustSecondsToday(5);
        assertEquals("Final seconds overall",9, project.getSecondsOverall());
        assertEquals("Final seconds today",5, project.getSecondsToday());
    }
    @Test
    public void testAdjustSecondsTodayWithSameValueAsCurrent(){
        project.setSecondsOverall(25);
        project.setSecondsToday(7);
        assertEquals("Initial seconds overall",25, project.getSecondsOverall());
        assertEquals("Initial seconds today",7, project.getSecondsToday());
        project.adjustSecondsToday(7);
        assertEquals("Final seconds overall",25, project.getSecondsOverall());
        assertEquals("Final seconds today",7, project.getSecondsToday());
    }
    @Test
    public void testAdjustSecondsTodayWithSmallerValueThenCurrent(){
        project.setSecondsOverall(35);
        project.setSecondsToday(14);
        assertEquals("Initial seconds overall",35, project.getSecondsOverall());
        assertEquals("Initial seconds today",14, project.getSecondsToday());
        project.adjustSecondsToday(7);
        assertEquals("Final seconds overall",28, project.getSecondsOverall());
        assertEquals("Final seconds today",7, project.getSecondsToday());
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

    @Test
    public void testAdjustSecondsTodayP1T1(){
        //Criar valores iniciais
        project.setSecondsOverall(4);
        project.setSecondsToday(2);

        project.adjustSecondsToday(-1);
        assertEquals("Seconds overall",2, project.getSecondsOverall());
        assertEquals("Seconds today",0, project.getSecondsToday());
    }

    @Test
    public void testAdjustSecondsTodayP2T2(){
        //Criar valores iniciais
        project.setSecondsOverall(4);
        project.setSecondsToday(2);

        project.adjustSecondsToday(0);
        assertEquals("Seconds overall",2, project.getSecondsOverall());
        assertEquals("Seconds today",0, project.getSecondsToday());
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

    @Test(expected = ProjectException.class)
    public void testGetElapsedSecondsRunningFalse() throws ProjectException {
        project.getElapsedSeconds();
    }

    @Test(expected = ProjectException.class)
    public void testStartRunningTrue() throws ProjectException {
        project.setRunning(true);
        project.start();
    }

    @Test
    public void testStartRunningFalse() throws ProjectException {
        project.start();

        assertEquals("Date de start nao corresponde à data atual",new Date() , project.getTimeStart()  );
        assertTrue("Running é esperado estar a true",project.isRunning());
    }

    @Test
    public void testToggleRunningTrue(){
        project.setRunning(true);
        project.toggle();
        assertFalse("Se running estava a true apos toggle é suposto passar a false ",project.isRunning());
    }

    @Test
    public void testToggleRunningFalse(){
        project.toggle();
        assertTrue("Se running estava a false apos toggle é suposto passar a true ",project.isRunning());
    }

    @Test
    public void testSetSecondsOverralWithNegativeSecodsValue(){
        project.setSecondsOverall(-3);
        assertEquals(0,project.getSecondsOverall());
    }

    @Test
    public void testSetSecondsTodayWithNegativeSecodsValue(){
        project.setSecondsToday(-3);
        assertEquals(0,project.getSecondsToday());
    }

    @Test
    public void testResetToday(){
        project.setSecondsToday(5);
        project.setQuotaToday(5);
        Date data = new Date();
        data.setTime(312312312);
        project.setTimeStart(data);

        project.resetToday();
        Date currentDate = new Date();

        assertEquals("Seconds Today:",0,project.getSecondsToday());
        assertEquals("QuotaToday:", 0,project.getQuotaToday());
        assertEquals("TimeStart:",currentDate, project.getTimeStart());

    }

    @Test
    public void testToStringWithRunningFalseAndCheckFalse(){
        assertEquals("Project [title=test, running=no, secondsOverall=0, secondsToday=0, checked=no]", project.toString() );
    }

    @Test
    public void testToStringWithRunningTrueAndCheckTrue(){
        project.setRunning(true);
        project.setChecked(true);
        assertEquals("Project [title=test, running=yes, secondsOverall=0, secondsToday=0, checked=yes]", project.toString() );
    }

    @Test
    public void testTitleSetterAndGetter() {

        assertEquals("Initial title (test getter):","test",project.getTitle() );
        project.setTitle("test2");
        assertEquals("After set title (test setter):", "test2",project.getTitle());

    }

    @Test
    public void testNotesSetterAndGetter() {

        assertEquals("Initial note (test getter):","",project.getNotes() );
        project.setNotes("Testing");
        assertEquals("After set note (test setter):", "Testing",project.getNotes());

    }

//    @Test
//    public void testTimeCreatedSetterAndGetter(){
//
//        assertEquals("Initial TimeCreated (test getter):",new Date(),project.getTimeCreated() );
//
//        Date tempDate = new Date();
//        tempDate.setTime(321893012);
//
//        project.setTimeCreated(tempDate);
//
//        assertEquals("After set TimeCreated (test setter):", tempDate,project.getTimeCreated());
//
//    }

    @Test
    public void testEmptyConstructor(){

        Project project = new Project();

        assertEquals("Project title:", "project",project.getTitle());
        assertEquals("Project notes","", project.getNotes());
        assertEquals("Project color",null,project.getColor());
        assertEquals("Project timeStart", new Date(), project.getTimeStart());
        assertEquals("Project timeCreated", new Date(), project.getTimeCreated());

    }

    @Test
    public void testColorSetterAndGetter() {

        assertEquals("Initial color (test getter):",null,project.getColor() );
        project.setColor(Color.BLACK);
        assertEquals("After set color (test setter):", Color.black,project.getColor());

    }

    @Test
    public void testisCheckedSetterAndGetter() {

        assertFalse("Initial checked (test getter):", project.isChecked());
        project.setChecked(true);
        assertTrue("After set checked (test setter):", project.isChecked());

    }

    @Test
    public void testQuotaOverallSetterAndGetter() {

        assertEquals("Initial Quota Overall (test getter):",0,project.getQuotaOverall() );
        project.setQuotaOverall(15);
        assertEquals("After set Quota Overall (test setter):", 15,project.getQuotaOverall());

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

    @Test
    public void testGetSecondsTodayIsRunningTrue(){
        project.setRunning(true);
        Date tempDate = new Date();
        tempDate.setTime(321893012);
        project.setTimeStart(tempDate);
        Date currentTime = new Date();
        int expected = (int) ((currentTime.getTime() - project.getTimeStart().getTime()) / 1000);

        assertEquals(expected,project.getSecondsToday());
    }

    @Test
    public void testGetSecondsTodayIsRunningFalse(){
        project.setRunning(false);
        Date tempDate = new Date();
        tempDate.setTime(321893012);
        project.setTimeStart(tempDate);

        int expected = project.getSecondsTodayForTesting();

        assertEquals(expected,project.getSecondsToday());
    }


    @Test
    public void testAdjustSecondsTodaySecondsTodayNegative(){
        project.setSecondsOverall(4);
        project.setSecondsToday(2);
        assertEquals("Initial seconds overall",4, project.getSecondsOverall());
        assertEquals("Initial seconds today",2, project.getSecondsToday());
        project.adjustSecondsToday(-1);
        assertEquals("Final seconds overall",2, project.getSecondsOverall());
        assertEquals("Final seconds today",0, project.getSecondsToday());
    }

    @Test
    public void testAdjustSecondsTodaySecondsTodayPositive(){
        project.setSecondsOverall(4);
        project.setSecondsToday(2);
        assertEquals("Initial seconds overall",4, project.getSecondsOverall());
        assertEquals("Initial seconds today",2, project.getSecondsToday());
        project.adjustSecondsToday(1);
        assertEquals("Final seconds overall",3, project.getSecondsOverall());
        assertEquals("Final seconds today",1, project.getSecondsToday());
    }

    /*********************************************/
    /**                                         **/
    /**      FIM TESTES DATAFLOW TESTING        **/
    /**                                         **/
    /*********************************************/


    /*********************************************/
    /**                                         **/
    /**         TESTES MUTATION TESTING         **/
    /**                                         **/
    /*********************************************/

    @Test
    public void testGetSecondsTodayForTesting(){
        project.setRunning(false);
        project.setSecondsToday(2);

        assertEquals(2,project.getSecondsTodayForTesting());
    }

    @Test
    public void testGetSecondsOverallWithRunningFalse(){

        project.setRunning(false);
        project.setSecondsOverall(3);

        assertEquals(3, project.getSecondsOverall());
    }

    @Test
    public void testGetSecondsOverallWithRunningTrue(){

        project.setRunning(true);
        Date tempDate = new Date();
        tempDate.setTime(321893012);
        project.setTimeStart(tempDate);
        Date currentTime = new Date();
        int expected = (int) ((currentTime.getTime() - project.getTimeStart().getTime()) / 1000);

        assertEquals(expected, project.getSecondsOverall());
    }


    @Test
    public void testGetQuotaToday(){
        project.setQuotaToday(3);
        assertEquals(3, project.getQuotaToday());
    }

    /*********************************************/
    /**                                         **/
    /**      FIM TESTES MUTATION TESTING        **/
    /**                                         **/
    /*********************************************/

}
