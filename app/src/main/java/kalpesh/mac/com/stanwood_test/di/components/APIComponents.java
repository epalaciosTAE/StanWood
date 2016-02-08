package kalpesh.mac.com.stanwood_test.di.components;

import dagger.Component;
import kalpesh.mac.com.stanwood_test.MainActivity;
import kalpesh.mac.com.stanwood_test.di.modules.APIModule;
import kalpesh.mac.com.stanwood_test.di.scopes.UserScope;


/**
 * Created by kalpesh on 20/01/2016.
 */

    @UserScope
    @Component(dependencies =NetComponent.class, modules = APIModule.class)
    public interface APIComponents {

    void inject(MainActivity activity);

}
