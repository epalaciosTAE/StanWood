package kalpesh.mac.com.stanwood_test;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import kalpesh.mac.com.stanwood_test.adapter.ViewPagerAdapter;
import kalpesh.mac.com.stanwood_test.model.data.Item;
import kalpesh.mac.com.stanwood_test.model.data.Layout;
import kalpesh.mac.com.stanwood_test.model.data.Section;
import kalpesh.mac.com.stanwood_test.model.service.IMarvels;
import kalpesh.mac.com.stanwood_test.utilities.RxUtils;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity {

    @Inject
    IMarvels iMarvels;
    @Bind(R.id.view_pager) protected ViewPager mViewPager;
    @Bind(R.id.tabs)
    protected PagerSlidingTabStrip mTabStrip;

    private ProgressDialog pDialog;
    private List<Section> mSectionlist;
//    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
//    private PagerSlidingTabStrip mTabStrip;

    /**
     * Subscription that represents a group of Subscriptions that are unsubscribed together.
     */
    private CompositeSubscription _subscriptions = new CompositeSubscription();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager);
        bindViews();
        injectApiComponent();
        setUpProgressDialog();
        pattern();

    }

    private void bindViews() {
        ButterKnife.bind(this);
    }

    private void injectApiComponent() {
        MarvelApplication.getApp(getApplicationContext()).getApiComponent().inject(this);
    }

    private void setUpProgressDialog() {
        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        _subscriptions = RxUtils.getNewCompositeSubIfUnsubscribed(_subscriptions);
    }

    @Override
    public void onPause() {
        super.onPause();
        RxUtils.unsubscribeIfNotNull(_subscriptions);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void pattern(){
        _subscriptions.add(iMarvels.getData()

                .observeOn(AndroidSchedulers.mainThread())// Handle on UI Thread
                .subscribeOn(Schedulers.io())
                .timeout(500, TimeUnit.MILLISECONDS)
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends Layout>>() {
                    @Override
                    public Observable<? extends Layout> call(Throwable throwable) {
                        Toast.makeText(getBaseContext(), "Error ", Toast.LENGTH_SHORT).show();
                        return Observable.error(throwable);
                    }
                })

                .retry()
                .distinct()
                .subscribe(new Observer<Layout>() {
                    @Override
                    public void onCompleted() {
                        Log.i("Retrofit", "onCompleted");

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("ERROR", "ERROR", e);

                    }

                    @Override
                    public void onNext(Layout layout) {
                        Log.i("Retrofit", "onNext");
                        mSectionlist=layout.getSections();
                        List<Item> items = extractItemsFromSection();
                        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), items);
                        mViewPager.setAdapter(mViewPagerAdapter);
                        mTabStrip.setViewPager(mViewPager);
                        hidePDialog();
                    }
                }));



    }

    /**
     * Converter function to extract items from section.
     * Constructor optimized in Item to get the 2 params required in Adapter
     * @return
     */
    @NonNull
    private List<Item> extractItemsFromSection() {
        List<Item> items = new ArrayList<Item>(mSectionlist.size());
        for (Section section : mSectionlist) {
            for (Item item : section.getItems()) {
                items.add(new Item(item.getTitle(), item.getImages()));
            }
        }
        return items;
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
}
