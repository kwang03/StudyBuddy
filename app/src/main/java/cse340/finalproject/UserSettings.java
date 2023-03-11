package cse340.finalproject;

/**
 * User settings class that keeps data about a users selected preferences
 */
public class UserSettings {

    //  default values
    static final long DEFAULT_STUDY_TIME = 1500000;
    static final long DEFAULT_BREAK_TIME = 300000;
    static final float DEFAULT_LIGHT_MIN = 400;
    static final float DEFAULT_LIGHT_MAX = 600;
    static final float DEFAULT_TEMP_MIN = 18;
    static final float DEFAULT_TEMP_MAX = 25;
    static final float DEFAULT_HUMIDITY_MIN = 40;
    static final float DEFAULT_HUMIDITY_MAX = 60;

    static final long LOW_STUDY_TIME = 600000;
    static final long HIGH_STUDY_TIME = 2700000;

    static final long LOW_BREAK_TIME = 120000;
    static final long HIGH_BREAK_TIME = 600000;

    static final float LOW_LIGHT_MIN = 100;
    static final float HIGH_LIGHT_MIN = 600;
    static final float LOW_LIGHT_MAX = 400;
    static final float HIGH_LIGHT_MAX = 800;

    static final float LOW_TEMP_MIN = 10;
    static final float LOW_TEMP_MAX = 18;
    static final float HIGH_TEMP_MIN = 25;
    static final float HIGH_TEMP_MAX = 30;

    static final float LOW_HUMIDITY_MIN = 20;
    static final float LOW_HUMIDITY_MAX = 40;
    static final float HIGH_HUMIDITY_MIN = 60;
    static final float HIGH_HUMIDITY_MAX = 80;

    private long studyTime;
    private long breakTime;
    private float lightMin;
    private float lightMax;
    private float tempMin;
    private float tempMax;
    private float humidityMin;
    private float humidityMax;

    /**
     * Create a user settings instance
     * @param studyTime
     * @param breakTime
     * @param lightMin
     * @param lightMax
     * @param tempMin
     * @param tempMax
     * @param humidityMin
     * @param humidityMax
     */
    public UserSettings(long studyTime, long breakTime, float lightMin, float lightMax,
                        float tempMin, float tempMax, float humidityMin, float humidityMax) {
        this.studyTime = studyTime == Long.MIN_VALUE ? DEFAULT_STUDY_TIME : studyTime;
        this.breakTime = breakTime == Long.MIN_VALUE ? DEFAULT_BREAK_TIME : breakTime;
        this.lightMin = lightMin == Float.MIN_VALUE ? DEFAULT_LIGHT_MIN : lightMin;
        this.lightMax = lightMax == Float.MIN_VALUE ? DEFAULT_LIGHT_MAX : lightMax;
        this.tempMin = tempMin == Float.MIN_VALUE ? DEFAULT_TEMP_MIN : tempMin;
        this.tempMax = tempMax == Float.MIN_VALUE ? DEFAULT_TEMP_MAX : tempMax;
        this.humidityMin = humidityMin == Float.MIN_VALUE ? DEFAULT_HUMIDITY_MIN : humidityMin;
        this.humidityMax = humidityMax == Float.MIN_VALUE ? DEFAULT_HUMIDITY_MAX : humidityMax;;
    }

    /**
     * gets the preferred study time
     * @return preferred study time
     */
    public long getStudyTime(){
        return studyTime;
    }

    /**
     * gets the preferred break time
     * @return preferred break time
     */
    public long getBreakTime() {
        return breakTime;
    }

    /**
     * gets the preferred light minimum value
     * @return light minimum value
     */
    public float getLightMin() {
        return lightMin;
    }

    /**
     * gets the preferred light maximum value
     * @return light maximum value
     */
    public float getLightMax() {
        return lightMax;
    }

    /**
     * gets the preferred temperature minimum value
     * @return temperature minimum value
     */
    public float getTempMin() {
        return tempMin;
    }

    /**
     * gets the preferred temperature maximum value
     * @return temperature maximum value
     */
    public float getTempMax() {
        return tempMax;
    }

    /**
     * gets the preferred humidity minimum value
     * @return humidity minimum value
     */
    public float getHumidityMin() {
        return humidityMin;
    }

    /**
     * gets the preferred humidity maximum value
     * @return humidity maximum value
     */
    public float getHumidityMax() {
        return humidityMax;
    }
}
