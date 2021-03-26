package de.dominik_geyer.jtimesched;

import org.junit.Test;
import java.util.logging.Logger;

import static de.dominik_geyer.jtimesched.JTimeSchedApp.*;
import static org.junit.Assert.*;


public class TestJTimeSchedApp {



    /*********************************************/
    /**                                         **/
    /**      TESTES PARA AUMENTAR COVERAGE      **/
    /**                                         **/
    /*********************************************/

//    @Test
//    public void testGetAppVersion(){
//        assertEquals("Project version:", "1.2-SNAPSHOT", getAppVersion());
//    }

    @Test
    public void testGetLogger(){
        assertEquals(Logger.class , getLogger().getClass());
        assertEquals(Logger.getLogger("JTimeSched") , getLogger());
    }

    /*********************************************/
    /**                                         **/
    /**   FIM TESTES PARA AUMENTAR COVERAGE     **/
    /**                                         **/
    /*********************************************/
}
