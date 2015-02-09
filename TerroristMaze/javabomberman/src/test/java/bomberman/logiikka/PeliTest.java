/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman.logiikka;

import bomberman.object.Pommi;
import bomberman.object.Seina;
import bomberman.object.Rajahdys;
import bomberman.Peli;
import bomberman.object.PowerUp;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jaollika@cs
 */
public class PeliTest {

    private Peli peli;

    public PeliTest() {
    }

    @Before
    public void setUp() throws InterruptedException {
        this.peli = new Peli();
    }

    @Test
    public void etenePelaajatOikeinNopeusX() {
        peli.getInventaario().getPelaaja(1).setNopeusX(1);
        peli.getInventaario().getPelaaja(1).siirra(-12, -12);
        peli.etenePelaajat();
        assertEquals(55, this.peli.getInventaario().getPelaaja(1).getX());
    }

    @Test
    public void etenePelaajatOikeinNopeusY() {
        peli.getInventaario().getPelaaja(1).setNopeusY(1);
        peli.getInventaario().getPelaaja(1).siirra(-12, -12);
        peli.etenePelaajat();
        assertEquals(55, this.peli.getInventaario().getPelaaja(1).getY());
    }

    @Test
    public void jataPommitPoistaaBoolean() {
        this.peli.jataPommit();
        assertFalse(this.peli.getInventaario().getPelaaja(1).getJattaakoPommin());
        assertFalse(this.peli.getInventaario().getPelaaja(2).getJattaakoPommin());
    }

    @Test
    public void jataPommitlisaaPomminJosOnJatettavaa() {
        this.peli.jataPommit();
        assertTrue(this.peli.getInventaario().getPommit().isEmpty());
        this.peli.getInventaario().getPelaaja(1).jataPommi();
        this.peli.jataPommit();
        assertFalse(this.peli.getInventaario().getPommit().isEmpty());
    }

    @Test
    public void jataPommitNollaaJaton() {
        this.peli.getInventaario().getPelaaja(1).jataPommi();
        this.peli.jataPommit();
        assertFalse(this.peli.getInventaario().getPelaaja(1).getJattaakoPommin());
    }

    @Test
    public void etenePommitLuoUudenListan() {
        ArrayList<Pommi> l1 = this.peli.getInventaario().getPommit();
        this.peli.etenePommit();
        assertNotSame(l1, this.peli.getInventaario().getPommit());
    }

    @Test
    public void etenePommiMuuttaaTimeria() {
        this.peli.getInventaario().getPommit().add(new Pommi(1, 1, 1, 1));
        this.peli.etenePommit();
        assertEquals(31, this.peli.getInventaario().getPommit().get(0).getTimer());
        this.peli.etenePommit();
        assertEquals(30, this.peli.getInventaario().getPommit().get(0).getTimer());
    }

    @Test
    public void etenePommiPoistaaRajahtaneen() {
        this.peli.getInventaario().getPelaaja(1).jataPommi();
        this.peli.jataPommit();
        this.peli.getInventaario().getPommit().get(0).setTimer(2);
        this.peli.etenePommit();
        assertFalse(this.peli.getInventaario().getPommit().isEmpty());
        this.peli.etenePommit();
        assertTrue(this.peli.getInventaario().getPommit().isEmpty());
    }

    @Test
    public void etenePommiAntaaPelaajallePommin() {
        this.peli.getInventaario().getPelaaja(1).jataPommi();
        this.peli.jataPommit();
        this.peli.getInventaario().getPommit().get(0).setTimer(0);
        this.peli.etenePommit();
        assertEquals(1, this.peli.getInventaario().getPelaaja(1).getPommejaKaytossa());
    }

    @Test
    public void etenePommiLuoUudenRajahdyksen() {
        this.peli.getInventaario().getPelaaja(1).jataPommi();
        this.peli.jataPommit();
        this.peli.getInventaario().getPommit().get(0).setTimer(0);
        assertTrue(this.peli.getInventaario().getRajahdykset().isEmpty());
        this.peli.etenePommit();
        assertFalse(this.peli.getInventaario().getRajahdykset().isEmpty());
    }

    @Test
    public void luoPieniRuudukkoOnOikeanKokoinen() {
        SeinaType[][] r = this.peli.luoPieniRuudukko();
        assertEquals(13, r.length);
        assertEquals(13, r[0].length);
    }

    @Test
    public void luoPieniRuudukkoSisaltaaKaikkia() {
        SeinaType[][] r = this.peli.luoPieniRuudukko();
        boolean sis0 = false;
        boolean sis1 = false;
        boolean sis2 = false;
        boolean sisEiMuuta = true;
        for (SeinaType[] r1 : r) {
            for (SeinaType s : r1) {
                if (s == SeinaType.VAPAA) {
                    sis0 = true;
                } else if (s == SeinaType.MUTASEINA) {
                    sis1 = true;
                } else if (s == SeinaType.KIVISEINA) {
                    sis2 = true;
                } else {
                    sisEiMuuta = false;
                }
            }
        }
        assertTrue(sis0);
        assertTrue(sis1);
        assertTrue(sis2);
        assertTrue(sisEiMuuta);
    }

    @Test
    public void tarkistaSijainninLaillisuusTesti1() {
        SeinaType[][] r = this.peli.luoPieniRuudukko();
        assertFalse(this.peli.tarkistaSijainninLaillisuus(50, 49, r));
        assertFalse(this.peli.tarkistaSijainninLaillisuus(171, 171, r));
        assertFalse(this.peli.tarkistaSijainninLaillisuus(170, 171, r));
        assertFalse(this.peli.tarkistaSijainninLaillisuus(171, 170, r));
        assertTrue(this.peli.tarkistaSijainninLaillisuus(169, 169, r));
        assertTrue(this.peli.tarkistaSijainninLaillisuus(150, 150, r));
        assertTrue(this.peli.tarkistaSijainninLaillisuus(140, 70, r));
        assertFalse(this.peli.tarkistaSijainninLaillisuus(140, 71, r));
        assertTrue(this.peli.tarkistaSijainninLaillisuus(70, 140, r));
        assertFalse(this.peli.tarkistaSijainninLaillisuus(71, 140, r));
        assertFalse(this.peli.tarkistaSijainninLaillisuus(75, 75, r));

        assertTrue(this.peli.tarkistaSijainninLaillisuus(75, 55, r));
        assertTrue(this.peli.tarkistaSijainninLaillisuus(55, 55, r));
        assertTrue(this.peli.tarkistaSijainninLaillisuus(55, 105, r));
        assertFalse(this.peli.tarkistaSijainninLaillisuus(75, 105, r));

        assertFalse(this.peli.tarkistaSijainninLaillisuus(55, 175, r));
    }

    @Test
    public void luoRajahdyksetLuoOikeanMaaranRajahdyksia5() {
        this.peli.luoRajahdykset(5, 5, 1);
        assertEquals(5, this.peli.getInventaario().getRajahdykset().size());
    }

    @Test
    public void luoRajahdyksetLuoOikeanMaaranRajahdyksia5enemmanVoimaa() {
        this.peli.luoRajahdykset(5, 5, 3);
        assertEquals(5, this.peli.getInventaario().getRajahdykset().size());
    }

    @Test
    public void luoRajahdyksetLuoOikeanMaaranRajahdyksia3() {
        this.peli.luoRajahdykset(1, 1, 1);
        assertEquals(3, this.peli.getInventaario().getRajahdykset().size());
    }

    @Test
    public void luoRajahdyksetLuoOikeanMaaranRajahdyksia7() {
        this.peli.luoRajahdykset(1, 1, 5);
        assertEquals(7, this.peli.getInventaario().getRajahdykset().size());
    }

    @Test
    public void luoYksiRajahdysSuuntaMeneeOikeaanSuuntaanX() {
        SeinaType[][] r = this.peli.luoPieniRuudukko();
        this.peli.luoYksiRajahdysSuunta(1, 0, 1, 1, 3, r);
        assertEquals(3, this.peli.getInventaario().getRajahdykset().size());
    }

    @Test
    public void luoYksiRajahdysSuuntaMeneeOikeaanSuuntaanY() {
        SeinaType[][] r = this.peli.luoPieniRuudukko();
        this.peli.luoYksiRajahdysSuunta(0, 1, 1, 1, 3, r);
        assertEquals(3, this.peli.getInventaario().getRajahdykset().size());
    }

    @Test
    public void poltaRuutuMerkitseeSeinanTuhottavaksi() {
        for (int i = 0; i < 10; i++) {
            this.peli.poltaRuutu(3, 2);
        }
        for (Seina seina : this.peli.getInventaario().getSeinat()) {
            if (seina.getX() == 3 && seina.getY() == 2) {
                assertTrue(seina.getTuhoutuu());
            } else {
                assertFalse(seina.getTuhoutuu());
            }
        }
    }

    @Test
    public void poltaRuutuTuhoaaSeinan() {
        ArrayList<Seina> l1 = this.peli.getInventaario().getSeinat();
        int i = this.peli.getInventaario().getSeinat().size();
        this.peli.poltaRuutu(3, 2);
        this.peli.paivitaSeinat();
        assertEquals(i, this.peli.getInventaario().getSeinat().size());
    }

    @Test
    public void eteneRajahdyksetMuuttaaListaa() {
        ArrayList<Rajahdys> l1 = this.peli.getInventaario().getRajahdykset();
        this.peli.eteneRajahdykset();
        assertNotSame(l1, this.peli.getInventaario().getRajahdykset());
    }

    @Test
    public void eteneRajahdyksetEdistaaRajahdyksia() {
        this.peli.luoRajahdykset(1, 1, 0);
        int i = this.peli.getInventaario().getRajahdykset().get(0).getTimer();
        this.peli.eteneRajahdykset();
        assertEquals(1, this.peli.getInventaario().getRajahdykset().size());
        assertEquals(i-1, this.peli.getInventaario().getRajahdykset().get(0).getTimer());
        while(i >= 0){
            this.peli.eteneRajahdykset();
            i--;
        }
        assertEquals(0, this.peli.getInventaario().getRajahdykset().size());
    }
    
    @Test
    public void eteneRajahdyksetTuhoaaRuudun(){
        this.peli.getInventaario().getRajahdykset().add(new Rajahdys(3,2));
        for (int i = 0; i < 10; i++) {
            this.peli.eteneRajahdykset();
        }
        boolean test = false;
        for (Seina s : this.peli.getInventaario().getSeinat()) {
            if(s.getX() ==3 && s.getY() == 2){
                test = s.getTuhoutuu();
            }
        }
        assertTrue(test);
    }

    @Test 
    public void tarkistaVoittajaPalauttaaTrue() {
        assertTrue(this.peli.tarkistaVoittaja() == Tulos.NYD);
    }

    @Test
    public void etenePelaajatAntaaPUnOikealle() {
        this.peli.getInventaario().getPowerUp().add(new PowerUp(1,1, PowerUpType.POMMEJA));
        this.peli.etenePelaajat();
        assertEquals(2, this.peli.getInventaario().getPelaaja(1).getPommeja());
    }

        
    @Test
    public void poltaRuutuTappaaPelaajan(){
        peli.poltaRuutu(1, 1);
        assertTrue(this.peli.getInventaario().getPelaaja(1).getHavinnyt());
        assertFalse(this.peli.getInventaario().getPelaaja(2).getHavinnyt());
    }
    
    @Test
    public void tseEtaisyysTesti(){
        assertEquals(2, peli.tsebysovEtaisyys(2, 1));
        assertEquals(3, peli.tsebysovEtaisyys(2, 3));
        assertEquals(2, peli.tsebysovEtaisyys(2, 2));
    }
    
    @Test
    public void itseisarvoTesti(){
        assertTrue(peli.etaisyysItseisArvo(-3, -5) >= 0);
        assertEquals(1, peli.etaisyysItseisArvo(2, 1));
        assertEquals(3, peli.etaisyysItseisArvo(2, 5));
        assertEquals(3, peli.etaisyysItseisArvo(-2, 1));
        assertEquals(7, peli.etaisyysItseisArvo(2, -5));
    }
    
    @Test
    public void tarkistaVoittajaPalauteOikein1(){
        peli.getInventaario().getPelaaja(1).havisi();
        assertEquals(Tulos.BLUE, this.peli.tarkistaVoittaja());
    }
    @Test
    public void tarkistaVoittajaPalauteOikein2(){
        peli.getInventaario().getPelaaja(2).havisi();
        assertEquals(Tulos.GREEN, this.peli.tarkistaVoittaja());
    }
    @Test
    public void tarkistaVoittajaPalauteOikein3(){
        peli.getInventaario().getPelaaja(1).havisi();
        peli.getInventaario().getPelaaja(2).havisi();
        assertEquals(Tulos.DRAW, this.peli.tarkistaVoittaja());
    }
    @Test
    public void tarkistaPowerUpTesti(){
        this.peli.getInventaario().getPowerUp().add(new PowerUp(1,1,PowerUpType.VOIMA));
        this.peli.tarkistaPowerUp(this.peli.getInventaario().getPelaaja(1));
        assertEquals(2, this.peli.getInventaario().getPelaaja(1).getVoima());
    }
    
}
