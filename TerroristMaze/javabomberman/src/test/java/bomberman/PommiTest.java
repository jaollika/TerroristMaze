/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import bomberman.object.Pommi;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author -
 */
public class PommiTest {
    private Pommi pommi1;
    private Pommi pommi2;
    
    public PommiTest() {
    }
    
    @Before
    public void setUp() {
        this.pommi1 = new Pommi(50,50,1,1);
        this.pommi2 = new Pommi(50,50,1,2);
    }

    @Test 
    public void pommiLuodaanOikein(){
        for (int i = 0; i < 10; i++) {
            assertEquals(i, new Pommi(1,1,1,i).getOmistaja());
        }
    }
    
    @Test
    public void getVoimaTest(){
        for (int i = 0; i < 10; i++) {
            assertEquals(i, new Pommi(1, 1, i, 1).getVoima());
        }
    }
    
    @Test
    public void paikkaOikeinAlleRajan() {
        Pommi p1 = new Pommi(45, 45, 1, 1);
        assertEquals(1, p1.getX());
        assertEquals(1, p1.getY());
    }

    public void paikkaOikeinYliRajan() {
        Pommi p1 = new Pommi(55, 55, 1, 1);
        assertEquals(1, p1.getX());
        assertEquals(1, p1.getY());
    }
}
    // TODO add test methods here.
// The methods must be annotated with annotation @Test. For example:
//
// @Test
// public void hello() {}

