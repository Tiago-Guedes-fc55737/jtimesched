import de.dominik_geyer.jtimesched.project.Project;
import de.dominik_geyer.jtimesched.project.ProjectException;
import de.dominik_geyer.jtimesched.project.ProjectTableModel;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;


import static org.junit.Assert.assertEquals;

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
    public void testRemoveProjectRowNegative() {
        projectTableModel.removeProject(-1);
    }


    @Test
    public void testRemoveProjectRealProjectRow() {
        projectTableModel.removeProject(2);
        projectList.remove(2);
        assertEquals("Final number of projects", 3, projectTableModel.getArPrj().size());
        assertEquals("Final array list", projectList,projectTableModel.getArPrj());
    }

    @Test(expected = ProjectException.class)
    public void testRemoveProjectNumberAboveExistentMaxRows() {
        projectTableModel.removeProject(8);
    }



}
