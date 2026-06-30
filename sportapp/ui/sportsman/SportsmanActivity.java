package com.dieselarena.sportapp.ui.sportsman;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.dieselarena.sportapp.R;
import com.dieselarena.sportapp.ui.sportsman.schedule.ScheduleFragment;
import com.dieselarena.sportapp.ui.sportsman.news.NewsFragment;
import com.dieselarena.sportapp.ui.sportsman.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SportsmanActivity extends AppCompatActivity {
    private int userId;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sportsman);

        userId = getIntent().getIntExtra("user_id", -1);
        userName = getIntent().getStringExtra("user_name");

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            if (item.getItemId() == R.id.nav_schedule) {
                selectedFragment = ScheduleFragment.newInstance(userId);
            } else if (item.getItemId() == R.id.nav_news) {
                selectedFragment = new NewsFragment();
            } else if (item.getItemId() == R.id.nav_profile) {
                selectedFragment = ProfileFragment.newInstance(userId);
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }
            return true;
        });

        // загружаем первый фрагмент
        if (savedInstanceState == null) {
            bottomNav.setSelectedItemId(R.id.nav_schedule);
        }
    }
}