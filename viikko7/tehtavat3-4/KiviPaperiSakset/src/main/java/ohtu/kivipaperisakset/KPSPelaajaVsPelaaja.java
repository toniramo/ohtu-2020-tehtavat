package ohtu.kivipaperisakset;

public class KPSPelaajaVsPelaaja extends KiviSaksetPaperi {

    public KPSPelaajaVsPelaaja(IO io) {
        super(io);
    }

    @Override
    protected String toisenSiirto() {
        return io.keraaTekstisyote("Toisen pelaajan siirto: ");
    }
    
}