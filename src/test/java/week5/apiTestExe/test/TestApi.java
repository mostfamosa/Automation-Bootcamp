package week5.apiTestExe.test;

import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import week5.apiTestExe.infra.ResponseWrapper;
import week5.apiTestExe.logic.ApiCalls;
import week5.apiTestExe.logic.Card;
import week5.apiTestExe.logic.DeckInfo;
import com.google.gson.Gson;
import week5.apiTestExe.logic.Pile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestApi {
    private Gson gson = new Gson();
    private String deck_id = "";
    private final String pileName = "Mostafa";

    private DeckInfo deckInfo;
    private ResponseWrapper res;


    @BeforeEach
    void setUp() {
        try {
            res = ApiCalls.createNewDeck();
        } catch (IOException e) {
            throw new RuntimeException("Can't create new deck " + e);
        }
        deckInfo = gson.fromJson(res.getData().toString(), DeckInfo.class);
        deck_id = deckInfo.getDeck_id();
    }


    @Test
    @Story("user trying to create new deck of cards")
    void create_New_Deck_Validations() {

        System.out.println("create_New_Deck_Validations:");
        System.out.println(deckInfo);

        //assert
        assertAll(
                //status validation
                () -> assertEquals(200, res.getStatus(), "Expected status code is 200"),

                //not null validation
                () -> assertThat(deckInfo.getDeck_id(), notNullValue()),
                () -> assertThat(deckInfo.getRemaining(), notNullValue()),
                () -> assertThat(deckInfo.isShuffled(), notNullValue()),
                () -> assertThat(deckInfo.isSuccess(), notNullValue()),

                //data validation
                () -> assertTrue(deckInfo.isSuccess()),
                () -> assertThat(deckInfo.getRemaining(), equalTo(52)),
                () -> assertFalse(deckInfo.isShuffled())
        );
    }

    @Test
    @Story("user trying to draw 2 cards from the deck")
    void draw_Two_Cards_From_Deck_Successfully() {
        //act
        try {
            res = ApiCalls.drawCardsFromDeckById(deck_id, 2);
        } catch (IOException e) {
            throw new RuntimeException("Can't draw cards!\n " + e);
        }
        DeckInfo deckInfo = gson.fromJson(res.getData().toString(), DeckInfo.class);

        System.out.println("draw_Two_Cards_From_Deck:");
        System.out.println(deckInfo);


        //assert
        assertAll(
                //status validation
                () -> assertEquals(200, res.getStatus(), "Expected status code is 200"),

                //not null validation
                () -> assertThat(deckInfo.getDeck_id(), notNullValue()),
                () -> assertThat(deckInfo.getRemaining(), notNullValue()),
                () -> assertThat(deckInfo.isSuccess(), notNullValue()),
                () -> assertThat(deckInfo.getCards(), notNullValue()),


                //data validation
                () -> assertTrue(deckInfo.isSuccess()),
                () -> assertThat(deckInfo.getRemaining(), equalTo(50)),
                () -> assertEquals(2, deckInfo.getCards().size())
        );
    }

    @Test
    @Story("user trying to create new pile from the drawn cards")
    void create_New_Pile_Successfully() {
        //Arrange
        ResponseWrapper res2;

        try {
            res2 = ApiCalls.drawCardsFromDeckById(deck_id, 2);
        } catch (IOException e) {
            throw new RuntimeException("Can't draw cards!\n " + e);
        }
        DeckInfo deckInfo2 = gson.fromJson(res2.getData().toString(), DeckInfo.class);
        Card firstCard = deckInfo2.getCards().get(0);
        Card secondCard = deckInfo2.getCards().get(1);

        ArrayList<Card> cards = new ArrayList<>();
        cards.add(firstCard);
        cards.add(secondCard);

        //Act
        try {
            res = ApiCalls.createNewPile(deck_id, pileName, cards);
        } catch (IOException e) {
            throw new RuntimeException("Can't create new pile !\n " + e);
        }
        DeckInfo deckInfo = gson.fromJson(res.getData().toString(), DeckInfo.class);

        //get the pile name and Pile object from the response
        String pileNameCheck = null;
        Pile pileCheck = null;
        for (Map.Entry<String, Pile> entry : deckInfo.getPiles().entrySet()) {
            if (entry.getKey().equals(pileName)) {
                pileNameCheck = entry.getKey();
                pileCheck = entry.getValue();
            }
        }
        System.out.println("create_New_Pile:");
        System.out.println(deckInfo);


        //Assert pile info
        assertEquals(pileName, pileNameCheck);
        assertEquals(2, pileCheck.getRemaining());

        assertAll(
                //status validation
                () -> assertEquals(200, res.getStatus(), "Expected status code is 200"),

                //not null validation
                () -> assertThat(deckInfo.getDeck_id(), notNullValue()),
                () -> assertThat(deckInfo.getRemaining(), notNullValue()),
                () -> assertThat(deckInfo.isSuccess(), notNullValue()),
                () -> assertThat(deckInfo.getPiles(), notNullValue()),


                //data validation
                () -> assertTrue(deckInfo.isSuccess()),
                () -> assertThat(deckInfo.getRemaining(), equalTo(50)),
                () -> assertFalse(deckInfo.isShuffled()),
                () -> assertThat(deckInfo.getPiles().size(), equalTo(1))
        );

    }

    @Test
    @Story("validating that the cards are in the pile")
    void validate_Card_Is_In_Pile(){
        //arrange
        ResponseWrapper res3,res2;
            //draw 2 cards
        try {
            res3 = ApiCalls.drawCardsFromDeckById(deck_id, 2);
        } catch (IOException e) {
            throw new RuntimeException("Can't draw cards!\n " + e);
        }
        DeckInfo deckInfo3 = gson.fromJson(res3.getData().toString(), DeckInfo.class);
        Card firstCard = deckInfo3.getCards().get(0);
        Card secondCard = deckInfo3.getCards().get(1);

        ArrayList<Card> cards = new ArrayList<>();
        cards.add(firstCard);
        cards.add(secondCard);

            //create new pile and add the 2 cards to it
        try {
            res2 = ApiCalls.createNewPile(deck_id, pileName, cards);
        } catch (IOException e) {
            throw new RuntimeException("Can't create new pile !\n " + e);
        }
        DeckInfo deckInfo2 = gson.fromJson(res2.getData().toString(), DeckInfo.class);



        //act
        try {
            res = ApiCalls.listOfCardsInPile(deck_id, pileName);
        } catch (IOException e) {
            throw new RuntimeException("Can't show cards in the pile !\n " + e);
        }

        DeckInfo deckInfo = gson.fromJson(res.getData().toString(), DeckInfo.class);
        System.out.println("validate_Card_Is_In_Pile:");
        System.out.println(deckInfo);


        //assert
        assertAll(
                //status validation
                () -> assertEquals(200, res.getStatus(), "Expected status code is 200"),

                //not null validation
                () -> assertThat(deckInfo.getDeck_id(), notNullValue()),
                () -> assertThat(deckInfo.getRemaining(), notNullValue()),
                () -> assertThat(deckInfo.isSuccess(), notNullValue()),
                () -> assertThat(deckInfo.getPiles(), notNullValue()),
                () -> assertThat(deckInfo.getPiles().get(pileName), notNullValue()),
                () -> assertThat(deckInfo.getPiles().get(pileName).getRemaining(), notNullValue()),
                () -> assertThat(deckInfo.getPiles().get(pileName).getCards(), notNullValue()),


                //data validation
                () -> assertTrue(deckInfo.isSuccess()),
                () -> assertThat(deckInfo.getRemaining(), equalTo(50)),
                () -> assertFalse(deckInfo.isShuffled()),
                () -> assertThat(deckInfo.getPiles().size(), equalTo(1)),
                () -> assertThat(deckInfo.getPiles().get(pileName).getRemaining(), equalTo(2)),
                () -> assertThat(deckInfo.getPiles().get(pileName).getCards().size(), equalTo(2)),
                () -> assertThat(deckInfo.getPiles().get(pileName).getCards(), hasItem(cards.get(0))),
                () -> assertThat(deckInfo.getPiles().get(pileName).getCards(), hasItem(cards.get(1)))
        );

    }

    @Test
    @Story("validating that the cards are NOT in the deck")
    void validate_Card_Is_In_Deck(){
        //arrange
        ResponseWrapper res3,res2;
        //draw 2 cards
        try {
            res3 = ApiCalls.drawCardsFromDeckById(deck_id, 2);
        } catch (IOException e) {
            throw new RuntimeException("Can't draw cards!\n " + e);
        }
        DeckInfo deckInfo3 = gson.fromJson(res3.getData().toString(), DeckInfo.class);
        Card firstCard = deckInfo3.getCards().get(0);
        Card secondCard = deckInfo3.getCards().get(1);

        ArrayList<Card> cards = new ArrayList<>();
        cards.add(firstCard);
        cards.add(secondCard);

        //create new pile and add the 2 cards to it
        try {
            res2 = ApiCalls.createNewPile(deck_id, pileName, cards);
        } catch (IOException e) {
            throw new RuntimeException("Can't create new pile !\n " + e);
        }
        DeckInfo deckInfo2 = gson.fromJson(res2.getData().toString(), DeckInfo.class);



        //act
        try {
            res = ApiCalls.drawCardsFromDeckById(deck_id, deckInfo2.getRemaining());
        } catch (IOException e) {
            throw new RuntimeException("Can't draw cards from the deck !\n " + e);
        }

        DeckInfo deckInfo = gson.fromJson(res.getData().toString(), DeckInfo.class);
        System.out.println("validate_Card_Is_In_Deck:");
        System.out.println(deckInfo);


        //assert
        assertAll(
                //status validation
                () -> assertEquals(200, res.getStatus(), "Expected status code is 200"),

                //not null validation
                () -> assertThat(deckInfo.getDeck_id(), notNullValue()),
                () -> assertThat(deckInfo.isSuccess(), notNullValue()),
                () -> assertThat(deckInfo.getCards(), notNullValue()),


                //data validation
                () -> assertTrue(deckInfo.isSuccess()),
                () -> assertThat(deckInfo.getCards(), not(hasItem(cards.get(0)))),
                () -> assertThat(deckInfo.getCards(), not(hasItem(cards.get(1))))

        );

    }

}
