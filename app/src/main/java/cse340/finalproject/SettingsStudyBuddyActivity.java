package cse340.finalproject;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class SettingsStudyBuddyActivity extends StudyBuddyActivity{

    private final int[] SPINNER_IDS = {R.id.study_time_spinner, R.id.break_time_spinner,
            R.id.light_spinner, R.id.temperature_spinner, R.id.humidity_spinner};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createTable();

        findViewById(R.id.settings).setBackgroundColor(getColor(R.color.teal_200));
    }

    private void createTable() {
        ConstraintLayout table = (ConstraintLayout) getLayoutInflater()
                .inflate(R.layout.settings_layout, null);

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;

        mainLayout.addView(table, layoutParams);
        createDropdowns();
    }

    private void createDropdowns() {
        for (int i = 0; i < SPINNER_IDS.length; i++) {
            int spinnerId = SPINNER_IDS[i];
            Spinner spinner = findViewById(spinnerId);

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.menu_items, android.R.layout.simple_spinner_item);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinner.setAdapter(adapter);

            AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    switch (spinnerId){
                        case R.id.study_time_spinner:
                            long studyTime = userSettings.getStudyTime();
                            if (position == 0) {
                                studyTime = UserSettings.HIGH_STUDY_TIME;
                            } else if (position == 1) {
                                studyTime = UserSettings.DEFAULT_STUDY_TIME;
                            } else if (position == 2) {
                                studyTime = UserSettings.LOW_STUDY_TIME;
                            }
                            sp.edit().putLong(getString(R.string.studyTimeKey), studyTime).apply();
                            break;
                        case R.id.break_time_spinner:
                            long breakTime = userSettings.getBreakTime();
                            if (position == 0) {
                                breakTime = UserSettings.HIGH_BREAK_TIME;
                            } else if (position == 1) {
                                breakTime = UserSettings.DEFAULT_BREAK_TIME;
                            } else if (position == 2) {
                                breakTime = UserSettings.LOW_BREAK_TIME;
                            }
                            sp.edit().putLong(getString(R.string.breakTimeKey), breakTime).apply();
                            break;
                        case R.id.light_spinner:
                            float lightMin = userSettings.getLightMin();
                            float lightMax = userSettings.getLightMax();
                            if (position == 0) {
                                lightMin = UserSettings.HIGH_LIGHT_MIN;
                                lightMax = UserSettings.HIGH_LIGHT_MAX;
                            } else if (position == 1) {
                                lightMin = UserSettings.DEFAULT_LIGHT_MIN;
                                lightMax = UserSettings.DEFAULT_LIGHT_MAX;
                            } else if (position == 2) {
                                lightMin = UserSettings.LOW_LIGHT_MIN;
                                lightMax = UserSettings.LOW_LIGHT_MAX;
                            }
                            sp.edit().putFloat(getString(R.string.lightMinKey), lightMin).apply();
                            sp.edit().putFloat(getString(R.string.lightMaxKey), lightMax).apply();
                            break;
                        case R.id.temperature_spinner:
                            float tempMin = userSettings.getTempMin();
                            float tempMax = userSettings.getTempMax();
                            if (position == 0) {
                                tempMin = UserSettings.HIGH_TEMP_MIN;
                                tempMax = UserSettings.HIGH_TEMP_MAX;
                            } else if (position == 1) {
                                tempMin = UserSettings.DEFAULT_TEMP_MIN;
                                tempMax = UserSettings.DEFAULT_TEMP_MAX;
                            } else if (position == 2) {
                                tempMin = UserSettings.LOW_TEMP_MIN;
                                tempMax  = UserSettings.LOW_TEMP_MAX;
                            }
                            sp.edit().putFloat(getString(R.string.tempMinKey), tempMin).apply();
                            sp.edit().putFloat(getString(R.string.tempMaxKey), tempMax).apply();
                            break;
                        case R.id.humidity_spinner:
                            float humidityMin = userSettings.getHumidityMin();
                            float humidityMax = userSettings.getHumidityMax();
                            if (position == 0) {
                                humidityMin = UserSettings.HIGH_HUMIDITY_MIN;
                                humidityMax = UserSettings.HIGH_HUMIDITY_MAX;
                            } else if (position == 1) {
                                humidityMin = UserSettings.DEFAULT_HUMIDITY_MIN;
                                humidityMax = UserSettings.DEFAULT_HUMIDITY_MAX;
                            } else if (position == 2) {
                                humidityMin = UserSettings.LOW_HUMIDITY_MIN;
                                humidityMax = UserSettings.LOW_HUMIDITY_MAX;
                            }
                            sp.edit().putFloat(getString(R.string.humidityMinKey), humidityMin).apply();
                            sp.edit().putFloat(getString(R.string.humidityMaxKey), humidityMax).apply();
                            break;
                    }


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            };

            setCurrentSelections(spinner, spinnerId);

            spinner.setOnItemSelectedListener(spinnerListener);
        }
    }

    private void setCurrentSelections(Spinner spinner, int spinnerId) {
        switch (spinnerId){
            case R.id.study_time_spinner:
                if (userSettings.getStudyTime() == UserSettings.LOW_STUDY_TIME) {
                    spinner.setSelection(2);
                } else if (userSettings.getStudyTime() == UserSettings.DEFAULT_STUDY_TIME) {
                    spinner.setSelection(1);
                } else if (userSettings.getStudyTime() == UserSettings.HIGH_STUDY_TIME){
                    spinner.setSelection(0);
                }
                break;
            case R.id.break_time_spinner:
                if (userSettings.getBreakTime() == UserSettings.LOW_BREAK_TIME) {
                    spinner.setSelection(2);
                } else if (userSettings.getBreakTime() == UserSettings.DEFAULT_BREAK_TIME) {
                    spinner.setSelection(1);
                } else {
                    spinner.setSelection(0);
                }
                break;
            case R.id.light_spinner:
                if (userSettings.getLightMin() == UserSettings.LOW_LIGHT_MIN) {
                    spinner.setSelection(2);
                } else if (userSettings.getLightMin() == UserSettings.DEFAULT_LIGHT_MIN) {
                    spinner.setSelection(1);
                } else {
                    spinner.setSelection(0);
                }
                break;
            case R.id.temperature_spinner:
                if (userSettings.getTempMin() == UserSettings.LOW_TEMP_MIN) {
                    spinner.setSelection(2);
                } else if (userSettings.getTempMin() == UserSettings.DEFAULT_TEMP_MIN) {
                    spinner.setSelection(1);
                } else {
                    spinner.setSelection(0);
                }
                break;
            case R.id.humidity_spinner:
                if (userSettings.getHumidityMin() == UserSettings.LOW_HUMIDITY_MIN) {
                    spinner.setSelection(2);
                } else if (userSettings.getHumidityMin() == UserSettings.DEFAULT_HUMIDITY_MIN) {
                    spinner.setSelection(1);
                } else {
                    spinner.setSelection(0);
                }
                break;
        }
    }

    @Override
    protected void createTitle() {
        super.createTitle();

        TextView text = findViewById(R.id.activity_title);
        text.setText(R.string.Settings);
    }
}
