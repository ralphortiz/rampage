package com.example.rampagegg.ui.matchstats;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rampagegg.R;
import com.example.rampagegg.database.DotaRoomDatabase;
import com.example.rampagegg.model.Item;
import com.example.rampagegg.model.matchdetails.Players;

import java.util.List;

public class PlayersStatsAdapter extends RecyclerView.Adapter<PlayersStatsAdapter.ViewHolder> {

    private static final String TAG = "PlayersStatsAdapter";
    private final List<Players> mPlayersList;
    private final Context mContext;
    private final OnPlayersListener mOnPlayersListener;

    public PlayersStatsAdapter(List<Players> playersList, Context context, OnPlayersListener onPlayersListener) {
        this.mPlayersList = playersList;
        this.mContext = context;
        this.mOnPlayersListener = onPlayersListener;
    }

    @NonNull
    @Override
    public PlayersStatsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_stats, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, mOnPlayersListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlayersStatsAdapter.ViewHolder holder, int position) {
        Players players = mPlayersList.get(position);
        DotaRoomDatabase db = DotaRoomDatabase.getInstance(mContext);

        String heroName = db.dotaDao().findHeroName(players.getHeroId()).replace("npc_dota_hero_", "");
        loadHeroImg(holder.imgPlayerHero, heroName);

        Item item = db.dotaDao().findItemName(players.getItem0());
        loadItemImg(holder.imgItem0, players.getItem0());
        loadItemImg(holder.imgItem1, players.getItem1());
        loadItemImg(holder.imgItem2, players.getItem2());
        loadItemImg(holder.imgItem3, players.getItem3());
        loadItemImg(holder.imgItem4, players.getItem4());
        loadItemImg(holder.imgItem5, players.getItem5());
        loadItemImg(holder.imgBackpack0, players.getBackPack0());
        loadItemImg(holder.imgBackpack1, players.getBackPack1());
        loadItemImg(holder.imgBackpack2, players.getBackPack2());
        loadItemImg(holder.imgNeutral, players.getItemNeutral());

        String playerName = players.getName();
        String personaName = players.getPersonaName();
        if (playerName != null && personaName != null)
            holder.tvPlayerName.setText(playerName);
        else if (playerName == null && personaName != null)
            holder.tvPlayerName.setText(personaName);
        else if (playerName != null && personaName == null)
            holder.tvPlayerName.setText(playerName);
        else
            holder.tvPlayerName.setText(R.string.no_player_name);

        Resources res = mContext.getResources();

        String kda = res.getString(R.string.match_kda, players.getKills(), players.getDeaths(), players.getAssists());
        holder.tvPlayerKda.setText(kda);
        String level = res.getString(R.string.match_hero_level, players.getLevel());
        holder.tvHeroLevel.setText(level);
        String lastHits = res.getString(R.string.match_last_hits, players.getLastHits());
        holder.tvLastHits.setText(lastHits);
        String denies = res.getString(R.string.match_denies, players.getDenies());
        holder.tvDenies.setText(denies);
        String heroDmg = res.getString(R.string.match_hero_damage, players.getHeroDamage());
        holder.tvHeroDmg.setText(heroDmg);
        String gpm = res.getString(R.string.match_gpm, players.getGoldPerMin());
        holder.tvGpm.setText(gpm);
        String xpm = res.getString(R.string.match_xpm, players.getXpPerMin());
        holder.tvXpm.setText(xpm);
        String gold = res.getString(R.string.match_net_worth, players.getNetWorth());
        holder.tvNet.setText(gold);

        if (players.getObsPurchased() == null) {
            holder.tvObserverWards.setText(R.string.match_observer_purchased_if_none);
        } else {
            String obsWards = res.getString(R.string.match_observer_purchased, players.getObsPurchased());
            holder.tvObserverWards.setText(obsWards);
        }
        if (players.getSenPurchased() == null) {
            holder.tvSentryWards.setText(R.string.match_sentry_purchased_if_none);
        } else {
            String senWards = res.getString(R.string.match_sentry_purchased, players.getSenPurchased());
            holder.tvSentryWards.setText(senWards);
        }
    }

    private void loadHeroImg(ImageView img, String name) {
        String imgUrl = "http://cdn.dota2.com/apps/dota2/images/heroes/" + name + "_full.png";
        Glide.with(mContext)
                .asBitmap()
                .load(imgUrl)
                .into(img);
    }

    private void loadItemImg(ImageView img, Long itemId) {
        DotaRoomDatabase db = DotaRoomDatabase.getInstance(mContext);
        if (itemId == 0) {
//            img.setVisibility(View.GONE);
            Glide.with(mContext)
                    .asBitmap()
                    .load(R.drawable.ic_empty_item)
                    .into(img);
        } else {
            Item item = db.dotaDao().findItemName(itemId);
            if (item.getRecipe() == 0) {
                String itemName = item.name.replace("item_", "");
                String imgUrl = "http://cdn.dota2.com/apps/dota2/images/items/" + itemName + "_lg.png";
                Glide.with(mContext)
                        .asBitmap()
                        .load(imgUrl)
                        .into(img);
            } else {
                String imgUrl = "http://cdn.dota2.com/apps/dota2/images/items/recipe_lg.png";
                Glide.with(mContext)
                        .asBitmap()
                        .load(imgUrl)
                        .into(img);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mPlayersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout llStatsCollapsed, llStatsExpanded;
        ImageView imgPlayerHero, imgItem0, imgItem1, imgItem2, imgItem3, imgItem4, imgItem5, imgBackpack0, imgBackpack1, imgBackpack2, imgNeutral;
        TextView tvPlayerName, tvPlayerKda, tvHeroLevel, tvLastHits, tvDenies, tvHeroDmg, tvGpm, tvXpm, tvNet, tvObserverWards, tvSentryWards;
        OnPlayersListener onPlayersListener;

        public ViewHolder(@NonNull View itemView, OnPlayersListener onMatchListener) {
            super(itemView);
            imgPlayerHero = itemView.findViewById(R.id.imgPlayerHero);
            imgItem0 = itemView.findViewById(R.id.imgItem0);
            imgItem1 = itemView.findViewById(R.id.imgItem1);
            imgItem2 = itemView.findViewById(R.id.imgItem2);
            imgItem3 = itemView.findViewById(R.id.imgItem3);
            imgItem4 = itemView.findViewById(R.id.imgItem4);
            imgItem5 = itemView.findViewById(R.id.imgItem5);
            imgBackpack0 = itemView.findViewById(R.id.imgBackpack0);
            imgBackpack1 = itemView.findViewById(R.id.imgBackpack1);
            imgBackpack2 = itemView.findViewById(R.id.imgBackpack2);
            imgNeutral = itemView.findViewById(R.id.imgNeutral);

            tvPlayerName = itemView.findViewById(R.id.tvPlayerName);
            tvPlayerKda = itemView.findViewById(R.id.tvPlayerKda);
            tvHeroLevel = itemView.findViewById(R.id.tvHeroLevel);
            tvLastHits = itemView.findViewById(R.id.tvLastHits);
            tvDenies = itemView.findViewById(R.id.tvDenies);
            tvHeroDmg = itemView.findViewById(R.id.tvHeroDmg);
            tvGpm = itemView.findViewById(R.id.tvGpm);
            tvXpm = itemView.findViewById(R.id.tvXpm);
            tvNet = itemView.findViewById(R.id.tvNet);
            tvObserverWards = itemView.findViewById(R.id.tvObserverWards);
            tvSentryWards = itemView.findViewById(R.id.tvSentryWards);
            this.onPlayersListener = onMatchListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onPlayersListener.onPlayersClick(getAdapterPosition());

        }
    }

    public interface OnPlayersListener {
        void onPlayersClick(int position);
    }
}
