import de.dominik_geyer.jtimesched.project.ProjectTime;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;

public class TestProjectTime {


    /*********************************************/
    /**                                         **/
    /**       TESTES CATEGORY PARTITION         **/
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
    /**     FIM TESTES CATEGORY PARTITION       **/
    /**                                         **/
    /*********************************************/



    /*********************************************/
    /**                                         **/
    /**      TESTES BOUNDARY VALUE ANALYSIS     **/
    /**                                         **/
    /*********************************************/


    @Test(expected = ParseException.class)
    public void testParserSecondsP1T1() throws ParseException {
        ProjectTime.parseSeconds("-1:32:32");
    }

    @Test
    public void testParserSecondsP2T2() throws ParseException {
        assertEquals( 1952,ProjectTime.parseSeconds("0:32:32"));

    }

    @Test(expected = ParseException.class)
    public void testParserSecondsP3T3() throws ParseException {
        ProjectTime.parseSeconds(":32:32");
    }

    @Test
    public void testParserSecondsP4T4() throws ParseException {
        assertEquals( 5552,ProjectTime.parseSeconds("1:32:32"));
    }

    @Test(expected = ParseException.class)
    public void testParserSecondsP5T5() throws ParseException {
        ProjectTime.parseSeconds("0:-1:32");
    }

    @Test
    public void testParserSecondsP6T6() throws ParseException {
        assertEquals( 3632,ProjectTime.parseSeconds("1:0:32"));
    }

    @Test
    public void testParserSecondsP6T7() throws ParseException {
        assertEquals( 7172,ProjectTime.parseSeconds("1:59:32"));

    }

    @Test(expected = ParseException.class)
    public void testParserSecondsP7T8() throws ParseException {
        ProjectTime.parseSeconds("1:60:32");
    }

    @Test(expected = ParseException.class)
    public void testParserSecondsP8T9() throws ParseException {
        ProjectTime.parseSeconds("1::32");
    }

    @Test
    public void testParserSecondsP9T10() throws ParseException {
        assertEquals( 3692,ProjectTime.parseSeconds("1:1:32"));
    }

    @Test
    public void testParserSecondsP9T11() throws ParseException {
        assertEquals( 4352,ProjectTime.parseSeconds("1:12:32"));
    }

    @Test(expected = ParseException.class)
    public void testParserSecondsP10T12() throws ParseException {
        ProjectTime.parseSeconds("1:123:32");
    }

    @Test(expected = ParseException.class)
    public void testParserSecondsP11T13() throws ParseException {
        ProjectTime.parseSeconds("1:32:-1");
    }

    @Test
    public void testParserSecondsP12T14() throws ParseException {
        assertEquals(5520,ProjectTime.parseSeconds("1:32:0"));
    }

    @Test
    public void testParserSecondsP12T15() throws ParseException {
        assertEquals(5579,ProjectTime.parseSeconds("1:32:59"));
    }

    @Test(expected = ParseException.class)
    public void testParserSecondsP13T16() throws ParseException {
        ProjectTime.parseSeconds("1:32:60");
    }

    @Test(expected = ParseException.class)
    public void testParserSecondsP14T17() throws ParseException {
        ProjectTime.parseSeconds("1:32:");
    }

    @Test
    public void testParserSecondsP15T18() throws ParseException {
        assertEquals(5521,ProjectTime.parseSeconds("1:32:1"));
    }

    @Test
    public void testParserSecondsP15T19() throws ParseException {
        assertEquals(5532,ProjectTime.parseSeconds("1:32:12"));
    }

    @Test(expected = ParseException.class)
    public void testParserSecondsP16T20() throws ParseException {
        ProjectTime.parseSeconds("1:32:123");
    }


    /*********************************************/
    /**                                         **/
    /**   FIM TESTES BOUNDARY VALUE ANALYSIS    **/
    /**                                         **/
    /*********************************************/


}
