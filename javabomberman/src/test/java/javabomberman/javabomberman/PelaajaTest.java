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
    public void pelaajaLiikkuu() {
        pelaaja.muutaNopeusX(1);
        pelaaja.muutaNopeusY(1);
        pelaaja.etene();
        assertTrue(80 == this.pelaaja.getX());
        assertTrue(80 == this.pelaaja.getY());
    }

    @Test
    public void muuttaaNopeuttaXJaEteneeOikein() {
        pelaaja.muutaNopeusX(2);
        pelaaja.muutaNopeusX(-1);
        pelaaja.etene();
        assertEquals(70, this.pelaaja.getX());
    }

    @Test
    public void muuttaaNopeuttaYJaEteneeOikein() {
        pelaaja.muutaNopeusX(5);
        pelaaja.muutaNopeusY(1);
        pelaaja.etene();
        pelaaja.etene();
        assertEquals(85, this.pelaaja.getY());
    }

    @Test
    public void aikooJattaaPommin() {
        pelaaja.jataPommi();
        assertTrue(pelaaja.getJattaakoPommin());
    }

    @Test
    public void pomminJattoVahentaaJalellaOleviaPommeja() {
        pelaaja.jataPommi();
        assertTrue(pelaaja.getPommejaKaytossa() == 0);
    }

    @Test
    public void lisaaKaytettaviaPommejaEiAnnaYlimaaraisia(){
        pelaaja.lisaaKaytettavaPommi();
        assertEquals(1, pelaaja.getPommejaKaytossa());
        pelaaja.lisaaKaytettavaPommi();
        pelaaja.lisaaKaytettavaPommi();
        assertEquals(1, pelaaja.getPommejaKaytossa());
    }
    
}
