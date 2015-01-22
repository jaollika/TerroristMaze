/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabomberman.javabomberman;

import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 *
 * @author jaollika@cs
 */
public class Peli {
    private EsineInventaario inventaario;
    private Piirto piirto;

    public Peli(){
        this.inventaario = new EsineInventaario();
        KeyListener kuuntelija = new Ohjaus(inventaario.getPelaaja(1), inventaario.getPelaaja(2));
        this.piirto = new Piirto(kuuntelija);
    }


    void paivitagrafiikka() {
        this.piirto.repaint();
    }

    void etene() {
        
        }
    
    public void piirra() throws InterruptedException{
        JFrame frame = new JFrame("BOMBER");
        frame.add(this.piirto);
        //koko on 13*50 ja 13*50
        frame.setSize(650, 650);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
