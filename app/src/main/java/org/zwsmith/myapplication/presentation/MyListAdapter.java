package org.zwsmith.myapplication.presentation;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.zwsmith.myapplication.R;

import java.util.ArrayList;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {
    private ArrayList<String> titles;

    MyListAdapter(ArrayList<String> titles) {
        this.titles = titles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView titleTextView = holder.itemView.findViewById(R.id.title_tv);
        titleTextView.setText(titles.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public void updateTitles(ArrayList<String>  titles) {
        this.titles = titles;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View itemView;

        ViewHolder(View view) {
            super(view);
            itemView = view;
        }
    }
}
