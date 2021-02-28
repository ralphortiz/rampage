package com.example.rampagegg.model.matchdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MatchDetails {

    @SerializedName("match_id")
    @Expose
    public Long matchId;
    @SerializedName("duration")
    @Expose
    public Long duration;
    @SerializedName("game_mode")
    @Expose
    public Long gameMode;
    @SerializedName("lobby_type")
    @Expose
    public Long lobbyType;
    @SerializedName("skill")
    @Expose
    public Long skill;
    @SerializedName("dire_score")
    @Expose
    public Long direScore;
    @SerializedName("radiant_score")
    @Expose
    public Long radiantScore;
    @SerializedName("radiant_win")
    @Expose
    public boolean radiantWin;
    @SerializedName("start_time")
    @Expose
    public Long startTime;
    @SerializedName("players")
    @Expose
    public List<Players> playersList;

    public Long getMatchId() {
        return matchId;
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

    public Long getSkill() {
        return skill;
    }

    public Long getDireScore() {
        return direScore;
    }

    public Long getRadiantScore() {
        return radiantScore;
    }

    public boolean isRadiantWin() {
        return radiantWin;
    }

    public Long getStartTime() {
        return startTime;
    }

    public List<Players> getPlayersList() {
        return playersList;
    }
}
