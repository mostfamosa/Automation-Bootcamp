package week5.apiTestExe.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class DeckInfoResponse {
    private boolean success;
    @JsonProperty("deck_id")
    private String deckId;
    private int remaining;
    private boolean shuffled;
    private List<Card> cards;
    private Map<String, Pile> piles;
}
