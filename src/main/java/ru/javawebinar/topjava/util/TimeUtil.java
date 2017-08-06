package ru.javawebinar.topjava.util;

import java.time.LocalTime;

/**
 * GKislin
 * 07.01.2015.
 */
public class TimeUtil {
    public static boolean isBetween(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        if(startTime == null) startTime = LocalTime.MIN;
        if (endTime == null) endTime = LocalTime.MAX;
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
    }
}
