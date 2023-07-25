package week5.apiTestExe.logic;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class DeckInfo {
    private boolean success;
    private String deck_id;
    private int remaining;
    private boolean shuffled;
    private List<Card> cards;
    private Map<String, Pile> piles;

}
