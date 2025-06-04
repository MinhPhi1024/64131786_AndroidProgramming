package com.ntu.leminhphi.example.mathquizapp.Adapter_User;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.ntu.leminhphi.example.mathquizapp.Models_User.SubDoiTuongModels;
import com.ntu.leminhphi.example.mathquizapp.R;
import com.ntu.leminhphi.example.mathquizapp.User_Questions;
import com.ntu.leminhphi.example.mathquizapp.User_Sub;
import com.ntu.leminhphi.example.mathquizapp.databinding.RvSubdoituongDesignBinding;

import java.util.ArrayList;

public class SubDoiTuongAdapters extends RecyclerView.Adapter<SubDoiTuongAdapters.viewHolder>{

    Context context;
    ArrayList<SubDoiTuongModels> list;

    private String tenlopID;

    private String themdoituongID;

    public SubDoiTuongAdapters(String tenlopID, ArrayList<SubDoiTuongModels> list, Context context) {
        this.tenlopID = tenlopID;
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_subdoituong_design,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        SubDoiTuongModels model = list.get(position);
        holder.binding.tvTenBai.setText(model.getTenlop());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, User_Questions.class);
                intent.putExtra("tenlopID",tenlopID);
                intent.putExtra("baihoc",model.getKey());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        RvSubdoituongDesignBinding binding;
        public viewHolder(@NonNull View itemView){
            super(itemView);

            binding = RvSubdoituongDesignBinding.bind(itemView);

        }
    }
}
