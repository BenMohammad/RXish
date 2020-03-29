package com.benmohammad.rxish.repository;

import android.util.Pair;

import com.benmohammad.rxish.model.User;
import com.benmohammad.rxish.networking.UserListDao;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import io.reactivex.Observable;

public class UserRepositoryImpl implements UserRepository {

    private UserListDao userListDao;

    @Inject
    public UserRepositoryImpl(UserListDao userListDao) {
        this.userListDao = userListDao;
    }



    @Override
    public Observable<Pair<List<User>, Integer>> fetchUserListFromWs() {
        Observable<List<User>> userLisObs = userListDao
                .fetchListOfUsersFromWS()
                .map(this::sortListAlphabetically);

        Observable<Integer> numberOfFavoriteObs = userLisObs
                .map(this::getNumberOfFavorites);

        return Observable.zip(userLisObs, numberOfFavoriteObs, Pair::new);
    }

    private List<User> sortListAlphabetically(List<User> users) {
        return users.stream()
                .sorted((user1, user2) -> user1.getName().compareTo(user2.getName()))
                .sorted((user1, user2) -> user2.getIsFavorite().compareTo(user1.getIsFavorite()))
                .collect(Collectors.toList());
    }

    private Integer getNumberOfFavorites(List<User> users) {
        return (int) users.stream()
                .filter(User::getIsFavorite)
                .count();
    }

    @Override
    public Pair<List<User>, Integer> refreshUserList() {
        return null;
    }
}
