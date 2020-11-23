package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;     // luotava uusi taulukko on 
                                    // näin paljon isompi kuin vanha
    private int kasvatuskoko;       // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] joukko;           // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;       // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        this(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            kapasiteetti = KAPASITEETTI;
        }
        if (kasvatuskoko < 0) {
            kasvatuskoko = OLETUSKASVATUS;
        }
        joukko = new int[kapasiteetti];
        this.kasvatuskoko = kasvatuskoko;
    }

    private int loytyyKohdasta(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (joukko[i] == luku) {
                return i;
            }
        }
        return -1;
    }

    private void kasvataTaulukkoa() {
        int[] kasvatettuTaulukko = new int[alkioidenLkm + kasvatuskoko];
        kopioiJoukonAlkiotValittuunTaulukkoon(kasvatettuTaulukko);
        joukko = kasvatettuTaulukko;
    }

    private void kopioiJoukonAlkiotValittuunTaulukkoon(int[] uusiTaulukko) {
        for (int i = 0; i < alkioidenLkm; i++) {
            uusiTaulukko[i] = joukko[i];
        }
    }

    private void siirraAlkiotVasemmalleAlkaenKohdasta(int kohta) {
        while (kohta < alkioidenLkm) {
            joukko[kohta] = joukko[1 + kohta++];
        }
    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            joukko[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm % joukko.length == 0) {
                kasvataTaulukkoa();
            }
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        return loytyyKohdasta(luku) >= 0;
    }

    public boolean poista(int luku) {
        int kohta = loytyyKohdasta(luku);
        if (kohta != -1) {
            alkioidenLkm--;
            siirraAlkiotVasemmalleAlkaenKohdasta(kohta);
            return true;
        }
        return false;
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        String tuloste = "{";
        for (int i = 0; i < alkioidenLkm; i++) {
            tuloste += joukko[i];
            if (i < alkioidenLkm - 1) {
                tuloste += ", ";
            }
        }
        return tuloste + "}";
    }

    public int[] toIntArray() {
        int[] a = new int[alkioidenLkm];
        kopioiJoukonAlkiotValittuunTaulukkoon(a);
        return a;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko c = new IntJoukko(a.alkioidenLkm + b.alkioidenLkm + 5);
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();

        for (int i = 0; i < a.alkioidenLkm; i++) {
            c.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < b.alkioidenLkm; i++) {
            c.lisaa(bTaulu[i]);
        }
        
        return c;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko c = new IntJoukko();
        int[] aTaulu = a.toIntArray();

        for (int i = 0; i < a.alkioidenLkm; i++) {
            int luku = aTaulu[i];
            if (b.kuuluu(luku)) {
                c.lisaa(luku);
            }
        }
        return c;
    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko c = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        
        for (int i = 0; i < aTaulu.length; i++) {
            int luku = aTaulu[i];
            if (!b.kuuluu(luku)) {
                c.lisaa(luku);
            }
        }
        return c;
    }
}
