package cse340.finalproject.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

import cse340.finalproject.R;


public class TimerView extends View {

    private long timeLeft;
    private long studyTime;
    private CountDownTimer timer;
    private long breakTime;
    private boolean currentlyStudying;
    private Button mStartPauseButton;

    private Paint paint;

    public TimerView(Context context, long studyTime, long breakTime) {
        super(context);

        paint = new Paint();
        timeLeft = studyTime;
        this.studyTime = studyTime;
        this.breakTime = breakTime;
        currentlyStudying = true;
    }

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

    private void createCountDownTimer(long timerTime) {
        timer = new CountDownTimer(timerTime, 1000) {
            @Override
            public void onTick(long l) {
                timeLeft = l;
                invalidate();
            }

            @Override
            public void onFinish() {
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

    public void setStartPauseButton(Button button) {
        mStartPauseButton = button;
    }

    public boolean getCurrentlyStudying() {
        return currentlyStudying;
    }

    public void pauseTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(width, height);
    }

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
