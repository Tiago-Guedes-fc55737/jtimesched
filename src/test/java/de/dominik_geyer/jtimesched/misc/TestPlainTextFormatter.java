package de.dominik_geyer.jtimesched.misc;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import static org.junit.Assert.assertEquals;

public class TestPlainTextFormatter {

    @Test
    public void testPlainText(){
        PlainTextFormatter plainTextFormatter = new PlainTextFormatter();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd (E) HH:mm:ss");

        Date tempDate = new Date();
        String actual = plainTextFormatter.format(new LogRecord(Level.ALL,"test"));

        String expected = sdf.format(tempDate) + " [" + Level.ALL + "]: " + "test";

        assertEquals(expected.trim(),actual.trim());

    }
}
