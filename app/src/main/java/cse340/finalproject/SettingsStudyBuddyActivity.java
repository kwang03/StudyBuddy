package cse340.finalproject;

import android.os.Bundle;

public class SettingsStudyBuddyActivity extends StudyBuddyActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findViewById(R.id.settings).setBackgroundColor(getColor(R.color.teal_200));
    }
}
