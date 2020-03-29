package com.benmohammad.rxish.views.main_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.benmohammad.rxish.R;
import com.benmohammad.rxish.adapters.MainActivityRecyclerViewAdapter;
import com.benmohammad.rxish.databinding.ActivityMainBinding;
import com.benmohammad.rxish.di.viewmodel_factories.ViewModelFactory;
import com.benmohammad.rxish.root.App;
import com.benmohammad.rxish.utils.UserListContainer;
import com.benmohammad.rxish.views.detail_activity.DetailActivity;

import javax.inject.Inject;

import static com.benmohammad.rxish.views.detail_activity.DetailActivity.ADAPTER_POSITION;

public class MainActivity extends AppCompatActivity implements MainActivityRecyclerViewAdapter.UserViewHolderInterface {

    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;
    @Inject
    MainActivityRecyclerViewAdapter adapter;
    @Inject
    ViewModelFactory factory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getApplicationComponent(this).inject(this);
        bindView();
        setAdapter();
        observeViewModel();
    }

    private void bindView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel.class);
        binding.setViewmodel(viewModel);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
    }

    private void setAdapter() {
        UserListContainer.referenceNumberOfFavorites.observe(this, number -> {
            adapter.numberOfFavorites = number;
            adapter.notifyDataSetChanged();
        });

        UserListContainer.referenceUserList.observe(this, users -> {
            adapter.userList = users;
            adapter.notifyDataSetChanged();
        });

        adapter.setUserViewHolderInterface(this);
        binding.recyclerView.setAdapter(adapter);
    }

    private void observeViewModel() {viewModel.fetchUserList();
        viewModel.isLoading.observe(this, isLoading -> {
            binding.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            binding.progressBar.setIndeterminate(isLoading);
        });

        viewModel.errorLoading.observe(this, errorLoading -> {
            binding.errorLoading.setVisibility(errorLoading ? View.VISIBLE : View.GONE);
        });

        viewModel.isInternetOnline.observe(this, isOnline ->
                binding.noInternetAvailable.setVisibility(isOnline ? View.GONE : View.VISIBLE));
    }



    @Override
    public void onUserClicked(int adapterPosition) {
        Intent intent =     new Intent(this, DetailActivity.class);
        intent.putExtra(ADAPTER_POSITION, adapterPosition);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(UserListContainer.referenceUserList.getValue() != null) {
            viewModel.updateUserListWithNewFavoriteCount();
        }
    }
}
