/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import bomberman.logiikka.Tulos;
import java.awt.BorderLayout;
import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JTextField;

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
                paivitaPeli(peli);
                pyorii = peli.tarkistaVoittaja();
                aika = System.nanoTime();
                peli.paivitagrafiikka();
                Thread.sleep(1);
            }
            peli.paivitagrafiikka();
            Thread.sleep(10);
        }

    }

    /**
     * Metodi paivittaa jarjestyksessa pelilogiikan osat
     *
     * @param peli
     */
    public void paivitaPeli(Pelilogiikka peli) {
        peli.etenePelaajat();
        peli.jataPommit();
        peli.etenePommit();
        peli.eteneRajahdykset();
        peli.paivitaSeinat();
    }
    
}
