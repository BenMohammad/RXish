package com.benmohammad.rxish.networking;

import com.benmohammad.rxish.model.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface UserListService {

    @GET("/technical-challenge/v3/contacts.json")
    Observable<List<User>> fetchUserList();



}
