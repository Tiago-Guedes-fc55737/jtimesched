import de.dominik_geyer.jtimesched.project.Project;
import de.dominik_geyer.jtimesched.project.ProjectException;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class TestProject {

    Project project;

    @Before
    public void setProject(){
        project = new Project("test");
    }

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



}
