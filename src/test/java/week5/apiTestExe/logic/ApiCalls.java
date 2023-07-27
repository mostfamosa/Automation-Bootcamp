package week5.apiTestExe.logic;

import org.codehaus.jackson.map.ObjectMapper;

import week5.apiTestExe.entity.Card;
import week5.apiTestExe.entity.DeckInfoResponse;
import week5.apiTestExe.infra.HttpRequest;
import week5.apiTestExe.infra.ResponseWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ApiCalls {
    private static final String BASE_URL = "https://deckofcardsapi.com/";
    private static ObjectMapper objectMapper = new ObjectMapper();

    //create new deck of cards
    public static ResponseWrapper<DeckInfoResponse> createNewDeck() {
        String url = BASE_URL + "api/deck/new";
        return HttpRequest.request(HttpMethod.POST, url, DeckInfoResponse.class);
    }

    //draw two cards from the deck by deckId
    public static ResponseWrapper<DeckInfoResponse> drawCardsFromDeckById(String deckId, int numberOfCardsToDraw) throws IOException {
        String url = BASE_URL + "api/deck/" + deckId + "/draw/";
        Map<String, String> param = new HashMap<>();
        param.put("count", String.valueOf(numberOfCardsToDraw));

        return HttpRequest.request(HttpMethod.GET, url, param, DeckInfoResponse.class);
    }

    //create new pile from the deck
    public static ResponseWrapper<DeckInfoResponse> createNewPile(String deckId, String pileName, ArrayList<Card> cardsToPutInPile) throws IOException {
        String url = BASE_URL + "api/deck/" + deckId + "/pile/" + pileName + "/add/";
        Map<String, String> param = new HashMap<>();
        StringBuilder cardsCodes = new StringBuilder();

        for (Card card : cardsToPutInPile) {
            cardsCodes.append(card.getCode());
            cardsCodes.append(",");
        }
        param.put("cards", cardsCodes.toString());
        return HttpRequest.request(HttpMethod.GET, url, param, DeckInfoResponse.class);
    }

    //show the list of cards in "pileName"
    public static ResponseWrapper<DeckInfoResponse> listOfCardsInPile(String deckId, String pileName) throws IOException {
        String url = BASE_URL + "api/deck/" + deckId + "/pile/" + pileName + "/list";
        return HttpRequest.request(HttpMethod.GET, url, DeckInfoResponse.class);
    }


    //return drawn cards to deck
    public static ResponseWrapper<DeckInfoResponse> returnDrawnCards(String deckId) throws IOException {
        String url = BASE_URL + "api/deck/" + deckId + "/return";
        return HttpRequest.request(HttpMethod.GET, url, DeckInfoResponse.class);
    }

    //return cards from pile to deck
    public static ResponseWrapper<DeckInfoResponse> returnCardsFromPileToDeck(String deckId, String pileName) throws IOException {
        String url = BASE_URL + "api/deck/" + deckId + "/pile/" + pileName + "/return";
        return HttpRequest.request(HttpMethod.GET, url, DeckInfoResponse.class);
    }
}
