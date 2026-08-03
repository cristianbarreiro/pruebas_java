package oop2;

public class Test {

    public static void main(String[] args) {
        User u = new User();
        u.setId(12345);
        u.setName("Homer");
        u.setUsername("homie2020");
        u.setPassword("x321p!");
        u.setGuest(false);

        User u2 = new User();
        u2.setId(6789);
        u2.setName("Marge");
        u2.setUsername("aw2X");
        //no password
        u2.setGuest(true);

        User u3 = new User("elbarto", "b4rt!");
        u3.setName("Bart");
        u3.setGuest(true);

        User u4 = new User(43673, "Lisa", "lsimpson", "ls3011", false);

        User u5 = new User("maggie01", "Margaret"); //watch out
        User u6 = new User("Margaret", "maggie01"); //watch out
    }
}
