package com.dieselarena.sportapp.ui.trainer.sportsmen;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import com.dieselarena.sportapp.R;
import com.dieselarena.sportapp.data.entity.User;

public class SportsmanCardDialog extends DialogFragment {
    private int sportsmanId;
    private TextView nameText;
    private TextView phoneText;
    private TextView groupText;

    public static SportsmanCardDialog newInstance(int sportsmanId) {
        SportsmanCardDialog dialog = new SportsmanCardDialog();
        Bundle args = new Bundle();
        args.putInt("sportsman_id", sportsmanId);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            sportsmanId = getArguments().getInt("sportsman_id");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_sportsman_card, null);

        nameText = view.findViewById(R.id.name_text);
        phoneText = view.findViewById(R.id.phone_text);
        groupText = view.findViewById(R.id.group_text);

        // загружаем данные спортсмена
        loadSportsmanData();

        builder.setView(view)
                .setTitle("Карточка спортсмена")
                .setPositiveButton("Закрыть", (dialog, which) -> dismiss());

        return builder.create();
    }

    private void loadSportsmanData() {
        SportsmenViewModel viewModel = new ViewModelProvider(requireActivity()).get(SportsmenViewModel.class);
        viewModel.getSportsmen().observe(this, sportsmen -> {
            for (User user : sportsmen) {
                if (user.getId() == sportsmanId) {
                    nameText.setText(user.getName());
                    phoneText.setText("Телефон: " + user.getPhone());
                    groupText.setText("Группа: " + user.getGroupId());
                    break;
                }
            }
        });
    }
}