package com.example.laban;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.service.autofill.FieldClassification;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView tvLaBan;
    ImageView imgLaBan;
    SensorManager sensorManager;
    Sensor acceler,magnetic;
    float[] lastAcceler= new float[3];
    float[] lastMagnetic = new float[3];
    float[] rotation = new float[9];
    float[] orientation = new float[3];

    boolean isLastAcceler = false;
    boolean isMagnetic = false;

    long lastUdateTime = 0;
    float currentDegree = 0f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
    }

    private void setEvent() {
        getWindow().addFlags((WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON));
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        acceler = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetic = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    private void setControl() {
        tvLaBan = findViewById(R.id.tvLaBan);
        imgLaBan = findViewById(R.id.imgLaBan);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor == acceler)
        {
            System.arraycopy(sensorEvent.values,0, lastAcceler,0 ,sensorEvent.values.length);
            isLastAcceler = true;
        }else if(sensorEvent.sensor==magnetic)
        {
            System.arraycopy(sensorEvent.values,0, lastMagnetic,0,sensorEvent.values.length);
            isMagnetic = true;
        }
        if(isLastAcceler && isMagnetic && System.currentTimeMillis() - lastUdateTime>250)
        {
            SensorManager.getRotationMatrix(rotation,null,lastAcceler,lastMagnetic);
            SensorManager.getOrientation(rotation,orientation );

            float azimuntInRadians = orientation[0];
            float azimuntIndegree = (float) Math.toDegrees(azimuntInRadians);

            RotateAnimation rotateAnimation = new RotateAnimation(currentDegree,-azimuntIndegree, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
            rotateAnimation.setDuration(250);
            rotateAnimation.setFillAfter(true);
            imgLaBan.startAnimation(rotateAnimation);
            currentDegree = -azimuntIndegree;
            lastUdateTime = System.currentTimeMillis();

            int x = (int) azimuntIndegree;
            tvLaBan.setText(x+"°");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,acceler,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this,magnetic,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this,acceler);
        sensorManager.unregisterListener(this,magnetic);
    }
}