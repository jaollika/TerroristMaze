/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman.logiikka;

import bomberman.object.Pelaaja;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;
import java.awt.event.KeyListener;

/**
 * Luokka luo Ohjaus olion, jolla pelia ohjataan.
 * 
 * @author jaollika
 */
public class Ohjaus implements KeyListener{
    private Pelaaja p1;
    private Pelaaja p2;
    
    /**
     * Metodi luo Ohjaus olion.
     * 
     * @param p1 pelaaja 1 
     * @param p2 pelaaja 2
     */
    public Ohjaus(Pelaaja p1, Pelaaja p2) {
        this.p1 = p1;
        this.p2 = p2;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {p2.setNopeusX(-1);}
        if (key == KeyEvent.VK_RIGHT) {p2.setNopeusX(1);}
        if (key == KeyEvent.VK_UP) {p2.setNopeusY(-1);}
        if (key == KeyEvent.VK_DOWN) {p2.setNopeusY(1);}
        
        if (key == KeyEvent.VK_A) {p1.setNopeusX(-1);}
        if (key == KeyEvent.VK_D) {p1.setNopeusX(1);}
        if (key == KeyEvent.VK_W) {p1.setNopeusY(-1);}
        if (key == KeyEvent.VK_S) {p1.setNopeusY(1);}
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {p2.setNopeusX(0);}
        if (key == KeyEvent.VK_RIGHT) {p2.setNopeusX(0);}
        if (key == KeyEvent.VK_UP) {p2.setNopeusY(0);}
        if (key == KeyEvent.VK_DOWN) {p2.setNopeusY(0);}
        
        if (key == KeyEvent.VK_A) {p1.setNopeusX(0);}
        if (key == KeyEvent.VK_D) {p1.setNopeusX(0);}
        if (key == KeyEvent.VK_W) {p1.setNopeusY(0);}
        if (key == KeyEvent.VK_S) {p1.setNopeusY(0);}
        
        if (key == KeyEvent.VK_Q) {
            p1.jataPommi();
        }
        if (key == KeyEvent.VK_O) {
            p2.jataPommi();
        }
    }
}
