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
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void whenSimulatorStartedInitialPaperTextIsAsSpecified(){
        PencilSimulator pencilsimulator = new PencilSimulator("This is a test piece of paper");
        assertEquals("This is a test piece of paper", pencilsimulator.paperText);
    }
    
}
