package com.benmohammad.rxish.repository;

import android.util.Pair;

import com.benmohammad.rxish.model.User;

import java.util.List;

import io.reactivex.Observable;

public interface UserRepository {

    Observable<Pair<List<User>, Integer>> fetchUserListFromWs();
    Pair<List<User>, Integer> refreshUserList();
}
