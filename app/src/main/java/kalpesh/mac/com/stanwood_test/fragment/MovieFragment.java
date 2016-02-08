package kalpesh.mac.com.stanwood_test.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import kalpesh.mac.com.stanwood_test.R;
import kalpesh.mac.com.stanwood_test.adapter.MovieAdapter;
import kalpesh.mac.com.stanwood_test.constants.Constants;
import kalpesh.mac.com.stanwood_test.decorator.MovieItemDecorator;
import kalpesh.mac.com.stanwood_test.model.data.Item;

/**
 * Created by Eduardo on 08/02/2016.
 */
public class MovieFragment extends Fragment {

    public static final String TAG = MovieFragment.class.getSimpleName();
    @Bind(R.id.img_card_view_big)
    protected ImageView mHeaderView;
    @Bind(R.id.list)
    protected RecyclerView mRecyclerView;

    public static MovieFragment newInstance(ArrayList<Item> items) {
        Bundle b = new Bundle();
        b.putParcelableArrayList(Constants.EXTRA_ITEMS, items);
        MovieFragment fragment = new MovieFragment();
        fragment.setArguments(b);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        List<Item> items = getArguments().getParcelableArrayList(Constants.EXTRA_ITEMS);
        Item header = null;
        if (items != null) {
            header = items.get(0);
        }
        View view = inflater.inflate(R.layout.recycler_main, container, false);
        bindViews(view);
        setUpHeader(header);
        setUpRecyclerView(items);
        return view;

    }

    private void bindViews(View view) {
        ButterKnife.bind(this, view);
    }

    private void setUpHeader(Item header) {
//        ImageView mHeaderView = (ImageView) view.findViewById(R.id.img_card_view_big);
        Glide.with(getActivity().getApplicationContext()).load(header.getImages().get(0).getUrl()).into(mHeaderView);
//        TextView headerTitle = (TextView) view.findViewById(R.id.tv_card_view_big);
//        headerTitle.setText(header.getTitle());
    }

    private void setUpRecyclerView(List<Item> items) {
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new MovieItemDecorator(items.size())); // TODO This behaviour is not good enough, improve it
        MovieAdapter adapter = new MovieAdapter(items, R.layout.card_row, getActivity().getApplicationContext());
        mRecyclerView.setAdapter(adapter);
    }


}
