package cse340.finalproject;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import cse340.finalproject.views.TimerView;

public class TimerStudyBuddyActivity extends StudyBuddyActivity {

    private TimerView timerView;
    private TextView studyText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findViewById(R.id.timer).setBackgroundColor(getColor(R.color.teal_200));

        addTimer();
        addStartPauseButton();
        addStudyMessage();
    }

    @Override
    protected void createTitle() {
        super.createTitle();

        TextView text = findViewById(R.id.activity_title);
        text.setText(R.string.StudyTimer);
    }

    private void addStudyMessage() {
        ConstraintLayout studyMessage = (ConstraintLayout) getLayoutInflater()
                .inflate(R.layout.study_message, null);

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;

        layoutParams.bottomMargin = screenHeight / 9;
        mainLayout.addView(studyMessage, layoutParams);
        studyText = findViewById(R.id.studyMessage);
    }

    private void addTimer() {
        timerView = new TimerView(getApplicationContext(), userSettings.getStudyTime(),
                userSettings.getBreakTime());

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;

        layoutParams.leftMargin = screenWidth / 2 -
                (int) getResources().getDimension(R.dimen.startButtonWidth) / 2;
        layoutParams.bottomMargin = screenHeight / 3;

        mainLayout.addView(timerView, layoutParams);
    }

    private void addStartPauseButton() {
        ConstraintLayout button = (ConstraintLayout) getLayoutInflater()
                .inflate(R.layout.start_pause_button, null);

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;

        layoutParams.leftMargin = screenWidth / 2 -
                (int) getResources().getDimension(R.dimen.startButtonWidth) / 2;
        layoutParams.bottomMargin = screenHeight / 3;
        mainLayout.addView(button, layoutParams);

        //  Set on click listener for the button
        findViewById(R.id.startPauseButton).setOnClickListener(view -> {
            Button startPauseButton = (Button) view;
            String text = (String) startPauseButton.getText();
            String newText = null;
            if (text.equals(getResources().getString(R.string.Pause))) {
                //  User clicked 'pause'
                startPauseButton.setText(R.string.Start);
                startPauseButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                startPauseButton.setTextColor(getResources().getColor(R.color.white));
                timerView.pauseTimer();
                if (timerView.getCurrentlyStudying()) {
                    newText = getResources().getString(R.string.Ready);
                    studyText.setTextColor(getResources().getColor(R.color.niceGreen));
                } else {
                    newText = getResources().getString(R.string.DeserveBreak);
                    studyText.setTextColor(Color.RED);
                }
            } else {
                //  User clicked 'start'
                startPauseButton.setText(R.string.Pause);
                startPauseButton.setBackgroundColor(getResources().getColor(R.color.grey));
                startPauseButton.setTextColor(getResources().getColor(R.color.black));
                timerView.startTimer();
                if (timerView.getCurrentlyStudying()) {
                    newText = getResources().getString(R.string.GoodWork);
                    studyText.setTextColor(getResources().getColor(R.color.niceGreen));
                } else {
                    newText = getResources().getString(R.string.RestUp);
                    studyText.setTextColor(Color.RED);
                }
            }
            studyText.setText(newText);
        });

        timerView.setStartPauseButton(findViewById(R.id.startPauseButton));
    }
}