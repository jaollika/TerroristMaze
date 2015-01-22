/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabomberman.javabomberman;

import java.awt.event.KeyEvent;
import javax.swing.Action;

/**
 *
 * @author jaollika@cs
 */
public class Pelaaja implements Esine{

    private int voima;
    private int nopeus;
    private int pommeja;
    private int pommejaKaytossa;
    private int sijaintiX;
    private int sijaintiY;
    private int nopeusX;
    private int nopeusY;
    private boolean havinnyt;
    private int nappainSetti;

    public Pelaaja(int x, int y, int z) {
        this.nopeus=1;
        this.pommeja=1;
        this.voima=1;
        this.havinnyt = false;
        this.sijaintiX = x;
        this.sijaintiY = y;
        this.nopeusX =0;
        this.nopeusY =0;
        this.nappainSetti = z;
    }
    
    @Override
    public int getX() {
       return this.sijaintiX;
    }

    @Override
    public int getY() {
        return this.sijaintiY;
    }

    public void muutaNopeus(int x, int y) {
        this.nopeusX = x * nopeus;
        this.nopeusY = y * nopeus;
    }

}
