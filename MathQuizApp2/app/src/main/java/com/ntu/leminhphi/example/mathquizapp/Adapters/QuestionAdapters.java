package com.ntu.leminhphi.example.mathquizapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ntu.leminhphi.example.mathquizapp.Models.DoiTuongModels;
import com.ntu.leminhphi.example.mathquizapp.Models.QuestionModels;
import com.ntu.leminhphi.example.mathquizapp.R;
import com.ntu.leminhphi.example.mathquizapp.databinding.RvSubdoituongDesignBinding;

import java.util.ArrayList;

public class QuestionAdapters extends RecyclerView.Adapter<QuestionAdapters.viewHolder>{

    Context context;
    ArrayList<QuestionModels> list;

    private String tenlopID;
    private String themdoituongID;

    public QuestionAdapters(Context context, ArrayList<QuestionModels> list, String tenlopID, String themdoituongID) {
        this.context = context;
        this.list = list;
        this.tenlopID = tenlopID;
        this.themdoituongID = themdoituongID;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_subdoituong_design,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        QuestionModels model = list.get(position);
        holder.binding.tvTenBai.setText(model.getQuestion());

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
