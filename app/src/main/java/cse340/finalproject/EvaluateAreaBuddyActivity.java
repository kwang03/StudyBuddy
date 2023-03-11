package cse340.finalproject;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

//  Heavily inspired by the SensorActivity class done in CSE 340 section
/**
 * Activity that displays the 'check your surroundings' screen of the app
 */
public class EvaluateAreaBuddyActivity extends StudyBuddyActivity {
    /**
     * The list of sensors that are shown
     */
    private static final int[] SENSORS_USED = {
            Sensor.TYPE_LIGHT,
            Sensor.TYPE_AMBIENT_TEMPERATURE,
            Sensor.TYPE_RELATIVE_HUMIDITY
    };

    private static final int DELAY = SensorManager.SENSOR_DELAY_UI;

    /**
     * Instance of the sensor manager for grabbing sensor data
     */
    private SensorManager mSensorManager;

    /**
     * Sensor event listener that handles changes in sensor data
     */
    private SensorEventListener sensorEventListener;

    /**
     * Callback for when this activity is created
     * @param savedInstanceState bundle for saved instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findViewById(R.id.check).setBackgroundColor(getColor(R.color.teal_200));

        createTable();

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.getSensorList(Sensor.TYPE_ALL);

        //  sets the sensor event listener for all the sensors
        sensorEventListener = createSensorEventListener();
        for (int sensorIndex : SENSORS_USED) {
            Sensor sensor = mSensorManager.getDefaultSensor(sensorIndex);
            mSensorManager.registerListener(sensorEventListener, sensor, DELAY);
        }
    }

    /**
     * callback for when the activity is paused
     */
    @Override
    protected void onPause() {
        super.onPause();

        //  unregister listeners
        for (int sensorIndex : SENSORS_USED) {
            Sensor sensor = mSensorManager.getDefaultSensor(sensorIndex);
            mSensorManager.unregisterListener(sensorEventListener, sensor);
        }
    }

    /**
     * Creates the title for this activity
     */
    @Override
    protected void createTitle() {
        super.createTitle();

        TextView text = findViewById(R.id.activity_title);
        text.setText(R.string.CheckSurroundings);
    }

    /**
     * Creates the table layout that shows the sensors and their measured values
     */
    private void createTable() {
        ConstraintLayout table = (ConstraintLayout) getLayoutInflater()
                .inflate(R.layout.sensor_layout, null);

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;

        mainLayout.addView(table, layoutParams);
    }


    /**
     * Help method to update the table row (at the given index) with the string value. Assumes
     * there *is* a table row at that index, and the value is something that will fit ont he screen.
     * (taken from section code)
     *
     * @param index The index of the table row up update. Must be > 0
     * @param value The value to update the text with.
     */
    private void updateTableData(int index, String value, int color) {
        TableLayout sensorTable = findViewById(R.id.sensorTable);
        TableRow row = (TableRow) sensorTable.getChildAt(index);
        if (row != null && !value.equals("")) {
            TextView sensorData = (TextView) row.getChildAt(1);
            sensorData.setText(value);
            sensorData.setTextColor(color);
        }
    }

    /**
     * Creates the event listener used by all the sensors
     * @return A sensor event listener that handles changes in all the used sensors
     */
    private SensorEventListener createSensorEventListener() {
        return new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                String toDisplay;
                int index;
                int color;
                TextView status;
                switch (event.sensor.getType()) {
                    case Sensor.TYPE_LIGHT:
                        float light = event.values[0];
                        status = findViewById(R.id.lightStatus);
                        if (light < userSettings.getLightMin()) {
                            color = getColor(R.color.colorPrimaryDark);
                            status.setText(getText(R.string.Up));
                            status.setContentDescription(getString(R.string.increase) +
                                    " " + getString(R.string.Light));
                        } else if (light > userSettings.getLightMax()) {
                            color = getColor(R.color.raspberry);
                            status.setText(getText(R.string.Down));
                            status.setContentDescription(getString(R.string.decrease) +
                                    " " + getString(R.string.Light));
                        } else {
                            color = getColor(R.color.niceGreen);
                            status.setText(getText(R.string.Good));
                            status.setContentDescription(getString(R.string.good) +
                                    " " + getString(R.string.Light));
                        }
                        status.setTextColor(color);
                        toDisplay = light + " lx";
                        index = 1;
                        break;
                    case Sensor.TYPE_AMBIENT_TEMPERATURE:
                        float temp = event.values[0];
                        status = findViewById(R.id.tempStatus);
                        if (temp < userSettings.getTempMin()) {
                            color = getColor(R.color.colorPrimaryDark);
                            status.setText(getText(R.string.Up));
                            status.setContentDescription(getString(R.string.increase) +
                                    " " + getString(R.string.Temperature));
                        }
                        else if (temp > userSettings.getTempMax()) {
                            color = getColor(R.color.raspberry);
                            status.setText(getText(R.string.Down));
                            status.setContentDescription(getString(R.string.decrease) +
                                    " " + getString(R.string.Temperature));
                        }
                        else {
                            color = getColor(R.color.niceGreen);
                            status.setText(getText(R.string.Good));
                            status.setContentDescription(getString(R.string.good) +
                                    " " + getString(R.string.Temperature));
                        }
                        status.setTextColor(color);
                        toDisplay = temp + " C";
                        index = 2;
                        break;
                    case Sensor.TYPE_RELATIVE_HUMIDITY:
                        float humidity = event.values[0];
                        status = findViewById(R.id.humidityStatus);
                        if (humidity < userSettings.getHumidityMin()) {
                            color = getColor(R.color.colorPrimaryDark);
                            status.setText(getText(R.string.Up));
                            status.setContentDescription(getString(R.string.increase) +
                                    " " + getString(R.string.Humidity));
                        }
                        else if (humidity > userSettings.getHumidityMax()) {
                            color = getColor(R.color.raspberry);
                            status.setText(getText(R.string.Down));
                            status.setContentDescription(getString(R.string.decrease) +
                                    " " + getString(R.string.Humidity));
                        }
                        else {
                            color = getColor(R.color.niceGreen);
                            status.setText(getText(R.string.Good));
                            status.setContentDescription(getString(R.string.good) +
                                    " " + getString(R.string.Humidity));
                        }
                        status.setTextColor(color);
                        toDisplay = humidity + " %";
                        index = 3;
                        break;

                    default:
                        color = Color.BLACK;
                        index = 0;
                        toDisplay = "---";
                }
                updateTableData(index, toDisplay, color);

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
    }
}

