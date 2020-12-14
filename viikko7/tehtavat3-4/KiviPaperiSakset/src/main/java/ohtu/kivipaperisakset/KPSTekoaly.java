package ohtu.kivipaperisakset;

public class KPSTekoaly extends KiviSaksetPaperi {

    Tekoaly tekoaly;

    public KPSTekoaly(IO io) {
        super(io);
        tekoaly = new Tekoaly();
    }

    @Override
    protected String toisenSiirto() {
        String tekoalynSiirto = tekoaly.annaSiirto();
        io.tulosta("Tietokone valitsi: " + tekoalynSiirto);
        return tekoalynSiirto;
    }



    /*private static final Scanner scanner = new Scanner(System.in);

    public void pelaa() {
        Tuomari tuomari = new Tuomari();
        Tekoaly tekoaly = new Tekoaly();

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
    }

    private static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }*/
}