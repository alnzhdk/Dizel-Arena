package com.dieselarena.sportapp.ui.trainer.trainings;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import com.dieselarena.sportapp.R;
import com.dieselarena.sportapp.data.entity.Training;
import com.dieselarena.sportapp.data.entity.User;
import com.dieselarena.sportapp.ui.trainer.sportsmen.SportsmenViewModel;
import java.util.ArrayList;
import java.util.List;

public class TrainingDialog extends DialogFragment {
    private EditText dateEditText;
    private EditText timeEditText;
    private Spinner sportsmanSpinner;
    private Spinner statusSpinner;
    private TrainingsViewModel viewModel;
    private List<User> sportsmenList;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_training, null);

        dateEditText = view.findViewById(R.id.date_edittext);
        timeEditText = view.findViewById(R.id.time_edittext);
        sportsmanSpinner = view.findViewById(R.id.sportsman_spinner);
        statusSpinner = view.findViewById(R.id.status_spinner);

        // статусы тренировки
        String[] statuses = {"scheduled", "completed", "missed"};
        ArrayAdapter<String> statusAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                statuses
        );
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(statusAdapter);

        // загружаем список спортсменов
        SportsmenViewModel sportsmenViewModel = new ViewModelProvider(requireActivity())
                .get(SportsmenViewModel.class);
        sportsmenViewModel.getSportsmen().observe(this, users -> {
            sportsmenList = users;
            List<String> names = new ArrayList<>();
            for (User user : users) {
                names.add(user.getName());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    names
            );
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sportsmanSpinner.setAdapter(adapter);
        });

        viewModel = new ViewModelProvider(requireActivity()).get(TrainingsViewModel.class);

        builder.setView(view)
                .setTitle("Создание тренировки")
                .setPositiveButton("Сохранить", (dialog, which) -> saveTraining())
                .setNegativeButton("Отмена", (dialog, which) -> dismiss());

        return builder.create();
    }

    private void saveTraining() {
        String date = dateEditText.getText().toString().trim();
        String time = timeEditText.getText().toString().trim();

        if (date.isEmpty() || time.isEmpty()) {
            Toast.makeText(getContext(), "Заполните дату и время", Toast.LENGTH_SHORT).show();
            return;
        }

        int sportsmanPosition = sportsmanSpinner.getSelectedItemPosition();
        if (sportsmanPosition < 0 || sportsmenList == null || sportsmenList.isEmpty()) {
            Toast.makeText(getContext(), "Выберите спортсмена", Toast.LENGTH_SHORT).show();
            return;
        }

        User selectedSportsman = sportsmenList.get(sportsmanPosition);
        String status = statusSpinner.getSelectedItem().toString();

        Training training = new Training(date, time, status, selectedSportsman.getId());
        viewModel.insertTraining(training);

        Toast.makeText(getContext(), "Тренировка создана", Toast.LENGTH_SHORT).show();
        dismiss();
    }
}