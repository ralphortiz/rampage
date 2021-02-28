package com.example.rampagegg.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class HomeViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    private final Application application;

    public HomeViewModelFactory(@NonNull Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == HomeViewModel.class) {
            return (T) new HomeViewModel(application);
        }
        return null;
    }
}
