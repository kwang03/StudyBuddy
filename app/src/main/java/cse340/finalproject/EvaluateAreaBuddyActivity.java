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
public class EvaluateAreaBuddyActivity extends StudyBuddyActivity {
    /**
     * The list of sensors that we have chosen to display in this
     */
    private static final int[] SENSORS_USED = {
            Sensor.TYPE_LIGHT,
            Sensor.TYPE_AMBIENT_TEMPERATURE,
            Sensor.TYPE_RELATIVE_HUMIDITY
    };

    /**
     * How fast we want to update the display - for the whole activity
     */
    private static final int DELAY = SensorManager.SENSOR_DELAY_UI;

    /**
     * Instance of the sensor manager for grabbing sensor data
     */
    private SensorManager mSensorManager;


    private SensorEventListener sensorEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findViewById(R.id.check).setBackgroundColor(getColor(R.color.teal_200));

        createTable();

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.getSensorList(Sensor.TYPE_ALL);

        sensorEventListener = createSensorEventListener();
        for (int sensorIndex : SENSORS_USED) {
            Sensor sensor = mSensorManager.getDefaultSensor(sensorIndex);
            mSensorManager.registerListener(sensorEventListener, sensor, DELAY);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        for (int sensorIndex : SENSORS_USED) {
            Sensor sensor = mSensorManager.getDefaultSensor(sensorIndex);
            mSensorManager.unregisterListener(sensorEventListener, sensor);
        }
    }

    @Override
    protected void createTitle() {
        super.createTitle();

        TextView text = findViewById(R.id.activity_title);
        text.setText(R.string.CheckSurroundings);
    }

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
                            color = Color.BLUE;
                            status.setText(getText(R.string.Up));
                        } else if (light > userSettings.getLightMax()) {
                            color = Color.RED;
                            status.setText(getText(R.string.Down));
                        } else {
                            color = Color.GREEN;
                            status.setText(getText(R.string.Good));
                        }
                        status.setTextColor(color);
                        toDisplay = light + " lx";
                        index = 1;
                        break;
                    case Sensor.TYPE_AMBIENT_TEMPERATURE:
                        float temp = event.values[0];
                        status = findViewById(R.id.tempStatus);
                        if (temp < userSettings.getTempMin()) {
                            color = Color.BLUE;
                            status.setText(getText(R.string.Up));
                        }
                        else if (temp > userSettings.getTempMax()) {
                            color = Color.RED;
                            status.setText(getText(R.string.Down));
                        }
                        else {
                            color = Color.GREEN;
                            status.setText(getText(R.string.Good));
                        }
                        status.setTextColor(color);
                        toDisplay = temp + " C";
                        index = 2;
                        break;
                    case Sensor.TYPE_RELATIVE_HUMIDITY:
                        float humidity = event.values[0];
                        status = findViewById(R.id.humidityStatus);
                        if (humidity < userSettings.getHumidityMin()) {
                            color = Color.BLUE;
                            status.setText(getText(R.string.Up));
                        }
                        else if (humidity > userSettings.getHumidityMax()) {
                            color = Color.RED;
                            status.setText(getText(R.string.Down));
                        }
                        else {
                            color = Color.GREEN;
                            status.setText(getText(R.string.Good));
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

