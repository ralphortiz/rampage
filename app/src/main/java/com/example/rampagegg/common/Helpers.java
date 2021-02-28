package com.example.rampagegg.common;

import android.content.Context;

import com.example.rampagegg.R;

import java.util.concurrent.TimeUnit;

public class Helpers {

    public static String getMatchTimeElapsed(Context context, long endTime) {
        String matchTimeElapsed = null;
        long endTimeInMillis = TimeUnit.SECONDS.toMillis(endTime);
        long timeElapsed = System.currentTimeMillis() - endTimeInMillis;
        long years = TimeUnit.MILLISECONDS.toDays(timeElapsed) / 365;
        long months = TimeUnit.MILLISECONDS.toDays(timeElapsed) / 30;
        long days = TimeUnit.MILLISECONDS.toDays(timeElapsed);
        long hours = TimeUnit.MILLISECONDS.toHours(timeElapsed);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(timeElapsed);

        if (years > 0) {
            matchTimeElapsed = context.getResources().getQuantityString(R.plurals.year, (int) years, years);
        } else if (months > 0) {
            matchTimeElapsed = context.getResources().getQuantityString(R.plurals.month, (int) months, months);
        } else if (days > 0) {
            matchTimeElapsed = context.getResources().getQuantityString(R.plurals.day, (int) days, days);
        } else if (hours > 0) {
            matchTimeElapsed = context.getResources().getQuantityString(R.plurals.hour, (int) hours, hours);
        } else if (minutes > 0) {
            matchTimeElapsed = context.getResources().getQuantityString(R.plurals.minute, (int) minutes, minutes);
        }
        return matchTimeElapsed;
    }

//    public static String getLastPlayed(Context context, long lastPlayed) {
//        String matchTimeElapsed = null;
//        long lastPlayedInMillis = TimeUnit.SECONDS.toMillis(lastPlayed);
//        long timeElapsed = System.currentTimeMillis() - lastPlayedInMillis;
//        long years = TimeUnit.MILLISECONDS.toDays(timeElapsed) / 365;
//        long months = TimeUnit.MILLISECONDS.toDays(timeElapsed) / 30;
//        long days = TimeUnit.MILLISECONDS.toDays(timeElapsed);
//        long hours = TimeUnit.MILLISECONDS.toHours(timeElapsed);
//        long minutes = TimeUnit.MILLISECONDS.toMinutes(timeElapsed);
//
//        if (years > 0) {
//            matchTimeElapsed = context.getResources().getQuantityString(R.plurals.year, (int) years, years);
//        } else if (months > 0) {
//            matchTimeElapsed = context.getResources().getQuantityString(R.plurals.month, (int) months, months);
//        } else if (days > 0) {
//            matchTimeElapsed = context.getResources().getQuantityString(R.plurals.day, (int) days, days);
//        } else if (hours > 0) {
//            matchTimeElapsed = context.getResources().getQuantityString(R.plurals.hour, (int) hours, hours);
//        } else {
//            matchTimeElapsed = context.getResources().getQuantityString(R.plurals.minute, (int) minutes, minutes);
//        }
//        return matchTimeElapsed;
//    }

    public static String getMatchDuration(Context context, long duration) {
        String matchDuration = null;
        long hours = duration / (60 * 60);
        long mins = (duration / 60) % 60;
        long seconds = duration % 60;
        if (hours != 0L)
            matchDuration = context.getResources().getString(R.string.match_duration, hours, mins, seconds);
        else
            matchDuration = context.getResources().getString(R.string.match_duration_zero_hours, mins, seconds);
        return matchDuration;
    }
}
