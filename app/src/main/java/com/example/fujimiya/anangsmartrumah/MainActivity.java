package com.example.fujimiya.anangsmartrumah;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    String clientId;
    MqttAndroidClient client;
    TextView suhu,kelembapan;
    String SL1T = "mati",SL2D = "mati",SL3KT = "mati",SL4RT = "mati",SL5KM="mati";
    String L1T,L2D,L3KT,L4RT,L5KM = "off";
    ImageView lampu_ruang_tamu,lampu_kamar_mandi,lampu_kamar_tidur,lampu_dapur,lampu_teras;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lampu_ruang_tamu = (ImageView) findViewById(R.id.lampu_ruang_tamu);
        lampu_kamar_mandi = (ImageView) findViewById(R.id.lampu_kamar_mandi);
        lampu_kamar_tidur = (ImageView) findViewById(R.id.lampu_kamartidur);
        lampu_dapur = (ImageView) findViewById(R.id.lampu_dapur);
        lampu_teras = (ImageView) findViewById(R.id.lampu_teras);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        cek();
//                        cek();
//                        cek();
                        finish();
                        startActivity(getIntent());
                        swipeRefreshLayout.setRefreshing(false);

                    }
                }, 2000);
            }
        });

        sambung();

        lampu_kamar_mandi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String payload = "the payload";
                try {
                    if (SL5KM.equals("hidup")) {
                        payload = "off";
                        lampu_kamar_mandi.setImageResource(R.drawable.lamp_luar_off);
                        //Toast.makeText(this, "Status : " + payload, Toast.LENGTH_LONG).show();
                    }
                    if (SL5KM.equals("mati")) {
                        lampu_kamar_mandi.setImageResource(R.drawable.lamp_luar_on);
                        payload = "on";
                    }

                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Error : " + e.toString(), Toast.LENGTH_LONG).show();
                }
                String topic = "L5KM";
                byte[] encodedPayload = new byte[0];
                try {
                    encodedPayload = payload.getBytes("UTF-8");
                    MqttMessage message = new MqttMessage(encodedPayload);
                    client.publish(topic, message);
                } catch (UnsupportedEncodingException | MqttException e) {
                    e.printStackTrace();
                }
            }
        });

        lampu_teras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String payload = "the payload Teras";
                //Toast.makeText(MainActivity.this, "Status : " + payload, Toast.LENGTH_LONG).show();
                try {
                    if (SL1T.equals("hidup")) {
                        payload = "off";
                        lampu_teras.setImageResource(R.drawable.lamp_luar_off);
                        //Toast.makeText(this, "Status : " + payload, Toast.LENGTH_LONG).show();
                        //Toast.makeText(MainActivity.this, "Status : " + payload, Toast.LENGTH_LONG).show();
                    }
                    if (SL1T.equals("mati")) {
                        lampu_teras.setImageResource(R.drawable.lamp_luar_on);
                        payload = "on";
                        //Toast.makeText(MainActivity.this, "Status : " + payload, Toast.LENGTH_LONG).show();
//                Toast.makeText(this, "Status : " + payload, Toast.LENGTH_LONG).show();
                    }

                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Error : " + e.toString(), Toast.LENGTH_LONG).show();
                }
                String topic = "L1T";
                byte[] encodedPayload = new byte[0];
                try {
                    encodedPayload = payload.getBytes("UTF-8");
                    MqttMessage message = new MqttMessage(encodedPayload);
                    client.publish(topic, message);
                } catch (UnsupportedEncodingException | MqttException e) {
                    e.printStackTrace();
                }
            }
        });

        lampu_dapur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String payload = "the payload";
                try {
                    if (SL2D.equals("hidup")) {
                        payload = "off";
                        lampu_dapur.setImageResource(R.drawable.lamp_luar_off);
                        //Toast.makeText(this, "Status : " + payload, Toast.LENGTH_LONG).show();
                    }
                    if (SL2D.equals("mati")) {
                        lampu_dapur.setImageResource(R.drawable.lamp_luar_on);
                        payload = "on";
                    }

                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Error : " + e.toString(), Toast.LENGTH_LONG).show();
                }
                String topic = "L2D";
                byte[] encodedPayload = new byte[0];
                try {
                    encodedPayload = payload.getBytes("UTF-8");
                    MqttMessage message = new MqttMessage(encodedPayload);
                    client.publish(topic, message);
                } catch (UnsupportedEncodingException | MqttException e) {
                    e.printStackTrace();
                }
            }
        });

        lampu_ruang_tamu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String payload = "the payload";
                try {
                    if (SL4RT.equals("hidup")) {
                        payload = "off";
                        lampu_ruang_tamu.setImageResource(R.drawable.lamp_luar_off);
                        //Toast.makeText(this, "Status : " + payload, Toast.LENGTH_LONG).show();
                    }
                    if (SL4RT.equals("mati")) {
                        lampu_ruang_tamu.setImageResource(R.drawable.lamp_luar_on);
                        payload = "on";
                    }

                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Error : " + e.toString(), Toast.LENGTH_LONG).show();
                }
                String topic = "L4RT";
                byte[] encodedPayload = new byte[0];
                try {
                    encodedPayload = payload.getBytes("UTF-8");
                    MqttMessage message = new MqttMessage(encodedPayload);
                    client.publish(topic, message);
                } catch (UnsupportedEncodingException | MqttException e) {
                    e.printStackTrace();
                }
            }
        });

        lampu_kamar_tidur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String payload = "the payload";
                try {
                    if (SL3KT.equals("hidup")) {
                        payload = "off";
                        lampu_kamar_tidur.setImageResource(R.drawable.lamp_luar_off);
                        //Toast.makeText(this, "Status : " + payload, Toast.LENGTH_LONG).show();
                    }
                    if (SL3KT.equals("mati")) {
                        lampu_kamar_tidur.setImageResource(R.drawable.lamp_luar_on);
                        payload = "on";
                    }

                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Error : " + e.toString(), Toast.LENGTH_LONG).show();
                }
                String topic = "L3KT";
                byte[] encodedPayload = new byte[0];
                try {
                    encodedPayload = payload.getBytes("UTF-8");
                    MqttMessage message = new MqttMessage(encodedPayload);
                    client.publish(topic, message);
                } catch (UnsupportedEncodingException | MqttException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    public  void sambung(){
        //"tcp://192.168.43.39:1883"
        clientId = MqttClient.generateClientId();
        client =
                new MqttAndroidClient(this.getApplicationContext(), "tcp://test.mosquitto.org:1883",
                        clientId);
        try {
            MqttConnectOptions options = new MqttConnectOptions();
            options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1);
//            options.setUserName("fujimiya");
//            options.setPassword("123".toCharArray());
            IMqttToken token = client.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                    //Log.d(TAG, "onSuccess");
                    Toast.makeText(MainActivity.this,"Terhubung",Toast.LENGTH_SHORT).show();
                    cek();
                    cek();
                    cek();
                    String topic = "test";
                    int qos = 1;
                    try {
                        IMqttToken subToken = client.subscribe(topic, qos);
                        subToken.setActionCallback(new IMqttActionListener() {
                            @Override
                            public void onSuccess(IMqttToken asyncActionToken) {
                                // The message was published
                            }

                            @Override
                            public void onFailure(IMqttToken asyncActionToken,
                                                  Throwable exception) {
                                // The subscription could not be performed, maybe the user was not
                                // authorized to subscribe on the specified topic e.g. using wildcards

                            }
                        });
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                    try {
                        client.subscribe("status_L1T",qos);
                        client.subscribe("status_L2D",qos);
                        client.subscribe("status_L3KT",qos);
                        client.subscribe("status_L4RT",qos);
                        client.subscribe("status_L5KM",qos);

                        client.setCallback(new MqttCallback() {
                            @Override
                            public void connectionLost(Throwable cause) {

                            }

                            @Override
                            public void messageArrived(String topic, MqttMessage message) throws Exception {
                                //Toast.makeText(MainActivity.this,"topic : "+topic+" message : "+message.toString(),Toast.LENGTH_SHORT).show();
                                //setMessageNotification(topic,message.toString());

                                if(topic.equals("status_L4RT")){
                                    SL4RT = ""+message;
                                    if(SL4RT.equals("hidup")){
                                        lampu_ruang_tamu.setImageResource(R.drawable.lamp_luar_on);
                                    }
                                    if(SL4RT.equals("mati")){
                                        lampu_ruang_tamu.setImageResource(R.drawable.lamp_luar_off);
                                    }
                                }
                                if(topic.equals("status_L2D")){
                                    SL2D = ""+message;
                                    if(SL2D.equals("hidup")){
                                        lampu_dapur.setImageResource(R.drawable.lamp_luar_on);
                                    }
                                    if(SL2D.equals("mati")){
                                        lampu_dapur.setImageResource(R.drawable.lamp_luar_off);
                                    }

                                }
                                if(topic.equals("status_L5KM")){
                                    SL5KM = ""+message;
                                    if(SL5KM.equals("hidup")){
                                        lampu_kamar_mandi.setImageResource(R.drawable.lamp_luar_on);
                                    }
                                    if(SL5KM.equals("mati")){
                                        lampu_kamar_mandi.setImageResource(R.drawable.lamp_luar_off);
                                    }

                                }
                                if(topic.equals("status_L1T")){
                                    SL1T = ""+message;
                                    if(SL1T.equals("hidup")){
                                        lampu_teras.setImageResource(R.drawable.lamp_luar_on);
                                    }
                                    if(SL1T.equals("mati")){
                                        lampu_teras.setImageResource(R.drawable.lamp_luar_off);
                                    }
                                }
                                if(topic.equals("status_L3KT")){
                                    SL3KT = ""+message;
                                    if(SL3KT.equals("hidup")){
                                        lampu_kamar_tidur.setImageResource(R.drawable.lamp_luar_on);
                                    }
                                    if(SL3KT.equals("mati")){
                                        lampu_kamar_tidur.setImageResource(R.drawable.lamp_luar_off);
                                    }
                                }
                            }

                            @Override
                            public void deliveryComplete(IMqttDeliveryToken token) {

                            }
                        });
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    //Log.d(TAG, "onFailure");
                    Toast.makeText(MainActivity.this,"Tidak Terhubung",Toast.LENGTH_LONG).show();
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }


    }
    public void cek(){
        String topic = "cek";
        String payload = "Periksa Kondisi";
        byte[] encodedPayload = new byte[0];
        try {
            encodedPayload = payload.getBytes("UTF-8");
            MqttMessage message = new MqttMessage(encodedPayload);
            client.publish(topic, message);
        } catch (UnsupportedEncodingException | MqttException e) {
            e.printStackTrace();
        }
    }


}
