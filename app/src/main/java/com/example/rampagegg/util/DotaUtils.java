package com.example.rampagegg.util;

import android.content.Context;
import android.util.Log;
import android.util.LongSparseArray;

import com.example.rampagegg.model.Hero;
import com.example.rampagegg.model.Item;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class DotaUtils {

    private static final String TAG = "DotaUtil";
    public static LongSparseArray<String> mode = new LongSparseArray<>();
    public static LongSparseArray<String> lobby = new LongSparseArray<>();
    public static LongSparseArray<String> skill = new LongSparseArray<>();

    static {
        mode.put(0, "Unknown");
        mode.put(1, "All Pick");
        mode.put(2, "Captains Mode");
        mode.put(3, "Random Draft");
        mode.put(4, "Single Draft");
        mode.put(5, "All Random");
        mode.put(6, "Intro");
        mode.put(7, "Diretide");
        mode.put(8, "Reverse Captains Mode");
        mode.put(9, "Greeviling");
        mode.put(10, "Tutorial");
        mode.put(11, "Mid Only");
        mode.put(12, "Least Played");
        mode.put(13, "Limited Heroes");
        mode.put(14, "Compendium Matchmaking");
        mode.put(15, "Custom");
        mode.put(16, "Captains Draft");
        mode.put(17, "Balanced Draft");
        mode.put(18, "Ability Draft");
        mode.put(19, "Event");
        mode.put(20, "All Random Deathmatch");
        mode.put(21, "1v1 Mid");
        mode.put(22, "All Draft");
        mode.put(23, "Turbo");
        mode.put(24, "Mutation");
    }

    static {
        lobby.put(0, "Normal");
        lobby.put(1, "Practice");
        lobby.put(2, "Tournament");
        lobby.put(3, "Tutorial");
        lobby.put(4, "Coop Bots");
        lobby.put(5, "Ranked Team MM");
        lobby.put(6, "Ranked Solo MM");
        lobby.put(7, "Ranked");
        lobby.put(8, "1v1 Mid");
        lobby.put(9, "Battle Cup");
    }

    static {
        skill.put(1, "Normal");
        skill.put(2, "High");
        skill.put(3, "Very High");
    }


    public static List<Hero> getHeroJson(Context context) {

        String jsonFileString = DotaUtils.getJsonFromAssets(context, "DotaHeroes.json");
        Gson gson = new Gson();
        Type type = new TypeToken<List<Hero>>() {
        }.getType();
        List<Hero> heroesList = new ArrayList<>();
        try {
            heroesList = gson.fromJson(jsonFileString, type);
        } catch (Exception e) {
            Log.e("error parsing", e.toString());
        }
        return heroesList;
    }

    public static List<Item> getItemJson(Context context) {

        String jsonFileString = DotaUtils.getJsonFromAssets(context, "DotaItems.json");
        Gson gson = new Gson();
        Type type = new TypeToken<List<Item>>() {
        }.getType();
        List<Item> itemList = new ArrayList<>();
        try {
            itemList = gson.fromJson(jsonFileString, type);
        } catch (Exception e) {
            Log.e("error parsing", e.toString());
        }
        return itemList;
    }

    private static String getJsonFromAssets(Context context, String fileName) {
        String jsonString;
        try {
            InputStream is = context.getAssets().open(fileName);

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonString = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return jsonString;
    }


}
