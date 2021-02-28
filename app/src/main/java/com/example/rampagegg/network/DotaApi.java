package com.example.rampagegg.network;

import com.example.rampagegg.model.heroesstats.HeroesStats;
import com.example.rampagegg.model.matchdetails.MatchDetails;
import com.example.rampagegg.model.matches.Matches;
import com.example.rampagegg.model.useraccount.Account;

import java.util.List;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface DotaApi {

    @Headers("Content-Type: application/json")
    @GET("api/players/{account_id}")
    Observable<Account> getProfile(@Path("account_id") int accountId);

    @Headers("Content-Type: application/json")
    @GET("api/players/{account_id}/recentMatches")
    Observable<List<Matches>> getRecentMatches(@Path("account_id") int accountId);

    @Headers("Content-Type: application/json")
    @GET("api/players/{account_id}/heroes ")
    Observable<List<HeroesStats>> getHeroesStats(@Path("account_id") int accountId);

    @Headers("Content-Type: application/json")
    @GET("api/matches/{match_id}")
    Call<MatchDetails> getMatchDetails(@Path("match_id") Long matchId);
}
