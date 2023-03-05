package cse340.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class StudyBuddyActivity extends AppCompatActivity {

    private final int[] NAV_BAR_IDS = {R.id.timer, R.id.check, R.id.settings};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addNavBar();
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
