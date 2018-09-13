/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pencilsimulator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joexi
 */
public class PencilSimulatorTest {
    
    PencilSimulator pencilsimulator;
    
    public PencilSimulatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        pencilsimulator = new PencilSimulator("This is a test piece of paper", 50, 20, 200);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void whenSimulatorStartedInitialPaperTextIsAsSpecified(){
        assertEquals("This is a test piece of paper", pencilsimulator.paperText);
    }
    
    @Test
    public void whenSimulatorStartedPencilVitalsAreAsSpecified(){
        assertTrue(50 == pencilsimulator.pointHealth);
        assertTrue(50 == pencilsimulator.MAX_POINT_HEALTH);
        assertTrue(20 == pencilsimulator.pencilLength);
        assertTrue(200 == pencilsimulator.eraserHealth);
    }
    
    @Test
    public void whenIWriteWithPencilStringIsAppended(){
        pencilsimulator.addText(" and we are writing on it");
        assertEquals(pencilsimulator.paperText, "This is a test piece of paper and we are writing on it");
    }
    
}
