package org.example.scoreboard;

import java.util.List;

/**
 * Defines the operations for a live football World Cup scoreboard.
 */
public interface Scoreboard {
    /**
     * Starts a new match with initial 0-0 score.
     * @param homeTeamName Name of the home team.
     * @param awayTeamName Name of the away team.
     * @return The newly created Match.
     */
    Match startMatch(String homeTeamName, String awayTeamName);

    /**
     * Updates the score for a given match.
     * @param match The match to update.
     * @param homeScore Absolute home score.
     * @param awayScore Absolute away score.
     */
    void updateScore(Match match, int homeScore, int awayScore);

    /**
     * Finishes a match, removing it from the scoreboard.
     * @param match The match to finish.
     */
    void finishMatch(Match match);

    /**
     * Returns a summary of matches currently in progress.
     * Ordered:
     *   1) by total score (descending)
     *   2) if tie, by the more recently started match first.
     * @return A list of Match objects in the required order.
     */
    List<Match> getSummary();
}
