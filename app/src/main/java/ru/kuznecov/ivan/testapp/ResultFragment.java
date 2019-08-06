package ru.kuznecov.ivan.testapp;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.kuznecov.ivan.testapp.adapter.Adapter;
import ru.kuznecov.ivan.testapp.network.NetworkService;
import ru.kuznecov.ivan.testapp.pojo.PhotoData;
import ru.kuznecov.ivan.testapp.pojo.UnsplashObj;


public class ResultFragment extends Fragment {

    private static final String ARG_QUERY = "query";
    private String query;
    private Context context;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private MyViewModel viewModel;
    private LiveData<UnsplashObj> liveData;

    public static Bundle getBundle(String param) {
        Bundle args = new Bundle();
        args.putString(ARG_QUERY, param);
        return args;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            query = getArguments().getString(ARG_QUERY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initRecyclerView(view);
        viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        viewModel.setQuery(query);
        liveData = viewModel.getLiveData();
        liveDataChange();


    }

    private void liveDataChange() {
        liveData.observe(this, new Observer<UnsplashObj>() {
            @Override
            public void onChanged(@Nullable UnsplashObj unsplashObj) {
                adapter.changeList(unsplashObj.getPhotoDataList());
            }
        });
    }

    private void initRecyclerView(@NonNull View view) {
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new Adapter(context);
        recyclerView.setAdapter(adapter);
        adapter.setListener(new Adapter.Listener() {
            @Override
            public void loadMore() {
                viewModel.loadNextPages();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

    }

}
