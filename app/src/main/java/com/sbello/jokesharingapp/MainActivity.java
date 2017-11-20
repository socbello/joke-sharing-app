package com.sbello.jokesharingapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by socbe on 11/11/2017.
 */

public class MainActivity extends AppCompatActivity implements
        MyLoginFragment.OnFragmentInteractionListener,
        MyNewAccountFragment.OnFragmentInteractionListener,
        MyJokeFeedFragment.OnFragmentInteractionListener
{

    private static final String TAG = "MainActivity";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                startActivity(intent);
            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_frame_layout, MyJokeFeedFragment.newInstance())
                            .commit();
                    mFab.setVisibility(View.VISIBLE);
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_frame_layout, MyLoginFragment.newInstance())
                            .commit();
                    mFab.setVisibility(View.GONE);
                }
            }
        };
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0){
            getSupportFragmentManager().popBackStack();
        } else{
            super.onBackPressed();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onLoginSuccess() {
        Toast.makeText(this, R.string.success_label, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginFailed() {
        Toast.makeText(this, R.string.failed_label,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreateAccount() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_frame_layout, MyNewAccountFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onAccountSuccessfullyCreated() {

    }

    public void logout(){
        if (mAuth != null) mAuth.signOut();
    }

    @Override
    public void onItemSelected() {

    }
}
