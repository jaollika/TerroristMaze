/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabomberman.javabomberman;

import javax.swing.JFrame;

/**
 *
 * @author jaollika@cs
 */
public class PeliMoottori {

    public void start() throws InterruptedException {
        final long OPTIMAL_TIME = 1000000000 / 60;
      
        
        Peli peli = new Peli();
        //luodaan peli
        
        boolean pyorii = true;
        while (pyorii) {
            paivitaPeli(peli);
            peli.paivitagrafiikka();
            if (peli.tarkistaVoittaja()) {
                pyorii = false;
            }
            Thread.sleep(100);
        }

    }

    public void paivitaPeli(Peli peli) {
        peli.etenePelaajat();
        peli.JataPommit();
        peli.etenePommit();
        peli.eteneRajahdykset();
        peli.paivitaSeinat();
    }
//    peli.paivitagrafiikka();
    
    
    
    
   
    
    
    
    
}
