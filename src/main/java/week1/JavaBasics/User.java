package week1.JavaBasics;

import week1.Generator;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String Name;
    private int id;
    private boolean isActive;

    public User(String name, int id, boolean isActive) {
        Name = name;
        this.id = id;
        this.isActive = isActive;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getName() {
        return Name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "Name='" + Name + '\'' +
                ", id=" + id +
                ", isActive=" + isActive +
                '}';
    }

    public boolean isActive() {
        return isActive;
    }


    public static Map<Integer, User> getRandomUsers(int count) {
        Map<Integer, User> result = new HashMap<>();

        for (int i = 0; i < count; i++) {
            User user = new User(Generator.generateRandomName(5), Generator.generateRandomId(), Generator.generateRandomBoolean());
            result.put(user.getId(), user);
        }
        return result;
    }

    public static User getUserById(Map<Integer, User> users,int id){
        for (Integer i : users.keySet()) {
            if(users.get(i).getId() == id){
                System.out.println("User by id :"+users.get(i).toString());
                return users.get(i);
            }
        }
        System.out.println("User not found!");
        return null;
    }
    public static int activatedUsers(Map<Integer, User> users){
        int cnt=0;
        for (Integer i : users.keySet()) {
            if(users.get(i).isActive())
                cnt++;
        }
        return cnt;
    }
    public static void showUsers(Map<Integer, User> users) {
        for (Integer id : users.keySet()) {
            System.out.println(users.get(id).toString());
        }
    }
}
