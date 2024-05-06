package com.example.banhang.Fragment;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.banhang.Database.Adapter;
import com.example.banhang.Database.Database;
import com.example.banhang.Database.Refresh;
import com.example.banhang.Database.ThongBao;
import com.example.banhang.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment implements Refresh {
    RecyclerView recyclerView;
    List<ThongBao> thongBaosList;
    private Database database;
    //SwipeRefreshLayout swipeRefreshLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        database = new Database(getActivity());
        thongBaosList = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerHome);

        refreshData();
        return view;
    }
    public void addThongBao(ThongBao thongBao) {
        thongBaosList.add(thongBao);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    private void refreshData() {
        Cursor data = database.getData("SELECT * FROM note");
        thongBaosList.clear();
        while (data.moveToNext()) {
            int id = data.getInt(0);
            String title = data.getString(1);
            String note = data.getString(2);
            int notice = data.getInt(3);
            boolean setnotice = true;
            if(notice == 0){
                setnotice =false;
            }
            String start = data.getString(4);
            String ends = data.getString(5);

            if (note == null && notice == 0) {
                ThongBao thongBao = new ThongBao(id, title, start,ends);
                thongBaosList.add(thongBao);
            } else {
                if (notice == 0) {
                    ThongBao thongBao = new ThongBao(id, title,note, start,ends);
                    thongBaosList.add(thongBao);
                } else {
                    if (note == null) {
                        ThongBao thongBao = new ThongBao(id, title, start,ends, setnotice);
                        thongBaosList.add(thongBao);
                    } else {
                        ThongBao thongBao = new ThongBao(id, title, note, start,ends, setnotice);
                        thongBaosList.add(thongBao);
                    }
                }
            }
        }

        Adapter adapter = new Adapter(getActivity(), thongBaosList);
        adapter.setOnRefreshCompleteListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter); // Update adapter
    }

    @Override
    public void onRefrehComplete() {
        refreshData();
    }
}

