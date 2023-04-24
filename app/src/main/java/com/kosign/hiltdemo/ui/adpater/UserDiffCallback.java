package com.kosign.hiltdemo.ui.adpater;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.kosign.hiltdemo.data.model.User;

import java.util.List;

public class UserDiffCallback extends DiffUtil.Callback {

    private final List<User> mOldUserList;
    private final List<User> mNewUserList;

    public UserDiffCallback(List<User> oldUserList, List<User> newUserList) {
        this.mOldUserList = oldUserList;
        this.mNewUserList = newUserList;
    }

    @Override
    public int getOldListSize() {
        return mOldUserList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewUserList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldUserList.get(oldItemPosition).getId() == mNewUserList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        User oldUser = mOldUserList.get(oldItemPosition);
        User newUser = mNewUserList.get(newItemPosition);
        return oldUser.getName().equals(newUser.getName());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
