package de.dominik_geyer.jtimesched;

import org.junit.Test;
import java.io.*;
import java.nio.channels.FileLock;
import java.util.logging.Logger;
import static org.junit.Assert.*;

public class TestJTimeSchedApp {


    /*********************************************/
    /**                                         **/
    /**      TESTES PARA AUMENTAR COVERAGE      **/
    /**                                         **/
    /*********************************************/

    @Test(expected = Exception.class)
    public void testLockInstance() throws IOException {
        String[] args = null;
        JTimeSchedApp.main(args);
        final File file = new File(JTimeSchedApp.LOCK_FILE);
        final RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        final FileLock fileLock = randomAccessFile.getChannel().tryLock();
        fileLock.release();
        randomAccessFile.close();
        file.delete();

    }


    @Test
    public void testGetLogger() throws IOException {
        assertEquals("Verifica se devolve um logger:",Logger.class, JTimeSchedApp.getLogger().getClass());
        assertEquals("Verifica se devolve o logger correto",Logger.getLogger("JTimeSched"),JTimeSchedApp.getLogger());

    }

    /*********************************************/
    /**                                         **/
    /**   FIM TESTES PARA AUMENTAR COVERAGE     **/
    /**                                         **/
    /*********************************************/
}
