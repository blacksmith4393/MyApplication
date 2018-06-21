package org.zwsmith.myapplication.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import org.zwsmith.myapplication.R;

import java.util.ArrayList;

public class MainFragment extends Fragment {
    private static final String TAG = MainFragment.class.getName();

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MyListAdapter adapter;
    private MyViewModel viewModel;
    private CompositeDisposable compositeDisposable;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new MyViewModel();
        compositeDisposable = new CompositeDisposable();
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.anime_list_rv);
        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MyListAdapter(new ArrayList<String>());

        recyclerView.setAdapter(adapter);

        Consumer<ArrayList<String>> onNext = new Consumer<ArrayList<String>>() {
            @Override
            public void accept(ArrayList<String> strings) throws Exception {
                adapter.updateTitles(strings);
            }
        };
        Consumer<Throwable> onError = new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.d(TAG, "Throwable = " + throwable);
            }
        };

        compositeDisposable.add(
                viewModel.viewInfoStream
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(onNext, onError)
        );

        Button getAnimeListButton = view.findViewById(R.id.primary_action_button);
        getAnimeListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }
}
