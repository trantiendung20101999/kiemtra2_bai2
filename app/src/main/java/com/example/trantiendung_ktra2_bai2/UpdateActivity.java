package com.example.trantiendung_ktra2_bai2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class UpdateActivity extends AppCompatActivity {

    EditText edName,edId;
    TextView txtDate,txtTime;
    Button btnDate , btnTime,btnDel,btnUpdate;
    CheckBox cb;
    SqliteCalenderHelper sqliteCalenderHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        edName = findViewById(R.id.edName);
        txtDate= findViewById(R.id.txtDate);
        txtTime=findViewById(R.id.txtTime);
        btnDate = findViewById(R.id.btnDate);
        btnTime = findViewById(R.id.btnTime);
        edId = findViewById(R.id.edId);
        btnDel = findViewById(R.id.btnDell);
        btnUpdate=findViewById(R.id.btnUpdate);
        cb =findViewById(R.id.cb);
        sqliteCalenderHelper = new SqliteCalenderHelper(this);
        Intent intent=getIntent();
        LichThi lichThi = (LichThi) intent.getSerializableExtra("lichthi");
        edId.setText(lichThi.getId()+"");
        edName.setText(lichThi.getTenmonhoc());
        txtDate.setText(lichThi.getDate());
        txtTime.setText(lichThi.getTime());
        String[] split = lichThi.getDate().split("/");
        int day = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int year = Integer.parseInt(split[2]);
        if(year>2021)
        {
            btnDel.setEnabled(false);
        }
        if(year == 2021 && month > 5)
        {
            btnDel.setEnabled(false);
        }
        if(year == 2021 && month == 5 && day > 28)
        {
            btnDel.setEnabled(false);
        }
        if(lichThi.getType().equalsIgnoreCase("viet"))
        {
            cb.setChecked(true);
        }

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int mDay = calendar.get(Calendar.DAY_OF_MONTH);
                int mMonth = calendar.get(Calendar.MONTH);
                int mYear = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txtDate.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },mYear,mMonth,mDay);
                datePickerDialog.show();
            }
        });
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                int mHour = calendar.get(Calendar.HOUR);
                int mMinute = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        UpdateActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                txtTime.setText(hourOfDay+":"+minute);
                            }
                        },
                        mHour,mMinute,false
                );
                timePickerDialog.show();

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(edId.getText().toString());
                String name = edName.getText().toString();
                String date = txtDate.getText().toString();
                String time = txtTime.getText().toString();
                String type;
                if(cb.isChecked()==true)
                {
                    type = "viet";
                }
                else
                {
                    type="khong";
                }
                LichThi lichThi1 = new LichThi(id,name,date,time,type);
                sqliteCalenderHelper.update(lichThi1);


                Intent intent = new Intent(UpdateActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = Integer.parseInt(edId.getText().toString());
                sqliteCalenderHelper.delete(id);
                Intent intent = new Intent(UpdateActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}