/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package woordbot;

import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Ian
 */
public class WoordlijstProcessorTest {
    File woordenlijst;
    
    public WoordlijstProcessorTest() {
        System.out.println("decreaseList");
        File file = new File("dictsmaller.txt");
        int min = 3;
        int max = 8;
        WoordlijstProcessor.decreaseList(file, min, max);
        woordenlijst = new File("woordenlijst.txt");
    }
    
    @BeforeClass
    public static void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testDecreaseList() {
        assertTrue(woordenlijst.exists());
    }

    @Test
    public void testZoekWoord() {
        System.out.println("zoekWoord");
        int lijnNummer = 1;
        WoordlijstProcessor instance = new WoordlijstProcessor();
        String expResult = "aan";
        String result = instance.zoekWoord(lijnNummer);
        assertEquals(expResult, result);
    }
    
}
