package week1.CreatAndDestroyObjects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.Stream;

public class TeamTest {
    private static Team team;

    @BeforeEach
    void setUp() {
        List<String> nameDictionary1 = Arrays.asList("John", "Michael", "David", "Sarah", "Emma", "James", "Emily", "William", "Olivia", "Sophia", "Zoro");
        team = Team.createTeamWithFormation(nameDictionary1, 1, 2, 4, 4);
    }

    @Test
    void team_playersNumber_ThrowException() {
        List<String> nameDictionary2 = Arrays.asList("Jack", "Steve", "Timon", "Ahmad", "Jad", "Tem", "Jack", "Samer", "Asad", "Marly", "Jimmy", "Naruto", "Lufi", "Ace", "Brad");
        assertThrows(IllegalArgumentException.class, () -> Team.createTeamWithFormation(nameDictionary2, 2, 2, 4, 4));
    }

    @Test
    void team_Players_Number_Equal_Eleven() {
        int numberOfPlayers = team.getmTeam().size();
        assertEquals(11, numberOfPlayers);
    }

    @Test
    void team_GoalKeeper_OnlyOne() {
        int numberOfGoalKeepers = (int) team.getmTeam().stream().filter(player -> player.getPosition().equals(Position.GOALKEEPER)).count();
        assertEquals(1, numberOfGoalKeepers, "The Number of GoalKeepers must be 1");
    }

    @Test
    void team_Defenders_Greater_Or_Equal_Than_Two() {
        int numberOfDefenders = (int) team.getmTeam().stream().filter(player -> player.getPosition().equals(Position.DEFENDER)).count();
        assertThat(numberOfDefenders, greaterThanOrEqualTo(2));
    }

    @Test
    void team_MidFielder_Greater_Or_Equal_Than_Two() {
        int numberOfMidfielder = (int) team.getmTeam().stream().filter(player -> player.getPosition().equals(Position.MIDFIELDER)).count();
        assertThat(numberOfMidfielder, greaterThanOrEqualTo(2));
    }

    @Test
    void team_Attacker_Greater_Or_Equal_Than_Two() {
        int numberOfAttacker = (int) team.getmTeam().stream().filter(player -> player.getPosition().equals(Position.ATTACKER)).count();
        assertThat(numberOfAttacker, greaterThanOrEqualTo(2));
    }

    @Test
    void write_To_File_Team_Data_Throws_NullPointerException() {
        team.setmDataFile(null);
        assertThrows(NullPointerException.class, () -> team.toString());
    }

    @Test
    void jersey_Numbers_Are_Unique() {
        Set<Integer> mySet = new HashSet<>(team.getmJerseys());
        assertEquals(team.getmJerseys().size(), mySet.size());
    }

    @ParameterizedTest
    @MethodSource("getGrades")
    void grade_Lower_Than_Hundred(int grade) {
        assertThat(grade, lessThanOrEqualTo(100));
    }

    @ParameterizedTest
    @MethodSource("getGrades")
    void grade_Higher_Than_Zero(int grade) {
        assertThat(grade, greaterThanOrEqualTo(0));
    }

    private static Stream<Arguments> getGrades() {
        return Stream.of(
                Arguments.of(team.getmGrades().get(0)),
                Arguments.of(team.getmGrades().get(1)),
                Arguments.of(team.getmGrades().get(2)),
                Arguments.of(team.getmGrades().get(4)),
                Arguments.of(team.getmGrades().get(5)),
                Arguments.of(team.getmGrades().get(6)),
                Arguments.of(team.getmGrades().get(3)),
                Arguments.of(team.getmGrades().get(7)),
                Arguments.of(team.getmGrades().get(8)),
                Arguments.of(team.getmGrades().get(9)),
                Arguments.of(team.getmGrades().get(10))
        );
    }

    @Test
    void create_Team_Without_NameGenerator_Throws_Exception() {
        assertThrows(NullPointerException.class, () -> new Team(Position.DEFENDER, 4, Position.GOALKEEPER, 1, Position.ATTACKER, 2, Position.MIDFIELDER, 2, 2));
    }

    @Test
    void create_Team_Formation() {
        int numberOfGoalKeeper = 1, numberOfDefenders = 2, numberOfMidfielders = 3, numberofAttackers = 4;
        int numberOfRandomPlayers = 1;
        List<String> nameDictionary2 = Arrays.asList("Jack", "Steve", "Timon", "Ahmad", "Jad", "Tem", "Jack", "Samer", "Asad", "Marly", "Jimmy", "Naruto", "Lufi", "Ace", "Brad");
        team = Team.createTeamWithFormation(nameDictionary2, numberOfGoalKeeper, numberOfDefenders, numberOfMidfielders, numberofAttackers, numberOfRandomPlayers);
        int checkGoalKeeper = (int) team.getmTeam().stream().filter(player -> player.getPosition().equals(Position.GOALKEEPER)).count();
        int checkDefenders = (int) team.getmTeam().stream().filter(player -> player.getPosition().equals(Position.DEFENDER)).count();
        int checkMidfielders = (int) team.getmTeam().stream().filter(player -> player.getPosition().equals(Position.MIDFIELDER)).count();
        int checkAttackers = (int) team.getmTeam().stream().filter(player -> player.getPosition().equals(Position.ATTACKER)).count();

        assertTrue(numberOfGoalKeeper == checkGoalKeeper && numberOfDefenders <= checkDefenders && numberOfMidfielders <= checkMidfielders && numberofAttackers <= checkAttackers);

    }

}
