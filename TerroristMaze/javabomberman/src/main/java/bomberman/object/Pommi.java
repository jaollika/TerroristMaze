/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman.object;

/**
 * Luokka kuvaa bomberman pelin Pommi-oliota. Pommi tuntee sen sijainnin,
 * voiman, ajastimen ja omistajan.
 *
 * @author jaollika@cs
 */
public class Pommi {

    private int x;
    private int y;
    private int voima;
    private int timer;
    private int omistaja;

    public Pommi(int x, int y, int voima, int omistaja) {
        this.x = ((x + 15) / 50);
        this.y = ((y + 15) / 50);
        // 13 x 13 ruudukko
        this.omistaja = omistaja;
        this.voima = voima;
        this.timer = 32;
    }

    public int getVoima() {
        return this.voima;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    /**
     * Metodi vahentaa ajastimen arvoa yhdella.
     */
    public void etene() {
        this.timer--;
    }

    public int getOmistaja() {
        return this.omistaja;
    }

    public int getTimer() {
        return this.timer;
    }

    public void setTimer(int i) {
        //Test only
        this.timer = i;
    }

}
