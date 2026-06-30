package com.dieselarena.sportapp.ui.trainer;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.dieselarena.sportapp.R;
import com.dieselarena.sportapp.ui.trainer.sportsmen.SportsmenFragment;
import com.dieselarena.sportapp.ui.trainer.trainings.TrainingsFragment;
import com.dieselarena.sportapp.ui.trainer.statistics.StatisticsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TrainerActivity extends AppCompatActivity {
    private int userId;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer);

        userId = getIntent().getIntExtra("user_id", -1);
        userName = getIntent().getStringExtra("user_name");

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            if (item.getItemId() == R.id.nav_sportsmen) {
                selectedFragment = new SportsmenFragment();
            } else if (item.getItemId() == R.id.nav_trainings) {
                selectedFragment = new TrainingsFragment();
            } else if (item.getItemId() == R.id.nav_statistics) {
                selectedFragment = new StatisticsFragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }
            return true;
        });

        if (savedInstanceState == null) {
            bottomNav.setSelectedItemId(R.id.nav_sportsmen);
        }
    }
}