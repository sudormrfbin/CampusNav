package com.google.ar.core.examples.java.persistentcloudanchor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.ar.core.examples.java.common.helpers.DisplayRotationHelper;

public class AddDistanceActivity extends AppCompatActivity {
    private static final String TAG = "AddDistanceActivity";
    private DistanceController distanceController = new DistanceController(this);;
    private DisplayRotationHelper displayRotationHelper;

    static Intent newIntent(Context packageContext) {
        return new Intent(packageContext, AddDistanceActivity.class);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_distance_lobby);
        displayRotationHelper = new DisplayRotationHelper(this);
        MaterialButton updateButton = findViewById(R.id.find_path_button);
        updateButton.setOnClickListener((view) -> onUpdateButtonPress());
    }

    private void onUpdateButtonPress() {
        Log.d(TAG, "Update button pressed");

        EditText anchor1 = (EditText) findViewById(R.id.anchor1);
        EditText anchor2 = (EditText) findViewById(R.id.anchor2);
        EditText dist = (EditText) findViewById(R.id.dist);

        //Get data entered by the user
        String anchor1Name = anchor1.getText().toString();
        String anchor2Name = anchor2.getText().toString();
        float distance = Float.parseFloat(dist.getText().toString());
        distanceController.DistanceSave(anchor1Name, anchor2Name, distance);

        // Clear the text fields
        anchor1.setText("");
        anchor2.setText("");
        dist.setText("");

        // Show a notification message
        String notificationMessage = "Distance updated";
        Toast.makeText(getApplicationContext(), notificationMessage, Toast.LENGTH_SHORT).show();

        distanceController.printDistances(anchor1Name);
        distanceController.printDistances(anchor2Name);
    }
}
