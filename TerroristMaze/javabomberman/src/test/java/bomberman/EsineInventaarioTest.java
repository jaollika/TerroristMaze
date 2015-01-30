/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import bomberman.object.Pommi;
import bomberman.object.Rajahdys;
import bomberman.object.EsineInventaario;
import bomberman.object.Seina;
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
    public void pelaajatEripaikoissa(){
        assertFalse(inventaario.getPelaaja(1).getX() == inventaario.getPelaaja(2).getX());
        assertFalse(inventaario.getPelaaja(1).getY() == inventaario.getPelaaja(2).getY());
    } 
    
    @Test
    public void seiniaOikeissaPaikoissa(){
        boolean test = true;
        for (Seina s1 : this.inventaario.getSeinat()) {
            if(s1.getX() > 11 || s1.getX()< 1){
                test = false;
            }
            if(s1.getY() > 11 ||s1.getY() < 1){
                test = false;
            }
        }
        assertTrue(test);
    }

    @Test
    public void seinatHaetaanOikein() {
        assertEquals(true, this.inventaario.getSeinat().isEmpty());
    }

    @Test
    public void seinatListaSisaltaaVainSeinia() {
        for (Seina seina : this.inventaario.getSeinat()) {
            assertEquals(Seina.class, seina.getClass());
        }
    }

    @Test
    public void antaaPelaajanOikein() {
        assertEquals(true, this.inventaario.getPelaaja(1).equals(this.inventaario.getPelaaja(1)));
        assertEquals(false, this.inventaario.getPelaaja(2).equals(this.inventaario.getPelaaja(1)));
    }

    @Test
    public void lisaaPomminListaan() {
        inventaario.lisaaPommi(0, 0, 1, 1);
        assertFalse(inventaario.getPommit().isEmpty());
    }
    
    @Test
    public void pommiListaAsetetaanOikein(){
        ArrayList<Pommi> l1 = this.inventaario.getPommit();
        ArrayList<Pommi> l2 = new ArrayList<Pommi>();
        this.inventaario.setPommilista(l2);
        l1 = this.inventaario.getPommit();
        assertEquals(l1, l2);
    }
    
    @Test
    public void getRajahdyksetOikein(){
        assertTrue(this.inventaario.getRajahdykset().isEmpty());
        ArrayList<Rajahdys> r = new ArrayList<Rajahdys>();
        assertEquals(r.getClass(), this.inventaario.getRajahdykset().getClass());
    }
    
    @Test
    public void lisaaRajahdyksenListaan() {
        inventaario.luoRajahdys(1, 1);
        assertFalse(this.inventaario.getRajahdykset().isEmpty());
    }
    @Test
    public void rajahdysListaAsetetaanOikein(){
        ArrayList<Rajahdys> l1 = this.inventaario.getRajahdykset();
        ArrayList<Rajahdys> l2 = new ArrayList<Rajahdys>();
        this.inventaario.setUusiRajahdysLista(l2);
        l1 = this.inventaario.getRajahdykset();
        assertEquals(l1, l2);
    }
    @Test
    public void seinaListaAsetetaanOikein(){
        ArrayList<Seina> l1 = this.inventaario.getSeinat();
        ArrayList<Seina> l2 = new ArrayList<Seina>();
        this.inventaario.setUusiSeinaLista(l2);
        l1 = this.inventaario.getSeinat();
        assertEquals(l1, l2);
    }

}
