package com.example.rampagegg.network;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.rampagegg.database.DotaRoomDatabase;
import com.example.rampagegg.model.heroesstats.HeroesStats;
import com.example.rampagegg.model.matches.Matches;
import com.example.rampagegg.model.useraccount.Account;
import com.example.rampagegg.util.Status;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class DotaRepository {
    private static final String TAG = "DotaRepository";
    private final DotaApi dotaApi;
    private final DotaRoomDatabase db;
    private final CompositeDisposable disposable;

    public DotaRepository(@NotNull Application application) {
        dotaApi = DotaService.getRetroClient().create(DotaApi.class);
        db = DotaRoomDatabase.getInstance(application);
        disposable = new CompositeDisposable();
    }

    public LiveData<Status> fetchDataFromServer() {
        final MutableLiveData<Status> data = new MutableLiveData<>();

        disposable.add(Observable.zip(
                dotaApi.getRecentMatches(278202820), dotaApi.getHeroesStats(278202820), (matchesList, heroesStatsList) -> {
                    db.dotaDao().updateMatches(matchesList);
                    db.dotaDao().insertAllHeroesStats(heroesStatsList);
                    return true;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            data.setValue(Status.SUCCESS);
                            Log.d(TAG, "Done");
                        },
                        throwable -> {
                            data.setValue(Status.ERROR);
                            Log.d(TAG, "Error");
                        }));
        return data;
    }

    public LiveData<Account> getUserProfile() {
        final MutableLiveData<Account> data = new MutableLiveData<>();

        disposable.add(dotaApi.getProfile(278202820)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(account -> {
                }));
        return data;
    }

    public LiveData<List<Matches>> getRecentMatches() {
        final MutableLiveData<List<Matches>> data = new MutableLiveData<>();

//        disposable.add(
//                dotaApi.getRecentMatches(278202820)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(matchesList -> db.dotaDao().updateMatches(matchesList)));
        return db.dotaDao().getAllMatches();
    }

    public LiveData<List<HeroesStats>> getHeroesStats() {
        final MutableLiveData<List<HeroesStats>> data = new MutableLiveData<>();

        disposable.add(dotaApi.getHeroesStats(278202820)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(heroesStatsList -> db.dotaDao().insertAllHeroesStats(heroesStatsList)));
        return db.dotaDao().getAllHeroesStats();
    }

    public void dispose() {
        disposable.dispose();
    }
}
