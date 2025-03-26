package org.example.scoreboard;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class InMemoryScoreboardTest {

    private Scoreboard scoreboard;

    @Before
    public void setUp() {
        scoreboard = new InMemoryScoreboard();
    }

    @Test
    public void testStartMatchAndGetSummary() {
        // 1) Start a match
        Match match = scoreboard.startMatch("Mexico", "Canada");
        // 2) Check it
        assertNotNull("Match should not be null", match);
        assertEquals("Mexico", match.getHomeTeam().getName());
        assertEquals("Canada", match.getAwayTeam().getName());
        assertEquals("Score should start at 0-0 (home)", 0, match.getHomeScore());
        assertEquals("Score should start at 0-0 (away)", 0, match.getAwayScore());

        // 3) Check scoreboard summary
        assertEquals("Should be exactly 1 match in summary", 1, scoreboard.getSummary().size());
        assertEquals(match, scoreboard.getSummary().get(0));
    }

    @Test
    public void testSummaryMultipleGamesRandomResults() {
        // Start multiple matches (order matters for tie-breaker: later started match comes first)
        Match m1 = scoreboard.startMatch("Team A", "Team B"); // first match
        Match m2 = scoreboard.startMatch("Team C", "Team D"); // second match
        Match m3 = scoreboard.startMatch("Team E", "Team F"); // third match
        Match m4 = scoreboard.startMatch("Team G", "Team H"); // fourth match
        Match m5 = scoreboard.startMatch("Team I", "Team J"); // fifth match

        // Update scores with random values:
        scoreboard.updateScore(m1, 1, 0); // Total = 1
        scoreboard.updateScore(m2, 2, 2); // Total = 4
        scoreboard.updateScore(m3, 0, 3); // Total = 3
        scoreboard.updateScore(m4, 3, 3); // Total = 6
        scoreboard.updateScore(m5, 2, 1); // Total = 3

    /*
     Expected ordering:
     1. m4: Total score 6 (highest)
     2. m2: Total score 4 (second highest)
     3. Tie between m3 and m5 (both total 3):
         - Since m5 was started after m3, m5 comes before m3.
     4. m1: Total score 1 (lowest)
    */

        List<Match> summary = scoreboard.getSummary();
        assertEquals(5, summary.size());
        assertEquals(m4, summary.get(0));
        assertEquals(m2, summary.get(1));
        assertEquals(m5, summary.get(2)); // m5 is more recent than m3
        assertEquals(m3, summary.get(3));
        assertEquals(m1, summary.get(4));
    }

    @Test
    public void testUpdateScore() {
        Match match = scoreboard.startMatch("Mexico", "Canada");
        scoreboard.updateScore(match, 3, 2);
        assertEquals(3, match.getHomeScore());
        assertEquals(2, match.getAwayScore());
    }

    @Test
    public void testFinishMatch() {
        Match m1 = scoreboard.startMatch("Mexico", "Canada");
        Match m2 = scoreboard.startMatch("Spain", "Brazil");

        assertEquals("There should be 2 matches initially", 2, scoreboard.getSummary().size());

        scoreboard.finishMatch(m1);
        assertEquals("Now only 1 match remains", 1, scoreboard.getSummary().size());
        assertEquals("That match should be m2", m2, scoreboard.getSummary().get(0));
    }

    @Test
    public void testGetSummarySorting() {
        // Start 3 matches
        Match m1 = scoreboard.startMatch("Mexico", "Canada");
        Match m2 = scoreboard.startMatch("Spain", "Brazil");
        Match m3 = scoreboard.startMatch("Germany", "France");

        // Update scores
        scoreboard.updateScore(m1, 0, 5);  // total = 5
        scoreboard.updateScore(m2, 10, 2); // total = 12
        scoreboard.updateScore(m3, 2, 2);  // total = 4

        // Expected order: m2(12) > m1(5) > m3(4)
        assertEquals(3, scoreboard.getSummary().size());
        assertEquals(m2, scoreboard.getSummary().get(0));
        assertEquals(m1, scoreboard.getSummary().get(1));
        assertEquals(m3, scoreboard.getSummary().get(2));
    }

    @Test
    public void testGetSummaryTieBreak() {
        // Start 2 matches
        Match m1 = scoreboard.startMatch("Uruguay", "Italy");
        Match m2 = scoreboard.startMatch("Argentina", "Australia");

        // Make them both have total = 6
        scoreboard.updateScore(m1, 3, 3);
        scoreboard.updateScore(m2, 3, 3);

        // Tied on total => the most recently started match (m2) should appear first
        assertEquals(2, scoreboard.getSummary().size());
        assertEquals(m2, scoreboard.getSummary().get(0));
        assertEquals(m1, scoreboard.getSummary().get(1));
    }
}
