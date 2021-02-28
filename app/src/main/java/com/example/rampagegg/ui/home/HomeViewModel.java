package com.example.rampagegg.ui.home;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.rampagegg.model.useraccount.Account;
import com.example.rampagegg.network.DotaRepository;
import com.example.rampagegg.util.Status;

public class HomeViewModel extends AndroidViewModel {
    private LiveData<Account> mAccount;
    private DotaRepository mDotaRepository;
    private LiveData<Status> mStatus;

    public HomeViewModel(Application application) {
        super(application);
        mDotaRepository = new DotaRepository(application);
        mAccount = mDotaRepository.getUserProfile();
        mStatus = mDotaRepository.fetchDataFromServer();
    }

    public LiveData<Account> getUserProfile() {
        return mAccount;
    }

    public LiveData<Status> getStatus() {
        return mStatus;
    }
}
