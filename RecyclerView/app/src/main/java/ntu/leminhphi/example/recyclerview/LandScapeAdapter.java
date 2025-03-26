package ntu.leminhphi.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class LandScapeAdapter extends RecyclerView.Adapter<LandScapeAdapter.ItemLandHolder> {
    Context context;
    ArrayList<LandScape> listDataLand;
    public LandScapeAdapter(Context context, ArrayList<LandScape> listDataLand) {
        this.context = context;
        this.listDataLand = listDataLand;
    }

    @NonNull
    @Override
    public ItemLandHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_land, parent, false);
        ItemLandHolder viewHolderCre = new ItemLandHolder(view);
        return viewHolderCre;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemLandHolder holder, int position) {
        //Lấy đối tượng hiển thị
        LandScape landScapeHienThi = listDataLand.get(position);
        //Trích thông tin
        String caption = landScapeHienThi.getLandCation();
        String tenFileAnh = landScapeHienThi.getLandImageFileName();
        //Đặt cho các trường thông tin qua tên
        holder.textCaption.setText(caption);
        //đặt ảnh
            String packageName = holder.itemView.getContext().getPackageName();
            int imageID = holder.itemView.getResources().getIdentifier(tenFileAnh, "mipmap", packageName);
        holder.imageViewLand.setImageResource(imageID);

    }

    @Override
    public int getItemCount() {
        return listDataLand.size();
    }

    class ItemLandHolder extends RecyclerView.ViewHolder {
        TextView textCaption;
        ImageView imageViewLand;

        public ItemLandHolder(@NonNull View itemView) {
            super(itemView);
            textCaption = itemView.findViewById(R.id.textViewLand);
            imageViewLand = itemView.findViewById(R.id.imageViewLand);

        }
    }
}
