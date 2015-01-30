/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman.object;

import java.util.ArrayList;

/**
 *
 * @author jaollika@cs
 */
public class EsineInventaario {

    private ArrayList<Seina> seinaLista;
    private ArrayList<Pommi> pommiLista;
    private ArrayList<PowerUp> powerUpLista;
    private ArrayList<Rajahdys> rajahdysLista;
    private Pelaaja pelaaja1;
    private Pelaaja pelaaja2;

    public EsineInventaario() {
        this.seinaLista = new ArrayList<Seina>();
        this.pommiLista = new ArrayList<Pommi>();
        this.powerUpLista = new ArrayList<PowerUp>();
        this.rajahdysLista = new ArrayList<Rajahdys>();
        this.pelaaja1 = new Pelaaja(12 + 50 * 1, 12 + 50 * 1);
        this.pelaaja2 = new Pelaaja(12 + 50 * 11, 12 + 50 * 11);

    }



    public ArrayList<Seina> getSeinat() {
        return this.seinaLista;
    }

    public Pelaaja getPelaaja(int i) {
        if (i == 1) {
            return this.pelaaja1;
        } else if (i == 2) {
            return this.pelaaja2;
        }
        throw new UnsupportedOperationException("3+ multiplayer not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Pommi> getPommit() {
        return this.pommiLista;
    }
    
    public void lisaaPommi(int x, int y, int voima, int omistaja){
        this.pommiLista.add(new Pommi(x, y, voima, omistaja));
    }

    public void setPommilista(ArrayList<Pommi> uusiPommilista) {
        this.pommiLista = uusiPommilista;
    }

    public void luoRajahdys(int x, int y) {
        this.rajahdysLista.add(new Rajahdys(x, y));
    }

    public ArrayList<Rajahdys> getRajahdykset() {
        return this.rajahdysLista;
    }

    public void setUusiRajahdysLista(ArrayList<Rajahdys> uusiLista) {
        this.rajahdysLista = uusiLista;
    }

    public void setUusiSeinaLista(ArrayList<Seina> uusiLista) {
        this.seinaLista = uusiLista;
    }

    public ArrayList<PowerUp> getPowerUp() {
        return this.powerUpLista;
    }

    public void lisaaPowerUp(int x, int y) {
        this.powerUpLista.add(new PowerUp(x, y));
    }

    public void setUusiPowerUpLista(ArrayList<PowerUp> pUlista) {
        this.powerUpLista = pUlista;
    }
    
}
