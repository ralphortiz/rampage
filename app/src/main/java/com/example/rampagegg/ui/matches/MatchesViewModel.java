package com.example.rampagegg.ui.matches;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.rampagegg.model.matches.Matches;
import com.example.rampagegg.network.DotaRepository;

import java.util.List;

public class MatchesViewModel extends AndroidViewModel {
    private LiveData<List<Matches>> mMatchesList;
    private DotaRepository mDotaRepository;

    public MatchesViewModel(@NonNull Application application) {
        super(application);
        mDotaRepository = new DotaRepository(application);
        mMatchesList = mDotaRepository.getRecentMatches();
    }

    public LiveData<List<Matches>> getMatchesList() {
        return mMatchesList;
    }
}
