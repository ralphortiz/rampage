package com.example.rampagegg.ui.matchstats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.rampagegg.R;
import com.example.rampagegg.common.Helpers;
import com.example.rampagegg.model.matchdetails.MatchDetails;
import com.example.rampagegg.model.matchdetails.Players;
import com.example.rampagegg.network.DotaApi;
import com.example.rampagegg.network.DotaService;
import com.example.rampagegg.util.DotaUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchDetailsActivity extends AppCompatActivity implements PlayersStatsAdapter.OnPlayersListener {

    private static final String TAG = "MatchDetailsActivity";
    private Long value;
    private final List<Players> mRadiantList = new ArrayList<>();
    private final List<Players> mDireList = new ArrayList<>();
    private List<Players> mPlayerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_details);

        Bundle extra = getIntent().getExtras();
        if (extra != null)
            value = extra.getLong("MatchId");

        getMatchDetails();
    }

    private void initRadiantRecyclerView() {
        RecyclerView rvRadiant = (RecyclerView) findViewById(R.id.rvRadiant);
        rvRadiant.setNestedScrollingEnabled(false);
        rvRadiant.setLayoutManager(new LinearLayoutManager(this));
        PlayersStatsAdapter adapter = new PlayersStatsAdapter(mRadiantList, this, this);
        rvRadiant.setAdapter(adapter);
    }

    private void initDireRecyclerView() {
        RecyclerView rvDire = (RecyclerView) findViewById(R.id.rvDire);
        rvDire.setNestedScrollingEnabled(false);
        rvDire.setLayoutManager(new LinearLayoutManager(this));
        PlayersStatsAdapter adapter = new PlayersStatsAdapter(mDireList, this, this);
        rvDire.setAdapter(adapter);
    }

    private void getMatchDetails() {
        DotaApi dotaApi = DotaService.getRetroClient().create(DotaApi.class);
        Call<MatchDetails> call = dotaApi.getMatchDetails(value);

        call.enqueue(new Callback<MatchDetails>() {
            @Override
            public void onResponse(Call<MatchDetails> call, Response<MatchDetails> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "onResponse: Server Response: " + response.toString());
                    Log.d(TAG, "onResponse: Received information: " + response.body());
                    MatchDetails matchDetails = response.body();
                    mPlayerList = matchDetails.getPlayersList();
                    for (int i = 0; i < mPlayerList.size(); i++) {
                        Players players = mPlayerList.get(i);
                        if (players.getPlayerSlot() <= 4)
                            mRadiantList.add(players);
                        else
                            mDireList.add(players);
                    }
                    initViews(matchDetails);
                    initRadiantRecyclerView();
                    initDireRecyclerView();
                } else {
                    Log.d(TAG, "unable to fetch the json data");
                }
            }

            @Override
            public void onFailure(Call<MatchDetails> call, Throwable t) {
                Log.e(TAG, "onFailure: Something went wrong: " + t.getMessage());
            }
        });
    }

    private void initViews(MatchDetails details) {
        TextView tvMatchId = (TextView) findViewById(R.id.tvMatchId);
        TextView tvGameType = (TextView) findViewById(R.id.tvGameType);
        TextView tvRadiantScore = (TextView) findViewById(R.id.tvRadiantScore);
        TextView tvDireScore = (TextView) findViewById(R.id.tvDireScore);
        TextView tvMatchDuration = (TextView) findViewById(R.id.tvMatchDuration);
        TextView tvMatchEnded = (TextView) findViewById(R.id.tvMatchEnded);
        TextView tvRadiantText = (TextView) findViewById(R.id.tvRadiantText);
        TextView tvDireText = (TextView) findViewById(R.id.tvDireText);

        Resources res = this.getResources();
        String matchId = res.getString(R.string.match_id, details.getMatchId());
        tvMatchId.setText(matchId);
        String gameType = res.getString(R.string.match_game_type, DotaUtils.lobby.get(details.getLobbyType()), DotaUtils.mode.get(details.getGameMode()));
        tvGameType.setText(gameType);
        String radiantScore = res.getString(R.string.match_radiant_score, details.getRadiantScore());
        tvRadiantScore.setText(radiantScore);
        String direScore = res.getString(R.string.match_dire_score, details.getDireScore());
        tvDireScore.setText(direScore);
        String matchDuration = Helpers.getMatchDuration(this, details.getDuration());
        tvMatchDuration.setText(matchDuration);
        String matchTimeElapsed = Helpers.getMatchTimeElapsed(this, details.getStartTime() + details.getDuration());
        String matchEnded = res.getString(R.string.match_ended, matchTimeElapsed);
        tvMatchEnded.setText(matchEnded);
        if (details.isRadiantWin())
            tvRadiantText.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_trophy, 0);
        else
            tvDireText.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_trophy, 0);
    }

    @Override
    public void onPlayersClick(int position) {
        Players players = mPlayerList.get(position);
    }
}