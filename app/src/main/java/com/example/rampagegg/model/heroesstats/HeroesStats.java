package com.example.rampagegg.model.heroesstats;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "table_heroes_stats")
public class HeroesStats {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "hero_id")
    @SerializedName("hero_id")
    @Expose
    public Long heroId;

    @ColumnInfo(name = "games")
    @SerializedName("games")
    @Expose
    public Long games;

    @ColumnInfo(name = "win")
    @SerializedName("win")
    @Expose
    public Long win;

    @ColumnInfo(name = "last_played")
    @SerializedName("last_played")
    @Expose
    public Long lastPlayed;

    public Long getHeroId() {
        return heroId;
    }

    public Long getGames() {
        return games;
    }

    public Long getWin() {
        return win;
    }

    public Long getLastPlayed() {
        return lastPlayed;
    }
}
