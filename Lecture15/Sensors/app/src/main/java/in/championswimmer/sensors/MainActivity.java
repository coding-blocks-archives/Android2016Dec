package in.championswimmer.sensors;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    public static final String TAG = "SENSORS";


    SensorManager sensMan;
    RelativeLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = (RelativeLayout) findViewById(R.id.activity_main);


        sensMan = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> mySensors = sensMan.getSensorList(Sensor.TYPE_ALL);

        for (Sensor sensor : mySensors) {
            Log.d(TAG, "onCreate: name = " + sensor.getName());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
                Log.d(TAG, "onCreate: type = " + sensor.getStringType());
            }
            Log.d(TAG, "onCreate: vendor = " + sensor.getVendor());
            Log.d(TAG, "onCreate: version = " + sensor.getVersion());
            Log.d(TAG, "onCreate: ===========================================");
        }

        Sensor accelSensor = sensMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Sensor proxSensor = sensMan.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        sensMan.registerListener(
                this,
                accelSensor,
                1000 * 1000
        );
        sensMan.registerListener(
                this,
                proxSensor,
                10 * 1000
        );
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensMan.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            Log.d(TAG, "onSensorChanged: LIGHT + " + event.values[0]);
            if (event.values[0] > 5) {

                mainLayout.setBackgroundColor(Color.WHITE);

            } else {
                mainLayout.setBackgroundColor(Color.BLACK);

            }
        }

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            Log.d(TAG, "onSensorChanged: ACCEL");
            int myColor = Color.rgb(
                    accelToCol(event.values[0]),
                    accelToCol(event.values[1]),
                    accelToCol(event.values[2])
            );
            mainLayout.setBackgroundColor(myColor);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    int accelToCol (float accel) {
        accel = accel + 12;
        return (int) ((accel / 24) * 255);
    }

}
