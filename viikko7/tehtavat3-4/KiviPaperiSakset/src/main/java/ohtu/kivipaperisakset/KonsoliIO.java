package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KonsoliIO implements IO {
    Scanner scanner;

    public KonsoliIO(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void tulosta(String viesti) {
        System.out.println(viesti);

    }

    @Override
    public String keraaTekstisyote(String viesti) {
        System.out.print(viesti);
        String syote = scanner.nextLine();
        return syote;
    }
    
}
