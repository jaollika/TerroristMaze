/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import bomberman.object.Rajahdys;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author -
 */
public class RajahdysTest {
    public Rajahdys rajahdys;
    
    public RajahdysTest() {
    }
    
    @Before
    public void setUp() {
        this.rajahdys = new Rajahdys(1,1);
    }
    
    @Test 
    public void rajahdysLuodaanOikein(){
        assertEquals(1, this.rajahdys.getX());
        assertEquals(1, this.rajahdys.getY());
        assertEquals(10, this.rajahdys.getTimer());
    }
    
    @Test
    public void rajahdysEteneeOikein(){
        for (int j = 1; j <= 9; j++) {
            assertFalse(this.rajahdys.etene());
        }
        for (int i = 0; i < 10; i++) {
            assertTrue(this.rajahdys.etene());
        }
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
