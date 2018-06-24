package org.zwsmith.myapplication.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.zwsmith.myapplication.R;
import org.zwsmith.myapplication.MyApplication;
import org.zwsmith.myapplication.core.dependencyInjection.AppComponent;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;

public class MainFragment extends Fragment {
    private static final String TAG = MainFragment.class.getName();
    private static final int RC_SIGN_IN = 123;

    private AppComponent appComponent;
    private MainViewModel mainViewModel;
    private List<AuthUI.IdpConfig> authProviders =
            Collections.singletonList(new AuthUI.IdpConfig.GoogleBuilder().build());

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appComponent = MyApplication.get(getActivity()).getAppComponent();
        mainViewModel = appComponent.getMainViewModel();
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
        Button actionButton = view.findViewById(R.id.primary_action_button);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignInActivity(authProviders);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    mainViewModel.createUser(user.getUid(), user.getDisplayName(), user.getEmail());
                }
            } else {
                if (response != null) {
                    String message = Objects.requireNonNull(response.getError()).getMessage();
                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                }
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
