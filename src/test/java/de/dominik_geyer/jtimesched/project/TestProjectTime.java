package de.dominik_geyer.jtimesched.project;

import org.junit.Test;
import java.text.ParseException;
import java.util.Date;
import static org.junit.Assert.*;

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
        assertEquals( 3632,ProjectTime.parseSeconds("1:0:32"));
    }

    @Test
    public void testParserSecondsP9T11() throws ParseException {
        assertEquals( 3632,ProjectTime.parseSeconds("1:00:32"));
    }

    @Test(expected = ParseException.class)
    public void testParserSecondsP10T12() throws ParseException {
        ProjectTime.parseSeconds("1:000:32");
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
        assertEquals(5520,ProjectTime.parseSeconds("1:32:0"));
    }

    @Test
    public void testParserSecondsP15T19() throws ParseException {
        assertEquals(5520,ProjectTime.parseSeconds("1:32:00"));
    }

    @Test(expected = ParseException.class)
    public void testParserSecondsP16T20() throws ParseException {
        ProjectTime.parseSeconds("1:32:000");
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
    public void testFormateDate(){

        Date tempDate = new Date();
        tempDate.setTime(312912781);


        String expected = "1970-01-04";

        assertEquals(expected, ProjectTime.formatDate(tempDate));


    }

    @Test
    public void testParseDate() throws ParseException {

        Date expectedDate  = new Date();
        expectedDate.setTime(312912781);
        expectedDate.setMinutes(0);
        expectedDate.setHours(0);
        expectedDate.setSeconds(0);

        assertEquals(expectedDate.toString(),ProjectTime.parseDate("1970-01-04").toString());
    }


    /*********************************************/
    /**                                         **/
    /**   FIM TESTES PARA AUMENTAR COVERAGE     **/
    /**                                         **/
    /*********************************************/


    /*********************************************/
    /**                                         **/
    /**         TESTES MUTATION TESTING         **/
    /**                                         **/
    /*********************************************/

    @Test
    public void testFormatSeconds(){


        assertEquals("1:05:50",ProjectTime.formatSeconds(3950));
    }


    /*********************************************/
    /**                                         **/
    /**      FIM TESTES MUTATION TESTING        **/
    /**                                         **/
    /*********************************************/



}
