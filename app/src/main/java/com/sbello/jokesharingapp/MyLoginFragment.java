package com.sbello.jokesharingapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by socbe on 11/11/2017.
 */

public class MyLoginFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "MyLoginFragment";

    private OnFragmentInteractionListener mListener;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button signInButton;

    public MyLoginFragment() {

    }

    public static MyLoginFragment newInstance() {
        MyLoginFragment fragment = new MyLoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_login, container, false);

        usernameEditText = (EditText) view.findViewById(R.id.username_edit_text);
        passwordEditText = (EditText) view.findViewById(R.id.password_edit_text);
        signInButton = (Button) view.findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(this);
        view.findViewById(R.id.create_account_button).setOnClickListener(this);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public void onClick(View view) {
        String email = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        if (view == signInButton) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                            if (!task.isSuccessful()) {
                                Log.w(TAG, "signInWithEmail:failed", task.getException());
                                mListener.onLoginFailed();
                            } else {
                                mListener.onLoginSuccess();
                            }
                        }
                    });
        } else if (view.getId() == R.id.create_account_button){
            mListener.onCreateAccount();
        }
    }

    public interface OnFragmentInteractionListener {
        void onLoginSuccess();
        void onLoginFailed();
        void onCreateAccount();
    }

}
