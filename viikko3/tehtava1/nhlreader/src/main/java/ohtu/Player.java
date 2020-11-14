
package ohtu;

/* 
Example from https://nhlstatisticsforohtu.herokuapp.com/players
{
    "name":"Travis Zajac",
    "nationality":"CAN",
    "assists":16,
    "goals":9,
    "penalties":28,
    "team":"NJD",
    "games":69}
} 
*/

public class Player {
    private String name;
    private String nationality;
    private int assists;
    private int goals;
    private int penalties;
    private String team;
    private int games;
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getPenalties() {
        return penalties;
    }

    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }

    @Override
    public String toString() {
        return name + " "
                + nationality + " "
                + assists + " "
                + goals + " "
                + penalties + " "
                + team + " "
                + games;
    }
      
}
