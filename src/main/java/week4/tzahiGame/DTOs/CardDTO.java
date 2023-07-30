package week4.tzahiGame.DTOs;

import lombok.Data;

@Data
public class CardDTO {
    private String code;
    private String image;
    private ImagesDTO images;
    private String value;
    private String suit;
    private int gameValue;
}
