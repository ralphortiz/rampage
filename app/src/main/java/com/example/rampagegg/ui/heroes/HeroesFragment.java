package com.example.rampagegg.ui.heroes;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rampagegg.R;
import com.example.rampagegg.model.heroesstats.HeroesStats;

import java.util.ArrayList;
import java.util.List;


public class HeroesFragment extends Fragment implements HeroesStatsAdapter.OnHeroStatsListener {

    private static final String TAG = "HeroesFragment";
    private Context mContext;
    private List<HeroesStats> mHeroesStatsList = new ArrayList<>();
    private HeroesStatsAdapter adapter;
    private RecyclerView rvHeroStats;
    private SwipeRefreshLayout swipeHeroesStats;
    private HeroesViewModel heroesViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_heroes, container, false);
        // Initialize Views
//        swipeHeroesStats = view.findViewById(R.id.swipeHeroesStats);
        rvHeroStats = view.findViewById(R.id.rvHeroStats);
        rvHeroStats.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new HeroesStatsAdapter(mHeroesStatsList, mContext, this);
        rvHeroStats.setAdapter(adapter);
//        heroesViewModel = new ViewModelProvider(this).get(HeroesViewModel.class);
//        heroesViewModel.init();
//        heroesViewModel.getHeroesStats().observe(this, new Observer<List<HeroesStats>>() {
//            @Override
//            public void onChanged(List<HeroesStats> heroesStatsList) {
//                adapter.setData(heroesStatsList);
//            }
//        });
        return view;
    }

    @Override
    public void onHeroClick(int position) {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }
}