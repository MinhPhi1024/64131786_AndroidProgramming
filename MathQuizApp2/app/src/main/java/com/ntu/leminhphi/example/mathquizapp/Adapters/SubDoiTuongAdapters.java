package com.ntu.leminhphi.example.mathquizapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ntu.leminhphi.example.mathquizapp.Models.SubDoiTuongModels;
import com.ntu.leminhphi.example.mathquizapp.R;
import com.ntu.leminhphi.example.mathquizapp.databinding.RvDoituongDesignBinding;
import com.ntu.leminhphi.example.mathquizapp.databinding.RvSubdoituongDesignBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SubDoiTuongAdapters extends RecyclerView.Adapter<SubDoiTuongAdapters.viewHolder>{

    Context context;
    ArrayList<SubDoiTuongModels> list;
    public SubDoiTuongAdapters(Context context, ArrayList<SubDoiTuongModels> models) {
        this.context = context;
        this.list = models;
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
