package com.benmohammad.rxish.model;


import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.benmohammad.rxish.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class User {


    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("isFavorite")
    @Expose
    private Boolean isFavorite;
    @SerializedName("smallImageURL")
    @Expose
    private String smallImageURL;
    @SerializedName("largeImageURL")
    @Expose
    private String largeImageURL;
    @SerializedName("emailAddress")
    @Expose
    private String emailAddress;
    @SerializedName("birthdate")
    @Expose
    private String birthdate;
    @SerializedName("phone")
    @Expose
    private Phone phone;
    @SerializedName("address")
    @Expose
    private Address address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Boolean getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(Boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public String getSmallImageURL() {
        return smallImageURL;
    }

    public void setSmallImageURL(String smallImageURL) {
        this.smallImageURL = smallImageURL;
    }

    public String getLargeImageURL() {
        return largeImageURL;
    }

    public void setLargeImageURL(String largeImageURL) {
        this.largeImageURL = largeImageURL;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

//    @BindingAdapter("android:imageUrl")
    public static void loadImage (View view, String smallImageURL){
        ImageView imageView = (ImageView) view;
        Picasso.get().load(smallImageURL).into(imageView, new Callback() {
            @Override
            public void onSuccess() {}

            @Override
            public void onError(Exception e) {
                Log.e("PicassoError ", "on user list view: ", e );
                //((ImageView) view).setImageResource(R.drawable.ic_person_light_grey_24dp);
            }
        });
    }
}



