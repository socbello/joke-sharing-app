package com.sbello.jokesharingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

/**
 * Created by socbe on 11/11/2017.
 */

public class FormActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mTitleEditText;
    private EditText mDescriptionEditText;
    private Button mSendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        mTitleEditText = (EditText) findViewById(R.id.form_title_edit_text);
        mDescriptionEditText = (EditText) findViewById(R.id.form_description_edit_text);
        mSendButton = (Button) findViewById(R.id.form_send_button);
        mSendButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mSendButton) {
            sendForm();
        }
    }

    private void sendForm() {
        mSendButton.setEnabled(false);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("jokes");
        Item item = new Item();
        item.audio = mTitleEditText.getText().toString();
        item.date = new Date();
        item.email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        item.description = mDescriptionEditText.getText().toString();
        myRef.push().setValue(item);
        finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
