package com.ntu.leminhphi.example.mathquizapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ntu.leminhphi.example.mathquizapp.Admin_Sub;
import com.ntu.leminhphi.example.mathquizapp.Models.DoiTuongModels;
import com.ntu.leminhphi.example.mathquizapp.R;
import com.ntu.leminhphi.example.mathquizapp.databinding.RvDoituongDesignBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DoiTuongAdapters extends RecyclerView.Adapter<DoiTuongAdapters.viewHolder>{

    Context context;
    ArrayList<DoiTuongModels> list;
    public DoiTuongAdapters(Context context, ArrayList<DoiTuongModels> models) {
        this.context = context;
        this.list = models;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_doituong_design,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        DoiTuongModels model = list.get(position);
        holder.binding.tvCarimg.setText(model.getTenlop());
        Picasso.get()
                .load(model.getHinh())
                .placeholder(R.drawable.logo_mathquiz)
                .into(holder.binding.imgCar);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Admin_Sub.class);
                intent.putExtra("tenlopID",model.getKey());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        RvDoituongDesignBinding binding;
        public viewHolder(@NonNull View itemView){
            super(itemView);

            binding = RvDoituongDesignBinding.bind(itemView);

        }
    }
}
