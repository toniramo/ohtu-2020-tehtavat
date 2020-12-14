package ohtu.kivipaperisakset;

import java.util.HashMap;
import java.util.Map;

public abstract class KiviSaksetPaperi {
    protected IO io;
    protected String ekanViimeisinSiirto;

    public KiviSaksetPaperi(IO io) {
        this.io = io;
    }

    private static Map<String, KiviSaksetPaperi> alustaPelivaihtoehdot(IO io) {
        Map<String, KiviSaksetPaperi> pelivaihtoehdot = new HashMap<>();
        pelivaihtoehdot.put("a", new KPSPelaajaVsPelaaja(io));
        pelivaihtoehdot.put("b", new KPSTekoaly(io));
        pelivaihtoehdot.put("c", new KPSParempiTekoaly(io));
        return pelivaihtoehdot;
    }

    public static void aloitaPeli(IO io) {
        Map<String, KiviSaksetPaperi> pelivaihtoehdot = alustaPelivaihtoehdot(io);

        while (true) {
            io.tulosta("\nValitse pelataanko" 
            + "\n (a) ihmistä vastaan " 
            + "\n (b) tekoälyä vastaan"       
            + "\n (c) parannettua tekoälyä vastaan" 
            + "\nmuilla valinnoilla lopetataan");

            String vastaus = io.keraaTekstisyote("");

            if (!pelivaihtoehdot.containsKey(vastaus)) {
                break;
            }

            io.tulosta("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
            pelivaihtoehdot.get(vastaus).pelaa();
        }    
    }

    public void pelaa() {
        Tuomari tuomari = new Tuomari();
        String ekanSiirto;
        String tokanSiirto;

        while(true) {
            ekanSiirto = ensimmaisenSiirto();
            tokanSiirto = toisenSiirto();
            if (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
                tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
                io.tulosta(tuomari.toString());
                io.tulosta("");
                continue;
            }
            break;
        }

        io.tulosta("");
        io.tulosta("Kiitos!");
        io.tulosta(tuomari.toString());
    }

    protected String ensimmaisenSiirto() {
        String siirto = io.keraaTekstisyote("Ensimmaisen pelaajan siirto: ");
        if (this.getClass().equals(KPSParempiTekoaly.class)) {
            ekanViimeisinSiirto = siirto;
        }
        return siirto;
    }

    abstract protected String toisenSiirto();

    protected static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }

}