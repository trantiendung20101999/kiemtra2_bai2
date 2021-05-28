package com.example.trantiendung_ktra2_bai2;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LichThiRevAdapter extends RecyclerView.Adapter<LichThiRevAdapter.LichThiHolder> {


    List<LichThi> list;

    public LichThiRevAdapter() {
    }

    public LichThiRevAdapter(List<LichThi> list) {
        this.list = list;
    }

    public List<LichThi> getList() {
        return list;
    }

    public void setList(List<LichThi> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LichThiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new LichThiHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LichThiHolder holder, int position) {


        LichThi lichThi = list.get(position);
        if(lichThi!=null)
        {
            holder.txtIdName.setText(lichThi.getId()+"-"+lichThi.getTenmonhoc());
            holder.txtType.setText(lichThi.getType());
            holder.txtGiothi.setText(lichThi.getTime());
            holder.txtNgayThi.setText(lichThi.getDate());
            holder.itemClickListener= new ItemClickListener() {
                @Override
                public void onClick(View view, int position) {
                    Intent intent = new Intent(view.getContext(),UpdateActivity.class);
                    intent.putExtra("lichthi",lichThi);
                    view.getContext().startActivity(intent);
                }
            };
        }
    }

    @Override
    public int getItemCount() {
        if (list!=null)
        {
            return list.size();
        }
        return 0;
    }

    public interface ItemClickListener{
        public void onClick(View view , int position);

    }
    class LichThiHolder extends RecyclerView.ViewHolder{

        TextView txtIdName, txtNgayThi,txtGiothi,txtType;

        ItemClickListener itemClickListener;

        public LichThiHolder(@NonNull View itemView) {
            super(itemView);
            txtIdName = itemView.findViewById(R.id.txtNameId);
            txtNgayThi = itemView.findViewById(R.id.txtNgaythi);
            txtGiothi = itemView.findViewById(R.id.txtGiothi);
            txtType = itemView.findViewById(R.id.txtType);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onClick(v,getAdapterPosition());
                }
            });
        }
    }
}
