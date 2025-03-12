package com.example.nihongo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.nihongo.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher_nihongo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        setUpNavigation();
    }

    private void setUpNavigation() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_activity_main);
        navController = navHostFragment.getNavController();

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_kana_chart, R.id.nav_profile,
                R.id.BLessonsFragment, R.id.BExercisesFragment, R.id.BVideosFragment,
                R.id.TLessonsFragment, R.id.TExercisesFragment, R.id.TVideosFragment)
                .build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.navView.setOnItemSelectedListener(item -> navigateToSelectedFragment(item.getItemId()));
    }

    private boolean navigateToSelectedFragment(int itemId){
        if (itemId == R.id.nav_home) {
            SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            String courseType = sharedPreferences.getString("courseType", "Beginner");

            int currentDestinationId = navController.getCurrentDestination().getId();

            if ("Traveler".equals(courseType) && currentDestinationId != R.id.Thome) {
                navController.navigate(R.id.Thome);
            } else {
                navController.navigate(R.id.nav_home);
            }
            return true;
        }
        else if (itemId == R.id.nav_kana_chart) {
            navController.navigate(R.id.nav_kana_chart);
            return true;
        } else if (itemId == R.id.nav_translator) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://translate.google.co.jp/?hl=en&sl=en&tl=ja&op=translate"));
            startActivity(intent);
            return true;
        } else if (itemId == R.id.nav_profile) {
            navController.navigate(R.id.nav_profile);
            return true;
        }
        return false;
    }
}
