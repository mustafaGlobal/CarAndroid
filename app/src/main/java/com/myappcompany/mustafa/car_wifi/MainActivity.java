package com.myappcompany.mustafa.car_wifi;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public class MainActivity extends AppCompatActivity {
    MqttHelper mqttHelper;
    TextView dataReceived;
    Button forwardButton;
    Button reverseButton;
    Button rightButton;
    Button leftButton;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initilizeLayoutElements();
        initilazeTouchListeners();
        startMqtt();
    }


    private  void initilizeLayoutElements() {
        dataReceived = (TextView) findViewById(R.id.dataRecived);
        forwardButton = (Button) findViewById(R.id.buttonForward);
        reverseButton = (Button) findViewById(R.id.buttonReverse);
        rightButton = (Button) findViewById(R.id.buttonRight);
        leftButton = (Button) findViewById(R.id.buttonLeft);
    }

    private void  initilazeTouchListeners()
    {
        forwardClick();
        reverseClick();
        rightClick();
        leftClick();
    }

    public void forwardClick() {
        forwardButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionevent) {
                int action = motionevent.getAction();
                if (action == MotionEvent.ACTION_DOWN) {
                    mqttHelper.sendMessage("esp/state","1");
                }
                else if (action == MotionEvent.ACTION_UP){
                    mqttHelper.sendMessage("esp/state", "0");
                }
                return false;
            }
        });
    }

    public void reverseClick() {
        reverseButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionevent) {
                int action = motionevent.getAction();
                if (action == MotionEvent.ACTION_DOWN) {
                    mqttHelper.sendMessage("esp/state","2");
                }
                else if (action == MotionEvent.ACTION_UP){
                    mqttHelper.sendMessage("esp/state", "0");
                }
                return false;
            }
        });
    }

    public void rightClick() {
        rightButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionevent) {
                int action = motionevent.getAction();
                if (action == MotionEvent.ACTION_DOWN) {
                    mqttHelper.sendMessage("esp/state","3");
                }
                else if (action == MotionEvent.ACTION_UP){
                    mqttHelper.sendMessage("esp/state", "0");
                }
                return false;
            }
        });
    }

    public void leftClick() {
        leftButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionevent) {
                int action = motionevent.getAction();
                if (action == MotionEvent.ACTION_DOWN) {
                    mqttHelper.sendMessage("esp/state","4");
                }
                else if (action == MotionEvent.ACTION_UP){
                    mqttHelper.sendMessage("esp/state", "0");
                }
                return false;
            }
        });
    }


    private void startMqtt() {
        mqttHelper = new MqttHelper(getApplicationContext());
        mqttHelper.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {

            }

            @Override
            public void connectionLost(Throwable throwable) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                Log.w("Debug", mqttMessage.toString());
                dataReceived.setText(mqttMessage.toString());
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });
    }
}







