package week5.apiTestExe.entities.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class DeckDTO {
    private boolean success;
    @JsonProperty("deck_id")
    private String deckId;
    private int remaining;
    private boolean shuffled;
    private List<CardDTO> cards;
    private Map<String, PileDTO> piles;
    private String error;
}
