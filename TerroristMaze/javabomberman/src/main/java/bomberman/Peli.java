/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import bomberman.Piirto;
import bomberman.logiikka.SeinaType;
import bomberman.object.Pelaaja;
import bomberman.object.Pommi;
import bomberman.object.EsineInventaario;
import bomberman.object.PowerUp;
import bomberman.object.Seina;
import bomberman.object.Rajahdys;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author jaollika@cs
 */
public class Peli {
    private EsineInventaario inventaario;
    private Piirto piirto;

    public Peli() throws InterruptedException{
        this.inventaario = new EsineInventaario();
        this.piirto = new Piirto(this.inventaario);
        this.piirraAlusta();
        this.luoSeinat();
    }
    
        public void piirraAlusta() throws InterruptedException{
        JFrame frame = new JFrame("BOMBER");
        frame.add(this.piirto);
        //koko on 13*50 ja 13*50
        frame.setSize(650, 650);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void paivitagrafiikka() {
        this.piirto.repaint();
    }

    public void etenePelaajat() {
        SeinaType[][] ruudukko = luoPieniRuudukko();
        for (int i = 1; i <= 2; i++) {
            Pelaaja p = this.inventaario.getPelaaja(i);
            int nopeus = p.getNopeus();
            while (nopeus > 0) {
                if (this.tarkistaSijainninLaillisuus(p.getX() + p.getNopeusX(), p.getY(), ruudukko)) {
                    this.inventaario.getPelaaja(i).siirra(p.getNopeusX(), 0);
                }
                if (this.tarkistaSijainninLaillisuus(p.getX(), p.getY() + p.getNopeusY(), ruudukko)) {
                    this.inventaario.getPelaaja(i).siirra(0, p.getNopeusY());
                }
                nopeus--;
            }
            tarkistaPowerUp(p);
        }
    }

    public void jataPommit() {
        for (int i = 1; i <= 2; i++) {
            Pelaaja p = this.inventaario.getPelaaja(i);
            if (p.getJattaakoPommin()) {
                p.setEiJataPommia();
                inventaario.lisaaPommi(p.getX(), p.getY(), p.getVoima(), i);
            }
        }
    }

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

    public void eteneRajahdykset() {
        ArrayList<Rajahdys> uusiLista = new ArrayList<Rajahdys>();
        for (Rajahdys rajahdys : this.inventaario.getRajahdykset()) {
            if(rajahdys.etene()){
            } else{
                uusiLista.add(rajahdys);
                poltaRuutu(rajahdys.getX(), rajahdys.getY());
            }
        }
        this.inventaario.setUusiRajahdysLista(uusiLista);
    }

    //esimerkki luokasta joka on public testien vuoksi
    public void poltaRuutu(int x, int y) {
        for (Seina seina : this.inventaario.getSeinat()) {
            if(seina.getX() == x && seina.getY() == y){
                seina.otaVauriota();
            }
        }
        for (Pommi pommi : this.inventaario.getPommit()) {
            if(pommi.getX() == x && pommi.getY() == y){
                pommi.setTimer(0);
            }
        }
        for (int i = 1; i <= 2; i++) {
            Pelaaja p = this.inventaario.getPelaaja(i);
            int pX = p.getX()+15;
            int pY = p.getY()+15;
            int rX = x*50+25;
            int rY = y*50+25;
            // pelaajan ja rajahdyksen keskipisteet
            if(tsebysovEtaisyys(etaisyysItseisArvo(pX, rX), etaisyysItseisArvo(pY, rY)) < 40){
                p.havisi();
            }
        }
    }
    
    public int tsebysovEtaisyys(int x, int y){
        if(x > y){
            return x;
        }
        return y;
    }
    
    public int etaisyysItseisArvo(int x1, int x2){
        if(x1 - x2 > 0){
            return x1-x2;
        }
        return x2-x1;
    }

    public boolean tarkistaVoittaja() {
        //AKA jatkuukopeli
        Pelaaja p1 = this.inventaario.getPelaaja(1);
        Pelaaja p2 = this.inventaario.getPelaaja(2);
        
        if(p1.getHavinnyt() == true && p2.getHavinnyt() == true){
            System.out.println("Double Suicide.");
        } else if(p1.getHavinnyt() == true){
            System.out.println("Player two murdered his comrade.");
        } else if(p2.getHavinnyt() == true){
            System.out.println("Player one is the only one alive.");
        } else{
            return true;
        }
        return false;
    }

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

    public EsineInventaario getInventaario(){
        //vain testikäyttöön
        return this.inventaario;
    }
    
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
        
        public SeinaType[][] luoPieniRuudukko(){
        // 0 = vapaa, 1 = kiveä, 2 = mutaseina
        SeinaType[][] vapaana = new SeinaType[13][13];
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {
                if (i == 0 || i == 12 || j == 0 || j == 12) {
                    vapaana[i][j] = SeinaType.KIVISEINA;
                }else if (i % 2 == 0 && j % 2 == 0) {
                    vapaana[i][j] = SeinaType.KIVISEINA;
                } else{
                    vapaana[i][j] = SeinaType.VAPAA;
                }
            }
        }
        
        for (Seina seina : this.inventaario.getSeinat()) {
            vapaana[seina.getX()][seina.getY()] = SeinaType.MUTASEINA;
        }
        return vapaana;
    }

    public void luoRajahdykset(int x, int y, int voima) {
        SeinaType[][] ruudukko = luoPieniRuudukko();
        this.inventaario.luoRajahdys(x, y);
        luoYksiRajahdysSuunta(1, 0, x, y, voima, ruudukko);
        luoYksiRajahdysSuunta(-1, 0, x, y, voima, ruudukko);
        luoYksiRajahdysSuunta(0, 1, x, y, voima, ruudukko);
        luoYksiRajahdysSuunta(0, -1, x, y, voima, ruudukko);
    }

    public void luoYksiRajahdysSuunta(int dx, int dy, int x, int y, int voima, SeinaType[][] ruudukko){
        while(voima > 0){
            x = x+dx;
            y = y+dy;
            if(ruudukko[x][y] != SeinaType.KIVISEINA){
                this.inventaario.luoRajahdys(x, y);
                if(ruudukko[x][y] == SeinaType.MUTASEINA){
                    voima = 0;
                }
            } else{
                voima = 0;
            }
            voima = voima-1;
        }
    }

        public boolean tarkistaSijainninLaillisuus(int dX, int dY, SeinaType[][] ruudukko) {
        
        //haetaan ruudut 13x13 ruukukossa jossa on seinää.
        int x = dX / 50;
        int y = dY / 50;
        if (ruudukko[x][y] != SeinaType.VAPAA) {
            return false;
        } else if ((dX % 50) > 20 && ruudukko[x + 1][y] != SeinaType.VAPAA) {
            return false;
        } else if ((dY % 50) > 20 && ruudukko[x][y + 1] != SeinaType.VAPAA) {
            return false;
        } else if ((dX % 50) > 20 && (dY % 50) > 20) {
            return false;
        }
        return true;
    }

    private void tarkistaPowerUp(Pelaaja p) {
        int x = (p.getX()+15)/50;
        int y = (p.getY()+15)/50;
        ArrayList<PowerUp> pUlista = new ArrayList<PowerUp>();
        for (PowerUp pU : this.inventaario.getPowerUp()) {
            if(pU.getX() == x && pU.getY() == y){
                p.keraaPowerUp(pU.getType());
            } else{
                pUlista.add(pU);
            }
        }
        this.inventaario.setUusiPowerUpLista(pUlista);
    }

}
