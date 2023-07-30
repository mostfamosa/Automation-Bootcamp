package week4.tzahiGame;


import week4.tzahiGame.DTOs.CardDTO;
import week4.tzahiGame.DTOs.Player;
import week4.tzahiGame.logic.gameApis;

import java.io.IOException;


public class Client {


    public static void main(String[] args) throws IOException {
        Player player1 = new Player("Tzahi");
        Player player2 = new Player("Mostafa");

        player1.setDeckDTO(gameApis.createNewDeck().getData());
        player2.setDeckDTO(gameApis.createNewDeck().getData());


        for (int i = 0; i < 52; i++) {
            CardDTO tzahiCard = gameApis.drawCardsFromDeckById(player1.getDeckDTO().getDeckId(), 1).getData().getCards().get(0);
            CardDTO mostafaCard = gameApis.drawCardsFromDeckById(player2.getDeckDTO().getDeckId(), 1).getData().getCards().get(0);

            gameApis.setActualValue(tzahiCard);
            gameApis.setActualValue(mostafaCard);


            System.out.println("Scores: \n" + player1.getName() + " score =" + player1.getScore());
            System.out.println(player2.getName() + " score =" + player2.getScore());

            System.out.println("Round " + (i + 1) + ":");
            System.out.println(player1.getName() + "'s card is: " + tzahiCard.getCode() + " & " + player2.getName() + "'s card is: " + mostafaCard.getCode());


            if (tzahiCard.getGameValue() > mostafaCard.getGameValue()) {
                player1.setScore(player1.getScore() + 1);
                player1.getWinningCards().add(tzahiCard.getCode());
                player1.getWinningCards().add(mostafaCard.getCode());

                System.out.println(player1.getName() + " Wins The Round :D");
            } else if (tzahiCard.getGameValue() < mostafaCard.getGameValue()) {
                player2.setScore(player2.getScore() + 1);
                player2.getWinningCards().add(tzahiCard.getCode());
                player2.getWinningCards().add(mostafaCard.getCode());
                System.out.println(player2.getName() + " Wins The Round :D");
            } else {
                System.out.println("It's a Tie You Both Got A Point Each :)");
                player1.setScore(player1.getScore() + 1);
                player2.setScore(player2.getScore() + 1);
            }
            System.out.println("**********************************\n");
        }

        if (player1.getScore() > player2.getScore()) {
            System.out.println(player1.getName() + " is The Winner!\nWith A Score = " + player1.getScore());
            System.out.println(player1.getName() + "'s Pile Of Cards = " + player1.getWinningCards());
        } else if (player1.getScore() < player2.getScore()) {
            System.out.println(player2.getName() + " is The Winner!\nWith A Score = " + player2.getScore());
            System.out.println(player2.getName() + "'s Pile Of Cards = " + player2.getWinningCards());
        } else {
            System.out.println(player1.getName() + " & " + player2.getName() + "You Are Both Winners (It's a TIE)\nWith A Score = " + player1.getScore());
            System.out.println(player1.getName() + "'s Pile Of Cards = " + player1.getWinningCards());
            System.out.println(player2.getName() + "'s Pile Of Cards = " + player2.getWinningCards());
        }

    }
}
