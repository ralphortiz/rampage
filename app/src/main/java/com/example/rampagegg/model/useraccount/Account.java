package com.example.rampagegg.model.useraccount;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Account {

    @SerializedName("tracked_until")
    @Expose
    public String trackedUntil;

    @SerializedName("solo_competitive_rank")
    @Expose
    public String soloCompetitiveRank;

    @SerializedName("rank_tier")
    @Expose
    public Long rankTier;

    @SerializedName("competitive_rank")
    @Expose
    public String competitiveRank;

    @SerializedName("profile")
    @Expose
    public Profile profile;

    @SerializedName("leaderboard_rank")
    @Expose
    public Long leaderboardRank;

    @SerializedName("mmr_estimate")
    @Expose
    public MmrEstimate mmrEstimate;

    public String getTrackedUntil() {
        return trackedUntil;
    }

    public String getSoloCompetitiveRank() {
        return soloCompetitiveRank;
    }

    public Long getRankTier() {
        return rankTier;
    }

    public String getCompetitiveRank() {
        return competitiveRank;
    }

    public Profile getProfile() {
        return profile;
    }

    public Long getLeaderboardRank() {
        return leaderboardRank;
    }

    public MmrEstimate getMmrEstimate() {
        return mmrEstimate;
    }
}


