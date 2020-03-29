package com.benmohammad.rxish.networking;

import com.benmohammad.rxish.model.User;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class UserListDAOImpl implements UserListDao {

    private Retrofit retrofit;

    @Inject
    public UserListDAOImpl(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public Observable<List<User>> fetchListOfUsersFromWS() {
        return retrofit
                .create(UserListService.class)
                .fetchUserList();
    }
}
