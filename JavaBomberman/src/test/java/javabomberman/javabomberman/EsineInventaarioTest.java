/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabomberman.javabomberman;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jaollika@cs
 */
public class EsineInventaarioTest {
    
    private EsineInventaario inventaario;
    
    @Before
    public void setUp() {
        this.inventaario = new EsineInventaario();
    }

    @Test
    public void seinatHaetaanOikein() {
        assertEquals(false, this.inventaario.getSeinat().isEmpty());
    }

    @Test
    public void seinatListaSisaltaaVainSeinia() {
        for (Esine seina : this.inventaario.getSeinat()) {
            assertEquals(Seina.class, seina.getClass());
        }
    }

    @Test
    public void antaaPelaajanOikein() {
        assertEquals(true, this.inventaario.getPelaaja(1).equals(this.inventaario.getPelaaja(1)));
        assertEquals(false, this.inventaario.getPelaaja(2).equals(this.inventaario.getPelaaja(1)));
        
   }
    
}
