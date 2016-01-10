package com.github.rodrigohenriques.data.internal.di;

import com.github.rodrigohenriques.data.api.PicNicApi;

import dagger.Module;
import dagger.Provides;
import retrofit.JacksonConverterFactory;
import retrofit.Retrofit;

@Module
public class DataModule {
    @Provides
    public PicNicApi provideApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://s3-eu-west-1.amazonaws.com/developer-application-test/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        return retrofit.create(PicNicApi.class);
    }
}
