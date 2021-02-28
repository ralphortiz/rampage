package com.example.rampagegg.ui.matches;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rampagegg.R;
import com.example.rampagegg.model.matches.Matches;
import com.example.rampagegg.ui.matchstats.MatchDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;


public class MatchesFragment extends Fragment implements MatchesAdapter.OnMatchListener {

    private static final String TAG = "MatchesFragment";
    private Context mContext;
    private List<Matches> mMatchesList = new ArrayList<>();
    private MatchesAdapter adapter;
    private RecyclerView rvMatches;
    private SwipeRefreshLayout swipeMatches;
    private MatchesViewModel matchesViewModel;
    private final CompositeDisposable disposables = new CompositeDisposable();

    public MatchesFragment() {
    }

    @Override
    public void onMatchClick(int position) {
        Intent intent = new Intent(mContext, MatchDetailsActivity.class);
        long matchId = mMatchesList.get(position).getMatchId();
        intent.putExtra("MatchId", matchId);
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matches, container, false);
        // Initialize Views
//        swipeMatches = view.findViewById(R.id.swipeMatches);
        rvMatches = view.findViewById(R.id.rvMatches);
        rvMatches.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new MatchesAdapter(mMatchesList, mContext, this);
        rvMatches.setAdapter(adapter);

        matchesViewModel = new ViewModelProvider(this, new MatchesViewModelFactory(getActivity().getApplication())).get(MatchesViewModel.class);
        matchesViewModel.getMatchesList().observe(getViewLifecycleOwner(), matchesList -> adapter.setData(matchesList));
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }
}