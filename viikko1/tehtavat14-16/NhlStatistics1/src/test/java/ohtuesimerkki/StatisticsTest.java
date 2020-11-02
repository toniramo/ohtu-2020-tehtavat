/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author toniramo
 */
public class StatisticsTest {
    
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void konstuktoriLuoPelaajatilastonJokaEiOleTyhja() {
        assertTrue(stats.search("") != null);
    }
    
    @Test
    public void tilastostaLoytyyPelaajaNimella() {
        //assertTrue(stats.search("Kurri") != null);
        assertEquals(new Player("Kurri",   "EDM", 37, 53).toString(),stats.search("Kurri").toString());
    }
    
    @Test
    public void tilastostaEiLoydySinneKuulumatontaPelaajaaNimella() {
        assertTrue(stats.search("Laine") == null);
    }
    
    @Test
    public void tilastostaLoytyyJoukkue() {
       List<Player> players = stats.team("EDM");
       assertFalse(players.isEmpty());
       for (Player player : players) {
           assertEquals("EDM", player.getTeam());
       }
    }
    
    @Test
    public void tilastostaEiLoydySinneKuulumatontaJoukkuetta() {
        assertTrue(stats.team("EDN").isEmpty());
    }
    
    @Test
    public void topScorersPalauttaaHalutunMaaranPelaajia() {
        assertEquals(4,stats.topScorers(4).size());
    }
    
    @Test
    public void topScorersPalauttaaKorkeintaanTilastossaOlevanMaaranPelaajia() {
        assertEquals(5,stats.topScorers(10).size());
    }
    
    @Test
    public void topScorersPalauttaaOikeanTopPelaajan() {
        assertEquals(new Player("Gretzky", "EDM", 35, 89).toString(),stats.topScorers(1).get(0).toString());
    }
}
