package java.com.foxminded.task_6.racing;

public class Abbreviation {
    private String abbreviation;
    private String name;
    private String team;

    public Abbreviation(String abbreviation, String name, String team) {
        this.abbreviation = abbreviation;
        this.name = name;
        this.team = team;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "abbreviation = " + abbreviation + "; name  = " + name + "; team  = " + team;
    }
}