package cse340.finalproject;

public class UserSettings {

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

    public long getStudyTime(){
        return studyTime;
    }

    public long getBreakTime() {
        return breakTime;
    }

    public void setStudyTime(long studyTime) {
        this.studyTime = studyTime;
    }

    public void setBreakTime(long breakTime) {
        this.breakTime = breakTime;
    }

    public float getLightMin() {
        return lightMin;
    }

    public float getLightMax() {
        return lightMax;
    }

    public float getTempMin() {
        return tempMin;
    }

    public float getTempMax() {
        return tempMax;
    }

    public float getHumidityMin() {
        return humidityMin;
    }

    public float getHumidityMax() {
        return humidityMax;
    }

    public void setLightMin(int lightMin) {
        this.lightMin = lightMin;
    }

    public void setLightMax(int lightMax) {
        this.lightMax = lightMax;
    }

    public void setTempMin(int tempMin) {
        this.tempMin = tempMin;
    }

    public void setTempMax(int tempMax) {
        this.tempMax = tempMax;
    }

    public void setHumidityMin(float humidityMin) {
        this.humidityMin = humidityMin;
    }

    public void setHumidityMax(float humidityMax) {
        this.humidityMax = humidityMax;
    }
}
