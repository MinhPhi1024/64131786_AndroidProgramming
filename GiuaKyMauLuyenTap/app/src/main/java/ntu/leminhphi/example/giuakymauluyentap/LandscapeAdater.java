package ntu.leminhphi.example.giuakymauluyentap;

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

public class LandscapeAdater extends RecyclerView.Adapter<LandscapeAdater.ItemViewHolder> {
    Context context;
    ArrayList<Landscape> dsLandscape;
    public LandscapeAdater(Context context, ArrayList<Landscape> dsLandscape) {
        this.context = context;
        this.dsLandscape = dsLandscape;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View item_view = inflater.inflate(R.layout.item_land, parent, false);
        ItemViewHolder viewHolderCre = new ItemViewHolder(item_view);
        return viewHolderCre;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Landscape landHienThi = dsLandscape.get(position);
        String landName = landHienThi.getLandName();
        String TenFile = landHienThi.getLandImageFileName();
        holder.txtLandName.setText(landName);

        String packageName = holder.imgLand.getContext().getPackageName();
        int id = context.getResources().getIdentifier(TenFile, "mipmap", context.getPackageName());
        holder.imgLand.setImageResource(id);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView txtLandName;
        ImageView imgLand;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            txtLandName = itemView.findViewById(R.id.tVLand);
            imgLand = itemView.findViewById(R.id.imgViewLand);

        }
    }

}
