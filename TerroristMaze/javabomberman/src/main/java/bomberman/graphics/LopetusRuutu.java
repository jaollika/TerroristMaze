/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman.graphics;

import bomberman.Enum.Tulos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Luokka hoitaa pelin paattymista ilmoittavan ruudun ja ohjelman sammumisen.
 *
 * @author Jaollika
 */
public class LopetusRuutu extends JPanel implements ActionListener {

    private JButton nappi;
    private String text;

    public LopetusRuutu(Tulos tulos) {
        this.text = luoTeksti(tulos);
        this.setLayout(null);
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    luoNappi();
                }
            });
        } catch (Exception exc) {
            System.out.println("Can't create because of " + exc);
        }
    }
    
     private void luoNappi() {
        this.nappi = new JButton(this.text);
        nappi.setBounds(0, 0, 300, 200);
        nappi.setActionCommand("LOPETA");
        nappi.addActionListener(this);
        add(nappi);
     }
     
    private String luoTeksti(Tulos tulos) {
        if(tulos == Tulos.GREEN){
            return "GREEN STANDS AS SOLE VICTOR";
        }
        if(tulos == Tulos.BLUE){
            return "BLUE SLAUGHTERED HIS ENEMY";
        }
        return "DOUBLE SUICIDE HAS OCCURRED";
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.exit(0);
    }
    
}
