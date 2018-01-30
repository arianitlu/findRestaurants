package com.example.pluscomputers.publictoilet2;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.media.Image;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ListLocationAdapter extends RecyclerView.Adapter<ListLocationAdapter.MyViewHolder> {

    private List<ListLocation> listLocations;
    private Context ctx;
    private Activity act;
    double latGPS;
    double lonGPS;

    public ListLocationAdapter(List<ListLocation> listLocations, Context ctx, Activity act) {
        this.listLocations = listLocations;
        this.ctx = ctx;
        this.act = act;

        callGPS();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView name,type,distance;
        public ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            name = itemView.findViewById(R.id.name1);
            type = itemView.findViewById(R.id.type);
            distance = itemView.findViewById(R.id.distance);
            imageView = itemView.findViewById(R.id.circleImageView);
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
        holder.distance.setText(distanceBetweenPoints(list.getmLatitude(), list.getmLongitude()) + " km");
        //holder.imageView.setImageResource(list.getImg());

    }

    @Override
    public int getItemCount() {
        return listLocations.size();
    }

    public String distanceBetweenPoints(double lat, double lng) {
        float results[] = new float[10];

        Location.distanceBetween(latGPS, lonGPS,
                lat, lng, results);

        String distanca = String.valueOf(results[0] / 1000);

        String distancaShkurtuar = distanca.substring(0, 4);

        return distancaShkurtuar;
    }

    public void callGPS() {
        ActivityCompat.requestPermissions(act, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION}, 123);

        GPStracker g = new GPStracker(ctx);
        Location l = g.getLocation();

        if (l != null) {
            latGPS = l.getLatitude();
            lonGPS = l.getLongitude();
        }

    }
}