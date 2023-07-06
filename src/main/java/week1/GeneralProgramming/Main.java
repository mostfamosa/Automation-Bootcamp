package week1.GeneralProgramming;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int playersNumber = 11;
        Team team = new Team(playersNumber);
        ArrayList<Player> myTeam = team.getTeam();
        System.out.println(myTeam);
    }
}
