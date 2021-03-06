/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman.logiikka;

import bomberman.Enum.Tulos;

/**
 * Luokka huolehtii että peli etenee askel askeleelta oikein
 * @author jaollika@cs
 */
public class PeliMoottori{

    /**
     * PeliMoottorin kayttama peli-olio
     */
    public Pelilogiikka peli;

    /**
     * Metodi aloittaa bomberman pelin pyörityksen
     * @throws InterruptedException
     */
    public void start() throws InterruptedException {
        peli = new Pelilogiikka();
        Tulos pyorii = Tulos.NYD;
        long aika = System.nanoTime();
        while (pyorii == Tulos.NYD) {
            if(System.nanoTime() - aika > 100000000){
                paivitaPeli();
                pyorii = peli.tarkistaVoittaja();
                aika = System.nanoTime();
                peli.paivitagrafiikka();
                Thread.sleep(1);
            }
            peli.paivitagrafiikka();
            Thread.sleep(10);
        }
        Thread.sleep(5000);
        peli.sammuta(pyorii);

    }

    /**
     * Metodi paivittaa jarjestyksessa pelilogiikan osat
     *
     * @param peli 
     */
    private void paivitaPeli() {
        peli.etenePelaajat();
        peli.jataPommit();
        peli.etenePommit();
        peli.eteneRajahdykset();
        peli.paivitaSeinat();
    }
    
}
