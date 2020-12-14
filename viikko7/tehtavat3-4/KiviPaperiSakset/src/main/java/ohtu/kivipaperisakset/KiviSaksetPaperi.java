package ohtu.kivipaperisakset;

import java.util.HashMap;
import java.util.Map;

public abstract class KiviSaksetPaperi {
    //private static final Scanner scanner = new Scanner(System.in);
    protected IO io;
    protected String ekanViimeisinSiirto;
    //private static Map<String, KiviSaksetPaperi> pelivaihtoehdot;

    public KiviSaksetPaperi(IO io) {
        this.io = io;

    }

    public static void aloitaPeli(IO io) {
        String infoViesti = "peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s";
        Map<String, KiviSaksetPaperi> pelivaihtoehdot = new HashMap<>();
        //pelivaihtoehdot = new HashMap<>();
        pelivaihtoehdot.put("a", new KPSPelaajaVsPelaaja(io));
        pelivaihtoehdot.put("b", new KPSTekoaly(io));
        pelivaihtoehdot.put("c", new KPSParempiTekoaly(io));

        while (true) {
            io.tulosta("\nValitse pelataanko" + "\n (a) ihmistä vastaan " + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan" + "\nmuilla valinnoilla lopetataan");

            String vastaus = io.keraaTekstisyote("");
            io.tulosta(infoViesti);
            System.out.println(pelivaihtoehdot.toString());
            if (!pelivaihtoehdot.containsKey(vastaus)) {
                break;
            }

            pelivaihtoehdot.get(vastaus).pelaa();
            /*if (vastaus.endsWith("a")) {
                io.tulosta(infoViesti);
                KPSPelaajaVsPelaaja kaksinpeli = new KPSPelaajaVsPelaaja(io);
                kaksinpeli.pelaa();
            } else if (vastaus.endsWith("b")) {
                io.tulosta(infoViesti);
                KPSTekoaly yksinpeli = new KPSTekoaly(io);
                yksinpeli.pelaa();
            } else if (vastaus.endsWith("c")) {
                io.tulosta(infoViesti);
                KPSParempiTekoaly pahaYksinpeli = new KPSParempiTekoaly(io);
                pahaYksinpeli.pelaa();
            } else {
                break;
            }*/
        }    
    }
    

    public void pelaa() {
        Tuomari tuomari = new Tuomari();

        String ekanSiirto = ensimmaisenSiirto();
        //io.keraaTekstisyote("Toisen pelaajan siirto: ");
        String tokanSiirto = toisenSiirto();

        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            io.tulosta(tuomari.toString());
            io.tulosta("");

            //System.out.println("Ensimmäisen pelaajan siirto: ");
            ekanSiirto = ensimmaisenSiirto();
            
            //System.out.println("Toisen pelaajan siirto: ");
            tokanSiirto = toisenSiirto();
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

    protected String getEkanViimeisinSiirto() {
        return ekanViimeisinSiirto;
    }

    abstract protected String toisenSiirto();

    protected static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }

    public KiviSaksetPaperi() {
    }
}