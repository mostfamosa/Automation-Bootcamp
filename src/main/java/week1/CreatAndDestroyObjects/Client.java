package week1.CreatAndDestroyObjects;


import java.util.Arrays;
import java.util.List;

public class Client {
    public static void main(String[] args) {


        List<String> nameDictionary1 = Arrays.asList("John", "Michael", "David", "Sarah", "Emma", "James", "Emily", "William", "Olivia", "Sophia", "Zoro");
        List<String> nameDictionary2 = Arrays.asList("Jack", "Steve", "Timon", "Ahmad", "Jad", "Tem", "Jack", "Samer", "Asad", "Marly", "Jimmy", "Naruto", "Lufi", "Ace", "Brad");
//
        Team team1 = Team.createTeamWithFormation(nameDictionary1, 1, 4, 4, 2);
        Team team2 = Team.createTeamWithFormation(nameDictionary2, 1, 4, 3, 3);
//        // Generate players for team1
//        team1.generateRandomPlayer(Position.DEFENDER, 20);
//        team1.generateRandomPlayer(Position.MIDFIELDER, 10);
//        team1.generateRandomPlayer(Position.ATTACKER, 2);
//
//        // Generate players for team2
//        team2.generateRandomPlayer(Position.DEFENDER, 4);
//        team2.generateRandomPlayer(Position.MIDFIELDER, 3);
//        team2.generateRandomPlayer(Position.ATTACKER, 3);

        // Write team1's data to a file
        System.out.println(team1);
        System.out.println(team2);
    }
}
