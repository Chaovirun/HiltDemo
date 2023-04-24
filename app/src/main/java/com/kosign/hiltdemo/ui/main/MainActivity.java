package com.kosign.hiltdemo.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kosign.hiltdemo.R;
import com.kosign.hiltdemo.data.model.LoginRequest;
import com.kosign.hiltdemo.data.model.User;
import com.kosign.hiltdemo.data.network.Resource;
import com.kosign.hiltdemo.data.network.Status;
import com.kosign.hiltdemo.data.repository.IUserRepository;
import com.kosign.hiltdemo.databinding.ActivityMainBinding;
import com.kosign.hiltdemo.ui.Constant;
import com.kosign.hiltdemo.ui.adpater.UserAdapter;
import com.kosign.hiltdemo.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@AndroidEntryPoint
public class MainActivity extends BaseActivity<ActivityMainBinding> {

    MainViewModel viewModel;
    UserAdapter adapter;

    List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.setViewModel(viewModel);

        viewModel.fetchUsers();

//        users = new ArrayList<>();
        adapter = new UserAdapter(viewModel.userList,viewModel);
        viewModel.user.observe(this, users -> {
            viewModel.userList.addAll(users);
            adapter.updateUsers(users);
            binding.rvUser.setAdapter(adapter);
            binding.rvUser.getRecycledViewPool().setMaxRecycledViews(0, 0);
        });

        viewModel.internetConnection.observe(this, b->{
            if (!b) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                //Setting message manually and performing action on button click
                builder.setMessage("Please check your connection!").setTitle("Notice")
                        .setCancelable(false)
                        .setPositiveButton("Try again", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                                viewModel.fetchUsers();
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        findViewById(R.id.request_more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Constant.token = "THIS IS FIREBASE TOKEN###";
//                Constant.new_base_url = "weeat.kosign.dev";
//                viewModel.login(new LoginRequest("", ""));
                viewModel.fetchUsers();
            }
        });

        EditText search = findViewById(R.id.ed_search);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filterSearch(editable.toString());
            }
        });

    }

    void filterSearch(String key){
        List<User> searchUsers = new ArrayList<>();
        for (User user :
                viewModel.userList) {

            boolean isSearchKeyMatched = Pattern.compile(Pattern.quote(key), Pattern.CASE_INSENSITIVE).matcher(user.getId()+user.getName()).find();
            if (isSearchKeyMatched){
                user.setSearchKey(key);
                searchUsers.add(user);
            }
        }
        adapter.setUsers(searchUsers);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

}