package cse340.finalproject.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

import cse340.finalproject.R;

/**
 * View for a study timer that alternates between a studying time and a break time. Includes a box
 * surrounding the displayed time
 */
public class TimerView extends View {

    private long timeLeft;
    private long studyTime;
    private CountDownTimer timer;
    private long breakTime;
    private boolean currentlyStudying;
    private Button mStartPauseButton;

    private Paint paint;

    /**
     * Creates a timerview object with the speciffied study time and break time
     * @param context app context
     * @param studyTime study time in milliseconds
     * @param breakTime break time in milliseconds
     */
    public TimerView(Context context, long studyTime, long breakTime) {
        super(context);

        paint = new Paint();
        timeLeft = studyTime;
        this.studyTime = studyTime;
        this.breakTime = breakTime;
        currentlyStudying = true;
    }

    /**
     * Starts the timer to begin counting down
     */
    public void startTimer() {
        long timerTime;
        if (currentlyStudying) {
            timerTime = studyTime;
        } else {
            timerTime = breakTime;
        }
        createCountDownTimer(timerTime);
        timer.start();
    }

    /**
     * creates a count down timer with a specified time to start at
     * @param timerTime the time the timer should start counting down from
     */
    private void createCountDownTimer(long timerTime) {
        // source: https://developer.android.com/reference/android/os/CountDownTimer
        timer = new CountDownTimer(timerTime, 1000) {
            @Override
            public void onTick(long l) {
                timeLeft = l;
                invalidate();
            }

            @Override
            public void onFinish() {
                //  Switch between study time and break time when the timer finishes
                if (currentlyStudying) {
                    currentlyStudying = false;
                    createCountDownTimer(breakTime);
                    timeLeft = breakTime;
                } else {
                    currentlyStudying = true;
                    createCountDownTimer(studyTime);
                    timeLeft = studyTime;
                }
                mStartPauseButton.callOnClick();
                pauseTimer();
                invalidate();
            }
        };
    }

    /**
     * Sets the button that controls the starting and pausing of this timer
     * @param button the button that is the start pause button
     */
    public void setStartPauseButton(Button button) {
        mStartPauseButton = button;
    }

    /**
     * Returns whether or not the timer is in a studying state or a break state
     * @return true if the timer is in a studying state and false if not
     */
    public boolean getCurrentlyStudying() {
        return currentlyStudying;
    }

    /**
     * Pauses the timer
     */
    public void pauseTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    /**
     * callback to set the dimensions of the timerview
     * @param widthMeasureSpec width measure
     * @param heightMeasureSpec height measure
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(width, height);
    }

    /**
     * Callback to actually draw the timer on the canvas
     * @param canvas canvas to draw the timer on
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Resources resources = getResources();

        //  Set paint for the time text
        paint.setTextSize(resources.getDimensionPixelSize(R.dimen.timerTextSize));
        paint.setColor(resources.getColor(R.color.colorPrimaryDark));
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(20);

        int secondsLeft = (int) timeLeft / 1000;
        int minutesLeft = 0;
        if (secondsLeft >= 60) {
            minutesLeft = secondsLeft / 60;
            secondsLeft = secondsLeft % 60;
        }

        // Draw the countdown clock
        canvas.drawText(String.format("%02d", minutesLeft) + ":" +
                String.format("%02d", secondsLeft), getWidth() / 2, getHeight() / 2, paint);

        //  set paint for box
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(resources.getColor(R.color.black));
        // Draw the surrounding box
        canvas.drawRect(getWidth() / 6,getHeight() / 3,getWidth() / 6 * 5
                ,getHeight() / 3 * 2, paint);
    }

}
