package java.com.foxminded.task_6.racing;

public class TimeData {
    private final String abbreviation;
    private final String time;

    public TimeData(String abbreviation, String time) {
        this.abbreviation = abbreviation;
        this.time = time;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getTime() {
        return time;
    }



    @Override
    public String toString() {
        return
                "abbreviation='" + abbreviation + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
