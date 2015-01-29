/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

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
        this.pommi1 = new Pommi(1,1,1,1);
        this.pommi2 = new Pommi(1,1,1,2);
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
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
