package com.dieselarena.sportapp.ui.trainer.statistics;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.dieselarena.sportapp.R;
import java.util.Locale;

public class StatisticsFragment extends Fragment {
    private TextView completedText;
    private TextView missedText;
    private TextView totalText;
    private StatisticsViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistics, container, false);

        completedText = view.findViewById(R.id.completed_text);
        missedText = view.findViewById(R.id.missed_text);
        totalText = view.findViewById(R.id.total_text);

        viewModel = new ViewModelProvider(this).get(StatisticsViewModel.class);
        viewModel.getStatistics().observe(getViewLifecycleOwner(), stats -> {
            completedText.setText(String.format(Locale.getDefault(), "Выполнено: %d", stats.completed));
            missedText.setText(String.format(Locale.getDefault(), "Пропущено: %d", stats.missed));
            totalText.setText(String.format(Locale.getDefault(), "Всего: %d", stats.total));
        });

        return view;
    }
}