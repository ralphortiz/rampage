package com.example.rampagegg.model.matchdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Players {

    @SerializedName("player_slot")
    @Expose
    public Long playerSlot;
    @SerializedName("backpack_0")
    @Expose
    public Long backPack0;
    @SerializedName("backpack_1")
    @Expose
    public Long backPack1;
    @SerializedName("backpack_2")
    @Expose
    public Long backPack2;
    @SerializedName("item_neutral")
    @Expose
    public Long itemNeutral;
    @SerializedName("kills")
    @Expose
    public Long kills;
    @SerializedName("deaths")
    @Expose
    public Long deaths;
    @SerializedName("assists")
    @Expose
    public Long assists;
    @SerializedName("net_worth")
    @Expose
    public Long netWorth;
    @SerializedName("gold_per_min")
    @Expose
    public Long goldPerMin;
    @SerializedName("xp_per_min")
    @Expose
    public Long xpPerMin;
    @SerializedName("personaname")
    @Expose
    public String personaName;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("last_hits")
    @Expose
    public Long LastHits;
    @SerializedName("denies")
    @Expose
    public Long Denies;
    @SerializedName("hero_damage")
    @Expose
    public Long heroDamage;
    @SerializedName("item_0")
    @Expose
    public Long item0;
    @SerializedName("item_1")
    @Expose
    public Long item1;
    @SerializedName("item_2")
    @Expose
    public Long item2;
    @SerializedName("item_3")
    @Expose
    public Long item3;
    @SerializedName("item_4")
    @Expose
    public Long item4;
    @SerializedName("item_5")
    @Expose
    public Long item5;
    @SerializedName("hero_id")
    @Expose
    public Long heroId;
    @SerializedName("level")
    @Expose
    public Long level;
    @SerializedName("purchase_ward_observer")
    @Expose
    public Long obsPurchased;
    @SerializedName("purchase_ward_sentry")
    @Expose
    public Long senPurchased;

    public Long getPlayerSlot() {
        return playerSlot;
    }

    public Long getBackPack0() {
        return backPack0;
    }

    public Long getBackPack1() {
        return backPack1;
    }

    public Long getBackPack2() {
        return backPack2;
    }

    public Long getItemNeutral() {
        return itemNeutral;
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

    public Long getNetWorth() {
        return netWorth;
    }

    public Long getGoldPerMin() {
        return goldPerMin;
    }

    public Long getXpPerMin() {
        return xpPerMin;
    }

    public String getPersonaName() {
        return personaName;
    }

    public String getName() {
        return name;
    }

    public Long getLastHits() {
        return LastHits;
    }

    public Long getDenies() {
        return Denies;
    }

    public Long getHeroDamage() {
        return heroDamage;
    }

    public Long getItem0() {
        return item0;
    }

    public Long getItem1() {
        return item1;
    }

    public Long getItem2() {
        return item2;
    }

    public Long getItem3() {
        return item3;
    }

    public Long getItem4() {
        return item4;
    }

    public Long getItem5() {
        return item5;
    }

    public Long getHeroId() {
        return heroId;
    }

    public Long getLevel() {
        return level;
    }

    public Long getObsPurchased() {
        return obsPurchased;
    }

    public Long getSenPurchased() {
        return senPurchased;
    }
}
