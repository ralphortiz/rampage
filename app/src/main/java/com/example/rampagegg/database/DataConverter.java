package com.example.rampagegg.database;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import androidx.room.TypeConverter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataConverter {

    @TypeConverter
    public static List<String> fromString(String string) {
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        return new Gson().fromJson(string, type);
    }

    @TypeConverter
    public static String fromArrayList(List<String> stringList) {
        return new Gson().toJson(stringList);
    }

//    @TypeConverter
//    public static Profile fromProfileString(String string) {
//        Type type = new TypeToken<Profile>() {
//        }.getType();
//        return new Gson().fromJson(string, type);
//    }
//
//    @TypeConverter
//    public static String fromProfile(Profile profile) {
//        return new Gson().toJson(profile);
//    }
//
//
//    @TypeConverter
//    public static MmrEstimate fromMmrEstimateString(String string) {
//        Type type = new TypeToken<Profile>() {
//        }.getType();
//        return new Gson().fromJson(string, type);
//    }
//
//    @TypeConverter
//    public static String fromMmrEstimate(MmrEstimate mmrEstimate) {
//        return new Gson().toJson(mmrEstimate);
//    }
}
