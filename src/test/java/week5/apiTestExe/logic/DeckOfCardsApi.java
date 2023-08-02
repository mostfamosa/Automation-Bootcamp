package week5.apiTestExe.logic;


import week5.apiTestExe.logic.entities.DTOs.CardDTO;
import week5.apiTestExe.logic.entities.DTOs.DeckDTO;
import week5.apiTestExe.infra.HttpRequest;
import week5.apiTestExe.logic.entities.enums.HttpMethods;
import week5.apiTestExe.infra.ResponseWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DeckOfCardsApi {
    private static final String BASE_URL = "https://deckofcardsapi.com/";


    //create new deck of cards
    public static ResponseWrapper<DeckDTO> createNewDeck()  {
        String url = BASE_URL + "api/deck/new";
        return HttpRequest.request(HttpMethods.POST, url, DeckDTO.class);
    }

    //draw two cards from the deck by deckId
    public static ResponseWrapper<DeckDTO> createNumberOfDecks(int numberOfDecks)  {
        String url = BASE_URL + "api/deck/new/shuffle/";
        Map<String, String> param = new HashMap<>();
        param.put("deck_count", String.valueOf(numberOfDecks));
        return HttpRequest.request(HttpMethods.GET, url, param, DeckDTO.class);
    }

    //draw two cards from the deck by deckId
    public static ResponseWrapper<DeckDTO> drawCardsFromDeckById(String deckId, int numberOfCardsToDraw)  {
        String url = BASE_URL + "api/deck/" + deckId + "/draw/";
        Map<String, String> param = new HashMap<>();
        param.put("count", String.valueOf(numberOfCardsToDraw));
        return HttpRequest.request(HttpMethods.GET, url, param, DeckDTO.class);
    }

    //create new pile from the deck
    public static ResponseWrapper<DeckDTO> createNewPile(String deckId, String pileName, ArrayList<CardDTO> cardsToPutInPile)  {
        String url = BASE_URL + "api/deck/" + deckId + "/pile/" + pileName + "/add/";
        Map<String, String> param = new HashMap<>();
        StringBuilder cardsCodes = new StringBuilder();

        for (CardDTO card : cardsToPutInPile) {
            cardsCodes.append(card.getCode());
            cardsCodes.append(",");
        }
        param.put("cards", cardsCodes.toString());
        return HttpRequest.request(HttpMethods.GET, url, param, DeckDTO.class);
    }

    //show the list of cards in "pileName"
    public static ResponseWrapper<DeckDTO> listOfCardsInPile(String deckId, String pileName)  {
        String url = BASE_URL + "api/deck/" + deckId + "/pile/" + pileName + "/list";
        return HttpRequest.request(HttpMethods.GET, url, DeckDTO.class);
    }


    //return drawn cards to deck
    public static ResponseWrapper<DeckDTO> returnDrawnCards(String deckId) {
        String url = BASE_URL + "api/deck/" + deckId + "/return";
        return HttpRequest.request(HttpMethods.GET, url, DeckDTO.class);
    }

    //return cards from pile to deck
    public static ResponseWrapper<DeckDTO> returnCardsFromPileToDeck(String deckId, String pileName)  {
        String url = BASE_URL + "api/deck/" + deckId + "/pile/" + pileName + "/return";
        return HttpRequest.request(HttpMethods.GET, url, DeckDTO.class);
    }
}
