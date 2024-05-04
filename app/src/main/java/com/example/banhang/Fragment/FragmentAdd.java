package com.example.banhang.Fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.banhang.R;

import java.util.Calendar;

public class FragmentAdd extends Fragment {
    EditText editNote;
    Switch switchNote;
    Button btnTimeStart, btnTimeEnds,btnDateEnds,btnDateStart;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        anhxa(view);

        switchNote.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    editNote.setVisibility(View.VISIBLE);
                }else{
                    editNote.setVisibility(View.GONE);
                }
            }
        });
        getDate(btnDateStart);
        getDate(btnDateEnds);
        getTime(btnTimeStart);
        getTime(btnTimeEnds);
        // Inflate the layout for this fragment
        return view;
    }

    public void anhxa(View view){
        switchNote = (Switch) view.findViewById(R.id.switchNote);
        editNote = (EditText) view.findViewById(R.id.editNote);
        btnTimeEnds = (Button) view.findViewById(R.id.btnTimeEnds);
        btnTimeStart = (Button) view.findViewById(R.id.btnTimeStart);
        btnDateEnds = (Button) view.findViewById(R.id.btnDateEnds);
        btnDateStart = (Button) view.findViewById(R.id.btnDateStart);
    }

    public void getDate(Button btn){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Tạo biến calendar để lấy ngày tháng năm
                Calendar calendar = Calendar.getInstance();

                int day = calendar.get(Calendar.DAY_OF_MONTH)+ 1;
                int month = calendar.get(Calendar.MONTH)+ 1;
                int year = calendar.get(Calendar.YEAR);

                //Tạo 1 DatePickerDialog để chọn ngày tháng năm
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        btn.setText(String.valueOf(dayOfMonth)+"/"+String.valueOf(month)+"/"+String.valueOf(year));
                    }
                }, year, month,day);

                //Tạo xong rồi thi show ra
                datePickerDialog.show();
            }
        });

    }

    public void getTime(Button btn){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Tạo biến calendar để lấy ngày tháng năm
                Calendar calendar = Calendar.getInstance();

                int hour = calendar.get(Calendar.HOUR);
                int minute = calendar.get(Calendar.MINUTE);

                Toast.makeText(getActivity(), String.valueOf(hour), Toast.LENGTH_SHORT).show();
                //Tạo 1 DatePickerDialog để chọn ngày tháng năm
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        btn.setText(String.valueOf(hourOfDay)+":"+String.valueOf(minute));
                    }
                }, hour, minute,true);

                timePickerDialog.show();
            }
        });

    }
}