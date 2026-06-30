package com.dieselarena.sportapp.ui.trainer.sportsmen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.dieselarena.sportapp.R;
import com.dieselarena.sportapp.data.entity.User;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SportsmenAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private List<Object> items;
    private OnSportsmanClickListener listener;

    public interface OnSportsmanClickListener {
        void onSportsmanClick(User sportsman);
    }

    public SportsmenAdapter(List<User> sportsmen, OnSportsmanClickListener listener) {
        this.listener = listener;
        updateData(sportsmen);
    }

    public void updateData(List<User> sportsmen) {
        items = new ArrayList<>();
        // группировка по groupId (упрощенно)
        Map<Integer, List<User>> grouped = new LinkedHashMap<>();
        for (User user : sportsmen) {
            if (!grouped.containsKey(user.getGroupId())) {
                grouped.put(user.getGroupId(), new ArrayList<>());
            }
            grouped.get(user.getGroupId()).add(user);
        }

        for (Map.Entry<Integer, List<User>> entry : grouped.entrySet()) {
            items.add("Группа " + entry.getKey());
            items.addAll(entry.getValue());
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position) instanceof String ? TYPE_HEADER : TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_1, parent, false);
            return new HeaderViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_sportsman, parent, false);
            return new ItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            ((HeaderViewHolder) holder).textView.setText((String) items.get(position));
        } else if (holder instanceof ItemViewHolder) {
            User sportsman = (User) items.get(position);
            ((ItemViewHolder) holder).nameText.setText(sportsman.getName());
            ((ItemViewHolder) holder).phoneText.setText(sportsman.getPhone());
            holder.itemView.setOnClickListener(v -> listener.onSportsmanClick(sportsman));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        HeaderViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView nameText;
        TextView phoneText;
        ItemViewHolder(View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.name_text);
            phoneText = itemView.findViewById(R.id.phone_text);
        }
    }
}