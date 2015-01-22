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
        this.nopeus=1;
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

    public boolean jattaakoPommin(){
        return this.jattamassaPommin;
    }
    public int getVoima(){
        return this.voima;
    }
    
    public void pommiJatettu(){
        this.jattamassaPommin = false;
        System.out.println("POMMI JATETTU");
    }
    
    public void muutaNopeusX(int x){
        this.nopeusX = x;
    }
    
    public void muutaNopeusY(int y){
        this.nopeusY = y;
        
    }

    public void etene() {
        this.sijaintiX = this.sijaintiX + this.nopeusX;
        this.sijaintiY = this.sijaintiY + this.nopeusY;
    }

    public void jataPommi() {
        if(this.jattamassaPommin = false && this.pommejaKaytossa > 0){
            this.jattamassaPommin = true;
            this.pommejaKaytossa = this.pommejaKaytossa-1;
            System.out.println("JATIN POMMIN");
        }
    }
    
    public void pommiRajahti(){
        this.pommejaKaytossa = this.pommejaKaytossa;
        if(this.pommejaKaytossa > this.pommeja){
            this.pommejaKaytossa = pommeja;
        }
    }
}
