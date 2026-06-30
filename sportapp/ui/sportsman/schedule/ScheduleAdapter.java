package com.dieselarena.sportapp.ui.sportsman.schedule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.dieselarena.sportapp.R;
import com.dieselarena.sportapp.data.entity.Training;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {
    private List<Training> trainings;

    public ScheduleAdapter(List<Training> trainings) {
        this.trainings = trainings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_training, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Training training = trainings.get(position);
        holder.dateText.setText(training.getDate());
        holder.timeText.setText(training.getTime());

        // цвет статуса
        int statusColor;
        String statusText;
        switch (training.getStatus()) {
            case "completed":
                statusColor = R.color.status_completed;
                statusText = "Выполнено";
                break;
            case "missed":
                statusColor = R.color.status_missed;
                statusText = "Пропущено";
                break;
            default:
                statusColor = R.color.status_pending;
                statusText = "Запланировано";
                break;
        }
        holder.statusText.setText(statusText);
        holder.statusText.setTextColor(holder.itemView.getContext().getColor(statusColor));
    }

    @Override
    public int getItemCount() {
        return trainings.size();
    }

    public void updateData(List<Training> newTrainings) {
        this.trainings = newTrainings;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateText;
        TextView timeText;
        TextView statusText;

        ViewHolder(View itemView) {
            super(itemView);
            dateText = itemView.findViewById(R.id.date_text);
            timeText = itemView.findViewById(R.id.time_text);
            statusText = itemView.findViewById(R.id.status_text);
        }
    }
}