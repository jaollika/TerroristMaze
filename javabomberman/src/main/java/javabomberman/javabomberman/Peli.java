/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabomberman.javabomberman;

import java.awt.event.KeyListener;
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
        int[][] ruudukko = luoPieniRuudukko();
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
        }
    }

    public void JataPommit() {
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

    private boolean tarkistaSijainninLaillisuus(int dX, int dY, int[][] ruudukko) {
        
        //haetaan ruudut 13x13 ruukukossa jossa on seinää.
        int x = dX / 50;
        int y = dY / 50;
        if (ruudukko[x][y] != 0) {
            return false;
        } else if ((dX % 50) > 20 && ruudukko[x + 1][y] != 0) {
            return false;
        } else if ((dY % 50) > 20 && ruudukko[x][y + 1] != 0) {
            return false;
        } else if ((dX % 50) > 20 && (dY % 50) > 20) {
            return false;
        }
        return true;
    }
    
    public int[][] luoPieniRuudukko(){
        // 0 = vapaa, 1 = kiveä, 2 = mutaseina
        int[][] vapaana = new int[13][13];
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {
                if (i == 0 || i == 12 || j == 0 || j == 12) {
                    vapaana[i][j] = 1;
                }else if (i % 2 == 0 && j % 2 == 0) {
                    vapaana[i][j] = 1;
                } else{
                    vapaana[i][j] = 0;
                }
            }
        }
        
        for (Seina seina : this.inventaario.getSeinat()) {
            vapaana[seina.getX()][seina.getY()] = 2;
        }
        return vapaana;
    }

    public void luoRajahdykset(int x, int y, int voima) {
        int[][] ruudukko = luoPieniRuudukko();
        this.inventaario.luoRajahdys(x, y);
        for (int i = 1; i <= voima; i++) {
            if (ruudukko[x + i][y] == 0) {
                this.inventaario.luoRajahdys(x+i, y);
            } else if(ruudukko[x + i][y] == 2){
                this.inventaario.luoRajahdys(x+i, y);
                break;
            } else{break;}
        }
        for (int i = 1; i <= voima; i++) {
            if (ruudukko[x - i][y] == 0) {
                this.inventaario.luoRajahdys(x-i, y);
            } else if(ruudukko[x - i][y] == 2){
                this.inventaario.luoRajahdys(x-i, y);
                break;
            } else{break;}
        }
        for (int i = 1; i <= voima; i++) {
            if (ruudukko[x][y+i] == 0) {
                this.inventaario.luoRajahdys(x, y+i);
            } else if(ruudukko[x][y+1] == 2){
                this.inventaario.luoRajahdys(x, y+i);
                break;
            } else{break;}
        }
        for (int i = 1; i <= voima; i++) {
            if (ruudukko[x][y-1] == 0) {
                this.inventaario.luoRajahdys(x, y-i);
            } else if(ruudukko[x][y-1] == 2){
                this.inventaario.luoRajahdys(x, y-i);
                break;
            } else{break;}
        } 
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

    private void poltaRuutu(int x, int y) {
        for (Seina seina : this.inventaario.getSeinat()) {
            if(seina.getX() == x && seina.getY() == y){
                seina.setTuhoutuu();
            }
        }
        for (int i = 1; i <= 2; i++) {
            Pelaaja p = this.inventaario.getPelaaja(i);
            if(Math.abs((p.getX()+15) -(50*x+25))< 40 && Math.abs((p.getY()+15) -(50*x+25)) < 40){
                p.havisi();
            }
        }
    }

    public boolean tarkistaVoittaja() {
        Pelaaja p1 = this.inventaario.getPelaaja(1);
        Pelaaja p2 = this.inventaario.getPelaaja(2);
        
        if(p1.getHavinnyt() == true && p2.getHavinnyt() == true){
            System.out.println("Double Suicide.");
        } else if(p1.getHavinnyt() == true){
            System.out.println("Player two murdered his comrade.");
        } else if(p2.getHavinnyt() == true){
            System.out.println("Player one is the only one alive.");
        } else{
            return false;
        }
        return true;
    }

    public void paivitaSeinat() {
        ArrayList<Seina> uusiLista = new ArrayList<Seina>();
        for (Seina seina : this.inventaario.getSeinat()) {
            if (seina.getTuhoutuu()) {
                // LUO POWER UP (maybe)
            } else {
                uusiLista.add(seina);
            }
        }
        this.inventaario.setUusiSeinaLista(uusiLista);
    }


}
