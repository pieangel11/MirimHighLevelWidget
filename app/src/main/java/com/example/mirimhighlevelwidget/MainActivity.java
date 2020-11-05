package com.example.mirimhighlevelwidget;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    RadioButton radioDate, radioTime;
    DatePicker datePick;
    TimePicker timePick;
    Chronometer chrono;
    TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        radioDate = findViewById(R.id.radio_date);
        radioTime = findViewById(R.id.radio_time);
        radioDate.setOnClickListener(radioListener);
        radioTime.setOnClickListener(radioListener);
        datePick = findViewById(R.id.date_pick);
        timePick = findViewById(R.id.time_pick);
        chrono = findViewById(R.id.chrono1);
        textResult = findViewById(R.id.text_result);
        chrono.setOnClickListener(chronoListenr);
        textResult.setOnLongClickListener(textListener);
        radioDate.setVisibility(View.INVISIBLE);
        radioTime.setVisibility(View.INVISIBLE);
        datePick.setVisibility(View.INVISIBLE);
        timePick.setVisibility(View.INVISIBLE);
    }
    View.OnClickListener radioListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.radio_date:
                    datePick.setVisibility(View.VISIBLE);
                    timePick.setVisibility(View.INVISIBLE);
                    break;
                case R.id.radio_time:
                    datePick.setVisibility(View.INVISIBLE);
                    timePick.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };
    View.OnClickListener chronoListenr = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            chrono.setBase(SystemClock.elapsedRealtime()); // avd에 있는 시간을 이용하여 timer를 시작한다.
            chrono.start();
            chrono.setTextColor(Color.RED);
            radioDate.setVisibility(View.VISIBLE);
            radioTime.setVisibility(View.VISIBLE);
        }
    };

    View.OnLongClickListener textListener = new View.OnLongClickListener() { // 해당객체를 오래 누르면 호출됨
        @Override
        public boolean onLongClick(View v) {
            chrono.stop();
            chrono.setTextColor(Color.BLUE);
            textResult.setText(datePick.getYear() + "년 " + (datePick.getMonth() + 1) + "월 " + datePick.getDayOfMonth() + "일"
                    + timePick.getCurrentHour() + "시 " + timePick.getCurrentMinute() + "분");

            radioDate.setVisibility(View.INVISIBLE);
            radioTime.setVisibility(View.INVISIBLE);
            datePick.setVisibility(View.INVISIBLE);
            timePick.setVisibility(View.INVISIBLE);
            return false;   // 기본 행동 취소
        }
    };
}