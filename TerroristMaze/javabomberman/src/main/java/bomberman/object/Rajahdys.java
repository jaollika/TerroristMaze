/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman.object;

/**
 * Luokka kuvaa Rajahdys oliota.
 *
 * @author jaollika@cs
 */
public class Rajahdys{
    private int sijaintiX;
    private int sijaintiY;
    private int timer;

    public Rajahdys(int x, int y){
        this.sijaintiX = x;
        this.sijaintiY = y;
        this.timer = 10;
    }
    

    public int getX() {
        return this.sijaintiX;
    }


    public int getY() {
        return this.sijaintiY;
    }

    /**
     * Metodi laskee Rajahdys luokan ajastinta ja palauttaa true arvo jos arvo
     * on nolla tai alle.
     *
     * @return saavuttaako ajastin 0-arvon
     */
    public boolean etene(){
        this.timer = this.timer-1;
        return this.timer <= 0;
    }
    
    public int getTimer(){
        return this.timer;
    }
}
