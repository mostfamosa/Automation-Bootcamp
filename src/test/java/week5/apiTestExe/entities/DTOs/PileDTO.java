package week5.apiTestExe.entities.DTOs;

import lombok.Data;

import java.util.ArrayList;

@Data
public class PileDTO {
    private int remaining;
    private ArrayList<CardDTO> cards;
}
