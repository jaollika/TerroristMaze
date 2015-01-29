package bomberman;

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
    private EsineInventaario inventaario;

    public Piirto(EsineInventaario inventaario) {
        this.PaivitaInventaario(inventaario);
        KeyListener kuuntelija = new Ohjaus(this.inventaario.getPelaaja(1), this.inventaario.getPelaaja(2));
        addKeyListener(kuuntelija);
        setFocusable(true);
    }
    public void PaivitaInventaario(EsineInventaario inventaario){
        this.inventaario = inventaario;
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        piirraPohja(g2D);
        piirraSeinat(g2D, this.inventaario);
        piirraPommit(g2D, this.inventaario);
        piirraRajahdykset(g2D);
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
        for (Seina seina : inventaario.getSeinat()) {
            g2D.fillRect(seina.getX() * 50 + 1, seina.getY() * 50 + 1, 48, 48);
        }

    }

    private void piirraPelaajat(Graphics2D g2D) {
        Pelaaja p = this.inventaario.getPelaaja(1);
        g2D.setColor(Color.GREEN);
        g2D.fillRect(p.getX(), p.getY(), 30, 30);
        p = this.inventaario.getPelaaja(2);
        g2D.setColor(Color.BLUE);
        g2D.fillRect(p.getX(), p.getY(), 30, 30);

    }

    private void piirraPommit(Graphics2D g2D, EsineInventaario inventaario) {
        g2D.setColor(Color.BLACK);
        for (Pommi pommi : inventaario.getPommit()) {
            g2D.fillOval(pommi.getX() * 50 + 12, pommi.getY() * 50 + 12, 26, 26);
        }

    }

    private void piirraRajahdykset(Graphics2D g2D) {
        g2D.setColor(Color.RED);
        for (Rajahdys rajahdys : this.inventaario.getRajahdykset()) {
            g2D.fillRect(
                    rajahdys.getX() * 50 + 10 - rajahdys.getTimer(), 
                    rajahdys.getY() * 50 + 10 - rajahdys.getTimer(), 
                    30 + 2 * rajahdys.getTimer(), 
                    30 + 2 * rajahdys.getTimer()
            );
        }

    }
}


