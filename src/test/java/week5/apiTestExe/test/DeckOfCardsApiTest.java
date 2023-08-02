package week5.apiTestExe.test;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import week5.apiTestExe.infra.ResponseWrapper;
import week5.apiTestExe.logic.DeckOfCardsApi;
import week5.apiTestExe.logic.entities.DTOs.CardDTO;
import week5.apiTestExe.logic.entities.DTOs.DeckDTO;
import week5.apiTestExe.logic.entities.DTOs.PileDTO;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class DeckOfCardsApiTest {
    private final String pileName = "Mostafa";
    private ResponseWrapper<DeckDTO> response;
    private String deckId = null;
    private static final Logger logger = LogManager.getLogger(DeckOfCardsApiTest.class);

    @BeforeEach
    void setUp() {

        response = DeckOfCardsApi.createNewDeck();
        deckId = response.getData().getDeckId();
    }


    @Test
    @Description("Verify creating new deck functionality")
    @Story("user trying to create new deck of cards")
    void create_New_Deck_Validations() {

        logger.info("create_New_Deck_Validations:");
        logger.debug(response.getData());

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

    @ParameterizedTest
    @MethodSource("getNumberOfDecks2")
    @Description("Verify that trying to create more than twenty new decks will fail")
    @Story("user trying to create more than twenty new decks")
    void create_More_Than_Twenty_Decks_Failed(int numberOfDecks) {

        logger.info("create_More_Than_Twenty_Decks_Failed (numberOfDecks = " + numberOfDecks + "):");
        //********** ALWAYS FAIL -  when the numberOfDecks = 24 for some reason **********
        response = DeckOfCardsApi.createNumberOfDecks(numberOfDecks);
        logger.debug(response.getData());

        //assert
        assertAll(
                //status validation
                () -> assertEquals(200, response.getStatus(), "Expected status code is 200"),

                //not null validation
                () -> assertThat(response.getData().isSuccess(), notNullValue()),
                () -> assertThat(response.getData().getError(), notNullValue()),

                //data validation
                () -> assertFalse(response.getData().isSuccess()),
                () -> assertThat(response.getData().getError(), equalTo("The max number of Decks is 20."))
        );
    }

    @ParameterizedTest
    @MethodSource("getNumberOfDecks")
    @Description("Verify creating new shuffled deck functionality")
    @Story("user trying to create new shuffled number of decks")
    void create_New_Shuffled_Number_Of_Decks(int numberOfDecks) {

        logger.info("create_New_Shuffled_Number_Of_Decks (numberOfDecks = " + numberOfDecks + "):");
        response = DeckOfCardsApi.createNumberOfDecks(numberOfDecks);

        logger.debug(response.getData());

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
                () -> assertThat(response.getData().getRemaining(), equalTo(52 * numberOfDecks)),
                () -> assertTrue(response.getData().isShuffled())
        );
    }

    private static Stream<Arguments> getNumberOfDecks() {
        return Stream.of(
                Arguments.of(1),
                Arguments.of(2),
                Arguments.of(3),
                Arguments.of(4),
                Arguments.of(5),
                Arguments.of(6),
                Arguments.of(7),
                Arguments.of(8),
                Arguments.of(9),
                Arguments.of(10),
                Arguments.of(20)
        );
    }

    private static Stream<Arguments> getNumberOfDecks2() {
        return Stream.of(
                Arguments.of(21),
                Arguments.of(22),
                Arguments.of(23),
                Arguments.of(24),
                Arguments.of(25),
                Arguments.of(26),
                Arguments.of(27),
                Arguments.of(28),
                Arguments.of(29),
                Arguments.of(30)
        );
    }

    @Test
    @Description("Verify drawing cards functionality")
    @Story("user trying to draw 2 cards from the deck")
    void draw_Two_Cards_From_Deck_Successfully() {
        logger.info("draw_Two_Cards_From_Deck_Successfully:");
        //act
        response = DeckOfCardsApi.drawCardsFromDeckById(deckId, 2);

        logger.debug(response.getData());


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
    @Description("Verify that trying to draw more cards than in the deck will fail")
    @Story("user trying to draw more cards than in the deck ( x > 52 )")
    void draw_Cards_More_Than_In_The_Deck_Failed() {
        logger.info("draw_Cards_More_Than_In_The_Deck_Failed:");
        //act
        response = DeckOfCardsApi.drawCardsFromDeckById(deckId, 53);

        logger.debug(response.getData());


        //assert
        assertAll(
                //status validation
                () -> assertEquals(200, response.getStatus(), "Expected status code is 200"),

                //not null validation
                () -> assertThat(response.getData().getDeckId(), notNullValue()),
                () -> assertThat(response.getData().getRemaining(), notNullValue()),
                () -> assertThat(response.getData().isSuccess(), notNullValue()),
                () -> assertThat(response.getData().getError(), notNullValue()),


                //data validation
                () -> assertFalse(response.getData().isSuccess()),
                () -> assertThat(response.getData().getRemaining(), equalTo(0)),
                () -> assertEquals("Not enough cards remaining to draw 53 additional", response.getData().getError())
        );
    }

    @Test
    @Description("Verify creating new pile functionality")
    @Story("user trying to create new pile from the drawn cards")
    void create_New_Pile_Successfully() {
        logger.info("create_New_Pile_Successfully:");
        //Arrange
        ResponseWrapper<DeckDTO> responseFromDrawingCards;

        responseFromDrawingCards = DeckOfCardsApi.drawCardsFromDeckById(deckId, 2);
        CardDTO firstCard = responseFromDrawingCards.getData().getCards().get(0);
        CardDTO secondCard = responseFromDrawingCards.getData().getCards().get(1);

        ArrayList<CardDTO> cards = new ArrayList<>();
        cards.add(firstCard);
        cards.add(secondCard);

        //Act
        response = DeckOfCardsApi.createNewPile(deckId, pileName, cards);

        //get the pile name and Pile object from the response
        String pileNameCheck = null;
        PileDTO pileCheck = null;
        for (Map.Entry<String, PileDTO> entry : response.getData().getPiles().entrySet()) {
            if (entry.getKey().equals(pileName)) {
                pileNameCheck = entry.getKey();
                pileCheck = entry.getValue();
            }
        }
        logger.debug(response.getData());


        String finalPileNameCheck = pileNameCheck;
        int finalRemainingCheck = pileCheck.getRemaining();

        assertAll(
                //status validation
                () -> assertEquals(200, response.getStatus(), "Expected status code is 200"),

                //not null validation
                () -> assertThat(response.getData().getDeckId(), notNullValue()),
                () -> assertThat(response.getData().getRemaining(), notNullValue()),
                () -> assertThat(response.getData().isSuccess(), notNullValue()),
                () -> assertThat(response.getData().getPiles(), notNullValue()),

                //Assert pile info
                () -> assertEquals(pileName, finalPileNameCheck),
                () -> assertEquals(cards.size(), finalRemainingCheck),

                //data validation
                () -> assertTrue(response.getData().isSuccess()),
                () -> assertThat(response.getData().getRemaining(), equalTo(50)),
                () -> assertThat(response.getData().getPiles().size(), equalTo(1))
        );

    }

    @Test
    @Description("Verify creating new pile without drawing cards first will fail")
    @Story("user trying to create new pile without drawing cards first")
    void create_New_Pile_Without_Drawing_Cards_First_Failed() {
        String pileNameCheck = null;
        PileDTO pileCheck = null;

        logger.info("create_New_Pile_Without_Drawing_Cards_First_Failed:");
        CardDTO firstCard = new CardDTO();
        CardDTO secondCard = new CardDTO();

        ArrayList<CardDTO> cards = new ArrayList<>();
        cards.add(firstCard);
        cards.add(secondCard);

        //Act
        response = DeckOfCardsApi.createNewPile(deckId, pileName, cards);

        //get the pile name and Pile object from the response

        for (Map.Entry<String, PileDTO> entry : response.getData().getPiles().entrySet()) {
            if (entry.getKey().equals(pileName)) {
                pileNameCheck = entry.getKey();
                pileCheck = entry.getValue();
            }
        }
        logger.debug(response.getData());


        String finalPileNameCheck = pileNameCheck;
        int finalRemainingCheck = pileCheck.getRemaining();
        assertAll(
                //status validation
                () -> assertEquals(200, response.getStatus(), "Expected status code is 200"),

                //not null validation
                () -> assertThat(response.getData().getDeckId(), notNullValue()),
                () -> assertThat(response.getData().getRemaining(), notNullValue()),
                () -> assertThat(response.getData().isSuccess(), notNullValue()),
                () -> assertThat(response.getData().getPiles(), notNullValue()),

                //Assert pile info
                () -> assertEquals(pileName, finalPileNameCheck),
                () -> assertEquals(0, finalRemainingCheck),

                //data validation
                () -> assertTrue(response.getData().isSuccess()),
                () -> assertThat(response.getData().getRemaining(), equalTo(52)),
                () -> assertThat(response.getData().getPiles().size(), equalTo(1))
        );
    }

    @Test
    @Description("Verify that the cards are in the pile")
    @Story("user trying to check that the cards are in the pile")
    void validate_Card_Is_In_Pile() {
        logger.info("validate_Card_Is_In_Pile:");
        //arrange
        ResponseWrapper<DeckDTO> responseFromDrawingCards, responseFromCreatingNewPile;
        //draw 2 cards
        responseFromDrawingCards = DeckOfCardsApi.drawCardsFromDeckById(deckId, 2);
        CardDTO firstCard = responseFromDrawingCards.getData().getCards().get(0);
        CardDTO secondCard = responseFromDrawingCards.getData().getCards().get(1);

        ArrayList<CardDTO> cards = new ArrayList<>();
        cards.add(firstCard);
        cards.add(secondCard);

        //create new pile and add the 2 cards to it
        responseFromCreatingNewPile = DeckOfCardsApi.createNewPile(deckId, pileName, cards);

        //act
        response = DeckOfCardsApi.listOfCardsInPile(deckId, pileName);

        logger.debug(response.getData());


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
    @Description("Verify that the cards are not in the deck")
    @Story("user trying to check that the cards are not in the deck")
    void validate_Card_Is_Not_In_Deck() {
        logger.info("validate_Card_Is_Not_In_Deck:");
        //arrange
        ResponseWrapper<DeckDTO> responseFromDrawingCards, responseFromCreatingNewPile;
        //draw 2 cards
        responseFromDrawingCards = DeckOfCardsApi.drawCardsFromDeckById(deckId, 2);
        CardDTO firstCard = responseFromDrawingCards.getData().getCards().get(0);
        CardDTO secondCard = responseFromDrawingCards.getData().getCards().get(1);

        ArrayList<CardDTO> cards = new ArrayList<>();
        cards.add(firstCard);
        cards.add(secondCard);

        //create new pile and add the 2 cards to it
        responseFromCreatingNewPile = DeckOfCardsApi.createNewPile(deckId, pileName, cards);


        //act
        response = DeckOfCardsApi.drawCardsFromDeckById(deckId, responseFromCreatingNewPile.getData().getRemaining());

        logger.debug(response.getData());


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
