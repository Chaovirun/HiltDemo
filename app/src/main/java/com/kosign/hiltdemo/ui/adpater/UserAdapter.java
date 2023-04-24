package com.kosign.hiltdemo.ui.adpater;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kosign.hiltdemo.BR;
import com.kosign.hiltdemo.R;
import com.kosign.hiltdemo.data.model.User;
import com.kosign.hiltdemo.databinding.UserItemLayoutBinding;
import com.kosign.hiltdemo.ui.main.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.BindableViewHolder> {

//    UserItemLayoutBinding binding;
    private final MainViewModel viewModel;
    public UserAdapter(List<User> users,MainViewModel viewModel){
        this.users.addAll(users);
        this.viewModel = viewModel;
    }

    List<User> users = new ArrayList<>();
    public void updateUsers(List<User> users){
        final UserDiffCallback callback = new UserDiffCallback(this.users, users);
        final DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);

        this.users.clear();
        this.users.addAll(users);
        result.dispatchUpdatesTo(this);
    }

    public void setUsers(List<User> users){
        this.users.clear();
        this.users.addAll(users);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BindableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UserItemLayoutBinding binding = UserItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new BindableViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull BindableViewHolder holder, int position) {
        holder.bind(users.get(position));
//        User user = users.get(position);
//        Glide.with(holder.imageView).load(user.getAvatar()).into(holder.imageView);
//        holder.textView.setText(user.getName());
//        holder.textView2.setText(user.getEmail());

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class BindableViewHolder extends RecyclerView.ViewHolder {

        private User user;

         private final UserItemLayoutBinding binding;

        void bind(User user){
//            binding.setViewModel(viewModel);
//            binding.setUser(user);
            this.user = user;
            binding.setVariable(BR.user, user);
            binding.executePendingBindings();
        }

        public BindableViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = (UserItemLayoutBinding) DataBindingUtil.bind(itemView);

            binding.card.setOnClickListener(view -> {
                user.setShowMore(binding.hideContent.getVisibility() != View.VISIBLE);
                notifyItemChanged(getAdapterPosition());
            });

        }

    }

}
