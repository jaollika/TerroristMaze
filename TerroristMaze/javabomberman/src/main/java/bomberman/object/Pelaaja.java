/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman.object;

import bomberman.logiikka.PowerUpType;
import java.awt.event.KeyEvent;
import javax.swing.Action;

/**
 * Luokka kuvaa Pelaaja oliota ja sen ominaisuuksia
 *
 * @author jaollika@cs
 */
public class Pelaaja {

    private int voima;
    private int nopeus;
    private int pommeja;
    private int pommejaKaytossa;
    private int sijaintiX;
    private int sijaintiY;
    private int nopeusX;
    private int nopeusY;
    private boolean havinnyt;
    private boolean jattamassaPommin;
    private int nappainSetti;

    /**
     * Metodi luo pelaajan haluttuun sijaintiin
     *
     * @param x -koordinaatti
     * @param y -koordinaatti
     */
    public Pelaaja(int x, int y) {
        this.nopeus = 5;
        this.pommeja = 1;
        this.pommejaKaytossa = 1;
        this.voima = 1;
        this.havinnyt = false;
        this.sijaintiX = x;
        this.sijaintiY = y;
        this.nopeusX = 0;
        // -1, 0 tai 1
        this.nopeusY = 0;
        // -1, 0 tai 1
        this.jattamassaPommin = false;
    }

    public int getX() {
        return this.sijaintiX;
    }

    public int getY() {
        return this.sijaintiY;
    }

    public int getPommejaKaytossa() {
        return this.pommejaKaytossa;
    }

    public boolean getJattaakoPommin() {
        return this.jattamassaPommin;
    }

    public int getVoima() {
        return this.voima;
    }

    /**
     * Metodi asettaa pelaajan voiman annettuun parametriin jos se on valilla
     * (0, 8)
     *
     * @param i haluttu voima
     */
    public void setVoima(int i) {
        if (i > 0 && i <= 8) {
            this.voima = i;
        }
    }

    public void lisaaVoimaa() {
        if (this.voima < 8) {
            this.voima++;
        }
    }

    public void setEiJataPommia() {
        this.jattamassaPommin = false;
    }

    public void setNopeusX(int x) {
        this.nopeusX = x;
    }

    public void setNopeusY(int y) {
        this.nopeusY = y;
    }

    /**
     * Metodi muuttaa pelaaja olion sijaintia nopeuden mukaan.
     */
    public void etene() {
        this.sijaintiX = this.sijaintiX + this.nopeusX;
        this.sijaintiY = this.sijaintiY + this.nopeusY;
    }

    /**
     * Metodi muuttaa pelaajan sijaintia halutun maaran
     *
     * @param x koordinaatti
     * @param y koordinaatti
     */
    public void siirra(int x, int y) {
        this.sijaintiX = this.sijaintiX + x;
        this.sijaintiY = this.sijaintiY + y;
    }

    public int getNopeus() {
        return this.nopeus;
    }

    public int getNopeusX() {
        return this.nopeusX;
    }

    public int getNopeusY() {
        return this.nopeusY;
    }

    public void setPommeja(int i) {
        this.pommeja = i;
    }

    public int getPommeja() {
        return this.pommeja;
    }

    /**
     * Metodi merkitsee pelaajan jattamaan pommin jos pelaajalla on kaytossaan
     * pommeja, seka vahentaa kaytettavissa olevien pommien lukumaaraa yhdella.
     */
    public void jataPommi() {
        if (this.jattamassaPommin == false) {
            if (this.pommejaKaytossa > 0) {
                this.jattamassaPommin = true;
                this.pommejaKaytossa = this.pommejaKaytossa - 1;
            }
        }
    }

    /**
     * Metodi kasvattaa pelaaja olion kaytossa olevien pommien lukumaaraa mutta
     * korkeintaan pommeja-arvoon asti.
     */
    public void lisaaKaytettavaPommi() {
        this.pommejaKaytossa = this.pommejaKaytossa + 1;
        if (this.pommejaKaytossa > this.pommeja) {
            this.pommejaKaytossa = this.pommeja;
        }
    }

    public void havisi() {
        this.havinnyt = true;
    }

    public boolean getHavinnyt() {
        return this.havinnyt;
    }

    public void keraaPowerUp(PowerUpType pU) {
        if (pU == PowerUpType.NOPEUS) {
            this.nopeus = this.nopeus + 2;
        }
        if (pU == PowerUpType.VOIMA) {
            this.voima = this.voima + 1;
        }
        if (pU == PowerUpType.POMMEJA) {
            this.pommeja = this.pommeja + 1;
            this.lisaaKaytettavaPommi();
        }
    }
}
