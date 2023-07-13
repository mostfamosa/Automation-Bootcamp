package week1.CreatAndDestroyObjects;

public class Player {

    private String mName;
    private int mGrade;
    private Position mPos;
    private int mNumber;

    /**
     * Constructor to create player object
     *
     * @param aName
     * @param aGrade
     * @param position
     * @param aNumber
     */
    private Player(String aName, int aGrade, Position position, int aNumber) {
        this.mName = aName;
        this.mGrade = aGrade;
        this.mPos = position;
        this.mNumber = aNumber;
    }

    public static Player newPlayer(String aName, int aGrade, Position position, int aNumber) {
        return new Player(aName, aGrade, position, aNumber);
    }

    /**
     * Add static factory methods to the Player class to enable creating different type of players.
     *
     * @param name
     * @param grade
     * @param number
     * @return
     */
    public static Player createGoalKeeper(String name, int grade, int number) {
        return new Player(name, grade, Position.GOALKEEPER, number);
    }

    public static Player createDefender(String name, int grade, int number) {
        return new Player(name, grade, Position.DEFENDER, number);
    }

    public static Player createMidfielder(String name, int grade, int number) {
        return new Player(name, grade, Position.MIDFIELDER, number);
    }

    public static Player createAttacker(String name, int grade, int number) {
        return new Player(name, grade, Position.ATTACKER, number);
    }

    /**
     * getters and setters
     */
    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        if (mName == null)
            throw new IllegalArgumentException("The Name Cannot Be Null!");
        this.mName = mName;
    }

    public int getGrade() {
        return mGrade;
    }

    public void setGrade(int mGrade) {
        if (mGrade > 100 || mGrade < 0)
            throw new IllegalArgumentException("The Grade Must Be Between 0-100");
        this.mGrade = mGrade;
    }

    public Position getPosition() {
        return mPos;
    }

    public void setPosition(Position mPos) {
        this.mPos = mPos;
    }

    public int getNumber() {
        return mNumber;
    }

    public void setmNumber(int mNumber) {
        if (mNumber < 0)
            throw new IllegalArgumentException("Jersey Number Can't Be Negative!");
        this.mNumber = mNumber;
    }
}