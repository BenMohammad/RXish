package com.benmohammad.rxish.root;

import com.benmohammad.rxish.di.MainActivityModule;
import com.benmohammad.rxish.di.NetworkModule;
import com.benmohammad.rxish.di.viewmodel_factories.ViewModelFactory;
import com.benmohammad.rxish.di.viewmodel_factories.ViewModelFactoryModule;
import com.benmohammad.rxish.views.detail_activity.DetailActivity;
import com.benmohammad.rxish.views.main_activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, MainActivityModule.class, ViewModelFactoryModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);
    void inject(DetailActivity detailActivity);
}
