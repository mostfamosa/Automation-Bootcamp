package week5.apiTestExe.logic;

import week5.apiTestExe.infra.HttpRequest;
import week5.apiTestExe.infra.ResponseWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ApiCalls {
    private static final String BASE_URL = "https://deckofcardsapi.com/";


    //create new deck of cards
    public static ResponseWrapper createNewDeck() throws IOException {
        String url = BASE_URL + "api/deck/new";
        ResponseWrapper res;
        try {
            res = HttpRequest.postOnlyWithUrl(url);
        } catch (IOException e) {
            throw new IOException("Can't create new deck");
        }
        return res;
    }

    //draw two cards from the deck by deck_id
    public static ResponseWrapper drawCardsFromDeckById(String deck_id, int numberOfCardsToDraw) throws IOException {
        String url = BASE_URL + "api/deck/" + deck_id + "/draw/";
        ResponseWrapper res;
        Map<String, String> param = new HashMap<>();
        param.put("count", String.valueOf(numberOfCardsToDraw));
        try {
            res = HttpRequest.getWithoutHeader(url, param);
        } catch (IOException e) {
            throw new IOException("Can't draw card/s !");
        }
        return res;
    }

    /**
     * @param deck_id          the id of the deck
     * @param cardsToPutInPile they must be drawn from the deck before
     * @return response with pile data
     * @throws IOException
     */
    public static ResponseWrapper createNewPile(String deck_id, String pileName, ArrayList<Card> cardsToPutInPile) throws IOException {
        String url = BASE_URL + "api/deck/" + deck_id + "/pile/" + pileName + "/add/";
        ResponseWrapper res;
        Map<String, String> param = new HashMap<>();
        StringBuilder cardsCodes = new StringBuilder();
        for (Card card : cardsToPutInPile) {
            cardsCodes.append(card.getCode());
            cardsCodes.append(",");
        }
        param.put("cards", cardsCodes.toString());
        try {
            res = HttpRequest.getWithoutHeader(url, param);
        } catch (IOException e) {
            throw new IOException("Can't create new pile !");
        }
        return res;
    }

    //show the list of cards in "pileName"
    public static ResponseWrapper listOfCardsInPile(String deck_id, String pileName) throws IOException {
        String url = BASE_URL + "api/deck/" + deck_id + "/pile/" + pileName + "/list";
        ResponseWrapper res;

        try {
            res = HttpRequest.getOnlyWithUrl(url);
        } catch (IOException e) {
            throw new IOException("Can't show list of cards !");
        }
        return res;
    }


    //return drawn cards to deck
    public static ResponseWrapper returnDrawnCards(String deck_id) throws IOException {
        String url = BASE_URL + "api/deck/" + deck_id + "/return";
        ResponseWrapper res;

        try {
            res = HttpRequest.getOnlyWithUrl(url);
        } catch (IOException e) {
            throw new IOException("Can't return card/s !");
        }
        return res;
    }

    //return cards from pile to deck
    public static ResponseWrapper returnCardsFromPileToDeck(String deck_id, String pileName) throws IOException {
        String url = BASE_URL + "api/deck/" + deck_id + "/pile/" + pileName + "/return";
        ResponseWrapper res;

        try {
            res = HttpRequest.getOnlyWithUrl(url);
        } catch (IOException e) {
            throw new IOException("Can't return card/s !");
        }
        return res;
    }
}
