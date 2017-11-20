package com.sbello.jokesharingapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by socbe on 11/11/2017.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private final Context context;
    private List<Item> items;

    public ItemAdapter(Context context) {
        this.context = context;
        this.items = new ArrayList<>();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        final Item item = this.items.get(position);
        holder.mTitleTextView.setText(item.audio);
        holder.mDescTextView.setText(item.description);
        holder.mEmailTextView.setText(item.email);
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public void addItem(Item item) {
        this.items.add(0, item);
        this.notifyItemInserted(0);
    }

    public void updateItem(Item item) {
        int position = this.items.indexOf(item);
        if (position >= 0) {
            this.items.remove(position);
            this.items.add(position, item);
        }
        this.notifyItemChanged(position);
    }

    public void removeItem(Item item) {
        int position = this.items.indexOf(item);
        if (position >= 0) {
            this.items.remove(position);
        }
        this.notifyItemRemoved(position);
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTitleTextView;
        private final TextView mDescTextView;
        private final TextView mEmailTextView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView)  itemView.findViewById(R.id.item_title_text_view);
            mDescTextView = (TextView) itemView.findViewById(R.id.item_description_text_view);
            mEmailTextView = (TextView) itemView.findViewById(R.id.item_email_text_view);
        }
    }
}
