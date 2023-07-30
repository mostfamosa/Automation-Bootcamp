package week4.tzahiGame.DTOs;

import lombok.Data;

import java.util.ArrayList;

@Data
public class PileDTO {
    private int remaining;
    private ArrayList<CardDTO> cards;
}
