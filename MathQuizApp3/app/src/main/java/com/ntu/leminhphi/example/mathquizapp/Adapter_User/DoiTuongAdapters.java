package com.ntu.leminhphi.example.mathquizapp.Adapter_User;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ntu.leminhphi.example.mathquizapp.MainActivity;

import com.ntu.leminhphi.example.mathquizapp.Models_User.DoiTuongModels;
import com.ntu.leminhphi.example.mathquizapp.R;
import com.ntu.leminhphi.example.mathquizapp.User_Sub;
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
        RvDoituongDesignBinding binding = RvDoituongDesignBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new viewHolder(binding);
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
                Intent intent = new Intent(context, User_Sub.class);
                intent.putExtra("tenlopID", model.getKey());
                intent.putExtra("baihoc", model.getTenlop());
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

        public viewHolder(@NonNull RvDoituongDesignBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

