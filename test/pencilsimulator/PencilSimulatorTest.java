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
    public void writerWantsToWriteWithPencilToBetterRememberThoughts(){
        pencilsimulator.addText(" and we are writing on it");
        assertEquals(pencilsimulator.paperText, "This is a test piece of paper and we are writing on it");
    }
    
    @Test
    public void spacesAndNewLinesCostNothing(){
        assertTrue(pencilsimulator.calculateWritingCost("\n") == 0);
        assertTrue(pencilsimulator.calculateWritingCost(" ") == 0);
        assertTrue(pencilsimulator.calculateWritingCost(" \n") == 0);
        assertTrue(pencilsimulator.calculateWritingCost("\n ") == 0);
    }
    
    @Test
    public void spacesAndNewLinesCostNothingAndLowerCasesCostOnePoint(){
        assertTrue(pencilsimulator.calculateWritingCost("a\n") == 1);
        assertTrue(pencilsimulator.calculateWritingCost("b ") == 1);
        assertTrue(pencilsimulator.calculateWritingCost(" \nc") == 1);
        assertTrue(pencilsimulator.calculateWritingCost("\n d") == 1);
    }
    
    @Test
    public void spacesAndNewLinesCostNothingLowerCasesCostOnePointUpperCasesCostTwo(){
        assertTrue(pencilsimulator.calculateWritingCost("A\n") == 2);
        assertTrue(pencilsimulator.calculateWritingCost("aA\n") == 3);
        assertTrue(pencilsimulator.calculateWritingCost(" B") == 2);
        assertTrue(pencilsimulator.calculateWritingCost("b B") == 3);
        assertTrue(pencilsimulator.calculateWritingCost(" \nC") == 2);
        assertTrue(pencilsimulator.calculateWritingCost(" \ncC") == 3);
        assertTrue(pencilsimulator.calculateWritingCost("\n D") == 2);
        assertTrue(pencilsimulator.calculateWritingCost("\n dD") == 3);
    }
    
    @Test
    public void letsAssumeOtherCharactersCostOne(){
        assertTrue(pencilsimulator.calculateWritingCost("Total is 5.56") == 12);
        assertTrue(pencilsimulator.calculateWritingCost("Phone # is 734-972-8096") == 21);
        assertTrue(pencilsimulator.calculateWritingCost("Smith Heating & Cooling") == 23);
        assertTrue(pencilsimulator.calculateWritingCost("2 + 2 = 4") == 5);
        assertTrue(pencilsimulator.calculateWritingCost("I am 'working'") == 13);
        assertTrue(pencilsimulator.calculateWritingCost("See: item 1, item 2, and item 3") == 25);
    }
    
    @Test
    public void pencilManufacturerWantsPencilsToGoDullToSellMorePencils(){
        pencilsimulator = new PencilSimulator("", 50, 20, 200);
        Integer tpHealth = pencilsimulator.addText("Writing costs lead");
        assertTrue(33 == pencilsimulator.pointHealth);
    }
    
    @Test
    public void whenAZeroPointPencilWritesItsHealthBecomesNegative(){
        pencilsimulator = new PencilSimulator("", 0, 20, 200);
        assertTrue(pencilsimulator.addText("really") == -6);
        assertTrue(pencilsimulator.addText("Really") == -7);
    }
    
    @Test
    public void zeroPointPencilsWriteSpaces(){
        pencilsimulator = new PencilSimulator("Let us do six spaces after this", 0, 20, 200);
        pencilsimulator.addText("really");
        assertEquals(pencilsimulator.paperText, "Let us do six spaces after this      ");
        pencilsimulator = new PencilSimulator("Let us do six spaces after this", 0, 20, 200);
        pencilsimulator.addText("Really");
        assertEquals(pencilsimulator.paperText, "Let us do six spaces after this      ");
    }
    
    @Test 
    public void whenIWriteWithAPencilOfLittleHealthItWritesThenSpacesOut(){
        pencilsimulator = new PencilSimulator("Let us do 3 spaces after this", 3, 20, 200);
        pencilsimulator.addText(" really");
        assertEquals(pencilsimulator.paperText, "Let us do 3 spaces after this rea   ");
        pencilsimulator = new PencilSimulator("Let us do 4 spaces after this", 3, 20, 200);
        pencilsimulator.addText(" Really");
        assertEquals(pencilsimulator.paperText, "Let us do 4 spaces after this Re    ");
    }
    
    @Test
    public void writerWantsToSharpenPencilToKeepWritingAfterItDulls(){
        pencilsimulator = new PencilSimulator("Sharpening pencils", 4, 20, 200);
        pencilsimulator.addText(" is A chore");
        assertEquals(pencilsimulator.paperText, "Sharpening pencils is A      ");
        pencilsimulator.sharpenPencil();
        pencilsimulator.addText("pain");
        assertEquals(pencilsimulator.paperText, "Sharpening pencils is A      pain");
    }
    
}
