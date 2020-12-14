package ohtu.kivipaperisakset;

public class KPSTekoaly extends KiviSaksetPaperi {

    private Tekoaly tekoaly;

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
}