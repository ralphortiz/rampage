package com.example.rampagegg.ui.matches;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MatchesViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    private final Application application;

    public MatchesViewModelFactory(@NonNull Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == MatchesViewModel.class) {
            return (T) new MatchesViewModel(application);
        }
        return null;
    }
}
