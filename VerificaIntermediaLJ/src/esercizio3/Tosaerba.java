/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esercizio3;

/**
 *
 * @author alfonso
 */
public class Tosaerba extends Macchina {

    private final int ruote;

    public Tosaerba(int ruote, String marca) {
        super(marca);
        this.ruote = ruote;
    }

    public int getRuote() {
        return ruote;
    }

    @Override
    public String toString() {
        return "Tosaerba{" + "ruote=" + ruote + "}\n" + super.toString();
    }

}
