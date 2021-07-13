package java.com.foxminded.task_6.racing;

public class RacerData implements Comparable<RacerData> {
    String abbreviation;
    String name;
    String team;
    String startTime;
    String finishTime;
    long labDuration;

    public RacerData(String abbreviation, String name, String team) {
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public long getLabDuration() {
        return labDuration;
    }

    public void setLabDuration(long labDuration) {
        this.labDuration = labDuration;
    }


    @Override
    public String toString() {
        return "RacerData{" +
                "abbreviation='" + abbreviation + '\'' +
                ", name='" + name + '\'' +
                ", team='" + team + '\'' +
                ", startTime='" + startTime + '\'' +
                ", finishTime='" + finishTime + '\'' +
                ", labDuration=" + labDuration +
                '}';
    }

    @Override
    public int compareTo(RacerData o) {
        return (int) (labDuration - o.getLabDuration());
    }
}