package week4.tzahiGame.DTOs;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Player {
    private String name;
    private int score = 0;
    private DeckDTO deckDTO = null;
    private List<String> winningCards = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }
}
