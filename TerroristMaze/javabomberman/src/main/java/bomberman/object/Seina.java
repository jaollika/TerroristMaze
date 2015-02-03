/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman.object;

/**
 *
 * @author jaollika@cs
 */
public class Seina{

    private int sijaintiX;
    private int sijaintiY;
    private boolean tuhoutumassa;
    private int vauriopisteet;

    public Seina(int x, int y) {
        this.sijaintiX = x;
        this.sijaintiY = y;
        this.tuhoutumassa = false;
        this.vauriopisteet = 8;
    }

    public int getX() {
        return this.sijaintiX;
    }

    public int getY() {
        return this.sijaintiY;
    }

    public void setTuhoutuu() {
        this.tuhoutumassa = true;
    }

    public boolean getTuhoutuu() {
        return this.tuhoutumassa;
    }

    public void otaVauriota() {
        this.vauriopisteet--;
        if(this.vauriopisteet <= 0){
            this.setTuhoutuu();
        }
    }

}
