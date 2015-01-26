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
    private boolean jattamassaPommin;
    private int nappainSetti;

    public Pelaaja(int x, int y) {
        this.nopeus=5;
        this.pommeja=1;
        this.pommejaKaytossa = 1;
        this.voima=1;
        this.havinnyt = false;
        this.sijaintiX = x;
        this.sijaintiY = y;
        this.nopeusX =0;
        this.nopeusY =0;
        this.jattamassaPommin = false;
    }
    
    @Override
    public int getX() {
       return this.sijaintiX;
    }

    @Override
    public int getY() {
        return this.sijaintiY;
    }
    
    public int getPommejaKaytossa(){
        return this.pommejaKaytossa;
    }

    public boolean getJattaakoPommin(){
        return this.jattamassaPommin;
    }
    public int getVoima(){
        return this.voima;
    }
    
    public void setEiJataPommia(){
        this.jattamassaPommin = false;
    }
    
    public void muutaNopeusX(int x){
        this.nopeusX = x;
    }
    
    public void muutaNopeusY(int y){
        this.nopeusY = y;
        
    }

    public boolean etene() {
        this.sijaintiX = this.sijaintiX + this.nopeusX * this.nopeus;
        this.sijaintiY = this.sijaintiY + this.nopeusY * this.nopeus;
        return false;
    }

    public void jataPommi() {
        if (this.jattamassaPommin == false) {
            if (this.pommejaKaytossa > 0) {
                this.jattamassaPommin = true;
                this.pommejaKaytossa = this.pommejaKaytossa - 1;
            }
        }
    }
    
    public void lisaaKaytettavaPommi(){
        this.pommejaKaytossa = this.pommejaKaytossa+1;
        if(this.pommejaKaytossa > this.pommeja){
            this.pommejaKaytossa = this.pommeja;
        }
    }

    public void palaa() {
        this.sijaintiX = this.sijaintiX - this.nopeusX *2;
        this.sijaintiY = this.sijaintiY - this.nopeusY *2;
    }
}