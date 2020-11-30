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
public abstract class Komento {

    TextField tuloskentta;
    TextField syotekentta;
    Button nollaa;
    Button undo;
    Sovelluslogiikka sovellus;
    int syote;

    public Komento(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;
    }

    public abstract void suorita();

    public abstract void peru();

    protected void haeUusiSyote() throws NumberFormatException {
        syote = Integer.parseInt(syotekentta.getText());
    }

    protected void paivitaTulos() {
        tuloskentta.setText(String.valueOf(sovellus.tulos()));
    }

    protected void nollaaSyotekentta() {
        syotekentta.setText("");
    }

    protected void enabloiNollaaJosTulosEiNolla() {
        if (sovellus.tulos() != 0) {
            nollaa.disableProperty().set(false);
        } else {
            nollaa.disableProperty().set(true);
        }
    }

    protected void disabloiUndo() {
        undo.disableProperty().set(true);
    }

    protected void enabloiUndo() {
        undo.disableProperty().set(false);
    }
}
