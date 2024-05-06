package com.example.banhang.Fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
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

import com.example.banhang.Activity.HomeActivity;
import com.example.banhang.Database.Database;
import com.example.banhang.R;

import java.util.Calendar;
import java.util.Date;

public class FragmentAdd extends Fragment {
    EditText editNote, editTitle;
    Switch switchNote, switchSetNoti;
    boolean notice;
    String dateStart, dateEnd, timeStart, timeEnd;
    Button btnTimeStart, btnTimeEnds,btnDateEnds,btnDateStart, btnSave;
    Calendar calendar1 = Calendar.getInstance();
    Calendar calendar2 = Calendar.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        Database database = new Database(getActivity());
        anhxa(view);
        notice = false;
        switchSetNoti.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    notice = true;
                }else{
                    notice = false;
                }
            }
        });
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

        //setDateStart
         btnDateStart.setOnClickListener(v -> {
             //Tạo biến calendar để lấy ngày tháng năm
             Calendar calendar = Calendar.getInstance();

             int day = calendar.get(Calendar.DAY_OF_MONTH)+ 1;
             int month = calendar.get(Calendar.MONTH)+ 1;
             int year = calendar.get(Calendar.YEAR);

             //Tạo 1 DatePickerDialog để chọn ngày tháng năm
             DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                 @Override
                 public void onDateSet(DatePicker view1, int year, int month, int dayOfMonth) {
                     btnDateStart.setText(String.valueOf(dayOfMonth)+"/"+String.valueOf(month)+"/"+String.valueOf(year));
                     dateStart = String.valueOf(dayOfMonth)+"/"+String.valueOf(month)+"/"+String.valueOf(year);
                     calendar1.set(year, month, dayOfMonth);
                 }
             }, year, month,day);
             //Tạo xong rồi thi show ra
             datePickerDialog.show();
         });
        //setDateEnd
        btnDateEnds.setOnClickListener(v -> {
            //Tạo biến calendar để lấy ngày tháng năm
            Calendar calendar = Calendar.getInstance();

            int day = calendar.get(Calendar.DAY_OF_MONTH)+ 1;
            int month = calendar.get(Calendar.MONTH)+ 1;
            int year = calendar.get(Calendar.YEAR);

            //Tạo 1 DatePickerDialog để chọn ngày tháng năm
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view12, int year, int month, int dayOfMonth) {
                    btnDateEnds.setText(String.valueOf(dayOfMonth)+"/"+String.valueOf(month)+"/"+String.valueOf(year));
                    dateEnd = String.valueOf(dayOfMonth)+"/"+String.valueOf(month)+"/"+String.valueOf(year);
                    calendar2.set(year, month, dayOfMonth);
                }
            }, year, month,day);
            //Tạo xong rồi thi show ra
            datePickerDialog.show();
        });

        //setTimeStart
        btnTimeStart.setOnClickListener(v -> {
            //Tạo biến calendar để lấy ngày tháng năm
            Calendar calendar = Calendar.getInstance();

            int hour = calendar.get(Calendar.HOUR);
            int minute = calendar.get(Calendar.MINUTE);
            //Tạo 1 DatePickerDialog để chọn ngày tháng năm
            TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view13, int hourOfDay, int minute) {
                    btnTimeStart.setText(String.valueOf(hourOfDay)+":"+String.valueOf(minute));
                    timeStart= String.valueOf(hourOfDay)+":"+String.valueOf(minute);
                }
            }, hour, minute,true);

            timePickerDialog.show();
        });

        //setTimeEnd
        btnTimeEnds.setOnClickListener(v -> {
            //Tạo biến calendar để lấy ngày tháng năm
            Calendar calendar = Calendar.getInstance();

            int hour = calendar.get(Calendar.HOUR);
            int minute = calendar.get(Calendar.MINUTE);

            //Tạo 1 DatePickerDialog để chọn ngày tháng năm
            TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view14, int hourOfDay, int minute) {
                    btnTimeEnds.setText(String.valueOf(hourOfDay)+":"+String.valueOf(minute));
                    timeEnd= String.valueOf(hourOfDay)+":"+String.valueOf(minute);
                }
            }, hour, minute,true);

            timePickerDialog.show();
        });;

        btnSave.setOnClickListener(v -> {
            String note = editNote.getText().toString().trim();
            String title = editTitle.getText().toString().trim();
            String start = timeStart + " " +dateStart;
            String end = timeEnd + " " + dateEnd;

            if(title.isEmpty()){
                Toast.makeText(getActivity(), "Please enter the title", Toast.LENGTH_SHORT).show();
            }else {
                if (timeStart == null || dateStart == null || timeEnd == null || dateEnd == null) {
                    Toast.makeText(getActivity(), "Please select an event time", Toast.LENGTH_SHORT).show();
                } else {
                    int comparisonResult = calendar1.compareTo(calendar2);
                    if (comparisonResult > 0) {
                        Toast.makeText(getActivity(), "Start date must be earlier than end date", Toast.LENGTH_SHORT).show();
                    } else {
                        if (note.isEmpty() && !notice) {
                            String sql = "INSERT INTO note VALUES(null, '" + title + "', null, false, '" + start + "', '" + end + "')";
                            add(sql, database);
                        } else {
                            if (!notice) {
                                String sql = "INSERT INTO note VALUES(null, '" + title + "','" + note + "',false, '" + start + "', '" + end + "')";
                                add(sql, database);
                            } else {
                                if (note.isEmpty()) {
                                    String sql = "INSERT INTO note VALUES(null, '" + title + "',null,true, '" + start + "', '" + end + "')";
                                    add(sql, database);
                                } else {
                                    String sql = "INSERT INTO note VALUES(null, '" + title + "','" + note + "',true , '" + start + "', '" + end + "')";
                                    add(sql, database);
                                }
                            }
                        }
                    }
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    public void anhxa(View view){
        switchSetNoti = (Switch) view.findViewById(R.id.switch2);
        editTitle = (EditText) view.findViewById(R.id.editTitle);
        btnSave = (Button) view.findViewById(R.id.btnSave);
        switchNote = (Switch) view.findViewById(R.id.switchNote);
        editNote = (EditText) view.findViewById(R.id.editNote);
        btnTimeEnds = (Button) view.findViewById(R.id.btnTimeEnds);
        btnTimeStart = (Button) view.findViewById(R.id.btnTimeStart);
        btnDateEnds = (Button) view.findViewById(R.id.btnDateEnds);
        btnDateStart = (Button) view.findViewById(R.id.btnDateStart);
    }

    public void add(String sql, Database database){
        database.creatData(sql);
        Toast.makeText(getActivity(), "Add successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}



//
//                SharedViewModel viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
//                List<ThongBao> list = viewModel.getThongBaoList().getValue();
//                if (list == null) {
//                    list = new ArrayList<>();
//                }
//                if(note == null){
//                    list.add(new ThongBao(title, start, end, notice));
//                    Toast.makeText(getActivity(), "Add successfully", Toast.LENGTH_SHORT).show();
//                }else {
//                    if (notice == false){
//                        list.add(new ThongBao(title, note, start, end));
//                        Toast.makeText(getActivity(), "Add successfully", Toast.LENGTH_SHORT).show();
//                    }else{
//                        if(note == null && notice == false){
//                            list.add(new ThongBao(title, start, end));
//                            Toast.makeText(getActivity(), "Add successfully", Toast.LENGTH_SHORT).show();
//                        }else{
//                            list.add(new ThongBao(title, note, start, end, notice));
//                            Toast.makeText(getActivity(), "Add successfully", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
//                viewModel.setThongBaoList(list);

