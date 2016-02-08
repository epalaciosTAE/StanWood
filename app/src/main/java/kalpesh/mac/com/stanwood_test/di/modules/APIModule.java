package kalpesh.mac.com.stanwood_test.di.modules;


import dagger.Module;
import dagger.Provides;
import kalpesh.mac.com.stanwood_test.di.scopes.UserScope;
import kalpesh.mac.com.stanwood_test.model.service.IMarvels;
import retrofit.RestAdapter;

@Module
public class APIModule {

    @Provides
    @UserScope
    public IMarvels providesIContactsInterface(RestAdapter retrofit) {
        return retrofit.create(IMarvels.class);
    }
}
