/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import org.apache.http.client.fluent.Request;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";

        String bodyText = Request.Get(url).execute().returnContent().asString();

        //System.out.println("json-muotoinen data:");
        //System.out.println( bodyText );

        Gson mapper = new Gson();
        Player[] players = mapper.fromJson(bodyText, Player[].class);

        /*System.out.println("Oliot:");
        for (Player player : players) {
            System.out.println(player);
        }*/
        System.out.println("Players from FIN " 
                + java.util.Calendar.getInstance().getTime() + "\n");
        
        System.out.printf("%-20s %-6s %2s + %2s   %2s%n", 
                "Name", "Tm.", "G.", "A.", "Points");
        
        Arrays.stream(players).filter(p -> p.getNationality().equals("FIN"))
                .sorted(Comparator.comparing(Player::getGoalsAndAssists).reversed())
                .forEach(p -> System.out.printf("%-20s %-6s %2d + %2d = %2d%n", 
                        p.getName(), 
                        p.getTeam(), 
                        p.getGoals(), 
                        p.getAssists(), 
                        p.getGoalsAndAssists()));
    }
}
