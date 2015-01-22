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
    public void start() throws InterruptedException{
        Peli peli = new Peli();
        peli.piirra();
        //luodaan peli
        while(true){
            peli.paivitagrafiikka();
            peli.etene();
            Thread.sleep(100);
        }
        
    }
    
    
    
}
