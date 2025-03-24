package org.example.scoreboard;

/**
 * Represents a football team with a name.
 */
public class Team {
    private final String name;

    /**
     * Creates a new team with the given name.
     * @param name The team's name.
     */
    public Team(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Team name cannot be null or empty");
        }
        this.name = name.trim();
    }

    /**
     * @return The name of this team.
     */
    public String getName() {
        return name;
    }
}
