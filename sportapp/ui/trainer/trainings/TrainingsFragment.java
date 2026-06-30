package com.dieselarena.sportapp.ui.trainer.trainings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.dieselarena.sportapp.R;
import com.dieselarena.sportapp.data.entity.Training;
import com.dieselarena.sportapp.data.entity.User;
import java.util.ArrayList;
import java.util.List;

public class TrainingsFragment extends Fragment {
    private RecyclerView recyclerView;
    private TrainingsAdapter adapter;
    private TrainingsViewModel viewModel;
    private Button addButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trainings, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        addButton = view.findViewById(R.id.add_button);

        adapter = new TrainingsAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(TrainingsViewModel.class);
        viewModel.getTrainings().observe(getViewLifecycleOwner(), trainings -> {
            adapter.updateData(trainings);
        });

        addButton.setOnClickListener(v -> showAddTrainingDialog());

        return view;
    }

    private void showAddTrainingDialog() {
        // диалог для создания тренировки
        TrainingDialog dialog = new TrainingDialog();
        dialog.show(getChildFragmentManager(), "add_training");
    }
}