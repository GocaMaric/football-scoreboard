package org.example.scoreboard;

/**
 * Represents a match between two teams, along with the current score.
 */
public class Match {
    private final Team homeTeam;
    private final Team awayTeam;
    private int homeScore;
    private int awayScore;

    /**
     * Creates a new match with two teams, initial score of 0-0.
     * @param homeTeam The home team.
     * @param awayTeam The away team.
     */
    public Match(Team homeTeam, Team awayTeam) {
        if (homeTeam == null || awayTeam == null) {
            throw new IllegalArgumentException("Teams cannot be null");
        }
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;
        this.awayScore = 0;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    /**
     * Updates the match score with absolute values (not increments).
     */
    public void updateScore(int homeScore, int awayScore) {
        if (homeScore < 0 || awayScore < 0) {
            throw new IllegalArgumentException("Scores cannot be negative");
        }
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    /**
     * @return The total score for this match (home + away).
     */
    public int getTotalScore() {
        return homeScore + awayScore;
    }
}
