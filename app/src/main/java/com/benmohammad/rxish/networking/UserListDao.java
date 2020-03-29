package com.benmohammad.rxish.networking;

import com.benmohammad.rxish.model.User;

import java.util.List;

import io.reactivex.Observable;

public interface UserListDao {

    Observable<List<User>> fetchListOfUsersFromWS();
}
