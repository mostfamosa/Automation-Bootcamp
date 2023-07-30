package week4.tzahiGame.logic;

import week4.tzahiGame.DTOs.CardDTO;
import week4.tzahiGame.DTOs.DeckDTO;
import week4.tzahiGame.response.ResponseWrapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import static week4.tzahiGame.infra.request.requestGet;
import static week4.tzahiGame.infra.request.requestPost;

public class gameApis {
    private static final String BASE_URL = "https://www.deckofcardsapi.com/";

    public static ResponseWrapper<DeckDTO> createNewDeck() throws IOException {
        String url = BASE_URL + "api/deck/new/shuffle/";
        return requestPost(url, DeckDTO.class);
    }

    public static ResponseWrapper<DeckDTO> drawCardsFromDeckById(String deckId, int numberOfCardsToDraw) throws IOException {
        String url = BASE_URL + "api/deck/" + deckId + "/draw/";
        Map<String, String> param = new HashMap<>();
        param.put("count", String.valueOf(numberOfCardsToDraw));

        return requestGet(url, param, DeckDTO.class);
    }

    public static void setActualValue(CardDTO card) {
        switch (card.getValue()){
            case "ACE":
                card.setGameValue(14);
                break;
            case "2":
                card.setGameValue(2);
                break;
            case "3":
                card.setGameValue(3);
                break;
            case "4":
                card.setGameValue(4);
                break;
            case "5":
                card.setGameValue(5);
                break;
            case "6":
                card.setGameValue(6);
                break;
            case "7":
                card.setGameValue(7);
                break;
            case "8":
                card.setGameValue(8);
                break;
            case "9":
                card.setGameValue(9);
                break;
            case "10":
                card.setGameValue(10);
                break;
            case "JACK":
                card.setGameValue(11);
                break;
            case "QUEEN":
                card.setGameValue(12);
                break;
            case "KING":
                card.setGameValue(13);
                break;
        }
    }
}
