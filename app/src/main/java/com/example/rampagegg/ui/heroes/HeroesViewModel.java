package com.example.rampagegg.ui.heroes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.rampagegg.model.heroesstats.HeroesStats;

import java.util.List;

public class HeroesViewModel extends ViewModel {
    private LiveData<List<HeroesStats>> mHeroesStatsList;

//    public void init() {
//        if (mHeroesStatsList != null) {
//            return;
//        }
//        DotaRepository mDotaRepository = DotaRepository.getInstance();
//        mHeroesStatsList = mDotaRepository.getHeroesStats();
//    }

    public LiveData<List<HeroesStats>> getHeroesStats() {
        return mHeroesStatsList;
    }
}
