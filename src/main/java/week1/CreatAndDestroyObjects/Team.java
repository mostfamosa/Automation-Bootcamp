package week1.CreatAndDestroyObjects;

import week1.Generator;

import java.util.ArrayList;

public class Team {

    private String name;
    private ArrayList<Player> teamPlayers = null;
    private ArrayList<Integer> jerseyNumbers;
    private int numberOfPlayers;

    public Team(int numberPlayers, String name) {
        this.numberOfPlayers = numberPlayers;
        this.name = name;
        jerseyNumbers = Generator.generateRandomUniqueNumbers(100, numberOfPlayers);
    }

    public Team() {

    }

    public ArrayList<Player> createTeamFormation(String[] namesDictionary,int numberOfGK, int numberOfDefenders, int numberOfAttackers, int numberOfMidfielders) {

        validateFormation(numberOfGK, numberOfDefenders, numberOfAttackers, numberOfMidfielders);

        NameGenerator nameGenerator = new NameGenerator(namesDictionary);

        ArrayList<String> newNames = nameGenerator.generateRandomRealNames(11);

        int cnt = 0;
        ArrayList<Player> newTeamPlayers = new ArrayList<>();
        for (int i = 0; i < numberOfGK; i++) {
            newTeamPlayers.add(Player.createGkPlayer(Generator.generateRandomGrade(), jerseyNumbers.get(cnt), newNames.get(cnt)));
            cnt++;
        }
        for (int i = 0; i < numberOfDefenders; i++) {
            newTeamPlayers.add(Player.createDefenderPlayer(Generator.generateRandomGrade(), jerseyNumbers.get(cnt), newNames.get(cnt)));
            cnt++;
        }
        for (int i = 0; i < numberOfAttackers; i++) {
            newTeamPlayers.add(Player.createAttackerPlayer(Generator.generateRandomGrade(), jerseyNumbers.get(cnt), newNames.get(cnt)));
            cnt++;
        }
        for (int i = 0; i < numberOfMidfielders; i++) {
            newTeamPlayers.add(Player.createMidfielderPlayer(Generator.generateRandomGrade(), jerseyNumbers.get(cnt), newNames.get(cnt)));
            cnt++;
        }
        setTeamPlayers(newTeamPlayers);
        return newTeamPlayers;
    }

    private static void validateFormation(int numberOfGK, int numberOfDefenders, int numberOfAttackers, int numberOfMidfielders) {
        if (numberOfAttackers + numberOfDefenders + numberOfGK + numberOfMidfielders != 11) {
            throw new IllegalArgumentException("Team cannot contain more than 11 players");
        }
        if (numberOfGK != 1) {
            throw new IllegalArgumentException("At team must have exactly one goal keeper");
        }
    }

    public ArrayList<Integer> getJerseyNumbers() {
        return jerseyNumbers;
    }

    public ArrayList<Player> getTeamPlayers() {
        return teamPlayers;
    }

    public void setTeamPlayers(ArrayList<Player> newTeamPlayers) {
        this.teamPlayers = newTeamPlayers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Team{" +
                "Name=" + name +
                ", Number Of Players =" + numberOfPlayers +
                '\n' + this.teamPlayers;
    }

}
