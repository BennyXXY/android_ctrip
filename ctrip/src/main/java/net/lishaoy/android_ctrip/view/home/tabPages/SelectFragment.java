package net.lishaoy.android_ctrip.view.home.tabPages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import net.lishaoy.android_ctrip.R;
import net.lishaoy.android_ctrip.api.RequestCenter;
import net.lishaoy.android_ctrip.model.TabSelect;
import net.lishaoy.android_ctrip.view.adapter.TabSelectAdapter;
import net.lishaoy.lib_network.listener.DisposeDataListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectFragment extends Fragment {

    @BindView(R.id.tab_select_recycler_container)
    RecyclerView tabSelectRecyclerContainer;
    private Unbinder unbinder;
    private List<TabSelect.DataBean.ViewspotsBean> viewspots;

    public SelectFragment() {
    }

    public static SelectFragment newInstance() {
        return new SelectFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select, container, false);
        unbinder = ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    private void initViews() {
        requestDatas();
    }

    private void getTabSeletItem() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        tabSelectRecyclerContainer.setLayoutManager(staggeredGridLayoutManager);
        TabSelectAdapter tabSelectAdapter = new TabSelectAdapter(getContext(),viewspots);
        tabSelectRecyclerContainer.setAdapter(tabSelectAdapter);
    }

    private void requestDatas() {
        RequestCenter.requestHomeTabSelect(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                TabSelect tabSelect = (TabSelect) responseObj;
                viewspots = tabSelect.getData().getViewspots();
                getTabSeletItem();
            }

            @Override
            public void onFailure(Object reasonObj) {

            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}