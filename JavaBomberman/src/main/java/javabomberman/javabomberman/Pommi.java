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
public class Pommi implements Esine{
    private int x;
    private int y;
    private int voima;
    private int timer;
    
    public Pommi(int x, int y, int voima){
        this.x = ((x+5)/50);
        this.y = ((y+5)/50);
        //pommit keskelle lahinta ruutua
        this.voima = voima;
    }
    
    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }
    
    public boolean etene(){
        this.timer --;
        return this.timer == 0;
    }
    
}
