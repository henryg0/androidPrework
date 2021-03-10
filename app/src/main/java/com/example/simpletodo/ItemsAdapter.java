package com.example.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder>{
    public interface OnLongClickListener {
        void onItemLongClicked(int position);
    }

    List<String> items;
    OnLongClickListener longClickListener;

    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener) {
        this.items = items;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Use layout inflator to inflate a view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent,false);
        return new ViewHolder(todoView);
        // wrap it inside a View Holder and return it

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // grab item at the position
        String item = items.get(position);
        holder.bind(item);
        // bind the item into specified view holder
    }

    @Override
    public int getItemCount() {
        // tells Rv how many items are in list
        return items.size();
    }

    // Container to provide easy access to views that represent each row of the list
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        // Update view inside of the view holder w/ new data
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    // android notifies listener which position was long pressed
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
