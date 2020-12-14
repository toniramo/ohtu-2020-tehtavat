package ohtu.kivipaperisakset;

// Kivi-Paperi-Sakset, jossa voidaan valita pelataanko vastustajaa
// vastaan vai ei
public class KPSParempiTekoaly extends KiviSaksetPaperi {

    private TekoalyParannettu tekoaly;

    public KPSParempiTekoaly (IO io) {
        this(io, 20);

    }

    public KPSParempiTekoaly (IO io, int tekoalynMuistinKoko) {
        super(io);
        tekoaly = new TekoalyParannettu(tekoalynMuistinKoko);
    }

    @Override
    protected String toisenSiirto() {
        String tekoalynSiirto = tekoaly.annaSiirto();
        io.tulosta("Tietokone valitsi: " + tekoalynSiirto);
        tekoaly.asetaSiirto(ekanViimeisinSiirto);
        return tekoalynSiirto;
    }

    //private static final Scanner scanner = new Scanner(System.in);

    /*
    public void pelaa() {
        Tuomari tuomari = new Tuomari();
        TekoalyParannettu tekoaly = new TekoalyParannettu(20);

        System.out.println("Ensimmäisen pelaajan siirto: ");
        String ekanSiirto = scanner.nextLine();
        String tokanSiirto;


        tokanSiirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + tokanSiirto);


        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(tuomari);
            System.out.println();

            System.out.println("Ensimmäisen pelaajan siirto: ");
            ekanSiirto = scanner.nextLine();

            tokanSiirto = tekoaly.annaSiirto();
            System.out.println("Tietokone valitsi: " + tokanSiirto);
            tekoaly.asetaSiirto(ekanSiirto);

        }

        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
    }*/

    /*
    private static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }*/
}
