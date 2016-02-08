package kalpesh.mac.com.stanwood_test;

import android.app.Application;
import android.content.Context;

import kalpesh.mac.com.stanwood_test.constants.Constants;
import kalpesh.mac.com.stanwood_test.di.components.APIComponents;
import kalpesh.mac.com.stanwood_test.di.components.DaggerAPIComponents;
import kalpesh.mac.com.stanwood_test.di.components.DaggerNetComponent;
import kalpesh.mac.com.stanwood_test.di.components.NetComponent;
import kalpesh.mac.com.stanwood_test.di.modules.APIModule;
import kalpesh.mac.com.stanwood_test.di.modules.AppModule;
import kalpesh.mac.com.stanwood_test.di.modules.NetModule;

/**
 * Created by kalpesh on 06/02/2016.
 */
public class MarvelApplication extends Application{
    private NetComponent mNetComponent;
    private APIComponents mApiComponents;

    public static MarvelApplication getApp(Context context) {
        return (MarvelApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setUpNetComponent();
        setUpApiComponent();

    }

    private void setUpApiComponent() {
        mApiComponents = DaggerAPIComponents.builder()
                .netComponent(mNetComponent)
                .aPIModule(new APIModule())
                .build();
    }

    private void setUpNetComponent() {
        mNetComponent = DaggerNetComponent.builder()
                .netModule(new NetModule(Constants.BASE_URL))
                .appModule(new AppModule(this))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }

    public APIComponents getApiComponent() {
        return mApiComponents;
    }
}
