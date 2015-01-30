/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import bomberman.object.Pelaaja;
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
    public void getXOikein() {
        assertEquals(75, this.pelaaja.getX());
    }

    @Test
    public void getYOikein() {
        assertEquals(75, this.pelaaja.getY());
    }
    
    @Test
    public void getPommejaKaytossaOikein() {
        assertEquals(1, this.pelaaja.getPommejaKaytossa());
    }
    
    @Test
    public void getJattaakoPomminBooleanOikein() {
        assertEquals(false, this.pelaaja.getJattaakoPommin());
    }
    
    @Test
    public void getVoimaOikein() {
        assertEquals(1, this.pelaaja.getVoima());
    }
    
    @Test
    public void setVoimaOikein() {
        pelaaja.setVoima(0);
        assertEquals(1, this.pelaaja.getVoima());
        pelaaja.setVoima(9);
        assertEquals(1, this.pelaaja.getVoima());
        pelaaja.setVoima(8);
        assertEquals(8, this.pelaaja.getVoima());
        pelaaja.setVoima(1);
        assertEquals(1, this.pelaaja.getVoima());
    }
    
    @Test
    public void lisaaVoimaaOikein(){
        pelaaja.lisaaVoimaa();
        assertEquals(2, this.pelaaja.getVoima());
        pelaaja.setVoima(8);
        pelaaja.lisaaVoimaa();
        assertEquals(8, this.pelaaja.getVoima());
    }

    @Test
    public void setEiJataPommiaOikein() {
        pelaaja.setEiJataPommia();
        assertEquals(false, this.pelaaja.getJattaakoPommin());
    }
    
    @Test
    public void pelaajanSijaintiOikein() {
        assertEquals(75, this.pelaaja.getX());
        assertEquals(75, this.pelaaja.getY());
    }
    
    @Test
    public void getNopeusXOikein() {
        assertEquals(0, this.pelaaja.getNopeusX());
    }
    
    @Test
    public void getNopeusYOikein() {
        assertEquals(0, this.pelaaja.getNopeusY());
    }
    
    @Test
    public void setNopeusXOikein() {
        this.pelaaja.setNopeusX(1);
        assertEquals(1, this.pelaaja.getNopeusX());
        this.pelaaja.setNopeusX(-5);
        assertEquals(-5, this.pelaaja.getNopeusX());
    }
    
    @Test
    public void setNopeusYOikein() {
        this.pelaaja.setNopeusY(1);
        assertEquals(1, this.pelaaja.getNopeusY());
        this.pelaaja.setNopeusY(-5);
        assertEquals(-5, this.pelaaja.getNopeusY());
    }
    
    @Test
    public void pelaajaSiirtyyOikein(){
        this.pelaaja.siirra(5, 0);
        assertEquals(80, this.pelaaja.getX());
        this.pelaaja.siirra(0, -5);
        assertEquals(70, this.pelaaja.getY());
    }
    
    @Test
    public void eteneOikein(){
        this.pelaaja.etene();
        assertEquals(75, this.pelaaja.getY());
        assertEquals(75, this.pelaaja.getX());
        this.pelaaja.setNopeusX(2);
        this.pelaaja.setNopeusY(5);
        this.pelaaja.etene();
        assertEquals(80, this.pelaaja.getY());
        assertEquals(77, this.pelaaja.getX());
    }
    
    @Test
    public void getNopeusOikein(){
        assertEquals(5, this.pelaaja.getNopeus());
    }
    
    @Test
    public void getsetPommejaOikein(){
        assertEquals(1, this.pelaaja.getPommeja());
        this.pelaaja.setPommeja(4);
        assertEquals(4, this.pelaaja.getPommeja());        
    }
    
    @Test
    public void lisaaKaytettavaPommiOikein(){
        this.pelaaja.lisaaKaytettavaPommi();
        assertEquals(1, this.pelaaja.getPommejaKaytossa());
        this.pelaaja.setPommeja(2);
        this.pelaaja.lisaaKaytettavaPommi();
        assertEquals(2, this.pelaaja.getPommejaKaytossa());
        this.pelaaja.setPommeja(1);
        this.pelaaja.lisaaKaytettavaPommi();
        assertEquals(1, this.pelaaja.getPommejaKaytossa());
        
    }
    
    @Test
    public void jataPommiOikein(){
        this.pelaaja.jataPommi();
        assertEquals(0, this.pelaaja.getPommejaKaytossa());
        assertEquals(true, this.pelaaja.getJattaakoPommin());
        this.pelaaja.setEiJataPommia();
        this.pelaaja.jataPommi();
        assertEquals(0, this.pelaaja.getPommejaKaytossa());
        assertEquals(false, this.pelaaja.getJattaakoPommin());
    }
    
    @Test
    public void havisiPerkele(){
        assertEquals(false, this.pelaaja.getHavinnyt());
        this.pelaaja.havisi();
        assertEquals(true, this.pelaaja.getHavinnyt());
    }
    
    

}
