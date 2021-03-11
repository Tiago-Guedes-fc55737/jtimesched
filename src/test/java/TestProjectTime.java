import de.dominik_geyer.jtimesched.project.ProjectTime;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;

public class TestProjectTime {


    /*********************************************/
    /**                                         **/
    /**                                         **/
    /**       TESTES CATEGORY PARTITION         **/
    /**                                         **/
    /**                                         **/
    /*********************************************/

    @Test(expected = ParseException.class)
    public void testParserSecondsWithNull() throws ParseException {
        ProjectTime.parseSeconds(null);
    }

    @Test(expected = ParseException.class)
    public void testParserSecondsWithCharacters() throws ParseException {
        ProjectTime.parseSeconds("as:32:32");
    }

    @Test(expected = ParseException.class)
    public void testParserSecondsWithNegativeNumbers() throws ParseException {
        ProjectTime.parseSeconds("9:-2:32");
    }

    @Test(expected = ParseException.class)
    public void testParserSecondsWithDifferentPattern() throws ParseException {
        ProjectTime.parseSeconds("3:32:2:32");
    }

    @Test
    public void testParserSecondsWithDesiredFormat() throws ParseException {
        assertEquals( 5552,ProjectTime.parseSeconds("1:32:32"));
    }

    @Test(expected = ParseException.class)
    public void testParserSecondsWithMinutesBiggerthen59() throws ParseException{
        ProjectTime.parseSeconds("1:60:32");
    }

    @Test(expected = ParseException.class)
    public void testParserSecondsWithSecondsBiggerthen59() throws ParseException{
        ProjectTime.parseSeconds("1:32:60");
    }


    /*********************************************/
    /**                                         **/
    /**                                         **/
    /**     FIM TESTES CATEGORY PARTITION       **/
    /**                                         **/
    /**                                         **/
    /*********************************************/



}
