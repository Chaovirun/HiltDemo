package com.kosign.hiltdemo.data.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("name")
    String name;

    @SerializedName("id")
    int id;

    @SerializedName("avatar")
    String avatar;

    @SerializedName("email")
    String email;

    String searchKey = "";

    Boolean showMore;

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public Boolean getShowMore() {
        return showMore;
    }

    public void setShowMore(Boolean showMore) {
        this.showMore = showMore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
