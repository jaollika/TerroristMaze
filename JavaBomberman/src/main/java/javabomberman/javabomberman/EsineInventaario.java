/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabomberman.javabomberman;

import java.util.ArrayList;

/**
 *
 * @author jaollika@cs
 */
public class EsineInventaario {

    private ArrayList<Esine> seinaLista;
    private ArrayList<Esine> pommiLista;
    private ArrayList<Esine> powerUpLista;
    private Pelaaja pelaaja1;
    private Pelaaja pelaaja2;

    public EsineInventaario() {
        this.seinaLista = new ArrayList<Esine>();
        this.pommiLista = new ArrayList<Esine>();
        this.powerUpLista = new ArrayList<Esine>();
        luoSeinat();
        this.pelaaja1 = new Pelaaja(12 + 50 * 1, 12 + 50 * 1);
        this.pelaaja2 = new Pelaaja(12 + 50 * 11, 12 + 50 * 11);

    }

    public void luoSeinat() {
        for (int i = 1; i < 12; i++) {
            for (int j = 1; j < 12; j++) {
                if (i + j > 3 && i + j < 21) {
                    if (i % 2 == 1 && j % 2 == 0) {
                        this.seinaLista.add(new Seina(i, j));
                    }
                    if (i % 2 == 0 && j % 2 == 1) {
                        this.seinaLista.add(new Seina(i, j));
                    }
                }
            }
        }
    }

    public ArrayList<Esine> getSeinat() {
        return this.seinaLista;
    }

    public Pelaaja getPelaaja(int i) {
        if (i == 1) {
            return this.pelaaja1;
        } else if (i == 2) {
            return this.pelaaja2;
        }
        throw new UnsupportedOperationException("3+ multiplayer not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Esine> getPommit() {
        return this.pommiLista;
    }
    
    public void lisaaPommi(int x, int y, int voima){
        this.pommiLista.add(new Pommi(x, y, voima));
        System.out.println("POMMI INVENTAARIOSSA");
    }
    
    
}
