package kalpesh.mac.com.stanwood_test.services;


import kalpesh.mac.com.stanwood_test.constants.Constants;
import kalpesh.mac.com.stanwood_test.model.service.IMarvels;
import kalpesh.mac.com.stanwood_test.utilities.APIErrorHandler;
import retrofit.RestAdapter;

/**
 * Created by kalpesh on 27/12/2015.
 */
public class Services {
    public static IMarvels _createRestruentshubApi() {

        RestAdapter.Builder builder= new RestAdapter.Builder()
                .setEndpoint(Constants.BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setErrorHandler(new APIErrorHandler());


        return builder.build().create(IMarvels.class);
    }
}
