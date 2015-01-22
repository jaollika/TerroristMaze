/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabomberman.javabomberman;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jaollika@cs
 */
public class PelaajaTest {
    private Pelaaja pelaaja;
    
    @Before
    public void setUp() {
        this.pelaaja = new Pelaaja(75, 75);
    }

    @Test
    public void pelaajanSijaintiOikein() {
        assertEquals(75, this.pelaaja.getX());
        assertEquals(75, this.pelaaja.getY());
    }

    @Test
    public void muuttaaNopeuttaXJaEteneeOikein() {
        pelaaja.muutaNopeusX(2);
        pelaaja.muutaNopeusX(-1);
        pelaaja.etene();
        assertEquals(74, this.pelaaja.getX());
    }
    
    public void muuttaaNopeuttaYJaEteneeOikein() {
        pelaaja.muutaNopeusX(5);
        pelaaja.muutaNopeusY(1);
        pelaaja.etene();
        pelaaja.etene();
        assertEquals(77, this.pelaaja.getY());
    }


    
}
