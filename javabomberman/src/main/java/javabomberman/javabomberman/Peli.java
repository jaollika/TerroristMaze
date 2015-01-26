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


    public void paivitagrafiikka() {
        this.piirto.repaint();
    }

    public void etenePelaajat() {
        inventaario.getPelaaja(1).etene();
        inventaario.getPelaaja(2).etene();
        tarkistaSijainninLaillisuus();
        }
    
    public void piirraAlusta() throws InterruptedException{
        JFrame frame = new JFrame("BOMBER");
        frame.add(this.piirto);
        //koko on 13*50 ja 13*50
        frame.setSize(650, 650);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
                //luo rajahdys
            } else {
                uusiPommilista.add(pommi);
            }
        }
        this.inventaario.setPommilista(uusiPommilista);
    }

    private void tarkistaSijainninLaillisuus() {
        int[][] vapaana = new int[13][13];
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {
                
                if (i == 0 || i == 12 || j == 0 || j == 12) {
                    vapaana[i][j] = 0;
                }else if (i % 2 == 0 && j % 2 == 0) {
                    vapaana[i][j] = 0;
                } else{
                    vapaana[i][j] = 1;
                }
            }
        }
        
        for (Esine seina : this.inventaario.getSeinat()) {
            vapaana[seina.getX()][seina.getY()] = 0;
        }
        for (int i = 1; i <= 2; i++) {
            Pelaaja p = this.inventaario.getPelaaja(i);
            boolean laiton = true;
            while(laiton){
                laiton = false;
                int x = p.getX()/50;
                int y = p.getY()/50;
                if (vapaana[x][y] == 0) {
                    laiton = true;
                } else if ((p.getX() % 50) > 20 && vapaana[x + 1][y] == 0) {
                    laiton = true;
                } else if ((p.getY() % 50) > 20 && vapaana[x][y + 1] == 0) {
                    laiton = true;
                } else if ((p.getX() % 50) > 20 && (p.getY() % 50) > 20) {
                    laiton = true;
                }
                p.palaa();
            }
        }
        
        
    }
}
