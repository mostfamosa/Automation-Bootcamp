package week5.apiTestExe.logic;

import lombok.Data;

@Data
public class Card {
    private String code;
    private String image;
    private Images images;
    private String value;
    private String suit;
}
