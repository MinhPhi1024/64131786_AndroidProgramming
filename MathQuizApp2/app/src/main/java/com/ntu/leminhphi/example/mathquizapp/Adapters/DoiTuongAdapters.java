package com.ntu.leminhphi.example.mathquizapp.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
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

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc chắn muốn xóa?");

                builder.setPositiveButton("Có", (dialogInterface, i) -> {
                    FirebaseDatabase.getInstance().getReference().child("tenlop")
                            .child(model.getKey())
                            .removeValue()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
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

        RvDoituongDesignBinding binding;
        public viewHolder(@NonNull View itemView){
            super(itemView);

            binding = RvDoituongDesignBinding.bind(itemView);

        }
    }
}
