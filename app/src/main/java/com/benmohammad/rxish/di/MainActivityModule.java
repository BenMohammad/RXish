package com.benmohammad.rxish.di;

import androidx.appcompat.widget.LinearLayoutCompat;

import com.benmohammad.rxish.adapters.MainActivityRecyclerViewAdapter;
import com.benmohammad.rxish.networking.UserListDAOImpl;
import com.benmohammad.rxish.networking.UserListDao;
import com.benmohammad.rxish.repository.UserRepository;
import com.benmohammad.rxish.repository.UserRepositoryImpl;
import com.benmohammad.rxish.views.main_activity.MainActivity;
import com.benmohammad.rxish.views.main_activity.MainActivityViewModel;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = NetworkModule.class)
public abstract class MainActivityModule {

    @Provides
    static MainActivityRecyclerViewAdapter provideMainActivityRecyclerAdapter() {
        return new MainActivityRecyclerViewAdapter();
    }

    @Provides
    static UserListDao provideUserListDao(Retrofit retrofit) {
        return new UserListDAOImpl(retrofit);
    }

    @Provides
    static UserRepository provideUserRepository(UserListDAOImpl userListDAOImpl) {
        return new UserRepositoryImpl(userListDAOImpl);
    }

    @Provides
    static MainActivityViewModel provideMainActivityViewModel(UserRepositoryImpl repository) {
        return new MainActivityViewModel(repository);
    }
}
