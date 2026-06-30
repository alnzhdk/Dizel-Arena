package com.dieselarena.sportapp.ui.trainer.trainings;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import com.dieselarena.sportapp.R;

public class ExerciseDialog extends DialogFragment {
    private EditText nameEditText;
    private EditText weightEditText;
    private EditText setsEditText;
    private EditText timeEditText;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_exercise, null);

        nameEditText = view.findViewById(R.id.name_edittext);
        weightEditText = view.findViewById(R.id.weight_edittext);
        setsEditText = view.findViewById(R.id.sets_edittext);
        timeEditText = view.findViewById(R.id.time_edittext);

        builder.setView(view)
                .setTitle("Добавить упражнение")
                .setPositiveButton("Сохранить", (dialog, which) -> {
                    String name = nameEditText.getText().toString().trim();
                    String weightStr = weightEditText.getText().toString().trim();
                    String setsStr = setsEditText.getText().toString().trim();
                    String timeStr = timeEditText.getText().toString().trim();

                    if (name.isEmpty()) {
                        Toast.makeText(getContext(), "Введите название", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // здесь можно сохранить упражнение в БД
                    Toast.makeText(getContext(), "Упражнение добавлено", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Отмена", (dialog, which) -> dismiss());

        return builder.create();
    }
}