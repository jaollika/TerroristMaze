/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman.object;

import bomberman.Enum.PowerUpTyyppi;
import java.util.Random;

/**
 * Luokka kuvaa pelissa kaytettavaa PowerUp oliota.
 *
 * @author -
 */
public class PowerUp {

    private PowerUpTyyppi tyyppi;
    private int x;
    private int y;

    /**
     * Metodi luo uuden tietylla PowerUpType arvolla varustetun PowerUp olion.
     *
     * @param x koordinaatti
     * @param y koordinaatti
     * @param tyyppi PowerUpin tyyppi
     */
    public PowerUp(int x, int y, PowerUpTyyppi tyyppi) {
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
            this.tyyppi = PowerUpTyyppi.VOIMA;
        } else if (i < 7) {
            this.tyyppi = PowerUpTyyppi.NOPEUS;
        } else {
            this.tyyppi = PowerUpTyyppi.POMMEJA;
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public PowerUpTyyppi getType() {
        return this.tyyppi;
    }
}
