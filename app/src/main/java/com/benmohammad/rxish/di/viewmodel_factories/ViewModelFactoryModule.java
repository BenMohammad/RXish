package com.benmohammad.rxish.di.viewmodel_factories;

import androidx.lifecycle.ViewModel;

import com.benmohammad.rxish.views.main_activity.MainActivityViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel.class)
    abstract ViewModel bindsLogicActivityViewModel(MainActivityViewModel viewModel);
}
