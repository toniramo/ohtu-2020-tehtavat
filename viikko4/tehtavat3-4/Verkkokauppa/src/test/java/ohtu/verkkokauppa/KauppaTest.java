/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.verkkokauppa;

import ohtu.verkkokauppa.*;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author toniramo
 */
public class KauppaTest {
    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa k;
    
    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
        k = new Kauppa(varasto, pankki, viite);  
    }

    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaan() {
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());
    }

    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikeallaAsiakkaallaTilinumerollaJaSummalla() {
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");
        //metodin tilisiirto parametrit: String nimi, int viitenumero, String tililta, String tilille, int summa
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5));
    }

    @Test
    public void kahdenSamanOstonYhteydessaPankinMetodiaTilisiirtoKutsutaanOikeallaAsiakkaallaTilinumerollaJaSummalla() {
        when(viite.uusi()).thenReturn(12);
        when(varasto.saldo(1)).thenReturn(2);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("viljo", "viljontili-1231");

        verify(pankki).tilisiirto(eq("viljo"), eq(12), eq("viljontili-1231"), eq("33333-44455"), eq(10));
    }

    @Test
    public void varastossaolevanJaLoppuneenTuotteenOstonYhteydessaPankinMetodiaTilisiirtoKutsutaanOikeallaAsiakkaallaTilinumerollaJaSummalla() {
        when(viite.uusi()).thenReturn(12);
        when(varasto.saldo(1)).thenReturn(2);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(0);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "piimä", 4));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("viljo", "viljontili-1231");

        verify(pankki).tilisiirto(eq("viljo"), eq(12), eq("viljontili-1231"), eq("33333-44455"), eq(5));
    }
    
    @Test
    public void kaupanMetodiAloitaAsiointiNollaaEdellisetOstokset() {
        when(viite.uusi())
                .thenReturn(12);
        when(varasto.saldo(1))
                .thenReturn(2)
                .thenReturn(1);
        when(varasto.haeTuote(1))
                .thenReturn(new Tuote(1, "maito", 5));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        
        k.aloitaAsiointi();
        
        k.tilimaksu("viljo", "viljontili-1231");
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), eq(0));
    }
    
    @Test
    public void jokaiselleMaksutapahtumallePyydetaanUusiViitenumero() {
        when(viite.uusi())
                .thenReturn(12)
                .thenReturn(13);
        when(varasto.saldo(1))
                .thenReturn(2)
                .thenReturn(1);
        when(varasto.saldo(2))
                .thenReturn(10)
                .thenReturn(9);
        when(varasto.haeTuote(1))
                .thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2))
                .thenReturn(new Tuote(2, "kananmuna", 8));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("viljo", "viljontili-1231");
        
        verify(viite, times(1)).uusi();
        verify(pankki).tilisiirto(anyString(), eq(12), anyString(), anyString(), anyInt());
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("matti", "tili-1232");
        
        verify(viite, times(2)).uusi();
        verify(pankki).tilisiirto(anyString(), eq(13), anyString(), anyString(), anyInt());
    }
    
    @Test
    public void ostoskoriinLisatynTuotteenPoistaminenPoistaaSenKoristaJaPalauttaaVarastoon() {
        Tuote maito = new Tuote(1, "maito", 5);
        
        when(viite.uusi())
                .thenReturn(12);
        when(varasto.saldo(1))
                .thenReturn(2)
                .thenReturn(1);
        when(varasto.saldo(2))
                .thenReturn(10);
        when(varasto.haeTuote(1))
                .thenReturn(maito);
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.poistaKorista(1);
        
        verify(varasto,times(1)).palautaVarastoon(maito);
        
        k.tilimaksu("Käyttäjä", "111-222");
        
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), eq(5));
    }
}
