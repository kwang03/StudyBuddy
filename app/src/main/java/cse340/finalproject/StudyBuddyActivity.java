package cse340.finalproject;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class StudyBuddyActivity extends AppCompatActivity {

    private final int[] NAV_BAR_IDS = {R.id.timer, R.id.check, R.id.settings};
    protected UserSettings userSettings;
    protected ConstraintLayout mainLayout;
    protected int screenWidth;
    protected int screenHeight;
    protected SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createUserSettings();
        mainLayout = findViewById(R.id.main_layout);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;

        addNavBar();
        createTitle();
    }

    protected void createUserSettings() {
        Context context = getApplicationContext();
        sp = context.getSharedPreferences(getString(R.string.sharedPreferences), MODE_PRIVATE);

        long studyTime = sp.getLong(getString(R.string.studyTimeKey), Long.MIN_VALUE);
        long breakTime = sp.getLong(getString(R.string.breakTimeKey), Long.MIN_VALUE);
        float lightMin = sp.getFloat(getString(R.string.lightMinKey), Float.MIN_VALUE);
        float lightMax = sp.getFloat(getString(R.string.lightMaxKey), Float.MIN_VALUE);
        float tempMin = sp.getFloat(getString(R.string.tempMinKey), Float.MIN_VALUE);
        float tempMax = sp.getFloat(getString(R.string.tempMaxKey), Float.MIN_VALUE);
        float humidityMin = sp.getFloat(getString(R.string.humidityMinKey), Float.MIN_VALUE);
        float humidityMax = sp.getFloat(getString(R.string.humidityMaxKey), Float.MIN_VALUE);

        userSettings = new UserSettings(studyTime, breakTime, lightMin, lightMax, tempMin, tempMax,
                humidityMin, humidityMax);
    }

    protected void createTitle() {
        ConstraintLayout title = (ConstraintLayout) getLayoutInflater()
                .inflate(R.layout.screen_title, null);

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;

        layoutParams.topMargin = screenHeight / 6;

        mainLayout.addView(title, layoutParams);
    }

    private void addNavBar() {
        ConstraintLayout mainLayout = findViewById(R.id.main_layout);

        LinearLayout navBar = (LinearLayout) getLayoutInflater().inflate(R.layout.nav_bar, null);
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );

        layoutParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;

        mainLayout.addView(navBar, layoutParams);

        setNarBarOnClickListeners();
    }

    private void setNarBarOnClickListeners() {
        for (int id : NAV_BAR_IDS) {
            ImageView navBarItem = findViewById(id);
            // This is the listener to see if a person wants to go back to the check surroundings page
            navBarItem.setOnClickListener(v-> {
                Intent intent = null;
                if (id == R.id.timer) {
                    intent = new Intent(StudyBuddyActivity.this, TimerStudyBuddyActivity.class);
                } else if (id == R.id.check){
                    intent = new Intent(StudyBuddyActivity.this, EvaluateAreaBuddyActivity.class);
                } else if (id == R.id.settings) {
                    intent = new Intent(StudyBuddyActivity.this, SettingsStudyBuddyActivity.class);
                }
                // start the activity
                startActivity(intent);
            });
        }


    }
}
