package com.example.rampagegg.model.useraccount;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile {
    @SerializedName("account_id")
    @Expose
    public Long accountId;

    @SerializedName("personaname")
    @Expose
    public String personaName;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("plus")
    @Expose
    public boolean plus;

    @SerializedName("steamid")
    @Expose
    public String steamId;

    @SerializedName("avatarfull")
    @Expose
    public String avatarFull;


    @SerializedName("last_login")
    @Expose
    public String lastLogin;

    public Long getAccountId() {
        return accountId;
    }

    public String getPersonaName() {
        return personaName;
    }

    public String getName() {
        return name;
    }

    public boolean isPlus() {
        return plus;
    }

    public String getSteamId() {
        return steamId;
    }

    public String getAvatarFull() {
        return avatarFull;
    }

    public String getLastLogin() {
        return lastLogin;
    }
}

