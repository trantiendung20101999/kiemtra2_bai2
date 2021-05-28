package com.example.trantiendung_ktra2_bai2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edName;
    TextView txtDate,txtTime;
    Button btnDate , btnTime,btnAdd;
    CheckBox cb;
    LichThiRevAdapter adapter;
    SqliteCalenderHelper sqliteCalenderHelper;
    RecyclerView rev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edName = findViewById(R.id.edName);
        txtDate= findViewById(R.id.txtDate);
        TextView txtTitle= findViewById(R.id.txtTitle);
        txtTime=findViewById(R.id.txtTime);
        btnDate = findViewById(R.id.btnDate);
        btnTime = findViewById(R.id.btnTime);
        btnAdd=findViewById(R.id.btnAdd);
        cb =findViewById(R.id.cb);
        adapter = new LichThiRevAdapter();
        rev = findViewById(R.id.rev);
        sqliteCalenderHelper = new SqliteCalenderHelper(this);
        List<LichThi> list = sqliteCalenderHelper.getAll();
        int count =0;
        for(int i=0;i<list.size();i++)
        {
            if(list.get(i).getType().equalsIgnoreCase("viet"))
            {
                count++;
            }
        }
        txtTitle.setText("So bai thi viet: "+count);
        adapter.setList(list);
        rev.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rev.setAdapter(adapter);

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int mDay = calendar.get(Calendar.DAY_OF_MONTH);
                int mMonth = calendar.get(Calendar.MONTH);
                int mYear = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                        MainActivity.this,
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
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                LichThi lichThi = new LichThi(name,date,time,type);
                Log.e("add",sqliteCalenderHelper.addOrder(lichThi)+"");
                List<LichThi> list = sqliteCalenderHelper.getAll();
                Log.e("ss",list.size()+"");
                adapter.setList(list);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        MenuItem item = menu.findItem(R.id.mSearch);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<LichThi> list = sqliteCalenderHelper.searchByName(newText);
                adapter.setList(list);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}