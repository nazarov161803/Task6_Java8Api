package com.foxminded.task_6;


import java.com.foxminded.task_6.racing.RacerLogic;
import java.util.List;

public class Main {

    private static final String START_RACE_LOG = "start.log";
    private static final String END_RACE_LOG = "end.log";
    private static final String ABBREVIATIONS = "abbreviations.txt";


    public static void main(String[] args) {


        RacerLogic racerLogic = new RacerLogic();

        List<String> result = racerLogic.takeResultToList(START_RACE_LOG, END_RACE_LOG, ABBREVIATIONS);
        result.forEach(System.out::println);

    }
}