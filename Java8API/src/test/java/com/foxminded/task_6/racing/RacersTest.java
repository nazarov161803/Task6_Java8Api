package java.com.foxminded.task_6.racing;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class RacersTest {

    RacerLogic racerLogic = new RacerLogic();

    String expectedResultAllRacers = "1 .Sebastian Vettel    |FERRARI                       |1:4:415 \n" +
            "2 .Daniel Ricciardo    |RED BULL RACING TAG HEUER     |1:12:013\n" +
            "3 .Valtteri Bottas     |MERCEDES                      |1:12:434\n" +
            "4 .Lewis Hamilton      |MERCEDES                      |1:12:460\n" +
            "5 .Stoffel Vandoorne   |MCLAREN RENAULT               |1:12:463\n" +
            "6 .Kimi Raikkonen      |FERRARI                       |1:12:639\n" +
            "7 .Fernando Alonso     |MCLAREN RENAULT               |1:12:657\n" +
            "8 .Sergey Sirotkin     |WILLIAMS MERCEDES             |1:12:706\n" +
            "9 .Charles Leclerc     |SAUBER FERRARI                |1:12:829\n" +
            "10.Sergio Perez        |FORCE INDIA MERCEDES          |1:12:848\n" +
            "11.Romain Grosjean     |HAAS FERRARI                  |1:12:930\n" +
            "12.Pierre Gasly        |SCUDERIA TORO ROSSO HONDA     |1:12:941\n" +
            "13.Carlos Sainz        |RENAULT                       |1:12:950\n" +
            "14.Esteban Ocon        |FORCE INDIA MERCEDES          |1:13:028\n" +
            "15.Nico Hulkenberg     |RENAULT                       |1:13:065\n" +
            "---------------------------------------------------------------\n" +
            "16.Brendon Hartley     |SCUDERIA TORO ROSSO HONDA     |1:13:179\n" +
            "17.Marcus Ericsson     |SAUBER FERRARI                |1:13:265\n" +
            "18.Lance Stroll        |WILLIAMS MERCEDES             |1:13:323\n" +
            "19.Kevin Magnussen     |HAAS FERRARI                  |1:13:393\n";

    String expectedResultOnly15Racers = "1 .Sebastian Vettel    |FERRARI                       |1:4:415 \n" +
            "2 .Daniel Ricciardo    |RED BULL RACING TAG HEUER     |1:12:013\n" +
            "3 .Valtteri Bottas     |MERCEDES                      |1:12:434\n" +
            "4 .Lewis Hamilton      |MERCEDES                      |1:12:460\n" +
            "5 .Stoffel Vandoorne   |MCLAREN RENAULT               |1:12:463\n" +
            "6 .Kimi Raikkonen      |FERRARI                       |1:12:639\n" +
            "7 .Fernando Alonso     |MCLAREN RENAULT               |1:12:657\n" +
            "8 .Sergey Sirotkin     |WILLIAMS MERCEDES             |1:12:706\n" +
            "9 .Charles Leclerc     |SAUBER FERRARI                |1:12:829\n" +
            "10.Sergio Perez        |FORCE INDIA MERCEDES          |1:12:848\n" +
            "11.Romain Grosjean     |HAAS FERRARI                  |1:12:930\n" +
            "12.Pierre Gasly        |SCUDERIA TORO ROSSO HONDA     |1:12:941\n" +
            "13.Carlos Sainz        |RENAULT                       |1:12:950\n" +
            "14.Esteban Ocon        |FORCE INDIA MERCEDES          |1:13:028\n" +
            "15.Nico Hulkenberg     |RENAULT                       |1:13:065\n" +
            "---------------------------------------------------------------\n";

    @Test
    void shouldThrowExceptionWhenFileIsNotExist() {
        String file = "nonexistentfile.txt";
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                Reader.readFile(file));
    }

    @Test
    void shouldThrowExceptionWhenFileIsEmpty() {
        String file = "empty.txt";
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                Reader.readFile(file));
    }

    @Test
    void shouldThrowExceptionWhenFileIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                Reader.readFile(null));
    }

    @Test
    void shouldThrowExceptionWhenAbbreviationsWrongFormat() {
        String abbreviations = "wrongFormatAbbreviations.txt";
        String start = "start.log";
        String end = "end.log";
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                racerLogic.takeResultToList(start, end, abbreviations));
    }

    @Test
    void shouldThrowExceptionWhenStartLogsWrongFormat() {
        String abbreviations = "abbreviations.txt";
        String start = "wrongStart.log";
        String end = "end.log";
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                racerLogic.takeResultToList(start, end, abbreviations));
    }

    @Test
    void shouldThrowExceptionWhenEndLogsWrongFormat() {
        String abbreviations = "abbreviations.txt";
        String start = "start.log";
        String end = "wrongEnd.log";
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                racerLogic.takeResultToList(start, end, abbreviations));
    }


    @Test
    void shouldReturnListWithOnly15Racers() {

        String abbreviations = "resources/abbreviations15.txt";
        String start = "start15.log";
        String end = "end15.log";
        List<String> listResultRace =  racerLogic.takeResultToList(start, end, abbreviations);

        StringBuilder stringBuilder = new StringBuilder();
        for (String racers : listResultRace) {
            stringBuilder.append(racers).append('\n');
        }
        String result = stringBuilder.toString();

        assertEquals(expectedResultOnly15Racers, result);
    }


    @Test
    void shouldReturnCorrectListOfAllRacers  () {

        String abbreviations = "abbreviations.txt";
        String start = "start.log";
        String end = "end.log";
        List<String> listResultRace =  racerLogic.takeResultToList(start, end, abbreviations);

        StringBuilder stringBuilder = new StringBuilder();
        for (String racers : listResultRace) {
            stringBuilder.append(racers).append('\n');
        }
        String result = stringBuilder.toString();
        assertEquals(expectedResultAllRacers, result);
    }
}

