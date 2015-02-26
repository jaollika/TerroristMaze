/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman.logiikka;

import bomberman.graphics.PeliPohja;
import bomberman.graphics.EsinePiirto;
import bomberman.Enum.Ruutu;
import bomberman.Enum.Tulos;
import bomberman.object.Pelaaja;
import bomberman.object.Pommi;
import bomberman.object.EsineInventaario;
import bomberman.object.PowerUp;
import bomberman.object.Seina;
import bomberman.object.Rajahdys;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 * Luokka hoitaa bomberman ohjelman logiikan
 *
 * @author jaollika@cs
 */
public class Pelilogiikka {

    private EsineInventaario inventaario;
    private PeliPohja pohja;

    /**
     * Metodi luo Pelilogiikka olion.
     *
     * @throws InterruptedException
     */
    public Pelilogiikka() throws InterruptedException {
        this.inventaario = new EsineInventaario();
        this.pohja = new PeliPohja(this.inventaario);
        this.luoSeinat();
    }

    /**
     * Metodi kutsuu Pelipohja luokkaa paivittamaan grafiikat
     *
     */
    public void paivitagrafiikka() {
        this.pohja.UudelleenMaalaa();
    }

    /**
     * Metodi tarkistaa jattaako EsineInventaarioon talletettu pelaaja olio
     * pommia. Jos jattaa, metodi kutsuu pelaajan .setEiJataPommia ja luo
     * EsineInventaario olioon uuden pommi olion.
     */
    public void jataPommit() {
        for (int i = 1; i <= 2; i++) {
            Pelaaja p = this.inventaario.getPelaaja(i);
            if (p.getJattaakoPommin()) {
                p.setEiJataPommia();
                inventaario.lisaaPommi(p.getX(), p.getY(), p.getVoima(), i);
            }
        }
    }

    /**
     * Metodi edistaa kaikkien Pommi olioiden ajastinta ja luo uuden rajahdyksen
     * jos pommin ajastin on 0. Pommeja joiden ajastin on 0 ei lisata listaan
     * joka korvaa vanhan pommilistan
     *
     */
    public void etenePommit() {
        ArrayList<Pommi> uusiPommilista = new ArrayList<Pommi>();
        for (Pommi pommi : this.inventaario.getPommit()) {
            pommi.etene();
            if (pommi.getTimer() <= 0) {
                this.inventaario.getPelaaja(pommi.getOmistaja()).lisaaKaytettavaPommi();
                luoRajahdykset(pommi.getX(), pommi.getY(), pommi.getVoima());
            } else {
                uusiPommilista.add(pommi);
            }
        }
        this.inventaario.setPommilista(uusiPommilista);
    }

    /**
     * Metodi vie jokaista rajahdysta eteenpain ja kopioi voimassa olevat
     * rajahdykset uuteen listaan joka korvaa vanhan. Jokaisen rajahdyksen
     * kohdalla kutsutaan poltaRuutu(int, int) metodia.
     *
     */
    public void eteneRajahdykset() {
        ArrayList<Rajahdys> uusiLista = new ArrayList<Rajahdys>();
        for (Rajahdys rajahdys : this.inventaario.getRajahdykset()) {
            if (rajahdys.etene()) {
            } else {
                uusiLista.add(rajahdys);
                poltaRuutu(rajahdys.getX(), rajahdys.getY());
            }
        }
        this.inventaario.setUusiRajahdysLista(uusiLista);
    }

    /**
     * Metodi kutsuu samassa sijainnissa olevien seinien otaVauriota() -metodia
     * sekÃ¤ asettaa pommien ajastimen nollaan. Ruudussa olevien pelaajien
     * havisi() metodia kutsutaan.
     *
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     *
     */
    public void poltaRuutu(int x, int y) {
        poltaSeinat(x, y);
        rajaytaPommiRuudusta(x, y);
        tapaPelaajatRuudusta(x, y);
    }

    private void poltaSeinat(int x, int y) {
        for (Seina seina : this.inventaario.getSeinat()) {
            if (seina.getX() == x && seina.getY() == y) {
                seina.otaVauriota();
            }
        }
    }

    private void rajaytaPommiRuudusta(int x, int y) {
        for (Pommi pommi : this.inventaario.getPommit()) {
            if (pommi.getX() == x && pommi.getY() == y) {
                pommi.setTimer(0);
            }
        }
    }

    private void tapaPelaajatRuudusta(int x, int y) {
        for (int i = 1; i <= 2; i++) {
            Pelaaja p = this.inventaario.getPelaaja(i);
            int pX = p.getX() + 15;
            int pY = p.getY() + 15;
            int rX = x * 50 + 25;
            int rY = y * 50 + 25;
            // pelaajan ja rajahdyksen keskipisteet
            if (tsebysovEtaisyys(etaisyysItseisArvo(pX, rX), etaisyysItseisArvo(pY, rY)) < 40) {
                p.setHavisi();
            }
        }
    }

    private int tsebysovEtaisyys(int x, int y) {
        if (x > y) {
            return x;
        }
        return y;
    }

    private int etaisyysItseisArvo(int x1, int x2) {
        if (x1 - x2 > 0) {
            return x1 - x2;
        }
        return x2 - x1;
    }

    /**
     * Metodi tarkistaa pelaajien havinnyt muuttujan. Jos pelaaja on havinnyt
     * metodi palauttaa vastaavan Tulos.enum arvon.
     *
     *
     * @return palauttaa voittajan vÃ¤rin, tasapelin tai Not yet Decided
     */
    public Tulos tarkistaVoittaja() {
        //AKA jatkuukopeli
        Pelaaja p1 = this.inventaario.getPelaaja(1);
        Pelaaja p2 = this.inventaario.getPelaaja(2);

        if (p1.getHavinnyt() == true && p2.getHavinnyt() == true) {
            return Tulos.DRAW;
        } else if (p1.getHavinnyt() == true) {
            return Tulos.BLUE;
        } else if (p2.getHavinnyt() == true) {
            return Tulos.GREEN;
        } else {
            return Tulos.NYD;
        }
    }

    /**
     * Metodi tarkistaa seinat oliot ja tuleeko niiden tuhoutua. Metodi luo
     * uuden listan EsineInventaario olion seinat listan tilalle, jossa on
     * vanhan listan seinat jotka eivÃ¤t ole Tuhoutumassa tilassa.
     *
     */
    public void paivitaSeinat() {
        ArrayList<Seina> uusiLista = new ArrayList<Seina>();
        for (Seina seina : this.inventaario.getSeinat()) {
            if (seina.getTuhoutuu()) {
                this.inventaario.luoUusiPU(seina.getX(), seina.getY());
            } else {
                uusiLista.add(seina);
            }
        }
        this.inventaario.setUusiSeinaLista(uusiLista);
    }

    public EsineInventaario getInventaario() {
        //vain testikÃ¤yttÃ¶Ã¶n
        return this.inventaario;
    }

    /**
     * Metodia kaytetaan peli olion luonnin yhteydessa varmistamaan peliin
     * liittyvien seinien olemassa olo ja sijainti.
     *
     */
    public void luoSeinat() {
        ArrayList<Seina> seinat = new ArrayList<Seina>();
        for (int i = 1; i < 12; i++) {
            for (int j = 1; j < 12; j++) {
                if (i + j > 3 && i + j < 21) {
                    if (i % 2 == 1 && j % 2 == 0) {
                        seinat.add(new Seina(i, j));
                    }
                    if (i % 2 == 0 && j % 2 == 1) {
                        seinat.add(new Seina(i, j));
                    }
                }
            }
        }
        this.inventaario.setUusiSeinaLista(seinat);
    }

    /**
     * Metodia rakentaa pohjan ja EsineInventaarion Seina-listan avulla ruudukon
     * jota kaytetaan javabomberman liikkumisen pohjana
     *
     * @return pelilaudan rakenne 13x13 ruudukossa
     */
    public Ruutu[][] luoPieniRuudukko() {
        // 0 = vapaa, 1 = kiveÃ¤, 2 = mutaseina
        Ruutu[][] vapaana = new Ruutu[13][13];
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {
                if (i == 0 || i == 12 || j == 0 || j == 12) {
                    vapaana[i][j] = Ruutu.KIVISEINA;
                } else if (i % 2 == 0 && j % 2 == 0) {
                    vapaana[i][j] = Ruutu.KIVISEINA;
                } else {
                    vapaana[i][j] = Ruutu.VAPAA;
                }
            }
        }

        for (Seina seina : this.inventaario.getSeinat()) {
            vapaana[seina.getX()][seina.getY()] = Ruutu.MUTASEINA;
        }
        for (Pommi pommi : this.inventaario.getPommit()) {
            vapaana[pommi.getX()][pommi.getY()] = Ruutu.POMMI;
        }
        vapaana[this.inventaario.getPelaaja(1).getX() / 50][this.inventaario.getPelaaja(1).getY() / 50] = Ruutu.PELAAJA;
        vapaana[this.inventaario.getPelaaja(2).getX() / 50][this.inventaario.getPelaaja(2).getY() / 50] = Ruutu.PELAAJA;
        return vapaana;
    }

    /**
     * Metodi luo uudet rajahdykset haluttuun ruutuun ja sen viereisiin
     * ruutuihin, riippuen asetetusta voima parametrista. Metodi kutsuu
     * luoYksiRajahdysSuunta -Metodia nelja kertaa, kerran jokaista ilmansuuntaa
     * kohti.
     *
     * @param x x -koordinaatti
     * @param y y -koordinaatti
     * @param voima Rajahdyksen pituus
     *
     * @see bomberman.object.Rajahdys
     */
    public void luoRajahdykset(int x, int y, int voima) {
        Ruutu[][] ruudukko = luoPieniRuudukko();
        this.inventaario.luoRajahdys(x, y);
        luoYksiRajahdysSuunta(1, 0, x, y, voima, ruudukko);
        luoYksiRajahdysSuunta(-1, 0, x, y, voima, ruudukko);
        luoYksiRajahdysSuunta(0, 1, x, y, voima, ruudukko);
        luoYksiRajahdysSuunta(0, -1, x, y, voima, ruudukko);
    }

    /**
     * Metodi luo uusia Rajahdys-olioita. Annetun pelitilanteen mukaan uusien
     * rajahdysten luonti saataa pysahtya. Metodi toimii iteratiivisesti.
     *
     * @param dx suunta x -akselilla
     * @param dy suunta y -akselilla
     * @param x x -koordinaatti
     * @param y y -koordinaatti
     * @param voima Rajahdyksen pituus
     * @param ruudukko annettu pelitilanne
     *
     */
    public void luoYksiRajahdysSuunta(int dx, int dy, int x, int y, int voima, Ruutu[][] ruudukko) {
        while (voima > 0) {
            x = x + dx;
            y = y + dy;
            if (ruudukko[x][y] != Ruutu.KIVISEINA) {
                this.inventaario.luoRajahdys(x, y);
                if (ruudukko[x][y] == Ruutu.MUTASEINA) {
                    voima = 0;
                }
            } else {
                voima = 0;
            }
            voima = voima - 1;
        }
    }

    /**
     * Metodi tarkistaa onko pelaaja riittavan lahella PowerUp oliota, jotta
     * keraaminen voi tapahtua. Jos pelaaja on riittavan lahella, kutsutaan
     * pelaajan keraaPowerUp(PowerUpType) metodia. Metodi myos korvaa
     * EsineInventaario olion PowerUpLista n paivitetylla versiolla.
     *
     * @param p pelaaja-olio
     *
     */
    public void tarkistaPowerUp(Pelaaja p) {
        int x = (p.getX() + 15) / 50;
        int y = (p.getY() + 15) / 50;
        ArrayList<PowerUp> pUlista = new ArrayList<PowerUp>();
        for (PowerUp pU : this.inventaario.getPowerUp()) {
            if (pU.getX() == x && pU.getY() == y) {
                p.keraaPowerUp(pU.getType());
            } else {
                pUlista.add(pU);
            }
        }
        this.inventaario.setUusiPowerUpLista(pUlista);
    }

    /**
     * Metodi luo pelin paattymisen ilmoittavan ruudun johon tulee viesti tulos
     * parametrin antaman arvon perusteella.
     *
     * @param tulos pelin lopputulos
     *
     */
    public void sammuta(Tulos tulos) {
        this.pohja.sammuta(tulos);
    }

    /**
     * Metodi siirtaa EsineInventaario olioon sailottyja pelaajia
     * tarkistaSijainninLaillisuus avulla, jonka jÃ¤lkeen se tarkistaa saiko
     * pelaaja PowerUpia
     *
     * bomberman.logiikka.SeinaType[][])
     */
    private void etenePelaaja(Pelaaja p, Ruutu[][] r) {
        int nopeus = p.getNopeus();
        while (nopeus > 0) {
            if (sijainti(r, p, p.getNopeusX(), 0)) {
                p.siirra(p.getNopeusX(), 0);
            }
            if (sijainti(r, p, 0, p.getNopeusY())) {
                p.siirra(0, p.getNopeusY());
            }
            nopeus--;
        }
        tarkistaPowerUp(p);
    }

    /**
     * Metodi kutsuu etenePelaajatmetodia siirtaakseen pelaajia eteenpain
     */
    public void etenePelaajat() {
        Ruutu[][] r = this.luoPieniRuudukko();
        etenePelaaja(this.inventaario.getPelaaja(1), r);
        etenePelaaja(this.inventaario.getPelaaja(2), r);
    }

    /**
     * Metodi tutkii onko annetulle pelaajalle sallittua liikkua 1 askel
     * eteenpain.
     *
     * @param r Ruutu[][] koordinaatisto
     * @param p annettu Pelaaja olio
     * @param dX pelaajan nopeus X suunnassa
     * @param dY pelaajan nopeus Y suunnassa
     * @return
     */
    public boolean sijainti(Ruutu[][] r, Pelaaja p, int dX, int dY) {
        int x = p.getX() + dX;
        int y = p.getY() + dY;
        //Metodi etsii tilanteet joissa sijainti ei kelpaa
        if (r[x / 50][y / 50] != Ruutu.PELAAJA) {
            if (r[x / 50][y / 50] != Ruutu.VAPAA) {
                if(x%50 > 40 || y%50 > 40){
                return false; 
                }
                // jos sijainnissa ei ole pelaaja tai se ei ole vapaa niin sijainti on laiton
            }
        }
        if (x % 50 > 20 && y % 50 > 20) {
            return false;
            // jos sijainti on oikeassa alareunassa sita ei hyvaksyta
        }
        if (y % 50 <= 20 && x % 50 > 20) {
            if ((r[(x / 50) + 1][y / 50] == Ruutu.KIVISEINA || r[(x / 50) + 1][y / 50] == Ruutu.MUTASEINA)) {
                return false;
                //seinan sisaan meneminen ei ole sallittu
            }
            if (r[(x / 50) + 1][y / 50] == Ruutu.POMMI) {
                if(p.getNopeusX() > 0 && p.getX()%50 <= 20){
                    return false;
                }
                //pommia kohti ei saa menna
            }
        }
        if (y % 50 > 20 && x % 50 <= 20) {
            if ((r[x / 50][(y / 50) + 1] == Ruutu.KIVISEINA || r[x / 50][(y / 50) + 1] == Ruutu.MUTASEINA)) {
                return false;
                //seinan sisaan meneminen ei ole sallittu
            }
            if (r[x / 50][(y / 50) + 1] == Ruutu.POMMI) {
                if(p.getNopeusY() > 0 && p.getY()%50 <= 20){
                    return false;
                }
                
                //pommia kohti ei saa menna
            }
        }
        return true;
    }

}
