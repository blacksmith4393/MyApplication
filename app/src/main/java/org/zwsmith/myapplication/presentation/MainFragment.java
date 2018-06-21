package org.zwsmith.myapplication.presentation;

import android.content.Intent;
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

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

import org.zwsmith.myapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;

public class MainFragment extends Fragment {
    private static final String TAG = MainFragment.class.getName();
    private static final int RC_SIGN_IN = 123;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button actionButton;
    private MyListAdapter adapter;
    private MyViewModel viewModel;
    private CompositeDisposable compositeDisposable;

    private List<AuthUI.IdpConfig> providers =
            Collections.singletonList(new AuthUI.IdpConfig.GoogleBuilder().build());

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
        recyclerView = view.findViewById(R.id.list_rv);
        actionButton = view.findViewById(R.id.primary_action_button);
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

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignInActivity(providers);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                // ...
            } else {
                String errorCode = null;
                if (response != null) {
                    errorCode = Integer.toString(Objects.requireNonNull(response.getError()).getErrorCode());
                }
                Log.d(TAG, errorCode);
            }
        }
    }

    private void startSignInActivity(List<AuthUI.IdpConfig> providers) {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }
}
