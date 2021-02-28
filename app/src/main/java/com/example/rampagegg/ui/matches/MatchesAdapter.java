package com.example.rampagegg.ui.matches;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rampagegg.common.Helpers;
import com.example.rampagegg.R;
import com.example.rampagegg.util.DotaUtils;
import com.example.rampagegg.database.DotaRoomDatabase;
import com.example.rampagegg.model.matches.Matches;

import java.util.List;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.ViewHolder> {

    private static final String TAG = "MatchesAdapter";
    private final Context mContext;
    private final OnMatchListener mOnMatchListener;
    private List<Matches> mMatchesList;

    public MatchesAdapter(List<Matches> matchesList, Context context, OnMatchListener onMatchListener) {
        this.mMatchesList = matchesList;
        this.mContext = context;
        this.mOnMatchListener = onMatchListener;
    }

    public void setData(List<Matches> matchesList) {
        mMatchesList.clear();
        mMatchesList.addAll(matchesList);
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MatchesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.matches_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, mOnMatchListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MatchesAdapter.ViewHolder holder, int position) {
        Matches matches = mMatchesList.get(position);
        DotaRoomDatabase db = DotaRoomDatabase.getInstance(mContext);

        String heroName = db.dotaDao().findHeroName(matches.getHeroId()).replace("npc_dota_hero_", "");
        String imageUrl = "http://cdn.dota2.com/apps/dota2/images/heroes/" + heroName + "_full.png";
        Glide.with(mContext)
                .asBitmap()
                .load(imageUrl)
                .into(holder.imgHero);

        String lobbyType = DotaUtils.lobby.get(matches.getLobbyType());
        holder.tvLobbyType.setText(lobbyType);
        String gameMode = DotaUtils.mode.get(matches.getGameMode());
        holder.tvGameMode.setText(gameMode);
        String kda = matches.getKills() + " / " + matches.getDeaths() + " / " + matches.getAssists();
        holder.tvKda.setText(kda);
        String result = isWinner(matches.isRadiantWin(), matches.getPlayerSlot());
        holder.tvResult.setText(result);
        if (result.equals("Won Match")) {
            holder.tvResult.setText(result);
            holder.tvResult.setTextColor(mContext.getResources().getColor(R.color.green));
        } else {
            holder.tvResult.setText(result);
            holder.tvResult.setTextColor(mContext.getResources().getColor(R.color.red));
        }
        String matchTimeElapsed = Helpers.getMatchTimeElapsed(mContext, matches.getStartTime() + matches.getDuration());
        holder.tvDate.setText(matchTimeElapsed);
    }

    private String isWinner(boolean radiantWin, Long playerSlot) {
        String result = null;
        if (radiantWin && playerSlot <= 127)
            result = "Won Match";
        else if (!radiantWin && playerSlot > 127)
            result = "Won Match";
        else
            result = "Lose Match";
        return result;
    }

    @Override
    public int getItemCount() {
        return mMatchesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imgHero;
        TextView tvLobbyType, tvGameMode, tvKda, tvResult, tvDate;
        OnMatchListener onMatchListener;

        public ViewHolder(@NonNull View itemView, OnMatchListener onMatchListener) {
            super(itemView);
            imgHero = itemView.findViewById(R.id.imgHero);
            tvLobbyType = itemView.findViewById(R.id.tvLobbyType);
            tvGameMode = itemView.findViewById(R.id.tvGameMode);
            tvKda = itemView.findViewById(R.id.tvKda);
            tvResult = itemView.findViewById(R.id.tvResult);
            tvDate = itemView.findViewById(R.id.tvDuration);
            this.onMatchListener = onMatchListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onMatchListener.onMatchClick(getAdapterPosition());
        }
    }

    public interface OnMatchListener {
        void onMatchClick(int position);
    }
}
