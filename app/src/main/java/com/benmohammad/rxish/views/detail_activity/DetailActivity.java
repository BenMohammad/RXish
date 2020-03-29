package com.benmohammad.rxish.views.detail_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.benmohammad.rxish.R;
import com.benmohammad.rxish.databinding.ActivityDetailBinding;
import com.benmohammad.rxish.repository.UserRepository;
import com.benmohammad.rxish.root.App;
import com.benmohammad.rxish.utils.UserListContainer;
import com.benmohammad.rxish.views.main_activity.MainActivity;

import java.util.Objects;


public class DetailActivity extends AppCompatActivity {

    public static final String ADAPTER_POSITION = "adapter_position";
    private ActivityDetailBinding binding;
    private int adapterPosition;
    private DetailActivityViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getApplicationComponent(this).inject(this);
        checkStatus();
        bindView();
        getClickUser();
        observeModel();

    }

    private void checkStatus() {
        if(UserListContainer.referenceUserList == null) {
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    private void getClickUser() {
        adapterPosition = Objects.requireNonNull(getIntent().getExtras().getInt(ADAPTER_POSITION));
        UserListContainer.referenceUserList.observe(this, user -> viewModel.setUser(user.get(adapterPosition)));
    }

    private void bindView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        viewModel = ViewModelProviders.of(this).get(DetailActivityViewModel.class);
        binding.backButton.setOnClickListener(v -> onBackPressed());
        binding.setViewmodel(viewModel);
    }

    private void observeModel() {
        viewModel.name.observe(this, name ->  binding.contactNameOnDetailScreen.setVisibility(name == null ? View.GONE : View.VISIBLE));
        viewModel.company.observe(this, company -> binding.companyNameOnDetailScreen.setVisibility(company == null ? View.GONE : View.VISIBLE));
        viewModel.homePhone.observe(this, homePhone -> binding.homePhone.setVisibility(homePhone == null ? View.GONE : View.VISIBLE));
        viewModel.mobilePhone.observe(this, mobilePhone -> binding.mobilePhone.setVisibility(mobilePhone == null ? View.GONE : View.VISIBLE));
        viewModel.workPhone.observe(this, workPhone -> binding.workPhone.setVisibility(workPhone == null ? View.GONE : View.VISIBLE));
        viewModel.address.observe(this, address -> binding.address.setVisibility(address == null ? View.GONE : View.VISIBLE));
        viewModel.birthDate.observe(this, birthDate -> binding.birthdate.setVisibility(birthDate == null ? View.GONE : View.VISIBLE));
        viewModel.email.observe(this, email -> binding.email.setVisibility(email == null? View.GONE : View.VISIBLE));
        viewModel.isFavorite.observe(this, isFavorite -> {
            changeFavoriteMark(isFavorite);
            changeReferenceUserList(isFavorite);
        });
    }

    private void changeReferenceUserList(Boolean isFavorite) {
        UserListContainer.referenceUserList.observe(this, users -> {users.get(adapterPosition).setIsFavorite(isFavorite);} );
    }

    private void changeFavoriteMark(Boolean isFavorite) {
        binding.greyStar.setVisibility(isFavorite ? View.GONE : View.VISIBLE);
        binding.animationFavourite.setVisibility(isFavorite ? View.VISIBLE : View.GONE);
        if(isFavorite) binding.animationFavourite.playAnimation();
    }
}
