package com.dateDialog;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lxs.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class A_DateDialog extends AppCompatActivity implements View.OnClickListener {
    private Button Date_btn,Time_btn,getDate_btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datedialog_layout);
        Date_btn=findViewById(R.id.date_btn);
        Date_btn.setOnClickListener(this);
        Time_btn=findViewById(R.id.Time_btn);
        Time_btn.setOnClickListener(this);
        getDate_btn=findViewById(R.id.getDate_btn);
        getDate_btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.date_btn:
                Calendar date=Calendar.getInstance();
                DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String y= String.valueOf(year);
                        String m=String.valueOf(month);
                        String d=String.valueOf(dayOfMonth);
                        if (month<10){
                            m="0"+month;
                        }
                        if (dayOfMonth<10){
                            d="0"+dayOfMonth;
                        }
                        Date_btn.setText(year+"-"+m+"-"+d);
                        Toast.makeText(A_DateDialog.this, year+"-"+m+"-"+d, Toast.LENGTH_SHORT).show();
                    }
                },date.get(Calendar.YEAR),date.get(Calendar.MONTH),date.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
//                PopupWindow window=new PopupWindow(v,GridLayout.LayoutParams.WRAP_CONTENT,GridLayout.LayoutParams.WRAP_CONTENT,true);
//                window.showAtLocation(Date_btn,GridLayout.LayoutParams.MATCH_PARENT,0,0);
                break;
            case R.id.Time_btn:
                Calendar Time=Calendar.getInstance();
                TimePickerDialog timePickerDialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String a= String.valueOf(hourOfDay);
                        String m= String.valueOf(minute);
                        if (hourOfDay<10){
                            a="0"+hourOfDay;
                        }
                        if (minute<10){
                            m="0"+minute;
                        }
                        Time_btn.setText(a+"-"+m);
                    }
                },Time.get(Calendar.HOUR_OF_DAY),Time.get(Calendar.MINUTE),true);
                timePickerDialog.show();
                break;
            case R.id.getDate_btn:
                getDate_btn.setText(new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()));
                break;
        }
    }
}
