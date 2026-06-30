package com.dieselarena.sportapp.ui.trainer.trainings;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.dieselarena.sportapp.R;
import com.dieselarena.sportapp.data.entity.Training;
import java.util.List;

public class TrainingsAdapter extends RecyclerView.Adapter<TrainingsAdapter.ViewHolder> {
    private List<Training> trainings;

    public TrainingsAdapter(List<Training> trainings) {
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

        String statusText;
        int statusColor;
        switch (training.getStatus()) {
            case "completed":
                statusText = "Выполнено";
                statusColor = R.color.status_completed;
                break;
            case "missed":
                statusText = "Пропущено";
                statusColor = R.color.status_missed;
                break;
            default:
                statusText = "Запланировано";
                statusColor = R.color.status_pending;
                break;
        }
        holder.statusText.setText(statusText);
        holder.statusText.setTextColor(holder.itemView.getContext().getColor(statusColor));

        holder.itemView.setOnClickListener(v -> {
            // можно добавить редактирование тренировки
        });
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