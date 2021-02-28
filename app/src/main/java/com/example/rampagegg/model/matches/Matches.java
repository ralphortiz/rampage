package com.example.rampagegg.model.matches;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "table_matches")
public class Matches {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "match_id")
    @SerializedName("match_id")
    @Expose
    public Long matchId;

    @ColumnInfo(name = "player_slot")
    @SerializedName("player_slot")
    @Expose
    public Long playerSlot;

    @ColumnInfo(name = "radiant_win")
    @SerializedName("radiant_win")
    @Expose
    public boolean radiantWin;

    @ColumnInfo(name = "duration")
    @SerializedName("duration")
    @Expose
    public Long duration;

    @ColumnInfo(name = "game_mode")
    @SerializedName("game_mode")
    @Expose
    public Long gameMode;

    @ColumnInfo(name = "lobby_type")
    @SerializedName("lobby_type")
    @Expose
    public Long lobbyType;

    @ColumnInfo(name = "hero_id")
    @SerializedName("hero_id")
    @Expose
    public Long heroId;

    @ColumnInfo(name = "start_time")
    @SerializedName("start_time")
    @Expose
    public Long startTime;

    @ColumnInfo(name = "kills")
    @SerializedName("kills")
    @Expose
    public Long kills;

    @ColumnInfo(name = "deaths")
    @SerializedName("deaths")
    @Expose
    public Long deaths;

    @ColumnInfo(name = "assists")
    @SerializedName("assists")
    @Expose
    public Long assists;

    @ColumnInfo(name = "skill")
    @SerializedName("skill")
    @Expose
    public Long skill;

    public Long getMatchId() {
        return matchId;
    }

    public Long getPlayerSlot() {
        return playerSlot;
    }

    public boolean isRadiantWin() {
        return radiantWin;
    }

    public Long getDuration() {
        return duration;
    }

    public Long getGameMode() {
        return gameMode;
    }

    public Long getLobbyType() {
        return lobbyType;
    }

    public Long getHeroId() {
        return heroId;
    }

    public Long getStartTime() {
        return startTime;
    }

    public Long getKills() {
        return kills;
    }

    public Long getDeaths() {
        return deaths;
    }

    public Long getAssists() {
        return assists;
    }

    @Override
    public String toString() {
        return "Matches{" +
                "matchId=" + matchId +
                ", heroId=" + heroId +
                ", kills=" + kills +
                ", deaths=" + deaths +
                ", assists=" + assists +
                '}';
    }
}
