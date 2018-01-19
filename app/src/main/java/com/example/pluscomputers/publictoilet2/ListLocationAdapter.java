package com.example.pluscomputers.publictoilet2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ListLocationAdapter extends RecyclerView.Adapter<ListLocationAdapter.MyViewHolder> {

    private List<ListLocation> listLocations;
    private Context ctx;
    String distanca1;

    public ListLocationAdapter(List<ListLocation> listLocations, Context ctx){
        this.listLocations = listLocations;
        this.ctx = ctx;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView name,type,distance;

        public MyViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            name = itemView.findViewById(R.id.name);
            type = itemView.findViewById(R.id.type);
            distance = itemView.findViewById(R.id.distance);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            ListLocation list = listLocations.get(position);

            Intent intent = new Intent(ctx, ListLocationsDetailsActivity.class);
            intent.putExtra("name", list.getName());
            intent.putExtra("latitude",list.getmLatitude());
            intent.putExtra("longitude",list.getmLongitude());
            ctx.startActivity(intent);
        }
    }

    
    @Override
    public ListLocationAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_with_image, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListLocationAdapter.MyViewHolder holder, int position) {
        ListLocation list = listLocations.get(position);

        holder.name.setText(list.getName());
        holder.type.setText(list.getType());
        holder.distance.setText(list.getDistance());
        //holder.distance.setText(distanca1);
        }


    /**public void distancaRe(String distance)
    {
       distanca1 = list.getDistance();
       distanca1 = distance;
    }**/

    @Override
    public int getItemCount() {
        return listLocations.size();
    }
}