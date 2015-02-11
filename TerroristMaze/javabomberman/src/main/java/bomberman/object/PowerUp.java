/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman.object;

import bomberman.logiikka.PowerUpType;
import java.util.Random;

/**
 * Luokka kuvaa pelissa kaytettavaa PowerUp oliota.
 *
 * @author -
 */
public class PowerUp {

    private PowerUpType tyyppi;
    private int x;
    private int y;

    /**
     * Metodi luo uuden tietylla PowerUpType arvolla varustetun PowerUp olion.
     *
     * @param x koordinaatti
     * @param y koordinaatti
     * @param tyyppi PowerUpin tyyppi
     */
    public PowerUp(int x, int y, PowerUpType tyyppi) {
        this.x = x;
        this.y = y;
        this.tyyppi = tyyppi;
    }

    /**
     * Metodi luo uuden satunnaisella PowerUpType arvolla varustetun PowerUp
     * olion.
     *
     * @param x
     * @param y
     */
    public PowerUp(int x, int y) {
        this.x = x;
        this.y = y;
        Random random = new Random();
        int i = random.nextInt(9);
        if (i < 4) {
            this.tyyppi = PowerUpType.VOIMA;
        } else if (i < 7) {
            this.tyyppi = PowerUpType.NOPEUS;
        } else {
            this.tyyppi = PowerUpType.POMMEJA;
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public PowerUpType getType() {
        return this.tyyppi;
    }
}
