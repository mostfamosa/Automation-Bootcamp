package week1.GeneralProgramming;

import Automation.src.week1.Generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Team {

    private static ArrayList<Integer> jerseyNumbers;
    private static int numberOfPlayers;

    public Team(int numberPlayers) {
        if (numberPlayers != 11)
            throw new IllegalArgumentException();
        else {
            this.numberOfPlayers = numberPlayers;
            jerseyNumbers = Generator.generateRandomUniqueNumbers(100, numberOfPlayers);
        }
    }

    public ArrayList<Integer> getJerseyNumbers() {
        return jerseyNumbers;
    }

    public ArrayList<Player> getTeam() {


        //unique random real names for team players
        ArrayList<String> realNames = Automation.src.week1.Generator.generateRandomRealNames(numberOfPlayers);

        //unique Jersey number
        Team myTeam = new Team(numberOfPlayers);
        ArrayList<Integer> jerseyNumbers = myTeam.getJerseyNumbers();

        ArrayList<Player> myNewTeam = new ArrayList<Player>();

        for (int i = 0; i < numberOfPlayers; i++) {
            Player pl = new Player(jerseyNumbers.get(i), realNames.get(i), Generator.generateRandomGrade());
            myNewTeam.add(pl);
        }

        //positions
        int lowLimit = 2;
        int highLimit = 6;
        Random rd = new Random();
        ArrayList<Integer> positionCount = new ArrayList<Integer>();

        positionCount.add(1);//only one goal keeper
        int secondPick = rd.nextInt(lowLimit + highLimit / 2) + lowLimit;
        positionCount.add(secondPick);

        highLimit = numberOfPlayers - 1 - secondPick;
        int thirdPick = rd.nextInt(((lowLimit + highLimit) / 2) - lowLimit) + lowLimit;
        positionCount.add(thirdPick);

        positionCount.add(numberOfPlayers - secondPick - thirdPick - 1);
        Collections.shuffle(positionCount);

        ArrayList<String> positionNames = new ArrayList<>();
        positionNames.add("Defender");
        positionNames.add("Midfielder");
        positionNames.add("Attacker");
        int playerIndexs = 0;
        String positionName = positionNames.remove(0);
        for (Integer pos : positionCount) {
            for (int i = 0; i < pos; i++) {
                if (pos == 1) {
                    myNewTeam.get(playerIndexs).setPosition("Goal Keeper");
                    playerIndexs++;
                } else {
                    myNewTeam.get(playerIndexs).setPosition(positionName);
                    playerIndexs++;
                }
            }
            //don't change the position if pos is 1 (goalkeeper not included in positionNames)
            if (!positionNames.isEmpty() && pos != 1)
                positionName = positionNames.remove(0);
        }

        return myNewTeam;
    }
}
