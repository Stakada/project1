package com.example.shotatakada.project1;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterTraffic extends RecyclerView.Adapter<AdapterTraffic.ViewHolder> {
    private Context context;
    private ArrayList<trafficItems> arr;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public AdapterTraffic(Context context, ArrayList<trafficItems> arr){
        this.context = context;
        this.arr = arr;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.trafficitems, parent,
                false);
        ViewHolder vh = new ViewHolder(item);
        return vh;
    }

    @Override
    public void onBindViewHolder(AdapterTraffic.ViewHolder holder, int position) {
        trafficItems currItem = arr.get(position);
        holder.mLabel.setText(currItem.getLabel());
        Glide.with(context).load(currItem.getImage()).into(holder.mImage);
    }


    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mLabel;
        public ImageView mImage;

        public ViewHolder(View view) {
            super(view);
            mLabel = (TextView) view.findViewById(R.id.label);
            mImage = (ImageView) view.findViewById(R.id.imageView);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }

                }
            });
        }
    }
}
