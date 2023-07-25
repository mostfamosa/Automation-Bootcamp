package week5.apiTestExe.logic;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Pile {
    private int remaining;
    private ArrayList<Card> cards;
}
