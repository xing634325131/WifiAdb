package com.gugutian.wifiadb;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gugutian.wifiadb.util.AdbUtils;
import com.gugutian.wifiadb.util.NetWorkUtils;
import com.gugutian.wifiadb.util.RootUtils;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Button start;
    private Button stop;
    private TextView connet;

    private String IP;
    private int port = 5555;

    private TextView currentIp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();
    }

    private void init() {
        start = (Button) findViewById(R.id.button1);
        stop = (Button) findViewById(R.id.button2);
        connet = (TextView) findViewById(R.id.textView2);
        currentIp = (TextView) findViewById(R.id.textView3);
        IP = NetWorkUtils.getLocalIpAddress(MainActivity.this);
        currentIp.setText(IP);
        currentIp.setTextColor(Color.RED);
        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (!checkRoot()) {
                    return;
                }

                currentIp.setText(IP);
                int rersut = -2;
                if (!NetWorkUtils.checkEnable(MainActivity.this)) {
                    connet.setText("请打开WIFI");
                    return;
                }
                try {
                    rersut = AdbUtils.set(port);
                    connet.setText("adb connet " + IP + ":" + port);
                } catch (Exception e) {
                    connet.setText("出错鸟!!!!!!\n" + e.getMessage());
                    e.printStackTrace();
                }
                System.out.println(rersut);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (!checkRoot()) {
                    return;
                }

                try {
                    AdbUtils.reset();
                    connet.setText("已经关闭网络调试");
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private boolean checkRoot() {
        boolean isRoot = false;
        try {
            isRoot = RootUtils.isDevicesSystemRoot();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!isRoot) {
            Toast.makeText(this, "设备未Root", Toast.LENGTH_LONG).show();
        }
        return isRoot;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}