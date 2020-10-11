package com.example.week7lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "MainActivity";

    //Values of sensors
    private TextView accXtv, accYtv, accZtv;
    private TextView gyrXtv, gyrYtv, gyrZtv;
    private TextView graXtv, graYtv, graZtv;
    private TextView ligtv, temptv, proxtv;

    private SensorManager sensorManager;
    private Sensor sAcc, sGyr, sProx, sGra, sTem = null;

    private DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accXtv = findViewById(R.id.accXTextView);
        accYtv = findViewById(R.id.accYTextView);
        accZtv = findViewById(R.id.accZTextView);

        gyrXtv = findViewById(R.id.gyroXTextView);
        gyrYtv = findViewById(R.id.gyroYTextView);
        gyrZtv = findViewById(R.id.gyroZTextView);

        graXtv = findViewById(R.id.gravityXTextView);
        graYtv = findViewById(R.id.gravityYTextView);
        graZtv = findViewById(R.id.gravityZTextView);

        ligtv = findViewById(R.id.lightVtextView);
        temptv = findViewById(R.id.temparatureVTextView);
        proxtv = findViewById(R.id.proximityVTextView);

        setupSensors();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w(TAG, "on Resume Coming...");
        if(sAcc != null)
        {
            sensorManager.registerListener(this, sAcc, SensorManager.SENSOR_DELAY_NORMAL);
            Log.w(TAG, "on Resume: Accelerometer assigning");
        }
        if(sGyr != null)
        {
            sensorManager.registerListener(this, sGyr, SensorManager.SENSOR_DELAY_NORMAL);
            Log.w(TAG, "on Resume: GyroScope assigning");
        }
        if(sTem != null) {
            sensorManager.registerListener(this, sTem, SensorManager.SENSOR_DELAY_NORMAL);
            Log.w(TAG, "on Resume: Temperature/Light assigning");
        }
        if(sGra != null) {
            sensorManager.registerListener(this, sGra, SensorManager.SENSOR_DELAY_NORMAL);
            Log.w(TAG, "on Resume: Gravity assigning");
        }
        if(sProx != null) {
            sensorManager.registerListener(this, sProx, SensorManager.SENSOR_DELAY_NORMAL);
            Log.w(TAG, "on Resume: Proximity assigning");
        }
    }

    private void setupSensors()
    {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //accelometer
        if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null)
        {
            Log.w(TAG,"Accelerometer supported");
            sAcc = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
        else
        {
            Toast.makeText(this,"Accelerometer not supported", Toast.LENGTH_SHORT).show();
            Log.w(TAG,"Accelerometer not supported");
        }

        //gyroscope
        if(sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) != null)
        {
            Log.w(TAG,"Gyroscope supported");
            sGyr = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        }
        else
        {
            Toast.makeText(this,"Gyroscope not supported", Toast.LENGTH_SHORT).show();
            Log.w(TAG,"Gyroscope not supported");
        }

        //temperature
        if(sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null)
        {
            Log.w(TAG,"Temperature supported");
            sTem = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        }
        else
        {
            sTem = null;
            Toast.makeText(this,"Temperature not supported", Toast.LENGTH_SHORT).show();
            Log.w(TAG,"Temperature not supported");
        }

        //Light
        if(sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null)
        {
            Log.w(TAG,"Light supported");
            sTem = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        }
        else
        {
            Toast.makeText(this,"Light not supported", Toast.LENGTH_SHORT).show();
            Log.w(TAG,"Light not supported");
        }

        //Gravity
        if(sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY) != null)
        {
            Log.w(TAG,"Gravity supported");
            sGra = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        }
        else
        {
            Toast.makeText(this,"Gravity not supported", Toast.LENGTH_SHORT).show();
            Log.w(TAG,"Gravity not supported");
        }

        //Proximity
        if(sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) != null)
        {
            Log.w(TAG,"Proximity supported");
            sProx = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        }
        else
        {
            Toast.makeText(this,"Proximity not supported", Toast.LENGTH_SHORT).show();
            Log.w(TAG,"Proximity not supported");
        }
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        String strX = "", strY = "", strZ = "", str = "";

        switch (event.sensor.getType())
        {
            case Sensor.TYPE_ACCELEROMETER:
                strX = "X: "+ df.format(event.values[0]);
                strY = "Y: "+ df.format(event.values[1]);
                strZ = "Z: "+ df.format(event.values[2]);
                //Log.w(TAG,"ACCELOROMETER: "+strX+" "+strY+" "+strZ);
                accXtv.setText(strX);
                accYtv.setText(strY);
                accZtv.setText(strZ);
                break;
            case Sensor.TYPE_GYROSCOPE:
                strX = "X: "+ df.format(event.values[0]);
                strY = "Y: "+ df.format(event.values[1]);
                strZ = "Z: "+ df.format(event.values[2]);
                //Log.w(TAG,"GYRO: "+strX+" "+strY+" "+strZ);
                gyrXtv.setText(strX);
                gyrYtv.setText(strY);
                gyrZtv.setText(strZ);
                break;
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                str = df.format(event.values[0]) + " C";
                Log.w(TAG,"TEMPERATURE: "+str);
                temptv.setText(str);
                break;
            case Sensor.TYPE_LIGHT:
                str = df.format(event.values[0]) + " LUX";
                //Log.w(TAG,"LIGHT: "+str);
                ligtv.setText(str);
                break;
            case Sensor.TYPE_GRAVITY:
                strX = "X: "+ df.format(event.values[0]);
                strY = "Y: "+ df.format(event.values[1]);
                strZ = "Z: "+ df.format(event.values[2]);
                //Log.w(TAG,"GRAVITY: "+strX+" "+strY+" "+strZ);
                graXtv.setText(strX);
                graYtv.setText(strY);
                graZtv.setText(strZ);
                break;
            case Sensor.TYPE_PROXIMITY:
                str = df.format(event.values[0]);
                //Log.w(TAG,"Proximity: "+str);
                proxtv.setText(str);
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}