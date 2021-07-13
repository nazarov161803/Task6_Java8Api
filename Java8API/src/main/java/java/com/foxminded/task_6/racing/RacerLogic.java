package java.com.foxminded.task_6.racing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class RacerLogic {

    private static final String BOUND = "-";
    private static final String PATTERN_DATE = "yyyy-MM-dd_HH:mm:ss.SSS";
    private static final String UNDERLINE_SEPARATOR = "_";
    private static final String COLON = ":";
    private static final String FORMATTER_PLACE = "%-2s.";
    private static final String FORMATTER_NAME = "%-20s|";
    private static final String FORMATTER_TEAM = "%-30s|";
    private static final String FORMATTER_TIME = "%-8s";
    private static final String FORMATTER_MILLISECOND = "%03d";


    public List<String> takeResultToList(String start, String end, String abbreviation) {


        List<Abbreviation> abbreviations = getListAbbreviation(Reader.readFile(abbreviation));
        List<TimeData> timeStart = getListRaceTime(Reader.readFile(start));
        List<TimeData> timeEnd = getListRaceTime(Reader.readFile(end));

        return mergeLists(timeStart, timeEnd, abbreviations);

    }


    private List<Abbreviation> getListAbbreviation(List<String> abbreviations) {

        return abbreviations.stream().
                peek(RacerLogic::checkAbbreviation).
                map(value -> value.split(UNDERLINE_SEPARATOR)).
                map(value -> new Abbreviation(value[0], value[1], value[2])).
                collect(Collectors.toList());
    }

    private List<TimeData> getListRaceTime(List<String> time) {

        return time.stream().
                peek(RacerLogic::checkTimeLog).
                map(value -> new TimeData(value.substring(0, 3), value.substring(3))).
                collect(Collectors.toList());
    }

    private List<String> mergeLists(List<TimeData> start, List<TimeData> end, List<Abbreviation> abbreviations) {

        AtomicInteger runCount = new AtomicInteger(0);

        return abbreviations.stream().
                map(racer -> new RacerData(racer.getAbbreviation(), racer.getName(), racer.getTeam())).
                peek(racerData -> racerData.setStartTime(findTimeInList(start, racerData))).
                peek(racerData -> racerData.setFinishTime(findTimeInList(end, racerData))).
                peek(racerData -> racerData.setLabDuration(calculateDuration(racerData))).
                sorted(RacerData::compareTo).
                map(racerResult -> (formatString(runCount.getAndIncrement(), racerResult))).
                collect(Collectors.toList());



    }

    private String findTimeInList(List<TimeData> logs, RacerData racerData) {
        return logs.stream().filter(racer -> racer.getAbbreviation().
                equals(racerData.getAbbreviation())).
                findAny().orElseThrow().getTime();
    }



    private String formatString(int racerIndex, RacerData racer) {

        String millisecondWithLeadZero = String.format(FORMATTER_MILLISECOND, racer.getLabDuration() % 1000);
        int sec = (int) ((racer.getLabDuration() / 1000) % 60);
        int min = (int) ((racer.getLabDuration() / 1000) / 60);
        String timeResult = (min + COLON + sec + COLON + (millisecondWithLeadZero));

        String place = String.format(FORMATTER_PLACE, racerIndex + 1);
        String name = String.format(FORMATTER_NAME, racer.getName());
        String team = String.format(FORMATTER_TEAM, racer.getTeam());
        String time = String.format(FORMATTER_TIME, timeResult);

        String result = place + name + team + time;

        int umberOfCharsLine = place.length() + name.length() + team.length() + time.length();

        String divider = BOUND.repeat(umberOfCharsLine);
        if (racerIndex == 14) {
            result += '\n' + divider;
        }

        return result;
    }

    private long calculateDuration(RacerData racerData) {

        String start = racerData.getStartTime();
        String end = racerData.getFinishTime();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_DATE);
        LocalDateTime formatDateTimeStart = LocalDateTime.parse(start, formatter);
        LocalDateTime formatDateTimeEnd = LocalDateTime.parse(end, formatter);
        return Duration.between(formatDateTimeStart, formatDateTimeEnd).toMillis();

    }

    private static void checkAbbreviation(String abbreviation) {
        Pattern pattern = Pattern.compile("^[a-zA-Z]{3}_[a-zA-Z\\s]*_[A-Z\\s]*$");
        Matcher matcher = pattern.matcher(abbreviation);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Invalid format file");

        }
    }

    private static void checkTimeLog(String log) {
        Pattern pattern = Pattern.compile("^[A-Z]{3}(\\d{4})-(\\d{2})-(\\d{2})_(\\d{2}):(\\d{2}):(\\d{2}).(\\d{3})$");
        Matcher matcher = pattern.matcher(log);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Invalid format file");
        }
    }
}
