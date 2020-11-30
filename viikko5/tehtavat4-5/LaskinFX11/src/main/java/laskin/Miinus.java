/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author toniramo
 */
public class Miinus extends Komento {

    public Miinus(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    @Override
    public void suorita() {
        try {
            haeUusiSyote();
            sovellus.miinus(syote);
            paivitaTulos();
            enabloiNollaaJosTulosEiNolla();
            enabloiUndo();
        } catch (NumberFormatException e) {
        }
        nollaaSyotekentta();
    }

    @Override
    public void peru() {
        sovellus.plus(syote);
        paivitaTulos();
        nollaaSyotekentta();
        syote = 0;
        disabloiUndo();
        enabloiNollaaJosTulosEiNolla();
    }
}
