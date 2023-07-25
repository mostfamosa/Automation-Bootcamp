package week1.CreatAndDestroyObjects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayerTest {
    private static Player player;

    @BeforeEach
    void setUp() {
        player = Player.newPlayer("John", 50, Position.GOALKEEPER, 1);
    }

    @Test
    void setName_To_Null_Throw_Exception(){
        assertThrows(IllegalArgumentException.class,()-> player.setName(null));
    }

    @Test
    void make_GoalKeeper(){
        assertEquals(Player.createGoalKeeper("a",1,2).getPosition(),Position.GOALKEEPER);
    }

    @Test
    void make_Attacker(){
        assertEquals(Player.createAttacker("a",1,2).getPosition(),Position.ATTACKER);
    }

    @Test
    void make_Midfielder(){
        assertEquals(Player.createMidfielder("a",1,2).getPosition(),Position.MIDFIELDER);
    }

    @Test
    void make_Defender(){
        assertEquals(Player.createDefender("a",1,2).getPosition(),Position.DEFENDER);
    }

    @Test
    void player_Name_Is_Not_Null() {
        assertThat(player.getName(), notNullValue());
    }


    @Test
    void grade_Above_hundred_Throw_Exception() {
        assertThrows(IllegalArgumentException.class, () -> player.setGrade(101));
    }

    @Test
    void grade_Below_Zero_Throw_Exception() {
        assertThrows(IllegalArgumentException.class, () -> player.setGrade(-2));
    }

    @Test
    void grade_Is_Above_Zero() {
        assertThat(player.getGrade(), greaterThanOrEqualTo(0));
    }

    @Test
    void grade_Is_Below_Hundred() {
        assertThat(player.getGrade(), lessThanOrEqualTo(100));
    }

    @Test
    void set_Position_To_Attacker() {
        player.setPosition(Position.ATTACKER);
        assertThat(player.getPosition(), equalTo(Position.ATTACKER));
    }

    @Test
    void set_Position_To_Defender() {
        player.setPosition(Position.DEFENDER);
        assertThat(player.getPosition(), equalTo(Position.DEFENDER));
    }

    @Test
    void set_Position_To_Midfielder() {
        player.setPosition(Position.MIDFIELDER);
        assertThat(player.getPosition(), equalTo(Position.MIDFIELDER));
    }

    @Test
    void set_Position_To_GoalKeeper() {
        player.setPosition(Position.GOALKEEPER);
        assertThat(player.getPosition(), equalTo(Position.GOALKEEPER));
    }

    @Test
    void setJersey_To_Negative_Number_Throws_Exception(){
        assertThrows(IllegalArgumentException.class,() -> player.setmNumber(player.getNumber()*-1));
    }

}
