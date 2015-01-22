/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabomberman.javabomberman;

/**
 *
 * @author jaollika@cs
 */
public class Seina implements Esine {

    private int sijaintiX;
    private int sijaintiY;
    private boolean tuhoutumassa;

    Seina(int x, int y) {
        this.sijaintiX = x;
        this.sijaintiY = y;
        this.tuhoutumassa = false;
    }

    @Override
    public int getX() {
        return this.sijaintiX;
    }

    @Override
    public int getY() {
        return this.sijaintiY;
    }

}
