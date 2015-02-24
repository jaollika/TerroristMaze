/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import graphics.LopetusRuutu;
import graphics.EsinePiirto;
import bomberman.object.EsineInventaario;
import bomberman.Enum.Tulos;
import javax.swing.JFrame;

/**
 * Luokka luo pohjan pelia varten.
 *
 * @author Jaollika
 */
public class PeliPohja {
    private JFrame frame;
    private EsinePiirto piirto;

    public PeliPohja(EsineInventaario inventaario) throws InterruptedException {
        this.piirto = new EsinePiirto(inventaario);
        this.piirraAlusta();
    }

            /**
     * Metodi piirtaa 13*50 x 13*50 ruudun, johon javabomberman mahtuu
     * optimaalisesti
     *
     */

        public void piirraAlusta() throws InterruptedException {
        this.frame = new JFrame("BOMBER");
        frame.add(this.piirto);
        //koko on 13*50 ja 13*50
        frame.setLocation(250, 200);
        frame.setSize(665, 685);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void UudellenMaalaa() {
        this.piirto.repaint();
    }

    
    public void sammuta(Tulos tulos) {
        frame.setVisible(false);
        this.frame = new JFrame("Game Over");
        frame.setLocation(250, 200);
        frame.setSize(315, 235);
        LopetusRuutu loppuG = new LopetusRuutu(tulos);
        frame.add(loppuG);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
