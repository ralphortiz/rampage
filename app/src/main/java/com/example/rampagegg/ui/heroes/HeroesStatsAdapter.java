package com.example.rampagegg.ui.heroes;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rampagegg.R;
import com.example.rampagegg.database.DotaRoomDatabase;
import com.example.rampagegg.model.heroesstats.HeroesStats;

import java.util.List;

public class HeroesStatsAdapter extends RecyclerView.Adapter<HeroesStatsAdapter.ViewHolder> {

    private static final String TAG = "HeroesStatsAdapter";
    private final Context mContext;
    private final OnHeroStatsListener mOnHeroStatsListener;
    private List<HeroesStats> mHeroesStatsList;

    public HeroesStatsAdapter(List<HeroesStats> heroesStatsList, Context context, OnHeroStatsListener onHeroStatsListener) {
        this.mHeroesStatsList = heroesStatsList;
        this.mContext = context;
        this.mOnHeroStatsListener = onHeroStatsListener;
    }

    public void setData(List<HeroesStats> heroesStatsList) {
        mHeroesStatsList.clear();
        mHeroesStatsList.addAll(heroesStatsList);
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HeroesStatsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.heroes_stats_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, mOnHeroStatsListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HeroesStatsAdapter.ViewHolder holder, int position) {
        HeroesStats heroesStats = mHeroesStatsList.get(position);
        DotaRoomDatabase db = DotaRoomDatabase.getInstance(mContext);
        String heroName = db.dotaDao().findHeroName(heroesStats.getHeroId()).replace("npc_dota_hero_", "");
        String imageUrl = "http://cdn.dota2.com/apps/dota2/images/heroes/" + heroName + "_full.png";
        Glide.with(mContext)
                .asBitmap()
                .load(imageUrl)
                .into(holder.imgHeroPlayed);

        Resources res = mContext.getResources();
        String games = res.getString(R.string.hero_stats_games, heroesStats.getGames());
        holder.tvHeroMatches.setText(games);

        float heroWinRate;
        if (heroesStats.getGames() != 0L)
            heroWinRate = (heroesStats.getWin().floatValue() / heroesStats.getGames().floatValue()) * 100;
        else
            heroWinRate = 0f;
        String winRate = res.getString(R.string.hero_stats_win_per, heroWinRate);
        holder.tvHeroWinRate.setText(winRate);
        setPercentageBar(holder.viewHeroWinRateBar, heroWinRate);

        float totalMatches = db.dotaDao().getTotalGames();
        String matches = res.getString(R.string.hero_stats_games, heroesStats.getGames());
        holder.tvHeroMatches.setText(matches);
        float gamesPercentage = (heroesStats.getGames().floatValue() / totalMatches) * 100;
        setPercentageBar(holder.viewHeroMatchesBar, gamesPercentage);

        Long heroLose = heroesStats.getGames() - heroesStats.getWin();
        String winLose = res.getString(R.string.hero_stats_win_lose, heroesStats.getWin(), heroLose);
        holder.tvWinLose.setText(winLose);
    }

    private void setPercentageBar(View view, float value) {
        int width = 85;
        float scale = view.getContext().getResources().getDisplayMetrics().density;
        width = (int) (width * scale + 0.5F);
        view.getLayoutParams().width = Math.round(width * (value / 100));
    }

    @Override
    public int getItemCount() {
        return mHeroesStatsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imgHeroPlayed;
        TextView tvHeroMatches, tvHeroWinRate, tvWinLose;
        OnHeroStatsListener onHeroStatsListener;
        View viewHeroMatchesBar, viewHeroWinRateBar;

        public ViewHolder(@NonNull View itemView, OnHeroStatsListener onHeroStatsListener) {
            super(itemView);
            imgHeroPlayed = itemView.findViewById(R.id.imgHeroPlayed);
            tvHeroMatches = itemView.findViewById(R.id.tvHeroMatches);
            tvHeroWinRate = itemView.findViewById(R.id.tvHeroWinRate);
            tvWinLose = itemView.findViewById(R.id.tvWinLose);
            viewHeroWinRateBar = itemView.findViewById(R.id.viewHeroWinRateBar);
            viewHeroMatchesBar = itemView.findViewById(R.id.viewHeroMatchesBar);
            this.onHeroStatsListener = onHeroStatsListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onHeroStatsListener.onHeroClick(getAdapterPosition());
        }
    }

    public interface OnHeroStatsListener {
        void onHeroClick(int position);
    }
}
