/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import javax.swing.JFrame;

/**
 *
 * @author jaollika@cs
 */
public class PeliMoottori {

    public void start() throws InterruptedException {
        
        Peli peli = new Peli();
        //luodaan peli
        
        boolean pyorii = true;
        long aika = System.nanoTime();
        while (pyorii) {
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

    public void paivitaPeli(Peli peli) {
        peli.etenePelaajat();
        peli.jataPommit();
        peli.etenePommit();
        peli.eteneRajahdykset();
        peli.paivitaSeinat();
    }

    
    
    
    
   
    
    
    
    
}
