package week1.CreatAndDestroyObjects;



public class Client {
    public static void main(String[] args){

        String[] dic1 = {"Shai", "Mostafa", "khader", "Assaf", "Sagi", "Lion", "Lior", "Jamal", "Moshe", "David", "Michel", "Joey", "Barney", "Ted", "James", "Samer", "Zeev"};
        int playersNumber = 11;
        Team myTeam = new Team(playersNumber,"barca");
        myTeam.createTeamFormation(dic1,1,3,3,4);

        Utils.writeToFile("the-team-data",myTeam.toString());
        System.out.println(myTeam);
    }
}
