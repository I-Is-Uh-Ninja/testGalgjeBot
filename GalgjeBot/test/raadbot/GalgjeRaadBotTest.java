/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raadbot;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ian
 */
public class GalgjeRaadBotTest {
    GalgjeRaadBot instance;
    
    public GalgjeRaadBotTest() {
        instance = new GalgjeRaadBot();
    }

    @Test
    public void testRaadWoord() {
        System.out.println("raadWoord");
        String woord = "h-is";
        String expResult = "huis";
        String result = instance.raadWoord(woord);
        assertEquals(expResult, result);
    }
    
}
