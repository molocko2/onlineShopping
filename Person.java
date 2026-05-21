import java.util.Date;
import java.util.Objects;

public abstract class Person {
    private String Name = "";
    private String Surname = "";
    private int BornTimestamp = 0;
    private final int id = Main.IDUtility.getID(this);

    public String getName() {
        return Name;
    }

    public String getSurname() {
        return Surname;
    }

    public int getBorn() {
        return BornTimestamp;
    }

    @Override
    public String toString() {
        return Name + " " + Surname;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Name, Surname, BornTimestamp, id);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return false;
        if ((other == null) || !(other instanceof Person)) return false;
        Person pr = (Person)other;
        return pr.id == id;
    }

    public Person(String n, String s, int b) {
        Name = n;
        Surname = s;
        BornTimestamp = b;
    }
}
