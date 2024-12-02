package org.catastream.ui.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.catastream.R;
import org.catastream.db.sqlLite.WishList;

import java.util.List;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder> {

    private List<WishList> wishLists;
    private final OnDeleteClickListener onDeleteClickListener;

    public CatalogAdapter(List<WishList> wishLists, OnDeleteClickListener onDeleteClickListener) {
        this.wishLists = wishLists;
        this.onDeleteClickListener = onDeleteClickListener;
    }

    @NonNull
    @Override
    public CatalogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.catalog_item, parent, false);
        return new CatalogViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CatalogViewHolder holder, int position) {
        WishList wishList = wishLists.get(position);

        holder.titleTextView.setText(wishList.getTitle());


        String posterUrl = wishList.getPosterPath();
        Glide.with(holder.itemView.getContext())
                .load(posterUrl)
                .placeholder(R.drawable.placeholder_image)
                .into(holder.posterImageView);

        holder.deleteButton.setOnClickListener(v -> {
            if (onDeleteClickListener != null) {
                onDeleteClickListener.onDeleteClick(wishList);
            }
        });
    }


    @Override
    public int getItemCount() {
        return wishLists.size();
    }

    public void updateWishLists(List<WishList> newWishLists) {
        this.wishLists = newWishLists;
        notifyDataSetChanged();
    }

    public static class CatalogViewHolder extends RecyclerView.ViewHolder {

        public ImageView posterImageView;
        public TextView titleTextView;
        public ImageView deleteButton;

        public CatalogViewHolder(View itemView) {
            super(itemView);
            posterImageView = itemView.findViewById(R.id.movie_poster);
            titleTextView = itemView.findViewById(R.id.movie_title);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(WishList wishList);
    }
}
