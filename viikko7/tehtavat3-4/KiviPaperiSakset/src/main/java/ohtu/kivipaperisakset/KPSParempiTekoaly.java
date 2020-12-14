package ohtu.kivipaperisakset;

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
}
