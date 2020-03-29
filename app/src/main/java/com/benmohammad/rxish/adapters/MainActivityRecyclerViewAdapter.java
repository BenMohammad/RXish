package com.benmohammad.rxish.adapters;

import android.graphics.drawable.LayerDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.benmohammad.rxish.R;
import com.benmohammad.rxish.databinding.UserViewholderLayoutBinding;
import com.benmohammad.rxish.databinding.UserViewholderLayoutBindingImpl;
import com.benmohammad.rxish.model.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivityRecyclerViewAdapter extends RecyclerView.Adapter<MainActivityRecyclerViewAdapter.UserViewHolder> {

    public int numberOfFavorites = 0;
    public List<User> userList = new ArrayList<>();
    private UserViewHolderInterface userViewHolderInterface;

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UserViewholderLayoutBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.user_viewholder_layout, parent, false);
        UserViewHolder viewHolder = new UserViewHolder(binding);
        viewHolder.userViewHolderInterface = userViewHolderInterface;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.layoutBinding.setUser(user);

        holder.layoutBinding.favoriteViewholderHeader.setVisibility(position == 0 && numberOfFavorites != 0 ? View.VISIBLE : View.GONE);
        holder.layoutBinding.otherContactsViewholderHeader.setVisibility(position == numberOfFavorites ? View.VISIBLE : View.GONE);
        holder.layoutBinding.animationFavourite.setVisibility(user.getIsFavorite() ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setUserViewHolderInterface(UserViewHolderInterface userViewHolderInterface) {
        this.userViewHolderInterface = userViewHolderInterface;
    }

    public interface UserViewHolderInterface {
        void onUserClicked(int adapterPosition);
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        UserViewHolderInterface userViewHolderInterface;
        UserViewholderLayoutBinding layoutBinding;

        UserViewHolder(UserViewholderLayoutBinding binding) {
            super(binding.getRoot());
            this.layoutBinding = binding;


            layoutBinding.rootView.setOnClickListener(v -> userViewHolderInterface.onUserClicked(getAdapterPosition()));
        }

    }
}
