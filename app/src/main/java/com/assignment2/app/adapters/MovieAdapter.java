package com.assignment2.app.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment2.R;
import com.assignment2.app.omdbapi.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Movie item, int pos);
    }

    private List<Movie> items;
    private OnItemClickListener listener;

    // Constructor
    public MovieAdapter(OnItemClickListener listener) {
        this.items = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.omdb_items, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Movie item = items.get(position);

        holder.title.setText(item.getTitle());
        holder.studio.setText(item.getStudio());
        holder.year.setText(String.valueOf(item.getYear()));

        String rating = String.format(Locale.getDefault(), "%.2f", item.getRating());
        holder.rating.setText(rating);


        holder.parent.setOnClickListener(v -> listener.onItemClick(item, position));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void clear(){
        this.items.clear();
        this.notifyDataSetChanged();
    }

    public void add(Movie result){

        this.items.add(0, result);
        notifyItemInserted(0);

        Log.d("App", "New size:" + this.items.size());
    }

    // ViewHolder class
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, studio, year, rating;
        RelativeLayout parent;


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            parent = itemView.findViewById(R.id.rl_parent);
            title = itemView.findViewById(R.id.tv_title);
            studio = itemView.findViewById(R.id.tv_studio);
            year = itemView.findViewById(R.id.tv_year);
            rating = itemView.findViewById(R.id.tv_rating);

        }
    }
}
