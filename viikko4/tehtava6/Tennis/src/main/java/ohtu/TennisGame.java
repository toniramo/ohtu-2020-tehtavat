package ohtu;

import java.util.HashMap;
import java.util.Map;

public class TennisGame {

    private int player1Score;
    private int player2Score;
    private String player1Name;
    private String player2Name;
    private Map<Integer, String> scoreDescriptions;
    private int minimumWinningScore;
    private enum ScoreType { DEFAULT, EVEN, ADVANTAGE, WINNING };

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        setScoreDescriptions();
        setMinimumWinningScore();
    }

    private void setScoreDescriptions() {
        scoreDescriptions = new HashMap<>();
        scoreDescriptions.put(0, "Love");
        scoreDescriptions.put(1, "Fifteen");
        scoreDescriptions.put(2, "Thirty");
        scoreDescriptions.put(3, "Forty");
    }

    private void setMinimumWinningScore() {
        minimumWinningScore = scoreDescriptions.size();
    }

    private int scoreDifference() {
        return Math.abs(player1Score - player2Score);
    }

    private boolean scoreEven() {
        return scoreDifference() == 0;
    }

    private String getEvenScoreDescription() {
        //if (scoreDescriptions.containsKey(player1Score)) {
        if (player1Score < minimumWinningScore) {
            return scoreDescriptions.get(player1Score) + "-All";
        }
        return "Deuce";
    }

    private boolean scoresBelowMinimumWinningScore() {
        return Math.max(player1Score, player2Score) < minimumWinningScore;
    }

    private boolean scoreAdvantage() {
        if (scoresBelowMinimumWinningScore()) {
            return false;
        }
        return scoreDifference() == 1;
    }
    
    private String getSpecialScoreDescription(String description) {
        return description + (player1Score > player2Score ? "1" : "2");
    }

    private String getAdvantageScoreDescription() {
        return getSpecialScoreDescription("Advantage player");
    }

    private boolean scoreWinning() {
        if (scoresBelowMinimumWinningScore()) {
            return false;
        }
        return scoreDifference() > 1;
    }

    private String getWinningScoreDescription() {
        return getSpecialScoreDescription("Win for player");
    }

    private String getDefaultScoreDescription() {
        return scoreDescriptions.get(player1Score) + "-" + scoreDescriptions.get(player2Score);
    }
    
    private ScoreType getScoreType() {
        if (scoreEven()) {
            return ScoreType.EVEN;
        }
        if (scoreWinning()) {
            return ScoreType.WINNING;
        }
        if (scoreAdvantage()) {
            return ScoreType.ADVANTAGE;
        }
        return ScoreType.DEFAULT; 
    }
    
    private String getScoreDescription(ScoreType type) {
        if (type.equals(ScoreType.EVEN)) {
            return getEvenScoreDescription();
        }
        if (type.equals(ScoreType.WINNING)) {
            return getWinningScoreDescription();
        }
        if (type.equals(ScoreType.ADVANTAGE)) {
            return getAdvantageScoreDescription();
        }
        return getDefaultScoreDescription();
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name)) {
            player1Score++;
            return;
        }
        player2Score++;
    }

    public String getScore() {
        return getScoreDescription(getScoreType());
    }
}
