package com.benmohammad.rxish.utils;

import androidx.lifecycle.MutableLiveData;

import com.benmohammad.rxish.model.User;

import java.util.List;

public class UserListContainer {

    public static MutableLiveData<List<User>> referenceUserList = new MutableLiveData<>();
    public static MutableLiveData<Integer> referenceNumberOfFavorites = new MutableLiveData<>();
}
