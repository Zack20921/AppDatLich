package com.example.banhang.Database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.banhang.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.AdapterViewHolder>{
    Context context;
    private List<ThongBao> thongBaoList;
    private Refresh refresh;
    public Adapter(Context context, List<ThongBao> thongBaoList) {
        this.context = context;
        this.thongBaoList = thongBaoList;
    }
    public void setOnRefreshCompleteListener(Refresh listener) {
        this.refresh = listener;
    }
    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.plan_card,parent,false);
        return new AdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
            ThongBao thongBaos = thongBaoList.get(position);
            if(thongBaos == null){
                return;
            }
            holder.txtPlan.setText(thongBaos.getTitle());
            holder.txtTimeStart.setText(thongBaos.getStart());
            holder.txtTimeEnd.setText(thongBaos.getEnd());

            if(thongBaos.isNoti()){
                holder.imgNoti.setVisibility(View.VISIBLE);
            }

            String note = thongBaos.getNote();
            if(note!= null){
                holder.txtNote.setVisibility(View.VISIBLE);
                holder.txtNoteDetail.setVisibility(View.VISIBLE);
                holder.txtNoteDetail.setText(note);
            }

            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Are you sure you want to delete this plan?");

                    builder.setPositiveButton("Delete", (dialog, which) -> {
                        Database database = new Database(context);
                        database.creatData("DELETE FROM note WHERE id = '" + thongBaos.getId() + "'");
                        Toast.makeText(context, "Deleted successfully!", Toast.LENGTH_SHORT).show();
                        if (refresh != null) {
                            refresh.onRefrehComplete();
                        }
                    });
                    builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
                    builder.create().show();
                }
            });
    }

    @Override
    public int getItemCount() {
        return thongBaoList.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder{
        TextView txtNote, txtNoteDetail, txtTimeStart, txtTimeEnd, txtPlan;
        ImageView imgNoti,imgDelete;
        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNote = itemView.findViewById(R.id.txtNote);
            txtNoteDetail = itemView.findViewById(R.id.txtNoteDetail);
            txtTimeStart = itemView.findViewById(R.id.txtTimeStart);
            txtTimeEnd = itemView.findViewById(R.id.txtTimeEnd);
            txtPlan = itemView.findViewById(R.id.txtPlan);
            imgNoti= itemView.findViewById(R.id.imgNoti);
            imgDelete= itemView.findViewById(R.id.imgDelete);
        }
    }
}
