package week5.apiTestExe.test;

import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import week5.apiTestExe.infra.ResponseWrapper;
import week5.apiTestExe.logic.ApiCalls;
import week5.apiTestExe.entity.Card;
import week5.apiTestExe.entity.DeckInfoResponse;
import week5.apiTestExe.entity.Pile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestApi {
    private final String pileName = "Mostafa";
    private ResponseWrapper<DeckInfoResponse> response;
    private String deckId = null;

    @BeforeEach
    void setUp() {
        response = ApiCalls.createNewDeck();
        deckId = response.getData().getDeckId();
    }


    @Test
    @Story("user trying to create new deck of cards")
    void create_New_Deck_Validations() {

        System.out.println("create_New_Deck_Validations:");
        System.out.println(response.getData());

        //assert
        assertAll(
                //status validation
                () -> assertEquals(200, response.getStatus(), "Expected status code is 200"),

                //not null validation
                () -> assertThat(response.getData().getDeckId(), notNullValue()),
                () -> assertThat(response.getData().getRemaining(), notNullValue()),
                () -> assertThat(response.getData().isShuffled(), notNullValue()),
                () -> assertThat(response.getData().isSuccess(), notNullValue()),

                //data validation
                () -> assertTrue(response.getData().isSuccess()),
                () -> assertThat(response.getData().getRemaining(), equalTo(52)),
                () -> assertFalse(response.getData().isShuffled())
        );
    }

    @Test
    @Story("user trying to draw 2 cards from the deck")
    void draw_Two_Cards_From_Deck_Successfully() {
        //act
        try {
            response = ApiCalls.drawCardsFromDeckById(deckId, 2);
        } catch (IOException e) {
            throw new RuntimeException("Can't draw cards!\n " + e);
        }

        System.out.println("draw_Two_Cards_From_Deck:");
        System.out.println(response.getData());


        //assert
        assertAll(
                //status validation
                () -> assertEquals(200, response.getStatus(), "Expected status code is 200"),

                //not null validation
                () -> assertThat(response.getData().getDeckId(), notNullValue()),
                () -> assertThat(response.getData().getRemaining(), notNullValue()),
                () -> assertThat(response.getData().isSuccess(), notNullValue()),
                () -> assertThat(response.getData().getCards(), notNullValue()),


                //data validation
                () -> assertTrue(response.getData().isSuccess()),
                () -> assertThat(response.getData().getRemaining(), equalTo(50)),
                () -> assertEquals(2, response.getData().getCards().size())
        );
    }

    @Test
    @Story("user trying to create new pile from the drawn cards")
    void create_New_Pile_Successfully() {
        //Arrange
        ResponseWrapper<DeckInfoResponse> responseFromDrawingCards;

        try {
             responseFromDrawingCards = ApiCalls.drawCardsFromDeckById(deckId, 2);
        } catch (IOException e) {
            throw new RuntimeException("Can't draw cards!\n " + e);
        }
        Card firstCard = responseFromDrawingCards.getData().getCards().get(0);
        Card secondCard = responseFromDrawingCards.getData().getCards().get(1);

        ArrayList<Card> cards = new ArrayList<>();
        cards.add(firstCard);
        cards.add(secondCard);

        //Act
        try {
            response = ApiCalls.createNewPile(deckId, pileName, cards);
        } catch (IOException e) {
            throw new RuntimeException("Can't create new pile !\n " + e);
        }

        //get the pile name and Pile object from the response
        String pileNameCheck = null;
        Pile pileCheck = null;
        for (Map.Entry<String, Pile> entry : response.getData().getPiles().entrySet()) {
            if (entry.getKey().equals(pileName)) {
                pileNameCheck = entry.getKey();
                pileCheck = entry.getValue();
            }
        }
        System.out.println("create_New_Pile:");
        System.out.println(response.getData());


        //Assert pile info
        assertEquals(pileName, pileNameCheck);
        assertEquals(2, pileCheck.getRemaining());

        assertAll(
                //status validation
                () -> assertEquals(200, response.getStatus(), "Expected status code is 200"),

                //not null validation
                () -> assertThat(response.getData().getDeckId(), notNullValue()),
                () -> assertThat(response.getData().getRemaining(), notNullValue()),
                () -> assertThat(response.getData().isSuccess(), notNullValue()),
                () -> assertThat(response.getData().getPiles(), notNullValue()),


                //data validation
                () -> assertTrue(response.getData().isSuccess()),
                () -> assertThat(response.getData().getRemaining(), equalTo(50)),
                () -> assertFalse(response.getData().isShuffled()),
                () -> assertThat(response.getData().getPiles().size(), equalTo(1))
        );

    }

    @Test
    @Story("validating that the cards are in the pile")
    void validate_Card_Is_In_Pile() {
        //arrange
        ResponseWrapper<DeckInfoResponse> responseFromDrawingCards, responseFromCreatingNewPile;
        //draw 2 cards
        try {
            responseFromDrawingCards = ApiCalls.drawCardsFromDeckById(deckId, 2);
        } catch (IOException e) {
            throw new RuntimeException("Can't draw cards!\n " + e);
        }
        Card firstCard = responseFromDrawingCards.getData().getCards().get(0);
        Card secondCard = responseFromDrawingCards.getData().getCards().get(1);

        ArrayList<Card> cards = new ArrayList<>();
        cards.add(firstCard);
        cards.add(secondCard);

        //create new pile and add the 2 cards to it
        try {
            responseFromCreatingNewPile = ApiCalls.createNewPile(deckId, pileName, cards);
        } catch (IOException e) {
            throw new RuntimeException("Can't create new pile !\n " + e);
        }

        //act
        try {
            response = ApiCalls.listOfCardsInPile(deckId, pileName);
        } catch (IOException e) {
            throw new RuntimeException("Can't show cards in the pile !\n " + e);
        }

        System.out.println("validate_Card_Is_In_Pile:");
        System.out.println(response.getData());


        //assert
        assertAll(
                //status validation
                () -> assertEquals(200, response.getStatus(), "Expected status code is 200"),

                //not null validation
                () -> assertThat(response.getData().getDeckId(), notNullValue()),
                () -> assertThat(response.getData().getRemaining(), notNullValue()),
                () -> assertThat(response.getData().isSuccess(), notNullValue()),
                () -> assertThat(response.getData().getPiles(), notNullValue()),
                () -> assertThat(response.getData().getPiles().get(pileName), notNullValue()),
                () -> assertThat(response.getData().getPiles().get(pileName).getRemaining(), notNullValue()),
                () -> assertThat(response.getData().getPiles().get(pileName).getCards(), notNullValue()),


                //data validation
                () -> assertTrue(response.getData().isSuccess()),
                () -> assertThat(response.getData().getRemaining(), equalTo(50)),
                () -> assertFalse(response.getData().isShuffled()),
                () -> assertThat(response.getData().getPiles().size(), equalTo(1)),
                () -> assertThat(response.getData().getPiles().get(pileName).getRemaining(), equalTo(2)),
                () -> assertThat(response.getData().getPiles().get(pileName).getCards().size(), equalTo(2)),
                () -> assertThat(response.getData().getPiles().get(pileName).getCards(), hasItem(cards.get(0))),
                () -> assertThat(response.getData().getPiles().get(pileName).getCards(), hasItem(cards.get(1)))
        );

    }

    @Test
    @Story("validating that the cards are NOT in the deck")
    void validate_Card_Is_Not_In_Deck() {
        //arrange
        ResponseWrapper<DeckInfoResponse> responseFromDrawingCards, responseFromCreatingNewPile;
        //draw 2 cards
        try {
            responseFromDrawingCards = ApiCalls.drawCardsFromDeckById(deckId, 2);
        } catch (IOException e) {
            throw new RuntimeException("Can't draw cards!\n " + e);
        }
        Card firstCard = responseFromDrawingCards.getData().getCards().get(0);
        Card secondCard = responseFromDrawingCards.getData().getCards().get(1);

        ArrayList<Card> cards = new ArrayList<>();
        cards.add(firstCard);
        cards.add(secondCard);

        //create new pile and add the 2 cards to it
        try {
            responseFromCreatingNewPile = ApiCalls.createNewPile(deckId, pileName, cards);
        } catch (IOException e) {
            throw new RuntimeException("Can't create new pile !\n " + e);
        }


        //act
        try {
            response = ApiCalls.drawCardsFromDeckById(deckId, responseFromCreatingNewPile.getData().getRemaining());
        } catch (IOException e) {
            throw new RuntimeException("Can't draw cards from the deck !\n " + e);
        }

        System.out.println("validate_Card_Is_In_Deck:");
        System.out.println(response.getData());


        //assert
        assertAll(
                //status validation
                () -> assertEquals(200, response.getStatus(), "Expected status code is 200"),

                //not null validation
                () -> assertThat(response.getData().getDeckId(), notNullValue()),
                () -> assertThat(response.getData().isSuccess(), notNullValue()),
                () -> assertThat(response.getData().getCards(), notNullValue()),


                //data validation
                () -> assertTrue(response.getData().isSuccess()),
                () -> assertThat(response.getData().getCards(), not(hasItem(cards.get(0)))),
                () -> assertThat(response.getData().getCards(), not(hasItem(cards.get(1))))

        );
    }
}
