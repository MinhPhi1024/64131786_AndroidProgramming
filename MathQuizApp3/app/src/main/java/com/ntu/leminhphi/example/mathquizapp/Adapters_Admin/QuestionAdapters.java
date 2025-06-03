package com.ntu.leminhphi.example.mathquizapp.Adapters_Admin;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.ntu.leminhphi.example.mathquizapp.Models_Admin.QuestionModels;
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

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc chắn muốn xóa?");

                builder.setPositiveButton("Có", (dialogInterface, i) -> {
                    FirebaseDatabase.getInstance().getReference().child("tenlop").child(tenlopID).child("baihoc").child(themdoituongID)
                            .child("cauhoi").child(model.getKey())
                            .removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                }
                            });
                });

                builder.setNegativeButton("Không", (dialogInterface, i) -> {
                    dialogInterface.cancel();
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                return false;
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
