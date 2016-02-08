package kalpesh.mac.com.stanwood_test.model.service;

import kalpesh.mac.com.stanwood_test.constants.Constants;
import kalpesh.mac.com.stanwood_test.model.data.Layout;
import retrofit.http.GET;
import retrofit.http.Headers;
import rx.Observable;

/**
 * Created by kalpesh on 06/02/2016.
 */
public interface IMarvels {
    @Headers({
            "token: f90384c1-5a26-4a76-9f3b-fc0b37fe06f8"
    })
    @GET(Constants.API_URL)
    Observable<Layout> getData();

}
