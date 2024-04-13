package com.example.mysicklecellapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find your CardViews
        CardView myCalenderCard = findViewById(R.id.myCalender);
        CardView myMedsCard = findViewById(R.id.myMeds);
        CardView myTrackerCard = findViewById(R.id.myTracker);
        CardView myCommunityCard = findViewById(R.id.myCommunity);
        CardView emergencyCard = findViewById(R.id.emergency);
        CardView myResourcesCard = findViewById(R.id.myResources);
        CardView findCenterCard = findViewById(R.id.findCenter);
        CardView settingsCard = findViewById(R.id.settings);

        // Set click listeners for each CardView
        myCalenderCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMyCalenderActivity();
            }
        });

        myMedsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMyMedsActivity();
            }
        });

        myTrackerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMyTrackerActivity();
            }
        });

        myCommunityCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMyCommunityActivity();
            }
        });

        emergencyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEmergencyActivity();
            }
        });

        myResourcesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMyResourcesActivity();
            }
        });

        findCenterCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFindCenterActivity();
            }
        });

        settingsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettingsActivity();
            }
        });
    }

    private void openMyCalenderActivity() {
        Intent intent = new Intent(this, MyCalenderActivity.class);
        startActivity(intent);
    }

    private void openMyMedsActivity() {
        Intent intent = new Intent(this, MyMedsActivity.class);
        startActivity(intent);
    }

    private void openMyTrackerActivity() {
        Intent intent = new Intent(this, MyTrackerActivity.class);
        startActivity(intent);
    }

    private void openMyCommunityActivity() {
        Intent intent = new Intent(this, MyCommunityActivity.class);
        startActivity(intent);
    }

    private void openEmergencyActivity() {
        Intent intent = new Intent(this, EmergencyActivity.class);
        startActivity(intent);
    }

    private void openMyResourcesActivity() {
        Intent intent = new Intent(this, MyResourcesActivity.class);
        startActivity(intent);
    }

    private void openFindCenterActivity() {
        Intent intent = new Intent(this, FindCenterActivity.class);
        startActivity(intent);
    }

    private void openSettingsActivity() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}
