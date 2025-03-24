package org.example.scoreboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * In-memory implementation of the Scoreboard interface.
 */
public class InMemoryScoreboard implements Scoreboard {

    // We'll track matches in the order they were started.
    // The last element of the list is the most recently started match.
    private final LinkedList<Match> matches = new LinkedList<>();

    @Override
    public Match startMatch(String homeTeamName, String awayTeamName) {
        Team home = new Team(homeTeamName);
        Team away = new Team(awayTeamName);
        Match match = new Match(home, away);
        matches.addLast(match);
        return match;
    }

    @Override
    public void updateScore(Match match, int homeScore, int awayScore) {
        match.updateScore(homeScore, awayScore);
    }

    @Override
    public void finishMatch(Match match) {
        matches.remove(match);
    }

    @Override
    public List<Match> getSummary() {
        // Make a copy so we don't mutate the original matches order
        List<Match> copy = new ArrayList<>(matches);

        // Sort by total score desc, then by recency if there's a tie
        Collections.sort(copy, (m1, m2) -> {
            int scoreCompare = Integer.compare(m2.getTotalScore(), m1.getTotalScore());
            if (scoreCompare != 0) {
                return scoreCompare;
            }
            // Tie in total score => show the more recently started match first
            int idx1 = matches.indexOf(m1);
            int idx2 = matches.indexOf(m2);
            // The match with the higher index in the original list is more recent
            return Integer.compare(idx2, idx1);
        });

        return copy;
    }
}
