package com.example.touchdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private LinearLayout linearlayout;
    private TextView tv_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
    }

    private void findViews() {
        tv_message = (TextView) findViewById(R.id.tv_message);
        linearlayout = (LinearLayout) findViewById(R.id.linearlayout);
        linearlayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                StringBuilder sb = new StringBuilder();

                sb.append(String.format("first pointer: (%.1f,%.1f)%n", motionEvent.getX(), motionEvent.getY()));

                sb.append("touch state: ");
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        sb.append("ACTION_DOWN\n");
                        break;

                    case MotionEvent.ACTION_MOVE:
                        sb.append("ACTION_MOVE\n");
                        break;

                    case MotionEvent.ACTION_UP:
                        sb.append("ACTION_UP\n");
                        break;

                    default:
                        sb.append("Duplicate touch state\n");
                        break;
                }

                int pointerCount = motionEvent.getPointerCount();
                sb.append(String.format("pointerCount: %d %n", pointerCount));

                //使用迴圈取得各點的ID與座標
                for (int i = 0; i < pointerCount; i++) {

                    //getX().getY()有加入參數
                    sb.append(String.format("pointer %d: (%.1f,%.1f)%n", motionEvent.getPointerId(i), motionEvent.getX(i), motionEvent.getY(i)));
                }

                tv_message.setText(sb);

                return true; //true才能同時顯示多點觸控，false只能顯示單點觸控.
            }
        });
    }
}