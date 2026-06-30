package com.dieselarena.sportapp.ui.trainer.sportsmen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.dieselarena.sportapp.R;
import com.dieselarena.sportapp.data.entity.User;
import java.util.ArrayList;
import java.util.List;

public class SportsmenFragment extends Fragment {
    private RecyclerView recyclerView;
    private SportsmenAdapter adapter;
    private SportsmenViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sportsmen, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new SportsmenAdapter(new ArrayList<>(), sportsman -> {
            // открыть карточку спортсмена
            openSportsmanCard(sportsman);
        });
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(SportsmenViewModel.class);
        viewModel.getSportsmen().observe(getViewLifecycleOwner(), sportsmen -> {
            // группируем по группам
            adapter.updateData(sportsmen);
        });

        return view;
    }

    private void openSportsmanCard(User sportsman) {
        // диалог с информацией о спортсмене
        SportsmanCardDialog dialog = SportsmanCardDialog.newInstance(sportsman.getId());
        dialog.show(getChildFragmentManager(), "sportsman_card");
    }
}