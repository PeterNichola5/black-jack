package org.project.players;

public class HumanPlayer extends Player {
    private String name;

    public HumanPlayer(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
