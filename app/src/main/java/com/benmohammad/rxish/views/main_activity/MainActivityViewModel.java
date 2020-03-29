package com.benmohammad.rxish.views.main_activity;

import android.util.Log;
import android.util.Pair;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.benmohammad.rxish.model.User;
import com.benmohammad.rxish.repository.UserRepository;
import com.benmohammad.rxish.utils.UserListContainer;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivityViewModel extends ViewModel {

    public static final String TAG = "MainActivityViewModel";

    MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    MutableLiveData<Boolean> isInternetOnline = new MutableLiveData();
    MutableLiveData<Boolean> errorLoading = new MutableLiveData<>();
    private UserRepository repository;

    @Inject
    public MainActivityViewModel(UserRepository userRepository) {
        this.repository = userRepository;
        setViewValues();
    }

    public void fetchUserList() {
        repository.fetchUserListFromWs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResults, this::handleError);
    }

    private void handleResults(Pair<List<User>, Integer> sortedUsers) {
        UserListContainer.referenceUserList.setValue(sortedUsers.first);
        UserListContainer.referenceNumberOfFavorites.setValue(sortedUsers.second);
    }

    private void handleError(Throwable t) {
        errorLoading.setValue(true);
        Log.e(TAG, "handleError");
    }

    public void setViewValues() {
        isInternetOnline.setValue(true);
        isLoading.setValue(false);
        errorLoading.setValue(false);
    }

    public void updateUserListWithNewFavoriteCount() {
        Pair<List<User>, Integer> userListFavoritePair = repository.refreshUserList();
        UserListContainer.referenceUserList.setValue(userListFavoritePair.first);
        UserListContainer.referenceNumberOfFavorites.setValue(userListFavoritePair.second);
    }

}
