import java.io.Serializable;

public class Person implements Serializable {
    private String name;
    transient private long uid;
    private String about;
    private int birthYear;

    public Person(String name, long uid, String about, int birthYear) {
        this.name = name;
        this.uid = uid;
        this.about = about;
        this.birthYear = birthYear;
    }

    public String getName() {
        return name;
    }

    public long getUid() {
        return uid;
    }

    public String getAbout() {
        return about;
    }

    public int getBirthYear() {
        return birthYear;
    }
}
