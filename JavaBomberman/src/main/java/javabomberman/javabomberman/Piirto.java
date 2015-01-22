package javabomberman.javabomberman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

/**
 *
 * @author jaollika@cs
 */
public class Piirto extends JPanel {


    Piirto(KeyListener kuuntelija) {
        addKeyListener(kuuntelija);
        setFocusable(true);
    }

    public void paint(Graphics g, EsineInventaario inventaario) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        piirraPohja(g2D);
        piirraSeinat(g2D, inventaario);
        piirraPelaajat(g2D);
        //piirretaan seinat

    }

    public void piirraPohja(Graphics g2D) {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {
                g2D.setColor(Color.gray);
                if (i == 0 || i == 12 || j == 0 || j == 12) {
                    g2D.fillRect(i * 50, j * 50, 50, 50);
                }
                if (i % 2 == 0 && j % 2 == 0) {
                    g2D.fillRect(i * 50, j * 50, 50, 50);
                }
            }
        }
    }

    public void piirraSeinat(Graphics g2D, EsineInventaario inventaario) {
        g2D.setColor(Color.ORANGE);
        for (Esine seina : inventaario.getSeinat()) {
            g2D.fillRect(seina.getX() * 50 + 1, seina.getY() * 50 + 1, 48, 48);
        }
    }

//       
//       //luodaan pelaajat
//       
//       this.lista.add(new Pelaaja(75,75, 0));
//       this.lista.add(new Pelaaja(12*50-25, 12*50-25, 1));
//       luoSeinat();
//       //luodaan seinat
//       
//    }


    public void etene() {
        
    }

    private void piirraPelaajat(Graphics2D g2D) {
        
    }
}





