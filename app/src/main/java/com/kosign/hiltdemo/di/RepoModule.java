package com.kosign.hiltdemo.di;

import com.kosign.hiltdemo.data.api.ApiService;
import com.kosign.hiltdemo.data.repository.IUserRepository;
import com.kosign.hiltdemo.data.repository.UserRepository;
import com.kosign.hiltdemo.data.source.DataSource;
import com.kosign.hiltdemo.data.source.IDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;

@Module
@InstallIn(SingletonComponent.class)
public class RepoModule {

    @Provides
    @Singleton
    IUserRepository provideUserRepository(IDataSource dataSource){
        return new UserRepository(dataSource);
    }

    @Provides
    @Singleton
    IDataSource provideDataSource(Retrofit retrofit){
        return new DataSource(retrofit);
    }

}
